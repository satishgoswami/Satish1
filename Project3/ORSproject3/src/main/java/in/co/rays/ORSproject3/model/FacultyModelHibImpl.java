package in.co.rays.ORSproject3.model;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.ORSproject3.dto.CollegeDTO;
import in.co.rays.ORSproject3.dto.CourseDTO;
import in.co.rays.ORSproject3.dto.FacultyDTO;
import in.co.rays.ORSproject3.dto.SubjectDTO;
import in.co.rays.ORSproject3.exception.ApplicationException;
import in.co.rays.ORSproject3.exception.DatabaseException;
import in.co.rays.ORSproject3.exception.DuplicateRecordException;
import in.co.rays.ORSproject3.utility.HibDataSource;

public class FacultyModelHibImpl implements FacultyModelInt {
    private static Logger log = Logger.getLogger(FacultyModelHibImpl.class);

    /**
     * Add a Faculty
     *
     * @param dto
     * @throws DatabaseException
     *
     */
    public long add(FacultyDTO dto) throws ApplicationException,
            DuplicateRecordException {

    	
    	
        FacultyDTO dtoExist = findByEmailId(dto.getEmailId());

        if (dtoExist != null) {
            throw new DuplicateRecordException("LoginId is already exist");
        }

    	CollegeModelInt model=ModelFactory.getInstance().getCollegeModel();
    	CollegeDTO cdto=  model.findByPK(dto.getCollegeId());
    	dto.setCollegeName(cdto.getName());
    	
    	CourseModelInt coursemodel = ModelFactory.getInstance().getCourseModel();
		  CourseDTO codto = coursemodel.findByPK(dto.getCourseId());
		  dto.setCourseName(codto.getCourseName());
		
		  SubjectModelInt subjectmodel = ModelFactory.getInstance().getSubjectModel();
		  SubjectDTO sdto = subjectmodel.findByPK(dto.getSubjectId());
		  dto.setSubjectName(sdto.getSubjectName());
		  
    	
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
            throw new ApplicationException("Exception in Faculty Add "
                    + e.getMessage());
        } finally {
            session.close();
        }

