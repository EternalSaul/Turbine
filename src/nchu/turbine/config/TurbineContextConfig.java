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
 * ������Spring������
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
	 * @param display		���ڰ���Shell
	 * @return   Shell		���ڰ���Dialog
	 * </br>EditDate: 2017-05-18
	 */
	@Bean
	@Autowired
	public Shell shell(Display display){
		return new Shell(display);
	}
	
	/**
	 * @return  Display     ���ڰ���Shell
	 * </br>EditDate: 2017-05-18
	 */
	@Bean
	public Display display(){
		return new Display();
	}
	
	/**
	 * ��Ϊ��������ת��Ϊ���ӵ�ת����ʽ���ܾ����任������ͨ�������������һ��������ʵ������Ϊע����
	 * @param magnetservice			ʵ�����λ��
	 * @return  IMagnetService		����һ���������ӷ�����
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
	 * ����һ���ļ�ϵͳ��ͼ�����ڻ�ȡDocument·��(windows)����Home·��(Linux)
	 * @return		swing���ļ�ϵͳ��ͼ
	 * </br>EditDate: 2017-05-19
	 */
	@Bean
	public FileSystemView fileSystemView(){
		return FileSystemView.getFileSystemView();
	}
	
}
