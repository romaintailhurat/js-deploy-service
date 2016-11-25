package fr.insee.archi.jsdeployer.repository;

import java.io.File;

import org.springframework.stereotype.Service;

public interface AppRepository {
	
	boolean cleanApp(String appName);
	
	boolean storeApp(String appName, File content);
	
	boolean storeApp(String appName, byte[] content);

}
