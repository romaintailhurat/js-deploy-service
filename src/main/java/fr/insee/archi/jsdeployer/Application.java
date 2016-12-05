package fr.insee.archi.jsdeployer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

import fr.insee.archi.jsdeployer.configuration.MVCConf;
import fr.insee.archi.jsdeployer.configuration.MainConfiguration;

@Import({ MainConfiguration.class, MVCConf.class })
@SpringBootApplication
public class Application {
	
	private static Log log = LogFactory.getLog(Application.class);

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        
        String appName = (String) ctx.getBean("appName"); 
        String test = ctx.getEnvironment().getProperty("test");

        log.info(String.format("Application %s has started", appName));
        log.info(String.format("%s", test));
    }

}
