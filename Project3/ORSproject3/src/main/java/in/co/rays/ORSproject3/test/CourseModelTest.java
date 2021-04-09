package in.co.rays.ORSproject3.test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.ORSproject3.dto.CourseDTO;
import in.co.rays.ORSproject3.exception.ApplicationException;
import in.co.rays.ORSproject3.exception.DuplicateRecordException;
import in.co.rays.ORSproject3.model.CourseModelInt;
import in.co.rays.ORSproject3.model.ModelFactory;

public class CourseModelTest {

public static CourseModelInt model=ModelFactory.getInstance().getCourseModel();
	
    /**
     * Main method to call test methods.
     *  
     * @param args
     */
    public static void main(String[] args) {
        //testAdd();
        // testDelete();
       testUpdate();
        // testFindByName();
        // testFindByPK();
   //     testSearch();
 //       testList();
 
    }
 
    /**
     * Tests add a College
     */
    public static void testAdd() {
 
        try {
            CourseDTO dto = new CourseDTO();
            //dto.setId(1L);
            dto.setCourseName("BSC");
           dto.setDuration("3 years");
           dto.setDescription("Science");
            dto.setCreatedBy("root");
            dto.setModifiedBy("root");
            dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
            dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
            long pk = model.add(dto);
            System.out.println("Test add succ");
           /* CourseDTO addedDto = model.findByPK(pk);
            if (addedDto == null) {
                System.out.println("Test add fail");
            }*/
        } catch (ApplicationException e) {
            e.printStackTrace();
        } catch (DuplicateRecordException e) {
            e.printStackTrace();
        }
 
    }
 
    /**
     * Tests delete a College
     */
    public static void testDelete() {
 
        try {
            CourseDTO dto = new CourseDTO();
            long pk = 4L;
            dto.setId(pk);
            model.delete(dto);
            System.out.println("Test Delete succ");
            CourseDTO deletedDto = model.findByPK(pk);
            if (deletedDto != null) {
                System.out.println("Test Delete fail");
            }
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }
 
    /**
     * Tests update a College
     */
    public static void testUpdate() {
 
        try {
            CourseDTO dto = new CourseDTO();
           dto.setId(2L);
            dto.setCourseName("Btech");
            dto.setDuration("four years");
            dto.setDescription("Engineering");
            dto.setCreatedBy("root");
            dto.setModifiedBy("root");
            dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
            dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
             model.update(dto);
            //CourseDTO updatedDTO = model.findByPK(1L);
            System.out.println("Test Update succ");
        } catch (ApplicationException e) {
            e.printStackTrace();
        } catch (DuplicateRecordException e) {
            e.printStackTrace();
        }
 
    }
 
    /**
     * Tests find a College by Name.
     */
 
    public static void testFindByName() {
 
        try {
            CourseDTO dto = model.findByName("Btech");
            if (dto == null) {
                System.out.println("Test Find By Name fail");
            }
            System.out.println(dto.getId());
            System.out.println(dto.getCourseName());
            System.out.println(dto.getDescription());
            System.out.println(dto.getDuration());
            System.out.println(dto.getCreatedBy());
            System.out.println(dto.getCreatedDatetime());
            System.out.println(dto.getModifiedBy());
            System.out.println(dto.getModifiedDatetime());
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
 
    }
 
    /**
     * Tests find a College by PK.
     */
    public static void testFindByPK() {
        try {
            CourseDTO dto = new CourseDTO();
            long pk = 2L;
            dto = model.findByPK(pk);
            if (dto == null) {
                System.out.println("Test Find By PK fail");
            }
            System.out.println(dto.getId());
            System.out.println(dto.getCourseName());
            System.out.println(dto.getDescription());
            System.out.println(dto.getDuration());
            System.out.println(dto.getCreatedBy());
            System.out.println(dto.getCreatedDatetime());
            System.out.println(dto.getModifiedBy());
            System.out.println(dto.getModifiedDatetime());
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
 
    }
 
    /**
     * Tests search a College
     */
 
    public static void testSearch() {
        try {
            CourseDTO dto = new CourseDTO();
            List list = new ArrayList();
            dto.setCourseName("BCOM");
            list = model.search(dto, 0, 10);
            if (list.size() < 0) {
                System.out.println("Test Search fail");
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                dto = (CourseDTO) it.next();
                System.out.println(dto.getId());
                System.out.println(dto.getCourseName());
                System.out.println(dto.getDescription());
                System.out.println(dto.getDuration());
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
            CourseDTO dto = new CourseDTO();
            List list = new ArrayList();
            list = model.list(0, 10);
            if (list.size() < 0) {
                System.out.println("Test list fail");
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                dto = (CourseDTO) it.next();
                System.out.println(dto.getId());
                System.out.println(dto.getCourseName());
                System.out.println(dto.getDescription());
                System.out.println(dto.getDuration());
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
