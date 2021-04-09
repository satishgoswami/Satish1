package in.co.rays.ORSproject3.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.rays.ORSproject3.dto.UserDTO;
import in.co.rays.ORSproject3.exception.ApplicationException;
import in.co.rays.ORSproject3.exception.DatabaseException;
import in.co.rays.ORSproject3.exception.DuplicateRecordException;
import in.co.rays.ORSproject3.exception.RecordNotFoundException;
import in.co.rays.ORSproject3.utility.EmailBuilder;
import in.co.rays.ORSproject3.utility.EmailMessage;
import in.co.rays.ORSproject3.utility.EmailUtility;
import in.co.rays.ORSproject3.utility.JDBCDataSource;

public class UserModelJDBCImpl implements UserModelInt {
	/** The log. */
	public static Logger log = Logger.getLogger(UserModelJDBCImpl.class);
	
	/** The pk. */
	long pk = 0;

	/**
	 * Find next PK of user
	 * 
	 * @throws DatabaseException
	 */

	public long nextPK() throws DatabaseException {
		log.debug("UserModel nextPk started");
		String query = "SELECT MAX(ID) FROM ST_USER";
		Connection con=null;
		UserDTO dto=null;
		try {

			con = JDBCDataSource.getConnection();
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pk = rs.getLong(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.debug("UserModel nextPk ended.");
		return pk + 1;
	}

	/**
	 * Add a User
	 * 
	 * @param dto
	 * @throws ApplicationException,
	 *             DuplicateRecordException
	 * 
	 */

	public long add(UserDTO dto) throws ApplicationException, DuplicateRecordException {
		log.debug("UserModel add started");
		Connection con=null;
		
		UserDTO existdto = findByEmailId(dto.getLogin());
		if (existdto != null) {
			throw new DuplicateRecordException("Login id already exists.");
		}

		try {
			String query = "INSERT INTO ST_USER VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			con = JDBCDataSource.getConnection();
			pk = nextPK();
			con.setAutoCommit(false);
			PreparedStatement ps = con.prepareStatement(query);
			ps.setLong(1, pk);
			ps.setString(2, dto.getFirstName());
			ps.setString(3, dto.getLastName());
			ps.setString(4, dto.getLogin());
			ps.setString(5, dto.getPassword());
			ps.setString(6, dto.getAddress());
			ps.setDate(7, new java.sql.Date(dto.getDob().getTime()));
			ps.setString(8, dto.getMobileNo());
			ps.setLong(9, dto.getRoleId());
			ps.setString(10,dto.getRoleName());
			ps.setString(12, dto.getGender());
			ps.setString(17, dto.getCreatedBy());
			ps.setString(18, dto.getModifiedBy());
			ps.setTimestamp(19, dto.getCreatedDatetime());
			ps.setTimestamp(20, dto.getCreatedDatetime());
			int c = ps.executeUpdate();
			con.commit();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(con);
		}
		log.debug("UserModel add ended.");
		return pk;
	}

	/**
	 * Delete a User
	 * 
	 * @param dto
	 * @throws ApplicationException
	 */
	public void delete(UserDTO dto) throws ApplicationException {
		log.debug("UserModel delete started");
		Connection con=null;
		try {
			String query = "DELETE FROM ST_USER WHERE ID=?";
			con = JDBCDataSource.getConnection();
			PreparedStatement ps = con.prepareStatement(query);
			ps.setLong(1, dto.getId());
			int a = ps.executeUpdate();
			System.out.println("Rows deleted " + a);
			ps.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(con);
		}
		log.debug("UserModel delete ended.");
	}

	/**
	 * Find User by Login
	 * 
	 * @param login
	 *            : get parameter
	 * @return dto
	 * @throws ApplicationException
	 */
	public UserDTO findByEmailId(String login) throws ApplicationException {

		log.debug("Model findByEmailId Started");
		Connection con=null;
		UserDTO dto=null;
		StringBuffer sql = new StringBuffer("SELECT* FROM ST_USER WHERE LOGIN=?");
		// System.out.println("sql is" + sql);

		try {
			con = JDBCDataSource.getConnection();
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setString(1, login);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				dto = new UserDTO();
				dto.setId(rs.getLong(1));
				dto.setFirstName(rs.getString(2));
				dto.setLastName(rs.getString(3));
				dto.setLogin(rs.getString(4));
				dto.setPassword(rs.getString(5));
				dto.setAddress(rs.getString(6));
				dto.setDob(rs.getDate(7));
				dto.setMobileNo(rs.getString(8));
				dto.setRoleId(rs.getLong(9));
				dto.setRoleName(rs.getString(10));
				dto.setGender(rs.getString(12));
				dto.setCreatedBy(rs.getString(17));
				dto.setModifiedBy(rs.getString(18));
				dto.setCreatedDatetime(rs.getTimestamp(19));
				dto.setModifiedDatetime(rs.getTimestamp(20));			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(con);
		}
		log.debug("Model findByEmailId End");
		return dto;
	}

	/**
	 * Update a user
	 * 
	 * @param dto
	 * @throws ApplicationException,
	 *             DuplicateRecordException
	 */
	public void update(UserDTO dto) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection con=null;
		System.out.println(dto.getDob());
		// UserDTO existdto=findByEmailId(dto.getLogin());

		// if(existdto!=null && !(existdto.getId() == dto.getId())){
		// throw new DuplicateRecordException("LoginId is already exist");

		String sql = ("UPDATE ST_USER SET FIRST_NAME=?,LAST_NAME=?,LOGIN=?,PASSWORD=?,ADDRESS=?,DOB=?,MOBILE_NO=?,ROLE_ID=?,ROLE_NAME=?,UNSUCCESSFUL_LOGIN=?,GENDER=?,LAST_LOGIN=? WHERE ID=?");

		try {
			con = JDBCDataSource.getConnection();
			con.setAutoCommit(false); // Begin transaction
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dto.getFirstName());
			ps.setString(2, dto.getLastName());
			ps.setString(3, dto.getLogin());
			ps.setString(4, dto.getPassword());
			ps.setString(5, dto.getAddress());
			ps.setDate(6, new java.sql.Date(dto.getDob().getTime()));
			ps.setString(7, dto.getMobileNo());
			ps.setLong(8, dto.getRoleId());
			ps.setString(9, dto.getRoleName());
			ps.setString(11, dto.getGender());
			ps.setLong(13, dto.getId());
			ps.executeUpdate();
			con.commit(); // End transaction
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			try {
				con.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating User ");
		} finally {
			JDBCDataSource.closeConnection(con);
		}
		log.debug("Model update Ended");
	}

	/**
	 * Find User by PK
	 * 
	 * : get parameter
	 * 
	 * @return dto
	 * @throws DatabaseException
	 * @param pk
	 */

	public UserDTO findByPk(long pk) throws ApplicationException {
		log.debug("Model findByPK started");
		StringBuffer sb = new StringBuffer("select* from st_user where ID=?");
		Connection con=null;
		UserDTO dto=null;
		try {
			con = JDBCDataSource.getConnection();
			PreparedStatement ps = con.prepareStatement(sb.toString());
			ps.setLong(1, pk);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				dto = new UserDTO();
				dto.setId(rs.getLong(1));
				dto.setFirstName(rs.getString(2));
				dto.setLastName(rs.getString(3));
				dto.setLogin(rs.getString(4));
				dto.setPassword(rs.getString(5));
				dto.setAddress(rs.getString(6));
				dto.setDob(rs.getDate(7));
				dto.setMobileNo(rs.getString(8));
				dto.setRoleId(rs.getLong(9));
				dto.setRoleName(rs.getString(10));
				dto.setGender(rs.getString(12));
				dto.setCreatedBy(rs.getString(17));
				dto.setModifiedBy(rs.getString(18));
				dto.setCreatedDatetime(rs.getTimestamp(19));
				dto.setModifiedDatetime(rs.getTimestamp(20));			}
			
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(con);
		}
		log.debug("Model findByPK End");
		return dto;

	}

