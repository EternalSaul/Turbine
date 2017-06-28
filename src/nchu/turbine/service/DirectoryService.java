package nchu.turbine.service;

import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nchu.turbine.interfaces.service.IDirectoryChooseService;

/**
 * ·������ʵ��
 * @author Saulxk
 * </br>EditDate: 2017-06-24
 */
@Component
public class DirectoryService extends BaseService implements IDirectoryChooseService{
	
	/**
	 * ����ѡ����
	 */
	@Autowired
	FileDialog chooser;
	
	/**
	 * �ļ�������
	 */
	@Autowired
	DirectoryDialog saver;
	
	/* (non-Javadoc)
	 * @see nchu.turbine.interfaces.service.IDirectoryChooseService#chooseTorrentFile()
	 * </br>EditDate: 2017-05-18
	 */
	@Override
	public String chooseTorrentFile() {
		return chooser.open();
	}

	/* (non-Javadoc)
	 * @see nchu.turbine.interfaces.service.IDirectoryChooseService#chooseSaveDirectory()
	 * </br>EditDate: 2017-05-18
	 */
	@Override
	public String chooseSaveDirectory() {
		return saver.open();
	}

}
