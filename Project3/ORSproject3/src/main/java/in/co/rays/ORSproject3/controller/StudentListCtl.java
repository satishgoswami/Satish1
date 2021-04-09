package in.co.rays.ORSproject3.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.dom4j.rule.Mode;

import in.co.rays.ORSproject3.dto.BaseDTO;
import in.co.rays.ORSproject3.dto.StudentDTO;
import in.co.rays.ORSproject3.exception.ApplicationException;
import in.co.rays.ORSproject3.model.ModelFactory;
import in.co.rays.ORSproject3.model.StudentModelInt;
import in.co.rays.ORSproject3.utility.DataUtility;
import in.co.rays.ORSproject3.utility.PropertyReader;
import in.co.rays.ORSproject3.utility.ServletUtility;

/**
 * Student List functionality Controller. Performs operation for list, search
 * and delete operations of Student
 *
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@WebServlet(name = "StudentListCtl", urlPatterns = { "/ctl/StudentListCtl" })
public class StudentListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(StudentListCtl.class);

	@Override
	protected void preload(HttpServletRequest request) {
		StudentModelInt model = ModelFactory.getInstance().getStudentModel();

		try {
			List list = model.list(0, 0);
			request.setAttribute("slist", list);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	protected BaseDTO populateBean(HttpServletRequest request) {

		StudentDTO bean = new StudentDTO();

		bean.setId(DataUtility.getLong(request.getParameter("firstName")));
		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
		bean.setEmail(DataUtility.getString(request.getParameter("email")));
		populateDTO(bean, request);
		return bean;
	}

	/**
	 * Contains display logic
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("inside STL do get");

		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

		StudentModelInt model = ModelFactory.getInstance().getStudentModel();

		StudentDTO bean = (StudentDTO) populateBean(request);

		List<StudentDTO> list = new ArrayList();
		List next = null;
		try {

			list = model.search(bean, pageNo, pageSize);
			next = model.search(bean, pageNo + 1, pageSize);
			if (list == null || list.size() == 0) {

				ServletUtility.setErrorMessage("no record found", request);
			}
			if (next == null || next.size() == 0) {
				request.setAttribute("nextListSize", "0");
			} else {
				request.setAttribute("nextListSize", next.size());
			}

			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Contains Submit logics
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));

		String[] ids = request.getParameterValues("ids");

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		if (OP_SEARCH.equalsIgnoreCase(op)) {
			pageNo = 1;
		} else if (OP_PREVIOUS.equalsIgnoreCase(op)) {
			
			
			 if(pageNo>1){
				 pageNo--; 
				 }
			 else{ 
					 pageNo=1; }
			 
		} else if (OP_NEXT.equalsIgnoreCase(op)) {
			pageNo++;
		}

		else if (OP_NEW.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.STUDENT_CTL, request, response);
			return;
		} else if (OP_BACK.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.STUDENT_LIST_CTL, request, response);
			return;
		}

		else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.STUDENT_LIST_CTL, request, response);
			return;
		}

		else if (OP_BACK.equalsIgnoreCase(op)) {
			System.out.println("inside back");
			ServletUtility.redirect(ORSView.STUDENT_LIST_CTL, request, response);
			return;
		} else if (OP_DELETE.equalsIgnoreCase(op)) {

			StudentModelInt mod = ModelFactory.getInstance().getStudentModel();
			StudentDTO dbean = new StudentDTO();
			System.out.println("ids val" + ids);
			if (ids != null && ids.length > 0) {

				for (String id2 : ids) {

					int idnew = DataUtility.getInt(id2);
					System.out.println("id new val" + idnew);
					dbean.setId(idnew);

					try {
						mod.delete(dbean);
						ServletUtility.setSuccessMessage("Record Deleted Successfully", request);

					} catch (ApplicationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} else {
				ServletUtility.setErrorMessage("Select Atleast One Record", request);
			}
		}

		StudentModelInt model = ModelFactory.getInstance().getStudentModel();
		StudentDTO bean = (StudentDTO) populateBean(request);
		List list = new ArrayList();
		List next=null;
		System.out.println("list before========" + list);
		try {
			list = model.search(bean, pageNo, pageSize);
			next=model.search(bean, pageNo+1, pageSize);
			System.out.println("list After========" + list);

			if ((list == null || list.size() == 0) && !OP_DELETE.equalsIgnoreCase(op)) {
				System.out.println("Student list..............");
				ServletUtility.setErrorMessage("No Student Found", request);
			}

			if (next == null || next.size() == 0) {
				request.setAttribute("nextListSize", "0");
			} else {
				request.setAttribute("nextListSize", next.size());
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
		ServletUtility.setBean(bean, request);
		ServletUtility.setList(list, request);
		ServletUtility.setPageNo(pageNo, request);
		ServletUtility.setPageSize(pageSize, request);
		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		return ORSView.STUDENT_LIST_VIEW;
	}

}
