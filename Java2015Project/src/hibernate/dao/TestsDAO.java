package hibernate.dao;

import hibernate.HibernateUtil;
import hibernate.java.Tests;
import hibernate.java.Users;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TestsDAO
{
	public List<Tests> GetTests()
	{
		Session session;
		try
		{
			session = HibernateUtil.currentSession();
			List<Tests> tests = session.createQuery("from Tests").list();
			for(Tests t : tests)
			{
				Hibernate.initialize(t.getSubjects());
			}
			HibernateUtil.closeSession();
			
			return tests;
		} catch (Exception e)
		{
			return null;
		}
		
	}
	
	public Tests GetTest(int id)
	{
		Session session;
		try
		{
			session = HibernateUtil.currentSession();
			Tests test = (Tests)session.get(Tests.class, id);
			Hibernate.initialize(test.getSubjects());
			
			HibernateUtil.closeSession();
			
			return test;
		} catch (Exception e)
		{
			return null;
		}
		
	}
}
