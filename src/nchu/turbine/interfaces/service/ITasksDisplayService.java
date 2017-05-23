package nchu.turbine.interfaces.service;

import java.awt.Event;
import java.io.File;

import com.turn.ttorrent.client.Client;

import nchu.turbine.view.CompletedTaskPanel;
import nchu.turbine.view.DownloadingTaskPanel;

public interface ITasksDisplayService {

	/**
	 * 按正在下载向量组，刷新显示正在下载任务列表
	 * </br>EditDate: 2017-05-21
	 */
	public void displayDownloadingTasks();
	/**
	 * 显示已完成的任务
	 * </br>EditDate: 2017-05-21
	 */
	public void displayCompletedTasks();
	/**
	 * 当新建任务时，添加一个下载任务到正在下载向量组
	 * @param nameLab		任务名	
	 * @param size			任务大小
	 * @param num			任务数量
	 * @param client		服务名
	 * @param createTime	创建时间
	 * @param torrent		种子文件路径
	 * @param saveDirectory	存储文件路径
	 * </br>EditDate: 2017-05-21
	 */
	public DownloadingTaskPanel addDownloadingTasks(String nameLab, String size, int num, Client client, long createTime,File saveDirectory,File torrent);

	/**
	 * 当删除任务，或者任务完成时
	 * 从正在下载向量组移除一个正在下载任务
	 * @param downloadingTaskPanel		改参数由删除按钮事件中传入
	 * </br>EditDate: 2017-05-21
	 */
	public void removeDownloadingTasks(DownloadingTaskPanel taskPanel);
	
	/**
	 * @param cLabname				任务名
	 * @param csize					任务大小
	 * @param ccompleteTime			任务完成时间
	 * @param saveDirectory					文件保存路径，用于查看删除
	 * 当任务完成时，添加一个下载任务到已完成的任务向量组
	 * </br>EditDate: 2017-05-21
	 */
	public CompletedTaskPanel addCompletedTasks(String cLabname,String csize,String ccompleteTime,File saveDirectory);
	
	/**
	 * 当用户选择删除一个已完成任务项时，从已完成的任务向量组删除该任务
	 * @param taskPanel 改参数由删除按钮事件中传入
	 * </br>EditDate: 2017-05-21
	 */
	public void removeCompletedTasks(CompletedTaskPanel taskPanel);
}
