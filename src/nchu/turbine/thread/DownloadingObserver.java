package nchu.turbine.thread;

import java.util.Observable;
import java.util.Observer;

import com.turn.ttorrent.client.Client;

import nchu.turbine.view.DownloadingTaskPanel;

public class DownloadingObserver implements Observer{

	final static int KB=1024;
	long remain;//剩余量
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
		
		//间隔的毫秒数
		double mills=times;
		times=System.currentTimeMillis();
		
		if(remain!=0){
		
			//间隔的秒数
			mills=(times-mills)/1000;
			System.out.println("mills:"+mills);
			
			//间隔时间内下载量
			long download=remain-client.getTorrent().getLeft();
			remain=remain-download;
			System.out.println("这是是DOWN:"+download);
			System.out.println("这是mills:"+mills);
			rate=download/mills;
			System.out.println("这是rate:"+rate);
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
	 * 更新时间
	 * </br>EditDate: 2017-05-21
	 */
	 private void timeUpdate(){

		//时间计算
		int hour=0,minute=0,second=0;
		
		if(rate>0){
			long remaincopy=remain;
			hour=(int) (remaincopy/(rate*3600));//剩余小时
			remaincopy=(long) (remaincopy%(rate*3600));
			minute=(int)(remaincopy/(rate*60));
			remaincopy=(long) (remaincopy%(rate*60));
			second=(int) (remaincopy/rate);//剩余秒钟
		}
		
		card.setTime(hour+"小时"+minute+"分钟"+second+"秒");
		
		System.out.println(hour+"小时"+minute+"分钟"+second+"秒");
		
	}
	
	/**
	 * 更新下载速度
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
