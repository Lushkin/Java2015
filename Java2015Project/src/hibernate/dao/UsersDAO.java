package hibernate.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import hibernate.HibernateUtil;
import hibernate.java.Users;
import hibernate.java.Promotions;

public class UsersDAO
{	
	@SuppressWarnings("unchecked")
	public Users Authenticate(String email, String password)
	{
		Session session;
		try
		{
			session = HibernateUtil.currentSession();
			List<Users> user = session.createQuery("from Users WHERE Email = '" + email + "' AND Password = '" + password + "'").list();
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
		catch (Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
			System.out.println(e.getCause());
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
		} 
		catch (Exception e)
		{
			System.out.println("Erreur dans CreateUser DAO");
		}
	}
	
	@SuppressWarnings("unchecked")
	public void UpdateUser(int id,String nom, String prenom, String email, Date datenaissance, String password)
	{		
		try
		{
			Transaction transac = HibernateUtil.currentSession().beginTransaction();
			Users user = (Users)HibernateUtil.currentSession().load(Users.class, id);
			user.setFirstName(prenom);
			user.setLastName(nom);
			user.setEmail(email);
			user.setPassword(password);
			user.setBirthDate(datenaissance);
			HibernateUtil.currentSession().saveOrUpdate(user);
			transac.commit();
			HibernateUtil.closeSession();
		} catch (Exception e)
		{
			System.out.println("Erreur dans UpdateUser DAO");
		}
	}
	
	public void DeleteUser(int id)
	{	
		try
		{
			Transaction transac = HibernateUtil.currentSession().beginTransaction();
			Users user = (Users)HibernateUtil.currentSession().load(Users.class, id);
			HibernateUtil.currentSession().delete(user);
			transac.commit();
			HibernateUtil.closeSession();
		} catch (Exception e)
		{
			System.out.println("Erreur dans Deleteuser DAO");
		}
	}
	
	public List<Users> GetTeachers()
	{
		Session session;
		try
		{
			session = HibernateUtil.currentSession();
			List<Users> teachers = session.createQuery("from Users WHERE Role = 2").list();
			HibernateUtil.closeSession();
			
			return teachers;
		} 
		catch (Exception e)
		{
			return null;
		}
	}
	
	public Users GetUser(int id)
	{
		try
		{
			Session session = HibernateUtil.currentSession();
			Users user = (Users)session.get(Users.class, id);
			HibernateUtil.closeSession();
			return user;
		}
		catch (Exception e)
		{
			 return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Users> GetStudents()
	{
		Session session;
		try
		{
			session = HibernateUtil.currentSession();
			List<Users> students = session.createQuery("from Users WHERE Role = 3").list();
			HibernateUtil.closeSession();
			
			return students;
		} 
		catch (Exception e)
		{
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Users> GetStudentsWithPromo()
	{
		Session session;
		try
		{
			session = HibernateUtil.currentSession();
			List<Users> students = session.createQuery("from Users WHERE Role = 3").list();
			for(Users u : students)
			{
				Hibernate.initialize(u.getPromotions());
				Hibernate.initialize(u.getUserTestses());
			}
			HibernateUtil.closeSession();
			
			return students;
		} 
		catch (Exception e)
		{
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Users> GetStudentsByPromo(int id)
	{
		Session session;
		try
		{
			session = HibernateUtil.currentSession();
			List<Users> students = session.createQuery("from Users WHERE Role = 3 AND PromotionId = " + id).list();
			for(Users u : students)
			{
				Hibernate.initialize(u.getPromotions());
				Hibernate.initialize(u.getUserTestses());
			}
			HibernateUtil.closeSession();
			
			return students;
		} 
		catch (Exception e)
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
								usr.setPromotions(null);
							else
								usr.setPromotions(promotion);
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
		catch(Exception e)
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

			Promotions promotion = (Promotions)session.get(Promotions.class, promotionId);
			if(promotion == null)
				return false; //Promotion does not exists
			
			System.out.println("Promotion : " + promotion.getName());
				
			for(int id : studentIds)
			{
				Users usr = (Users) session.load(Users.class, id);
				
				if(usr != null)	
				{
					System.out.println("User : " + usr.getFirstName() + " " + usr.getLastName());
					usr.setPromotions(promotion);
					session.save(usr);
					tx.commit();
					System.out.println("User : " + usr.getFirstName() + " " + usr.getLastName() + " / " + usr.getPromotions().getName());
				}
				else
					System.out.println("User not found");
			}
			HibernateUtil.closeSession();
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception : " + e);
			return false;
		}
	}
}
