package nchu.turbine.interfaces.service;

import java.io.File;

import com.turn.ttorrent.common.Torrent;

/**
 * ͨ���������ӻ�ȡ�����ļ�
 * @author Saulxk
 * </br>EditDate: 2017-05-23
 */
public interface IMagnetService {
	
	/**
	 * ���ڷ��������ļ��ı���λ��
	 * ������������޷�ת��Ϊ�����ļ����򷵻�null
	 * @param magnetlink
	 * @return	ָ�������ļ�����λ�õ��ļ�����
	 * </br>EditDate: 2017-05-20
	 */
	public File Magnet2Torrent(String magnetlink);
	
	/**
	 * ���ڻ�ȡ����������ת��Ϊ�����ļ��ĵ�ǰ����
	 * @return
	 * </br>EditDate: 2017-05-20
	 */
	double getProcess();
}
