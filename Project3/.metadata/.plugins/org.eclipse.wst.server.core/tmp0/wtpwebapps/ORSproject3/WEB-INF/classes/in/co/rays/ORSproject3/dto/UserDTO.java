package in.co.rays.ORSproject3.dto;

import java.sql.Timestamp;
import java.util.Date;

/**
 * User DTO classes
 *
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 *
 */

public class UserDTO extends BaseDTO {

	/**
	 * Lock Active constant for User
	 */
	public static final String ACTIVE = "Active";
	/**
	 * Lock Inactive constant for User
	 */
	public static final String INACTIVE = "Inactive";

	/** First Name of User. */
	private String firstName;
	/** Last Name of User. */
	private String lastName;
	/** Login of User. */

	private String login;

	/** password of User. */
	private String password;
	/** Confirm password of User. */
	private String confirmPassword;

	/** DOB of User. */
	private Date dob;
	
	/** address of User. */
	private String address;
	/** Mobile No of User. */
	private String mobileNo;
	/** role id of User. */
	private long roleId;
	/** Number of unsuccessful login attempt. */

	/** gender of User. */
	private String gender;/*
	*//** User Lock. *//*

	private String lock;
*/	/**
	 * IP Address of User from where User was registred.
	 */

	private String roleName;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	/*public String getLock() {
		return lock;
	}

	public void setLock(String lock) {
		this.lock = lock;
	}*/

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see in.co.rays.project4.bean.DropdownListBean#getKey()
	 */
	public String getKey() {
		return id + "";
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.co.rays.project4.bean.DropdownListBean#getvalue()
	 */
	public String getValue() {
		return firstName + "" + lastName;
	}

}
