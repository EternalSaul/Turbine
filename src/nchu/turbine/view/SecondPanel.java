package nchu.turbine.view;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import nchu.turbine.event.ToggleListener;

@Component
@Primary
@Qualifier("secondPanel")
public class SecondPanel extends JPanel{

	MouseListener toggleListener;
	MouseListener newTaskListener;
	
	public SecondPanel(@Qualifier("toggleListener") MouseListener toggleListener,
			@Qualifier("newDownloadTaskListener") MouseListener newTaskListener){
		this.toggleListener=toggleListener;
		this.newTaskListener=newTaskListener;
		initialize();
	}
	private void initialize() {
		this.setBounds(0, 0, 420, 460);
		//frame.getContentPane().add(secondPanel);
		this.setLayout(null);
		
		JPanel headPanel = new JPanel();
		headPanel.setLayout(null);
		headPanel.setBorder(BorderFactory.createLineBorder(Color.gray));
		headPanel.setBounds(10, 10, 400, 25);
		this.add(headPanel);
		
		JButton newTorrentBtn = new JButton("\u65B0\u5EFA");
		newTorrentBtn.setBounds(0, 0, 63, 27);
		headPanel.add(newTorrentBtn);
		newTorrentBtn.addMouseListener(newTaskListener);
		
		JPanel bodyPanel = new JPanel();
		bodyPanel.setLayout(null);
		bodyPanel.setBorder(BorderFactory.createLineBorder(Color.gray));
		bodyPanel.setBounds(10, 60, 400, 347);
		this.add(bodyPanel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(BorderFactory.createLineBorder(Color.gray));
		panel_1.setBounds(10, 21, 380, 81);
		bodyPanel.add(panel_1);
		
		JLabel name = new JLabel("\u540D\u5B57\uFF1A");
		name.setBounds(10, 10, 54, 15);
		panel_1.add(name);
		
		JLabel nameLab = new JLabel("test01.exe");
		nameLab.setBounds(62, 10, 202, 15);
		panel_1.add(nameLab);
		
		JLabel size = new JLabel("7.81G");
		size.setForeground(Color.GREEN);
		size.setBounds(10, 35, 54, 15);
		panel_1.add(size);
		
		JLabel num = new JLabel("68\u4E2A\u6587\u4EF6");
		num.setForeground(Color.GREEN);
		num.setBounds(72, 35, 54, 15);
		panel_1.add(num);
		
		JLabel time = new JLabel("1:24:01");
		time.setForeground(Color.GREEN);
		time.setBounds(171, 35, 73, 15);
		panel_1.add(time);
		
		JLabel speed = new JLabel("1.62MB/S");
		speed.setForeground(Color.GREEN);
		speed.setBounds(274, 35, 54, 15);
		panel_1.add(speed);
		
		JLabel label_7 = new JLabel("\u8FDB\u5EA6\u6761--------------------------------------------------------\u4EE3\u6307");
		label_7.setBounds(10, 56, 357, 15);
		panel_1.add(label_7);
		
		JButton stop = new JButton("\u6682\u505C");
		stop.setForeground(Color.RED);
		stop.setBounds(274, 6, 93, 23);
		panel_1.add(stop);
		
		JPanel footPanel = new JPanel();
		footPanel.setLayout(null);
		footPanel.setBounds(10, 417, 400, 33);
		this.add(footPanel);
		
		JButton button_2 = new JButton("\u5DF2\u5B8C\u6210(10)");
		button_2.setBounds(0, 0, 400, 33);
		footPanel.add(button_2);
		button_2.addMouseListener(toggleListener);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(10, 40, 400, 25);
		this.add(panel);
		
		JLabel label = new JLabel("\u6B63\u5728\u4E0B\u8F7D(4)");
		label.setBounds(0, 5, 390, 15);
		panel.add(label);
	}
}
