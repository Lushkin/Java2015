package hibernate;

import hibernate.base.BaseUsers;

/**
 * This is the object class that relates to the Users table.
 * Any customizations belong here.
 */
public class Users extends BaseUsers {

/*[CONSTRUCTOR MARKER BEGIN]*/
	public Users () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Users (java.lang.Integer _id) {
		super(_id);
	}

	/**
	 * Constructor for required fields
	 */
	public Users (
		java.lang.Integer _id,
		hibernate.Promotions _promotion,
		java.lang.String _email,
		java.lang.String _password,
		java.lang.Integer _role,
		java.lang.String _firstName,
		java.lang.String _lastName,
		java.util.Date _birthDate) {

		super (
			_id,
			_promotion,
			_email,
			_password,
			_role,
			_firstName,
			_lastName,
			_birthDate);
	}

/*[CONSTRUCTOR MARKER END]*/
}