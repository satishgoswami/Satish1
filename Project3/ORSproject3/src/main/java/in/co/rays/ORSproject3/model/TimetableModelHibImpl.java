package in.co.rays.ORSproject3.model;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.ORSproject3.dto.CourseDTO;
import in.co.rays.ORSproject3.dto.SubjectDTO;
import in.co.rays.ORSproject3.dto.TimetableDTO;
import in.co.rays.ORSproject3.exception.ApplicationException;
import in.co.rays.ORSproject3.exception.DatabaseException;
import in.co.rays.ORSproject3.exception.DuplicateRecordException;
import in.co.rays.ORSproject3.utility.HibDataSource;

/**
 * @author '
 *
 */
public class TimetableModelHibImpl implements TimetableModelInt {
    private static Logger log = Logger.getLogger(TimetableModelHibImpl.class);

    /**
     * Add a Timetable
     *
     * @param dto
     * @throws DatabaseException
     *
     */
    public long add(TimetableDTO dto) throws ApplicationException,
            DuplicateRecordException {


    	
    	CourseModelInt coursemodel = ModelFactory.getInstance().getCourseModel();
		  CourseDTO codto = coursemodel.findByPK(dto.getCourseId());
		  dto.setCourseName(codto.getCourseName());
		
		  SubjectModelInt subjectmodel = ModelFactory.getInstance().getSubjectModel();
		  SubjectDTO sdto = subjectmodel.findByPK(dto.getSubId());
		  dto.setSubName(sdto.getSubjectName());
		  
    TimetableDTO dto1=findByCSS(dto.getCourseName(),dto.getSubName(), dto.getSemester());
    TimetableDTO dto2=findByCSE(dto.getCourseName(),dto.getSubName(), dto.getExamDate());

    if(dto1!=null || dto2!=null){
    	throw new DuplicateRecordException("Record Already exist");
    }
    
    	log.debug("Model add Started");
        long pk = 0;
        Session session = HibDataSource.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(dto);
            pk = dto.getId();
            transaction.commit();
        } catch (HibernateException e) {
        	e.printStackTrace();
            log.error("Database Exception..", e);
            if (transaction != null) {
                transaction.rollback();
            }
            throw new ApplicationException("Exception in Timetable Add "
                    + e.getMessage());
        } finally {
            session.close();
        }

