package hibernate.java;
// Generated Mar 12, 2015 7:14:43 PM by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;

/**
 * Answers generated by hbm2java
 */
public class Answers implements java.io.Serializable
{
	private static final long serialVersionUID = 6395697768698731670L;
	private int id;
	private String value;
	private int type;
	private byte isCorrect;
	private Set<StudentAnswers> studentAnswerses = new HashSet<StudentAnswers>(
			0);
	private Set<QuestionAnswers> questionAnswerses = new HashSet<QuestionAnswers>(
			0);

	public Answers()
	{
	}

	public Answers(int id, String value, int type, byte isCorrect)
	{
		this.id = id;
		this.value = value;
		this.type = type;
		this.isCorrect = isCorrect;
	}

	public Answers(int id, String value, int type, byte isCorrect,
			Set<StudentAnswers> studentAnswerses,
			Set<QuestionAnswers> questionAnswerses)
	{
		this.id = id;
		this.value = value;
		this.type = type;
		this.isCorrect = isCorrect;
		this.studentAnswerses = studentAnswerses;
		this.questionAnswerses = questionAnswerses;
	}

	public int getId()
	{
		return this.id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getValue()
	{
		return this.value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	public int getType()
	{
		return this.type;
	}

	public void setType(int type)
	{
		this.type = type;
	}

	public byte getIsCorrect()
	{
		return this.isCorrect;
	}

	public void setIsCorrect(byte isCorrect)
	{
		this.isCorrect = isCorrect;
	}

	public Set<StudentAnswers> getStudentAnswerses()
	{
		return this.studentAnswerses;
	}

	public void setStudentAnswerses(Set<StudentAnswers> studentAnswerses)
	{
		this.studentAnswerses = studentAnswerses;
	}

	public Set<QuestionAnswers> getQuestionAnswerses()
	{
		return this.questionAnswerses;
	}

	public void setQuestionAnswerses(Set<QuestionAnswers> questionAnswerses)
	{
		this.questionAnswerses = questionAnswerses;
	}

}
