package nchu.turbine.view;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Turbine上下文
 * @author Saulxk
 * </br>EditDate: 2017-05-23
 */
public class TurbineView {
	final private static ClassPathXmlApplicationContext applicationContext=new ClassPathXmlApplicationContext("Bean.xml");
	public static ClassPathXmlApplicationContext getContext(){
		return applicationContext;
	}
}
