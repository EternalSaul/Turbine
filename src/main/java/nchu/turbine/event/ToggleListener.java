package nchu.turbine.event;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import nchu.turbine.view.SecondPanel;
import nchu.turbine.view.ThirdPanel;
import nchu.turbine.view.TurbineView;

/**
 * 切换正在下载任务和已下载任务界面的事件
 * @author Saulxk
 * </br>EditDate: 2017-06-24
 */
@Component
@Qualifier("toggleListener")
public class ToggleListener implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent e) {
		
		ThirdPanel jPanel2=(ThirdPanel) TurbineView.getContext().getBean("thirdPanel");
		SecondPanel jPanel=(SecondPanel) TurbineView.getContext().getBean("secondPanel");
		
		if(jPanel.isVisible()){
			jPanel.setVisible(false);
			jPanel2.setVisible(true);
		}else {
			jPanel2.setVisible(false);
			jPanel.setVisible(true);
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
