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
import in.co.rays.ORSproject3.dto.MarksheetDTO;
import in.co.rays.ORSproject3.exception.ApplicationException;
import in.co.rays.ORSproject3.model.MarksheetModelInt;
import in.co.rays.ORSproject3.model.ModelFactory;
import in.co.rays.ORSproject3.model.StudentModelInt;
import in.co.rays.ORSproject3.utility.DataUtility;
import in.co.rays.ORSproject3.utility.PropertyReader;
import in.co.rays.ORSproject3.utility.ServletUtility;



/**
 * Marksheet List functionality Controller. Performs operation for list, search
 * and delete operations of Marksheet
 *
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

/**
 * Servlet implementation class MarksheetlistCtl
 */
@WebServlet(name = "MarksheetListCtl", urlPatterns = { "/ctl/MarksheetListCtl" })
public class MarksheetListCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(MarksheetListCtl.class);

	@Override
	protected void preload(HttpServletRequest request) {
		StudentModelInt model = ModelFactory.getInstance().getStudentModel();
		try {
			List list = model.list(0, 0);
			request.setAttribute("slist", list);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

		MarksheetModelInt model1 =ModelFactory.getInstance().getMarksheetModel();
		try {
			List mlist = model1.list(0, 0);
			request.setAttribute("mlist", mlist);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected BaseDTO populateBean(HttpServletRequest request) {
		MarksheetDTO bean = new MarksheetDTO();

		bean.setId(DataUtility.getLong(request.getParameter("rollNo")));
		bean.setStudentId(DataUtility.getLong(request.getParameter("name")));
		System.out.println(request.getParameter("name")+"---------------0000");
		return bean;
	}

	/**
	 * ContainsDisplaylogics
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		MarksheetModelInt model =ModelFactory.getInstance().getMarksheetModel();
		MarksheetDTO bean = (MarksheetDTO) populateBean(request);

		List<MarksheetDTO> list = new ArrayList<MarksheetDTO>();
		List next=null;		
		int pageNo = 1;

		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;

		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		try {
			list = model.search(bean, pageNo, pageSize);
			next= model.search(bean, pageNo+1, pageSize);
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
		} catch (ApplicationException e) {
			log.error(e);
			e.printStackTrace();
		}

		ServletUtility.forward(getView(), request, response);
		log.debug("MarksheetListCtl doGet End");

	}

	/**
	 * Contains Submit logics
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("MarksheetListCtl doPost Start");

		String op = DataUtility.getString(request.getParameter("operation"));

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		String ids[] = (String[]) request.getParameterValues("ids");
		pageNo = (pageNo == 0) ? 1 : pageNo;

		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		if (OP_SEARCH.equalsIgnoreCase(op)) {
			System.out.println("search called");
			pageNo = 1;
		} else if (OP_NEXT.equalsIgnoreCase(op)) {
			System.out.println("next called");
			pageNo++;
		} else if (OP_PREVIOUS.equalsIgnoreCase(op)) {
			if (pageNo > 1) {
				pageNo--;
			} else {
				pageNo = 1;
			}
	
		}

		else if (OP_NEW.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.MARKSHEET_CTL, request, response);
			return;
		}

		else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.MARKSHEET_LIST_CTL, request, response);
			return;
		} else if (OP_BACK.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.MARKSHEET_LIST_CTL, request, response);
			return;
		} else if (OP_DELETE.equalsIgnoreCase(op)) {

			MarksheetModelInt mod =ModelFactory.getInstance().getMarksheetModel();
			if (ids != null && ids.length > 0) {

				pageNo = 1;

				MarksheetDTO dbean = new MarksheetDTO();

				for (String id2 : ids) {

					int idnew = DataUtility.getInt(id2);
					System.out.println("new id value" + idnew);
					dbean.setId(idnew);
					try {
						mod.delete(dbean);
						ServletUtility.setSuccessMessage("Record Deleted Successfully", request);

					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			} else {
				ServletUtility.setErrorMessage("Select Atleast One Record", request);

			}
			// ServletUtility.forward(getView(), request, response);
		}

		MarksheetDTO bean = (MarksheetDTO) populateBean(request);
		System.out.println(bean.getId() + "-----------------");
		MarksheetModelInt model = ModelFactory.getInstance().getMarksheetModel();

		List<MarksheetDTO> list = new ArrayList<MarksheetDTO>();
		List next=null;
		try {
			list = model.search(bean, pageNo, pageSize);
			next= model.search(bean, pageNo+1, pageSize);
			if (list == null || list.size() == 0 && !OP_DELETE.equalsIgnoreCase(op)) {
				ServletUtility.setErrorMessage("No Marksheet Found", request);
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
		return ORSView.MARKSHEET_LIST_VIEW;
	}

}
