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

public class StopDownloadingTaskActionListener implements ActionListener{
	Client client;
	DownloadingTaskPanel taskPanel;
	
	
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
				if(stop.getText().equals("ÔÝÍ£")){
					stop.setText("¼ÌÐø");
//					client.deleteObservers();
					client.stop();
				}else{
					stop.setText("ÔÝÍ£");
					Client client=null;
					try {
						SharedTorrent torrent=SharedTorrent.fromFile(taskPanel.getTorrent(),taskPanel.getSaveDirectory());
						client = new Client(InetAddress.getLocalHost(), torrent);
						taskPanel.setClient(client);
						IDownloadService downloadService=(IDownloadService) TurbineView.getContext().getBean("downloadService");
						StopDownloadingTaskActionListener.this.client=client;
						downloadService.startdownload(client, taskPanel, taskPanel.getSaveDirectory());
//						client.download();
//						client.addObserver(new DownloadingObserver(taskPanel));
//						client.waitForCompletion();
//						if(torrent.isComplete()){
//							
//						}else{
//							
//						}
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
