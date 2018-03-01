package com.jmclabs.rest.filters;

import com.sun.jersey.api.client.WebResource.Builder;

public class BuilderResources {

	public BuilderResources() {
	}
	
	public Builder customBuilder(Builder originBuilder) {
		Builder customBuilder = originBuilder;
		customBuilder.header("AOOOOOOOO", "hhhhhhhhhh");

		return customBuilder;

	}
}