	/**
	 * Search User with pagination
	 * 
	 * @return list : List of Users
	 * @param bean
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * 
	 * @throws DatabaseException
	 */

	public List search(UserDTO dto, int pageno, int pagesize) throws ApplicationException {
		log.debug("Model search Started");
		Connection con=null;
		StringBuffer sql = new StringBuffer("SELECT* FROM ST_USER WHERE 1=1");

		if (dto != null) {
			if (dto.getId() > 0) {
				sql.append(" AND id = " + dto.getId());
			}
			if (dto.getFirstName() != null && dto.getFirstName().trim().length() > 0) {
				sql.append(" AND FIRST_NAME like '" + dto.getFirstName() + "%'");
			}
			if (dto.getLastName() != null && dto.getLastName().trim().length() > 0) {
				sql.append(" AND LAST_NAME like '" + dto.getLastName() + "%'");
			}
			if (dto.getLogin() != null && dto.getLogin().trim().length() > 0) {
				sql.append(" AND LOGIN like '" + dto.getLogin() + "%'");
			}
			if (dto.getPassword() != null && dto.getPassword().trim().length() > 0) {
				sql.append(" AND PASSWORD like '" + dto.getPassword() + "%'");
			}
			if (dto.getDob() != null && dto.getDob().getDate() > 0) {
				sql.append(" AND DOB = " + dto.getGender());
			}
			if (dto.getMobileNo() != null && dto.getMobileNo().trim().length() > 0) {
				sql.append(" AND MOBILE_NO = " + dto.getMobileNo());
			}
			if (dto.getRoleId() > 0) {
				sql.append(" AND ROLE_ID = " + dto.getRoleId());
			}
			if (dto.getRoleName() != null && dto.getRoleName().trim().length() > 0) {
				sql.append(" AND ROLE_NAME like '" + dto.getRoleName() + "%'");
			}

			if (dto.getGender() != null && dto.getGender().trim().length() > 0) {
				sql.append(" AND GENDER like '" + dto.getGender() + "%'");
			}

		}

		// if page size is greater than zero then apply pagination
		if (pagesize > 0) {
			// Calculate start record index
			pageno = (pageno - 1) * pagesize;

			sql.append(" Limit " + pageno + ", " + pagesize);
			// sql.append(" limit " + pageNo + "," + pageSize);
		}

		System.out.println(sql);
		List list = new ArrayList();

		try {
			con = JDBCDataSource.getConnection();
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				dto = new UserDTO();
				dto.setId(rs.getLong(1));
				dto.setFirstName(rs.getString(2));
				dto.setLastName(rs.getString(3));
				dto.setLogin(rs.getString(4));
				dto.setPassword(rs.getString(5));
				dto.setAddress(rs.getString(6));
				dto.setDob(rs.getDate(7));
				dto.setMobileNo(rs.getString(8));
				dto.setRoleId(rs.getLong(9));
				dto.setRoleName(rs.getString(10));
				dto.setGender(rs.getString(12));
				dto.setCreatedBy(rs.getString(17));
				dto.setModifiedBy(rs.getString(18));
				dto.setCreatedDatetime(rs.getTimestamp(19));
				dto.setModifiedDatetime(rs.getTimestamp(20));

				list.add(dto);
			}
			rs.close();
		} catch (Exception e) {
	         e.printStackTrace();
			log.error("Database Exception..", e);
			//throw new ApplicationException("Exception : Exception in search user");
		} finally {
			JDBCDataSource.closeConnection(con);
		}

