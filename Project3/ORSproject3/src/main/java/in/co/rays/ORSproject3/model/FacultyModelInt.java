package in.co.rays.ORSproject3.model;

import java.util.List;

import in.co.rays.ORSproject3.dto.FacultyDTO;
import in.co.rays.ORSproject3.exception.ApplicationException;
import in.co.rays.ORSproject3.exception.DatabaseException;
import in.co.rays.ORSproject3.exception.DuplicateRecordException;

public interface FacultyModelInt {
	 /**
     * Add a Faculty
     *
     * @param dto
     * @throws ApplicationException
     * @throws DuplicateRecordException
     *             : throws when Faculty already exists
     */
    public long add(FacultyDTO dto) throws ApplicationException,
            DuplicateRecordException;

    /**
     * Update a Faculty
     *
     * @param dto
     * @throws ApplicationException
     * @throws DuplicateRecordException
     *             : if updated user record is already exist
     */
    public void update(FacultyDTO dto) throws ApplicationException,
            DuplicateRecordException;

    /**
     * Delete a Faculty
     *
     * @param dto
     * @throws ApplicationException
     */
    public void delete(FacultyDTO dto) throws ApplicationException;

    /**
     * Find Faculty by EmailId
     *
     * @param name
     *            : get parameter
     * @return dto
     * @throws ApplicationException
     */
    public FacultyDTO findByEmailId(String emailId) throws ApplicationException;

    /**
     * Find Faculty by PK
     *
     * @param pk
     *            : get parameter
     * @return dto
     * @throws ApplicationException
     */
    public FacultyDTO findByPK(long pk) throws ApplicationException;

    /**
     * Search Faculty with pagination
     *
     * @return list : List of Faculty
     * @param dto
     *            : Search Parameters
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     * @throws ApplicationException
     */
    public List search(FacultyDTO dto, int pageNo, int pageSize)
            throws ApplicationException;

    /**
     * Search Faculty
     *
     * @return list : List of Faculty
     * @param dto
     *            : Search Parameters
     * @throws ApplicationException
     */
    public List search(FacultyDTO dto) throws ApplicationException;

    /**
     * Gets List of Faculty
     *
     * @return list : List of Faculty
     * @throws DatabaseException
     */
    public List list() throws ApplicationException;

    /**
     * get List of Faculty with pagination
     *
     * @return list : List of Faculty
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     * @throws ApplicationException
     */
    public List list(int pageNo, int pageSize) throws ApplicationException;

}
