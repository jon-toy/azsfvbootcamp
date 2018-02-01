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

import com.jontoy.bo.Player;
import com.jontoy.bo.Session;
import com.jontoy.bo.SessionEntry;
import com.jontoy.repos.PlayerRepo;
import com.jontoy.repos.SessionEntryRepo;
import com.jontoy.repos.SessionRepo;

@Controller
@RequestMapping("/session_entries")
public class SessionEntryController 
{
	@Autowired
	private SessionEntryRepo repo;
	
	@Autowired
	private PlayerRepo player_repo;
	
	@Autowired
	private SessionRepo session_repo;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<SessionEntry> listOfSessionEntries()
	{
		List<SessionEntry> ret = new ArrayList<>();
		
		for ( SessionEntry entry : repo.findAll() )
		{
			ret.add(entry);
		}
		
		return ret;
	}
	
	@RequestMapping("/list_by_player")
	@ResponseBody
	public List<SessionEntry> findByPlayerId(@RequestParam Long player_id)
	{
		List<SessionEntry> ret = new ArrayList<>();
		
		Player player = player_repo.findOne(player_id);
		
		for ( SessionEntry entry : repo.findSessionEnryByPlayer(player) )
		{
			ret.add(entry);
		}
		
		return ret;
	}
	
	@RequestMapping("/list_by_session")
	@ResponseBody
	public List<SessionEntry> findBySessionId(@RequestParam Long session_id)
	{
		List<SessionEntry> ret = new ArrayList<>();
		
		Session session = session_repo.findOne(session_id);
		
		for ( SessionEntry entry : repo.findSessionEnryBySession(session) )
		{
			ret.add(entry);
		}
		
		return ret;
	}
	
	@RequestMapping("/{id}")
	@ResponseBody
	public SessionEntry getSessionEntry(@PathVariable Long id)
	{
		return repo.findOne(id);
	}
	
	@RequestMapping("/add_session_entry")
	@ResponseBody
	public String addSession(@RequestParam(value="session_id") long session_id, @RequestParam(value="player_id") long player_id, 
			@RequestParam(value="start_rank") int start_rank, @RequestParam(value="end_rank") int end_rank, @RequestParam(value="wins") int wins, 
			@RequestParam(value="chara1") String character1, @RequestParam(value="chara2", defaultValue="UNK") String character2, 
			@RequestParam(value="chara3", defaultValue="UNK") String character3)
	{
		SessionEntry e1 = new SessionEntry();
		
		Session session = session_repo.findOne(session_id);
		Player player = player_repo.findOne(player_id);
		
		e1.setSession(session);
		e1.setPlayer(player);
		e1.setStartRank(start_rank);
		e1.setEndRank(end_rank);
		e1.setCharacter1(character1);
		if ( !character2.equals("UNK") ) e1.setCharacter2(character2);
		if ( !character3.equals("UNK") ) e1.setCharacter3(character3);		
		e1.setWins(wins);
		
		repo.save(e1);
		
		return "Save Successful";
	}
	
	@RequestMapping("/remove_session_entry")
	@ResponseBody
	public String removePlayer(@RequestParam(value="session_entry") long session_id)
	{
		SessionEntry entry = repo.findOne(session_id);
		
		if ( entry == null ) return "Invalid Session Entry";
		
		repo.delete(entry);
		
		return "Session Entry Removed";
	}
	
	@RequestMapping("/get_latest_entry_for_player")
	@ResponseBody
	public SessionEntry getLatestSessionEntry(@RequestParam(value="player_id") long player_id)
	{
		Player player = player_repo.findOne(player_id);
		
		SessionEntry ret = null;
		
		for ( SessionEntry entry : repo.findSessionEnryByPlayer(player) )
		{
			if ( ret == null ) ret = entry;
			if ( entry.getSession().getTime() > ret.getSession().getTime() ) ret = entry;
		}
		
		return ret;
	}
	
	@PostConstruct
	public void init()
	{
		SessionEntry e1 = new SessionEntry();
		
		Session session = session_repo.findOne((long)1);
		Player player = player_repo.findOne((long)1);
		
		e1.setEntryId((long)1);
		e1.setSession(session);
		e1.setPlayer(player);
		e1.setStartRank(0);
		e1.setEndRank(0);
		e1.setCharacter1("KRN");		
		e1.setStartRank(5);
		
		//repo.save(e1);
	}
}
