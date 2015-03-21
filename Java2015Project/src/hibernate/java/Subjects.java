package hibernate.java;
// Generated Mar 12, 2015 7:14:43 PM by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;

/**
 * Subjects generated by hbm2java
 */
public class Subjects implements java.io.Serializable
{
	private static final long serialVersionUID = 43159268492446255L;
	private int id;
	private String name;
	private Set<Tests> testses = new HashSet<Tests>(0);
	private Set<UserSubjects> userSubjectses = new HashSet<UserSubjects>(0);

	public Subjects()
	{
	}

	public Subjects(int id, String name)
	{
		this.id = id;
		this.name = name;
	}

	public Subjects(int id, String name, Set<Tests> testses,
			Set<UserSubjects> userSubjectses)
	{
		this.id = id;
		this.name = name;
		this.testses = testses;
		this.userSubjectses = userSubjectses;
	}

	public int getId()
	{
		return this.id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Set<Tests> getTestses()
	{
		return this.testses;
	}

	public void setTestses(Set<Tests> testses)
	{
		this.testses = testses;
	}

	public Set<UserSubjects> getUserSubjectses()
	{
		return this.userSubjectses;
	}

	public void setUserSubjectses(Set<UserSubjects> userSubjectses)
	{
		this.userSubjectses = userSubjectses;
	}

}
