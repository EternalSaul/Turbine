package nchu.turbine.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.filechooser.FileSystemView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nchu.turbine.interfaces.service.IMagnetService;
import nchu.turbine.utils.Base32Utils;

@Component
public class MagnetService implements IMagnetService{
	
	@Autowired
	FileSystemView fileSystemView;
	
	double process=0;
	
	@Override
	public double getProcess() {
		return process;
	}

	@Override
	public File Magnet2Torrent(String magnetlink) {
		//������������
		String urn=magnetlink.replaceAll("^\\S*btih:\\b","");
		File tf=null;
		try {
			tf=TurbineMagnetServer(urn);
			System.out.println("û�ҵ�");
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			if(tf==null) tf=VezuMagnetServer(urn);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tf;
	}
	
	public File TurbineMagnetServer(String hash) throws IOException{
		
		System.out.println("TurbineMagnetServerִ��");
		
		//����������
		URL url=new URL("http://127.0.0.1:6868/"+hash);
		HttpURLConnection connection=(HttpURLConnection) url.openConnection();

		//����Ǵ������Ӧֱ�ӷ���null
		if(connection.getResponseCode()!=200) return null;
		if(!connection.getContentType().equals("application/x-bittorrent")) return null;
		
		//��ȡ���������ļ���·��
		File tf=getTorrentSavePath();
		
		//����Ϣд�������ļ�
		FileOutputStream fo=new FileOutputStream(tf);
		InputStream is=connection.getInputStream();
		byte b[]=new byte[4096];
		int len=0;
		int k=0;
		System.out.println(is.available());
		while((len=is.read(b, 0, 4096))!=-1){
			k+=len;
			fo.write(b, 0, len);
			fo.flush();
		}
		
		System.out.println("TurbineMagnetServer�ѷ���");
		
		return tf;
	}
	
	public File VezuMagnetServer(String urn) throws IOException{
		System.out.println("vezu�Ѿ�ִ��");
		String hash=Base32Utils.biginteger_Encode_Base32(urn);
		String link="http://magnet.vuze.com/magnetLookup?hash="+hash;
		//���Ի�ȡ�����ļ�
		File tf=null;
		int len=0;
		byte[] b=new byte[10240];
		tf=getTorrentSavePath();	//�����ļ�����·��
		HttpURLConnection connection=null;
		try {
			URL url = new URL(link);
			connection = (HttpURLConnection) url.openConnection();	///��ȡHTTP����
			if(connection.getResponseCode()!=200) return null;
			if(!connection.getContentType().equals("application/x-bittorrent"))	return null;	//�ж������Ƿ�Ϊ����
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		//��ȡ�����ļ�
		try(InputStream is=connection.getInputStream();
			BufferedOutputStream tfos=new BufferedOutputStream(new FileOutputStream(tf),10240);){
			long total=connection.getContentLengthLong();		//��ȡ�����ļ��ܳ���
			System.out.println("length:"+total);
			double downloaded=0;
			while((len=is.read(b,0, 10240))!=-1){		//����
				downloaded+=len;
				process=downloaded/total*100;
				System.out.println(process);
				tfos.write(b,0,len);
				tfos.flush();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tf;
		
	}
	
	
	protected File getTorrentSavePath(){
		String document=fileSystemView.getDefaultDirectory().getPath();
		document+="/Turbine";
		File torrentdirectory=new File(document);
		if(!torrentdirectory.exists()) torrentdirectory.mkdirs();		//����TurbineĬ���ĵ�
		File torrent=new File(document+"/"+System.currentTimeMillis()+".torrent");		//����һ�������ļ�
		return torrent;
	}
	
}
