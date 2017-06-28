package nchu.turbine.event;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import nchu.turbine.view.MyFrame;

/**
 * 下载窗体显示事件
 * @author Saulxk
 * </br>EditDate: 2017-06-24
 */
@Component
@Qualifier("newDownloadTaskListener")
public class NewDownloadTaskListener implements MouseListener{
	/**
	 * 对应下载窗体
	 */
	@Autowired
	MyFrame myFrame;
	@Override
	public void mouseClicked(MouseEvent e) {
		myFrame.setVisible();		//显示下载窗体
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
