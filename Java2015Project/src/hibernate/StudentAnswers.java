package hibernate;

import hibernate.base.BaseStudentAnswers;

/**
 * This is the object class that relates to the StudentAnswers table.
 * Any customizations belong here.
 */
public class StudentAnswers extends BaseStudentAnswers {

/*[CONSTRUCTOR MARKER BEGIN]*/
	public StudentAnswers () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public StudentAnswers (java.lang.Integer _id) {
		super(_id);
	}

	/**
	 * Constructor for required fields
	 */
	public StudentAnswers (
		java.lang.Integer _id,
		hibernate.Users _student,
		hibernate.Tests _test,
		hibernate.Answers _answer,
		boolean _isChecked) {

		super (
			_id,
			_student,
			_test,
			_answer,
			_isChecked);
	}

/*[CONSTRUCTOR MARKER END]*/
}