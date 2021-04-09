package in.co.rays.ORSproject3.model;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.ORSproject3.dto.UserDTO;
import in.co.rays.ORSproject3.exception.ApplicationException;
import in.co.rays.ORSproject3.utility.HibDataSource;

public class UserModelPractice {

	public  static long add(UserDTO dto) throws ApplicationException {
		long pk = 0;

		Session session=null;
		Transaction trx = null;

		
		
		
		try {
			session = HibDataSource.getSession();
			trx = session.beginTransaction();
			session.save(dto);
			pk = dto.getId();
			trx.commit();
		} catch (HibernateException ex) {
			ex.printStackTrace();
			if (trx != null) {
				trx.rollback();
			}
			throw new ApplicationException(ex.getMessage());
		} finally {
			session.close();
		}

		return pk;
	}

	public void update(UserDTO dto) throws ApplicationException {

		Session session = null;
		Transaction trx = null;

		try {
			session = HibDataSource.getSession();
			trx = session.beginTransaction();
			session.update(dto);
			trx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (trx != null) {
				trx.rollback();
			}
			throw new ApplicationException(e.getMessage());

		} finally {

			session.close();
		}
	}
	
	public static UserDTO findByEmail(String loginid){
		UserDTO dto1=null;
		
		Session session=null;
		Transaction trx=null;
		
		session=HibDataSource.getSession();
		Criteria c=	session.createCriteria(UserDTO.class);
		//above line can be considered as "select * from ST_USER"

		c.add(Restrictions.eq("login", loginid));
		
		List listt=c.list();
		
if(listt.size()==1){
		dto1=(UserDTO)listt.get(0);
}
		
		System.out.println(listt.size());
		
	 return dto1;	
	}
	
	public static UserDTO findBypk(long pk){
		UserDTO dto1=null;
		
		Session session=null;
		Transaction trx=null;
		
		session=HibDataSource.getSession();
		
		Object o=session.get(UserDTO.class, pk);
		dto1=(UserDTO)o;
		
		
	 return dto1;	
	}
	
	public static UserDTO authenticate(String login,String paswwrd){
		
		UserDTO dto=null;
		Session session=null;
		Transaction trx=null;
		
		session=HibDataSource.getSession();
		
		Query q=session.createQuery("from UserDTO where login=?,password=?");
		q.setString(0, login);
		q.setString(1, paswwrd);
		
		List list=q.list();
		dto=(UserDTO)list.get(0);
		
		return dto;
	}
	
	public static List search(UserDTO dto,int pageNo,int pageSize){
		List list=null;
		
Session session=null;
Transaction trx=null;

session=HibDataSource.getSession();

Criteria c=session.createCriteria(UserDTO.class);

if(dto!=null){

	if(dto.getId()>0){
	c.add(Restrictions.eq("id", dto.getId()));
	}

	if(dto.getFirstName()!=null){
		c.add(Restrictions.like("firstName", dto.getFirstName()+"%"));
	}

	if(pageSize>0){
		c.setFirstResult((pageNo-1)*pageSize);
		c.setMaxResults(pageSize);
		
	}
	
}
		return list;
	} 
	
	
	
	
	
public static void main(String[] args) throws ApplicationException {
/*	UserDTO dto=new UserDTO();
	dto.setFirstName("Shivam");
	dto.setLastName("Jain");
	
long x=	add(dto);
	System.out.println(x+" row inserted");
*/
/*UserDTO dto=findBypk(100);

System.out.println(dto);
System.out.println(dto.getFirstName());
*/

UserDTO dto=findByEmail("danishahmed@gmail.com");

System.out.println(dto.getFirstName());
}
	
	
}
