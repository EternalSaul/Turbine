package nchu.turbine.event;

import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.xml.stream.events.StartDocument;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.turn.ttorrent.common.Torrent;

import nchu.turbine.exception.TorrentFileNotFoundException;
import nchu.turbine.interfaces.service.IDownloadService;
import nchu.turbine.interfaces.service.IMagnetService;
import nchu.turbine.interfaces.service.ITasksDisplayService;
import nchu.turbine.view.TurbineView;

/**
 * 新任务下载按钮事件
 * @author Saulxk
 * </br>EditDate: 2017-05-24
 */
@Component
@Qualifier("downloadListener")
public class DownloadListener implements MouseListener{
	private JTextField savePath;
	private JTextField torrentPath;
	private JFrame frame;
	private JPanel filesDisplay;
	ExecutorService service=Executors.newFixedThreadPool(1);
	/**
	 * 磁力链接转化服务
	 */
	@Autowired
	private IMagnetService magnetService;
	
	@Autowired
	private ITasksDisplayService displayService;
	
	/**
	 * 下载服务
	 */
	@Autowired
	private IDownloadService downloadService;
	
	public void setSavePath(JTextField savePath) {
		this.savePath = savePath;
	}

	public void setTorrentPath(JTextField torrentPath) {
		this.torrentPath = torrentPath;
	}
	
	public void setFilesDisplay(JPanel filesDisplay) {
		this.filesDisplay = filesDisplay;
	}
	
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		String sp=savePath.getText().trim();
		String tp=torrentPath.getText().trim();
		File torrentfile=null,
			 savedirectory=null;
		
		//获取种子文件
		if(tp.startsWith("magnet:?xt=urn:btih:")){//磁力链接
			JOptionPane.showMessageDialog(frame, "点击确定开始检测磁力链接,等待弹出提示框","等待", JOptionPane.PLAIN_MESSAGE);
			Future<File> future=service.submit(new Callable<File>() {//构建线程Callable,并传递给线程池
				@Override
				public File call() throws Exception {
					return magnetService.Magnet2Torrent(tp);
				}
			});
			try {
				//阻塞线程
				torrentfile=future.get(2, TimeUnit.MINUTES);
			} catch (InterruptedException | ExecutionException | TimeoutException e1) {
				e1.printStackTrace();
			}
			if(torrentfile==null){
				JOptionPane.showMessageDialog(frame, "磁力链接检测失败，未找到对应种子文件!","错误", JOptionPane.WARNING_MESSAGE);
				frame.setVisible(false);
			}
//			else{
//				JOptionPane.showMessageDialog(frame, "检测成功开始下载","成功", JOptionPane.CLOSED_OPTION);
//			}
//			torrentfile=magnetService.Magnet2Torrent(tp);
		}else{
			torrentfile=new File(tp);
			if(!torrentfile.exists()){
				JOptionPane.showMessageDialog(frame, "找不到种子文件，种子文件路径错误!","错误", JOptionPane.WARNING_MESSAGE);
				frame.setVisible(false);
				throw new TorrentFileNotFoundException("找不到种子文件，种子文件路径错误!");
			}
		}
		
		int i=1;
		try {
			//列出文件清单让用户确定是否下载
			Torrent torrent=Torrent.load(torrentfile);
			displayService.displayTorrentFilesList(filesDisplay, torrent.getFilenames());
			
			//阻塞确定，返回0为确定下载
			i=JOptionPane.showConfirmDialog(frame,"检测成功，点击确定开始下载","种子加载成功",JOptionPane.YES_NO_OPTION);
			
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		//清空文件清单
		displayService.removeTorrentFilesList(filesDisplay);
		
		//用户确定下载
		if(i==0){
		//获取保存路径
		savedirectory=new File(sp);
		if(!savedirectory.exists())	savedirectory.mkdirs();
		
		//开始下载
		downloadService.startdownload(torrentfile, savedirectory);
		}
		
		//清空窗口信息
		savePath.setText("");
		torrentPath.setText("");
		
		//关闭窗口显示
		frame.setVisible(false);
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

}
