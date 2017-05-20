package nchu.turbine.thread;

import java.lang.reflect.Array;
import java.util.Hashtable;
import java.util.Set;
import java.util.Vector;

import com.turn.ttorrent.client.Client;

/**
 * ����ֻ��Saul�������������õ�
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void printInfo(){
		System.out.println("�ļ���"+client.getTorrent().getFilenames().get(0));
		System.out.println("�ļ��ܴ�С"+client.getTorrent().getSize());
		System.out.println("��������"+client.getTorrent().getTrackerCount());
		System.out.println("�ϴ���"+client.getTorrent().getUploaded());
		System.out.println("������"+client.getTorrent().getLeft()+"\n\n");
	}
	

}
