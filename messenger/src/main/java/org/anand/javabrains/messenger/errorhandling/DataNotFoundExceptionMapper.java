package org.anand.javabrains.messenger.errorhandling;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFountException> {

	@Override
	public Response toResponse(DataNotFountException exception) {
		ErrorMessage errmsg = new ErrorMessage();
		errmsg.setErrorCode(Status.NOT_FOUND.getStatusCode());
		errmsg.setErrorMessage(exception.getMessage());
	return Response.status(Status.NOT_FOUND).entity(errmsg).build();
	}

}
