package hibernate.base;

import java.io.Serializable;


/**
 * This class has been automatically generated by Hibernate Synchronizer.
 * For more information or documentation, visit The Hibernate Synchronizer page
 * at http://www.binamics.com/hibernatesync or contact Joe Hudson at joe@binamics.com.
 *
 * This is an object that contains data related to the UserQuestions table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="UserQuestions"
 */
public abstract class BaseUserQuestions  implements Serializable {

	public static String PROP_USER = "User";
	public static String PROP_QUESTION = "Question";
	public static String PROP_ID = "Id";


	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer _id;

	// many to one
	private hibernate.Users _user;
	private hibernate.Questions _question;


	// constructors
	public BaseUserQuestions () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseUserQuestions (java.lang.Integer _id) {
		this.setId(_id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseUserQuestions (
		java.lang.Integer _id,
		hibernate.Users _user,
		hibernate.Questions _question) {

		this.setId(_id);
		this.setUser(_user);
		this.setQuestion(_question);
		initialize();
	}

	protected void initialize () {}



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="Id"
     */
	public java.lang.Integer getId () {
		return _id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param _id the new ID
	 */
	public void setId (java.lang.Integer _id) {
		this._id = _id;
		this.hashCode = Integer.MIN_VALUE;
	}


	/**
     * @hibernate.property
     *  column=UserId
	 * not-null=true
	 */
	public hibernate.Users getUser () {
		return this._user;
	}

	/**
	 * Set the value related to the column: UserId
	 * @param _user the UserId value
	 */
	public void setUser (hibernate.Users _user) {
		this._user = _user;
	}


	/**
     * @hibernate.property
     *  column=QuestionId
	 * not-null=true
	 */
	public hibernate.Questions getQuestion () {
		return this._question;
	}

	/**
	 * Set the value related to the column: QuestionId
	 * @param _question the QuestionId value
	 */
	public void setQuestion (hibernate.Questions _question) {
		this._question = _question;
	}


	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof hibernate.base.BaseUserQuestions)) return false;
		else {
			hibernate.base.BaseUserQuestions mObj = (hibernate.base.BaseUserQuestions) obj;
			if (null == this.getId() || null == mObj.getId()) return false;
			else return (this.getId().equals(mObj.getId()));
		}
	}


	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}

}