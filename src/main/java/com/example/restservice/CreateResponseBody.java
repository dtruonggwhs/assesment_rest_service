package com.example.restservice;

/**
 * @author DucTruong
 * This class generate the response body to pass back
 */
public class CreateResponseBody {
	private ValidateResponse[] response;
	
	public CreateResponseBody(ValidateResponse[] validateRespObj) {
		this.response = validateRespObj;
	}
	
	public ValidateResponse[] getResponse() {
		return response;
	}
}
