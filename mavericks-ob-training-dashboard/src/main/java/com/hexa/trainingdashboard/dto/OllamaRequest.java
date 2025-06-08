package com.hexa.trainingdashboard.dto;

public class OllamaRequest {
	
	private String model;
    private String prompt;
    private boolean stream;
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getPrompt() {
		return prompt;
	}
	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}
	public boolean isStream() {
		return stream;
	}
	public void setStream(boolean stream) {
		this.stream = stream;
	}
	public OllamaRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}
