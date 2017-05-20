package test;

import static org.junit.Assert.*;

import javax.swing.filechooser.FileSystemView;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import nchu.turbine.interfaces.service.IDirectoryChooseService;
import nchu.turbine.interfaces.service.IMagnetService;

public class BeanTest {

	@Test
	public void magnetServiceTest() {
		ClassPathXmlApplicationContext applicationContext=new ClassPathXmlApplicationContext("Bean.xml");
		IMagnetService string=(IMagnetService) applicationContext.getBean("magnetService");
		System.out.println(string.getClass().getName());
	}
	
	@Test
	public void fileSystemView() {
		ClassPathXmlApplicationContext applicationContext=new ClassPathXmlApplicationContext("Bean.xml");
		FileSystemView fileSystemView= (FileSystemView) applicationContext.getBean("fileSystemView");
		System.out.println(fileSystemView);
	}

}
