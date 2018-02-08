package nchu.turbine.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import org.aspectj.weaver.bcel.UnwovenClassFileWithThirdPartyManagedBytecode.IByteCodeProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import nchu.turbine.event.DownloadListener;
import nchu.turbine.interfaces.service.IDirectoryChooseService;
import nchu.turbine.interfaces.service.IMagnetService;

@Component
public class MyFrame implements IListBodyPanel{
	private JFrame frame;
	private JTextField savePath;
	private JTextField torrentPath;
	private DownloadListener downloadListener;
	@Autowired
	private IDirectoryChooseService chooseService;
	JPanel bodyPanel;
	@Override
	public JPanel getBodyPanel() {
		return bodyPanel;
	}

	/**
	 * Create the application.
	 */
	public MyFrame(@Autowired DownloadListener downloadListener) {
		this.downloadListener=downloadListener;
		initialize();
		downloadListener.setFrame(frame);
		downloadListener.setSavePath(savePath);
		downloadListener.setTorrentPath(torrentPath);
		downloadListener.setFilesDisplay(bodyPanel);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 310);
		frame.setResizable(false);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        点X只是隐藏窗体，而非退出程序
		frame.getContentPane().setLayout(null);
		frame.setTitle("Turbine 1.0");
		JPanel headPanel = new JPanel();
		headPanel.setBounds(10, 10, 414, 81);
		headPanel.setBorder(BorderFactory.createLineBorder(Color.gray));
		frame.getContentPane().add(headPanel);
		headPanel.setLayout(null);
		
		JLabel lab1 = new JLabel("\u79CD\u5B50\uFF1A");
		lab1.setBounds(38, 10, 54, 15);
		headPanel.add(lab1);
		
		JLabel lab2 = new JLabel("\u4FDD\u5B58\u8DEF\u5F84\uFF1A");
		lab2.setBounds(10, 56, 82, 15);
		headPanel.add(lab2);
		
		savePath = new JTextField();
		savePath.setBounds(98, 53, 221, 21);
		headPanel.add(savePath);
		savePath.setColumns(10);
		
		JButton viewButton = new JButton("\u6D4F\u89C8");
		viewButton.addActionListener(new ActionListener() {//浏览按钮
			public void actionPerformed(ActionEvent arg0) {
				savePath.setText(chooseService.chooseSaveDirectory());
			}
		});
		viewButton.setBounds(329, 54, 75, 20);
		headPanel.add(viewButton);
		
		torrentPath = new JTextField();
		torrentPath.setBounds(98, 7, 221, 21);
		headPanel.add(torrentPath);
		torrentPath.setColumns(10);
		
		JButton viewButton1 = new JButton("\u6D4F\u89C8");
		viewButton1.setBounds(329, 8, 75, 20);
		headPanel.add(viewButton1);
		viewButton1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				torrentPath.setText(chooseService.chooseTorrentFile());
			}
		});
		
		JScrollPane jScrollPane=new JScrollPane();
		jScrollPane.setBounds(10,101, 414, 121);
		bodyPanel = new JPanel();
		bodyPanel.setBorder(BorderFactory.createLineBorder(Color.gray));
		jScrollPane.getViewport().add(bodyPanel);
		frame.getContentPane().add(jScrollPane);
		
		JPanel footPanel = new JPanel();
		footPanel.setBounds(10, 232, 414, 29);
		frame.getContentPane().add(footPanel);
		footPanel.setLayout(null);
		
		JButton downloadButton = new JButton("\u4E0B\u8F7D");
		downloadButton.setBounds(153, 0, 96, 27);
		footPanel.add(downloadButton);
		downloadButton.addMouseListener(downloadListener);
	}
	
	public void setVisible(){
		frame.setVisible(true);
	}
}
