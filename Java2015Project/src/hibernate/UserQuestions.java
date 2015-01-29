package hibernate;

import hibernate.base.BaseUserQuestions;

/**
 * This is the object class that relates to the UserQuestions table.
 * Any customizations belong here.
 */
public class UserQuestions extends BaseUserQuestions {

/*[CONSTRUCTOR MARKER BEGIN]*/
	public UserQuestions () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public UserQuestions (java.lang.Integer _id) {
		super(_id);
	}

	/**
	 * Constructor for required fields
	 */
	public UserQuestions (
		java.lang.Integer _id,
		hibernate.Users _user,
		hibernate.Questions _question) {

		super (
			_id,
			_user,
			_question);
	}

/*[CONSTRUCTOR MARKER END]*/
}