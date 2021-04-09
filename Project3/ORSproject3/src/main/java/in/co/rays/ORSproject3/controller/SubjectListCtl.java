package in.co.rays.ORSproject3.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.ORSproject3.dto.BaseDTO;
import in.co.rays.ORSproject3.dto.SubjectDTO;
import in.co.rays.ORSproject3.exception.ApplicationException;
import in.co.rays.ORSproject3.model.CourseModelInt;
import in.co.rays.ORSproject3.model.ModelFactory;
import in.co.rays.ORSproject3.model.SubjectModelInt;
import in.co.rays.ORSproject3.utility.DataUtility;
import in.co.rays.ORSproject3.utility.PropertyReader;
import in.co.rays.ORSproject3.utility.ServletUtility;

@WebServlet(name = "SubjectListCtl", urlPatterns = { "/ctl/SubjectListCtl" })

public class SubjectListCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(SubjectListCtl.class);

	protected void preload(HttpServletRequest request) {

		System.out.println("preload of subject");

		CourseModelInt cmodel =ModelFactory.getInstance().getCourseModel();

		try {
			List clist = cmodel.list(0,0);
			request.setAttribute("courselist", clist);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		SubjectModelInt smodel = ModelFactory.getInstance().getSubjectModel();

		try {
			List slist = smodel.list(0,0);
			request.setAttribute("subjectlist", slist);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	protected BaseDTO populateBean(HttpServletRequest request) {

		SubjectDTO bean = new SubjectDTO();
		bean.setCourseId(DataUtility.getInt(request.getParameter("courseId")));
		bean.setId(DataUtility.getLong(request.getParameter("subjectId")));
		System.out.println(bean.getCourseName());
		
		populateDTO(bean, request);
		return bean;
	
	}

	/* 
	 * 
	 * Contains view logic
	 * */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		  
		System.out.println("inside subject do get");
		
		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

		SubjectModelInt model =ModelFactory.getInstance().getSubjectModel();
		SubjectDTO bean = (SubjectDTO) populateBean(request);
		List<SubjectDTO> list = new ArrayList<SubjectDTO>();
		List next=null;
		try {
			list = model.search(bean, pageNo, pageSize);
			next= model.search(bean, pageNo+1, pageSize);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		if (list == null || list.size() == 0) {
			ServletUtility.setErrorMessage("No record found ", request);
		}
		if(next==null || next.size()==0)
		{
		  request.setAttribute("nextListSize", "0");	
		}else
		{
		  request.setAttribute("nextListSize", next.size());		
		}

		ServletUtility.setList(list, request);
		ServletUtility.setPageNo(pageNo, request);
		ServletUtility.setPageSize(pageSize, request);
		ServletUtility.forward(getView(), request, response);
	}

	/* 
	 * 
	 * Contain Submit logic
	 *  */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		long id1 = DataUtility.getInt(request.getParameter("id"));
		String op = DataUtility.getStringData(request.getParameter("operation"));
	
		System.out.println("inside do post of subjectListCTl");
		
		int pageNo = DataUtility.getInt(request.getParameter("pageno"));
		int pageSize = DataUtility.getInt(request.getParameter("pagesize"));
		String[] ids = request.getParameterValues("ids");

		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		if (OP_SEARCH.equalsIgnoreCase(op)) {
			pageNo = 1;
		} else if (OP_NEW.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.SUBJECT_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.SUBJECT_LIST_CTL, request, response);
			return;
		}else if (OP_BACK.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.WELCOME_CTL, request, response);
			return;
		}
		else if (OP_NEXT.equalsIgnoreCase(op)) {
			pageNo++;
		} else if (OP_PREVIOUS.equalsIgnoreCase(op)) {
			pageNo--;
		} else if (OP_DELETE.equalsIgnoreCase(op)) {
System.out.println(op+"-----------------");
			SubjectModelInt model = ModelFactory.getInstance().getSubjectModel();

			if (ids != null && ids.length > 0) {
				pageNo = 1;
				SubjectDTO bean = new SubjectDTO();
				for (String id : ids) {
					int id2 = DataUtility.getInt(id);
					bean.setId(id2);
					try {
						model.delete(bean);
						ServletUtility.setSuccessMessage("Subject Deleted Successfully", request);
					} catch (Exception e) {
						ServletUtility.handleException(e, request, response);
						e.printStackTrace();
					}

				}
			}

			else {
				ServletUtility.setErrorMessage("Select atleast one record", request);
			}
		}

		SubjectDTO bean1 = (SubjectDTO) populateBean(request);
		SubjectModelInt mod = ModelFactory.getInstance().getSubjectModel();
		List list1 = new ArrayList();

		List next=null;
		try {
			list1 = mod.search(bean1, pageNo, pageSize);
			next= mod.search(bean1, pageNo+1, pageSize);
			if ((list1.size() == 0 || list1 == null) && !OP_DELETE.equalsIgnoreCase(op)) {
				System.out.println("--------------yah tk aaya");
				ServletUtility.setErrorMessage("No record found", request);
			
			}
			if(next==null || next.size()==0)
			{
			  request.setAttribute("nextListSize", "0");	
			}else
			{
			  request.setAttribute("nextListSize", next.size());		
			}

			ServletUtility.setBean(bean1, request);
		ServletUtility.setList(list1, request);
		ServletUtility.setPageNo(pageNo, request);
		ServletUtility.setPageSize(pageSize, request);
		ServletUtility.forward(getView(), request, response);
	}
		 catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}		
	@Override
	protected String getView() {
		return ORSView.SUBJECT_LIST_VIEW;
	}

}
