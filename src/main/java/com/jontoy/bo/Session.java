package com.jontoy.bo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sessions")
public class Session 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long sessionId;
	
	private String name;
	private String location;
	
	private Long time;
	
	public Long getSessionId() 
	{
		return sessionId;
	}
	
	public void setSessionId(Long sessionId) 
	{
		this.sessionId = sessionId;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public String getLocation() 
	{
		return location;
	}
	
	public void setLocation(String location) 
	{
		this.location = location;
	}
	
	public Long getTime() 
	{
		return time;
	}
	
	public void setTime(Long time) 
	{
		this.time = time;
	}
}