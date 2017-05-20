package nchu.turbine.event;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.lang.reflect.Field;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import nchu.turbine.exception.TorrentFileNotFoundException;
import nchu.turbine.interfaces.service.IMagnetService;
import nchu.turbine.view.TurbineView;

@Component
@Qualifier("downloadListener")
public class DownloadListener implements MouseListener{
	private JTextField savePath;
	private JTextField torrentPath;
	private JFrame frame;
	@Autowired
	private IMagnetService magnetService;

	public void setSavePath(JTextField savePath) {
		this.savePath = savePath;
	}

	public void setTorrentPath(JTextField torrentPath) {
		this.torrentPath = torrentPath;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		String sp=savePath.getText().trim();
		String tp=torrentPath.getText().trim();
		File torrentfile=null,
			 savedirectory=null;
		
		//获取种子文件
		if(tp.startsWith("magnet:?xt=urn:btih:")){//磁力链接
			torrentfile=magnetService.Magnet2Torrent(tp);
		}else{
			torrentfile=new File(tp);
			if(!torrentfile.exists()){	
				JOptionPane.showMessageDialog(frame, "找不到种子文件，种子文件路径错误!","错误", JOptionPane.WARNING_MESSAGE);
				throw new TorrentFileNotFoundException("找不到种子文件，种子文件路径错误!");
			}
		}
		
		//获取保存路径
		savedirectory=new File(tp);
		if(!savedirectory.exists())	savedirectory.mkdirs();
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

}
