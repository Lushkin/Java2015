package hibernate;

import hibernate.dao.PromotionsDAO;
import hibernate.dao.UsersDAO;

public class DataAccess
{
	private static UsersDAO users;
	
	public static UsersDAO Users()
	{
		if (users == null)
			users = new UsersDAO();
		return users;
	}
	
	private static PromotionsDAO promotions;
	
	public static PromotionsDAO Promotions()
	{
		if (promotions == null)
			promotions = new PromotionsDAO();
		return promotions;
	}
}
