package hibernate.dao;

import hibernate.DataAccess;
import hibernate.HibernateUtil;
import hibernate.java.Questions;
import hibernate.java.Subjects;
import hibernate.java.Tests;
import hibernate.java.UserTests;
import hibernate.java.Users;

import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sun.xml.internal.ws.wsdl.writer.UsingAddressing;

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
	
	public Tests UpdateTest(int id, String title, int subjectId, Date startDate, Date endDate, int duration)
	{
		try
		{
			Transaction transac = HibernateUtil.currentSession().beginTransaction();
			Tests test = (Tests)HibernateUtil.currentSession().load(Tests.class, id);
			Hibernate.initialize(test.getSubjects());
			test.setTitle(title);
			
			if(test.getSubjects().getId() != subjectId)
			{
				Subjects subject = DataAccess.Subjects().GetSubject(subjectId);
				if(subject != null)
					test.setSubjects(subject);
			}
			
			test.setStartDate(startDate);
			test.setEndDate(endDate);
			test.setDuration(duration);
			HibernateUtil.currentSession().saveOrUpdate(test);
			transac.commit();
			HibernateUtil.closeSession();
			return test;
		} catch (Exception e)
		{
			System.out.println("erreur dans test Update! gg lucas, tu sais pas coder..." + e.getMessage());
		}
		return null;
	}
	
	public Tests CreateTest(String title, int subjectId, Date startDate, Date endDate, int duration)
	{
		try
		{
			Transaction transac = HibernateUtil.currentSession().beginTransaction();
			Tests test = new Tests();
			test.setTitle(title);
			
			Subjects subject = DataAccess.Subjects().GetSubject(subjectId);
			if(subject != null)
				test.setSubjects(subject);
			
			test.setStartDate(startDate);
			test.setEndDate(endDate);
			test.setDuration(duration);
			
			
			HibernateUtil.currentSession().saveOrUpdate(test);
			transac.commit();
			HibernateUtil.closeSession();
			return test;
		} catch (Exception e)
		{
			System.out.println("erreur dans test Update! gg lucas, tu sais pas coder..." + e.getMessage());
		}
		return null;
	}
	
	public UserTests GetUserTest(int userId, int testId)
	{
		Session session;
		try
		{
			session = HibernateUtil.currentSession();
			UserTests test = (UserTests)session.createQuery("from UserTests Where UserId = " + userId + " AND TestId = " + testId).list().get(0);
			
			HibernateUtil.closeSession();
			
			return test;
		} catch (Exception e)
		{
			return null;
		}
	}
	
}
