package test;

import static org.junit.Assert.*;

import java.io.ObjectOutputStream;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import nchu.turbine.interfaces.service.IDirectoryChooseService;

public class fileloadtest {
	
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
	public void directory() {
		System.out.println(getClass().getResource("/"));
	}

}
