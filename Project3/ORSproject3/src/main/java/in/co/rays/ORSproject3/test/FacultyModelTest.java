package in.co.rays.ORSproject3.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.ORSproject3.dto.FacultyDTO;
import in.co.rays.ORSproject3.exception.ApplicationException;
import in.co.rays.ORSproject3.exception.DuplicateRecordException;
import in.co.rays.ORSproject3.model.FacultyModelInt;
import in.co.rays.ORSproject3.model.ModelFactory;
import in.co.rays.ORSproject3.model.FacultyModelInt;

public class FacultyModelTest {
	public static FacultyModelInt model = ModelFactory.getInstance().getFacultyModel();

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
		 //testUpdate();
		// testFindByPK();
		// testFindByEmailId();
		 //testSearch();
		testList();

	}

	/**
	 * Tests add a Faculty
	 * 
	 * @throws ParseException
	 */
	public static void testAdd() throws ParseException {

		try {
			FacultyDTO dto = new FacultyDTO();
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

			// dto.setId(1L);
			dto.setFirstName("Shyam");
			dto.setLastName("Tiwari");
			dto.setDateOfJoining(sdf.parse("29/01/2019"));
			dto.setMobileNo("6778899000");
			dto.setEmailId("shyam@gmail.com");
			dto.setCollegeId(3L);
			dto.setCourseId(3L);
			dto.setSubjectId(3L);
			dto.setGender("M");
			dto.setQualification("Mtech");
			dto.setAddress("Indore");

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
	 * Tests delete a Faculty
	 */
	public static void testDelete() {

		try {
			FacultyDTO dto = new FacultyDTO();
			long pk = 3L;
			dto.setId(pk);
			model.delete(dto);
			System.out.println("Test Delete succ");

			FacultyDTO deletedDto = model.findByPK(pk);
			if (deletedDto != null) {
				System.out.println("Test Delete fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests update a Faculty
	 * 
	 * @throws DuplicateRecordException
	 */
	public static void testUpdate() throws DuplicateRecordException {

		try {
			FacultyDTO dto = model.findByPK(1);
			dto.setId(1);
			dto.setFirstName("Faizan");
			dto.setLastName("Khan");
			dto.setMobileNo("9000900088");
			dto.setEmailId("fz@gmail.com");
			dto.setCollegeId(1);
			dto.setCourseId(1);
			dto.setSubjectId(1);
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
	 * Tests find a Faculty by PK.
	 */
	public static void testFindByPK() {
		try {
			FacultyDTO dto = new FacultyDTO();
			long pk = 1L;
			dto = model.findByPK(pk);
			if (dto == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.println(dto.getId());
			System.out.println(dto.getFirstName());
			System.out.println(dto.getLastName());
			System.out.println(dto.getMobileNo());
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
	 * Tests find a Faculty by EmailId.
	 */
	public static void testFindByEmailId() {
		try {
			FacultyDTO dto = new FacultyDTO();
			dto = model.findByEmailId("fz@gmail.com");
			if (dto == null) {
				System.out.println("Test Find By EmailId fail");
			}
			System.out.println(dto.getId());
			System.out.println(dto.getFirstName());
			System.out.println(dto.getLastName());
			System.out.println(dto.getMobileNo());
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
			FacultyDTO dto = new FacultyDTO();
			List list = new ArrayList();
			dto.setFirstName("Sneha");
			list = model.search(dto, 0, 0);
			if (list.size() < 0) {
				System.out.println("Test Serach fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				dto = (FacultyDTO) it.next();
				System.out.println(dto.getId());
				System.out.println(dto.getFirstName());
				System.out.println(dto.getLastName());
				System.out.println(dto.getMobileNo());
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
			FacultyDTO dto = new FacultyDTO();
			List list = new ArrayList();
			list = model.list(0, 10);
			if (list.size() < 0) {
				System.out.println("Test list fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				dto = (FacultyDTO) it.next();
				System.out.println(dto.getId());
				System.out.println(dto.getFirstName());
				System.out.println(dto.getLastName());
				System.out.println(dto.getMobileNo());
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
