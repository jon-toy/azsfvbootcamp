package com.jontoy.bo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="matches")
public class Match 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long matchId;
	
	@ManyToOne
	@JoinColumn(name = "p1_id", referencedColumnName="playerId")
	private Player player1;
	
	@ManyToOne
	@JoinColumn(name = "p2_id", referencedColumnName="playerId")
	private Player player2;
	
	@ManyToOne
	@JoinColumn(name = "w_id", referencedColumnName="playerId")
	private Player winner;
	
	private String character1;
	private String character2;
	
	private Integer score1;
	private Integer score2;
	
	private MatchType type;
	
	private String url;
	private String title;
	private String description;
	
	@OneToOne
	@JoinColumn(name = "session_id")
	private Session session;

	public Long getMatchId() {
		return matchId;
	}

	public void setMatchId(Long matchId) {
		this.matchId = matchId;
	}

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public Player getWinner() {
		return winner;
	}

	public void setWinner(Player winner) {
		this.winner = winner;
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

	public Integer getScore1() {
		return score1;
	}

	public void setScore1(Integer score1) {
		this.score1 = score1;
	}

	public Integer getScore2() {
		return score2;
	}

	public void setScore2(Integer score2) {
		this.score2 = score2;
	}

	public MatchType getType() {
		return type;
	}

	public void setType(MatchType type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
