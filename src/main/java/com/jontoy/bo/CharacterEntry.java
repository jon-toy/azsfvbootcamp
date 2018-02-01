package com.jontoy.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="characters")
public class CharacterEntry 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long entryId;
	
	@ManyToOne
	@JoinColumn(name = "player_id")
	private Player player;
	
	@Column(name="character_string")
	private String character;
	
	public Long getEntryId() 
	{
		return entryId;
	}
	
	public void setEntryId(Long entryId) 
	{
		this.entryId = entryId;
	}
	
	public Player getPlayer() 
	{
		return player;
	}
	
	public void setPlayer(Player player) 
	{
		this.player = player;
	}
	
	public String getCharacter() 
	{
		return character;
	}
	
	public void setCharacter(String character) 
	{
		this.character = character;
	}
}
