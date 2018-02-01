package com.jontoy.bo;

public class CharacterJSON 
{
	private String code;
	private String prettyName;
	
	public CharacterJSON(Character chara)
	{
		this.code = chara.name();
		this.prettyName = chara.getPrettyName();
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
