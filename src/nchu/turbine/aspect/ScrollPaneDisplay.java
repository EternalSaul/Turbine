package nchu.turbine.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ScrollPaneDisplay {
//	@Pointcut("execution(void nchu.turbine.view.SecondPanel.kk(..))")
//	public void scrollPanelDispaly(){}
//	
//	@After("scrollPanelDispaly()")
//	public void DownloadingCardDisplay(){
//		System.out.println("sds");
//	}
}
