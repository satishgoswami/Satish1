package in.co.rays.ORSproject3.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.rays.ORSproject3.dto.CourseDTO;
import in.co.rays.ORSproject3.dto.SubjectDTO;
import in.co.rays.ORSproject3.exception.ApplicationException;
import in.co.rays.ORSproject3.exception.DuplicateRecordException;
import in.co.rays.ORSproject3.utility.JDBCDataSource;

public  class SubjectModelJDBCImpl implements SubjectModelInt {

	/** The log. */
	private static Logger log = Logger.getLogger(SubjectModelJDBCImpl.class);
	
	/**
	 * Next pk.
	 *
	 * @return the integer
	 * @throws SQLException the SQL exception
	 */
	public Integer nextPk() throws SQLException{
		
        log.debug("");
        
		int pk=0;
		Connection conn=null;
		
		try{
			conn= JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement
					("select max(id) from st_subject");
			
			ResultSet rs= pstmt.executeQuery();
			while(rs.next()){
				pk= rs.getInt(1);
			}
			rs.close();
			pstmt.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			conn.close();
		}
		return pk=pk+1; 
	}

	public long add(SubjectDTO dto) throws ApplicationException, DuplicateRecordException {
		// TODO Auto-generated method stub
		return 0;
	}

	public void update(SubjectDTO dto) throws ApplicationException, DuplicateRecordException {
		// TODO Auto-generated method stub
		
	}

	public void delete(SubjectDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		
	}

