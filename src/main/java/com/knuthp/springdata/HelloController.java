package com.knuthp.springdata;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/hello")
public class HelloController {
	private static final String TEMPLATE = "Hello, %s!";

	@RequestMapping("/")
	@ResponseBody
	public HttpEntity<Hello> hello(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
		Hello hello = new Hello(String.format(TEMPLATE, name));
		hello.add(linkTo(methodOn(HelloController.class).hello(name)).withSelfRel());
		return new ResponseEntity<>(hello, HttpStatus.OK);
	}

	@RequestMapping("/error")
	public HttpEntity<Hello> error() {
		throw new IllegalStateException("Not correct state");
	}

	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<String> handleException(IllegalStateException ex) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
	}

	@RequestMapping("/error2")
	public HttpEntity<Hello> error2() {
		throw new IllegalArgumentException("Not correct argument");
	}

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.EXPECTATION_FAILED)

	public String handleException2(IllegalArgumentException ex) {
		return ex.getMessage();
	}

}