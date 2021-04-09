package in.co.rays.ORSproject3.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.ORSproject3.dto.TimetableDTO;
import in.co.rays.ORSproject3.exception.ApplicationException;
import in.co.rays.ORSproject3.exception.DuplicateRecordException;
import in.co.rays.ORSproject3.model.TimetableModelInt;
import in.co.rays.ORSproject3.model.ModelFactory;

public class TimetableModelTest {
	public static TimetableModelInt model = ModelFactory.getInstance().getTimetableModel();

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
		// testUpdate();
		// testFindByPK();
		 //testFindByCSS();
		//testFindByCSE();
		//testSearch();
		 testList();

	}


	/**
	 * Tests add a Timetable
	 * 
	 * @throws ParseException
	 */
	public static void testAdd() throws ParseException {

		try {
			TimetableDTO dto = new TimetableDTO();
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

			// dto.setId(1L);

			dto.setCourseId(1L);
			dto.setSubId(1L);
			dto.setSemester("3rd");
			dto.setExamTime("1 to 5");
			dto.setExamDate(sdf.parse("22/02/2020"));
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
	 * Tests delete a Timetable
	 */
	public static void testDelete() {

		try {
			TimetableDTO dto = new TimetableDTO();
			long pk = 3L;
			dto.setId(pk);
			model.delete(dto);
			System.out.println("Test Delete succ");

			TimetableDTO deletedDto = model.findByPK(pk);
			if (deletedDto != null) {
				System.out.println("Test Delete fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests update a Timetable
	 * 
	 * @throws DuplicateRecordException
	 */
	public static void testUpdate() throws DuplicateRecordException {

		try {
			TimetableDTO dto = model.findByPK(1);
			dto.setId(1);
			dto.setCourseId(1);
			dto.setSubId(1);
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
	 * Tests find a Timetable by PK.
	 */
	public static void testFindByPK() {
		try {
			TimetableDTO dto = new TimetableDTO();
			long pk = 1L;
			dto = model.findByPK(pk);
			if (dto == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.println(dto.getId());
			System.out.println(dto.getCreatedBy());
			System.out.println(dto.getCreatedDatetime());
			System.out.println(dto.getModifiedBy());
			System.out.println(dto.getModifiedDatetime());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}
	/**
	 * Tests find a Timetable by CSS.
	 */
	public static void testFindByCSS() {
		try {
			TimetableDTO dto = new TimetableDTO();
			long pk = 1L;
			dto = model.findByCSS("Btech","M-Science","3rd");
			if (dto == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.println(dto.getExamDate());
			System.out.println(dto.getCourseName()+dto.getSubName());
			System.out.println(dto.getId());
			System.out.println(dto.getCreatedBy());
			System.out.println(dto.getCreatedDatetime());
			System.out.println(dto.getModifiedBy());
			System.out.println(dto.getModifiedDatetime());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}
	public static void testFindByCSE() throws ParseException {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

			
			TimetableDTO dto = new TimetableDTO();
			dto = model.findByCSE("Btech","M-Science",sdf.parse("02/10/2021"));
			if (dto == null) {
				System.out.println("Test Find By PK fail");
			}
			else{
			System.out.println(dto.getExamDate());
			System.out.println(dto.getCourseName()+dto.getSubName());
			System.out.println(dto.getId()+"-------------");
			System.out.println(dto.getCreatedBy());
			System.out.println(dto.getCreatedDatetime());
			System.out.println(dto.getModifiedBy());
			System.out.println(dto.getModifiedDatetime());
		}} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	
	
	/**
	 * Tests get Search
	 */
	public static void testSearch() {

		try {
			TimetableDTO dto = new TimetableDTO();
			List list = new ArrayList();
			dto.setCourseName("Btech");
			list = model.search(dto, 0, 0);
			if (list.size() < 0) {
				System.out.println("Test Serach fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				dto = (TimetableDTO) it.next();
				System.out.println(dto.getId());
			System.out.println(dto.getSubName());
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
			TimetableDTO dto = new TimetableDTO();
			List list = new ArrayList();
			list = model.list(0, 10);
			if (list.size() < 0) {
				System.out.println("Test list fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				dto = (TimetableDTO) it.next();
				System.out.println(dto.getId());
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
