package nchu.turbine.event;

import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.lang.reflect.Field;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.xml.stream.events.StartDocument;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import nchu.turbine.exception.TorrentFileNotFoundException;
import nchu.turbine.interfaces.service.IDownloadService;
import nchu.turbine.interfaces.service.IMagnetService;
import nchu.turbine.view.TurbineView;

@Component
@Qualifier("downloadListener")
public class DownloadListener implements MouseListener{
	private JTextField savePath;
	private JTextField torrentPath;
	private JFrame frame;
	ExecutorService service=Executors.newFixedThreadPool(1);
	/**
	 * ��������ת������
	 */
	@Autowired
	private IMagnetService magnetService;
	
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
			}else{
				JOptionPane.showMessageDialog(frame, "���ɹ���ʼ����","�ɹ�", JOptionPane.CLOSED_OPTION);
			}
//			torrentfile=magnetService.Magnet2Torrent(tp);
		}else{
			torrentfile=new File(tp);
			if(!torrentfile.exists()){
				JOptionPane.showMessageDialog(frame, "�Ҳ��������ļ��������ļ�·������!","����", JOptionPane.WARNING_MESSAGE);
				frame.setVisible(false);
				throw new TorrentFileNotFoundException("�Ҳ��������ļ��������ļ�·������!");
			}
		}
		
		//��ȡ����·��
		savedirectory=new File(sp);
		if(!savedirectory.exists())	savedirectory.mkdirs();
		
		downloadService.startdownload(torrentfile, savedirectory);
		
		savePath.setText("");
		torrentPath.setText("");
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
