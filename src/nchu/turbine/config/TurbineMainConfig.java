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

@Configuration
@EnableAspectJAutoProxy
@Import(value={TurbineViewConfig.class,TurbineContextConfig.class})
public class TurbineMainConfig{
}
