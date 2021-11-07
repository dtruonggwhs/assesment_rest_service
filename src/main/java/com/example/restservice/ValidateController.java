package com.example.restservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;


/**
 * @author DucTruong
 * This class is use to capture the initial REST request based on mapping, here we're using POST as opposed to GET 
 * so we can pass in request through request body
 */
@RestController
public class ValidateController {
	@Autowired
    private YAMLConfig appConfig;
	private String accountNum;
	private String[] providers;
	Logger logger = LoggerFactory.getLogger(ValidateController.class);
	

	
	@PostMapping("/validate")
	@ResponseBody
	public CreateResponseBody validate(@RequestBody Validate validateObj) {
		String provider;
		ValidateResponse[] response; 
		CreateResponseBody finalResponse;
		this.accountNum = validateObj.getAccountNumber();
		
	/**	This if/else statement handles if the user request contain providers, if not we will make requests
	 	to all available providers
	*/
		if(validateObj.getProvidersLength()>0){
			response = new ValidateResponse[validateObj.getProvidersLength()];
			this.providers = validateObj.getProviders();
		}
		else{
			response = new ValidateResponse[appConfig.getProviders().size()];
			this.providers = appConfig.getProviders().keySet().toArray(new String[appConfig.getProviders().size()]);
		}
		for (int i=0; i < this.providers.length; i++) {
            provider = this.providers[i];
            response[i] = validateAccount(provider, this.accountNum);
        }
		finalResponse = new CreateResponseBody(response);
		return finalResponse;
	}
	
	/**
	 * This method make a request to each provided providers to get back if an account is valid.
	 * @param providerToValidate
	 * @param accountnum
	 * @return
	 */
	public ValidateResponse validateAccount(String providerToValidate, String accountnum) {
		String providerURL;
		RestTemplate restTemplate = new RestTemplate();
		((SimpleClientHttpRequestFactory)restTemplate.getRequestFactory()).setConnectTimeout(1000);
		((SimpleClientHttpRequestFactory)restTemplate.getRequestFactory()).setReadTimeout(1000);
		try {
			providerURL = appConfig.getProviders().get(providerToValidate);
		}
		catch(IllegalArgumentException e) {
			providerURL = null;
		}
		//	here we throw exception if the provider is not found in configuration
		if(providerURL == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Provider not found");
		}
		else {
			try {
				ResponseEntity<String> providerResponse = restTemplate.getForEntity(providerURL + "?accountNum="+accountnum, String.class);
			}
			catch( ResourceAccessException e ){
				logger.debug("socket timed out: " + providerURL + " " + e);
			}
			//	We are always returning isValid:true here because the providers is not available to be query from
			return new ValidateResponse(providerToValidate,true);
		}
	}
}