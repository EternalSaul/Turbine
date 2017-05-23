package nchu.turbine.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import org.springframework.beans.factory.annotation.Autowired;

import com.turn.ttorrent.client.Client;

import nchu.turbine.event.DeleteDownloadingTaskActionListener;
import nchu.turbine.event.StopDownloadingTaskActionListener;
import nchu.turbine.interfaces.service.ITasksDisplayService;

public class DownloadingTaskPanel extends JPanel{
	long createTime;
	Client client;			//���ض�
	JLabel name;
	JLabel nameLab;
	JLabel size;
	JLabel num;
	JLabel time;
	JLabel speed;
	File torrent;
	File saveDirectory;//�ļ�����Ŀ¼
	JProgressBar progress;//���ؽ���
	JButton delete;
	JButton stop;
	public long getCreateTime() {
		return createTime;
	}
	public Client getClient() {
		return client;
	}
	public void setTime(String time) {
		this.time.setText(time);
	}
	
	public void setSpeed(String speed) {
		this.speed.setText(speed);
	}
	
	public void setProgress(double progress) {
		this.progress.setValue((int)progress);
		this.progress.setString(String.valueOf(String.format("%.2f", progress))+"%");
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public File getSaveDirectory() {
		return saveDirectory;
	}
	
	public File getTorrent() {
		return torrent;
	}
	
	public JButton getStop() {
		return stop;
	}
	
	public JButton getDelete() {
		return delete;
	}
	/**
	 * @param nameLab				������
	 * @param size					�����С
	 * @param num					�ļ���Ŀ
	 * @param client				���ص��̶߳�
	 * @param createTime			����ʱ��
	 * @param saveDirectory			����·��
	 * </br>EditDate: 2017-05-21
	 */
	public DownloadingTaskPanel(String nameLab,String size,int num,Client client,long createTime,File saveDirectory,File torrent) {
		this.client=client;
		this.createTime=createTime;
		this.saveDirectory=saveDirectory;
		this.torrent=torrent;
		init(nameLab,size,num);
	}
	
	
//	public DownloadingTaskPanel(long createTime, Client client, JLabel name, JLabel nameLab, JLabel size, JLabel num,
//			JLabel time, JLabel speed, JLabel stop, File torrent, File saveDirectory, JProgressBar progress,JButton delete) {
//		this.createTime = createTime;
//		this.client = client;
//		this.name = name;
//		this.nameLab = nameLab;
//		this.size = size;
//		this.num = num;
//		this.time = time;
//		this.speed = speed;
//		this.stop = stop;
//		this.torrent = torrent;
//		this.saveDirectory = saveDirectory;
//		this.progress = progress;
//		this.delete=delete;
//		init(name, csize, cnum);
//	}
	
	/**
	 * @param cnameLab		������
	 * @param csize			�����С
	 * @param cnum			�ļ���Ŀ
	 * </br>EditDate: 2017-05-21
	 */
	public void init(String cnameLab,String csize,int cnum){
		this.setLayout(null);
		this.setBorder(BorderFactory.createLineBorder(Color.gray));
		this.setBounds(10, 21, 380, 100);
		
		this.name = new JLabel("������:");
		this.name.setBounds(10, 10, 54, 15);
		this.add(name);
		
		nameLab = new JLabel(cnameLab);
		nameLab.setBounds(62, 10, 202, 15);
		this.add(nameLab);
		
		size = new JLabel(csize);
		size.setForeground(Color.BLUE);
		size.setBounds(10, 35, 54, 15);
		this.add(size);
		
		num = new JLabel(String.valueOf(cnum));
		num.setForeground(Color.BLUE);
		num.setBounds(72, 35, 54, 15);
		this.add(num);
		
		time = new JLabel("--:--:--");
		time.setForeground(Color.BLUE);
		time.setBounds(171, 35, 73, 15);
		this.add(time);
		
		speed = new JLabel("--B/S");
		speed.setForeground(Color.BLUE);
		speed.setBounds(274, 35, 54, 15);
		this.add(speed);
		
		progress=new JProgressBar();
		progress.setBounds(10, 56, 357, 15);
		progress.setValue(0);
		progress.setStringPainted(true);
		this.add(progress);
		
		stop = new JButton("\u6682\u505C");
		stop.setForeground(Color.RED);
		stop.setBounds(274, 75, 93, 23);
		this.add(stop);
		
		stop.addActionListener(new StopDownloadingTaskActionListener(client,this));
		
		delete = new JButton("ɾ��");
		delete.setForeground(Color.RED);
		delete.setBounds(170, 75, 93, 23);
		this.add(delete);
		
		delete.addActionListener(new DeleteDownloadingTaskActionListener(client, this));
	}
	
	public DownloadingTaskPanel serializableClone(){
		System.out.println("sdsds"+saveDirectory);
		System.out.println("sss"+torrent);
		return new DownloadingTaskPanel(nameLab.getText(), size.getText(), 
				Integer.parseInt(num.getText()),null,createTime, saveDirectory,torrent);
	}
	
}
