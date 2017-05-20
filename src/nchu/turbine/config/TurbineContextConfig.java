package nchu.turbine.config;

import javax.swing.filechooser.FileSystemView;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import nchu.turbine.interfaces.service.IHandleExceptionService;
import nchu.turbine.interfaces.service.IMagnetService;
import nchu.turbine.service.HandleExceptionService;
import nchu.turbine.service.ServiceScanTarget;
import nchu.turbine.view.MyFrame;
import nchu.turbine.view.ViewScanTarget;

/**
 * 此类是Spring配置类
 * @author Saulxk
 * </br>EditDate: 2017-05-17
 */
@ComponentScan(basePackageClasses={ServiceScanTarget.class})
@Configuration
@PropertySource(value="classpath:TorrentService.properties")
public class TurbineContextConfig { 
	
	@Bean
	@Autowired
	public FileDialog torrentChooser(Shell shell){
		FileDialog dialog=new FileDialog(shell,SWT.OPEN);
		dialog.setFilterExtensions(new String[]{"*.torrent" });
		return dialog;
	}
	
	@Bean
	@Autowired
	public DirectoryDialog fileSaver(Shell shell){
		DirectoryDialog dialog=new DirectoryDialog(shell, SWT.OPEN);
		return dialog;
	}
	
	/**
	 * @param display		用于包容Shell
	 * @return   Shell		用于包含Dialog
	 * </br>EditDate: 2017-05-18
	 */
	@Bean
	@Autowired
	public Shell shell(Display display){
		return new Shell(display);
	}
	
	/**
	 * @return  Display     用于包含Shell
	 * </br>EditDate: 2017-05-18
	 */
	@Bean
	public Display display(){
		return new Display();
	}
	
	/**
	 * 因为磁力链接转化为种子的转化方式可能经常变换，所以通过相对类名反射一个服务类实现来作为注入类
	 * @param magnetservice			实现类的位置
	 * @return  IMagnetService		返回一个磁力链接服务类
	 * </br>EditDate: 2017-05-19
	 */
	@Bean
	public IMagnetService magnetService(@Value("${magnetservice}") String magnetservice){
		IMagnetService service=null;
		try {
			Class clz=getClass().forName(magnetservice);
			service=(IMagnetService) clz.newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return service;
	}
	
	/**
	 * 返回一个文件系统视图，用于获取Document路径(windows)或者Home路径(Linux)
	 * @return		swing的文件系统视图
	 * </br>EditDate: 2017-05-19
	 */
	@Bean
	public FileSystemView fileSystemView(){
		return FileSystemView.getFileSystemView();
	}
	
}