        log.debug("Model add End");
        return dto.getId();
    }

    /**
     * Delete a Timetable
     *
     * @param dto
     * @throws DatabaseException
     */
     
    public void delete(TimetableDTO dto) throws ApplicationException {
        log.debug("Model delete Started");
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibDataSource.getSession();
            transaction = session.beginTransaction();
            session.delete(dto);
            transaction.commit();
        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            if (transaction != null) {
                transaction.rollback();
            }
            throw new ApplicationException("Exception in Timetable Delete"
                    + e.getMessage());
        } finally {
            session.close();
        }
        log.debug("Model delete End");
    }

    /**
     * Find Timetable by coursename,subname,semester
     *
     * @param coursename,subname,semester
     *            : get parameter
     * @return dto
     * @throws DatabaseException
     */
     
    public TimetableDTO findByCSS(String course, String subject, String sem) throws ApplicationException {
        log.debug("Model findByCSS Started");
        Session session = null;
        TimetableDTO dto = null;
        try {
            session = HibDataSource.getSession();
            Criteria criteria = session.createCriteria(TimetableDTO.class);
            criteria.add(Restrictions.eq("courseName", course));
            criteria.add(Restrictions.eq("subName", subject));
            criteria.add(Restrictions.eq("semester", sem));
            List list = criteria.list();

            if (list.size() == 1) {
                dto = (TimetableDTO) list.get(0);
            }

        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception in getting Timetable by email " + e.getMessage());

        } finally {
            session.close();
        }

        log.debug("Model findByLoginId End");
        return dto;
    }

    /**
     * find timetable by course,subject,examDate
     * @param course
     * @param subject
     * @param examDate
     * @return
     * @throws ApplicationException
     */
    public TimetableDTO findByCSE(String course, String subject,Date examDate) throws ApplicationException {
        log.debug("Model findByCSE Started");
        Session session = null;
        TimetableDTO dto = null;
        try {
        	
            session = HibDataSource.getSession();
            Criteria criteria = session.createCriteria(TimetableDTO.class);
            criteria.add(Restrictions.eq("courseName", course));
            criteria.add(Restrictions.eq("subName", subject));
            criteria.add(Restrictions.eq("examDate", examDate));
             
            List list = criteria.list();

            if (list.size() == 1) {
                dto = (TimetableDTO) list.get(0);
            }

        } catch (HibernateException e) {
        	e.printStackTrace();
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception in getting Timetable by CSE " + e.getMessage());

        } finally {
            session.close();
        }

        log.debug("Model findByLoginId End");
        return dto;
    }

    
    
    /**
     * Find Timetable by PK
     *
     * @param pk
     *            : get parameter
     * @return dto
     * @throws DatabaseException
     */
     
    public TimetableDTO findByPK(long pk) throws ApplicationException {
        log.debug("Model findByPK Started");
        Session session = null;
        TimetableDTO dto = null;
        try {
            session = HibDataSource.getSession();
            dto = (TimetableDTO) session.get(TimetableDTO.class, pk);
        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting Timetable by pk");
        } finally {
            session.close();
        }

        log.debug("Model findByPK End");
        return dto;
    }

    /**
     * Update a Timetable
     *
     * @param dto
     * @throws DatabaseException
     */
     
    public void update(TimetableDTO dto) throws ApplicationException,
            DuplicateRecordException {
        log.debug("Model update Started");
        
        CourseModelInt coursemodel = ModelFactory.getInstance().getCourseModel();
		  CourseDTO codto = coursemodel.findByPK(dto.getCourseId());
		  dto.setCourseName(codto.getCourseName());
		
		  SubjectModelInt subjectmodel = ModelFactory.getInstance().getSubjectModel();
		  SubjectDTO sdto = subjectmodel.findByPK(dto.getSubId());
		  dto.setSubName(sdto.getSubjectName());
        
        TimetableDTO dto1=findByCSS(dto.getCourseName(),dto.getSubName(), dto.getSemester());
        TimetableDTO dto2=findByCSE(dto.getCourseName(),dto.getSubName(), dto.getExamDate());

        if(dto1!=null || dto2!=null){
        	throw new DuplicateRecordException("Record Already exist");
        }        
        
        Session session = null;
        Transaction transaction = null;
        
        try {
            session = HibDataSource.getSession();
            transaction = session.beginTransaction();
            session.update(dto);
            transaction.commit();
        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            if (transaction != null) {
                transaction.rollback();
                throw new ApplicationException("Exception in Timetable Update"
                        + e.getMessage());
            }
        } finally {
            session.close();
        }
        log.debug("Model update End");
    }

    /**
     * Searches Timetable
     *
     * @param dto
     *            : Search Parameters
     * @throws DatabaseException
     */
     
    public List search(TimetableDTO dto) throws ApplicationException {
        return search(dto, 0, 0);
    }

    /**
     * Searches Timetable with pagination
     *
     * @return list : List of Timetable
     * @param dto
     *            : Search Parameters
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     *
     * @throws DatabaseException
     */
     
    public List search(TimetableDTO dto, int pageNo, int pageSize)
            throws ApplicationException {

        log.debug("Model search Started");
        Session session = null;
        List list = null;
        try {
            session = HibDataSource.getSession();
            Criteria criteria = session.createCriteria(TimetableDTO.class);

            if (dto.getId() > 0) {
                criteria.add(Restrictions.eq("id", dto.getId()));
            }
            if (dto.getCourseId() > 0) {
                criteria.add(Restrictions.eq("courseId", dto.getCourseId()));
            }
            if (dto.getSubId() > 0) {
                criteria.add(Restrictions.eq("subId", dto.getSubId()));
            }
            if (dto.getCourseName() != null && dto.getCourseName().length() > 0) {
                criteria.add(Restrictions.like("courseName", dto.getCourseName()
                        + "%"));
            }
            if (dto.getSubName() != null && dto.getSubName().length() > 0) {
                criteria.add(Restrictions.like("subName", dto.getSubName()
                        + "%"));
            }
            if (dto.getExamDate() != null) {
                criteria.add(Restrictions.like("examDate", dto.getExamDate()));
            }
           
            // if page size is greater than zero the apply pagination
            if (pageSize > 0) {
                criteria.setFirstResult(((pageNo - 1) * pageSize));
                criteria.setMaxResults(pageSize);
            }

            list = criteria.list();
        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            throw new ApplicationException("Exception in Timetable search");
        } finally {
            session.close();
        }

        log.debug("Model search End");
        return list;
    }

    /**
     * Gets List of Timetable
     *
     * @return list : List of Timetable
     * @throws DatabaseException
     */
     
    public List list() throws ApplicationException {
        return list(0, 0);
    }

    /**
     * get List of Timetable with pagination
     *
     * @return list : List of Timetable
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     * @throws DatabaseException
     */
    public List list(int pageNo, int pageSize) throws ApplicationException {
        log.debug("Model list Started");
        Session session = null;
        List list = null;
        try {
            session = HibDataSource.getSession();
            Criteria criteria = session.createCriteria(TimetableDTO.class);

            // if page size is greater than zero then apply pagination
            if (pageSize > 0) {
                pageNo = ((pageNo - 1) * pageSize) + 1;
                criteria.setFirstResult(pageNo);
                criteria.setMaxResults(pageSize);
            }

            list = criteria.list();
        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in  Timetable list");
        } finally {
            session.close();
        }

        log.debug("Model list End");
        return list;
    }

	
	
}
