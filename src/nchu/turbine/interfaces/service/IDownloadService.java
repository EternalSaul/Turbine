package nchu.turbine.interfaces.service;

import java.io.File;

import com.turn.ttorrent.client.Client;

import nchu.turbine.view.DownloadingTaskPanel;

/**
 * 下载服务，用于委派下载任务
 * @author Saulxk
 * </br>EditDate: 2017-05-24
 */
public interface IDownloadService {

	/**
	 * 用于新建任务时间中新任务的下载
	 * @param torrentfile		种子文件
	 * @param directory			下载文件保存路径
	 * </br>EditDate: 2017-05-23
	 */
	void startdownload(File torrentfile, File directory);

	/**
	 * 用户重新开始下载事件中被停止任务的下载
	 * @param client			Torrent下载客户端
	 * @param taskPanel			正在下载任务显示块
	 * @param directory			下载文件保存路径
	 * </br>EditDate: 2017-05-23
	 */
	void startdownload(Client client, DownloadingTaskPanel taskPanel, File directory);

}
