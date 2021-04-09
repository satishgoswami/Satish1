package in.co.rays.ORSproject3.test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.rays.ORSproject3.dto.RoleDTO;
import in.co.rays.ORSproject3.exception.ApplicationException;
import in.co.rays.ORSproject3.exception.DuplicateRecordException;
import in.co.rays.ORSproject3.model.ModelFactory;
import in.co.rays.ORSproject3.model.RoleModelInt;

public class RoleModelTest {

	public static RoleModelInt model = ModelFactory.getInstance().getRoleModel();

	public static void main(String[] args) throws ApplicationException, DuplicateRecordException {
		testadd();
	//testupdate();
	//testDelete();
	// testFindByPK();
    // testFindByName();
     //testSearch();
   // testList();
	
	}

	public static void testadd() throws ApplicationException, DuplicateRecordException {
		RoleDTO dto = new RoleDTO();
		dto.setName("student");
		dto.setDescription("Student can update there profile");
		dto.setCreatedBy("root");
		dto.setModifiedBy(dto.getCreatedBy());
		dto.setCreatedDatetime(new Timestamp(System.currentTimeMillis()));
		dto.setModifiedDatetime(dto.getCreatedDatetime());

		long pk = model.add(dto);
		if (pk > 0) {
			System.out.println("added");
		} else {
			System.out.println("test fail");
		}
	}

	public static void testupdate() throws ApplicationException, DuplicateRecordException{
		RoleDTO dto=new RoleDTO();
		dto.setName("Collage");
		dto.setId(2);
		dto.setDescription("college can update there profile");
		dto.setCreatedBy("root");
		dto.setModifiedBy(dto.getCreatedBy());
		dto.setCreatedDatetime(new Timestamp(System.currentTimeMillis()));
		dto.setModifiedDatetime(dto.getCreatedDatetime());

		
		model.update(dto);
	}
	public static void testDelete() throws ApplicationException{
		RoleDTO dto=new RoleDTO();
		dto.setId(3);
		model.delete(dto);
		
	}
	/**
     * Tests find a User by PK.
     */
    public static void testFindByPK() {
        try {
            RoleDTO dto = new RoleDTO();
            long pk = 2L;
            dto = model.findByPK(pk);
            if (dto == null) {
                System.out.println("Test Find By PK fail");
            }
            System.out.println(dto.getId());
            System.out.println(dto.getName());
            System.out.println(dto.getDescription());
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
 
    }
 
    /**
     * Tests find a User by Login.
     */
    public static void testFindByName() {
        try {
            RoleDTO dto = new RoleDTO();
            dto = model.findByName("admin");
            if (dto == null) {
                System.out.println("Test Find By PK fail");
            }
            System.out.println(dto.getId());
            System.out.println(dto.getName());
            System.out.println(dto.getDescription());
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }
 
    /**
     * Tests get Search
     */
    public static void testSearch() {
 
        try {
            RoleDTO dto = new RoleDTO();
            List list = new ArrayList();
            dto.setName("admin");
            list = model.search(dto, 0, 0);
            if (list.size() < 0) {
                System.out.println("Test Serach fail");
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                dto = (RoleDTO) it.next();
                System.out.println(dto.getId());
                System.out.println(dto.getName());
                System.out.println(dto.getDescription());
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
            RoleDTO dto = new RoleDTO();
            List list = new ArrayList();
            list = model.list(0, 10);
            if (list.size() < 0) {
                System.out.println("Test list fail");
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                dto = (RoleDTO) it.next();
                System.out.println(dto.getId());
                System.out.println(dto.getName());
                System.out.println(dto.getDescription());
            }
 
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }
}
