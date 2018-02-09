package nchu.turbine.event;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import nchu.turbine.exception.TurbineException;

@Primary
@Component
public class DefaultMouseListener implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent e) {
		throw new TurbineException("未定义任何事件");
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
