package fr.insee.archi.jsdeployer.repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;

@Service
public class FileRepository implements AppRepository {

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
		Path path = Paths.get("D:/__TEMP/js-deploy/" + appName + ".zip");
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
