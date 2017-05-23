package nchu.turbine.view;

import java.awt.Color;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import nchu.turbine.event.ToggleListener;
import nchu.turbine.interfaces.dao.ICompletedTaskDao;

@Component
@Qualifier("thirdPanel")
public class ThirdPanel extends JPanel implements IListBodyPanel{
	MouseListener toggleListener;
	MouseListener newTaskListener;
	JPanel bodyPanel;
	public JPanel getBodyPanel() {
		return bodyPanel;
	}
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
		
		JLabel label = new JLabel("\u5DF2\u5B8C\u6210");
		label.setBounds(0, 10, 400, 15);
		panel.add(label);
		
		JScrollPane jScrollPane=new JScrollPane();
		jScrollPane.setBounds(10, 60, 400, 347);
		bodyPanel = new JPanel();
		bodyPanel.setBorder(BorderFactory.createLineBorder(Color.gray));
		jScrollPane.getViewport().add(bodyPanel);
		this.add(jScrollPane);
		
		JPanel footPanel = new JPanel();
		footPanel.setLayout(null);
		footPanel.setBounds(10, 417, 400, 33);
		this.add(footPanel);
		
		JButton button_3 = new JButton("\u6B63\u5728\u4E0B\u8F7D");
		button_3.setBounds(0, 0, 400, 33);
		footPanel.add(button_3);
		button_3.addMouseListener(toggleListener);
		}
	}