	public SubjectDTO findByName(String name) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	public SubjectDTO findByPK(long pk) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	public List search(SubjectDTO dto, int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	public List search(SubjectDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	public List list() throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	public List list(int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Adds the.
	 *
	 * @param bean the bean
	 * @return the long
	 * @throws ApplicationException 
	 * @throws SQLException 
	 *//*
	public long add(SubjectDTO bean) throws ApplicationException, SQLException{
		
		
		log.debug("");
		int pk=0;
		
		
		CourseModelJDBCImpl cmodel= new CourseModelJDBCImpl();
		CourseDTO cbean = cmodel.findByPK(bean.getCourseId());
		System.out.println("course name"+cbean.getCourseName());
		bean.setCourseName(cbean.getCourseName());
		
		
		SubjectDTO beanExist = findByName(bean.getSubjectName());
		System.out.println("beanExist="+beanExist);
		 if(beanExist!=null){
			 throw new Exception("subject name alredy exist");
		 }
		
		
		 Connection conn= null;
		
		try{
			pk=nextPk();
			conn=JDBCDataSource.getConnection();
            conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement
					("insert into st_subject values(?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1,pk);
			pstmt.setString(2,bean.getSubjectName());
			pstmt.setString(3,bean.getDescription());
			pstmt.setLong(4,bean.getCourseId());
			pstmt.setLong(5,bean.getSubjectId());
			pstmt.setString(6,bean.getCourseName());
			pstmt.setString(7,bean.getCreatedBy());
			pstmt.setString(8,bean.getModifiedBy());
			pstmt.setTimestamp(9,bean.getCreatedDatetime());
			pstmt.setTimestamp(10,bean.getModifiedDatetime());
			
			pstmt.executeUpdate();
			System.out.println("record added");
			conn.commit();
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
	 * Delete.
	 *
	 * @param bean the bean
	 * @throws Exception the exception
	 *//*
	public static void delete(SubjectDTO bean) throws Exception{
		log.debug("");
		System.out.println("model delete  started");		
		Connection conn= null;
        
		try{
			conn= JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from st_subject where id=?");
			pstmt.setLong(1,bean.getId());
			int c=pstmt.executeUpdate();
			System.out.println("record deleted         "+c);
			conn.commit();
			pstmt.close();
		}
		catch(Exception e){
			e.printStackTrace();
			conn.rollback();
		}
		finally{
			conn.close();
		}
	}
	
	*//**
	 * Update.
	 *
	 * @param bean the bean
	 * @throws SQLException the SQL exception
	 *//*
	public static void update(SubjectDTO bean) throws SQLException{
		log.debug("");
		System.out.println(" student model update  started");		
		Connection conn= null;
		
		try{
			
			conn= JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement
					("update st_subject set course_id=?,course_name=?,subject_name=?,description=?,created_by=?,modified_by=?,created_Datetime=?,modified_Datetime=?,subject_id=? where id=?");
			pstmt.setLong(1,bean.getCourseId());
			pstmt.setString(2,bean.getCourseName());
			pstmt.setString(3,bean.getSubjectName());
			pstmt.setString(4,bean.getDescription());
			pstmt.setString(5,bean.getCreatedBy());
			pstmt.setString(6,bean.getModifiedBy());
			pstmt.setTimestamp(7,bean.getCreatedDatetime());
			pstmt.setTimestamp(8,bean.getModifiedDatetime());
			pstmt.setLong(9,bean.getSubjectId());
			pstmt.setLong(10, bean.getId());
			pstmt.executeUpdate();
			System.out.println("record updated");
			conn.commit();
			pstmt.close();
		}
		catch(Exception e){
			e.printStackTrace();
			conn.rollback();
		}
		finally{
			conn.close();
		}
	}
	
	*//**
	 * Find by pk.
	 *
	 * @param pk the pk
	 * @return the subject bean
	 * @throws SQLException the SQL exception
	 *//*
	public static SubjectDTO findByPk(long pk) throws SQLException{
		log.debug("");
		System.out.println("studentmodel findBypk  started");	
		
		SubjectDTO bean = new SubjectDTO();
		
		StringBuffer sql = new StringBuffer("select * from st_subject where id=?");
		Connection conn= null;
		
		try{
			conn= JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setLong(1,pk);
			ResultSet rs = pstmt.executeQuery();
			conn.commit();
			while(rs.next()){
				
			  bean.setId(rs.getLong(1));
			  bean.setSubjectName(rs.getString(2));
			  bean.setDescription(rs.getString(3));
			  bean.setCourseId(rs.getInt(4));
			  bean.setSubjectId(rs.getInt(5));
			  bean.setCourseName(rs.getString(6));
			  bean.setCreatedBy(rs.getString(7));
			  bean.setModifiedBy(rs.getString(8));
			  bean.setCreatedDatetime(rs.getTimestamp(9));
			  bean.setModifiedDatetime(rs.getTimestamp(10));
			  			  
			}
			rs.close();
			pstmt.close();
		}
		catch(Exception e){
			e.printStackTrace();
			conn.rollback();
		}
		finally{
			conn.close();
		}
		
		return bean;
		
		
	}
	
	*//**
	 * Find by name.
	 *
	 * @param name the name
	 * @return the subject bean
	 * @throws SQLException the SQL exception
	 *//*
	public SubjectDTO findByName(String name) throws SQLException{
		log.debug("");
		System.out.println("model update  started");	
		
		SubjectDTO bean = null;;
		
		StringBuffer sql = new StringBuffer("select * from st_subject where subject_name=?");
		Connection conn= null;
		
		try{
			conn= JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1,name);
			ResultSet rs = pstmt.executeQuery();
			conn.commit();
			while(rs.next()){
				bean = new SubjectDTO();
				  bean.setId(rs.getLong(1));
				  bean.setSubjectName(rs.getString(2));
				  bean.setDescription(rs.getString(3));
				  bean.setCourseId(rs.getInt(4));
				  bean.setSubjectId(rs.getInt(5));
				  bean.setCourseName(rs.getString(6));
				  bean.setCreatedBy(rs.getString(7));
				  bean.setModifiedBy(rs.getString(8));
				  bean.setCreatedDatetime(rs.getTimestamp(9));
				  bean.setModifiedDatetime(rs.getTimestamp(10));
				  			  	}
			rs.close();
			pstmt.close();
		}
		catch(Exception e){
			e.printStackTrace();
			conn.rollback();
		}
		finally{
			conn.close();
		}
		
		return bean;
		
	}
	
	*//**
	 * Search.
	 *
	 * @param bean the bean
	 * @return the list
	 *//*
	public List search(SubjectDTO bean){
		return search(bean);
	}
	
	*//**
	 * Search.
	 *
	 * @param bean the bean
	 * @param pageNo the page no
	 * @param pageSize the page size
	 * @return the list
	 * @throws SQLException the SQL exception
	 *//*
	public static List search(SubjectDTO bean,int pageNo,int pageSize) throws SQLException{
		
		log.debug("");
		
		List<SubjectDTO> list = new ArrayList<SubjectDTO>();
		
		StringBuffer sql = new StringBuffer
				("select * from st_subject where true");
		
		Connection conn= null;
		
		if(bean!=null){
			
			if(bean.getId()>0){
				sql.append(" And ID ="+bean.getId());
				
			}
			if(bean.getCourseId()>0){
				sql.append(" And COURSE_ID ="+bean.getCourseId());
				
			}
			
			if(bean.getCourseName()!=null && bean.getCourseName().length()>0){
				sql.append(" AND COURSE_NAME LIKE '"+bean.getCourseName()+"%'");
			}
			
			if(bean.getSubjectName()!=null && bean.getSubjectName().length()>0){
				sql.append(" AND SUBJECT_NAME LIKE '"+bean.getSubjectName()+"%'");
			System.out.println("=======================");
			}
			
			if(bean.getDescription()!=null && bean.getDescription().length()>0){
				sql.append(" AND DESCRIPTION LIKE '"+bean.getDescription()+"%'");
			}
		}
		if(pageSize>0){
			pageNo= ((pageNo-1)*pageSize);
			sql.append(" LIMIT "+pageNo+" ,"+pageSize);
		}
		
		System.out.println("sal"+sql);
		try{
			conn= JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			
			ResultSet rs = pstmt.executeQuery();
			conn.commit();
			SubjectDTO bean1=null;
			while(rs.next()){
				bean1 = new SubjectDTO();
				  bean1.setId(rs.getLong(1));
				  bean1.setSubjectName(rs.getString(2));
				  bean1.setDescription(rs.getString(3));
				  bean1.setCourseId(rs.getInt(4));
				  bean1.setSubjectId(rs.getInt(5));
				  bean1.setCourseName(rs.getString(6));
				  bean1.setCreatedBy(rs.getString(7));
				  bean1.setModifiedBy(rs.getString(8));
				  bean1.setCreatedDatetime(rs.getTimestamp(9));
				  bean1.setModifiedDatetime(rs.getTimestamp(10));
				  			  
				  list.add(bean1);
			}
		}
		catch(Exception e){
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
	 * @param pageNo the page no
	 * @param pageSize the page size
	 * @return the list
	 * @throws Exception the exception
	 *//*
	public static List list(int pageNo,int pageSize) throws Exception{
		
		System.out.println("inside list");
		Connection conn=null;
		List<SubjectDTO> list = new ArrayList<SubjectDTO>();
		
		StringBuffer sql = new StringBuffer("select * from st_subject");
		
		if(pageSize>0){
			pageNo= (pageNo-1)*pageSize;
		    sql.append(" LIMIT "+pageNo+" ,"+pageSize);	
		}
	     
		try{
			conn= JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			
			ResultSet rs = pstmt.executeQuery();
			conn.commit();
			SubjectDTO bean=null;
			while(rs.next()){
				bean = new SubjectDTO();
				  bean.setId(rs.getLong(1));
				  bean.setSubjectName(rs.getString(2));
				  bean.setDescription(rs.getString(3));
				  bean.setCourseId(rs.getInt(4));
				  bean.setSubjectId(rs.getInt(5));
				  bean.setCourseName(rs.getString(6));
				  bean.setCreatedBy(rs.getString(7));
				  bean.setModifiedBy(rs.getString(8));
				  bean.setCreatedDatetime(rs.getTimestamp(9));
				  bean.setModifiedDatetime(rs.getTimestamp(10));
				  			    
				  list.add(bean);			}
		}
		catch(Exception e){
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

	public void update(SubjectDTO dto) throws ApplicationException, DuplicateRecordException {
		// TODO Auto-generated method stub
		
	}

	public void delete(SubjectDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		
	}

	public SubjectDTO findByPK(long pk) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	public List search(SubjectDTO dto, int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	public List list(int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}
*/
}
