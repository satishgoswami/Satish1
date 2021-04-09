package in.co.rays.ORSproject3.model;

import java.util.Date;
import java.util.List;

import in.co.rays.ORSproject3.dto.TimetableDTO;
import in.co.rays.ORSproject3.exception.ApplicationException;
import in.co.rays.ORSproject3.exception.DuplicateRecordException;

public class TimetableModelJDBCImpl implements TimetableModelInt {

	public long add(TimetableDTO dto) throws ApplicationException, DuplicateRecordException {
		// TODO Auto-generated method stub
		return 0;
	}

	public void update(TimetableDTO dto) throws ApplicationException, DuplicateRecordException {
		// TODO Auto-generated method stub
		
	}

	public void delete(TimetableDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		
	}

	public TimetableDTO findByPK(long pk) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	public List search(TimetableDTO dto, int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	public List search(TimetableDTO dto) throws ApplicationException {
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

	public TimetableDTO findByCSS(String coursename, String subname, String sem) {
		// TODO Auto-generated method stub
		return null;
	}

	public TimetableDTO findByCSE(String course, String subject, Date examDate) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

}
