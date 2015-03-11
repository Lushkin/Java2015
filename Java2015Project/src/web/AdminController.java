package web;

import hibernate.DataAccess;
import hibernate.Promotions;
import hibernate.Users;
import hibernate.dao.PromotionsDAO;

import java.io.IOException;
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
				}
			}
		}
		else
		{
			GetTeachers(req, rep);
			getServletContext().getRequestDispatcher(adminUrl).forward(req, rep);
		}
	}
	
	private void CreatePromotion(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException, HibernateException
	{
		String promotionName = req.getParameter("PromotionName");
		DataAccess.promotions().CreatePromotion(promotionName);
	}
	
	private void GetTeachers(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException
	{
		System.out.println("teachers");
		List<Users> teachers = DataAccess.Users().GetTeachers();		
		req.setAttribute("Teachers", teachers);
	}
}
