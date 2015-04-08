package web;

import hibernate.DataAccess;
import hibernate.java.UserTests;
import hibernate.java.Users;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StudentController extends HttpServlet
{
	String url;
	public void init()
	{
		url = getInitParameter("StudentUrl");
	}
	
	public void service(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException
	{
		if(req.getSession().getAttribute("user") == null || ((Users)req.getSession().getAttribute("user")).getRole() != 3)
		{
			rep.sendRedirect("/Java2015Project/");
			return;
		}
		String action = req.getPathInfo();
		if(action != null)
		{
		}
		else
		{
			Users user = (Users)req.getSession().getAttribute("user");
			List<UserTests> pendingTests = DataAccess.Students().GetPendingTests(user.getId());
			req.setAttribute("PendingTests", pendingTests);
			List<UserTests> finishedTests = DataAccess.Students().GetFinishedTests(user.getId());
			req.setAttribute("FinishedTests", finishedTests);
			getServletContext().getRequestDispatcher(url).forward(req, rep);
		}
	}
}