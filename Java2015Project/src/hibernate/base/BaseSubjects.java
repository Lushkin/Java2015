package hibernate.base;

import java.io.Serializable;


/**
 * This class has been automatically generated by Hibernate Synchronizer.
 * For more information or documentation, visit The Hibernate Synchronizer page
 * at http://www.binamics.com/hibernatesync or contact Joe Hudson at joe@binamics.com.
 *
 * This is an object that contains data related to the Subjects table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="Subjects"
 */
public abstract class BaseSubjects  implements Serializable {

	public static String PROP_NAME = "Name";
	public static String PROP_ID = "Id";


	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer _id;

	// fields
	private java.lang.String _name;

	// collections
	private java.util.Set _userSubjectsSet;
	private java.util.Set _testsSet;


	// constructors
	public BaseSubjects () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseSubjects (java.lang.Integer _id) {
		this.setId(_id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseSubjects (
		java.lang.Integer _id,
		java.lang.String _name) {

		this.setId(_id);
		this.setName(_name);
		initialize();
	}

	protected void initialize () {}



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="increment"
     *  column="Id"
     */
	public java.lang.Integer getId () {
		return _id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param _id the new ID
	 */
	public void setId (java.lang.Integer _id) {
		this._id = _id;
		this.hashCode = Integer.MIN_VALUE;
	}


	/**
	 * Return the value associated with the column: Name
	 */
	public java.lang.String getName () {
		return _name;
	}

	/**
	 * Set the value related to the column: Name
	 * @param _name the Name value
	 */
	public void setName (java.lang.String _name) {
		this._name = _name;
	}


	/**
	 * Return the value associated with the column: UserSubjectsSet
	 */
	public java.util.Set getUserSubjectsSet () {
		return this._userSubjectsSet;
	}

	/**
	 * Set the value related to the column: UserSubjectsSet
	 * @param _userSubjectsSet the UserSubjectsSet value
	 */
	public void setUserSubjectsSet (java.util.Set _userSubjectsSet) {
		this._userSubjectsSet = _userSubjectsSet;
	}
	
	public void addToUserSubjectsSet (Object obj) {
		if (null == this._userSubjectsSet) this._userSubjectsSet = new java.util.HashSet();
		this._userSubjectsSet.add(obj);
	}



	/**
	 * Return the value associated with the column: TestsSet
	 */
	public java.util.Set getTestsSet () {
		return this._testsSet;
	}

	/**
	 * Set the value related to the column: TestsSet
	 * @param _testsSet the TestsSet value
	 */
	public void setTestsSet (java.util.Set _testsSet) {
		this._testsSet = _testsSet;
	}
	
	public void addToTestsSet (Object obj) {
		if (null == this._testsSet) this._testsSet = new java.util.HashSet();
		this._testsSet.add(obj);
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof hibernate.base.BaseSubjects)) return false;
		else {
			hibernate.base.BaseSubjects mObj = (hibernate.base.BaseSubjects) obj;
			if (null == this.getId() || null == mObj.getId()) return false;
			else return (this.getId().equals(mObj.getId()));
		}
	}


	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}

}