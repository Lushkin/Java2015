package web;

import hibernate.DataAccess;
import hibernate.java.Users;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserController  extends HttpServlet
{
	String urlAdmin, urlTeacher, urlStudent;
	public void init()
	{
		System.out.println("init methode called !");
		urlAdmin = getInitParameter("AdminUrl");
		urlTeacher = getInitParameter("TeacherUrl");
		urlStudent = getInitParameter("StudentUrl");
	}
	
	public void service(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException
	{	
		String action = req.getPathInfo();
		
		if(action != null)
		{
			switch(action)
			{
			case "/Login":
				Login(req, rep);
				break;
			}
		}
	}
	
	private void Login(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException
	{
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		Users user = DataAccess.Users().Authenticate(email, password);
		
		if(user == null)
		{
			System.out.println("User null");
			req.getSession().setAttribute("errorMsg", "Nom d'utilisateur ou mot de passe incorect !");
			rep.sendRedirect("/Java2015Project/");
		}
		else
		{
			System.out.println("User = " + user.getFirstName());
			switch (user.getRole())
			{
			case 1:
				rep.sendRedirect("/Java2015Project/Admin");
				break;
			case 2:
				rep.sendRedirect("/Java2015Project/Teacher");
				break;
			case 3:
				getServletContext().getRequestDispatcher(urlStudent).forward(req, rep);
				break;
			}
			
		}
	}
}
