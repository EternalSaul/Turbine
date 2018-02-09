package nchu.turbine.view;

import java.awt.EventQueue;
import java.awt.event.MouseListener;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import nchu.turbine.interfaces.service.IMagnetService;
import nchu.turbine.interfaces.service.ITasksDisplayService;

@Component
@Qualifier("mainFrame")
public class MainFrame extends JFrame{
	
	private JPanel jpanel;
	private JPanel jPanel2;
	

	public MainFrame(@Qualifier("secondPanel") JPanel jpanel,@Qualifier("thirdPanel")JPanel jPanel2) {
		this.jpanel = jpanel;
		this.jPanel2=jPanel2;
		initialize();
	}
	

	public JPanel getJpanel() {// 获取当前 的Jpanel
		return jpanel;
	}

	public void setJpanel(JPanel jpanel) {// 重新设置Jpanel
		this.jpanel = jpanel;
	}

	private void initialize() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		this.setResizable(false);
		this.setBounds(100, 100, 426, 489);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Turbinev2.2(" + df.format(new Date()) + ")");
		this.getContentPane().setLayout(null);
		this.getContentPane().add(jpanel);
		this.getContentPane().add(jPanel2);
		jpanel.setVisible(true);
		jPanel2.setVisible(false);
	}
}
