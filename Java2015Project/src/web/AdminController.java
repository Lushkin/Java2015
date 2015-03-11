package web;

import hibernate.DataAccess;
import hibernate.Promotions;
import hibernate.Users;
import hibernate.dao.PromotionsDAO;

import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.hibernate.HibernateException;

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
		
		System.out.println(action);
		if(action != null)
		{
			if(req.getMethod() == "POST")
			{
				try
				{
					CreatePromotion(req,rep);
				} catch (HibernateException e)
				{
					e.printStackTrace();
				}
				getServletContext().getRequestDispatcher(adminUrl).forward(req, rep);
			}else{
				switch(action)
				{
					case "/CreatePromotion":
						req.getServletContext().getRequestDispatcher("/WEB-INF/Views/User/Admin/CreatePromotion.jsp").forward(req, rep);
					break;
					case "/StudentsToPromotion":
						GetPromotionsAndStudents(req, rep);
						req.getServletContext().getRequestDispatcher("/WEB-INF/Views/User/Admin/StudentsToPromotion.jsp").forward(req, rep);
					break;
					case "/SaveStudentsToPromotion":
						PutStudentsInPromotion(req, rep);
					break;
				}
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
	
	private void CreatePromotion(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException, HibernateException
	{
		String promotionName = req.getParameter("PromotionName");
		DataAccess.Promotions().CreatePromotion(promotionName);
	}
	
	private void GetTeachers(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException
	{
		List<Users> teachers = DataAccess.Users().GetTeachers();		
		req.setAttribute("Teachers", teachers);
	}
	
	
	private void GetPromotionsAndStudents(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException
	{
		System.out.println("GetPromotions");
		GetPromotions(req, rep);
		
		System.out.println("GetStudents");
		GetStudents(req, rep);
	}
	
	private void PutStudentsInPromotion(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException
	{
		System.out.println("promo");
		List<Integer> studentIds = Arrays.asList(9);
		
		boolean result = DataAccess.Users().PutUsersIntoPromotion(1, studentIds);
		System.out.println("Result : " + result);
		
	}
	
	private void GetPromotions(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException
	{
		List<Promotions> promotions = DataAccess.Promotions().GetPromotions();
		req.setAttribute("Promotions", promotions);
	}
	
	private void GetStudents(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException
	{
		List<Users> students = DataAccess.Users().GetStudents();		
		req.setAttribute("Students", students);
	}
}
