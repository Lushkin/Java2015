package hibernate;

import hibernate.base.BaseTeacherPromotions;

/**
 * This is the object class that relates to the TeacherPromotions table.
 * Any customizations belong here.
 */
public class TeacherPromotions extends BaseTeacherPromotions {

/*[CONSTRUCTOR MARKER BEGIN]*/
	public TeacherPromotions () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public TeacherPromotions (java.lang.Integer _id) {
		super(_id);
	}

	/**
	 * Constructor for required fields
	 */
	public TeacherPromotions (
		java.lang.Integer _id,
		hibernate.Users _teacher,
		hibernate.Promotions _promotion) {

		super (
			_id,
			_teacher,
			_promotion);
	}

/*[CONSTRUCTOR MARKER END]*/
}