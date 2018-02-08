package nchu.turbine.config;

import java.util.HashMap;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

import nchu.turbine.event.EventScanTarget;
import nchu.turbine.interfaces.dao.ICompletedTaskDao;
import nchu.turbine.interfaces.dao.IDownloadingTaskDao;
import nchu.turbine.service.ServiceScanTarget;
import nchu.turbine.view.CompletedTaskPanel;
import nchu.turbine.view.DownloadingTaskPanel;
import nchu.turbine.view.MyFrame;
import nchu.turbine.view.ViewScanTarget;

/**
 * ��ͼ�����࣬���ý������
 * @author Saulxk
 * </br>EditDate: 2017-05-24
 */
@Configuration
@Import(value={TurbineContextConfig.class})
@ComponentScan(basePackageClasses={ViewScanTarget.class,EventScanTarget.class})
public class TurbineViewConfig {
	
	/**
	 * ������������ݴ�ȡ����
	 */
	@Autowired
	ICompletedTaskDao ctd;
	
	/**
	 * ���������������ݴ�ȡ����
	 */
	@Autowired
	IDownloadingTaskDao dtd;
	

	/**
	 * ������������������ Bean
	 * @return  ����downloadingTask��Vector
	 * </br>EditDate: 2017-05-24
	 */
	@Bean
	@Qualifier("downloadingTaskPanels")
	Vector<DownloadingTaskPanel> downloadingTaskPanels(){
		return dtd.find();
	}
	

	/**
	 * ����ɵ������ Bean
	 * @return  ����completedTask��Vector
	 * </br>EditDate: 2017-05-24
	 */
	@Bean
	@Qualifier("completedTaskPanels")
	Vector<CompletedTaskPanel> completedTaskPanels(){
		return ctd.find();
	}
}
