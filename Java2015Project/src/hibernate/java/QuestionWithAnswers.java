package hibernate.java;

import java.util.ArrayList;
import java.util.List;

public class QuestionWithAnswers
{
	private Questions question;
	private List<StudentAnswers> answers;
	
	public QuestionWithAnswers()
	{
		answers = new ArrayList<StudentAnswers>();
	}

	public Questions getQuestion()
	{
		return question;
	}

	public void setQuestion(Questions question)
	{
		this.question = question;
	}

	public List<StudentAnswers> getAnswers()
	{
		return answers;
	}
	
	public void AddAnswer(StudentAnswers answer)
	{
		answers.add(answer);
	}
}
