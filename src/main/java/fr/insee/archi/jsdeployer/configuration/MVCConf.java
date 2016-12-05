package fr.insee.archi.jsdeployer.configuration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import fr.insee.archi.jsdeployer.Constants;

@Configuration
public class MVCConf extends WebMvcConfigurerAdapter {
	
	private static Log log = LogFactory.getLog(MVCConf.class);
	
	private static final String STATIC_HANDLER = "/apps/**";
	
	@Value("${repository}")
	private String repository;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String staticDir = "file:" + repository + Constants.APPS_REPO;
		log.info("Handling static resources mapped to /apps/**, static resources directory is : " + staticDir);		
	    registry.addResourceHandler(STATIC_HANDLER)
	    		.addResourceLocations(staticDir);
	}
}
