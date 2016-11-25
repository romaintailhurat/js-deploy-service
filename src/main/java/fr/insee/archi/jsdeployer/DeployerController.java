package fr.insee.archi.jsdeployer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.insee.archi.jsdeployer.repository.AppRepository;

@RestController
@RequestMapping("/deploy")
public class DeployerController {
	
	private static Log log = LogFactory.getLog(DeployerController.class);
	
	@Autowired
	private AppRepository repo;
	
	@GetMapping()
	public String info() {
		return "deployment API";
	}
	
	@PostMapping(value= "/{appName}", consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public DeployResponse deploy(
			@RequestBody byte[] body,
			@PathVariable String appName
			) throws IOException {
		log.info(String.format("Deploying %s", appName));
		log.info(String.format("Size is : %s ko", Integer.toString(body.length/1024)));
		repo.storeApp(appName, body);
		return new DeployResponse(String.format("%s deployed !", appName));
	}

}
