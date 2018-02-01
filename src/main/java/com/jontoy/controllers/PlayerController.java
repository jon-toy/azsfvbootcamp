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

import com.jontoy.bo.CharacterEntry;
import com.jontoy.bo.Player;
import com.jontoy.bo.PlayerJSON;
import com.jontoy.bo.Session;
import com.jontoy.bo.SessionEntry;
import com.jontoy.repos.CharacterEntryRepo;
import com.jontoy.repos.PlayerRepo;
import com.jontoy.repos.SessionEntryRepo;
import com.jontoy.repos.SessionRepo;

@Controller
@RequestMapping("/players")
public class PlayerController 
{
	@Autowired
	private PlayerRepo repo;
	
	@Autowired
	private CharacterEntryRepo character_entry_repo;
	
	@Autowired
	private SessionEntryRepo session_entry_repo;
	
	@Autowired
	private SessionRepo session_repo;	
	
	@RequestMapping("/list")
	@ResponseBody
	public List<PlayerJSON> listOfPlayers()
	{
		List<PlayerJSON> ret = new ArrayList<>();
		
		for ( Player player : repo.findAll() )
		{
			List<SessionEntry> session_entries = session_entry_repo.findSessionEnryByPlayer(player);
			ret.add(new PlayerJSON(player, session_entries));
		}
		
		return ret;
	}
	
	@RequestMapping("/{id}")
	@ResponseBody
	public PlayerJSON getPlayer(@PathVariable long id)
	{
		Player player = repo.findOne(id);
		List<SessionEntry> session_entries = session_entry_repo.findSessionEnryByPlayer(player);
		
		return new PlayerJSON(player, session_entries);
	}
	
	@RequestMapping("/add_player")
	@ResponseBody
	public PlayerJSON addPlayer(@RequestParam(value="name") String name, @RequestParam(value="gamertag") String gamertag, 
			@RequestParam(value="chara1") String character1, @RequestParam(value="chara2", defaultValue="UNK") String character2, 
			@RequestParam(value="chara3", defaultValue="UNK") String character3, @RequestParam(value="rank") int rank)
	{
		Player p1 = new Player();
		p1.setGamertag(gamertag);
		p1.setName(name);
		repo.save(p1);
		
		List<String> characters = new ArrayList<String>();
		characters.add(character1);
		
		// Add an initial session entry
		Session session = session_repo.findOne(SessionController.INITIAL_SESSION_ID);
		SessionEntry initial_entry = new SessionEntry();
		initial_entry.setSession(session);
		initial_entry.setPlayer(p1);
		initial_entry.setStartRank(rank);
		initial_entry.setEndRank(rank);
		initial_entry.setCharacter1(character1);
		if ( !character2.equals("UNK") ) 
		{
			initial_entry.setCharacter2(character2);
			characters.add(character2);
		}
		if ( !character3.equals("UNK") )
		{
			initial_entry.setCharacter3(character3);
			characters.add(character3);
		}
		initial_entry.setWins(0);
		session_entry_repo.save(initial_entry);
		
		List<SessionEntry> entries = new ArrayList<SessionEntry>();
		entries.add(initial_entry);
		
		// Create character entries for each of the three characters
		for ( String character : characters )
		{
			CharacterEntry entry = new CharacterEntry();					
			entry.setCharacter(character);		
			entry.setPlayer(p1);	
			character_entry_repo.save(entry);
		}
		
		return new PlayerJSON(p1, entries);
	}
	
	@RequestMapping("/edit_player")
	@ResponseBody
	public PlayerJSON editPlayer(@RequestParam(value="player") long player_id, @RequestParam(value="name") String name, @RequestParam(value="gamertag") String gamertag, 
			@RequestParam(value="chara1") String character1, @RequestParam(value="chara2", defaultValue="UNK") String character2, 
			@RequestParam(value="chara3", defaultValue="UNK") String character3, @RequestParam(value="rank") int rank)
	{
		Player p1 = repo.findOne(player_id);
		
		if ( p1 == null ) return null;
		if ( rank == -1 ) return null;
		
		p1.setGamertag(gamertag);
		p1.setName(name);
		repo.save(p1);
		
		List<String> characters = new ArrayList<String>();
		characters.add(character1);
		
		// Remove initial entry
		SessionEntry initial_entry_to_delete = null;
		for ( SessionEntry entry : session_entry_repo.findSessionEnryByPlayer(p1) )
		{
			if ( entry.getSession().getSessionId() == SessionController.INITIAL_SESSION_ID )
			{
				initial_entry_to_delete = entry;
			}
		}			
		session_entry_repo.delete(initial_entry_to_delete);
		
		// Add an initial session entry
		Session session = session_repo.findOne(SessionController.INITIAL_SESSION_ID);
		SessionEntry initial_entry = new SessionEntry();
		initial_entry.setSession(session);
		initial_entry.setPlayer(p1);
		initial_entry.setStartRank(rank);
		initial_entry.setEndRank(rank);
		initial_entry.setCharacter1(character1);
		if ( !character2.equals("UNK") ) 
		{
			initial_entry.setCharacter2(character2);
			characters.add(character2);
		}
		if ( !character3.equals("UNK") )
		{
			initial_entry.setCharacter3(character3);
			characters.add(character3);
		}
		initial_entry.setWins(0);
		session_entry_repo.save(initial_entry);
		
		List<SessionEntry> entries = new ArrayList<SessionEntry>();
		entries.add(initial_entry);
		
		// Remove character entries
		List<CharacterEntry> character_entries = character_entry_repo.findCharacterEntriesByPlayer(p1);
		
		for ( CharacterEntry entry : character_entries )
		{
			character_entry_repo.delete(entry);;
		}
		
		// Create character entries for each of the three characters
		for ( String character : characters )
		{
			CharacterEntry entry = new CharacterEntry();					
			entry.setCharacter(character);		
			entry.setPlayer(p1);	
			character_entry_repo.save(entry);
		}
		
		return new PlayerJSON(p1, entries);
	}
	
	@RequestMapping("/remove_player")
	@ResponseBody
	public String removePlayer(@RequestParam(value="player") long player_id)
	{
		Player player = repo.findOne(player_id);
		
		if ( player == null ) return "Invalid Player";
		
		List<CharacterEntry> character_entries = character_entry_repo.findCharacterEntriesByPlayer(player);
		
		for ( CharacterEntry entry : character_entries )
		{
			character_entry_repo.delete(entry);;
		}
		
		List<SessionEntry> session_entries = session_entry_repo.findSessionEnryByPlayer(player);
		
		for ( SessionEntry entry : session_entries )
		{
			session_entry_repo.delete(entry);
		}
		
		repo.delete(player);
		
		return "Player Removed";
	}
	
	@PostConstruct
	public void init()
	{
		for ( int i = 0; i <= 10; i++ )
		{
			Player p1 = new Player();
			p1.setPlayerId((long)(i+1));
			p1.setGamertag("Player " + (i+1));
			p1.setName("Player " + (i+1));
			
			//repo.save(p1);	
		}
	}
}
