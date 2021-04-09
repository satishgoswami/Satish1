package in.co.rays.ORSproject3.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import in.co.rays.ORSproject3.dto.CollegeDTO;
import in.co.rays.ORSproject3.dto.CourseDTO;
import in.co.rays.ORSproject3.dto.FacultyDTO;
import in.co.rays.ORSproject3.dto.SubjectDTO;
import in.co.rays.ORSproject3.exception.ApplicationException;
import in.co.rays.ORSproject3.exception.DatabaseException;
import in.co.rays.ORSproject3.exception.DuplicateRecordException;
import in.co.rays.ORSproject3.utility.JDBCDataSource;

public class FacultyModelJDBCImpl implements FacultyModelInt {
	/**
	 * Find next PK of Faculty
	 * 
	 * @throws DatabaseException
	 */
	public static Integer nextPk() throws DatabaseException {
		int i = 0;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			con = JDBCDataSource.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement("SELECT MAX(ID) FROM FACULTY");
			rs = ps.executeQuery();

			while (rs.next()) {
				i = rs.getInt(1);
			}
			con.commit();
			ps.close();
			rs.close();
		} catch (Exception e) {
			throw new DatabaseException("exception in faculty model  nextPK..... " + e.getMessage());
		}

		return i + 1;

	}
