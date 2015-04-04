package hibernate.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import hibernate.HibernateUtil;
import hibernate.java.Subjects;
import hibernate.java.Tests;
import hibernate.java.Users;

public class SubjectDAO
{
	public List<Subjects> GetSubjects()
	{
		Session session;
		try
		{
			session = HibernateUtil.currentSession();
			List<Subjects> subjects = session.createQuery("from Subjects").list();
			HibernateUtil.closeSession();
			
			return subjects;
		} catch (Exception e)
		{
			return null;
		}
	}
	
	public Subjects GetSubject(int id)
	{
		Session session;
		try
		{
			session = HibernateUtil.currentSession();
			Subjects subject = (Subjects)session.get(Subjects.class, id);
			HibernateUtil.closeSession();
			
			return subject;
		} catch (Exception e)
		{
			return null;
		}
	}
	
	public void updateSubject(int id, String name)
	{
		Session session;
		try
		{
			Transaction transac = HibernateUtil.currentSession().beginTransaction();
			Subjects subject = (Subjects)HibernateUtil.currentSession().load(Subjects.class, id);
			subject.setName(name);
			HibernateUtil.currentSession().saveOrUpdate(subject);
			transac.commit();
			HibernateUtil.closeSession();
		} catch (Exception e)
		{
			System.out.println("erreur update Subject... gg lucas !" + e.getMessage());
		}
	}
}
