package nchu.turbine.service;

import org.springframework.beans.factory.annotation.Autowired;

import nchu.turbine.interfaces.service.IHandleExceptionService;

/**
 * ���������࣬�ṩ���쳣������
 * @author Saulxk
 * </br>EditDate: 2017-06-24
 */
public class BaseService{
  /**
 * �쳣����������ʵ��
 */
  @Autowired
  IHandleExceptionService exceptionService;
}
