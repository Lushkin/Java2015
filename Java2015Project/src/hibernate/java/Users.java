package hibernate.java;
// Generated Mar 12, 2015 7:14:43 PM by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Users generated by hbm2java
 */
public class Users implements java.io.Serializable
{
	private static final long serialVersionUID = 6925912329060329285L;
	private int id;
	private Promotions promotions;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private String email;
	private String password;
	private int role;
	private Set<Tests> testses = new HashSet<Tests>(0);
	private Set<UserTests> userTestses = new HashSet<UserTests>(0);
	private Set<UserSubjects> userSubjectses = new HashSet<UserSubjects>(0);
	private Set<UserQuestions> userQuestionses = new HashSet<UserQuestions>(0);
	private Set<TeacherPromotions> teacherPromotionses = new HashSet<TeacherPromotions>(
			0);
	private Set<StudentAnswers> studentAnswerses = new HashSet<StudentAnswers>(
			0);

	public Users()
	{
	}

	public Users(int id, String firstName, String lastName,
			Date birthDate, String email, String password, int role)
	{
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public Users(int id, Promotions promotions, String firstName,
			String lastName, Date birthDate, String email,
			String password, int role, Set<Tests> testses,
			Set<UserTests> userTestses, Set<UserSubjects> userSubjectses,
			Set<UserQuestions> userQuestionses,
			Set<TeacherPromotions> teacherPromotionses,
			Set<StudentAnswers> studentAnswerses)
	{
		this.id = id;
		this.promotions = promotions;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.email = email;
		this.password = password;
		this.role = role;
		this.testses = testses;
		this.userTestses = userTestses;
		this.userSubjectses = userSubjectses;
		this.userQuestionses = userQuestionses;
		this.teacherPromotionses = teacherPromotionses;
		this.studentAnswerses = studentAnswerses;
	}

	public int getId()
	{
		return this.id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public Promotions getPromotions()
	{
		return this.promotions;
	}

	public void setPromotions(Promotions promotions)
	{
		this.promotions = promotions;
	}

	public String getFirstName()
	{
		return this.firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return this.lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public Date getBirthDate()
	{
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate)
	{
		this.birthDate = birthDate;
	}

	public String getEmail()
	{
		return this.email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPassword()
	{
		return this.password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public int getRole()
	{
		return this.role;
	}

	public void setRole(int role)
	{
		this.role = role;
	}

	public Set<Tests> getTestses()
	{
		return this.testses;
	}

	public void setTestses(Set<Tests> testses)
	{
		this.testses = testses;
	}

	public Set<UserTests> getUserTestses()
	{
		return this.userTestses;
	}

	public void setUserTestses(Set<UserTests> userTestses)
	{
		this.userTestses = userTestses;
	}

	public Set<UserSubjects> getUserSubjectses()
	{
		return this.userSubjectses;
	}

	public void setUserSubjectses(Set<UserSubjects> userSubjectses)
	{
		this.userSubjectses = userSubjectses;
	}

	public Set<UserQuestions> getUserQuestionses()
	{
		return this.userQuestionses;
	}

	public void setUserQuestionses(Set<UserQuestions> userQuestionses)
	{
		this.userQuestionses = userQuestionses;
	}

	public Set<TeacherPromotions> getTeacherPromotionses()
	{
		return this.teacherPromotionses;
	}

	public void setTeacherPromotionses(
			Set<TeacherPromotions> teacherPromotionses)
	{
		this.teacherPromotionses = teacherPromotionses;
	}

	public Set<StudentAnswers> getStudentAnswerses()
	{
		return this.studentAnswerses;
	}

	public void setStudentAnswerses(Set<StudentAnswers> studentAnswerses)
	{
		this.studentAnswerses = studentAnswerses;
	}

}
