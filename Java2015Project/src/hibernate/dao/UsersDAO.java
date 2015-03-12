package hibernate.dao;


import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;
import hibernate.HibernateUtil;
import hibernate.Promotions;
import hibernate.Users;
import hibernate.base.BaseUsersDAO;

/**
 * This class has been automatically generated by Hibernate Synchronizer.
 * For more information or documentation, visit The Hibernate Synchronizer page
 * at http://www.binamics.com/hibernatesync or contact Joe Hudson at joe@binamics.com.
 *
 * This is the object class that relates to the Users table.
 * Any customizations belong here.
 */
public class UsersDAO extends BaseUsersDAO {
	
	public Users Authenticate(String email, String password)
	{
		Session session;
		try
		{
			session = HibernateUtil.currentSession();
			List<Users> user = session.find("from Users WHERE Email = '" + email + "' AND Password = '" + password + "'");
			HibernateUtil.closeSession();
			
			if(user != null && user.size() > 0)
			{
				return user.get(0);
			}
			else
			{
				return null;
			}
		} 
		catch (HibernateException e)
		{
			return null;
		}
	}
	
	public void CreateUser(String nom, String prenom, String email, Date datenaissance, String password, int role)
	{
		Users user = new Users();
		user.setFirstName(prenom);
		user.setLastName(nom);
		user.setEmail(email);
		user.setPassword(password);
		user.setBirthDate(datenaissance);
		user.setRole(role);
		
		try
		{
			Transaction transac = HibernateUtil.currentSession().beginTransaction();
			HibernateUtil.currentSession().saveOrUpdate(user);
			transac.commit();
		} catch (HibernateException e)
		{
			System.out.println("Erreur dans CreateUser DAO");
		}
	}
	
	public List<Users> GetTeachers()
	{
		Session session;
		try
		{
			session = HibernateUtil.currentSession();
			List<Users> teachers = session.find("from Users WHERE Role = 2");
			HibernateUtil.closeSession();
			
			return teachers;
		} 
		catch (HibernateException e)
		{
			return null;
		}
	}
	
	public Users GetUser(int id)
	{
		try
		{
			Session session = HibernateUtil.currentSession();
			Users user = (Users)session.load(Users.class, id);
			HibernateUtil.closeSession();
			return user;
		}
		catch (HibernateException e)
		{
			 return null;
		}
	}
	
	public List<Users> GetStudents()
	{
		Session session;
		try
		{
			session = HibernateUtil.currentSession();
			List<Users> students = session.find("from Users WHERE Role = 3");
			HibernateUtil.closeSession();
			
			return students;
		} 
		catch (HibernateException e)
		{
			return null;
		}
	}
	
	
	public boolean PutUsersIntoPromotion(HashMap<String, String> std, HashMap<String, String> prom)
	{
		Session session;
		try
		{
			session = HibernateUtil.currentSession();
			Transaction tx = session.beginTransaction();
			
			for(String p: prom.keySet()){
				Promotions promotion = null;
				if(Integer.parseInt(prom.get(p)) != 0)
				{
					promotion = (Promotions) session.load(Promotions.class, Integer.parseInt(prom.get(p)));
					
					if(promotion == null)
						return false; //Promotion does not exists
				}		
				
				for(String s : std.keySet())
				{
					Users usr = (Users) session.load(Users.class, Integer.parseInt(std.get(s)));
					if(usr != null)	
					{
						if(p.equalsIgnoreCase(s))
						{
							if(Integer.parseInt(prom.get(p)) == 0)						
								usr.setPromotion(null);
							else
								usr.setPromotion(promotion);
							session.save(usr);
							tx.commit();
						}
					}
					else
						System.out.println("User not found");
				}
			}
			HibernateUtil.closeSession();
			return true;
        }
		catch(HibernateException e)
		{
			System.out.println("Exception : " + e);
			return false;
		}
	}
	
	public boolean PutUsersIntoPromotion(int promotionId, List<Integer> studentIds)
	{
		if(promotionId <= 0)
			return false; //Promotion Id must be greather than 0
		
		Session session;
		try
		{
			session = HibernateUtil.currentSession();
			Transaction tx = session.beginTransaction();

			Promotions promotion = (Promotions) session.load(Promotions.class, promotionId);
			if(promotion == null)
				return false; //Promotion does not exists
			
			System.out.println("Promotion : " + promotion.getName());
				
			for(int id : studentIds)
			{
				Users usr = (Users) session.load(Users.class, id);
				
				if(usr != null)	
				{
					System.out.println("User : " + usr.getFirstName() + " " + usr.getLastName());
					usr.setPromotion(promotion);
					session.save(usr);
					tx.commit();
					System.out.println("User : " + usr.getFirstName() + " " + usr.getLastName() + " / " + usr.getPromotion().getName());
				}
				else
					System.out.println("User not found");
			}
			HibernateUtil.closeSession();
			return true;
		}
		catch(HibernateException e)
		{
			System.out.println("Exception : " + e);
			return false;
		}
	}
}
