package web;

import hibernate.DataAccess;
import hibernate.java.Answers;
import hibernate.java.Categories;
import hibernate.java.Promotions;
import hibernate.java.QuestionAnswers;
import hibernate.java.Questions;
import hibernate.java.Subjects;
import hibernate.java.Tests;
import hibernate.java.Users;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Utils.Tools;

public class TeacherController extends HttpServlet
{
	private String questionUrl;
	private List<Categories> categories;
	
	public void init()
	{
		questionUrl =  getInitParameter("teacherQuestionURl");
		categories = DataAccess.Questions().getCategories();
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
						editTestPost(req,rep);
						break;
					case "/CreateTest":
						createTestPost(req,rep);
						break;
					case "/CreateQuestion":
						createQuestion(req, rep);
						break;
					case "/EditQuestion":
						editQuestion(req, rep);
						break;
					case "/TestToStudent" :
						testToStudent(req, rep);
						break;
				}
			}else{
				switch(action)
				{
					case "/EditTest":
						editTest(req,rep);
						break;
					case "/CreateTest":
						createTest(req,rep);
						break;
					case "/Questions":
						getQuestions(req, rep);
						break;
					case "/CreateQuestion":
						createQuestionView(req, rep);
					break;
					case "/DeleteQuestion":
						deleteQuestion(req, rep);
						break;
					case "/EditQuestion":
						editQuestionView(req, rep);
						break;
					case "/TestToStudent" :
						testToStudent(req, rep);
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
	private void editTest(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException
	{
		int id = Integer.parseInt(req.getParameter("id"));
		System.out.println(id);
		Tests test = (Tests)DataAccess.Tests().GetTest(id);
		List<Subjects> subjects = (List<Subjects>)DataAccess.Subjects().GetSubjects();
		System.out.println(test);
		req.setAttribute("Test", test);
		req.setAttribute("Subjects", subjects);
		List<Questions> questionsTest = (List<Questions>)DataAccess.Questions().GetQuestionsByIdTest(test.getId());
		List<Questions> questions = (List<Questions>)DataAccess.Questions().getQuestions(((Users)req.getSession().getAttribute("user")).getId());
		System.out.println("taile questionsTest : " + questionsTest.size());
		System.out.println("taile questions: " + questions.size());
		req.setAttribute("QuestionsTest", questionsTest);
		req.setAttribute("Questions", questions);
		req.setAttribute("Tools", new Tools());
		getServletContext().getRequestDispatcher(getInitParameter("EditTestUrl")).forward(req, rep);
	}
	private void editTestPost(HttpServletRequest req, HttpServletResponse rep) throws IOException
	{
		int id = Integer.parseInt(req.getParameter("id"));
		String title = req.getParameter("title");
		int subjectId = Integer.parseInt(req.getParameter("subject"));
		System.out.println(subjectId);
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date startDate = new Date();
		Date endDate = new Date();
		String quest = req.getParameter("questions");
		String[] quests = quest.split("|");
		DataAccess.Questions().DeleteAllQuestionForTestId(id);
		for (String q : quests)
		{
			System.out.println(q);
			if(!q.equals("")&& !q.equals("|"))
			{
				int questionId = Integer.parseInt(q);
				DataAccess.Questions().AddTestQuestions(questionId, id);
			}
		}
		
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
		rep.sendRedirect("/Java2015Project/Teacher");
	}
	
	private void editQuestion(HttpServletRequest req, HttpServletResponse rep) throws IOException
	{
		int id = Integer.parseInt(req.getParameter("id"));
		Questions question = DataAccess.Questions().getQuestion(id);
		int categoryId = Integer.parseInt(req.getParameter("Categorie"));
		
		for(Categories c : categories)
		{
			if(c.getId() == categoryId)
			{
				question.setCategories(c);
			}
		}
		question.setContent(req.getParameter("Question"));
		question.setPonderation(new BigDecimal(req.getParameter("Points").replaceAll(",", ".")));
		
		DataAccess.Questions().DeleteQuestionAnswers(question.getQuestionAnswerses());
		
		Set<QuestionAnswers> questionAnswers = new HashSet<QuestionAnswers>();		
		int i = 0;
		while(req.getParameter("Answer" + i) != null)
		{
			if(req.getParameter("Answer" + i) != null && !req.getParameter("Answer" + i).isEmpty())
			{
				Answers answer = new Answers(0, req.getParameter("Answer" + i), 1, (byte)(req.getParameter("AnswerCb" + i) != null ? 1 : 0));
				questionAnswers.add(new QuestionAnswers(0, question, answer));
			}
			i++;
		}		
		question.setQuestionAnswerses(questionAnswers);
		
		DataAccess.Questions().CreateQuestion(question);
		rep.sendRedirect("/Java2015Project/Teacher/Questions");
	}

	private void editQuestionView(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException
	{
		int id = Integer.parseInt(req.getParameter("id"));
		Questions question = DataAccess.Questions().getQuestion(id);
		QuestionAnswers q = new QuestionAnswers();
		req.setAttribute("Question", question);
		req.setAttribute("Categories", categories);
		req.getServletContext().getRequestDispatcher("/WEB-INF/Views/User/Teacher/Question/EditQuestion.jsp").forward(req, rep);
	}

	private void deleteQuestion(HttpServletRequest req, HttpServletResponse rep) throws IOException
	{
		int id = Integer.parseInt(req.getParameter("id"));
		DataAccess.Questions().DeleteQuestion(id);
		rep.sendRedirect("/Java2015Project/Teacher/Questions");
	}

	private void getQuestions(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException
	{
		Users user = (Users)req.getSession().getAttribute("user");
		List<Questions> questions = DataAccess.Questions().getQuestions(user.getId());
		req.setAttribute("Questions", questions);
		getServletContext().getRequestDispatcher(questionUrl).forward(req, rep);
	}
	
	private void createQuestionView(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException
	{
		req.setAttribute("Categories", categories);
		req.getServletContext().getRequestDispatcher("/WEB-INF/Views/User/Teacher/Question/CreateQuestion.jsp").forward(req, rep);
	}
	private void createTest(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException
	{
		List<Subjects> subjects = (List<Subjects>)DataAccess.Subjects().GetSubjects();
		req.setAttribute("Subjects", subjects);
		List<Questions> questions = (List<Questions>)DataAccess.Questions().getQuestions(((Users)req.getSession().getAttribute("user")).getId());
		req.setAttribute("Questions", questions);
		req.getServletContext().getRequestDispatcher("/WEB-INF/Views/User/Teacher/CreateTest.jsp").forward(req, rep);
	}
	private void createTestPost(HttpServletRequest req, HttpServletResponse rep) throws IOException
	{
		String title = req.getParameter("title");
		int subjectId = Integer.parseInt(req.getParameter("subject"));
		System.out.println(subjectId);
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date startDate = new Date();
		Date endDate = new Date();
		String quest = req.getParameter("questions");
		String[] quests = quest.split("|");
		
		
		try
		{
			startDate = (Date) format.parse(req.getParameter("startDate"));
			endDate = (Date) format.parse(req.getParameter("endDate"));
		} catch (ParseException e)
		{
			e.printStackTrace();
		}
		
		int duration = Integer.parseInt(req.getParameter("duration"));
		
		Tests test = DataAccess.Tests().CreateTest(title, subjectId, startDate, endDate, duration);
		
		for (String q : quests)
		{
			System.out.println(q);
			if(!q.equals("")&& !q.equals("|"))
			{
				int questionId = Integer.parseInt(q);
				DataAccess.Questions().AddTestQuestions(questionId, test.getId());
			}
		}
		rep.sendRedirect("/Java2015Project/Teacher");
	}
	
	private void createQuestion(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException
	{
		Users user = (Users)req.getSession().getAttribute("user");
		Questions question = new Questions();
		int categoryId = Integer.parseInt(req.getParameter("Categorie"));
		
		for(Categories c : categories)
		{
			if(c.getId() == categoryId)
			{
				question.setCategories(c);
			}
		}
		question.setContent(req.getParameter("Question"));
		question.setOwnerId(user.getId());
		question.setPonderation(new BigDecimal(req.getParameter("Points").replaceAll(",", ".")));
		
		Set<QuestionAnswers> questionAnswers = new HashSet<QuestionAnswers>();		
		int i = 0;
		while(req.getParameter("Answer" + i) != null)
		{
			if(req.getParameter("Answer" + i) != null && !req.getParameter("Answer" + i).isEmpty())
			{
				Answers answer = new Answers(0, req.getParameter("Answer" + i), 1, (byte)(req.getParameter("AnswerCb" + i) != null ? 1 : 0));
				questionAnswers.add(new QuestionAnswers(0, question, answer));
			}
			i++;
		}		
		question.setQuestionAnswerses(questionAnswers);
		
		DataAccess.Questions().CreateQuestion(question);
		rep.sendRedirect("/Java2015Project/Teacher/Questions");
	}
	
	private void testToStudent(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException
	{
		int id = Integer.parseInt(req.getParameter("id"));
		int promotionId = -1;
		Promotions actualPromo = null;
		List<Users> students = null;
		if(req.getParameter("promotionId") != null)
		{
			promotionId = Integer.parseInt(req.getParameter("promotionId"));
			actualPromo = (Promotions)DataAccess.Promotions().GetPromotion(promotionId);
			students = (List<Users>)DataAccess.Users().GetStudentsByPromo(promotionId);
		}
		
		System.out.println(id);
		Tests test = (Tests)DataAccess.Tests().GetTest(id);
		List<Promotions> promotions = (List<Promotions>)DataAccess.Promotions().GetPromotions();
		System.out.println(test);
		req.setAttribute("Test", test);
		req.setAttribute("Promotions", promotions);
		req.setAttribute("Students", students);
		req.setAttribute("ActualPromo", actualPromo);
		req.setAttribute("Tools", new Tools());
		getServletContext().getRequestDispatcher(getInitParameter("TestToStudentsUrl")).forward(req, rep);
	}
}
