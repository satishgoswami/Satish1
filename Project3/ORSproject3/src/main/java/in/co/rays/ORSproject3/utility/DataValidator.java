package in.co.rays.ORSproject3.utility;

import java.util.Date;
import in.co.rays.ORSproject3.utility.DataUtility;
/**
	 * This class validates input data
	 * 
	 * @author SunilOS
	 * @version 1.0
	 * @Copyright (c) SunilOS
	 */

	public class DataValidator {

	    /**
	     * Checks if value is Null
	     * 
	     * @param val
	     * @return
	     */
	    public static boolean isNull(String val) {
	        if (val == null || val.trim().length() == 0) {
	            return true;
	        } else {
	            return false;
	        }
	    }

	    /**
	     * Checks if value is NOT Null
	     * 
	     * @param val
	     * @return
	     */
	    public static boolean isNotNull(String val) {
	        return !isNull(val);
	    }

	    /**
	     * Checks if value is an Integer
	     * 
	     * @param val
	     * @return
	     */

	    public static boolean isInteger(String val) {

	        if (isNotNull(val)) {
	            try {
	                int i = Integer.parseInt(val);
	                
	                return true;
	            } catch (NumberFormatException e) {
	                return false;
	            }

	        } else {
	            return false;
	        }
	    }

	    /**
	     * Checks if value is Long
	     * 
	     * @param val
	     * @return
	     */
	    public static boolean isLong(String val) {
	        if (isNotNull(val)) {
	            try {
	                long i = Long.parseLong(val);
	                return true;
	            } catch (NumberFormatException e) {
	                return false;
	            }

	        } else {
	            return false;
	        }
	    }

	    /**
	     * Checks if value is valid Email ID
	     * 
	     * @param val
	     * @return
	     */
	    public static boolean isEmail(String val) {

	        String emailreg = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	        if (isNotNull(val)) {
	            try {
	                return val.matches(emailreg);
	            } catch (NumberFormatException e) {
	                return false;
	            }

	        } else {
	            return false;
	        }
	    }

	    /**
	     * Checks if value is Date
	     * 
	     * @param val
	     * @return
	     */
	    public static boolean isDate(String val) {

	        Date d = null;
	        if (isNotNull(val)) {
	            d = DataUtility.getDate(val);
	        }
	        return d != null;
	    }
	    
	    /**
		 * Checks if value is valid Name
		 * 
		 * @param val
		 * @return
		 */
	    
	    public static boolean isName(String name)
	    {
	    	String namer = "^[a-zA-Z_-]+$";
			if (isNotNull(name) && name.matches(namer)) {

				return true;
			} else {
				return false;
			}
		}

	    /**
		 * Checks if value is valid Name
		 * 
		 * @param val
		 * @return
		 */
		public static boolean isName1(String name) { // my method created

			String namere = "^[^-\\s][\\p{L} .']+$";

			if (isNotNull(name) && name.matches(namere)) {

				return true;
			} else {
				return false;
			}
		}

		/**
		 * Checks if value is valid Password
		 * 
		 * @param val
		 * @return
		 */
		public static boolean isPassword(String pass) { // my method created

			System.out.println("validate pass");
			String passreg = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20})";
			// String passreg="^[0-9a-zA-Z]{5}$";
			// String spass = pass.trim();
			// int checkIndex = spass.indexOf(" ");
			// checkIndex==-1
			
			if (isNotNull(pass) && pass.matches(passreg)) {
				System.out.println("true");
				return true;
			}

			else {
				return false;
			}
		}
	/**
	 * Checks if value is valid Roll No
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isRollNo(String roll) { // my method created
		// System.out.println("rollno");
		// String rollreg =
		// "((?=.*\\d).{1,4}(?=.*[A-Z]).{1,2}(?=.*\\d).{1,6})$";
		// String rollreg="^[a-zA-z\\s]+$";
		String rollreg = "^[0-9]{3}[A-Za-z]{2}[0-9]{2,6}$";
		// String sroll = roll.trim();
		if (DataValidator.isNotNull(roll)) {

			boolean check = roll.matches(rollreg);
			System.out.println(check);
			return check;
		}

		else {

			return false;
		}
	}

	/**
	 * Checks if value is valid Address
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isAddress(String pass) { // my method created

		System.out.println("validate pass");
	//	String passreg = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,20})";

//		String passreg = "^[0-9a-zA-Z/s,-]+$";
		String passreg = "^[0-9a-zA-Z\\s,-]+$";
		// String passreg="^[0-9a-zA-Z]{5}$";
		// String spass = pass.trim();
		// int checkIndex = spass.indexOf(" ");
		// checkIndex==-1
		if (isNotNull(pass) && pass.matches(passreg)) {
			System.out.println("tttttttttttttttttttttttrue");
			return true;
		}

		else {
			return false;
		}
	}
	/**
	 * Checks if value is valid Age
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isValidAge(String val) {

		Date today = new Date();

		System.out.println(today);

		Date enterDate = DataUtility.getDate(val);

		System.out.println(enterDate);

		int age = today.getYear() - enterDate.getYear();
		System.out.println("age=" + age);

		if (age > 18 && isNotNull(val)) {
			return true;
		} else {
			return false;
		}
	}/**
	 * Checks if value is valid Mobile No
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isMobileNo(String mobile) {

		String mobilereg = "^[6-9][0-9]{9}$";

		if (isNotNull(mobile) && mobile.matches(mobilereg)) {

			return true;
		} else {
			return false;
		}

	}

	/**
	 * Checks if value is valid PhoneNo
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isPhoneNo(String phone) {

		String mobilereg = "^[0-9][0-9]{10}$";

		if (isNotNull(phone) && phone.matches(mobilereg)) {

			return true;
		} else {
			return false;
		}

	}




	    	

	    /**
	     * Test above methods
	     * 
	     * @param args
	     */
	    public static void main(String[] args) {

	        /*System.out.println("It is not null-" + isNotNull("xyz"));
	        System.out.println("It is not null-" + isNotNull("123"));
	        System.out.println("It is Not Null- " + isNotNull(null));
	        System.out.println("It is null-" + isNull("123"));
	        System.out.println("It is null-" + isNull(null));
	        System.out.println("Is this is an Int-" + isInteger(null));
	        System.out.println("Is this is an Int-" + isInteger("1xy"));
	        System.out.println("Is this is an Int-" + isInteger("123"));
*/	        System.out.println(isAddress("Indore"));
	    }


}
