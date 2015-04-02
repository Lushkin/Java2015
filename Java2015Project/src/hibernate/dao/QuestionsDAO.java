package hibernate.dao;

import java.util.List;

import org.hibernate.Session;

import hibernate.HibernateUtil;
import hibernate.java.Promotions;
import hibernate.java.Questions;

public class QuestionsDAO
{
	public List<Questions> getQuestions(int teacherId)
	{
		Session session;
		try
		{
			session = HibernateUtil.currentSession();
			List<Questions> questions = session.createQuery("from Questions WHERE OwnerId = " + teacherId).list();
			HibernateUtil.closeSession();
			
			return questions;
		} 
		catch (Exception e)
		{
			return null;
		}
	}
}
