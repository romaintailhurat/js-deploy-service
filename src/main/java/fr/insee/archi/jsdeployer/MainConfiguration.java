package fr.insee.archi.jsdeployer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainConfiguration {
	
	@Bean(name = "appName")
	public String getAppName() {
		return "js-deployer"; 
	}
	
}
