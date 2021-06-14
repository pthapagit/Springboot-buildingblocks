package com.stacksimplify.restservices.Hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HelloWorldController {
	
	//simple method
	//URI = /helloworld
	//GET
	
	//@RequestMapping(method = RequestMethod.GET, path = "/helloworld")
	//or
	@GetMapping("/helloworld1")
	
	
	public String helloWorld() {
		return "Hello World1";
	}
	
	@GetMapping("/helloworld-bean")
	public UserDetails helloWorldBean() {
		return new UserDetails("Prabesh", "Thapa", "Euless");
	}

}
