package fr.insee.archi.jsdeployer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apps")
public class AppsController {
	
	@GetMapping("/{appName}")
	public String getApp(@PathVariable String appName) {
		return String.format("You are looking for application %s", appName);
	}

}
