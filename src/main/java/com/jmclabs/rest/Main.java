package com.jmclabs.rest;

import javax.ws.rs.core.MultivaluedMap;

import com.jmclabs.rest.client.MSFactoryImpl;
import com.jmclabs.rest.filters.BuilderResources;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource.Builder;

public class Main {

	public static void main(String[] args) {

		Client client = new MSFactoryImpl().getClient();

		callToJersey1(client);

		// callToJersey2();

	}

	private static void callToJersey1(Client client) {
		// int maxConn = (Integer)
		// client.getProperties().get(ClientProperties.CONNECT_TIMEOUT);

		// GET request to findBook resource with a query parameter
		// callToBookEntity(client);

		String getBookURL = "http://localhost:8080/JerseyHelloWorld/rest-points/show-on-screen/jose";
		Builder webResourceGet = client.resource(getBookURL).header("Header-FOO", "BAR");

		ClientResponse response = new BuilderResources().customBuilder(webResourceGet).get(ClientResponse.class);
		response.getHeaders().add("oijoijoijoj", "135464");
		
		System.out.println(response.getHeaders().get("oijoijoijoj"));
		
		// String response = webResourceGet.get(String.class);
//		ClientResponse response = bre.header("hbjhb", "321321").get(ClientResponse.class);

		MultivaluedMap<String, String> headers = response.getHeaders();
//		MultivaluedMap<String, String> headers = response.getMetadata();
		System.out.printf("Headers:\n");
		for (String key : headers.keySet()) {
			System.out.printf(" %s: %s\n", key, headers.getFirst(key));
			// System.out.println(response + "\nTimeout:" + maxConn);
			// System.out.println(client.getProperties().get(ClientConfig.PROPERTY_CONNECT_TIMEOUT));
			// System.out.println(webResourceGet.head().getHeaders());
		}

	}

	/*
	 * private static void callToJersey2() { try{
	 * 
	 * ClientConfig jerseyClientConfig = new ClientConfig();
	 * jerseyClientConfig.register(HandlerRequestClientFilter.class);
	 * javax.ws.rs.client.Client client2 =
	 * ClientBuilder.newClient(jerseyClientConfig);
	 * 
	 * WebTarget target1 = client2.target("http://www.google.es"); String res1 =
	 * target1.request().header("Client-Name",
	 * "jose-1").get().readEntity(String.class); System.out.println(res1);
	 * 
	 * System.out.println("Output from Server .... \n");
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } }
	 */

	// private static void callToBookEntity(Client client) {
	// String getBookURL =
	// "http://localhost:8080/jersey-client-json-example/services/book-service/find";
	// WebResource webResourceGet =
	// client.resource(getBookURL).queryParam("message", "jose");
	// ClientResponse response = webResourceGet.get(ClientResponse.class);
	//// BookEntity responseEntity = response.getEntity(BookEntity.class);
	//// if (response.getStatus() != 200) {
	//// throw new WebApplicationException();
	//// }
	//// System.out.println(responseEntity.toString());
	// }
}

// if(response.isEmpty()){
// System.out.println("Respuesta vacia");
// }else{
// System.out.println(response + "\nTimeout:" + maxConn);
// System.out.println();
// }
