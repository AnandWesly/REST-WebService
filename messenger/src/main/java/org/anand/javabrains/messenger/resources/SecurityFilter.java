package org.anand.javabrains.messenger.resources;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.internal.util.Base64;


@Provider
public class SecurityFilter implements ContainerRequestFilter{

	private static final String AUTHORIZATION_HEADER ="Authorization";
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER);
		
		if(authHeader!= null && authHeader.size() >0){
			String authToken = authHeader.get(0);
			authToken= authToken.replaceFirst("Basic ", "");
			System.out.println(authToken);
			String decodeString = Base64.decodeAsString(authToken);
			System.out.println(decodeString);
			StringTokenizer stinToken = new StringTokenizer(decodeString, ":");
			String userName = stinToken.nextToken();
			String password = stinToken.nextToken();
			if(userName.equals("user") && password.equals("password")){
				return;
			}
		}
		
		Response response =Response.status(Status.UNAUTHORIZED).entity("Not authorized to use the resource").build();
		requestContext.abortWith(response);
	}
}
