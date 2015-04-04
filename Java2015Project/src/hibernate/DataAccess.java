package hibernate;

import hibernate.dao.*;

public class DataAccess
{
	private static UsersDAO users;
	
	public static UsersDAO Users()
	{
		if (users == null)
			users = new UsersDAO();
		return users;
	}
	
	private static PromotionsDAO promotions;
	
	public static PromotionsDAO Promotions()
	{
		if (promotions == null)
			promotions = new PromotionsDAO();
		return promotions;
	}
	
	private static TestsDAO tests;
	
	public static TestsDAO Tests()
	{
		if (tests == null)
			tests = new TestsDAO();
		return tests;
	}

	private static QuestionsDAO questions;

	public static QuestionsDAO Questions()
	{
		if (questions == null)
			questions = new QuestionsDAO();
		return questions;
	}
	
	private static SubjectDAO subjects;

	public static SubjectDAO Subjects()
	{
		if (subjects == null)
			subjects = new SubjectDAO();
		return subjects;
	}
}
