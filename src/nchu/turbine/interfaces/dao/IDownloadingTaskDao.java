package nchu.turbine.interfaces.dao;

import java.io.IOException;
import java.util.Vector;

import nchu.turbine.view.DownloadingTaskPanel;

public interface IDownloadingTaskDao {

	/**
	 * ȡ���־û��ļ��еĵ�ǰ��������������
	 * @return		�־û��ļ��еĵ�ǰ��������������
	 * </br>EditDate: 2017-05-22
	 */
	Vector<DownloadingTaskPanel> find();
	
	/**
	 * �־û���ǰ���������������еĶ���
	 * @param ts	��ǰ��������������
	 * </br>EditDate: 2017-05-22
	 */
	void save(Vector<DownloadingTaskPanel> ts);
	
}
