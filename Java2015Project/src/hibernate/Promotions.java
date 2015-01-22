package hibernate;

import hibernate.base.BasePromotions;

/**
 * This is the object class that relates to the Promotions table.
 * Any customizations belong here.
 */
public class Promotions extends BasePromotions {

/*[CONSTRUCTOR MARKER BEGIN]*/
	public Promotions () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Promotions (java.lang.Integer _id) {
		super(_id);
	}

	/**
	 * Constructor for required fields
	 */
	public Promotions (
		java.lang.Integer _id,
		java.lang.String _name) {

		super (
			_id,
			_name);
	}

/*[CONSTRUCTOR MARKER END]*/
}