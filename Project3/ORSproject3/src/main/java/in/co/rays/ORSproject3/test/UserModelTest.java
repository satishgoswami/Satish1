package in.co.rays.ORSproject3.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.ORSproject3.dto.UserDTO;
import in.co.rays.ORSproject3.exception.ApplicationException;
import in.co.rays.ORSproject3.exception.DuplicateRecordException;
import in.co.rays.ORSproject3.exception.RecordNotFoundException;
import in.co.rays.ORSproject3.model.ModelFactory;
import in.co.rays.ORSproject3.model.UserModelHibImpl;
import in.co.rays.ORSproject3.model.UserModelInt;
import in.co.rays.ORSproject3.model.UserModelJDBCImpl;

/**
 * User Model Test classes
 * 
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 * 
 */
public class UserModelTest {

	/**
	 * Model object to test
	 */

	public static UserModelInt model = ModelFactory.getInstance().getUserModel();

	/**
	 * Main method to call test methods.
	 * 
	 * @param args
	 * @throws ParseException
	 * @throws DuplicateRecordException
	 * @throws RecordNotFoundException
	 * @throws ApplicationException
	 */
	public static void main(String[] args)
			throws ParseException, ApplicationException, RecordNotFoundException, DuplicateRecordException {
		// testAdd();
		// testDelete();
		// testupdate();
		// testfindByPk();
		//testfindByEmailId();
		// testSearch();
		// testList();
		// testAuthenticate();
		// testchangePassword();
	//	testRegisterUser();
		 testforgetPassword();
	}

	/**
	 * Tests add a User
	 * 
	 * @throws ParseException
	 * @throws ApplicationException
	 * @throws RecordNotFoundException
	 * @throws DuplicateRecordException
	 */
	public static void testAdd()
			throws ParseException, ApplicationException, RecordNotFoundException, DuplicateRecordException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = sdf.parse("11/07/1995"); // util date object

