package com.jontoy.bo;

import java.util.List;

public class PlayerJSON 
{
	private String name;
	private String gamertag;
	private Long playerId;
	private int wins;
	private int differential;
	private int rank;
	
	public PlayerJSON(Player player, List<SessionEntry> session_entries)
	{
		this.name = player.getName();
		this.setPlayerId(player.getPlayerId());
		this.gamertag = player.getGamertag();
		
		this.wins = 0;
		
		for ( SessionEntry session_entry : session_entries )
		{
			this.wins += session_entry.getWins();
		}
		
		SessionEntry entry_for_ranks = SessionEntry.getMostRecentSessionEntry(session_entries);
		if ( entry_for_ranks == null )
		{
			this.differential = 0;
			this.rank = 0;
		}
		else
		{
			this.differential = entry_for_ranks.getStartRank() - entry_for_ranks.getEndRank();
			this.rank = entry_for_ranks.getEndRank();
		}
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGamertag() {
		return gamertag;
	}

	public void setGamertag(String gamertag) {
		this.gamertag = gamertag;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getDifferential() {
		return differential;
	}

	public void setDifferential(int differential) {
		this.differential = differential;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public Long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}
}
