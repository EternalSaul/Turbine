package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Vector;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.turn.ttorrent.client.Client;
import com.turn.ttorrent.client.SharedTorrent;

import nchu.turbine.dao.DownLoadingTaskDao;
import nchu.turbine.interfaces.service.IMagnetService;
import nchu.turbine.service.MagnetService;
import nchu.turbine.view.CompletedTaskPanel;
import nchu.turbine.view.DownloadingTaskPanel;

public class netTest {
	
	@Test
	public void magenet_load(){
		ClassPathXmlApplicationContext applicationContext=new ClassPathXmlApplicationContext("Bean.xml");
		IMagnetService service=(IMagnetService) applicationContext.getBean("magnetService");
		File file=service.Magnet2Torrent("magnet:?xt=urn:btih:11aabbec897260de25a71f149712114bf9e38ddf");
		System.out.println(file.getPath());
	}
	
	@Test
	public void ft(){
//			File file=new File("D:\\hello.tur");
			try {
				FileOutputStream stream=new FileOutputStream("D:\\hello.turk");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	@Test
	public void ftt() throws NoSuchAlgorithmException, IOException{
		SharedTorrent torrent=SharedTorrent.fromFile(new File("ubuntu-12.04.5-desktop-i386.iso.torrent"),new File("C:\\Users\\Saulxk\\Desktop\\ÍøÂç±à³Ì"));
		List<String> s=torrent.getFilenames();
		Client client=new Client(InetAddress.getLocalHost(), torrent);
		Vector<DownloadingTaskPanel> taskPanels=new Vector<DownloadingTaskPanel>();
		taskPanels.add(new DownloadingTaskPanel("dsds", "sdss", 15, null,13456, null,null));
		DownLoadingTaskDao dao=new DownLoadingTaskDao();
		dao.save(taskPanels);
	}
	
}
