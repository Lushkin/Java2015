package hibernate;

import hibernate.base.BaseQuestionAnswers;

/**
 * This is the object class that relates to the QuestionAnswers table.
 * Any customizations belong here.
 */
public class QuestionAnswers extends BaseQuestionAnswers {

/*[CONSTRUCTOR MARKER BEGIN]*/
	public QuestionAnswers () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public QuestionAnswers (java.lang.Integer _id) {
		super(_id);
	}

	/**
	 * Constructor for required fields
	 */
	public QuestionAnswers (
		java.lang.Integer _id,
		hibernate.Questions _question,
		hibernate.Answers _answer) {

		super (
			_id,
			_question,
			_answer);
	}

/*[CONSTRUCTOR MARKER END]*/
}