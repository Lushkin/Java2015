package hibernate;

import hibernate.base.BaseAnswers;

/**
 * This is the object class that relates to the Answers table.
 * Any customizations belong here.
 */
public class Answers extends BaseAnswers {

/*[CONSTRUCTOR MARKER BEGIN]*/
	public Answers () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Answers (java.lang.Integer _id) {
		super(_id);
	}

	/**
	 * Constructor for required fields
	 */
	public Answers (
		java.lang.Integer _id,
		java.lang.String _value,
		java.lang.Integer _type,
		boolean _isCorrect) {

		super (
			_id,
			_value,
			_type,
			_isCorrect);
	}

/*[CONSTRUCTOR MARKER END]*/
}