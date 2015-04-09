package hibernate.dao;

import hibernate.DataAccess;
import hibernate.HibernateUtil;
import hibernate.java.Questions;
import hibernate.java.Subjects;
import hibernate.java.Tests;
import hibernate.java.UserTests;
import hibernate.java.Users;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
				Hibernate.initialize(t.getUserTestses());
			}
			HibernateUtil.closeSession();
			
			return tests;
		} catch (Exception e)
		{
			return null;
		}
		
	}
	
//	public List<Tests> GetAttributedTests()
//	{
//		Session session;
//		try
//		{
//			session = HibernateUtil.currentSession();
//			List<Tests> tests = session.createQuery("from Tests").list();
//			for(Tests t : tests)
//			{
//				Hibernate.initialize(t.getSubjects());
//				Hibernate.initialize(t.getUserTestses());
//			}
//			HibernateUtil.closeSession();
//			
//			return tests;
//		} catch (Exception e)
//		{
//			return null;
//		}
//		
//	}
	
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
	
	public void UpdateUserTest(UserTests userTest)
	{
		try
		{
			Transaction transac = HibernateUtil.currentSession().beginTransaction();
			HibernateUtil.currentSession().saveOrUpdate(userTest);
			transac.commit();
			HibernateUtil.closeSession();
		} catch (Exception e)
		{
			System.out.println("Erreur dans UpdateUserTest DAO");
		}	
	}
	
	public void DeleteTest(int id)
	{
		try
		{
			Transaction transac = HibernateUtil.currentSession().beginTransaction();
			Tests test = (Tests)HibernateUtil.currentSession().get(Tests.class, id);
			HibernateUtil.currentSession().delete(test);
			transac.commit();
			
		} catch (Exception e)
		{
			System.out.println(e.getMessage());
			System.out.println("Erreur dans DeleteTest DAO");
		}
		finally
		{
			HibernateUtil.closeSession();
		}
	}
	
	@SuppressWarnings("unchecked")
	public boolean RemoveTestFromStudents(HashMap<String, Boolean> userTest, int testId)
	{
		Session session;
		try
		{
			session = HibernateUtil.currentSession();
			Transaction transac = session.beginTransaction();
			
			List<UserTests> tests = session.createQuery("FROM UserTests WHERE TestId = " + testId).list();
			for(int i = 0; i < tests.size(); i++)
			{
				for(String u: userTest.keySet()){
					if(tests.get(i).getUsers().getId() == Integer.parseInt(u))
					{
						session.delete(tests.get(i));
						System.out.println("UserId " + u + " deleted from TestId " + testId);
					}
				}
			}
			transac.commit();
			session = HibernateUtil.currentSession();
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Error : " + e.getMessage());
			return false;
		}
	}
	
	public boolean PutTestToStudents(HashMap<String, Boolean> userTest, int testId)
	{
		Session session;
		try
		{
			session = HibernateUtil.currentSession();
			Transaction transac = session.beginTransaction();
			
			for ( Map.Entry<String, Boolean> entry : userTest.entrySet()) {
			    String key = entry.getKey();
			    Boolean val = entry.getValue();
			    
			    if(val)
			    {
			    	UserTests ut = new UserTests();
				    ut.setUsers((Users)session.get(Users.class, Integer.parseInt(key)));
				    ut.setTests((Tests)session.get(Tests.class, testId));
				    session.saveOrUpdate(ut);
				    System.out.println("UserId " + key + " added to TestId " + testId);
			    }
			}
			transac.commit();
			HibernateUtil.closeSession();
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Error : " + e.getMessage());
			return false;
		}
	}
}
