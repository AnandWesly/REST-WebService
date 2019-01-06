package org.anand.javabrains.messenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.anand.javabrains.messenger.model.Profile;
import org.anand.javabrains.messenger.service.ProfileService;
import org.anand.javabrains.messenger.service.ProfileService;

@Path("/profiles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProfileResource {
ProfileService messagSrv = new ProfileService();
	
	@GET
	public List<Profile> getProfiles(){
		return messagSrv.getAllprofiles();
	}
	
	@GET
	@Path("/{messageId}")
	public Profile getProfile(@PathParam("messageId")String messageId){
		return messagSrv.getProfile(messageId);
	}
	
	@POST
	public Profile addProfile(Profile message){
		return messagSrv.addProfile(message);
		
	}
	
	@DELETE
	@Path("/{messageId}")
	public Profile deleteProfile(@PathParam("messageId")String id){
		return messagSrv.removeProfile(id);
	}
	
	@PUT
	public Profile updateProfile(Profile message){
		return messagSrv.updateProfile(message);
	}
}
