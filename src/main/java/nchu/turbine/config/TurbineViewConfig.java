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
 * 视图配置类，配置界面的类
 * @author Saulxk
 * </br>EditDate: 2017-05-24
 */
@Configuration
@Import(value={TurbineContextConfig.class})
@ComponentScan(basePackageClasses={ViewScanTarget.class,EventScanTarget.class})
public class TurbineViewConfig {
	
	/**
	 * 已完成任务数据存取对象
	 */
	@Autowired
	ICompletedTaskDao ctd;
	
	/**
	 * 正在下载任务数据存取对象
	 */
	@Autowired
	IDownloadingTaskDao dtd;
	

	/**
	 * 正在下载任务的任务表 Bean
	 * @return  保存downloadingTask的Vector
	 * </br>EditDate: 2017-05-24
	 */
	@Bean
	@Qualifier("downloadingTaskPanels")
	Vector<DownloadingTaskPanel> downloadingTaskPanels(){
		return dtd.find();
	}
	

	/**
	 * 已完成的任务表 Bean
	 * @return  保存completedTask的Vector
	 * </br>EditDate: 2017-05-24
	 */
	@Bean
	@Qualifier("completedTaskPanels")
	Vector<CompletedTaskPanel> completedTaskPanels(){
		return ctd.find();
	}
}
