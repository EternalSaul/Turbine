package nchu.turbine.interfaces.service;

import nchu.turbine.exception.TurbineException;

/**
 * �ýӿ����ڴ������쳣��������������ʾ�û�
 * @author Saulxk
 * </br>EditDate: 2017-05-21
 */
public interface IHandleExceptionService {
	/**
	 * @param exception			�쳣
	 * @param title				�쳣������
	 * @param messageType		��������
	 * </br>EditDate: 2017-05-21
	 */
	public void handleException(TurbineException exception,String title,int messageType);
}
