package com.jontoy.bo;


public enum Character 
{
	ALX("Alex"),
	BLR("Balrog (Boxer)"),
	BRD("Birdie"),
	BSN("M. Bison (Dictator)"),
	CMY("Cammy"),
	CNL("Chun-Li"),
	SIM("Dhalsim"),
	FNG("Fang"),
	GUI("Guile"),
	JUR("Juri"),
	LAU("Laura"),
	IBK("Ibuki"),
	KEN("Ken"),
	KRN("Karin"),
	NCL("Necalli"),
	NSH("Nash"),
	RMK("R. Mika"),
	RSD("Rashid"),
	RYU("Ryu"),
	URN("Urien"),
	VEG("Vega (Claw"),
	ZNG("Zangief"),
	
	UNK("Unknown")
	;
	
	private String pretty_name;
	
	private Character(String pretty_name)
	{
		this.pretty_name = pretty_name;
	}
	
	public String getPrettyName()
	{
		return this.pretty_name;
	}
	
	public static Character fromString(String in)
	{
		return fromString(in, UNK);
	}
	
	public static Character fromString(String in, Character default_value)
	{
		if (in != null) 
		{
			for ( Character b : Character.values() ) 
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
