package com.knuthp.springdata;

import java.util.Map;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "full", types = { Customer.class })
public interface CustomerAllProjection {

	long getId();

	String getFirstName();

	String getLastName();

	Map<String, Membership> getMemberships();

}
