package com.jontoy.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jontoy.bo.Player;
import com.jontoy.bo.Session;
import com.jontoy.bo.SessionEntry;

@Repository
public interface SessionEntryRepo extends CrudRepository<SessionEntry, Long>
{
	public List<SessionEntry> findSessionEnryByPlayer(Player player);
	public List<SessionEntry> findSessionEnryBySession(Session session);
}