/*
	*//**
	 * Add a Faculty
	 * 
	 * @param bean
	 *
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 *//*
public static long add(FacultyDTO bean) throws Exception{
		
		int pk=0;
		 Connection conn=null;
		
		  CollegeModelJDBCImpl CollegeModelJDBCImpl = new CollegeModelJDBCImpl();
		  CollegeDTO  collegebean= CollegeModelJDBCImpl.findByPK(bean.getCollegeId());
		  System.out.println(" name of colege"+collegebean.getName());
		  bean.setCollegeName(collegebean.getName());
		  
		  
		  CourseModelJDBCImpl coursemodel = new CourseModelJDBCImpl();
		  CourseDTO coursebean = coursemodel.findByPK(bean.getCourseId());
		  System.out.println(" name of course"+coursebean.getCourseName());
		  bean.setCourseName(coursebean.getCourseName());
		
		  
		  SubjectModelJDBCImpl subjectmodel = new SubjectModelJDBCImpl();
		  SubjectDTO subjectbean = subjectmodel.findByPK(bean.getSubjectId());
		  System.out.println("name of subject"+subjectbean.getSubjectName());
		  bean.setSubjectName(subjectbean.getSubjectName());

		  FacultyDTO beanExist = findByEmail(bean.getEmailId());
		  
		  if(beanExist!=null){
			  throw new DuplicateRecordException("Faculty name already exist");
		  }
		  
		  try{
			pk= nextPk();
			conn= JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement
					("insert into faculty values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setLong(1, nextPk());
			ps.setString(2, bean.getFirstName());
			ps.setString(3, bean.getLastName());
			ps.setString(4, bean.getGender());
			ps.setString(5, bean.getAddress());
			ps.setDate(6, new java.sql.Date(bean.getDateOfJoining().getTime()));
			ps.setString(7, bean.getQualification());
			ps.setString(8, bean.getEmailId());
			ps.setString(9, bean.getMobileNo());
			ps.setLong(10, bean.getCollegeId());
			ps.setString(11, bean.getCollegeName());
			ps.setLong(12, bean.getCourseId());
			ps.setString(13, bean.getCourseName());
			ps.setLong(14, bean.getSubjectId());
			ps.setString(15, bean.getSubjectName());
			ps.setString(16, bean.getCreatedBy());
			ps.setString(17, bean.getModifiedBy());
			ps.setTimestamp(18, bean.getCreatedDatetime());
			ps.setTimestamp(19, bean.getModifiedDatetime());			
			ps.executeUpdate();
			System.out.println("record inserted");
			conn.commit();
			ps.close();
		}
		catch(Exception e){
			e.printStackTrace();
			conn.rollback();
		}
		finally{
			conn.close();
		}
		
		return pk;
	}


	*//**
	 * Update a Faculty
	 * 
	 * @param bean
	 * @throws ApplicationException
	 * @throws SQLException 
	 * 
	 *//*
	public static void update(FacultyDTO bean) throws Exception {
		
		System.out.println("update faculty id is : " + bean.getId());
		Connection con = null;
		PreparedStatement ps = null;
		Date date = new Date(bean.getDateOfJoining().getTime());

		CollegeModelJDBCImpl CollegeModelJDBCImpl = new CollegeModelJDBCImpl();
		  CollegeDTO  collegebean= CollegeModelJDBCImpl.findByPK(bean.getCollegeId());
		  System.out.println(" name of colege"+collegebean.getName());
		  bean.setCollegeName(collegebean.getName());
		  
		  
		  CourseModelJDBCImpl coursemodel = new CourseModelJDBCImpl();
		  CourseDTO coursebean = coursemodel.findByPK(bean.getCourseId());
		  System.out.println(" name of course"+coursebean.getCourseName()
		  );
		  bean.setCourseName(coursebean.getCourseName());
		
		  
		  SubjectModelJDBCImpl subjectmodel = new SubjectModelJDBCImpl();
		  SubjectDTO subjectbean = subjectmodel.findByPK(bean.getSubjectId());
		  System.out.println("name of subject"+subjectbean.getSubjectName());
		  bean.setSubjectName(subjectbean.getSubjectName());

		  try {
			con = JDBCDataSource.getConnection();
			ps = con.prepareStatement("UPDATE FACULTY SET FIRST_NAME=?,LAST_NAME=?,"
					+ "GENDER=?,ADDRESS=?,DATE_OF_JOINING=?,QUALIFICATION=?,EMAIL_ID=?,MOBILE_NO=?,"
					+ "COLLEGE_ID=?,COLLEGE_NAME=?,COURSE_ID=?,COURSE_NAME=?,SUBJECT_ID=?,"
					+ "SUBJECT_NAME=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_Datetime=?,"
					+ "MODIFIED_Datetime=? WHERE ID=?");
			con.setAutoCommit(false);

			
			ps.setString(1, bean.getFirstName());
			ps.setString(2, bean.getLastName());
			ps.setString(3, bean.getGender());
			ps.setString(4, bean.getAddress());
		//	ps.setDate(5, date);
			ps.setString(6, bean.getQualification());
			ps.setString(7, bean.getEmailId());
			ps.setString(8, bean.getMobileNo());
			ps.setLong(9, bean.getCollegeId());
			ps.setString(10, bean.getCollegeName());
			ps.setLong(11, bean.getCourseId());
			ps.setString(12, bean.getCourseName());
			ps.setLong(13, bean.getSubjectId());
			ps.setString(14, bean.getSubjectName());
			ps.setString(15, bean.getCreatedBy());
			ps.setString(16, bean.getModifiedBy());
			ps.setTimestamp(17, bean.getCreatedDatetime());
			ps.setTimestamp(18, bean.getModifiedDatetime());
			ps.setLong(19, bean.getId());
			int c=ps.executeUpdate();
			
			ps.close();
			con.commit();
			System.out.println("update faculty Success : ");
		} catch (Exception e) {
			e.printStackTrace();
			con.rollback();
		}
		JDBCDataSource.closeConnection(con);

	}

	*//**
	 * Delete a Faculty
	 * 
	 * @param bean
	 * @throws ApplicationException
	 *//*
	public static void delete(FacultyDTO bean) throws ApplicationException {
		// log.debug("Model delete Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			System.out.println("conn start");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM FACULTY WHERE ID=?");
			pstmt.setLong(1, bean.getId());
			int i = pstmt.executeUpdate();

			conn.commit(); // End transaction
			pstmt.close();

		} catch (Exception e) {
			throw new ApplicationException("exception in faculty model  delete..... " + e.getMessage());
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	*//**
	 * Find Faculty by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return bean
	 * @throws DatabaseException
	 *//*
	public static FacultyDTO findByPK(long pk) throws DatabaseException {
		// log.debug("Model findByPk Start");
		StringBuffer sql = new StringBuffer("SELECT * FROM FACULTY WHERE ID=?");
		FacultyDTO bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new FacultyDTO();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setGender(rs.getString(4));
				bean.setAddress(rs.getString(5));
				bean.setDateOfJoining(rs.getDate(6));
				bean.setQualification(rs.getString(7));
				bean.setEmailId(rs.getString(8));
				bean.setMobileNo(rs.getString(9));
				bean.setCollegeId(rs.getLong(10));
				bean.setCollegeName(rs.getString(11));
				bean.setCourseId(rs.getLong(12));
				bean.setCourseName(rs.getString(13));
				bean.setSubjectId(rs.getLong(14));
				bean.setSubjectName(rs.getString(15));
				bean.setCreatedBy(rs.getString(16));
				bean.setModifiedBy(rs.getString(17));
				bean.setCreatedDatetime(rs.getTimestamp(18));
				bean.setModifiedDatetime(rs.getTimestamp(19));
			}
			pstmt.close();
			rs.close();
		} catch (Exception e) {
			throw new DatabaseException("exception in faculty model  findByPk..... " + e.getMessage());
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	*//**
	 * Find Faculty by Email
	 * 
	 * @param Email
	 *            : get parameter
	 * @return bean
	 * @throws ApplicationException
	 *//*
	public static FacultyDTO findByEmail(String Email) throws ApplicationException {
		// log.debug("Model findByPk Start");
		StringBuffer sql = new StringBuffer("SELECT * FROM FACULTY WHERE EMAIL_ID=?");
		FacultyDTO bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, Email);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new FacultyDTO();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setGender(rs.getString(4));
				bean.setAddress(rs.getString(5));
				bean.setDateOfJoining(rs.getDate(6));
				bean.setQualification(rs.getString(7));
				bean.setEmailId(rs.getString(8));
				bean.setMobileNo(rs.getString(9));
				bean.setCollegeId(rs.getLong(10));
				bean.setCollegeName(rs.getString(11));
				bean.setCourseId(rs.getLong(12));
				bean.setCourseName(rs.getString(13));
				bean.setSubjectId(rs.getLong(14));
				bean.setSubjectName(rs.getString(15));
				bean.setCreatedBy(rs.getString(16));
				bean.setModifiedBy(rs.getString(17));
				bean.setCreatedDatetime(rs.getTimestamp(18));
				bean.setModifiedDatetime(rs.getTimestamp(19));
			}
			pstmt.close();
			rs.close();
		} catch (Exception e) {
			throw new ApplicationException("exception in faculty findByEmail  add..... " + e.getMessage());
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	*//**
	 * Search Faculty with pagination
	 * 
	 * @return list : List of Faculty
	 * @param bean
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * 
	 * @throws ApplicationException
	 *//*
	public static List search(FacultyDTO bean, int pageNo, int pageSize) throws ApplicationException {
		StringBuffer sql = new StringBuffer("SELECT * FROM FACULTY WHERE 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getCollegeId() > 0) {
				sql.append(" AND COLLEGE_ID = " + bean.getCollegeId());
			}
			if (bean.getCourseId() > 0) {
				sql.append(" AND COURSE_ID = " + bean.getCourseId());
			}
			
			if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {
				sql.append(" AND FIRST_NAME like '" + bean.getFirstName() + "%'");
			}
			if (bean.getLastName() != null && bean.getLastName().length() > 0) {
				sql.append(" AND LAST_NAME like '" + bean.getLastName() + "%'");
			}
			if (bean.getMobileNo() != null && bean.getMobileNo().length() > 0) {
				sql.append(" AND MOBILE_NO = " + bean.getMobileNo());
			}
			if (bean.getEmailId() != null && bean.getEmailId().length() > 0) {
				sql.append(" AND EMAIL_ID LIKE  '" + bean.getEmailId() + "%'");
			}
			
			if (bean.getSubjectId() > 0) {
				sql.append(" AND COURSE_ID = " + bean.getSubjectId());
			}
		}

		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + ", " + pageSize);
			// sql.append(" limit " + pageNo + "," + pageSize);
		}
System.out.println(sql+"---------------final------------");
		List<FacultyDTO> list = new ArrayList<FacultyDTO>();
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new FacultyDTO();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setGender(rs.getString(4));
				bean.setAddress(rs.getString(5));
				bean.setDateOfJoining(rs.getDate(6));
				bean.setQualification(rs.getString(7));
				bean.setEmailId(rs.getString(8));
				bean.setMobileNo(rs.getString(9));
				bean.setCollegeId(rs.getLong(10));
				bean.setCollegeName(rs.getString(11));
				bean.setCourseId(rs.getLong(12));
				bean.setCourseName(rs.getString(13));
				bean.setSubjectId(rs.getLong(14));
				bean.setSubjectName(rs.getString(15));
				bean.setCreatedBy(rs.getString(16));
				bean.setModifiedBy(rs.getString(17));
				bean.setCreatedDatetime(rs.getTimestamp(18));
				bean.setModifiedDatetime(rs.getTimestamp(19));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			throw new ApplicationException("exception in faculty model  search..... " + e.getMessage());
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return list;
	}

	*//**
	 * Search Faculty
	 * 
	 * @param bean
	 *            : Search Parameters
	 * @throws ApplicationException
	 *//*
	public static List search(FacultyDTO bean) throws ApplicationException {
		return search(bean, 0, 0);
	}

	*//**
	 * Get List of Faculty with pagination
	 * 
	 * @return list : List of Faculty
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws ApplicationException
	 *//*
	public static List list(int pageNo, int pageSize) throws Exception {
		System.out.println("list model called");
		Connection conn = null;

		StringBuffer sql = new StringBuffer("select * from faculty");

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" LIMIT " + pageNo + " ," + pageSize);
		}
		PreparedStatement ps = null;
		FacultyDTO bean = null;
		List list = new ArrayList();
		try {
			conn = JDBCDataSource.getConnection();
			ps = conn.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				bean = new FacultyDTO();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setGender(rs.getString(4));
				bean.setAddress(rs.getString(5));
				bean.setDateOfJoining(rs.getDate(6));
				bean.setQualification(rs.getString(7));
				bean.setEmailId(rs.getString(8));
				bean.setMobileNo(rs.getString(9));
				bean.setCollegeId(rs.getLong(10));
				bean.setCollegeName(rs.getString(11));
				bean.setCourseId(rs.getLong(12));
				bean.setCourseName(rs.getString(13));
				bean.setSubjectId(rs.getLong(14));
				bean.setSubjectName(rs.getString(15));
				bean.setCreatedBy(rs.getString(16));
				bean.setModifiedBy(rs.getString(17));
				bean.setCreatedDatetime(rs.getTimestamp(18));
				bean.setModifiedDatetime(rs.getTimestamp(19));
				list.add(bean);
			}
			rs.close();
		} catch(Exception e){
	    	e.printStackTrace();
	    	conn.rollback();
	    }
	    finally{
	    	conn.close();
	    }
		
		return list;
		
		
	}

	*//**
	 * List.
	 *
	 * @return the list
	 * @throws Exception the exception
	 *//*
	public List list() throws Exception{
		return list(0,0);
	}
*/
	public long add(FacultyDTO dto) throws ApplicationException, DuplicateRecordException {
		// TODO Auto-generated method stub
		return 0;
	}

	public void update(FacultyDTO dto) throws ApplicationException, DuplicateRecordException {
		// TODO Auto-generated method stub
		
	}

	public void delete(FacultyDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		
	}

	public FacultyDTO findByEmailId(String emailId) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	public FacultyDTO findByPK(long pk) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	public List search(FacultyDTO dto, int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	public List search(FacultyDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	public List list(int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}
	public List list() throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}
}
