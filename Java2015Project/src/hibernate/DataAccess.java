package hibernate;

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
}
