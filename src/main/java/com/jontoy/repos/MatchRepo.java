package com.jontoy.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jontoy.bo.Match;
import com.jontoy.bo.Player;
import com.jontoy.bo.Session;

@Repository
public interface MatchRepo extends CrudRepository<Match, Long>
{
	public List<Match> findMatchByPlayer1(Player player1);
	public List<Match> findMatchByPlayer2(Player player2);
	public List<Match> findMatchByWinner(Player winner);
	public List<Match> findMatchBySession(Session session);
}
