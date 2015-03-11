package web;

import hibernate.DataAccess;
import hibernate.Users;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminController extends HttpServlet
{
	public void init()
	{
		System.out.println("init methode called !");
	}
	
	public void service(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException
	{	
		String action = req.getPathInfo();
		
		if(action != null)
		{
			switch(action)
			{
			case "/Teachers":
				GetTeachers(req, rep);
				break;
			case "Promotions":
				break;
			case "Students":
				break;
			}
		}
		else
		{
			GetTeachers(req, rep);
		}
	}
	
	private void GetTeachers(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException
	{
		System.out.println("teachers");
		List<Users> teachers = DataAccess.Users().GetTeachers();		
		req.setAttribute("Teachers", teachers);
		getServletContext().getRequestDispatcher("/WEB-INF/Views/User/admin.jsp").forward(req, rep);
	}
}
