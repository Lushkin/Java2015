package hibernate;

import hibernate.base.BaseQuestions;

/**
 * This is the object class that relates to the Questions table.
 * Any customizations belong here.
 */
public class Questions extends BaseQuestions {

/*[CONSTRUCTOR MARKER BEGIN]*/
	public Questions () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Questions (java.lang.Integer _id) {
		super(_id);
	}

	/**
	 * Constructor for required fields
	 */
	public Questions (
		java.lang.Integer _id,
		hibernate.Categories _category,
		java.lang.String _content,
		java.math.BigDecimal _ponderation) {

		super (
			_id,
			_category,
			_content,
			_ponderation);
	}

/*[CONSTRUCTOR MARKER END]*/
}