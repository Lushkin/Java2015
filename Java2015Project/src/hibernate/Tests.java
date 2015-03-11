package hibernate;

import hibernate.base.BaseTests;

/**
 * This is the object class that relates to the Tests table.
 * Any customizations belong here.
 */
public class Tests extends BaseTests {

/*[CONSTRUCTOR MARKER BEGIN]*/
	public Tests () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Tests (java.lang.Integer _id) {
		super(_id);
	}

	/**
	 * Constructor for required fields
	 */
	public Tests (
		java.lang.Integer _id,
		hibernate.Users _author,
		hibernate.Subjects _subject,
		boolean _isValidated,
		java.util.Date _endDate,
		java.util.Date _startDate,
		java.lang.String _title) {

		super (
			_id,
			_author,
			_subject,
			_isValidated,
			_endDate,
			_startDate,
			_title);
	}

/*[CONSTRUCTOR MARKER END]*/
}