package Utils;

import hibernate.java.Questions;
import hibernate.java.TestQuestions;
import hibernate.java.Tests;
import hibernate.java.UserTests;
import hibernate.java.Users;

import java.util.List;

public class Tools
{
	public Tools()
	{
	}
	
	public boolean TestQuestionsContains(List<Questions> testQuestions, Questions testQuestion)
	{
		boolean Exist = false;
		for (Questions tq : testQuestions)
		{
			if(tq.getId() == testQuestion.getId())
				Exist = true;
		}
		return Exist;
	}
	
	public boolean UserTestContains(Users user, Tests test)
	{
		if(user.getUserTestses() == null)
			return false;
		boolean Exists = false;
		for(UserTests ut : user.getUserTestses())
		{
			if(ut.getTests().getId() == test.getId())
				Exists = true;
		}
		return Exists;
	}
}
