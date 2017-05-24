package nchu.turbine.service;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.turn.ttorrent.client.Client;
import com.turn.ttorrent.client.SharedTorrent;

import nchu.turbine.exception.TurbineException;
import nchu.turbine.interfaces.service.IDownloadService;
import nchu.turbine.interfaces.service.ITasksDisplayService;
import nchu.turbine.thread.DownloadingObserver;
import nchu.turbine.view.DownloadingTaskPanel;
@Component
public class DownloadService extends BaseService implements IDownloadService{
	
	/**
	 * �����б���ʾ����
	 */
	@Autowired
	ITasksDisplayService displayService;
	
	DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	final static long MB=1024*1024;
	final static long GB=MB*1024;
	
	/**
	 * ��ʼ��������
	 * @param torrentfile			�����ļ�
	 * @param directorty			����·��
	 * </br>EditDate: 2017-05-21
	 */
	@Override
	public void startdownload(File torrentfile,File directory){
		new Thread(new Runnable() {
			public void run() {
			try {
					SharedTorrent torrent=SharedTorrent.fromFile(torrentfile, directory);
					//����Torrent�ͻ���
					Client client=new Client(InetAddress.getLocalHost(), torrent);
					System.out.println("��ʼ��������");
					client.setMaxDownloadRate(500.0);
					client.setMaxUploadRate(10.0);
					
					//��ʼ����
					client.download();
					
					DownloadingTaskPanel taskPanel=displayService.addDownloadingTasks(torrent.getName(),
							sizeInfo(torrent.getSize()), torrent.getFilenames().size(), client, System.currentTimeMillis(),directory,torrentfile);
					
					//����ֱ���ļ��������
					client.waitForCompletion();	
					
					//��֤����ͣ���������
					validateComplete(torrent, taskPanel, directory);
					
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}	
	
	
	@Override
	public void startdownload(Client client,DownloadingTaskPanel taskPanel,File directory){
		new Thread(new Runnable() {
			public void run() {
				
					client.setMaxDownloadRate(500.0);
					client.setMaxUploadRate(10.0);
					
					//��ʼ����
					client.download();
					
					//��ӹ۲�
					client.addObserver(new DownloadingObserver(taskPanel));
					
					//��ȡ����
					SharedTorrent torrent=client.getTorrent();
					
					//�����ȴ�ֹͣ���ж�
					client.waitForCompletion();
					
					//��֤����ͣ���������
					validateComplete(torrent, taskPanel, directory);

					
			}
		}).start();
	}	
	/**
	 * ���������С��Ϣ
	 * @param size		�������ֽ���
	 * @return
	 * </br>EditDate: 2017-05-21
	 */
	protected String sizeInfo(long size){
		if(size>GB){
			return String.valueOf(((double)size/GB))+"MB";
		}else if(size>MB){
			return String.valueOf(((double)size/MB))+"MB";
		}else if(size>1024){
			return String.valueOf(size/1024)+"KB";
		}
		return String.valueOf(size)+"B";
	}
	
	protected void validateComplete(SharedTorrent torrent,DownloadingTaskPanel taskPanel,File directory){
		if(torrent.isComplete()){//��������Ƿ����
			exceptionService.handleException(new TurbineException(torrent.getName()+"�ļ��Ѿ�����!"),"��ʾ",JOptionPane.DEFAULT_OPTION);
			displayService.removeDownloadingTasks(taskPanel);
			displayService.addCompletedTasks(torrent.getName(),sizeInfo(torrent.getSize()), format.format(System.currentTimeMillis()), directory);
		}else{
			exceptionService.handleException(new TurbineException(torrent.getName()+"��������ͣ!"),"��ʾ",JOptionPane.DEFAULT_OPTION);
		}
	}
}
