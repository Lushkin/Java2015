package hibernate.dao;

import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;
import hibernate.HibernateUtil;
import hibernate.Promotions;
import hibernate.Users;
import hibernate.base.BasePromotionsDAO;

/**
 * This class has been automatically generated by Hibernate Synchronizer.
 * For more information or documentation, visit The Hibernate Synchronizer page
 * at http://www.binamics.com/hibernatesync or contact Joe Hudson at joe@binamics.com.
 *
 * This is the object class that relates to the Promotions table.
 * Any customizations belong here.
 */
public class PromotionsDAO extends BasePromotionsDAO {
	public Promotions CreatePromotion(String promotionName) throws HibernateException
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
		catch (HibernateException e)
		{
			return null;
		}
		finally
		{
			HibernateUtil.closeSession();
		}
		return promotion;
	}
	
	public List<Promotions> GetPromotions()
	{
		Session session;
		try
		{
			session = HibernateUtil.currentSession();
			List<Promotions> promotions = session.find("from Promotions");
			HibernateUtil.closeSession();
			
			return promotions;
		} 
		catch (HibernateException e)
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
			Promotions promotion = (Promotions)session.load(Promotions.class, id);
			HibernateUtil.closeSession();
			
			return promotion;
		} 
		catch (HibernateException e)
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
		catch (HibernateException e)
		{
			System.out.println("erreur dans update promotion");
		}
	}
}
