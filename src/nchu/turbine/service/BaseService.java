package nchu.turbine.service;

import org.springframework.beans.factory.annotation.Autowired;

import nchu.turbine.interfaces.service.IHandleExceptionService;

public class BaseService{
  @Autowired
  IHandleExceptionService exceptionService;
}
