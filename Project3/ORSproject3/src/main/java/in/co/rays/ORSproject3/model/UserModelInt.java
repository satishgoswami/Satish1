package in.co.rays.ORSproject3.model;

import java.util.List;

import antlr.RecognitionException;
import in.co.rays.ORSproject3.dto.UserDTO;
import in.co.rays.ORSproject3.dto.UserDTO;
import in.co.rays.ORSproject3.exception.ApplicationException;
import in.co.rays.ORSproject3.exception.DatabaseException;
import in.co.rays.ORSproject3.exception.DuplicateRecordException;
import in.co.rays.ORSproject3.exception.RecordNotFoundException;

/**
 * Data Access Object of User
 *
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 */

public interface UserModelInt {

	/**
	 * Add a User
	 *
	 * @param dto
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 *             : throws when User already exists
	 */
	public long add(UserDTO dto) throws ApplicationException, DuplicateRecordException;

	/**
	 * Update a User
	 * 
	 * @param dto
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 */
	public void update(UserDTO dto) throws ApplicationException, DuplicateRecordException;

	/**
	 * Delete a User
	 * 
	 * @param dto
	 * @throws ApplicationException
	 */
	public void delete(UserDTO dto) throws ApplicationException;

	/**
	 * find a User using pk
	 * 
	 * @param pk
	 * @return
	 * @throws ApplicationException
	 * @throws RecordNotFoundException
	 */
	public UserDTO findByPk(long pk) throws ApplicationException, RecordNotFoundException;

	/**
	 * find User using Login Id
	 * 
	 * @param emailid
	 * @return
	 * @throws ApplicationException
	 * @throws RecordNotFoundException
	 */
	public UserDTO findByEmailId(String emailid) throws ApplicationException, RecordNotFoundException;
    /**
     * Search User with pagination
     *
     * @return list : List of User
     * @param dto
     *            : Search Parameters
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     * @throws ApplicationException
     */
    public List search(UserDTO dto, int pageNo, int pageSize)
            throws ApplicationException;
/*
    *//**
     * Search User
     *
     * @return list : List of User
     * @param dto
     *            : Search Parameters
     * @throws ApplicationException
     *//*
    public List search(UserDTO dto) throws ApplicationException;
*/
    /**
     * Gets List of College
     *
     * @return list : List of College
     * @throws DatabaseException
     *//*
    public List list() throws ApplicationException;
*/
    /**
     * get List of College with pagination
     *
     * @return list : List of College
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     * @throws ApplicationException
     */
    public List list(int pageNo, int pageSize) throws ApplicationException;
	/**
	 * Authenticate.
	 *
	 * @param login the login
	 * @param pass the pass
	 * @return the user bean
	 * @throws ApplicationException the application exception
	 */
    
    public UserDTO authenticate(String login,String pass) throws ApplicationException;
	
    /**
	 * Register user.
	 *
	 * @param dto the dto
	 * @return the long
	 * @throws ApplicationException the application exception
	 * @throws DuplicateRecordException the duplicate record exception
	 */
	public long registerUser(UserDTO dto) throws ApplicationException, DuplicateRecordException;

	/**
	 * @param id
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 * @throws RecordNotFoundException
	 * @throws ApplicationException
	 */
	public  boolean changePassword(long id, String oldPassword, String newPassword) throws ApplicationException,  RecordNotFoundException;

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
	public  boolean forgetPassword(String login) throws ApplicationException, RecordNotFoundException;
}