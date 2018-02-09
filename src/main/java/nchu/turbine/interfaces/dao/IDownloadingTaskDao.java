package nchu.turbine.interfaces.dao;

import java.io.IOException;
import java.util.Vector;

import nchu.turbine.view.DownloadingTaskPanel;

public interface IDownloadingTaskDao {

	/**
	 * 取出持久化文件中的当前下载任务向量组
	 * @return		持久化文件中的当前下载任务向量组
	 * </br>EditDate: 2017-05-22
	 */
	Vector<DownloadingTaskPanel> find();
	
	/**
	 * 持久化当前下载任务向量组中的对象
	 * @param ts	当前下载任务向量组
	 * </br>EditDate: 2017-05-22
	 */
	void save(Vector<DownloadingTaskPanel> ts);
	
}
