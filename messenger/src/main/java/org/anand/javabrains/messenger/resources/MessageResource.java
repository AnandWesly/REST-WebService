package org.anand.javabrains.messenger.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import org.anand.javabrains.messenger.errorhandling.DataNotFountException;
import org.anand.javabrains.messenger.model.Message;
import org.anand.javabrains.messenger.service.MessageService;

@Path("/messages")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MessageResource {

	MessageService messagSrv = new MessageService();
	
	@GET
	public List<Message> getMessages(@BeanParam FilterBeanResource filterbean){
		if(filterbean.getYear() >0){
			return messagSrv.getAllMessagesWithYear(filterbean.getYear());
		}
		if(filterbean.getStart() >=0 && filterbean.getSize() >0){
			return messagSrv.getAllMessagesPaginate(filterbean.getStart(), filterbean.getSize());
		}
		return messagSrv.getAllMessages();
	}
	
	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId")long messageId,@Context UriInfo uriInfo){
		Message msg = messagSrv.getMessage(messageId);
		if(msg == null){
			
			throw new DataNotFountException("Message is not found"+messageId);
		}
		msg.addLinks(uriInfo.getAbsolutePathBuilder().toString(), "self");
		msg.addLinks(getUriForProfile(uriInfo,msg), "profile");
		return msg;
	}
	
	private String getUriForProfile(UriInfo uriInfo, Message msg) {

		return uriInfo.getBaseUriBuilder().path(ProfileResource.class).path(msg.getAuthor()).build().toString();
	}

	@POST
	public Response addMessage(Message message,@Context UriInfo uriInfo) throws URISyntaxException{
		Message messageOut = messagSrv.addMessage(message);
		return Response.created(uriInfo.getAbsolutePathBuilder().path(String.valueOf(messageOut.getId())).build()).entity(messageOut).build();
		
	}
	
	@DELETE
	@Path("/{messageId}")
	public Message deleteMessage(@PathParam("messageId")long id){
		return messagSrv.removeMessage(id);
	}
	
	@PUT
	public Message updateMessage(Message message){
		return messagSrv.updateMessage(message);
	}
	
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource(){
		return new CommentResource();
	}
}
