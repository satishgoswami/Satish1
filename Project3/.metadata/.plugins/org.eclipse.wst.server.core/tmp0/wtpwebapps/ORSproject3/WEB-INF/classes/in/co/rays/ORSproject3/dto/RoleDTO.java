package in.co.rays.ORSproject3.dto;

/**
 * Contains Role data
 *
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 *
 */

public class RoleDTO extends BaseDTO  {

	/**
	 * Predefined Role constants
	 */
	
	public static final int ADMIN = 1;
	
	
	/** The Constant FACULTY. */
	public static final int FACULTY = 3;
	
	/** The Constant STUDENT. */
	public static final int STUDENT = 2;
	
	
	/** The Constant COLLEGE. */
	public static final int COLLEGE= 4;
	
	/** The Constant KIOSK. */
	public static final int KIOSK = 5;
	
    /**
     * Role Name
     */

    private String name;

    /**
     * Role Description
     */
    private String description;

    /**
     * accessor
     */

    public String getKey() {
        return id + "";
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getValue() {
        return name;
    }

}