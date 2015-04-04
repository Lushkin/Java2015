package web;

import hibernate.DataAccess;
import hibernate.java.Promotions;
import hibernate.java.Questions;
import hibernate.java.Subjects;
import hibernate.java.Tests;
import hibernate.java.Users;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
		if(req.getSession().getAttribute("user") == null || ((Users)req.getSession().getAttribute("user")).getRole() != 2)
		{
			rep.sendRedirect("/Java2015Project/");
			return;
		}
		
		String action = req.getPathInfo();
		System.out.println(action);
		System.out.println(req.getMethod());
		if(action != null)
		{
			if(req.getMethod() == "POST")
			{
				switch(action)
				{
					case "/EditTest":
						int id = Integer.parseInt(req.getParameter("id"));
						String title = req.getParameter("title");
						int subjectId = Integer.parseInt(req.getParameter("subject"));
						System.out.println(subjectId);
						DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
						Date startDate = new Date();
						Date endDate = new Date();
						try
						{
							startDate = (Date) format.parse(req.getParameter("startDate"));
							endDate = (Date) format.parse(req.getParameter("endDate"));
						} catch (ParseException e)
						{
							e.printStackTrace();
						}
						
						int duration = Integer.parseInt(req.getParameter("duration"));
						DataAccess.Tests().UpdateTest(id, title, subjectId, startDate, endDate, duration);
						getServletContext().getRequestDispatcher(getInitParameter("EditTestUrl")).forward(req, rep);
						break;
					case "/Questions":
						getQuestions(req, rep);
					break;
				}
			}else{
				switch(action)
				{
					case "/EditTest":
						int id = Integer.parseInt(req.getParameter("id"));
						System.out.println(id);
						Tests test = (Tests)DataAccess.Tests().GetTest(id);
						List<Subjects> subjects = (List<Subjects>)DataAccess.Subjects().GetSubjects();
						System.out.println(test);
						req.setAttribute("Test", test);
						req.setAttribute("Subjects", subjects);
						getServletContext().getRequestDispatcher(getInitParameter("EditTestUrl")).forward(req, rep);
						break;
					case "/Questions":
						getQuestions(req, rep);
					break;
				}
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
