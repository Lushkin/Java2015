package hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import hibernate.HibernateUtil;
import hibernate.java.Categories;
import hibernate.java.Promotions;
import hibernate.java.Questions;
import hibernate.java.Users;

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
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public List<Categories> getCategories()
	{
		Session session;
		try
		{
			session = HibernateUtil.currentSession();
			List<Categories> categories = session.createQuery("from Categories").list();
			HibernateUtil.closeSession();
			
			return categories;
		} 
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public void CreateQuestion(Questions question)
	{
		try
		{
			Transaction transac = HibernateUtil.currentSession().beginTransaction();
			HibernateUtil.currentSession().saveOrUpdate(question);
			transac.commit();
		} 
		catch (Exception e)
		{
			System.out.println("Erreur dans CreateQuestion DAO");
			System.out.println(e.getMessage());
		}
	}
	
	public void DeleteQuestion(int id)
	{
		try
		{
			Transaction transac = HibernateUtil.currentSession().beginTransaction();
			Questions question = (Questions)HibernateUtil.currentSession().load(Questions.class, id);
			HibernateUtil.currentSession().delete(question);
			transac.commit();
			HibernateUtil.closeSession();
		} catch (Exception e)
		{
			System.out.println("Erreur dans DeleteQuestion DAO");
		}
	}
}
