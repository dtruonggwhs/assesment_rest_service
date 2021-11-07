package com.example.restservice;

import java.util.HashMap;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class YAMLConfig {
 
    private String environment;
    private HashMap<String, String> providers;
    
    public HashMap<String, String> getProviders(){
    	return providers;
    }
    
    public void setProviders(HashMap<String, String> p) {
    	this.providers = p;
    }
    
    public void setEnvironment(String env) {
    	this.environment = env;
    }
    
    public String getEnvironment() {
    	return environment;
    }

}