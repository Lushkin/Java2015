package web;

import hibernate.DataAccess;
import hibernate.java.Promotions;
import hibernate.java.Questions;
import hibernate.java.Tests;
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
		System.out.println(action);
		if(action != null)
		{
			switch(action)
			{
				case "/EditTest":
					int id = Integer.parseInt(req.getParameter("id"));
					System.out.println(id);
					Tests test = (Tests)DataAccess.Tests().GetTest(id);
					System.out.println(test);
					req.setAttribute("Test", test);
					getServletContext().getRequestDispatcher(getInitParameter("EditTestUrl")).forward(req, rep);
				case "/Questions":
					getQuestions(req, rep);
				break;
			}
		}else{
			List<Tests> tests = DataAccess.Tests().GetTests();
			System.out.println(tests.size());
			req.setAttribute("Tests", tests);
			getServletContext().getRequestDispatcher(getInitParameter("teacherURl")).forward(req, rep);
		}
			
	}
	
	private void getQuestions(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException
	{
		Users user = (Users)req.getSession().getAttribute("user");
		List<Questions> questions = DataAccess.Questions().getQuestions(user.getId());
		req.setAttribute("Questions", questions);
		getServletContext().getRequestDispatcher(questionUrl).forward(req, rep);
	}
}
