package nchu.turbine.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

import nchu.turbine.service.ServiceScanTarget;
import nchu.turbine.view.MyFrame;
import nchu.turbine.view.ViewScanTarget;

/**
 * 为集合配置类，它集合了其他两个配置类
 * @author Saulxk
 * </br>EditDate: 2017-05-24
 */
@Configuration
@EnableAspectJAutoProxy
@Import(value={TurbineViewConfig.class,TurbineContextConfig.class})
public class TurbineMainConfig{
}
