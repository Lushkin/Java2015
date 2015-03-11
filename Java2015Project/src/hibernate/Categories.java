package hibernate;

import hibernate.base.BaseCategories;

/**
 * This is the object class that relates to the Categories table.
 * Any customizations belong here.
 */
public class Categories extends BaseCategories {

/*[CONSTRUCTOR MARKER BEGIN]*/
	public Categories () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Categories (java.lang.Integer _id) {
		super(_id);
	}

	/**
	 * Constructor for required fields
	 */
	public Categories (
		java.lang.Integer _id,
		java.lang.String _name) {

		super (
			_id,
			_name);
	}

/*[CONSTRUCTOR MARKER END]*/
}