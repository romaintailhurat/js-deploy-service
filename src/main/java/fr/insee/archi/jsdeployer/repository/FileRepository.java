package fr.insee.archi.jsdeployer.repository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import fr.insee.archi.jsdeployer.Constants;
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
		recordContent(appName, content);
		saveContent(appName, getFileFromBytes(content));	
		return true; // TODO handle wrong cases
	}

	private void recordContent(String appName, byte[] content) {
		String historyPath = repository + Constants.HISTORY_REPO;
		log.info("Recording raw content to " + historyPath);
		makeDirIfNeeded(historyPath);
		Path path = Paths.get(historyPath + appName + ".zip");
		try {
			Files.write(path, content);			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void saveContent(String appName, File f) {
		ZipFile z;
		String globalAppsPath = repository + Constants.APPS_REPO ; //File.separator
		makeDirIfNeeded(globalAppsPath);
		String appPath = globalAppsPath + appName + File.separator;
		log.info("Saving content to " + appPath);
		makeDirIfNeeded(appPath);
		try {
			z = new ZipFile(f);
			for (Enumeration<? extends ZipEntry> entries = z.entries(); entries.hasMoreElements();) {
				ZipEntry ze = entries.nextElement();
				log.info("Saving zip entries " + ze.getName());
				Path targetFilePath = Paths.get(appPath + ze.getName());
				if (ze.isDirectory()) {
					log.info("Zip entry is a directory");
					makeDirIfNeeded(targetFilePath.toString());
				} else {
					log.info("Zip entry is a file");
					//Files.createFile(targetFilePath);
					InputStream is = z.getInputStream(ze);
					Files.copy(is, targetFilePath);
				}				
			}				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void makeDirIfNeeded(String appPath) {
		log.info("Creating dir " + appPath);
		File destDir = new File(appPath);
        if (!destDir.exists()) {
            destDir.mkdir();
        }
	}

	private File getFileFromBytes(byte[] content) {
		File f = new File("temp.zip");
		try {
			FileUtils.writeByteArrayToFile(f, content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return f;
	}

}
