package nchu.turbine.service;

import org.springframework.stereotype.Component;

import nchu.turbine.exception.TurbineException;
import nchu.turbine.interfaces.service.IHandleExceptionService;

@Component
public class HandleExceptionService extends BaseService implements IHandleExceptionService{

	@Override
	public void handleException(TurbineException exception) {
	}
  
}
