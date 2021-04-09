package in.co.rays.ORSproject3.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;



import in.co.rays.ORSproject3.dto.CourseDTO;
import in.co.rays.ORSproject3.exception.ApplicationException;
import in.co.rays.ORSproject3.exception.DatabaseException;
import in.co.rays.ORSproject3.exception.DuplicateRecordException;
import in.co.rays.ORSproject3.utility.JDBCDataSource;

public class CourseModelJDBCImpl implements CourseModelInt {
	
	public   Logger log = Logger.getLogger(CourseModelJDBCImpl.class);

	/**
	 * Find next PK of Course
	 * 
	 * @return long
	 * @throws DatabaseException
	 */
	public   long nextPk() throws DatabaseException {
		log.debug("CourseModel nextPk started");
		long i = 0;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			con = JDBCDataSource.getConnection();
			con.setAutoCommit(false);
			ps = (PreparedStatement) con.prepareStatement("SELECT MAX(ID) FROM ST_COURSE");
			rs = ps.executeQuery();

			while (rs.next()) {
				i = rs.getLong(1);
			}
			con.commit();
			ps.close();
			rs.close();
		} catch (Exception e) {
			throw new DatabaseException("exception in CourseModel  nextPK..... " + e.getMessage());
		}
		log.debug("CourseModel nextPk ended");
		return i + 1;

	}

	/**
	 * Add a Course
	 * 
	 * @param bean
	 *            :bean
	 * @return 
	 *
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 */
	public long add(CourseDTO bean) throws ApplicationException, DuplicateRecordException {
		log.debug("CourseModel add Started");
		CourseDTO duplicataRole = findByName(bean.getCourseName());
		// Check if create Role already exist
		if (duplicataRole != null) {
			throw new DuplicateRecordException("Course already exists");
		}
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			con = JDBCDataSource.getConnection();
			con.setAutoCommit(false);
			ps = (PreparedStatement) con.prepareStatement("INSERT INTO ST_COURSE VALUES(?,?,?,?,?,?,?,?)");
			ps.setLong(1, nextPk());
			ps.setString(2, bean.getCourseName());
			ps.setString(3, bean.getDescription());
			ps.setString(4, bean.getDuration());
			ps.setString(5, bean.getCreatedBy());
			ps.setString(6, bean.getModifiedBy());
			ps.setTimestamp(7, bean.getCreatedDatetime());
			ps.setTimestamp(8, bean.getModifiedDatetime());
			int c = ps.executeUpdate();
			System.out.println("Rows added" + c);
			ps.close();
			con.commit();
		} catch (Exception e) {
			throw new ApplicationException("exception in course model add" + e.getMessage());
		}
		JDBCDataSource.closeConnection(con);
		log.debug("CourseModel add ended");
		return 0;
	}

	/**
	 * Update a Course
	 * 
	 * @param bean
	 *            :bean
	 * @throws ApplicationException
	 * 
	 */
	public  void update(CourseDTO bean) throws ApplicationException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = JDBCDataSource.getConnection();
			ps = con.prepareStatement("UPDATE ST_COURSE SET COURSE_NAME=?,DURATION=?,Description=?"
					+ ",CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?," + "MODIFIED_DATETIME=? WHERE ID=?");
			con.setAutoCommit(false);
			ps.setString(1, bean.getCourseName());
			ps.setString(2, bean.getDuration());
			ps.setString(3, bean.getDescription());
			ps.setString(4, bean.getCreatedBy());
			ps.setString(5, bean.getModifiedBy());
			ps.setTimestamp(6, bean.getCreatedDatetime());
			ps.setTimestamp(7, bean.getModifiedDatetime());
			ps.setLong(8, bean.getId());
			int x=ps.executeUpdate();
			System.out.println("record updated "+x);
			ps.close();
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
			//throw new ApplicationException("exception in course model update...." + e.getMessage());
		}
		JDBCDataSource.closeConnection(con);

	}

	/**
	 * Delete a Course
	 * 
	 * @param bean
	 *            :bean
	 * @throws ApplicationException
	 */
	public   void delete(CourseDTO bean) throws ApplicationException {
		log.debug("CourseModel delete Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			// System.out.println("conn start");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement("DELETE FROM ST_COURSE WHERE ID=?");
			pstmt.setLong(1, bean.getId());
			int i = pstmt.executeUpdate();
			System.out.println("rows deleted " + i);
			conn.commit(); // End transaction
			pstmt.close();

		} catch (Exception e) {
			throw new ApplicationException("exception in course model delete ... " + e.getMessage());
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("CourseModel delete ended");
	}

	/**
	 * Find Course by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return bean :bean
	 * @throws ApplicationException
	 */
	public   CourseDTO findByPK(long pk) throws ApplicationException {
		log.debug("CourseModel findByPk Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_COURSE WHERE ID=?");
		CourseDTO bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new CourseDTO();
				bean.setId(rs.getLong(1));
				bean.setCourseName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setDuration(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));
			}
			pstmt.close();
			rs.close();
		} catch (Exception e) {
			throw new ApplicationException("exception in course model findByPK..." + e.getMessage());
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("CourseModel findByPk ended");
		return bean;

	}

	/**
	 * Find Course by Course Name
	 * 
	 * @param Name
	 *            : get parameter
	 * @return bean
	 * @throws ApplicationException
	 */
	public   CourseDTO findByName(String Name) throws ApplicationException {
		log.debug("CourseModel findByName Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_COURSE WHERE COURSE_NAME=?");
		CourseDTO bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql.toString());
			pstmt.setString(1, Name);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new CourseDTO();
				bean.setId(rs.getLong(1));
				bean.setCourseName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setDuration(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));
			}
			pstmt.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("CourseModel findByName ended");
		return bean;
	}

	/**
	 * Search Course with pagination
	 * 
	 * @return list : List of Course
	 * @param bean
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * 
	 * @throws ApplicationException
	 */

	public   List search(CourseDTO bean, int pageNo, int pageSize) throws ApplicationException

	{
		log.debug("CourseModel search started");

		StringBuffer sql = new StringBuffer("SELECT * FROM ST_COURSE WHERE 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getCourseName() != null && bean.getCourseName().trim().length() > 0) {
				sql.append(" AND COURSE_NAME like '" + bean.getCourseName() + "%'");
			}
			if (bean.getDuration() != null && bean.getDuration().trim().length() > 0) {
				sql.append(" AND DURATION like '" + bean.getDuration() + "%'");
			}

		}

		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + ", " + pageSize);
			// sql.append(" limit " + pageNo + "," + pageSize);
		}

		List<CourseDTO> list = new ArrayList<CourseDTO>();
		Connection conn = null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn = JDBCDataSource.getConnection();
			pstmt = (PreparedStatement) conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new CourseDTO();
				bean.setId(rs.getLong(1));
				bean.setCourseName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setDuration(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));
				list.add(bean);
			}
			pstmt.close();
			rs.close();
		} catch (Exception e) {
			throw new ApplicationException("exception in course model search..." + e.getMessage());
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("CourseModel search ended");
		return list;
	}

	/**
	 * Search Course
	 * 
	 * @param bean
	 *            : Search Parameters
	 * @throws ApplicationException
	 */
	public   List search(CourseDTO bean) throws ApplicationException {
		return search(bean, 0, 0);
	}

	/**
	 * Get List of Course with pagination
	 * 
	 * @return list : List of Course
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws ApplicationException
	 */

	public   List list(int pageNo, int pageSize) throws ApplicationException {
		log.debug("CourseModel list started");
		List<CourseDTO> list = new ArrayList<CourseDTO>();
		Connection con = null;
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_COURSE WHERE true");

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" Limit " + pageNo + "," + pageSize);
		}
		System.out.println("FINAL SQL OF COURSE list :" + sql);
		PreparedStatement ps = null;
		CourseDTO bean;
		try {
			con = JDBCDataSource.getConnection();
			ps = (PreparedStatement) con.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				bean = new CourseDTO();
				bean.setId(rs.getLong(1));
				bean.setCourseName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setDuration(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			throw new ApplicationException("exception in course model list..." + e.getMessage());
		} finally {
			JDBCDataSource.closeConnection(con);
		}
		log.debug("CourseModel list ended");
		return list;
	}

	/**
	 * Get List of Course
	 * 
	 * @return list : List of Course
	 * @throws ApplicationException
	 */
	public   List list() throws ApplicationException {
		return list(0, 0);
	}

}
