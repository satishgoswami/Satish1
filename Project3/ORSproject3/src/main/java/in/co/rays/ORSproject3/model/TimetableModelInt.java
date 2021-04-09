package in.co.rays.ORSproject3.model;

import java.util.Date;
import java.util.List;

import in.co.rays.ORSproject3.dto.TimetableDTO;
import in.co.rays.ORSproject3.exception.ApplicationException;
import in.co.rays.ORSproject3.exception.DatabaseException;
import in.co.rays.ORSproject3.exception.DuplicateRecordException;

public interface TimetableModelInt {
	 
	/**
     * Add a Timetable
     *
     * @param dto
     * @throws ApplicationException
     * @throws DuplicateRecordException
     *             : throws when Timetable already exists
     */
    public long add(TimetableDTO dto) throws ApplicationException,
            DuplicateRecordException;

    /**
     * Update a Timetable
     *
     * @param dto
     * @throws ApplicationException
     * @throws DuplicateRecordException
     *             : if updated user record is already exist
     */
    public void update(TimetableDTO dto) throws ApplicationException,
            DuplicateRecordException;

    /**
     * Delete a Timetable
     *
     * @param dto
     * @throws ApplicationException
     */
    public void delete(TimetableDTO dto) throws ApplicationException;

   
    /**
     * Find Timetable by PK
     *
     * @param pk
     *            : get parameter
     * @return dto
     * @throws ApplicationException
     */
    public TimetableDTO findByPK(long pk) throws ApplicationException;

    /**
     * Search Timetable with pagination
     *
     * @return list : List of Timetable
     * @param dto
     *            : Search Parameters
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     * @throws ApplicationException
     */
    public List search(TimetableDTO dto, int pageNo, int pageSize)
            throws ApplicationException;

    /**
     * Search Timetable
     *
     * @return list : List of Timetable
     * @param dto
     *            : Search Parameters
     * @throws ApplicationException
     */
    public List search(TimetableDTO dto) throws ApplicationException;

    /**
     * Gets List of Timetable
     *
     * @return list : List of Timetable
     * @throws DatabaseException
     */
    public List list() throws ApplicationException;

    /**
     * get List of Timetable with pagination
     *
     * @return list : List of Timetable
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     * @throws ApplicationException
     */
    public List list(int pageNo, int pageSize) throws ApplicationException;

	/**
	 * @param coursename
	 * @param subname
	 * @param sem
	 * @return
	 */
	public TimetableDTO findByCSS(String coursename, String subname, String sem) throws ApplicationException;

	/**
	 * @param course
	 * @param subject
	 * @param examDate
	 * @return
	 * @throws ApplicationException
	 */
	public TimetableDTO findByCSE(String course, String subject,Date examDate) throws ApplicationException;
}
