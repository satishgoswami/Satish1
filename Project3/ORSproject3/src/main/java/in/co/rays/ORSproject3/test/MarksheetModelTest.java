package in.co.rays.ORSproject3.test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.ORSproject3.dto.MarksheetDTO;
import in.co.rays.ORSproject3.exception.ApplicationException;
import in.co.rays.ORSproject3.exception.DuplicateRecordException;
import in.co.rays.ORSproject3.model.MarksheetModelInt;
import in.co.rays.ORSproject3.model.MarksheetModelJDBCImpl;
import in.co.rays.ORSproject3.model.ModelFactory;

public class MarksheetModelTest {
	/**
     * Model object to test
     */
 
   public static MarksheetModelInt model = ModelFactory.getInstance().getMarksheetModel();
 
 //   public static MarksheetModelInt model = new MarksheetModelJDBCImpl();
 
    /**
     * Main method to call test methods.
     *  
     * @param args
     */
    public static void main(String[] args) {
       // testAdd();
        // testDelete();
         //testUpdate();
         testFindByRollNo();
        // testFindByPK();
   //      testList();
         //testSearch();
        //testMeritList();
 
    }
 
    /**
     * Tests add a Marksheet
     */
    public static void testAdd() {
 
        try {
            MarksheetDTO dto = new MarksheetDTO();
            //dto.setId(10L);
            dto.setRollNo("105");
            dto.setPhysics(89);
            dto.setChemistry(87);
            dto.setMaths(79);
            dto.setStudentId(5L);
            dto.setCreatedBy("root");
            dto.setModifiedBy("root");
            dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
            dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
            
            long pk = model.add(dto);
            System.out.println("Test add succ");
          
            if (pk==0) {
                System.out.println("Test add fail");
            }
        } catch (ApplicationException e) {
            e.printStackTrace();
        } catch (DuplicateRecordException e) {
            e.printStackTrace();
        }
 
    }
 
    /**
     * Tests delete a Marksheet
     */
    public static void testDelete() {
 
        try {
            MarksheetDTO dto = new MarksheetDTO();
            long pk = 8L;
            dto.setId(pk);
            model.delete(dto);
            MarksheetDTO deletedDto = model.findByPK(pk);
            if (deletedDto != null) {
                System.out.println("Test Delete fail");
            }
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }
 
    /**
     * Tests update a Marksheet
     */
    public static void testUpdate() {
 
        try {
            MarksheetDTO dto = model.findByPK(3L);
            
            dto.setChemistry(100);
            dto.setMaths(100);
            model.update(dto);
            System.out.println("Test Update ");
          
        } catch (ApplicationException e) {
            e.printStackTrace();
        } catch (DuplicateRecordException e) {
            e.printStackTrace();
        }
 
    }
 
    /**
     * Tests find a marksheet by Roll No.
     */
 
    public static void testFindByRollNo() {
 
        try {
            MarksheetDTO dto = model.findByRollNo("101ME18");
            if (dto == null) {
                System.out.println("Test Find By RollNo fail");
            }
            System.out.println(dto.getId());
            System.out.println(dto.getRollNo());
            System.out.println(dto.getName());
            System.out.println(dto.getPhysics());
            System.out.println(dto.getChemistry());
            System.out.println(dto.getMaths());
            System.out.println(dto.getCreatedBy());
            System.out.println(dto.getCreatedDatetime());
            System.out.println(dto.getModifiedBy());
            System.out.println(dto.getModifiedDatetime());
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
 
    }
 
    /**
     * Tests find a marksheet by PK.
     */
    public static void testFindByPK() {
        try {
            MarksheetDTO dto = new MarksheetDTO();
            long pk = 3L;
            dto = model.findByPK(pk);
            if (dto == null) {
                System.out.println("Test Find By PK fail");
            }
            System.out.println(dto.getId());
            System.out.println(dto.getRollNo());
            System.out.println(dto.getName());
            System.out.println(dto.getPhysics());
            System.out.println(dto.getChemistry());
            System.out.println(dto.getMaths());
            System.out.println(dto.getCreatedBy());
            System.out.println(dto.getCreatedDatetime());
            System.out.println(dto.getModifiedBy());
            System.out.println(dto.getModifiedDatetime());
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
 
    }
 
    /**
     * Tests list of Marksheets
     */
    public static void testList() {
        try {
            MarksheetDTO dto = new MarksheetDTO();
            List list = new ArrayList();
            list = model.list(1, 6);
            if (list.size() < 0) {
                System.out.println("Test List fail");
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                dto = (MarksheetDTO) it.next();
                System.out.println(dto.getId());
                System.out.println(dto.getRollNo());
                System.out.println(dto.getName());
                System.out.println(dto.getPhysics());
                System.out.println(dto.getChemistry());
                System.out.println(dto.getMaths());
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
     * Tests search a Marksheets
     */
 
    public static void testSearch() {
        try {
            MarksheetDTO dto = new MarksheetDTO();
            List list = new ArrayList();
            dto.setName("Vini Dubey");
            
            list = model.search(dto, 0, 10);
            if (list.size() < 0) {
                System.out.println("Test Search fail");
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                dto = (MarksheetDTO) it.next();
                System.out.println(dto.getId());
                System.out.println(dto.getRollNo());
                System.out.println(dto.getName());
                System.out.println(dto.getPhysics());
                System.out.println(dto.getChemistry());
                System.out.println(dto.getMaths());
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
     * Tests get the meritlist of Marksheets
     */
    public static void testMeritList() {
        try {
            MarksheetDTO dto = new MarksheetDTO();
            List list = new ArrayList();
            list = model.getMeritList(1, 5);
            if (list.size() < 0) {
                System.out.println("Test List fail");
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                dto = (MarksheetDTO) it.next();
                System.out.println(dto.getId());
                System.out.println(dto.getRollNo());
                System.out.println(dto.getName());
                System.out.println(dto.getPhysics());
                System.out.println(dto.getChemistry());
                System.out.println(dto.getMaths());
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
