package test;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.LayoutManager2;
import java.awt.ScrollPane;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;


public class ScrollTest extends JFrame{
public ScrollTest() {
//	this.setLayout(new FlowLayout());
	this.setVisible(true);
	this.setSize(400, 300);
	this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
	this.setResizable(false);
	JScrollPane pane=new JScrollPane();
	pane.setBounds(0, 0, 390, 300);
	JPanel jPanel=new JPanel();
	jPanel.setBounds(0, 0, 380, 300);
	jPanel.setLayout(null);
	jPanel.setBorder(BorderFactory.createLineBorder(Color.red));
	jPanel.setPreferredSize(new Dimension(390, 600));
	jPanel.add("123",new DownloadCard());
	DownloadCard card=new DownloadCard();
	jPanel.add("123",card);
	jPanel.add("123",new DownloadCard());
	pane.getViewport().add(jPanel);
	this.add(pane);
}
private class DownloadCard extends JPanel{
	JLabel name;
	JLabel nameLab;
	JLabel size;
	JLabel num;
	JLabel time;
	JLabel speed;
	JLabel stop;
	public DownloadCard() {
		this.setLayout(null);
		this.setBorder(BorderFactory.createLineBorder(Color.gray));
		this.setBounds(10, 21, 380, 100);
		
		JLabel name = new JLabel("\u540D\u5B57\uFF1A");
		name.setBounds(10, 10, 54, 15);
		this.add(name);
		
		JLabel nameLab = new JLabel("test01.exe");
		nameLab.setBounds(62, 10, 202, 15);
		this.add(nameLab);
		
		JLabel size = new JLabel("7.81G");
		size.setForeground(Color.GREEN);
		size.setBounds(10, 35, 54, 15);
		this.add(size);
		
		JLabel num = new JLabel("68\u4E2A\u6587\u4EF6");
		num.setForeground(Color.GREEN);
		num.setBounds(72, 35, 54, 15);
		this.add(num);
		
		JLabel time = new JLabel("1:24:01");
		time.setForeground(Color.GREEN);
		time.setBounds(171, 35, 73, 15);
		this.add(time);
		
		JLabel speed = new JLabel("1.62MB/S");
		speed.setForeground(Color.GREEN);
		speed.setBounds(274, 35, 54, 15);
		this.add(speed);
		
//		JLabel label_7 = new JLabel("\u8FDB\u5EA6\u6761--------------------------------------------------------\u4EE3\u6307");
//		label_7.setBounds(10, 56, 357, 15);
//		this.add(label_7);
		JProgressBar progressBar=new JProgressBar();
		progressBar.setBounds(10, 56, 357, 15);
		this.add(progressBar);
		progressBar.setValue((int)17.5);
		progressBar.setStringPainted(true);
		progressBar.setString(String.valueOf(17.5)+"%");
		
		JButton stop = new JButton("\u6682\u505C");
		stop.setForeground(Color.RED);
		stop.setBounds(274, 75, 93, 23);
		this.add(stop);
		
		JButton delete = new JButton("É¾³ý");
		delete.setForeground(Color.RED);
		delete.setBounds(170, 75, 93, 23);
		this.add(delete);
	}
}
public static void main(String[] args) {
	ScrollTest scrollTest=new ScrollTest();
}
}
