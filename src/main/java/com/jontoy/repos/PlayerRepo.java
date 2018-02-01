package com.jontoy.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jontoy.bo.Player;

@Repository
public interface PlayerRepo extends CrudRepository<Player, Long>
{
}
