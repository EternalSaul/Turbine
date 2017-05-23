package nchu.turbine.dao;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Vector;

import javax.swing.filechooser.FileSystemView;

import org.springframework.stereotype.Component;

import com.turn.ttorrent.client.Client;
import com.turn.ttorrent.client.SharedTorrent;

import nchu.turbine.event.DeleteDownloadingTaskActionListener;
import nchu.turbine.event.StopDownloadingTaskActionListener;
import nchu.turbine.interfaces.dao.IDownloadingTaskDao;
import nchu.turbine.view.DownloadingTaskPanel;

@Component
public class DownLoadingTaskDao extends BaseDao<DownloadingTaskPanel> implements IDownloadingTaskDao{
	final static String diretory=FileSystemView.getFileSystemView().getDefaultDirectory()+"/Turbine/turbineD.tur";
	
	@Override
	public Vector<DownloadingTaskPanel> find() {
		try {
			return	resetClient(super.find(diretory));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void save(Vector<DownloadingTaskPanel> ts) {
		try {
			super.save(cleanClient(ts),diretory);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private Vector<DownloadingTaskPanel> cleanClient(Vector<DownloadingTaskPanel> ts){
		Vector<DownloadingTaskPanel> vd=new Vector<DownloadingTaskPanel>();
		for(DownloadingTaskPanel taskPanel:ts){
			DownloadingTaskPanel panel=taskPanel.serializableClone();
			panel.getStop().setText("¼ÌÐø");
			vd.add(panel);
		}
		return vd;
	}
	
	private Vector<DownloadingTaskPanel> resetClient(Vector<DownloadingTaskPanel> vd) throws UnknownHostException, IOException, NoSuchAlgorithmException{
		Vector<DownloadingTaskPanel> ts=new Vector<DownloadingTaskPanel>();
		for(DownloadingTaskPanel taskPanel:vd){
			System.out.println("dao.torrent:"+taskPanel.getTorrent());
			System.out.println("dao.save:"+taskPanel.getSaveDirectory());
			SharedTorrent torrent=SharedTorrent.fromFile(taskPanel.getTorrent(),taskPanel.getSaveDirectory());
			Client client=new Client(InetAddress.getLocalHost(), torrent);
			client.setMaxDownloadRate(500.0);
			client.setMaxUploadRate(10.0);
			taskPanel.setClient(client);
			taskPanel.getStop().addActionListener(new StopDownloadingTaskActionListener(client,taskPanel));
			taskPanel.getDelete().addActionListener(new DeleteDownloadingTaskActionListener(client, taskPanel));
			ts.add(taskPanel);
		}
		return ts;
	}

	
}
