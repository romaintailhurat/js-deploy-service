package fr.insee.archi.jsdeployer.repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import fr.insee.archi.jsdeployer.DeployerController;

@Service
public class FileRepository implements AppRepository {
	
	private static Log log = LogFactory.getLog(DeployerController.class);
	
	@Value("${repository}")
	private String repository;

	@Override
	public boolean cleanApp(String appName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean storeApp(String appName, File content) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean storeApp(String appName, byte[] content) {
		log.info(String.format("Repo from properties : %s", repository));
		Path path = Paths.get(repository + appName + ".zip");
		try {
			Files.write(path, content);
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
