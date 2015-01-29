package hibernate;

import hibernate.base.BaseSubjects;

/**
 * This is the object class that relates to the Subjects table.
 * Any customizations belong here.
 */
public class Subjects extends BaseSubjects {

/*[CONSTRUCTOR MARKER BEGIN]*/
	public Subjects () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Subjects (java.lang.Integer _id) {
		super(_id);
	}

	/**
	 * Constructor for required fields
	 */
	public Subjects (
		java.lang.Integer _id,
		java.lang.String _name) {

		super (
			_id,
			_name);
	}

/*[CONSTRUCTOR MARKER END]*/
}