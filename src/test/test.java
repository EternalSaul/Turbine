package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.file.FileSystem;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.filechooser.FileSystemView;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.junit.Test;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.FileSystemUtils;

import com.turn.ttorrent.client.Client;
import com.turn.ttorrent.client.SharedTorrent;
import com.turn.ttorrent.common.Torrent;

import nchu.turbine.thread.IndicateDownloading;
import nchu.turbine.utils.Base32Utils;

/**
 * Ttorrent¿ò¼Ü²âÊÔÀà
 * @author Saulxk
 * EditDate: 2017-05-17
 */
public class test {

	@Test
	public void downloadTest() {
		try {
			SharedTorrent torrent=SharedTorrent.fromFile(new File("ubuntu-12.04.5-desktop-i386.iso.torrent"),new File("C:\\Users\\Saulxk\\Desktop\\ÍøÂç±à³Ì"));
			List<String> s=torrent.getFilenames();
			Client client=new Client(InetAddress.getLocalHost(), torrent);
			client.setMaxDownloadRate(500.0);
			client.setMaxUploadRate(10.0);
			new Thread(new IndicateDownloading(client)).start();
			client.download();
			client.addObserver(new Observer() {
				@Override
				public void update(Observable o, Object arg) {
					Client client = (Client) o;
				    float progress = client.getTorrent().getCompletion();
				    System.out.println(progress);
				}
			});
			client.waitForCompletion();
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

	@Test
	public void downloadTest02() {
		try {
			File file=new File("C:\\Users\\Saulxk\\Desktop\\ÍøÂç±à³Ì\\config\\file\\jp");
			if(!file.exists()){
				file.mkdirs();
			}
			SharedTorrent torrent=SharedTorrent.fromFile(new File("kkk.torrent"),new File("C:\\Users\\Saulxk\\Desktop\\ÍøÂç±à³Ì\\config\\file\\jp"));
			List<String> ss=torrent.getFilenames();
			Client client=new Client(InetAddress.getLocalHost(), torrent);
			System.out.println(torrent.getCreatedBy());
			System.out.println(torrent.isMultifile());
			for(String s:ss){
				System.out.println(s);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void downloadTest03() {
		try {
			File file=new File("C:\\Users\\Saulxk\\Desktop\\ÍøÂç±à³Ì\\config\\file\\t\\s.torrent");
			if(file.exists()){
				file.mkdirs();
			}
			Torrent torrent= SharedTorrent.create(file
					, new URI("magnet:?xt=urn:btih:4F8247E48C70F7770FD9C25F0B4554A4541D5626")
					,"sds");
			torrent.save(new FileOutputStream(file));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void m_to_t(){
		try {
			URL url=new URL("http://bt.box.n0808.com/4F/26/4F8247E48C70F7770FD9C25F0B4554A4541D5626.torrent");
			HttpURLConnection connection=(HttpURLConnection) url.openConnection();
			connection.setConnectTimeout(400000);
			connection.setReadTimeout(100000);
			InputStream inputStream=connection.getInputStream();
			System.out.println(inputStream.available());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void base32() throws DecoderException{
		Base32 base32=new Base32();
		BigInteger integer=new BigInteger("03621694F0E8B2CE87216C99CB5CA3AF23029E37",16);
		String b32=new String(base32.encode(integer.toByteArray()));
		System.out.println(b32);
	}
	
	@Test
	public void magnet() {
//		magnet:?xt=urn:btih:92F0151BC73EDB47EB573052478ECA4ACAE899D1
		String magnetlink="magnet:?xt=urn:btih:11aabbec897260de25a71f149712114bf9e38ddf";
		String urn=magnetlink.replaceAll("^\\S*btih:\\b","");
		System.out.println(urn);
		String hash=Base32Utils.biginteger_Encode_Base32(urn);
		System.out.println(hash);
	}
	
	@Test
	public void sysdirect() {
		System.out.println(getClass().getName());
		FileSystemView view=FileSystemView.getFileSystemView();
		System.out.println(view.getDefaultDirectory().getPath());
	}
}
