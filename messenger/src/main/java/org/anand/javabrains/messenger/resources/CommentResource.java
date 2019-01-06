package org.anand.javabrains.messenger.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/")
public class CommentResource {

	@GET
	public String getSub(){
		return "subresource";
	}
	
	@GET
	@Path("/{commentId}")
	public String getCommentId(@PathParam("commentId") String commentId,@PathParam("messageId") String messageId){
		return "CommentId:"+commentId+", Msg Id"+ messageId;
	}
}
