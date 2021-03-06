package hibernate.java;
// Generated Mar 12, 2015 7:14:43 PM by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;

/**
 * Categories generated by hbm2java
 */
public class Categories implements java.io.Serializable
{
	private static final long serialVersionUID = 7575401226510193031L;
	private int id;
	private String name;
	private Set<Questions> questionses = new HashSet<Questions>(0);

	public Categories()
	{
	}

	public Categories(int id, String name)
	{
		this.id = id;
		this.name = name;
	}

	public Categories(int id, String name, Set<Questions> questionses)
	{
		this.id = id;
		this.name = name;
		this.questionses = questionses;
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

	public Set<Questions> getQuestionses()
	{
		return this.questionses;
	}

	public void setQuestionses(Set<Questions> questionses)
	{
		this.questionses = questionses;
	}

}