		UserDTO dto = new UserDTO();
		dto.setFirstName("Shadab");
		dto.setLastName("Khan");
		dto.setLogin("khan@gmail.com");
		dto.setPassword("8109295145");
		dto.setDob(d);
		dto.setGender("Male");
		dto.setAddress("Rajwada");
		dto.setMobileNo("8000006783");
		dto.setRoleId(2);
		dto.setRoleName("Student");
		dto.setCreatedBy("root");
		dto.setModifiedBy("root");
		dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));

		long pk = model.add(dto);

		if (pk > 0) {
			System.out.println("Successfully add");
		} else {
			System.out.println("Test add failed");
		}

	}

	public static void testupdate() throws ApplicationException, DuplicateRecordException, ParseException {

		UserDTO dto = new UserDTO();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = sdf.parse("14/06/1991"); // util date object
		dto.setId(3);
		dto.setFirstName("Komal");
		dto.setLastName("Verma");
		dto.setLogin("komu@gmail.com");
		dto.setPassword("Komal@123");
		dto.setDob(d);
		dto.setGender("Female");
		dto.setAddress("Rajenda Nagar");
		dto.setMobileNo("7879550804");
		dto.setRoleId(1);
		dto.setRoleName("admin");
		dto.setCreatedBy("root");
		dto.setModifiedBy("root");
		dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.update(dto);
	}

	public static void testDelete() throws ApplicationException {

		UserDTO dto = new UserDTO();
		long pk = 4l;
		dto.setId(pk);
		model.delete(dto);
		System.out.println("data deleted");

	}

	/**
	 * Tests find a User by PK.
	 */

	public static void testfindByPk() {
		try {
			UserDTO dto = new UserDTO();
			long pk = 1L;
			dto = model.findByPk(pk);
			if (dto == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.println(dto.getId());
			System.out.println(dto.getFirstName());
			System.out.println(dto.getLastName());
			System.out.println(dto.getLogin());
			System.out.println(dto.getPassword());
			System.out.println(dto.getDob());
			System.out.println(dto.getRoleId());
			System.out.println(dto.getGender());
			System.out.println(dto.getMobileNo());
			System.out.println(dto.getCreatedBy());
			System.out.println(dto.getModifiedBy());
			System.out.println(dto.getCreatedDatetime());
			System.out.println(dto.getModifiedDatetime());
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (RecordNotFoundException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Tests find a User by Login.
	 */

	public static void testfindByEmailId() {
		UserDTO dto = new UserDTO();
		try {
			dto = model.findByEmailId("danishahmed34@gmail.com");
			if (dto == null) {
				System.out.println("Test Find By PK fail");
			}
		//	System.out.println(dto.getId());
			System.out.println(dto.getFirstName());
			System.out.println(dto.getLastName());
			System.out.println(dto.getLogin());
			System.out.println(dto.getPassword());
			System.out.println(dto.getDob());
			System.out.println(dto.getRoleId());
			System.out.println(dto.getGender());
			System.out.println(dto.getMobileNo());
			System.out.println(dto.getCreatedBy());
			System.out.println(dto.getModifiedBy());
			System.out.println(dto.getCreatedDatetime());
			System.out.println(dto.getModifiedDatetime());
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (RecordNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests get List.
	 */

	public static void testList() {

		try {
			UserDTO dto = new UserDTO();
			List list = new ArrayList();
			list = model.list(0, 10);
			if (list.size() < 0) {
				System.out.println("Test list fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				dto = (UserDTO) it.next();
				System.out.println(dto.getId());
				System.out.println(dto.getFirstName());
				System.out.println(dto.getLastName());
				System.out.println(dto.getLogin());
				System.out.println(dto.getPassword());
				System.out.println(dto.getDob());
				System.out.println(dto.getRoleId());
				System.out.println(dto.getGender());
				System.out.println(dto.getMobileNo());
				System.out.println(dto.getCreatedBy());
				System.out.println(dto.getModifiedBy());
				System.out.println(dto.getCreatedDatetime());
				System.out.println(dto.getModifiedDatetime());
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testSearch() {

		try {
			UserDTO dto = new UserDTO();
			List list = new ArrayList(); //
			// dto.setFirstName("Danish");
			// dto.setLogin("komu@gmail.com");
			// dto.setRoleName("admin");
			dto.setRoleId(1);
			list = model.search(dto, 0, 10);

			if (list.size() < 0) {
				System.out.println("Test Serach fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				dto = (UserDTO) it.next();
				System.out.println(dto.getId());
				System.out.println(dto.getFirstName());
				System.out.println(dto.getLastName());
				System.out.println(dto.getLogin());
				System.out.println(dto.getPassword());
				System.out.println(dto.getDob());
				System.out.println(dto.getRoleId());
				System.out.println(dto.getGender());
				System.out.println(dto.getMobileNo());
				System.out.println(dto.getCreatedBy());
				System.out.println(dto.getModifiedBy());
				System.out.println(dto.getCreatedDatetime());
				System.out.println(dto.getModifiedDatetime());
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests authenticate User.
	 */

	public static void testAuthenticate() {

		try {
			UserDTO dto = new UserDTO(); //
			dto.setLogin("danishahmed34@gmail.com");
			dto.setPassword("Danish@123");
			dto = model.authenticate(dto.getLogin(), dto.getPassword());
			if (dto != null) {
				System.out.println("Successfully login");
				System.out.println("Hi" + dto.getFirstName());
			} else {
				System.out.println("Invalied login Id & password");
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests add a User UserDTO dto = new UserDTO();
	 * 
	 * @throws ParseException
	 */

	public static void testchangePassword() {

		try {
			UserDTO dto = model.findByEmailId("ranjitchoudhary20@gmail.com");
			String oldPassword = dto.getPassword();
			dto.setId(15l);
			dto.setPassword("rr");
			String newPassword = dto.getPassword();
			try {
				model.changePassword(15L, oldPassword, newPassword);
			} catch (RecordNotFoundException e) {
				e.printStackTrace();
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (RecordNotFoundException e1) { // TODO Auto-generated catch
												// block
			e1.printStackTrace();
		}

	}

	/**
	 * Tests add a User register
	 * 
	 * @throws ParseException
	 * @throws RecordNotFoundException
	 */

	public static void testRegisterUser() throws ParseException, RecordNotFoundException {
		try {
			UserDTO dto = new UserDTO();
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

			dto.setFirstName("Bhanu");
			dto.setLastName("Bheru");
			dto.setLogin("danishahmed34@gmail.com");
			dto.setPassword("4444");
			dto.setDob(sdf.parse("11/12/1999"));
			dto.setGender("Male");
			dto.setRoleId(2);
			dto.setAddress("Bhopal");
			dto.setRoleName("Student");
			dto.setCreatedBy("root");
			dto.setModifiedBy("root");
			dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
			dto.setModifiedDatetime(new Timestamp(new Date().getTime()));

			long pk = model.registerUser(dto);
			System.out.println("Successfully register");
			System.out.println(dto.getFirstName());
			System.out.println(dto.getLogin());
			System.out.println(dto.getLastName());
			System.out.println(dto.getDob());
			UserDTO registerDto = model.findByPk(pk);
			if (registerDto == null) {
				System.out.println("Test add fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests fogetPassword
	 * 
	 * @throws ParseException
	 */
		  public static void testforgetPassword() { 
			  try { 
				  boolean b =model.forgetPassword("danishahmed34@gmail.com");
		  
		  System.out.println("Suucess : Test Forget Password Success");
		  
		  } catch (RecordNotFoundException e) { e.printStackTrace(); } catch
		  (ApplicationException e) { e.printStackTrace(); }
		  
		  }
		 
}
