package in.co.rays.ORSproject3.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.ORSproject3.dto.BaseDTO;
import in.co.rays.ORSproject3.dto.FacultyDTO;
import in.co.rays.ORSproject3.exception.ApplicationException;
import in.co.rays.ORSproject3.model.CollegeModelInt;
import in.co.rays.ORSproject3.model.CourseModelInt;
import in.co.rays.ORSproject3.model.FacultyModelInt;
import in.co.rays.ORSproject3.model.ModelFactory;
import in.co.rays.ORSproject3.utility.DataUtility;
import in.co.rays.ORSproject3.utility.PropertyReader;
import in.co.rays.ORSproject3.utility.ServletUtility;



@WebServlet(name = "FacultyListCtl", urlPatterns = { "/ctl/FacultyListCtl" })
public class FacultyListCtl extends BaseCtl {

	private static final long serialVersionUID = 1L;

	@Override
	protected void preload(HttpServletRequest request) {

		/*FacultyModel model = new FacultyModel();
		try {
			List flist = model.list(0, 0);
			request.setAttribute("flist", flist);

		} catch (Exception e) {
			e.printStackTrace();
		}*/
		CollegeModelInt cmodel =ModelFactory.getInstance().getCollegeModel();
		try {
			List clist = cmodel.list(0, 0);
			request.setAttribute("clist", clist);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		CourseModelInt ccmodel =ModelFactory.getInstance().getCourseModel();
		try {
			List cclist = ccmodel.list(0, 0);
			request.setAttribute("cclist", cclist);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	protected BaseDTO populateBean(HttpServletRequest request) {

		FacultyDTO bean = new FacultyDTO();

		bean.setFirstName(DataUtility.getString(request.getParameter("fname")));

		bean.setCollegeId(DataUtility.getLong(request.getParameter("cname")));
		bean.setCourseId(DataUtility.getLong(request.getParameter("lname")));

		System.out.println(request.getParameter("lname")+"==============--------------");
		return bean;
	}

	/*
	 * Contain view logic
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("inside doGet");

		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

		List list = new ArrayList();
		List next=null;
		FacultyModelInt model =ModelFactory.getInstance().getFacultyModel();
		FacultyDTO bean = new FacultyDTO();
		try {

			list = model.search(bean, pageNo, pageSize);
			next = model.search(bean, pageNo+1, pageSize);
			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("no record found", request);
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
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * contain submit logic
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("inside do post");

		long id = DataUtility.getInt(request.getParameter("id"));
		String op = DataUtility.getString(request.getParameter("operation"));

		String ids[] = request.getParameterValues("ids");

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		if (OP_SEARCH.equalsIgnoreCase(op)) {
			System.out.println("search called");
			pageNo = 1;
		}

		else if (OP_PREVIOUS.equalsIgnoreCase(op)) {

			if (pageNo > 1) {
				pageNo--;
			} else {
				pageNo = 1;
			}
		}

		else if (OP_NEXT.equalsIgnoreCase(op)) {
			pageNo++;
		}

		else if (OP_NEW.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.FACULTY_CTL, request, response);
			return;
		}

		else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.FACULTY_LIST_CTL, request, response);
			return;
		} else if (OP_BACK.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.FACULTY_LIST_CTL, request, response);
			return;
		} else if (OP_DELETE.equalsIgnoreCase(op)) {

			FacultyModelInt mod = ModelFactory.getInstance().getFacultyModel();
			FacultyDTO dbean = new FacultyDTO();

			if (ids != null && ids.length > 0) {

				for (String id2 : ids) {
					int idnew = DataUtility.getInt(id2);
					dbean.setId(idnew);
					try {
						mod.delete(dbean);
						ServletUtility.setSuccessMessage("Record Deleted Successfully", request);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} else {
				ServletUtility.setErrorMessage("Select Atleast One Record", request);
			}
		}

		List list = new ArrayList();
		List next= null;
		FacultyModelInt model =ModelFactory.getInstance().getFacultyModel();
		FacultyDTO bean = null;
		try {
			bean = (FacultyDTO) populateBean(request);
			list = model.search(bean, pageNo, pageSize);
			next = model.search(bean, pageNo + 1, pageSize);
			System.out.println(list.size()+"----------size-------");
			if (list == null || list.size() == 0 && !OP_DELETE.equalsIgnoreCase(op)) {
				ServletUtility.setErrorMessage("No Faculty Found", request);
			}
			if(next==null || next.size()==0)
			{
			  request.setAttribute("nextListSize", "0");	
			}else
			{
			  request.setAttribute("nextListSize", next.size());		
			}
			ServletUtility.setBean(bean, request);
			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected String getView() {
		return ORSView.FACULTY_LIST_VIEW;
	}

}
