package nchu.turbine.event;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;

import javax.swing.JButton;

import org.springframework.beans.factory.annotation.Autowired;

import com.turn.ttorrent.client.Client;
import com.turn.ttorrent.client.SharedTorrent;
import com.turn.ttorrent.client.Client.ClientShutdown;

import nchu.turbine.interfaces.service.IDownloadService;
import nchu.turbine.interfaces.service.ITasksDisplayService;
import nchu.turbine.thread.DownloadingObserver;
import nchu.turbine.view.DownloadingTaskPanel;
import nchu.turbine.view.TurbineView;

/**
 * 暂停和继续任务按钮事件
 * @author Saulxk
 * </br>EditDate: 2017-05-24
 */
public class StopDownloadingTaskActionListener implements ActionListener{
	
	Client client;
	DownloadingTaskPanel taskPanel;
	
	
	/**	
	 * @param client		对应Ttorrent框架中 BT任务客户对象
	 * @param taskPanel		对应正在下载任务对象
	 * </br>EditDate: 2017-06-24
	 */
	public StopDownloadingTaskActionListener(Client client,	DownloadingTaskPanel taskPanel) {
		this.client = client;
		this.taskPanel=taskPanel;
	}
	
	public Client getClient() {
		return client;
	}

	public DownloadingTaskPanel getTaskPanel() {
		return taskPanel;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		new Thread(new Runnable() {
			public void run() {
				JButton stop=((JButton)event.getSource());
				if(stop.getText().equals("暂停")){
					//切换到不响应事件状态
					stop.setText("正在暂停");
					client.stop();
					//暂停成功切换回响应状态
					stop.setText("继续");
				}else if(stop.getText().equals("继续")){
					stop.setText("暂停");
					Client client=null;
					try {
						//解析种子文件，创建下载种子
						SharedTorrent torrent=SharedTorrent.fromFile(taskPanel.getTorrent(),taskPanel.getSaveDirectory());
						//创建新的下载客户端
						client = new Client(InetAddress.getLocalHost(), torrent);
						taskPanel.setClient(client);
						//从上下文中取出下载服务
						IDownloadService downloadService=(IDownloadService) TurbineView.getContext().getBean("downloadService");
						//刷新自身的下载客户端
						StopDownloadingTaskActionListener.this.client=client;
						//把任务委派给下载服务中的下载线程
						downloadService.startdownload(client, taskPanel, taskPanel.getSaveDirectory());
					} catch (NoSuchAlgorithmException e) {
						e.printStackTrace();
					} catch (UnknownHostException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
}
