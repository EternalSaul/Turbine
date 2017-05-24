package nchu.turbine.service;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.turn.ttorrent.client.Client;
import com.turn.ttorrent.client.SharedTorrent;

import nchu.turbine.exception.TurbineException;
import nchu.turbine.interfaces.service.IDownloadService;
import nchu.turbine.interfaces.service.ITasksDisplayService;
import nchu.turbine.thread.DownloadingObserver;
import nchu.turbine.view.DownloadingTaskPanel;
@Component
public class DownloadService extends BaseService implements IDownloadService{
	
	/**
	 * 下载列表显示服务
	 */
	@Autowired
	ITasksDisplayService displayService;
	
	DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	final static long MB=1024*1024;
	final static long GB=MB*1024;
	
	/**
	 * 开始下载任务
	 * @param torrentfile			种子文件
	 * @param directorty			下载路径
	 * </br>EditDate: 2017-05-21
	 */
	@Override
	public void startdownload(File torrentfile,File directory){
		new Thread(new Runnable() {
			public void run() {
			try {
					SharedTorrent torrent=SharedTorrent.fromFile(torrentfile, directory);
					//创建Torrent客户端
					Client client=new Client(InetAddress.getLocalHost(), torrent);
					System.out.println("开始下载任务");
					client.setMaxDownloadRate(500.0);
					client.setMaxUploadRate(10.0);
					
					//开始下载
					client.download();
					
					DownloadingTaskPanel taskPanel=displayService.addDownloadingTasks(torrent.getName(),
							sizeInfo(torrent.getSize()), torrent.getFilenames().size(), client, System.currentTimeMillis(),directory,torrentfile);
					
					//阻塞直到文件下载完成
					client.waitForCompletion();	
					
					//验证是暂停任务还是完成
					validateComplete(torrent, taskPanel, directory);
					
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}	
	
	
	@Override
	public void startdownload(Client client,DownloadingTaskPanel taskPanel,File directory){
		new Thread(new Runnable() {
			public void run() {
				
					client.setMaxDownloadRate(500.0);
					client.setMaxUploadRate(10.0);
					
					//开始下载
					client.download();
					
					//添加观察
					client.addObserver(new DownloadingObserver(taskPanel));
					
					//获取种子
					SharedTorrent torrent=client.getTorrent();
					
					//阻塞等待停止或中断
					client.waitForCompletion();
					
					//验证是暂停任务还是完成
					validateComplete(torrent, taskPanel, directory);

					
			}
		}).start();
	}	
	/**
	 * 计算任务大小信息
	 * @param size		任务总字节数
	 * @return
	 * </br>EditDate: 2017-05-21
	 */
	protected String sizeInfo(long size){
		if(size>GB){
			return String.valueOf(((double)size/GB))+"MB";
		}else if(size>MB){
			return String.valueOf(((double)size/MB))+"MB";
		}else if(size>1024){
			return String.valueOf(size/1024)+"KB";
		}
		return String.valueOf(size)+"B";
	}
	
	protected void validateComplete(SharedTorrent torrent,DownloadingTaskPanel taskPanel,File directory){
		if(torrent.isComplete()){//检测任务是否完成
			exceptionService.handleException(new TurbineException(torrent.getName()+"文件已经下载!"),"提示",JOptionPane.DEFAULT_OPTION);
			displayService.removeDownloadingTasks(taskPanel);
			displayService.addCompletedTasks(torrent.getName(),sizeInfo(torrent.getSize()), format.format(System.currentTimeMillis()), directory);
		}else{
			exceptionService.handleException(new TurbineException(torrent.getName()+"任务已暂停!"),"提示",JOptionPane.DEFAULT_OPTION);
		}
	}
}
