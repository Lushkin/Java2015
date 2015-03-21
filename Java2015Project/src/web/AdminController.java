package web;

import hibernate.DataAccess;
import hibernate.java.Promotions;
import hibernate.java.Users;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminController extends HttpServlet
{
	private String adminUrl;
	
	public void init()
	{
		adminUrl = getInitParameter("AdminUrl");
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
	
	private void CreatePromotion(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException
	{
		String promotionName = req.getParameter("PromotionName");
		DataAccess.Promotions().CreatePromotion(promotionName);
	}
	private void CreateProf(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException, ParseException
	{
		String prenom = req.getParameter("Prenom");
		String nom = req.getParameter("Nom");

		DateFormat format = new SimpleDateFormat("yyyy-dd-MM", Locale.ENGLISH);
		Date dateNaissance = (Date) format.parse(req.getParameter("DateNaissance"));

		String email = req.getParameter("Email");
		String password = req.getParameter("Password");
		
		DataAccess.Users().CreateUser(nom, prenom, email, dateNaissance, password, 2);
	}
	
	private void CreateEtudiant(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException, ParseException
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
