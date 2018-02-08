package nchu.turbine.interfaces.service;

import java.io.File;

import com.turn.ttorrent.client.Client;

import nchu.turbine.view.DownloadingTaskPanel;

/**
 * ���ط�������ί����������
 * @author Saulxk
 * </br>EditDate: 2017-05-24
 */
public interface IDownloadService {

	/**
	 * �����½�����ʱ���������������
	 * @param torrentfile		�����ļ�
	 * @param directory			�����ļ�����·��
	 * </br>EditDate: 2017-05-23
	 */
	void startdownload(File torrentfile, File directory);

	/**
	 * �û����¿�ʼ�����¼��б�ֹͣ���������
	 * @param client			Torrent���ؿͻ���
	 * @param taskPanel			��������������ʾ��
	 * @param directory			�����ļ�����·��
	 * </br>EditDate: 2017-05-23
	 */
	void startdownload(Client client, DownloadingTaskPanel taskPanel, File directory);

}
