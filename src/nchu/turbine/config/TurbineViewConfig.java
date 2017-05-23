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

@Configuration
@Import(value={TurbineContextConfig.class})
@ComponentScan(basePackageClasses={ViewScanTarget.class,EventScanTarget.class})
public class TurbineViewConfig {
	/**
	 * @return	保存downloadingTask的Vector
	 * </br>EditDate: 2017-05-21
	 */
	
	@Autowired
	ICompletedTaskDao ctd;
	
	@Autowired
	IDownloadingTaskDao dtd;
	
	@Bean
	@Qualifier("downloadingTaskPanels")
	Vector<DownloadingTaskPanel> downloadingTaskPanels(){
//		return new Vector<DownloadingTaskPanel>();
		return dtd.find();
	}
	
	/**
	 * @return	保存completedTask的Vector
	 * </br>EditDate: 2017-05-21
	 */
	@Bean
	@Qualifier("completedTaskPanels")
	Vector<CompletedTaskPanel> completedTaskPanels(){
//		return new Vector<CompletedTaskPanel>();
		return ctd.find();
	}
}