		log.debug("Model search Ended");
		return list;
	} 

	/**
	 * Get List of User with pagination
	 * 
	 * @return list : List of users
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws DatabaseException
	 */

	public List list(int pageNo, int pageSize) throws ApplicationException {

		log.debug("Model list Started");
		Connection con=null;
		UserDTO dto=null;
		List list = new ArrayList();
		StringBuffer sql = new StringBuffer("select * from ST_USER");

		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}

		try {
			con = JDBCDataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new UserDTO();
				dto.setId(rs.getLong(1));
				dto.setFirstName(rs.getString(2));
				dto.setLastName(rs.getString(3));
				dto.setLogin(rs.getString(4));
				dto.setPassword(rs.getString(5));
				dto.setAddress(rs.getString(6));
				dto.setDob(rs.getDate(7));
				dto.setMobileNo(rs.getString(8));
				dto.setRoleId(rs.getLong(9));
				dto.setRoleName(rs.getString(10));
				dto.setGender(rs.getString(12));
				dto.setCreatedBy(rs.getString(17));
				dto.setModifiedBy(rs.getString(18));
				dto.setCreatedDatetime(rs.getTimestamp(19));
				dto.setModifiedDatetime(rs.getTimestamp(20));
				list.add(dto);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting list of users");
		} finally {
			JDBCDataSource.closeConnection(con);
		}

		log.debug("Model list End");
		return list;

	}

	/**
	 * Authenticate.
	 *
	 * @param login the login
	 * @param pass the pass
	 * @return the user bean
	 * @throws ApplicationException the application exception
	 */
	public UserDTO authenticate(String login,String pass) throws ApplicationException {

		log.debug("Model authenticate Started");
		Connection con=null;
		UserDTO dto=null;
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_USER WHERE LOGIN = ? AND PASSWORD=?");

		try {
			con = JDBCDataSource.getConnection();
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setString(1, login);
			ps.setString(2, pass);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				dto = new UserDTO();
				dto.setId(rs.getLong(1));
				dto.setFirstName(rs.getString(2));
				dto.setLastName(rs.getString(3));
				dto.setLogin(rs.getString(4));
				dto.setPassword(rs.getString(5));
				dto.setAddress(rs.getString(6));
				dto.setDob(rs.getDate(7));
				dto.setMobileNo(rs.getString(8));
				dto.setRoleId(rs.getLong(9));
				dto.setRoleName(rs.getString(10));
				dto.setGender(rs.getString(12));
				dto.setCreatedBy(rs.getString(17));
				dto.setModifiedBy(rs.getString(18));
				dto.setCreatedDatetime(rs.getTimestamp(19));
				dto.setModifiedDatetime(rs.getTimestamp(20));

			}
		} catch (Exception e) {
			log.error("Database Exception..", e);
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in get roles");

		} finally {
			JDBCDataSource.closeConnection(con);
		}

		log.debug("Model authenticate End");
		return dto;
	}
	
	/**
	 * Register user.
	 *
	 * @param dto the dto
	 * @return the long
	 * @throws ApplicationException the application exception
	 * @throws DuplicateRecordException the duplicate record exception
	 */
	public long registerUser(UserDTO dto) throws ApplicationException, DuplicateRecordException {

		log.debug("Model RegisterUser Started");
		long pk = add(dto);

		HashMap<String, String> map = new HashMap<String, String>();

		map.put("login", dto.getLogin());
		map.put("password", dto.getPassword());

		String message = EmailBuilder.getUserRegistrationMessage(map);
		EmailMessage msg = new EmailMessage();

		msg.setTo(dto.getLogin());
		msg.setSubject("Registration is successful for ORS Project SunilOS");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);

		EmailUtility.sendMail(msg);
		return pk;
	}	/**
	 * @param id
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 * @throws RecordNotFoundException
	 * @throws ApplicationException
	 */
	public  boolean changePassword(long id, String oldPassword, String newPassword) throws ApplicationException,  RecordNotFoundException 
	{
		log.debug("changePassword started");
		Connection con = null;
		PreparedStatement ps=null;
		boolean flag=false;
		
		UserDTO dto = new UserDTO();
		
	dto = findByPk(id);
		System.out.println(dto.getFirstName());
		
		if(dto!=null && dto.getPassword().equals(oldPassword) )
		{	System.out.println("password matched");
			
		dto.setPassword(newPassword);
		try{
		update(dto);
		}
		 catch (DuplicateRecordException e) {
				e.printStackTrace();
			}
		
		flag=true;
		}
		else {
			throw new RecordNotFoundException("Login not exist");
		}
		/*	HashMap<String, String> map = new HashMap<String, String>();
			map.put("login", ub.getLogin());
	        map.put("password", ub.getPassword());
	        map.put("firstName", ub.getFirstName());
	        map.put("lastName", ub.getLastName());
	        
	       String message = EmailBuilder.getChangePasswordMessage(map);
			
	       EmailMessage msg = new EmailMessage();
	       msg.setTo(ub.getLogin());
	       msg.setSubject("SUNARYS ORS Password has been changed Successfully.");
	       msg.setMessage(message);
	       msg.setMessageType(EmailMessage.HTML_MSG); 
	       
	       EmailUtility.sendMail(msg);
		*/
		return flag;
	}
	
	/**
	 * Send the password of User to his Email
	 * 
	 * @return boolean : true if success otherwise false
	 * @param login
	 *            : User Login
	 * @throws ApplicationException
	 * @throws RecordNotFoundException
	 *             : if user not found
	 */
	public  boolean forgetPassword(String login) throws ApplicationException, RecordNotFoundException 
	{
		UserDTO bean = new UserDTO();
		boolean flag=false;
		bean = findByEmailId(login);
		if(bean==null)
		{
		  throw new RecordNotFoundException("Login Id Not Found");	
		}else{
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("login", bean.getLogin());
		map.put("password", bean.getPassword());
		map.put("firstName", bean.getFirstName());
		map.put("lastName", bean.getLastName());
		String message = EmailBuilder.getForgetPasswordMessage(map);
		
		EmailMessage msg = new EmailMessage();
		msg.setTo(login);
		msg.setSubject("Password Reset");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);
		
		EmailUtility.sendMail(msg);
		flag=true;
		}
		return flag;
	}





}
