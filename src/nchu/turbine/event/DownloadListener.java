package nchu.turbine.event;

import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.xml.stream.events.StartDocument;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.turn.ttorrent.common.Torrent;

import nchu.turbine.exception.TorrentFileNotFoundException;
import nchu.turbine.interfaces.service.IDownloadService;
import nchu.turbine.interfaces.service.IMagnetService;
import nchu.turbine.interfaces.service.ITasksDisplayService;
import nchu.turbine.view.TurbineView;

/**
 * ���������ذ�ť�¼�
 * @author Saulxk
 * </br>EditDate: 2017-05-24
 */
@Component
@Qualifier("downloadListener")
public class DownloadListener implements MouseListener{
	private JTextField savePath;
	private JTextField torrentPath;
	private JFrame frame;
	private JPanel filesDisplay;
	ExecutorService service=Executors.newFixedThreadPool(1);
	/**
	 * ��������ת������
	 */
	@Autowired
	private IMagnetService magnetService;
	
	@Autowired
	private ITasksDisplayService displayService;
	
	/**
	 * ���ط���
	 */
	@Autowired
	private IDownloadService downloadService;
	
	public void setSavePath(JTextField savePath) {
		this.savePath = savePath;
	}

	public void setTorrentPath(JTextField torrentPath) {
		this.torrentPath = torrentPath;
	}
	
	public void setFilesDisplay(JPanel filesDisplay) {
		this.filesDisplay = filesDisplay;
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
		
		//��ȡ�����ļ�
		if(tp.startsWith("magnet:?xt=urn:btih:")){//��������
			JOptionPane.showMessageDialog(frame, "���ȷ����ʼ����������,�ȴ�������ʾ��","�ȴ�", JOptionPane.PLAIN_MESSAGE);
			Future<File> future=service.submit(new Callable<File>() {//�����߳�Callable,�����ݸ��̳߳�
				@Override
				public File call() throws Exception {
					return magnetService.Magnet2Torrent(tp);
				}
			});
			try {
				//�����߳�
				torrentfile=future.get(2, TimeUnit.MINUTES);
			} catch (InterruptedException | ExecutionException | TimeoutException e1) {
				e1.printStackTrace();
			}
			if(torrentfile==null){
				JOptionPane.showMessageDialog(frame, "�������Ӽ��ʧ�ܣ�δ�ҵ���Ӧ�����ļ�!","����", JOptionPane.WARNING_MESSAGE);
				frame.setVisible(false);
			}
//			else{
//				JOptionPane.showMessageDialog(frame, "���ɹ���ʼ����","�ɹ�", JOptionPane.CLOSED_OPTION);
//			}
//			torrentfile=magnetService.Magnet2Torrent(tp);
		}else{
			torrentfile=new File(tp);
			if(!torrentfile.exists()){
				JOptionPane.showMessageDialog(frame, "�Ҳ��������ļ��������ļ�·������!","����", JOptionPane.WARNING_MESSAGE);
				frame.setVisible(false);
				throw new TorrentFileNotFoundException("�Ҳ��������ļ��������ļ�·������!");
			}
		}
		
		int i=1;
		try {
			//�г��ļ��嵥���û�ȷ���Ƿ�����
			Torrent torrent=Torrent.load(torrentfile);
			displayService.displayTorrentFilesList(filesDisplay, torrent.getFilenames());
			
			//����ȷ��������0Ϊȷ������
			i=JOptionPane.showConfirmDialog(frame,"���ɹ������ȷ����ʼ����","���Ӽ��سɹ�",JOptionPane.YES_NO_OPTION);
			
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		//����ļ��嵥
		displayService.removeTorrentFilesList(filesDisplay);
		
		//�û�ȷ������
		if(i==0){
		//��ȡ����·��
		savedirectory=new File(sp);
		if(!savedirectory.exists())	savedirectory.mkdirs();
		
		//��ʼ����
		downloadService.startdownload(torrentfile, savedirectory);
		}
		
		//��մ�����Ϣ
		savePath.setText("");
		torrentPath.setText("");
		
		//�رմ�����ʾ
		frame.setVisible(false);
		
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
