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
	private String questionUrl;
	
	public void init()
	{
		questionUrl =  getInitParameter("teacherQuestionURl");
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
	
	private void getQuestions(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException
	{
		Users user = (Users)req.getSession().getAttribute("user");
		List<Questions> questions = DataAccess.Questions().getQuestions(user.getId());
		req.setAttribute("Questions", questions);
		getServletContext().getRequestDispatcher(questionUrl).forward(req, rep);
	}
}
