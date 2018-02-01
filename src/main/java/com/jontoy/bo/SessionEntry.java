package com.jontoy.bo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="sessionentries")
public class SessionEntry 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long entryId;
	
	@OneToOne
	@JoinColumn(name = "player_id")
	private Player player;
	
	@OneToOne
	@JoinColumn(name = "session_id")
	private Session session;
	
	private int startRank;
	private int endRank;
	private int wins;
	
	private String character1;
	private String character2;
	private String character3;
	
	public Long getEntryId() {
		return entryId;
	}
	public void setEntryId(Long entryId) {
		this.entryId = entryId;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}
	public int getStartRank() {
		return startRank;
	}
	public void setStartRank(int startRank) {
		this.startRank = startRank;
	}
	public int getEndRank() {
		return endRank;
	}
	public void setEndRank(int endRank) {
		this.endRank = endRank;
	}
	public int getWins() {
		return wins;
	}
	public void setWins(int wins) {
		this.wins = wins;
	}
	public String getCharacter1() {
		return character1;
	}
	public void setCharacter1(String character1) {
		this.character1 = character1;
	}
	public String getCharacter2() {
		return character2;
	}
	public void setCharacter2(String character2) {
		this.character2 = character2;
	}
	public String getCharacter3() {
		return character3;
	}
	public void setCharacter3(String character3) {
		this.character3 = character3;
	}	
	
	public static SessionEntry getMostRecentSessionEntry(List<SessionEntry> session_entries)
	{
		SessionEntry latest = null;
		
		for ( SessionEntry session_entry : session_entries )
		{
			if ( latest == null ) latest = session_entry;
			
			if ( session_entry.getSession().getTime() > latest.getSession().getTime() ) latest = session_entry;
		}
		
		return latest;
	}
	
	public static SessionEntry getSecondMostRecentSessionEntry(List<SessionEntry> session_entries)
	{
		SessionEntry latest = null;
		SessionEntry second_latest = null;
		
		for ( SessionEntry session_entry : session_entries )
		{
			if ( latest == null ) latest = session_entry;
			if ( second_latest == null ) second_latest = session_entry;
			
			if ( session_entry.getSession().getTime() > latest.getSession().getTime() ) latest = session_entry;
			if ( session_entry.getSession().getTime() > second_latest.getSession().getTime() && session_entry.getSession().getTime() < latest.getSession().getTime()) second_latest = session_entry;
		}
		
		return second_latest;
	}
}
