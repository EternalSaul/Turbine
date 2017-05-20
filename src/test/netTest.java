package test;

import java.io.File;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import nchu.turbine.interfaces.service.IMagnetService;
import nchu.turbine.service.MagnetService;

public class netTest {
	
	@Test
	public void magenet_load(){
		ClassPathXmlApplicationContext applicationContext=new ClassPathXmlApplicationContext("Bean.xml");
		IMagnetService service=(IMagnetService) applicationContext.getBean("magnetService");
		File file=service.Magnet2Torrent("magnet:?xt=urn:btih:11aabbec897260de25a71f149712114bf9e38ddf");
		System.out.println(file.getPath());
	}
	
}
