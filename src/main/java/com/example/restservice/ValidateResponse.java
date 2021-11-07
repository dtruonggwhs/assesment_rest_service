package com.example.restservice;

/**
 * @author DucTruong
 *	This class generate Validate Response Object
 */
public class ValidateResponse {
	private String provider;
	private boolean isValid;
	
	public ValidateResponse(String p, boolean s) {
		this.provider = p;
		this.isValid = s;
	}
	
	public String getProvider() {
		return provider;
	}

	public boolean getIsValid() {
		return isValid;
	}
}
