package nchu.turbine.interfaces.service;

public interface IDirectoryChooseService {
	/**
	 * 用于在window文件浏览窗口下选择种子文件</br>
	 * @return		Torrent文件绝对位置
	 * </br>EditDate: 2017-05-18
	 */
	public String chooseTorrentFile();
	/**
	 * 用于在window文件浏览窗口下选择文件要下载到的路径文件夹</br>
	 * @return		文件夹绝对路径
	 * </br>EditDate: 2017-05-18
	 */
	public String chooseSaveDirectory();
}
