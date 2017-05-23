package nchu.turbine.interfaces.dao;

import java.io.IOException;
import java.util.Vector;

import nchu.turbine.view.DownloadingTaskPanel;

public interface IDownloadingTaskDao {

	/**
	 * @return
	 * </br>EditDate: 2017-05-22
	 */
	Vector<DownloadingTaskPanel> find();
	
	/**
	 * @param ts	��ǰ��������������
	 * </br>EditDate: 2017-05-22
	 */
	void save(Vector<DownloadingTaskPanel> ts);
	
}
