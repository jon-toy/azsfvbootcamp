package com.jontoy.bo;

public class MatchTypeJSON 
{
	private String code;
	private String prettyName;
	
	public MatchTypeJSON(MatchType type)
	{
		this.code = type.name();
		this.prettyName = type.getPrettyPrint();
	}

	public String getCode() 
	{
		return code;
	}

	public void setCode(String code) 
	{
		this.code = code;
	}

	public String getPrettyName() 
	{
		return prettyName;
	}

	public void setPrettyName(String prettyName) 
	{
		this.prettyName = prettyName;
	}
}
