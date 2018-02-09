package nchu.turbine.aspect;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.util.Vector;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.turn.ttorrent.client.Client;
import com.turn.ttorrent.client.SharedTorrent;

import nchu.turbine.event.DeleteDownloadingTaskActionListener;
import nchu.turbine.event.OpenCompletedDownloadTaskActionListener;
import nchu.turbine.event.StopDownloadingTaskActionListener;
import nchu.turbine.view.DownloadingTaskPanel;

/**
 * 切面类用于拦截DownLoadingTaskDao的一些方法，让业务分离
 * @author Saulxk
 * </br>EditDate: 2017-05-23
 */
@Aspect
@Component
public class DownloadingTaskAccessAspect {
	@Pointcut("execution(** nchu.turbine.dao.DownLoadingTaskDao.save(java.util.Vector<nchu.turbine.view.DownloadingTaskPanel>))&&args(ts)")
	public void DownloadingTaskSaveInterface(Vector<DownloadingTaskPanel> ts){}
	
	@Pointcut("execution(java.util.Vector<nchu.turbine.view.DownloadingTaskPanel> nchu.turbine.dao.DownLoadingTaskDao.find())")
	public void DownloadingTaskFindInterface(){}
	
	/**
	 * 拦截IDownLoadingTaskDao的任务持久化方法
	 * 将任务向量拷贝为Client为null的任务后返回
	 * @param point
	 * @param ts
	 * @throws Throwable
	 * </br>EditDate: 2017-05-23
	 */
	@Around("DownloadingTaskSaveInterface(ts)")
	public void DownloadingTaskSaveAdvice(ProceedingJoinPoint point,Vector<DownloadingTaskPanel> ts) throws Throwable{
		System.out.println("下载中任务保存切面被执行");
		Object os[]=new Object[1];
		os[0]=cleanClient(ts);
		point.proceed(os);
	}
	
	/**
	 * 拦截IDownLoadingTaskDao取出正在下载任务的方法
	 * 把这些任务初始化后返回
	 * @param point
	 * @return
	 * @throws Throwable
	 * </br>EditDate: 2017-05-23
	 */
	@Around("DownloadingTaskFindInterface()")
	public Vector<DownloadingTaskPanel> DownloadingTaskFindAdvice(ProceedingJoinPoint point) throws Throwable{
		System.out.println("下载中任务取出切面被执行");
		return resetClient((Vector<DownloadingTaskPanel>) point.proceed());
	}
	
	
	/**
	 * 因为Client不可串行化，该方法则把其设置为NULL后的正在下载任务的拷贝向量返回给调用者
	 * @param ts		正在下载的任务向量
	 * @return
	 * </br>EditDate: 2017-05-23
	 */
	private Vector<DownloadingTaskPanel> cleanClient(Vector<DownloadingTaskPanel> ts){
		Vector<DownloadingTaskPanel> vd=new Vector<DownloadingTaskPanel>();
		for(DownloadingTaskPanel taskPanel:ts){
			DownloadingTaskPanel panel=taskPanel.serializableClone();
			panel.getStop().setText("继续");
			vd.add(panel);
		}
		return vd;
	}
	
	/**
	 * 该方法用于初始化正在下载任务
	 * @param vd		正在下载的任务向量
	 * @return
	 * @throws UnknownHostException
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * </br>EditDate: 2017-05-23
	 */
	private Vector<DownloadingTaskPanel> resetClient(Vector<DownloadingTaskPanel> vd) throws UnknownHostException, IOException, NoSuchAlgorithmException{
		Vector<DownloadingTaskPanel> ts=new Vector<DownloadingTaskPanel>();
		for(DownloadingTaskPanel taskPanel:vd){
			System.out.println("dao.torrent:"+taskPanel.getTorrent());
			System.out.println("dao.save:"+taskPanel.getSaveDirectory());
			SharedTorrent torrent=SharedTorrent.fromFile(taskPanel.getTorrent(),taskPanel.getSaveDirectory());
			Client client=new Client(InetAddress.getLocalHost(), torrent);
			client.setMaxDownloadRate(500.0);
			client.setMaxUploadRate(10.0);
			taskPanel.setClient(client);
			taskPanel.getStop().addActionListener(new StopDownloadingTaskActionListener(client,taskPanel));
			taskPanel.getDelete().addActionListener(new DeleteDownloadingTaskActionListener(client, taskPanel));
			taskPanel.getOpenFile().addActionListener(new OpenCompletedDownloadTaskActionListener(taskPanel.getSaveDirectory()));
			ts.add(taskPanel);
		}
		return ts;
	}
	
}
