package nchu.turbine.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

import nchu.turbine.event.EventScanTarget;
import nchu.turbine.service.ServiceScanTarget;
import nchu.turbine.view.MyFrame;
import nchu.turbine.view.ViewScanTarget;

@Configuration
@Import(value={TurbineContextConfig.class})
@ComponentScan(basePackageClasses={ViewScanTarget.class,EventScanTarget.class})
public class TurbineViewConfig {
}
