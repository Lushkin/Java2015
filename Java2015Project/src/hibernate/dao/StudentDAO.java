package hibernate.dao;

import hibernate.HibernateUtil;
import hibernate.java.Tests;
import hibernate.java.UserTests;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;

public class StudentDAO
{
	@SuppressWarnings("unchecked")
	public List<UserTests> GetPendingTests(int studentId)
	{
		Session session;
		try
		{
			SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
			String today = formater.format(new Date());
			session = HibernateUtil.currentSession();
			List<UserTests> tests = session.createQuery("select ut from UserTests ut join ut.tests t Where UserId = " + studentId + " AND State = 0 AND t.startDate <= GETDATE() AND t.endDate > GETDATE()").list();
			for(UserTests t : tests)
			{
				Hibernate.initialize(t.getTests());
				Hibernate.initialize(t.getTests().getSubjects());
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
			//Object toto = session.createQuery("select endDate from Tests WHERE endDate < GETDATE()").list();
			List<UserTests> tests = session.createQuery("select ut from UserTests ut join ut.tests t Where userId = " + studentId  + " AND (state = 1 OR t.endDate < GETDATE())").list();
			for(UserTests t : tests)
			{
				Hibernate.initialize(t.getTests());
				Hibernate.initialize(t.getTests().getSubjects());
			}
			HibernateUtil.closeSession();
			
			return tests;
		} catch (Exception e)
		{
			return null;
		}
	}
}
