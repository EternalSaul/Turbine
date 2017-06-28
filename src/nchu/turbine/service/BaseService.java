package nchu.turbine.service;

import org.springframework.beans.factory.annotation.Autowired;

import nchu.turbine.interfaces.service.IHandleExceptionService;

/**
 * 服务对象基类，提供了异常处理功能
 * @author Saulxk
 * </br>EditDate: 2017-06-24
 */
public class BaseService{
  /**
 * 异常处理服务对象实例
 */
  @Autowired
  IHandleExceptionService exceptionService;
}
