package nchu.turbine.interfaces.dao;

import java.io.IOException;
import java.util.Vector;

import nchu.turbine.view.CompletedTaskPanel;

public interface ICompletedTaskDao {
	/**
	 * ȡ���־û���������������������
	 * @return    �־û��ļ�������ɵ�����������
	 * </br>EditDate: 2017-05-23
	 */
	Vector<CompletedTaskPanel> find();
	/**
	 * �־û������������������еĶ���
	 * @param ts		����������������
	 * </br>EditDate: 2017-05-23
	 */
	void save(Vector<CompletedTaskPanel> ts);
}
