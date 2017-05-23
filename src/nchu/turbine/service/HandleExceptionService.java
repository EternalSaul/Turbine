package nchu.turbine.service;


import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import nchu.turbine.exception.TurbineException;
import nchu.turbine.interfaces.service.IHandleExceptionService;
import nchu.turbine.view.TurbineView;

@Component
public class HandleExceptionService extends BaseService implements IHandleExceptionService{
	
	JFrame main;
	
	@Value("${mainView}")
	String mainView;
	

	@Override
	public void handleException(TurbineException exception,String title,int messageType) {
		if(main==null) main=(JFrame) TurbineView.getContext().getBean(mainView);
		JOptionPane.showMessageDialog(main, exception.getMessage(),title, messageType);
	}
  
}
