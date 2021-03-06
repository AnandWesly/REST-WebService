package org.anand.javabrains.messenger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/inject")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
public class InjectResource {
	
	@GET
	@Path("Matrixparam")
	public String getMatricParam(@MatrixParam("param")String value,
									@HeaderParam("headerParam") String headerValue,
									@CookieParam("JSESSIONID") String jsessionId){
		return "Matrix:"+value+",Header param:"+headerValue+",CookieParam:"+jsessionId;
	}
	
	@GET
	@Path("context")	
	public String getParamUsingContext(@Context UriInfo uriInfo,@Context HttpHeaders httpHeader){
		return "UriInfo:"+ uriInfo.getPath().toString()+",HeaderCooki:"+httpHeader.getCookies().toString();
	}

}
