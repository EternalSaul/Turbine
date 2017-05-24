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
import nchu.turbine.event.OpenCompletedDownloadTaskActionListener;
import nchu.turbine.event.StopDownloadingTaskActionListener;
import nchu.turbine.interfaces.service.ITasksDisplayService;

public class DownloadingTaskPanel extends JPanel{
	long createTime;
	Client client;			//下载端
	JLabel name;
	JLabel nameLab;
	JLabel size;
	JLabel num;
	JLabel time;
	JLabel speed;
	File torrent;
	File saveDirectory;//文件保存目录
	JProgressBar progress;//下载进度
	JButton delete;
	JButton stop;
	JButton openFile;
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
	
	public JButton getOpenFile() {
		return openFile;
	}
	/**
	 * @param nameLab				任务名
	 * @param size					任务大小
	 * @param num					文件数目
	 * @param client				下载的线程端
	 * @param createTime			创建时间
	 * @param saveDirectory			保存路径
	 * </br>EditDate: 2017-05-21
	 */
	public DownloadingTaskPanel(String nameLab,String size,int num,Client client,long createTime,File saveDirectory,File torrent) {
		this.client=client;
		this.createTime=createTime;
		this.saveDirectory=saveDirectory;
		this.torrent=torrent;
		init(nameLab,size,num);
	}
	
	/**
	 * @param cnameLab		任务名
	 * @param csize			任务大小
	 * @param cnum			文件数目
	 * </br>EditDate: 2017-05-21
	 */
	public void init(String cnameLab,String csize,int cnum){
		this.setLayout(null);
		this.setBorder(BorderFactory.createLineBorder(Color.gray));
		this.setBounds(10, 21, 380, 100);
		
		this.name = new JLabel("任务名:");
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
		
		delete = new JButton("删除");
		delete.setForeground(Color.RED);
		delete.setBounds(170, 75, 93, 23);
		this.add(delete);
		
		delete.addActionListener(new DeleteDownloadingTaskActionListener(client, this));
		
		openFile = new JButton("\u6253\u5F00\u6587\u4EF6");//打开文件按钮
		openFile.setForeground(Color.BLACK);
		openFile.setForeground(Color.RED);
		openFile.setBounds(65, 75, 93, 23);
		this.add(openFile);
		openFile.addActionListener(new OpenCompletedDownloadTaskActionListener(saveDirectory));
	}
	
	/**
	 * 让DownloadingTaskPanel可串行化
	 * 实际上是把Client设置为null
	 * @return		原对象的可串行化对象
	 * </br>EditDate: 2017-05-23
	 */
	public DownloadingTaskPanel serializableClone(){
		return new DownloadingTaskPanel(nameLab.getText(), size.getText(), 
				Integer.parseInt(num.getText()),null,createTime, saveDirectory,torrent);
	}
	
}
