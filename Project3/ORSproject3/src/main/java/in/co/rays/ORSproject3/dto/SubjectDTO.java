package in.co.rays.ORSproject3.dto;

/**
 * Subject JavaBean encapsulates Subject attributes.
 *
 * @author Proxy
 * @version 1.0 Copyright (c) Proxy
 */
public class SubjectDTO extends BaseDTO {
	
	
	/** Name of Subject. */
	private String subjectname;
	
	/** Description of Subject. */
	private String Description;
	
	/** courseId of Subject. */
	private long courseId;
	
	/** subjectId of Subject. */
	private long subjectId;

	/** courseName of Subject. */
	private String courseName;

	
	/**
	 * Gets the subject name.
	 *
	 * @return the subject name
	 */
	public String getSubjectName() {
		return subjectname;
	}

	/**
	 * Sets the subject name.
	 *
	 * @param subjectname the new subject name
	 */
	public void setSubjectName(String subjectname) {
		this.subjectname = subjectname;
	}

	/**
	 * Gets the subjectId of Subject.
	 *
	 * @return the subjectId of Subject
	 */
	public long getSubjectId() {
		return subjectId;
	}

	/**
	 * Sets the subjectId of Subject.
	 *
	 * @param subjectId the new subjectId of Subject
	 */
	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return Description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		Description = description;
	}

	/**
	 * Gets the courseId of Subject.
	 *
	 * @return the courseId of Subject
	 */
	public long getCourseId() {
		return courseId;
	}

	/**
	 * Sets the courseId of Subject.
	 *
	 * @param courseId the new courseId of Subject
	 */
	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	/**
	 * Gets the courseName of Subject.
	 *
	 * @return the courseName of Subject
	 */
	public String getCourseName() {
		return courseName;
	}

	/**
	 * Sets the courseName of Subject.
	 *
	 * @param courseName the new courseName of Subject
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}


	
	/* (non-Javadoc)
	 * @see in.co.rays.project4.bean.DropdownListBean#getKey()
	 */
	public String getKey() {
		return id+"";
	}

	/* (non-Javadoc)
	 * @see in.co.rays.project4.bean.DropdownListBean#getvalue()
	 */

	public String getValue() {
		return subjectname;
	}

}
