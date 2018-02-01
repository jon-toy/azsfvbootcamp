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
import com.jontoy.bo.CharacterEntry;
import com.jontoy.bo.CharacterJSON;
import com.jontoy.bo.Player;
import com.jontoy.repos.CharacterEntryRepo;
import com.jontoy.repos.PlayerRepo;

@Controller
@RequestMapping("/characters")
public class CharacterEntryController 
{
	@Autowired
	private CharacterEntryRepo repo;
	
	@Autowired
	private PlayerRepo player_repo;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<CharacterEntry> listOfEntries()
	{
		List<CharacterEntry> ret = new ArrayList<>();
		
		for ( CharacterEntry entry : repo.findAll() )
		{
			ret.add(entry);
		}
		
		return ret;
	}
	
	@RequestMapping("/list_by_player")
	@ResponseBody
	public List<CharacterEntry> findByPlayerId(@RequestParam long player_id)
	{
		List<CharacterEntry> ret = new ArrayList<>();
		
		Player player = player_repo.findOne(player_id);
		
		for ( CharacterEntry entry : repo.findCharacterEntriesByPlayer(player) )
		{
			ret.add(entry);
		}
		
		return ret;
	}
	
	@RequestMapping("/list_by_character")
	@ResponseBody
	public List<CharacterEntry> findByPlayerId(@RequestParam(value="character") String character_string)
	{
		Character character = Character.fromString(character_string);
		
		List<CharacterEntry> ret = new ArrayList<>();
		
		for ( CharacterEntry entry : repo.findCharacterEntriesByCharacter(character) )
		{
			ret.add(entry);
		}
		
		return ret;
	}
	
	@RequestMapping("/list_characters")
	@ResponseBody
	public List<CharacterJSON> listOfCharacters()
	{
		List<CharacterJSON> ret = new ArrayList<>();
		
		for ( Character chara : Character.values() )
		{
			ret.add(new CharacterJSON(chara));
		}
		
		return ret;
	}
	
	@RequestMapping("/{id}")
	@ResponseBody
	public CharacterEntry getCharacterEntry(@PathVariable long id)
	{
		return repo.findOne(id);
	}
	
	@RequestMapping("/add_entry")
	@ResponseBody
	public String addEntry(@RequestParam(value="player") long player_id, @RequestParam(value="character") String character_string)
	{
		CharacterEntry entry = new CharacterEntry();
		
		Character character = Character.fromString(character_string);
		
		if ( character == Character.UNK ) return "Invalid Character";
		
		entry.setCharacter(character.name());
		
		Player player = player_repo.findOne(player_id);
		entry.setPlayer(player);
		
		repo.save(entry);
		
		return "Save Successful";
	}
	
	@PostConstruct
	public void init()
	{
		for ( int i = 1; i <= 20; i++ )
		{
 			CharacterEntry entry = new CharacterEntry();
			
			entry.setEntryId((long) (i+1));
			
			if ( i % 2 == 0 )
			{
				Character character = Character.KRN;
				entry.setCharacter(character.name());
			}
			else 
			{
				Character character = Character.CMY;
				entry.setCharacter(character.name());
			}
			
			Player player;
			if ( i <= 11 )
				player = player_repo.findOne((long)i);
			else 
				player = player_repo.findOne((long)(i/2));
			
			if ( player != null )
			{
				entry.setPlayer(player);
			
				repo.save(entry);
			}
		}
	}
}
