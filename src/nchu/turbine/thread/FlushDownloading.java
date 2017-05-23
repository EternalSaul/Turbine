package nchu.turbine.thread;

import com.turn.ttorrent.client.Client;

import nchu.turbine.view.DownloadingTaskPanel;

//public class FlushDownloading extends Thread {
//	final static int KB=1024;
//	long total;//总量
//	long remain;//剩余量
//	long downloaded;//当前一秒的下载量
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
//	 * 更新时间
//	 * </br>EditDate: 2017-05-21
//	 */
//	 private void timeUpdate(){
//		downloaded=(remain-client.getTorrent().getLeft());//过去剩余量减去当前剩余量，为当前下载量
//		remain=remain-downloaded;//当前剩余量
//		rate=downloaded;//转化为double
//		//时间计算
//		long remaincopy=remain;
//		int hour=(int) (remaincopy/(downloaded*3600));//剩余小时
//		remaincopy=(remaincopy%(downloaded*3600));
//		int minute=(int)(remaincopy/(downloaded*60));
//		remaincopy=(remaincopy%(downloaded*60));
//		int second=(int) (remaincopy%(downloaded));//剩余秒钟
//		
//		card.setTime(hour+"小时"+minute+"分钟"+second+"秒");
//	}
//	
//	/**
//	 * 更新下载速度
//	 * </br>EditDate: 2017-05-21
//	 */
//	private void rateUpdate(){
//		if((rate=rate/KB)<1024){
//			card.setSpeed(String.format("%.2lf",rate)+"KB/s");
//		}else{
//			rate/=1024;
//			card.setSpeed(String.format("%.2lf",rate)+"MB/s");
//		}
////		System.out.println("文件名"+client.getTorrent().getFilenames().get(0));
////		System.out.println("文件总大小"+client.getTorrent().getSize());
////		System.out.println("服务器数"+client.getTorrent().getTrackerCount());
////		System.out.println("上传数"+client.getTorrent().getUploaded());
////		System.out.println("下载数"+client.getTorrent().getLeft()+"\n\n");
//	}
//}
