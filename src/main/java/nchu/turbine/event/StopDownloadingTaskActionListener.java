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
 * ��ͣ�ͼ�������ť�¼�
 * @author Saulxk
 * </br>EditDate: 2017-05-24
 */
public class StopDownloadingTaskActionListener implements ActionListener{
	
	Client client;
	DownloadingTaskPanel taskPanel;
	
	
	/**	
	 * @param client		��ӦTtorrent����� BT����ͻ�����
	 * @param taskPanel		��Ӧ���������������
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
				if(stop.getText().equals("��ͣ")){
					//�л�������Ӧ�¼�״̬
					stop.setText("������ͣ");
					client.stop();
					//��ͣ�ɹ��л�����Ӧ״̬
					stop.setText("����");
				}else if(stop.getText().equals("����")){
					stop.setText("��ͣ");
					Client client=null;
					try {
						//���������ļ���������������
						SharedTorrent torrent=SharedTorrent.fromFile(taskPanel.getTorrent(),taskPanel.getSaveDirectory());
						//�����µ����ؿͻ���
						client = new Client(InetAddress.getLocalHost(), torrent);
						taskPanel.setClient(client);
						//����������ȡ�����ط���
						IDownloadService downloadService=(IDownloadService) TurbineView.getContext().getBean("downloadService");
						//ˢ����������ؿͻ���
						StopDownloadingTaskActionListener.this.client=client;
						//������ί�ɸ����ط����е������߳�
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
