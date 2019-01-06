package org.anand.javabrains.messenger.errorhandling;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable exception) {
		// TODO Auto-generated method stub
		ErrorMessage err = new ErrorMessage();
		err.setErrorCode(Status.INTERNAL_SERVER_ERROR.getStatusCode());
		err.setErrorMessage(exception.getMessage());
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(err).build();
	}

}
