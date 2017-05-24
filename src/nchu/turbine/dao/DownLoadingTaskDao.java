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
			return	super.find(diretory);
	}

	@Override
	public void save(Vector<DownloadingTaskPanel> ts) {
		try {
			super.save(ts, diretory);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
