package web;

import hibernate.DataAccess;
import hibernate.Promotions;
import hibernate.Users;
import hibernate.dao.PromotionsDAO;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
		if(action != null)
		{
			if(req.getMethod() == "POST")
			{
				try
				{
					switch(action)
					{
						case "/CreatePromotion":
							CreatePromotion(req,rep);
						break;
						case "/CreateProf":
							CreateProf(req, rep);
						break;
						case "/CreateEtudiant":
							CreateEtudiant(req,rep);
						break;
					}
					
				} catch (Exception e)
				{
					e.printStackTrace();
				}
				rep.sendRedirect("/Java2015Project/Admin");
			}else{
				switch(action)
				{
					case "/CreatePromotion":
						req.getServletContext().getRequestDispatcher("/WEB-INF/Views/User/Admin/CreatePromotion.jsp").forward(req, rep);
					break;
					case "/CreateProf":
						req.getServletContext().getRequestDispatcher("/WEB-INF/Views/User/Admin/CreateProf.jsp").forward(req, rep);
					break;
					case "/CreateEtudiant":
						req.getServletContext().getRequestDispatcher("/WEB-INF/Views/User/Admin/CreateEtudiant.jsp").forward(req, rep);
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
		DataAccess.promotions().CreatePromotion(promotionName);
	}
	private void CreateProf(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException, HibernateException, ParseException
	{
		String prenom = req.getParameter("Prenom");
		String nom = req.getParameter("Nom");

		DateFormat format = new SimpleDateFormat("yyyy-dd-MM", Locale.ENGLISH);
		Date dateNaissance = (Date) format.parse(req.getParameter("DateNaissance"));

		String email = req.getParameter("Email");
		String password = req.getParameter("Password");
		
		DataAccess.Users().CreateUser(nom, prenom, email, dateNaissance, password, 2);
	}
	
	private void CreateEtudiant(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException, HibernateException, ParseException
	{
		String prenom = req.getParameter("Prenom");
		String nom = req.getParameter("Nom");

		DateFormat format = new SimpleDateFormat("yyyy-dd-MM", Locale.ENGLISH);
		Date dateNaissance = (Date) format.parse(req.getParameter("DateNaissance"));

		String email = req.getParameter("Email");
		String password = req.getParameter("Password");
		
		DataAccess.Users().CreateUser(nom, prenom, email, dateNaissance, password, 3);
	}
	private void GetTeachers(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException
	{
		List<Users> teachers = DataAccess.Users().GetTeachers();		
		req.setAttribute("Teachers", teachers);
	}
	
	private void GetPromotions(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException
	{
		List<Promotions> promotions = DataAccess.promotions().GetPromotions();
		req.setAttribute("Promotions", promotions);
	}
	
	private void GetStudents(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException
	{
		List<Users> students = DataAccess.Users().GetStudents();		
		req.setAttribute("Students", students);
	}
}
