package com.jontoy.bo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="players")
public class Player 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long playerId;
	
	private String gamertag;
	private String name;

	@OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
	@ElementCollection(targetClass=CharacterEntry.class)
	private Set<CharacterEntry> characters;
	
	public Long getPlayerId() 
	{
		return playerId;
	}
	
	public void setPlayerId(Long player_id) {
		this.playerId = player_id;
	}
	
	public String getGamertag() {
		return gamertag;
	}
	
	public void setGamertag(String gamertag) {
		this.gamertag = gamertag;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
