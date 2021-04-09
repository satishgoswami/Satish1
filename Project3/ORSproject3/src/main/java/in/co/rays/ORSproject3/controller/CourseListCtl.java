package in.co.rays.ORSproject3.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.ORSproject3.dto.BaseDTO;
import in.co.rays.ORSproject3.dto.CourseDTO;
import in.co.rays.ORSproject3.exception.ApplicationException;
import in.co.rays.ORSproject3.model.CourseModelInt;
import in.co.rays.ORSproject3.model.ModelFactory;
import in.co.rays.ORSproject3.utility.DataUtility;
import in.co.rays.ORSproject3.utility.PropertyReader;
import in.co.rays.ORSproject3.utility.ServletUtility;

/**
 * Course List functionality Controller. Performs operation for list, search and
 * delete operations of Course
 *
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@WebServlet(name = "CourseListCtl", urlPatterns = { "/ctl/CourseListCtl" })

public class CourseListCtl extends BaseCtl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(CourseListCtl.class);

	@Override
	protected void preload(HttpServletRequest request) {
		CourseModelInt model = ModelFactory.getInstance().getCourseModel();
		try {
			List<CourseDTO> clist = model.list(0, 0);
			request.setAttribute("clist", clist);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected BaseDTO populateBean(HttpServletRequest request) {

		CourseDTO bean = new CourseDTO();
		bean.setId(DataUtility.getLong(request.getParameter("CourseName")));
		populateDTO(bean, request);
		return bean;
	}

	/**
	 * Contains Display logics
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("CourseListCtl doGet Start");
		System.out.println("inside CourseListCTL doGet");

		CourseModelInt model = ModelFactory.getInstance().getCourseModel();
		CourseDTO bean = (CourseDTO) populateBean(request);

		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
		List list = new ArrayList();
		List next = null;
		try {
			list = model.search(bean, pageNo, pageSize);
			next = model.search(bean, pageNo + 1, pageSize);
			System.out.println("-----------" + bean.getCourseName());

			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No record found ", request);
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
		} catch (ApplicationException e) {
			e.printStackTrace();
			ServletUtility.handleException(e, request, response);
			return;
		}
		log.debug("CourseListCtl doPOst End");

	}

	/**
	 * Contains Submit logics
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("UserListCtl doPost Start");

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		System.out.println("-------------------------------------");
		System.out.println(pageNo);
		System.out.println(pageSize);

		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;


		System.out.println("-------------------------------------");
		System.out.println(pageNo);
		System.out.println(pageSize);

		
		String op = DataUtility.getString(request.getParameter("operation"));

		// get the selected checkbox ids array for delete list
		String[] ids = request.getParameterValues("ids");

		if (OP_SEARCH.equalsIgnoreCase(op)) {
			pageNo = 1;
		}

		else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.COURSE_LIST_CTL, request, response);
			return;
		}

		else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {

			pageNo--;

		} else if (OP_NEW.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.COURSE_CTL, request, response);
			return;
		} else if (OP_NEXT.equalsIgnoreCase(op)) {
			pageNo++;
		} else if (OP_BACK.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.COURSE_LIST_CTL, request, response);
			return;
		}

		else if (OP_DELETE.equalsIgnoreCase(op)) {
			CourseModelInt model = ModelFactory.getInstance().getCourseModel();
			System.out.println("id delete...........");
			if (ids != null && ids.length > 0) {
				pageNo = 1;
				for (String id : ids) {
					CourseDTO deleteBean = new CourseDTO();
					int id2 = DataUtility.getInt(id);
					deleteBean.setId(id2);
					try {
						model.delete(deleteBean);
						ServletUtility.setSuccessMessage("Record Deleted Successfully", request);
					} catch (ApplicationException e) {
						ServletUtility.handleException(e, request, response);
						e.printStackTrace();
					}

				}
			} else {
				ServletUtility.setErrorMessage("Select Atleast one record", request);
			}

		}
		CourseModelInt model = ModelFactory.getInstance().getCourseModel();

		CourseDTO bean = (CourseDTO) populateBean(request);

		List list = new ArrayList();
		List next = null;
		try {
			list = model.search(bean, pageNo, pageSize);
			next = model.search(bean, pageNo + 1, pageSize);
			if (list == null || list.size() == 0 && !OP_DELETE.equalsIgnoreCase(op)) {
				ServletUtility.setErrorMessage("No record found ", request);
			}

			if (next == null || next.size() == 0) {
				request.setAttribute("nextListSize", "0");
			} else {
				request.setAttribute("nextListSize", next.size());
			}
			ServletUtility.setBean(bean, request);
			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);
		} catch (ApplicationException e) {
			ServletUtility.handleException(e, request, response);
			e.printStackTrace();
		}

	}

	@Override
	protected String getView() {
		return ORSView.COURSE_LIST_VIEW;
	}

}
