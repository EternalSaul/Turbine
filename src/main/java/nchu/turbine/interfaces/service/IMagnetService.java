package nchu.turbine.interfaces.service;

import java.io.File;

import com.turn.ttorrent.common.Torrent;

/**
 * 通过磁力链接获取种子文件
 * @author Saulxk
 * </br>EditDate: 2017-05-23
 */
public interface IMagnetService {
	
	/**
	 * 用于返回种子文件的保存位置
	 * 如果磁力链接无法转换为种子文件，则返回null
	 * @param magnetlink
	 * @return	指向种子文件保存位置的文件引用
	 * </br>EditDate: 2017-05-20
	 */
	public File Magnet2Torrent(String magnetlink);
	
	/**
	 * 用于获取当磁力链接转化为种子文件的当前进度
	 * @return
	 * </br>EditDate: 2017-05-20
	 */
	double getProcess();
}
