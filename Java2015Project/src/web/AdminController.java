package web;

import hibernate.DataAccess;
import hibernate.Promotions;
import hibernate.Users;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminController extends HttpServlet
{
	private String adminUrl;
	
	public void init()
	{
		System.out.println("init");
		adminUrl = getInitParameter("AdminUrl");
		System.out.println(adminUrl);
	}
	
	public void service(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException
	{	
		String action = req.getPathInfo();
		
		if(action != null)
		{
			switch(action)
			{
			}
		}
		else
		{
			GetTeachers(req, rep);
			GetStudents(req, rep);
			GetPromotions(req, rep);
			getServletContext().getRequestDispatcher(adminUrl).forward(req, rep);
		}
	}
	
	private void GetTeachers(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException
	{
		List<Users> teachers = DataAccess.Users().GetTeachers();		
		req.setAttribute("Teachers", teachers);
	}
	
	private void GetPromotions(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException
	{
		//List<Promotions> promotions = DataAccess.Users().GetTeachers();
		List<Promotions> promotions = new ArrayList<Promotions>();
		req.setAttribute("Promotions", promotions);
	}
	
	private void GetStudents(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException
	{
		List<Users> students = DataAccess.Users().GetStudents();		
		req.setAttribute("Students", students);
	}
}
