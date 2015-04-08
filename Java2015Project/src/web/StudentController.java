package web;

import hibernate.DataAccess;
import hibernate.java.Answers;
import hibernate.java.QuestionAnswers;
import hibernate.java.Questions;
import hibernate.java.StudentAnswers;
import hibernate.java.Tests;
import hibernate.java.UserTests;
import hibernate.java.Users;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

	public void service(HttpServletRequest req, HttpServletResponse rep)
			throws ServletException, IOException
	{
		if (req.getSession().getAttribute("user") == null
				|| ((Users) req.getSession().getAttribute("user")).getRole() != 3)
		{
			rep.sendRedirect("/Java2015Project/");
			return;
		}
		String action = req.getPathInfo();
		if (action != null)
		{
			if (req.getMethod() == "POST")
			{
				switch (action)
				{
				case "/DoTest":
					saveTest(req, rep);
					break;
				}

			} else
			{
				switch (action)
				{
				case "/DoTest":
					int id = Integer.parseInt(req.getParameter("id"));
					req.setAttribute("Questions", DataAccess.Questions()
							.GetQuestionsByIdTest(id));
					req.setAttribute("Test", DataAccess.Tests().GetTest(id));
					getServletContext().getRequestDispatcher(
							"/WEB-INF/Views/User/Student/DoTest.jsp").forward(
							req, rep);
					break;
				}
			}
		} else
		{
			Users user = (Users) req.getSession().getAttribute("user");
			List<UserTests> pendingTests = DataAccess.Students()
					.GetPendingTests(user.getId());
			req.setAttribute("PendingTests", pendingTests);
			List<UserTests> finishedTests = DataAccess.Students()
					.GetFinishedTests(user.getId());
			req.setAttribute("FinishedTests", finishedTests);
			getServletContext().getRequestDispatcher(url).forward(req, rep);
		}
	}

	private void saveTest(HttpServletRequest req, HttpServletResponse rep)
			throws IOException
	{
		Users user = (Users) req.getSession().getAttribute("user");
		int testId = Integer.parseInt(req.getParameter("id"));
		UserTests userTest = DataAccess.Tests().GetUserTest(user.getId(),
				testId);
		Set<StudentAnswers> studentAnswers = new HashSet<StudentAnswers>();

		Enumeration<String> allParamsNames = req.getParameterNames();
		while (allParamsNames.hasMoreElements())
		{
			String param = allParamsNames.nextElement();
			if (param.contains("Answer-"))
			{
				String[] ids = param.split("-");
				Answers answer = new Answers();
				answer.setId(Integer.parseInt(ids[1]));
				Tests test = new Tests();
				test.setId(testId);
				byte checked = (byte) (req.getParameter(param) != null ? 1 : 0);
				studentAnswers.add(new StudentAnswers(0, answer, user, test,
						checked));
			}
		}

		user.setStudentAnswerses(studentAnswers);
		userTest.setState(1);
		userTest.setMark(calculateMark(testId, studentAnswers));
		// DataAccess.Users().UpdateUser(user);
		// rep.sendRedirect("/Java2015Project/Student");
	}

	private double calculateMark(int testId, Set<StudentAnswers> answers)
	{
		int total = 0;
		int right = 0;
		List<Questions> questions = DataAccess.Questions().GetQuestionsByIdTest(testId);
		for(Questions q : questions)
		{
			total += q.getPonderation().intValue();
			boolean correct = true;
			for(QuestionAnswers qa : q.getQuestionAnswerses())
			{
				for(StudentAnswers a : answers)
				{
					if(qa.getAnswers().getId() == a.getAnswers().getId())
					{
						if(a.getIsChecked() != a.getAnswers().getIsCorrect())
						{
							correct = false;
						}
					}
				}
			}
			
			if(correct)
			{
				right += q.getPonderation().intValue();
			}
		}
		return (right * 20) / total;
	}
}