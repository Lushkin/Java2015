package hibernate;

import hibernate.base.BaseUserSubjects;

/**
 * This is the object class that relates to the UserSubjects table.
 * Any customizations belong here.
 */
public class UserSubjects extends BaseUserSubjects {

/*[CONSTRUCTOR MARKER BEGIN]*/
	public UserSubjects () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public UserSubjects (java.lang.Integer _id) {
		super(_id);
	}

	/**
	 * Constructor for required fields
	 */
	public UserSubjects (
		java.lang.Integer _id,
		hibernate.Users _user,
		hibernate.Subjects _subject) {

		super (
			_id,
			_user,
			_subject);
	}

/*[CONSTRUCTOR MARKER END]*/
}