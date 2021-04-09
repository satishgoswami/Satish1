package in.co.rays.ORSproject3.dto;

import java.util.Date;

/**
 * Timetable JavaBean encapsulates Timetable attributes
 * 
 * @author Proxy
 * @version 1.0
 * Copyright (c) Proxy
 * 
 */
public class TimetableDTO extends BaseDTO
{
	/**
	 * subId of Timetable
	 */
	private long subId;
	/**
	 * subName of Timetable
	 */
	private String subName;
	/**
	 * courseId of Timetable
	 */
	private long courseId;
	/**
	 * courseName of Timetable
	 */
	private String courseName;
	/**
	 * semester of Timetable
	 */
	private String semester;
	/**
	 * examDate of Timetable
	 */
	private Date examDate;
	/**
	 * examTime of Timetable
	 */
	private String examTime;
	
	/**
	 * Gets the subId of Timetable.
	 *
	 * @return the subId of Timetable
	 */
	public long getSubId() {
		return subId;
	}
	
	/**
	 * Sets the subId of Timetable.
	 *
	 * @param subId the new subId of Timetable
	 */
	public void setSubId(long subId) {
		this.subId = subId;
	}
	
	/**
	 * Gets the subName of Timetable.
	 *
	 * @return the subName of Timetable
	 */
	public String getSubName() {
		return subName;
	}
	
	/**
	 * Sets the subName of Timetable.
	 *
	 * @param subName the new subName of Timetable
	 */
	public void setSubName(String subName) {
		this.subName = subName;
	}
	
	/**
	 * Gets the courseId of Timetable.
	 *
	 * @return the courseId of Timetable
	 */
	public long getCourseId() {
		return courseId;
	}
	
	/**
	 * Sets the courseId of Timetable.
	 *
	 * @param courseId the new courseId of Timetable
	 */
	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}
	
	/**
	 * Gets the courseName of Timetable.
	 *
	 * @return the courseName of Timetable
	 */
	public String getCourseName() {
		return courseName;
	}
	
	/**
	 * Sets the courseName of Timetable.
	 *
	 * @param courseName the new courseName of Timetable
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	/**
	 * Gets the semester of Timetable.
	 *
	 * @return the semester of Timetable
	 */
	public String getSemester() {
		return semester;
	}
	
	/**
	 * Sets the semester of Timetable.
	 *
	 * @param semester the new semester of Timetable
	 */
	public void setSemester(String semester) {
		this.semester = semester;
	}
	
	/**
	 * Gets the examDate of Timetable.
	 *
	 * @return the examDate of Timetable
	 */
	public Date getExamDate() {
		return examDate;
	}
	
	/**
	 * Sets the examDate of Timetable.
	 *
	 * @param examDate the new examDate of Timetable
	 */
	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}
	
	/**
	 * Gets the examTime of Timetable.
	 *
	 * @return the examTime of Timetable
	 */
	public String getExamTime() {
		return examTime;
	}
	
	/**
	 * Sets the examTime of Timetable.
	 *
	 * @param examTime the new examTime of Timetable
	 */
	public void setExamTime(String examTime) {
		this.examTime = examTime;
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
		return subName;
	}

	
	
	
}
