package nchu.turbine.thread;

import com.turn.ttorrent.client.Client;

import nchu.turbine.view.DownloadingTaskPanel;

//public class FlushDownloading extends Thread {
//	final static int KB=1024;
//	long total;//����
//	long remain;//ʣ����
//	long downloaded;//��ǰһ���������
//	double rate;
//	Client client;
//	DownloadingTaskPanel card;
//	
//	public FlushDownloading(Client client, DownloadingTaskPanel card) {
//		super();
//		this.client = client;
//		this.card = card;
//	}
//
//	public void run() {
//		while(true){
//		remain=total=client.getTorrent().getSize();
//		timeUpdate();
//		rateUpdate();
//		try {
//			this.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		}
//	}
//	
//	/**
//	 * ����ʱ��
//	 * </br>EditDate: 2017-05-21
//	 */
//	 private void timeUpdate(){
//		downloaded=(remain-client.getTorrent().getLeft());//��ȥʣ������ȥ��ǰʣ������Ϊ��ǰ������
//		remain=remain-downloaded;//��ǰʣ����
//		rate=downloaded;//ת��Ϊdouble
//		//ʱ�����
//		long remaincopy=remain;
//		int hour=(int) (remaincopy/(downloaded*3600));//ʣ��Сʱ
//		remaincopy=(remaincopy%(downloaded*3600));
//		int minute=(int)(remaincopy/(downloaded*60));
//		remaincopy=(remaincopy%(downloaded*60));
//		int second=(int) (remaincopy%(downloaded));//ʣ������
//		
//		card.setTime(hour+"Сʱ"+minute+"����"+second+"��");
//	}
//	
//	/**
//	 * ���������ٶ�
//	 * </br>EditDate: 2017-05-21
//	 */
//	private void rateUpdate(){
//		if((rate=rate/KB)<1024){
//			card.setSpeed(String.format("%.2lf",rate)+"KB/s");
//		}else{
//			rate/=1024;
//			card.setSpeed(String.format("%.2lf",rate)+"MB/s");
//		}
////		System.out.println("�ļ���"+client.getTorrent().getFilenames().get(0));
////		System.out.println("�ļ��ܴ�С"+client.getTorrent().getSize());
////		System.out.println("��������"+client.getTorrent().getTrackerCount());
////		System.out.println("�ϴ���"+client.getTorrent().getUploaded());
////		System.out.println("������"+client.getTorrent().getLeft()+"\n\n");
//	}
//}
