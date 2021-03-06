package hibernate.java;
// Generated Mar 12, 2015 7:14:43 PM by Hibernate Tools 4.0.0

/**
 * UserTests generated by hbm2java
 */
public class TestQuestions implements java.io.Serializable
{
	private static final long serialVersionUID = -8501483268690252731L;
	private int id;
	private Tests tests;
	private Questions questions;

	public TestQuestions()
	{
	}

	public TestQuestions(int id, Tests tests, Questions questions)
	{
		this.id = id;
		this.tests = tests;
		this.questions = questions;
	}

	public int getId()
	{
		return this.id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public Tests getTests()
	{
		return this.tests;
	}

	public void setTests(Tests tests)
	{
		this.tests = tests;
	}

	public Questions getQuestions()
	{
		return this.questions;
	}

	public void setQuestions(Questions question)
	{
		this.questions = question;
	}
	
	public boolean equals(TestQuestions o)
	{
		return o.getId() == this.getId();
	}
	
}
