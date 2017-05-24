package nchu.turbine.interfaces.dao;

import java.io.IOException;
import java.util.Vector;

import nchu.turbine.view.CompletedTaskPanel;

public interface ICompletedTaskDao {
	/**
	 * 取出持久化的已完成任务对象向量组
	 * @return    持久化文件中已完成的任务向量组
	 * </br>EditDate: 2017-05-23
	 */
	Vector<CompletedTaskPanel> find();
	/**
	 * 持久化已完成任务的向量组中的对象
	 * @param ts		已完成任务的向量组
	 * </br>EditDate: 2017-05-23
	 */
	void save(Vector<CompletedTaskPanel> ts);
}
