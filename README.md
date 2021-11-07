###	Framework
*	Java Spring Boot
*	Maven
*	Eclipse
*	JDK 1.8


###	Getting Started
#####	Running Application
Clone project

	git clone https://github.com/dtruonggwhs/assesment_rest_service.git
	
	sudo git clone https://github.com/dtruonggwhs/assesment_rest_service.git

change directory to `/rest-service`<br>

	./mvn spring-boot:run
OR

	./mvnw spring-boot:run

MacOS

	sudo ./mvn spring-boot:run
OR

	sudo ./mvnw spring-boot:run

#### 	Change Environment
*	Navigate to `/rest-service/src/main/resources/application.properties` <br>
*	Change `spring.profiles.active=dev` to `dev` for development and `prod` for production environment

####		Using the API
API endpoint `http://localhost:8080/validate`

####		Note for application
There is only two providers available: `provider1,provider2`
<br>
Application will return `404 NOT Found` if any other providers is provided. 
<br>
DO NOT PASS any provider to utilize all available providers (`see request without providers`) for example.

#####	POST Request <br>
Request with Providers
		
	{
		"accountnum":"123456",
		"providers": ["provider1"]
	}
	
Response with Providers

	{
	    "response": [
	        {
	            "provider": "provider1",
	            "isValid": true
	        }
	    ]
	}
		
Request without Providers

	{
		"accountnum":"123456"
	}
	
Response without Providers

	{
	    "response": [
	        {
	            "provider": "provider1",
	            "isValid": true
	        },
	        {
	            "provider": "provider2",
	            "isValid": true
	        }
	    ]
	}
	
#####	Application YAMl Profiles
	spring:
	    config:
	        activate:
	            on-profile: dev
	name: dev-YAML
	environment: development
	providers:
	   provider1: https://provider1.com/v1/api/account/validate
	   provider2: https://provider2.com/v2/api/account/validate
	---
	spring:
	    config:
	        activate:
	            on-profile: prod
	name: prod-YAML
	environment: production
	providers:
	    provider1: https://provider1.com/prod/v1/api/account/validate
	    provider2: https://provider2.com/prod/v2/api/account/validate
	    
####	Application Overview
#####	RestServiceApplication.java
`/rest-service/src/main/java/com/example/restservice/RestServiceApplication.java`<br>

This class kickoff the application by calling main()
___

#####	ValidateController.java <br>
`/rest-service/src/main/java/com/example/restservice/ValidateController.java` <br>

This java class controls the routing for service end-point<br>
`ValidateController.java` handles the processing of the request body and initiating the corresponding methods to validate based on providers and account number.
	
___

#####	Validate.java <br>
`/rest-service/src/main/java/com/example/restservice/Validate.java` <br>

This class generate an object from the initial request object which is then use to validate each providers and accountNumber<br>
	
___

#####	ValidateResponse.java <br>
`/rest-service/src/main/java/com/example/restservice/ValidateResponse.java` <br>

This class generate Validate Response Object<br>

	{
        "provider": "provider1",
        "isValid": true
    }
	
___
	
#####	CreateResponseBody.java <br>
`/rest-service/src/main/java/com/example/restservice/CreateResponseBody.java` <br>

This class generate a response body to pass back<br>

	"response": [
	    ......
	]
	
___

#####	YAMLConfig.java
`/rest-service/src/main/java/com/example/restservice/YAMLConfig.java` <br>

This class prepare a YAML object from configuration for use within the application