package hibernate.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import hibernate.DataAccess;
import hibernate.HibernateUtil;
import hibernate.java.Categories;
import hibernate.java.Promotions;
import hibernate.java.QuestionAnswers;
import hibernate.java.Questions;
import hibernate.java.TestQuestions;
import hibernate.java.Tests;
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
			for(Questions q : questions)
			{
				Hibernate.initialize(q.getCategories());
			}
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

	public Questions getQuestion(int id)
	{
		Session session;
		try
		{
			session = HibernateUtil.currentSession();
			Questions question = (Questions)session.get(Questions.class, id);
			Hibernate.initialize(question.getQuestionAnswerses());
			HibernateUtil.closeSession();
			return question;
		} 
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return null;
		}		
	}
	
	public void DeleteQuestionAnswers(Set<QuestionAnswers> answers)
	{
		try
		{
			Transaction transac = HibernateUtil.currentSession().beginTransaction();
			for(QuestionAnswers a : answers)
			{
				HibernateUtil.currentSession().delete(a);
			}
			transac.commit();
			HibernateUtil.closeSession();
		} catch (Exception e)
		{
			System.out.println("Erreur dans DeleteQuestion DAO");
		}
	}
	
	public List<Questions> GetQuestionsByIdTest(int testId)
	{
		Session session;
		try
		{
			session = HibernateUtil.currentSession();
			@SuppressWarnings("unchecked")
			List<TestQuestions> testquestions = session.createQuery("From TestQuestions WHERE TestId = " + testId).list();
			List<Questions> questions = new ArrayList<Questions>();
			for (TestQuestions tq : testquestions)
			{
				Hibernate.initialize(tq.getQuestions());
				questions.add(tq.getQuestions());
			}
			HibernateUtil.closeSession();
			return questions;
		} 
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return null;
		}
	}
	public void DeleteAllQuestionForTestId(int testId)
	{
		Session session;
		try
		{
			session = HibernateUtil.currentSession();
			Transaction transac = HibernateUtil.currentSession().beginTransaction();
			@SuppressWarnings("unchecked")
			List<TestQuestions> testquestions = session.createQuery("From TestQuestions WHERE TestId = " + testId).list();
			for(TestQuestions tq : testquestions)
			{
				HibernateUtil.currentSession().delete(tq);
			}
			transac.commit();
			HibernateUtil.closeSession();
		} catch (Exception e)
		{
			System.out.println(e.getMessage());
			System.out.println("Erreur dans DeleteQuestion DAO");
		}
	}
	public void AddTestQuestions(int questionId, int testId)
	{
		try
		{
			Transaction transac = HibernateUtil.currentSession().beginTransaction();
			TestQuestions tq = new TestQuestions();
			tq.setQuestions((Questions)DataAccess.Questions().getQuestion(questionId));
			tq.setTests((Tests)DataAccess.Tests().GetTest(testId));
			HibernateUtil.currentSession().saveOrUpdate(tq);
			transac.commit();
		} 
		catch (Exception e)
		{
			System.out.println("Erreur dans addtestquestion DAO");
			System.out.println(e.getMessage());
		}
	}
}
