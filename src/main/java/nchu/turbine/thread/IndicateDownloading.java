package nchu.turbine.thread;

import java.lang.reflect.Array;
import java.util.Hashtable;
import java.util.Set;
import java.util.Vector;

import com.turn.ttorrent.client.Client;

/**
 * 此类只是Saul用来做测试类用的
 * @author Saulxk
 * EditDate: 2017-05-17
 */
public class IndicateDownloading implements Runnable {
	Client client;
	
	public IndicateDownloading() {
		
	}
	
	public IndicateDownloading(Client client) {
		this.client=client;
	}

	@Override
	public void run() {
		try {
			while(true){
				printInfo();
				Thread.sleep(2000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void printInfo(){
		System.out.println("123");
		System.out.println("文件名"+client.getTorrent().getFilenames().get(0));
		System.out.println("文件总大小"+client.getTorrent().getSize());
		System.out.println("服务器数"+client.getTorrent().getTrackerCount());
		System.out.println("上传数"+client.getTorrent().getUploaded());
		System.out.println("剩余数"+client.getTorrent().getLeft());
		System.out.println("下载数"+client.getTorrent().getDownloaded()+"\n\n");
	}
	

}
