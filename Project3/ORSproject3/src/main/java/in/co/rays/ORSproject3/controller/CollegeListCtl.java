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
import in.co.rays.ORSproject3.dto.CollegeDTO;
import in.co.rays.ORSproject3.exception.ApplicationException;
import in.co.rays.ORSproject3.model.CollegeModelHibImpl;
import in.co.rays.ORSproject3.model.CollegeModelInt;
import in.co.rays.ORSproject3.model.ModelFactory;
import in.co.rays.ORSproject3.utility.DataUtility;
import in.co.rays.ORSproject3.utility.PropertyReader;
import in.co.rays.ORSproject3.utility.ServletUtility;

/**
 * College List functionality Controller. Performs operation for list, search
 * and delete operations of College
 *
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@WebServlet(name = "CollegeListCtl", urlPatterns = { "/ctl/CollegeListCtl" })
public class CollegeListCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(CollegeListCtl.class);

	@Override
	protected void preload(HttpServletRequest request) {

		CollegeModelHibImpl model = new CollegeModelHibImpl();

		try {
			List clist = model.list(0, 0);
			request.setAttribute("clist", clist);

		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected BaseDTO populateBean(HttpServletRequest request) {

		CollegeDTO bean = new CollegeDTO();
		bean.setId(DataUtility.getLong(request.getParameter("cName")));
		bean.setCity(DataUtility.getString(request.getParameter("city")));
System.out.println(request.getParameter("city")+"cccccccccccccccccccc");
		populateDTO(bean, request);
		return bean;
	}

	/**
	 * Contains display logic
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

		CollegeDTO bean = (CollegeDTO) populateBean(request);
		CollegeModelHibImpl model = new CollegeModelHibImpl();

		List list = null;
		List next=null;
		try {
			list = model.search(bean, pageNo, pageSize);
			next= model.search(bean, pageNo+1, pageSize);
		} catch (ApplicationException e) {
			log.error(e);
			ServletUtility.handleException(e, request, response);
			return;
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

	/**
	 * Contains submit logic
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("CollegeListCtl doPost Start");

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		String ids[] = (String[]) request.getParameterValues("ids");
		pageNo = (pageNo == 0) ? 1 : pageNo;

		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;
		String op = DataUtility.getString(request.getParameter("operation"));

		if (OP_SEARCH.equalsIgnoreCase(op)) {
			pageNo = 1;
		} else if (OP_NEXT.equalsIgnoreCase(op)) {
			pageNo++;
		} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
			pageNo--;
		} else if (OP_NEW.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.COLLEGE_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.COLLEGE_LIST_CTL, request, response);
			return;
		} else if (OP_BACK.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.COLLEGE_LIST_CTL, request, response);
			return;
		}

		else if (OP_DELETE.equalsIgnoreCase(op)) {
			CollegeModelInt mod =ModelFactory.getInstance().getCollegeModel();

			if (ids != null && ids.length > 0) {
				pageNo = 1;
				CollegeDTO dbean = new CollegeDTO();
				for (String id2 : ids) {
					int idnew = DataUtility.getInt(id2);
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
		}

		CollegeModelInt model =ModelFactory.getInstance().getCollegeModel();
		CollegeDTO bean = (CollegeDTO) populateBean(request);
		List list = new ArrayList();
		List next=null;
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
			

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		ServletUtility.setBean(bean, request);
		ServletUtility.setList(list, request);
		ServletUtility.setPageNo(pageNo, request);
		ServletUtility.setPageSize(pageSize, request);
		ServletUtility.forward(getView(), request, response);

		log.debug("CollegeListCtl dopost ended");
	}

	@Override
	protected String getView() {
		return ORSView.COLLEGE_LIST_VIEW;
	}
}