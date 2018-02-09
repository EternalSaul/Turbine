package nchu.turbine.interfaces.service;

import nchu.turbine.exception.TurbineException;

/**
 * 该接口用于处理部分异常，并弹出窗体提示用户
 * @author Saulxk
 * </br>EditDate: 2017-05-21
 */
public interface IHandleExceptionService {
	/**
	 * @param exception			异常
	 * @param title				异常窗口名
	 * @param messageType		窗口类型
	 * </br>EditDate: 2017-05-21
	 */
	public void handleException(TurbineException exception,String title,int messageType);
}
