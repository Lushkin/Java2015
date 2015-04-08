package hibernate.dao;

import hibernate.HibernateUtil;
import hibernate.java.Tests;
import hibernate.java.UserTests;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;

public class StudentDAO
{
	public List<UserTests> GetPendingTests(int studentId)
	{
		Session session;
		try
		{
			session = HibernateUtil.currentSession();
			List<UserTests> tests = session.createQuery("from UserTests Where UserId = " + studentId + " AND State = 0").list();
			for(UserTests t : tests)
			{
				Hibernate.initialize(t.getTests());
			}
			HibernateUtil.closeSession();
			
			return tests;
		} catch (Exception e)
		{
			return null;
		}
	}
	
	public List<UserTests> GetFinishedTests(int studentId)
	{
		Session session;
		try
		{
			session = HibernateUtil.currentSession();
			List<UserTests> tests = session.createQuery("from UserTests Where UserId = " + studentId + " AND State = 1").list();
			for(UserTests t : tests)
			{
				Hibernate.initialize(t.getTests());
			}
			HibernateUtil.closeSession();
			
			return tests;
		} catch (Exception e)
		{
			return null;
		}
	}
}
