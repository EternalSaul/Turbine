package nchu.turbine.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;

import javax.swing.filechooser.FileSystemView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
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
		document+="/Tubine";
		File torrentdirectory=new File(document);
		if(!torrentdirectory.exists()) torrentdirectory.mkdirs();		//����TurbineĬ���ĵ�
		File torrent=new File(document+"/"+System.currentTimeMillis()+".torrent");		//����һ�������ļ�
		return torrent;
	}
	
}
