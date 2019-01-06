package org.anand.javabrains.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.anand.javabrains.messenger.database.DataBase;
import org.anand.javabrains.messenger.errorhandling.ErrorMessage;
import org.anand.javabrains.messenger.model.Message;

public class MessageService {
	private static Map<Long,Message> messages = DataBase.getMessages();

	public MessageService(){
		Message m1 = new Message(1, "Hello workld", "Anand", new Date());
		Message m2 = new Message(2, "Hello Wesly", "Wesly", new Date());
		messages.put(m1.getId(), m1);
		messages.put(m2.getId(), m2);

	}
	public List<Message> getAllMessages(){

		return new ArrayList<Message>(messages.values());

	}
	public List<Message> getAllMessagesWithYear(int year){
		List<Message> listyearMessages = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		for(Message msg : messages.values()){
			cal.setTime(msg.getCreated());
			if(cal.get(Calendar.YEAR) == year){
				listyearMessages.add(msg);
			}
		}
		
		return listyearMessages;
	}
	public List<Message> getAllMessagesPaginate(int start,int size){
		List<Message> listPaginate =new ArrayList<Message>(messages.values());
		return listPaginate.subList(start, start+size);
	}
	public Message getMessage(long id){
		if(messages.get(id) == null){
		ErrorMessage errmsg = new ErrorMessage();
		errmsg.setErrorCode(Status.NOT_FOUND.getStatusCode());
		errmsg.setErrorMessage("Message id not found");
	Response response = Response.status(Status.NOT_FOUND).entity(errmsg).build();
	 throw new WebApplicationException(response);
		}
		return messages.get(id);
	}

	public Message addMessage(Message msg){
		msg.setId(messages.size()+1);
		messages.put(msg.getId(), msg);
		return msg;
	}

	public Message updateMessage(Message msg){
		if(msg.getId() <= 0){
			return null;
		}
		messages.put(msg.getId(), msg);
		return msg;
	}

	public Message removeMessage(long id){
		return messages.remove(id);
	}
}
