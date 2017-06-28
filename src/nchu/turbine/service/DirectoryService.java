package nchu.turbine.service;

import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nchu.turbine.interfaces.service.IDirectoryChooseService;

/**
 * 路径服务实现
 * @author Saulxk
 * </br>EditDate: 2017-06-24
 */
@Component
public class DirectoryService extends BaseService implements IDirectoryChooseService{
	
	/**
	 * 种子选择器
	 */
	@Autowired
	FileDialog chooser;
	
	/**
	 * 文件保存器
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
