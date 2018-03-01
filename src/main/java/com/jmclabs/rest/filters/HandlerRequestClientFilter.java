package com.jmclabs.rest.filters;

import java.net.URI;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.filter.ClientFilter;

@Provider
public class HandlerRequestClientFilter extends ClientFilter {
	
	static Logger log = LoggerFactory.getLogger(HandlerRequestClientFilter.class);
	boolean isDebugEnable = log.isDebugEnabled();

	@Override
	public ClientResponse handle(ClientRequest clientReqCtx) throws ClientHandlerException {
		 
		if (isDebugEnable) {
			log.debug("HandlerClientRequestFilter-[IN]-filer()");
		}
		
		// Adding some headers
		clientReqCtx.getHeaders().add("cli-Header-1", "value-1");
		clientReqCtx.getHeaders().add("cli-Header-2", "value-1");

		/**
		 * Print all headers that will go inside client request before get in to
		 * ContainerRequest (servlet-side)
		 */
		try {
			String method = clientReqCtx.getMethod();
			URI uri = clientReqCtx.getURI();
			MultivaluedMap<String, Object> headers = clientReqCtx.getHeaders();
			System.out.printf("Sending %s to: %s\n", method, uri);
			log.debug("Sending {} to: {}\n", method, uri);
			System.out.printf("Headers filtro:\n");
			log.debug("Headers:\n");
			for (String key : headers.keySet()) {
				System.out.printf(" %s: %s\n", key, headers.getFirst(key));
				log.debug(" {}: {}\n", key, headers.getFirst(key));
			}
		} catch (Exception e) {
			log.error("HandlerClientRequestFilter-[ERROR] {}", e);
			System.out.printf("Error - HandlerClientRequestFilter: %s", e);
		}
		return getNext().handle(clientReqCtx);
	}

	
	
	
	
//	@Override
//	public ClientResponse handle(ClientRequest clientRequest) throws ClientHandlerException {
//		System.out.println("HandlerRequestClientFilter - [IN]");
//		clientRequest.getHeaders().putSingle("Header-FOO", "BAR");
//		clientRequest.getHeaders().putSingle(HttpHeaders.CONTENT_TYPE, "application/json");
//        return getNext().handle(clientRequest);
//	}

}
