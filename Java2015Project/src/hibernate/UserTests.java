package hibernate;

import hibernate.base.BaseUserTests;

/**
 * This is the object class that relates to the UserTests table.
 * Any customizations belong here.
 */
public class UserTests extends BaseUserTests {

/*[CONSTRUCTOR MARKER BEGIN]*/
	public UserTests () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public UserTests (java.lang.Integer _id) {
		super(_id);
	}

	/**
	 * Constructor for required fields
	 */
	public UserTests (
		java.lang.Integer _id,
		hibernate.Tests _test,
		hibernate.Users _user,
		java.lang.Integer _state) {

		super (
			_id,
			_test,
			_user,
			_state);
	}

/*[CONSTRUCTOR MARKER END]*/
}