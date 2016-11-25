package fr.insee.archi.jsdeployer;

import java.time.LocalDateTime;

public class DeployResponse {
	
	private String message;
	private String timestamp;
	
	public DeployResponse(String message) {
		this.setMessage(message);
		this.setTimestamp(LocalDateTime.now().toString());
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
}
