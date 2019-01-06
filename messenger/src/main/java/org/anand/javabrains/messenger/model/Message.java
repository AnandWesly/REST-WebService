package org.anand.javabrains.messenger.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Message {
	private long id;
	private String message;
	private String author;
	private Date created;
	private List<Links> links = new ArrayList<>();
	public Message(){
		
	}
	
	public List<Links> getLinkList() {
		return links;
	}

	public void setLinkList(List<Links> linkList) {
		this.links = linkList;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public Message(long id, String message, String author, Date created) {
		this.id = id;
		this.message = message;
		this.author = author;
		this.created = created;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public void addLinks(String link,String rel){
		Links newlink = new Links();
		newlink.setLink(link);
		newlink.setRel(rel);
		links.add(newlink);
	}
}
