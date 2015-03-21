package hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import hibernate.HibernateUtil;
import hibernate.java.Promotions;

public class PromotionsDAO 
{
	public Promotions CreatePromotion(String promotionName)
	{
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		
		Promotions promotion = new Promotions();
		promotion.setName(promotionName);
		try
		{
			System.out.println(promotion.getId());
			session.save(promotion);
			tx.commit();
		}
		catch (Exception e)
		{
			return null;
		}
		finally
		{
			HibernateUtil.closeSession();
		}
		return promotion;
	}
	
	@SuppressWarnings("unchecked")
	public List<Promotions> GetPromotions()
	{
		Session session;
		try
		{
			session = HibernateUtil.currentSession();
			List<Promotions> promotions = session.createQuery("from Promotions").list();
			HibernateUtil.closeSession();
			
			return promotions;
		} 
		catch (Exception e)
		{
			return null;
		}
	}
	
	public Promotions GetPromotion(int id)
	{
		Session session;
		try
		{
			session = HibernateUtil.currentSession();
			Promotions promotion = (Promotions)session.get(Promotions.class, id);
			HibernateUtil.closeSession();
			
			return promotion;
		} 
		catch (Exception e)
		{
			return null;
		}
	}
	
	public void UpdatePromotion(int id, String name)
	{
		Session session;
		try
		{
			session = HibernateUtil.currentSession();
			Transaction transac = session.beginTransaction();
			Promotions promotion = (Promotions)session.load(Promotions.class, id);
			promotion.setName(name);
			session.saveOrUpdate(promotion);
			transac.commit();
			HibernateUtil.closeSession();
		} 
		catch (Exception e)
		{
			System.out.println("erreur dans update promotion");
		}
	}
	
	public void DeletePromotion(int id)
	{
		Session session;
		try
		{
			session = HibernateUtil.currentSession();
			Transaction transac = session.beginTransaction();
			Promotions promotion = (Promotions)session.load(Promotions.class, id);
			session.delete(promotion);
			transac.commit();
			HibernateUtil.closeSession();
		} 
		catch (Exception e)
		{
			System.out.println("erreur dans delete promotion");
		}
	}
}
