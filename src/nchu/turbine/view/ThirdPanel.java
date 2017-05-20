package nchu.turbine.view;

import java.awt.Color;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import nchu.turbine.event.ToggleListener;

@Component
@Qualifier("thirdPanel")
public class ThirdPanel extends JPanel{
	MouseListener toggleListener;
	MouseListener newTaskListener;
	public ThirdPanel(@Qualifier("toggleListener") MouseListener toggleListener,
			@Qualifier("newDownloadTaskListener") MouseListener newTaskListener){
		this.toggleListener=toggleListener;
		this.newTaskListener=newTaskListener;
		initialize();
	}
		private void initialize() {	
		this.setBounds(0, 0, 420, 460);
		//frame.getContentPane().add(thirdPanel);
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
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(10, 34, 400, 25);
		this.add(panel);
		
		JLabel label = new JLabel("\u5DF2\u5B8C\u6210(10)");
		label.setBounds(0, 10, 400, 15);
		panel.add(label);
		
		JPanel bodyPanel = new JPanel();
		bodyPanel.setLayout(null);
		bodyPanel.setBorder(BorderFactory.createLineBorder(Color.gray));
		bodyPanel.setBounds(10, 60, 400, 347);
		this.add(bodyPanel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(BorderFactory.createLineBorder(Color.gray));
		panel_2.setBounds(10, 23, 380, 81);
		bodyPanel.add(panel_2);
		
		JLabel label_1 = new JLabel("\u540D\u5B57\uFF1A");
		label_1.setBounds(10, 10, 54, 15);
		panel_2.add(label_1);
		
		JLabel label_2 = new JLabel("test01.exe");
		label_2.setBounds(62, 10, 202, 15);
		panel_2.add(label_2);
		
		JLabel label_3 = new JLabel("7.81G");
		label_3.setForeground(Color.BLACK);
		label_3.setBounds(10, 35, 54, 15);
		panel_2.add(label_3);
		
		JButton openFile = new JButton("\u6253\u5F00\u6587\u4EF6");
		openFile.setForeground(Color.BLACK);
		openFile.setBounds(274, 6, 93, 23);
		panel_2.add(openFile);
		
		JLabel label_4 = new JLabel("2017-06-17 8:29:20");
		label_4.setBounds(62, 35, 202, 15);
		panel_2.add(label_4);
		
		JButton reDownload = new JButton("\u91CD\u65B0\u4E0B\u8F7D");
		reDownload.setBounds(274, 31, 93, 23);
		panel_2.add(reDownload);
		
		JPanel footPanel = new JPanel();
		footPanel.setLayout(null);
		footPanel.setBounds(10, 417, 400, 33);
		this.add(footPanel);
		
		JButton button_3 = new JButton("\u6B63\u5728\u4E0B\u8F7D(4)");
		button_3.setBounds(0, 0, 400, 33);
		footPanel.add(button_3);
		button_3.addMouseListener(toggleListener);
		}
	}
