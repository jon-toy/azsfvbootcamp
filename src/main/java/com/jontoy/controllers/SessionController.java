package com.jontoy.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jontoy.bo.Session;
import com.jontoy.repos.SessionRepo;

@Controller
@RequestMapping("/sessions")
public class SessionController 
{
	public static final long INITIAL_SESSION_ID = 6L;
	
	@Autowired
	private SessionRepo repo;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<Session> listOfSession()
	{
		List<Session> ret = new ArrayList<>();
		
		for ( Session session : repo.findAll() )
		{
			ret.add(session);
		}
		
		return ret;
	}
	
	@RequestMapping("/{id}")
	@ResponseBody
	public Session getSession(@PathVariable Long id)
	{
		return repo.findOne(id);
	}
	
	@RequestMapping("/add_session")
	@ResponseBody
	public String addSession(@RequestParam(value="name") String name, @RequestParam(value="location") String location, @RequestParam(value="time") Long time)
	{
		Session s1 = new Session();
		
		s1.setLocation(location);
		s1.setName(name);
		s1.setTime(time);
		
		repo.save(s1);
		
		return "Save Successful";
	}
	
	@PostConstruct
	public void init() throws ParseException
	{
		// Leave this in for production

		Session s1 = new Session();
		s1.setSessionId((long) 1);
		s1.setLocation("The Gaming Zone");
		s1.setName("Session 1: 2/26");
		String string_date = "26-February-2016";
		SimpleDateFormat f = new SimpleDateFormat("dd-MMM-yyyy");
		Date d = f.parse(string_date);
		long milliseconds = d.getTime();		
		s1.setTime(milliseconds);
		repo.save(s1);
		
		Session s2 = new Session();
		s2.setSessionId((long) 2);
		s2.setLocation("The Gaming Zone");
		s2.setName("Session 2: 3/4");
		string_date = "04-March-2016";
		f = new SimpleDateFormat("dd-MMM-yyyy");
		d = f.parse(string_date);
		milliseconds = d.getTime();		
		s2.setTime(milliseconds);
		repo.save(s2);
		
		Session s3 = new Session();
		s3.setSessionId((long) 3);
		s3.setLocation("The Gaming Zone");
		s3.setName("Session 3: 3/11");
		string_date = "11-March-2016";
		f = new SimpleDateFormat("dd-MMM-yyyy");
		d = f.parse(string_date);
		milliseconds = d.getTime();		
		s3.setTime(milliseconds);
		repo.save(s3);
		
		Session s4 = new Session();
		s4.setSessionId((long) 4);
		s4.setLocation("The Gaming Zone");
		s4.setName("Session 4: 3/25");
		string_date = "25-March-2016";
		f = new SimpleDateFormat("dd-MMM-yyyy");
		d = f.parse(string_date);
		milliseconds = d.getTime();		
		s4.setTime(milliseconds);
		repo.save(s4);
		
		Session s5 = new Session();
		s5.setSessionId((long) 5);
		s5.setLocation("The Gaming Zone");
		s5.setName("Session 5: 4/1");
		string_date = "01-April-2016";
		f = new SimpleDateFormat("dd-MMM-yyyy");
		d = f.parse(string_date);
		milliseconds = d.getTime();		
		s5.setTime(milliseconds);
		repo.save(s5);
		
		Session initial = new Session();
		initial.setSessionId(INITIAL_SESSION_ID);
		initial.setLocation("The Gaming Zone");
		initial.setName("Initial");
		string_date = "13-November-1987";
		f = new SimpleDateFormat("dd-MMM-yyyy");
		d = f.parse(string_date);
		milliseconds = d.getTime();		
		initial.setTime(milliseconds);
		repo.save(initial);
	}
}
