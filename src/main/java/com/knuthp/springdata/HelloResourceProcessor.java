package com.knuthp.springdata;

import org.springframework.data.rest.webmvc.RepositoryLinksResource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class HelloResourceProcessor implements ResourceProcessor<RepositoryLinksResource> {

	@Override
	public RepositoryLinksResource process(RepositoryLinksResource resource) {
		resource.add(ControllerLinkBuilder.linkTo(HelloController.class).withRel("hello"));
		return resource;
	}
}