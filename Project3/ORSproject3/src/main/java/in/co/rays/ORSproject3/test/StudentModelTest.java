package in.co.rays.ORSproject3.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.ORSproject3.dto.StudentDTO;
import in.co.rays.ORSproject3.exception.ApplicationException;
import in.co.rays.ORSproject3.exception.DuplicateRecordException;
import in.co.rays.ORSproject3.model.ModelFactory;
import in.co.rays.ORSproject3.model.StudentModelInt;
import in.co.rays.ORSproject3.model.StudentModelJDBCImpl;

public class StudentModelTest {
	/**
	 * Model object to test
	 */

	// public static StudentModelInt model = new StudentModelHibImpl();

	public static StudentModelInt model = ModelFactory.getInstance().getStudentModel();

	/**
	 * Main method to call test methods.
	 * 
	 * @param args
	 * @throws ParseException
	 * @throws DuplicateRecordException 
	 */
	public static void main(String[] args) throws ParseException, DuplicateRecordException {
			//testAdd();
		// testDelete();
		testUpdate();
		 //testFindByPK();
		// testFindByEmailId();
		 //testSearch();
		//testList();

	}

	/**
	 * Tests add a Student
	 * 
	 * @throws ParseException
	 */
	public static void testAdd() throws ParseException {

		try {
			StudentDTO dto = new StudentDTO();
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

			// dto.setId(1L);
			dto.setFirstName("Anil");
			dto.setLastName("Sendhwa");
			dto.setDob(sdf.parse("01/10/1994"));
			dto.setMobileNo("9990001919");
			dto.setEmail("anil345@gmail.com");
			dto.setCollegeId(1L);
			//dto.setCollegeName("Truba");
			dto.setAddress("Mhow");

			dto.setCreatedBy("root");
			dto.setModifiedBy("root");
			dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
			dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
			long pk = model.add(dto);
			if (pk == 0) {
				System.out.println("Test add fail");
			} else {
				System.out.println("Test add succ");

			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests delete a Student
	 */
	public static void testDelete() {

		try {
			StudentDTO dto = new StudentDTO();
			long pk = 3L;
			dto.setId(pk);
			model.delete(dto);
			System.out.println("Test Delete succ");

			StudentDTO deletedDto = model.findByPK(pk);
			if (deletedDto != null) {
				System.out.println("Test Delete fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests update a Student
	 * 
	 * @throws DuplicateRecordException
	 */
	public static void testUpdate() throws DuplicateRecordException {

		try {
			StudentDTO dto = model.findByPK(2);
			dto.setId(2);
			dto.setFirstName("Imran");
			dto.setLastName("Khan");
			dto.setMobileNo("9000900088");
			dto.setEmail("fz@gmail.com");
			dto.setCollegeId(1);
			dto.setCollegeName("Acro 1");
			dto.setAddress("Agar Malwa");
		
			dto.setCreatedBy("root");
			dto.setModifiedBy("root");
			dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
			dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
			model.update(dto);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests find a Student by PK.
	 */
	public static void testFindByPK() {
		try {
			StudentDTO dto = new StudentDTO();
			long pk = 1L;
			dto = model.findByPK(pk);
			if (dto == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.println(dto.getId());
			System.out.println(dto.getFirstName());
			System.out.println(dto.getLastName());
			System.out.println(dto.getDob());
			System.out.println(dto.getMobileNo());
			System.out.println(dto.getEmail());
			System.out.println(dto.getCollegeId());
			System.out.println(dto.getCreatedBy());
			System.out.println(dto.getCreatedDatetime());
			System.out.println(dto.getModifiedBy());
			System.out.println(dto.getModifiedDatetime());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests find a Student by EmailId.
	 */
	public static void testFindByEmailId() {
		try {
			StudentDTO dto = new StudentDTO();
			dto = model.findByEmailId("fz@gmail.com");
			if (dto == null) {
				System.out.println("Test Find By EmailId fail");
			}
			System.out.println(dto.getId());
			System.out.println(dto.getFirstName());
			System.out.println(dto.getLastName());
			System.out.println(dto.getDob());
			System.out.println(dto.getMobileNo());
			System.out.println(dto.getEmail());
			System.out.println(dto.getCollegeId());
			System.out.println(dto.getCreatedBy());
			System.out.println(dto.getCreatedDatetime());
			System.out.println(dto.getModifiedBy());
			System.out.println(dto.getModifiedDatetime());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests get Search
	 */
	public static void testSearch() {

		try {
			StudentDTO dto = new StudentDTO();
			List list = new ArrayList();
			dto.setFirstName("Satish");
			list = model.search(dto, 0, 0);
			if (list.size() < 0) {
				System.out.println("Test Serach fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				dto = (StudentDTO) it.next();
				System.out.println(dto.getId());
				System.out.println(dto.getFirstName());
				System.out.println(dto.getLastName());
				System.out.println(dto.getDob());
				System.out.println(dto.getMobileNo());
				System.out.println(dto.getEmail());
				System.out.println(dto.getCollegeId());
				System.out.println(dto.getCreatedBy());
				System.out.println(dto.getCreatedDatetime());
				System.out.println(dto.getModifiedBy());
				System.out.println(dto.getModifiedDatetime());
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests get List.
	 */
	public static void testList() {

		try {
			StudentDTO dto = new StudentDTO();
			List list = new ArrayList();
			list = model.list(0, 10);
			if (list.size() < 0) {
				System.out.println("Test list fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				dto = (StudentDTO) it.next();
				System.out.println(dto.getId());
				System.out.println(dto.getFirstName());
				System.out.println(dto.getLastName());
				System.out.println(dto.getDob());
				System.out.println(dto.getMobileNo());
				System.out.println(dto.getEmail());
				System.out.println(dto.getCollegeId());
				System.out.println(dto.getCreatedBy());
				System.out.println(dto.getCreatedDatetime());
				System.out.println(dto.getModifiedBy());
				System.out.println(dto.getModifiedDatetime());
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}
}
