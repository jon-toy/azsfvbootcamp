package com.jontoy.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jontoy.bo.Session;

@Repository
public interface SessionRepo extends CrudRepository<Session, Long>
{

}
