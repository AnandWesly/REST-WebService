package org.anand.javabrains.messenger.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.anand.javabrains.messenger.database.DataBase;
import org.anand.javabrains.messenger.model.Profile;

public class ProfileService {
	private static Map<String,Profile> profiles = DataBase.getProfiles();
	
	public ProfileService (){
		profiles.put("Anand", new Profile(1,"Anand","Wesly",new Date()));
	}
	
public List<Profile> getAllprofiles(){
		
		return new ArrayList<Profile>(profiles.values());
		
	}
	
	public Profile getProfile(String id){
		return profiles.get(id);
	}
	
	public Profile addProfile(Profile msg){
		msg.setId(profiles.size()+1);
		profiles.put(msg.getProfileName(), msg);
		return msg;
	}
	
	public Profile updateProfile(Profile msg){
		if(msg.getProfileName().isEmpty()){
			return null;
		}
		profiles.put(msg.getProfileName(), msg);
		return msg;
	}
	
	public Profile removeProfile(String id){
		return profiles.remove(id);
	}

}
