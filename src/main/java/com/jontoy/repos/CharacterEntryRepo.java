package com.jontoy.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jontoy.bo.CharacterEntry;
import com.jontoy.bo.Character;
import com.jontoy.bo.Player;

@Repository
public interface CharacterEntryRepo extends CrudRepository<CharacterEntry, Long>
{
	public List<CharacterEntry> findCharacterEntriesByPlayer(Player player);
	public List<CharacterEntry> findCharacterEntriesByCharacter(Character character);
}
