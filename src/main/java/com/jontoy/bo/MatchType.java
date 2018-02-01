package com.jontoy.bo;

public enum MatchType 
{
	PLACEMENT("Placement Match"),
	MONEYMATCH("Money Match"),
	EXHIBITION("Exhibition"),
	UNKNOWN("Unknown Match Type")
	;
	
	private String pretty_print;
	
	private MatchType(String pretty_print)
	{
		this.pretty_print = pretty_print;
	}
	
	public String getPrettyPrint()
	{
		return this.pretty_print;
	}
	
	public static MatchType fromString(String in, MatchType default_value)
	{
		if (in != null) 
		{
			for ( MatchType b : MatchType.values() ) 
			{
			    if ( in.equalsIgnoreCase(b.name()) ) 
			    {
			    	return b;
			    }
			}
		}
		return default_value;
	}
}
