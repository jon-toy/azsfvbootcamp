package com.jontoy.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jontoy.bo.Character;
import com.jontoy.bo.CharacterJSON;
import com.jontoy.bo.Match;
import com.jontoy.bo.MatchJSON;
import com.jontoy.bo.MatchType;
import com.jontoy.bo.MatchTypeJSON;
import com.jontoy.bo.Player;
import com.jontoy.bo.Session;
import com.jontoy.bo.SessionEntry;
import com.jontoy.repos.MatchRepo;
import com.jontoy.repos.PlayerRepo;
import com.jontoy.repos.SessionRepo;

@Controller
@RequestMapping("/matches")
public class MatchController 
{
	@Autowired
	private MatchRepo repo;
	
	@Autowired
	private PlayerRepo player_repo;
	
	@Autowired
	private SessionRepo session_repo;	
	
	@RequestMapping("/list")
	@ResponseBody
	public List<Match> listOfMatches()
	{
		List<Match> ret = new ArrayList<>();
		
		for ( Match match : repo.findAll() )
		{
			ret.add(match);
		}
		
		return ret;
	}
	
	@RequestMapping("/{id}")
	@ResponseBody
	public Match getMatch(@PathVariable long id)
	{
		return repo.findOne(id);
	}
	
	@RequestMapping("/add_match")
	@ResponseBody
	public String addMatch(@RequestParam(value="player1") long player1, @RequestParam(value="player2") long player2, @RequestParam(value="winner") long winner,  
			@RequestParam(value="chara1") String character1, @RequestParam(value="chara2") String character2,
			@RequestParam(value="score1") int score1, @RequestParam(value="score2") int score2,
			@RequestParam(value="type") String type, @RequestParam(value="url", defaultValue="") String url, @RequestParam(value="title", defaultValue="") String title, 
			@RequestParam(value="description", defaultValue="") String description,  @RequestParam(value="session") long session)
	{
		Match match = new Match();
		
		Player p1 = player_repo.findOne(player1);
		Player p2 = player_repo.findOne(player2);
		Player w = player_repo.findOne(winner);
		
		if ( p1 == null || p2 == null || w == null ) return "Invalid Players";
		
		match.setPlayer1(p1);
		match.setPlayer2(p2);
		match.setWinner(w);
		
		match.setCharacter1(character1);
		match.setCharacter2(character2);
		
		match.setScore1(score1);
		match.setScore2(score2);
		match.setType(MatchType.fromString(type, MatchType.UNKNOWN));
		match.setUrl(url);
		match.setTitle(title);
		match.setDescription(description);
		
		Session s = session_repo.findOne(session);
		
		if ( s == null ) return "Invalid Session";
		
		match.setSession(s);
		
		repo.save(match);
		
		return "Save Successful";
	}
	
	@RequestMapping("/remove_match")
	@ResponseBody
	public String removeMatch(@RequestParam(value="match") long match_id)
	{
		repo.delete(match_id);
		
		return "Match Removed";
	}
	
	@RequestMapping("/list_types")
	@ResponseBody
	public List<MatchTypeJSON> listOfTypes()
	{
		List<MatchTypeJSON> ret = new ArrayList<>();
		
		for ( MatchType type : MatchType.values() )
		{
			ret.add(new MatchTypeJSON(type));
		}
		
		return ret;
	}
	
	@RequestMapping("/list_by_session")
	@ResponseBody
	public List<Match> findBySessionId(@RequestParam Long session_id)
	{
		List<Match> ret = new ArrayList<>();
		
		Session session = session_repo.findOne(session_id);
		
		for ( Match match : repo.findMatchBySession(session) )
		{
			ret.add(match);
		}
		
		return ret;
	}
	
	@RequestMapping("/list_by_player")
	@ResponseBody
	public List<Match> findByPlayerId(@RequestParam Long player_id)
	{
		List<Match> ret = new ArrayList<>();
		
		Player player = player_repo.findOne(player_id);
		
		for ( Match match : repo.findMatchByPlayer1(player) )
		{
			ret.add(match);
		}
		
		for ( Match match : repo.findMatchByPlayer2(player) )
		{
			ret.add(match);
		}
		
		return ret;
	}
	
	@PostConstruct
	public void init()
	{
		
	}
}
