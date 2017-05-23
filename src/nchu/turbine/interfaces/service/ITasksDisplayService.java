package nchu.turbine.interfaces.service;

import java.awt.Event;
import java.io.File;

import com.turn.ttorrent.client.Client;

import nchu.turbine.view.CompletedTaskPanel;
import nchu.turbine.view.DownloadingTaskPanel;

public interface ITasksDisplayService {

	/**
	 * ���������������飬ˢ����ʾ�������������б�
	 * </br>EditDate: 2017-05-21
	 */
	public void displayDownloadingTasks();
	/**
	 * ��ʾ����ɵ�����
	 * </br>EditDate: 2017-05-21
	 */
	public void displayCompletedTasks();
	/**
	 * ���½�����ʱ�����һ������������������������
	 * @param nameLab		������	
	 * @param size			�����С
	 * @param num			��������
	 * @param client		������
	 * @param createTime	����ʱ��
	 * @param torrent		�����ļ�·��
	 * @param saveDirectory	�洢�ļ�·��
	 * </br>EditDate: 2017-05-21
	 */
	public DownloadingTaskPanel addDownloadingTasks(String nameLab, String size, int num, Client client, long createTime,File saveDirectory,File torrent);

	/**
	 * ��ɾ�����񣬻����������ʱ
	 * �����������������Ƴ�һ��������������
	 * @param downloadingTaskPanel		�Ĳ�����ɾ����ť�¼��д���
	 * </br>EditDate: 2017-05-21
	 */
	public void removeDownloadingTasks(DownloadingTaskPanel taskPanel);
	
	/**
	 * @param cLabname				������
	 * @param csize					�����С
	 * @param ccompleteTime			�������ʱ��
	 * @param saveDirectory					�ļ�����·�������ڲ鿴ɾ��
	 * ���������ʱ�����һ��������������ɵ�����������
	 * </br>EditDate: 2017-05-21
	 */
	public CompletedTaskPanel addCompletedTasks(String cLabname,String csize,String ccompleteTime,File saveDirectory);
	
	/**
	 * ���û�ѡ��ɾ��һ�������������ʱ��������ɵ�����������ɾ��������
	 * @param taskPanel �Ĳ�����ɾ����ť�¼��д���
	 * </br>EditDate: 2017-05-21
	 */
	public void removeCompletedTasks(CompletedTaskPanel taskPanel);
}
