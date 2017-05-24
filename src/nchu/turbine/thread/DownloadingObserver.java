package nchu.turbine.thread;

import java.util.Observable;
import java.util.Observer;

import com.turn.ttorrent.client.Client;

import nchu.turbine.view.DownloadingTaskPanel;

public class DownloadingObserver implements Observer{

	final static int KB=1024;
	long remain;//ʣ����
	double rate;
	DownloadingTaskPanel card;
	float progress;
	Client client;
	long times;
	public DownloadingObserver(DownloadingTaskPanel taskPanel) {
		this.card = taskPanel;
		client=taskPanel.getClient();
		times=System.currentTimeMillis();
//		flushView();
	}

	@Override
	public void update(Observable o, Object arg) {
		Client client=(Client)o;
		
		//����ĺ�����
		double mills=times;
		times=System.currentTimeMillis();
		
		if(remain!=0){
		
			//���������
			mills=(times-mills)/1000;
			System.out.println("mills:"+mills);
			
			//���ʱ����������
			long download=remain-client.getTorrent().getLeft();
			remain=remain-download;
			System.out.println("������DOWN:"+download);
			System.out.println("����mills:"+mills);
			rate=download/mills;
			System.out.println("����rate:"+rate);
			timeUpdate();
			rateUpdate();
			
			progress=client.getTorrent().getCompletion();
			card.setProgress(progress);
			System.out.println(client.getTorrent().getCompletion());
			
		}else{
			remain=client.getTorrent().getLeft();
		}
	}
	
	/**
	 * ����ʱ��
	 * </br>EditDate: 2017-05-21
	 */
	 private void timeUpdate(){

		//ʱ�����
		int hour=0,minute=0,second=0;
		
		if(rate>0){
			long remaincopy=remain;
			hour=(int) (remaincopy/(rate*3600));//ʣ��Сʱ
			remaincopy=(long) (remaincopy%(rate*3600));
			minute=(int)(remaincopy/(rate*60));
			remaincopy=(long) (remaincopy%(rate*60));
			second=(int) (remaincopy/rate);//ʣ������
		}
		
		card.setTime(hour+"Сʱ"+minute+"����"+second+"��");
		
		System.out.println(hour+"Сʱ"+minute+"����"+second+"��");
		
	}
	
	/**
	 * ���������ٶ�
	 * </br>EditDate: 2017-05-21
	 */
	private void rateUpdate(){
		rate=rate/1024;
		if(rate<1){
			card.setSpeed(rate+"B/s");
		}
		else if(rate<1024){
			card.setSpeed(String.format("%.2f",rate)+"KB/s");
			System.out.println(rate+"KB/s");
		}else{
			rate/=1024;
			card.setSpeed(String.format("%.2f",rate)+"MB/s");
			System.out.println(rate+"MB/s");
		};
		System.out.println("ratex:"+rate);
	}
	
}
