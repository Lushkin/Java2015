package web;

import hibernate.DataAccess;
import hibernate.java.Questions;
import hibernate.java.Users;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TeacherController extends HttpServlet
{
	public void init()
	{
		
	}
	
	public void service(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException
	{
		String action = req.getPathInfo();
		
		if(action != null)
		{
			switch(action)
			{
				case "/Questions":
					getQuestions(req, rep);
				break;
			}
		}else
			getServletContext().getRequestDispatcher(getInitParameter("teacherURl")).forward(req, rep);
	}
	
	private void getQuestions(HttpServletRequest req, HttpServletResponse rep)
	{
		List<Questions> questions = DataAccess.Questions().getQuestions(0);		
		req.setAttribute("Questions", questions);
	}
}