        log.debug("Model add End");
        return dto.getId();
    }

    /**
     * Delete a Faculty
     *
     * @param dto
     * @throws DatabaseException
     */
     
    public void delete(FacultyDTO dto) throws ApplicationException {
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
            throw new ApplicationException("Exception in Faculty Delete"
                    + e.getMessage());
        } finally {
            session.close();
        }
        log.debug("Model delete End");
    }

    /**
     * Find Faculty by Login
     *
     * @param login
     *            : get parameter
     * @return dto
     * @throws DatabaseException
     */
     
    public FacultyDTO findByEmailId(String email) throws ApplicationException {
        log.debug("Model findByLoginId Started");
        Session session = null;
        FacultyDTO dto = null;
        try {
            session = HibDataSource.getSession();
            Criteria criteria = session.createCriteria(FacultyDTO.class);
            criteria.add(Restrictions.eq("emailId", email));
            List list = criteria.list();

            if (list.size() == 1) {
                dto = (FacultyDTO) list.get(0);
            }

        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception in getting Faculty by email " + e.getMessage());

        } finally {
            session.close();
        }

        log.debug("Model findByLoginId End");
        return dto;
    }

    /**
     * Find Faculty by PK
     *
     * @param pk
     *            : get parameter
     * @return dto
     * @throws DatabaseException
     */
     
    public FacultyDTO findByPK(long pk) throws ApplicationException {
        log.debug("Model findByPK Started");
        Session session = null;
        FacultyDTO dto = null;
        try {
            session = HibDataSource.getSession();
            dto = (FacultyDTO) session.get(FacultyDTO.class, pk);
        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting Faculty by pk");
        } finally {
            session.close();
        }

        log.debug("Model findByPK End");
        return dto;
    }

    /**
     * Update a Faculty
     *
     * @param dto
     * @throws DatabaseException
     */
     
    public void update(FacultyDTO dto) throws ApplicationException,
            DuplicateRecordException {
        log.debug("Model update Started");
        Session session = null;
        Transaction transaction = null;

        FacultyDTO dtoExist = findByEmailId(dto.getEmailId());

        // Check if updated Email already exist
        if (dtoExist != null && dtoExist.getId() != dto.getId()) {
            throw new DuplicateRecordException("Email is already exist");
        }
        CollegeModelInt model=ModelFactory.getInstance().getCollegeModel();
    	CollegeDTO cdto=  model.findByPK(dto.getCollegeId());
    	dto.setCollegeName(cdto.getName());
    	
    	CourseModelInt coursemodel = ModelFactory.getInstance().getCourseModel();
		  CourseDTO codto = coursemodel.findByPK(dto.getCourseId());
		  dto.setCourseName(codto.getCourseName());
		
		  SubjectModelInt subjectmodel = ModelFactory.getInstance().getSubjectModel();
		  SubjectDTO sdto = subjectmodel.findByPK(dto.getSubjectId());
		  dto.setSubjectName(sdto.getSubjectName());
		

        try {
            session = HibDataSource.getSession();
            transaction = session.beginTransaction();
            session.update(dto);
            transaction.commit();
        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            if (transaction != null) {
                transaction.rollback();
                throw new ApplicationException("Exception in Faculty Update"
                        + e.getMessage());
            }
        } finally {
            session.close();
        }
        log.debug("Model update End");
    }

    /**
     * Searches Faculty
     *
     * @param dto
     *            : Search Parameters
     * @throws DatabaseException
     */
     
    public List search(FacultyDTO dto) throws ApplicationException {
        return search(dto, 0, 0);
    }

    /**
     * Searches Faculty with pagination
     *
     * @return list : List of Faculty
     * @param dto
     *            : Search Parameters
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     *
     * @throws DatabaseException
     */
     
    public List search(FacultyDTO dto, int pageNo, int pageSize)
            throws ApplicationException {

        log.debug("Model search Started");
        Session session = null;
        List list = null;
        try {
            session = HibDataSource.getSession();
            Criteria criteria = session.createCriteria(FacultyDTO.class);

            if (dto.getId() > 0) {
                criteria.add(Restrictions.eq("id", dto.getId()));
            }
            if (dto.getFirstName() != null && dto.getFirstName().length() > 0) {
                criteria.add(Restrictions.like("firstName", dto.getFirstName()
                        + "%"));
            }
            if (dto.getCollegeId() > 0) {
                criteria.add(Restrictions.eq("collegeId", dto.getCollegeId()));
            }
            if (dto.getCourseId() > 0) {
                criteria.add(Restrictions.eq("courseId", dto.getCourseId()));
            }
            if (dto.getLastName() != null && dto.getLastName().length() > 0) {
                criteria.add(Restrictions.like("lastName", dto.getLastName()
                        + "%"));
            }
           
            if (dto.getEmailId() != null && dto.getEmailId().length() > 0) {
                criteria.add(Restrictions.like("email", dto.getEmailId()
                        + "%"));
            }
            if (dto.getMobileNo() != null && dto.getMobileNo().length() > 0) {
                criteria.add(Restrictions.like("mobileNo", dto.getMobileNo()
                        + "%"));
            }

            // if page size is greater than zero the apply pagination
            if (pageSize > 0) {
                criteria.setFirstResult(((pageNo - 1) * pageSize));
                criteria.setMaxResults(pageSize);
            }

            list = criteria.list();
        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            throw new ApplicationException("Exception in Faculty search");
        } finally {
            session.close();
        }

        log.debug("Model search End");
        return list;
    }

    /**
     * Gets List of Faculty
     *
     * @return list : List of Faculty
     * @throws DatabaseException
     */
     
    public List list() throws ApplicationException {
        return list(0, 0);
    }

    /**
     * get List of Faculty with pagination
     *
     * @return list : List of Faculty
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
            Criteria criteria = session.createCriteria(FacultyDTO.class);

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
                    "Exception : Exception in  Faculty list");
        } finally {
            session.close();
        }

        log.debug("Model list End");
        return list;
    }

}
