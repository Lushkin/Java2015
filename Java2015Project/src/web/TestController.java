package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestController extends HttpServlet
{
	String url;
	public void init()
	{
		System.out.println("init methode called !");
		url = getInitParameter("testUrl");
	}
	
	public void service(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException
	{	System.out.println("service methode called !");		
		getServletContext().getRequestDispatcher(url).forward(req, rep);
	}
}
