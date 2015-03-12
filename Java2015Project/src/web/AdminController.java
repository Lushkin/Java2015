package web;

import hibernate.DataAccess;
import hibernate.HibernateUtil;
import hibernate.Promotions;
import hibernate.Users;
import hibernate.dao.PromotionsDAO;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
		adminUrl = getInitParameter("AdminUrl");
	}
	
	public void service(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException
	{	
		String action = req.getPathInfo();
		if(action != null)
		{
			if(req.getMethod() == "POST")
			{
				System.out.println("POST " + action);
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
						case "/SaveStudentsToPromotion":
							PutStudentsInPromotion(req, rep);
						break;
						case "/EditPromotion":
							EditPromotion(req,rep);
						break;
						case "/EditEtudiant":
							EditEtudiant(req,rep);
						break;
						case "/EditProf":
							EditEtudiant(req,rep);
						break;
					}
					
				} catch (Exception e)
				{
					e.printStackTrace();
				}
				rep.sendRedirect("/Java2015Project/Admin");
			}else{
				System.out.println(action);
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
					case "/EditPromotion":
						GetPromotion(req, rep);
						req.getServletContext().getRequestDispatcher("/WEB-INF/Views/User/Admin/EditPromotion.jsp").forward(req, rep);
					break;
					case "/EditEtudiant":
						GetStudent(req, rep);
						req.getServletContext().getRequestDispatcher("/WEB-INF/Views/User/Admin/EditEtudiant.jsp").forward(req, rep);
					break;
					case "/EditProf":
						GetStudent(req, rep);
						req.getServletContext().getRequestDispatcher("/WEB-INF/Views/User/Admin/EditProf.jsp").forward(req, rep);
					break;
					case "/DeleteProf":
						try
						{
							DeleteProf(req, rep);
						} catch (HibernateException e)
						{
						}
						GetTeachers(req, rep);
						GetStudents(req, rep);
						GetPromotions(req, rep);
						getServletContext().getRequestDispatcher(adminUrl).forward(req, rep);
						break;
					case "/DeletePromotion":
						System.out.println("delete promotion ->");
						DeletePromotion(req, rep);
						GetTeachers(req, rep);
						GetStudents(req, rep);
						GetPromotions(req, rep);
						getServletContext().getRequestDispatcher(adminUrl).forward(req, rep);
						break;
					case "/DeleteEtudiant":
						DeleteEtudiant(req, rep);
						GetTeachers(req, rep);
						GetStudents(req, rep);
						GetPromotions(req, rep);
						getServletContext().getRequestDispatcher(adminUrl).forward(req, rep);
						break;
				}
			}
		}
		else
		{
			System.out.println("no action");
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
	
	private void EditPromotion(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException, HibernateException
	{
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("PromotionName");
		DataAccess.Promotions().UpdatePromotion(id, name);
	}
	
	private void EditEtudiant(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException, HibernateException, ParseException
	{
		int id = Integer.parseInt(req.getParameter("id"));
		String prenom = req.getParameter("Prenom");
		String nom = req.getParameter("Nom");
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date dateNaissance = (Date) format.parse(req.getParameter("DateNaissance"));
		String email = req.getParameter("Email");
		String password = req.getParameter("Password");
		DataAccess.Users().UpdateUser(id,nom, prenom, email, dateNaissance, password);
	}
	
	private void GetTeachers(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException
	{
		List<Users> teachers = DataAccess.Users().GetTeachers();		
		req.setAttribute("Teachers", teachers);
	}
	
	private void GetPromotion(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException
	{
		int id = Integer.parseInt(req.getParameter("id"));
		Promotions promotion = DataAccess.Promotions().GetPromotion(id);
		req.setAttribute("Promotion", promotion);
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
		String json = req.getParameter("students");
		System.out.println(json);
		System.out.println(java.util.Arrays.toString(json.split("\\|")));
		String[] lines = json.split("\\|");
		HashMap<String, String> students = new HashMap<String, String>();
		HashMap<String, String> promos = new HashMap<String, String>();
		
		String[] col;
		for(int i = 1; i < lines.length; i++)
		{
			if(lines[i].contains("\""))
				lines[i] = lines[i].substring(0, lines[i].length()-1);

				col = lines[i].split("\\:");
				students.put(Integer.toString(i), col[1]);
				promos.put(Integer.toString(i), col[0]);
				
				String toto = "roro";
				toto.toString();
		}
		System.out.println(students.toString());
		System.out.println(promos.toString());
		
		boolean result = DataAccess.Users().PutUsersIntoPromotion(students, promos);		
	}
	
	private void GetPromotions(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException
	{

		List<Promotions> promotions = DataAccess.Promotions().GetPromotions();
		req.setAttribute("Promotions", promotions);
		DateFormat d  = new SimpleDateFormat();
	}
	
	private void DeletePromotion(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException
	{
		int id = Integer.parseInt(req.getParameter("id"));
		try
		{
			List<Users> users = HibernateUtil.currentSession().find("from Users WHERE PromotionId = " + id);
			for (Users user : users)
			{
				user.setPromotion(null);
				DataAccess.Users().update(user);
			}
			DataAccess.Promotions().DeletePromotion(id);
		} catch (HibernateException e)
		{
			System.out.println("erreur delete promotion");
		}
	}
	
	private void DeleteProf(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException, HibernateException
	{
		int id = Integer.parseInt(req.getParameter("id"));
		DataAccess.Users().DeleteUser(id);
	}
	
	private void DeleteEtudiant(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException
	{
		int id = Integer.parseInt(req.getParameter("id"));
		DataAccess.Users().DeleteUser(id);
	}
	
	private void GetStudents(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException
	{
		List<Users> students = DataAccess.Users().GetStudents();		
		req.setAttribute("Students", students);
	}
	
	private void GetStudent(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException
	{
		int id = Integer.parseInt(req.getParameter("id"));
		Users user = DataAccess.Users().GetUser(id);
		req.setAttribute("Etudiant", user);
	}
}
