package nchu.turbine.interfaces.service;

public interface IDirectoryChooseService {
	/**
	 * ������window�ļ����������ѡ�������ļ�</br>
	 * @return		Torrent�ļ�����λ��
	 * </br>EditDate: 2017-05-18
	 */
	public String chooseTorrentFile();
	/**
	 * ������window�ļ����������ѡ���ļ�Ҫ���ص���·���ļ���</br>
	 * @return		�ļ��о���·��
	 * </br>EditDate: 2017-05-18
	 */
	public String chooseSaveDirectory();
}
