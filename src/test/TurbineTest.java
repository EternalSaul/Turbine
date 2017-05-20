package test;

import static org.junit.Assert.*;

import java.awt.FileDialog;
import java.awt.Toolkit;
import java.io.File;
import java.io.FilenameFilter;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import nchu.turbine.interfaces.service.IDirectoryChooseService;
import nchu.turbine.service.BaseService;

/**
 * Turbine工程测试类
 *
 * @author Saulxk
 * EditDate: 2017-05-17
 */
public class TurbineTest {

	@Test
	public void spring_useful_test() {
		ClassPathXmlApplicationContext applicationContext=new ClassPathXmlApplicationContext("Bean.xml");
		IDirectoryChooseService chooseService=(IDirectoryChooseService) applicationContext.getBean("directoryService");
		System.out.println(chooseService.chooseTorrentFile());
		System.out.println(chooseService.chooseSaveDirectory());
		System.err.println(chooseService.chooseTorrentFile());
		System.out.println(chooseService.chooseSaveDirectory());
	}
	
	
	@Test
	public void flie_observe() {
		JFrame frame=new JFrame("123");
//		JFileChooser chooser=new JFileChooser();
		FileDialog dialog=new FileDialog(frame, "选择种子", FileDialog.LOAD);
		dialog.setFilenameFilter(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".torrent");
			}
		});
		dialog.setMultipleMode(false);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		dialog.setVisible(true);
		String directory=dialog.getDirectory();
		String name=dialog.getFile();
		System.out.println(directory+name);
		while(true);
	}
	
	@Test
	public void flie_save() {
		JFrame frame=new JFrame("123");
		FileDialog dialog=new FileDialog(frame, "选择种子", FileDialog.SAVE);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		dialog.setVisible(true);
		String directory=dialog.getDirectory();
		String name=dialog.getFile();
		System.out.println(directory+name);
		while(true);
	}

	
	@Test
	public void flie_choose() {
		JFrame frame=new JFrame("123");
		frame.setBounds(0,0,400,600);
		JFileChooser chooser=new JFileChooser();
//		chooser.setBounds(0,0,400, 600);
		frame.add(chooser);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setVisible(true);
//		System.out.println(directory+name);
		while(true);
	}
	
	@Test
	public void flie_eclipase_load() {
		Display display = new Display();
		Shell shell = new Shell(display);
		org.eclipse.swt.widgets.FileDialog dialog=new org.eclipse.swt.widgets.FileDialog(shell,SWT.OPEN);
		dialog.setFilterExtensions(new String[]{"*.torrent" });
		String filename=dialog.open();
		System.out.println(filename);
	}
	
	@Test
	public void flie_eclipase_save() {
		Display display = new Display();
		Shell shell = new Shell(display);
		DirectoryDialog dialog=new DirectoryDialog(shell, SWT.OPEN);
		String directory=dialog.open();
		System.out.println(directory);
	}

}
