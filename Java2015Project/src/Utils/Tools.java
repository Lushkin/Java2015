package Utils;

import hibernate.java.Questions;
import hibernate.java.TestQuestions;

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
}
