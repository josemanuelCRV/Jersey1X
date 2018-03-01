package com.jmclabs.rest.client;

import javax.annotation.Resource;
import javax.ws.rs.core.MultivaluedMap;

import com.jmclabs.rest.filters.HandlerRequestClientFilter;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.ClientFilter;
import com.sun.jersey.api.json.JSONConfiguration;

public class MSFactoryImpl implements MSFactory {

	private static Client jerseyClient;
	private static ClientConfig config = new DefaultClientConfig();

	@Resource
	HandlerRequestClientFilter filter;

	@Override
	public Client getClient() {
		withCustomConfigClient();
		return jerseyClient;
	}

	private void withCustomConfigClient() {
		config.getProperties().put(ClientConfig.PROPERTY_CONNECT_TIMEOUT, 1000);
		config.getProperties().put(ClientConfig.PROPERTY_READ_TIMEOUT, 2000);
		config.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		// config.getProperties().put(ClientConfig.PROPERTY_THREADPOOL_SIZE,
		// 40);
		// jerseyClient.addFilter(filter);

		jerseyClient = Client.create(config);
		jerseyClient.addFilter(new MyClientFilter());
		jerseyClient.addFilter(new HandlerRequestClientFilter());

		// ContainerRequestFilter crq = new HandlerRequestClientFilter();
		// ClientFilter customFilter;
		// jerseyClient.addFilter(customFilter);
	}

	class MyClientFilter extends ClientFilter {

		@Override
		public ClientResponse handle(ClientRequest cr) throws ClientHandlerException {
			System.out.println("called");
			cr.getHeaders().add("YYYYYYY", "00000000");

			MultivaluedMap<String, Object> headers = cr.getHeaders();
			// MultivaluedMap<String, String> headers = response.getMetadata();
			System.out.printf("Headers:\n");
			for (String key : headers.keySet()) {
				System.out.printf(" %s: %s\n", key, headers.getFirst(key));
			}
			return getNext().handle(cr);
		}
	}

	// class HandlerRequestClientFilter extends ClientFilter {
	//
	// @Override
	// public ClientResponse handle(ClientRequest clientRequest) throws
	// ClientHandlerException {
	// System.out.println("HandlerRequestClientFilter - [IN]");
	// clientRequest.getHeaders().add("Header-FOO", "BAR");
	// clientRequest.getHeaders().putSingle(HttpHeaders.CONTENT_TYPE,
	// "application/json");
	// return getNext().handle(clientRequest);
	// }
	//
	// }

}

// https://jersey.github.io/documentation/1.19.1/client-api.html#d4e704
// https://docs.mulesoft.com/anypoint-connector-devkit/v/3.4/connector-to-restful-service-with-jersey-client-example
