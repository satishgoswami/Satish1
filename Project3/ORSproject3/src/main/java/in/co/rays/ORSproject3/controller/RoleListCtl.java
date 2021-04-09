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
import in.co.rays.ORSproject3.dto.RoleDTO;
import in.co.rays.ORSproject3.exception.ApplicationException;
import in.co.rays.ORSproject3.model.ModelFactory;
import in.co.rays.ORSproject3.model.RoleModelInt;
import in.co.rays.ORSproject3.utility.DataUtility;
import in.co.rays.ORSproject3.utility.PropertyReader;
import in.co.rays.ORSproject3.utility.ServletUtility;

/**
 * Role List functionality Controller. Performs operation for list, search
 * operations of Role
 *
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@WebServlet(name = "RoleListCtl", urlPatterns = { "/ctl/RoleListCtl" })
public class RoleListCtl extends BaseCtl {
	private static Logger log = Logger.getLogger(RoleListCtl.class);

	@Override
	protected void preload(HttpServletRequest request) {
		RoleModelInt model =ModelFactory.getInstance().getRoleModel();
		try {
			List list = model.list(0, 0);
			request.setAttribute("rlist", list);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected BaseDTO populateBean(HttpServletRequest request) {
		RoleDTO bean = new RoleDTO();
		bean.setId(DataUtility.getLong(request.getParameter("name")));

		return bean;
	}

	/**
	 * Contains Display logics
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("RoleListCtl doGet Start");
		List list = null;
		List next=null;
		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
		RoleDTO bean = (RoleDTO) populateBean(request);
		String op = DataUtility.getString(request.getParameter("operation"));
		RoleModelInt model =ModelFactory.getInstance().getRoleModel();
		try {
			list = model.search(bean, pageNo, pageSize);
			next= model.search(bean, pageNo+1, pageSize);
			ServletUtility.setList(list, request);
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
		} catch (ApplicationException e) {
			log.error(e);
			ServletUtility.handleException(e, request, response);
			return;
		}
		log.debug("RoleListCtl doGet End");
	}

	/**
	 * Contains Submit logics
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("");
		System.out.println("inside do post");

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		String op = DataUtility.getString(request.getParameter("operation"));

		String ids[] = request.getParameterValues("ids");

		if (OP_SEARCH.equalsIgnoreCase(op)) {
			pageNo = 1;
		} else if (OP_PREVIOUS.equalsIgnoreCase(op)) {

			if (pageNo > 1) {
				pageNo--;
			} else {
				pageNo = 1;
			}
		} else if (OP_NEXT.equalsIgnoreCase(op)) {
			pageNo++;
		}

		else if (OP_NEW.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.ROLE_CTL, request, response);
			return;
		}

		else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.ROLE_LIST_CTL, request, response);
			return;
		} else if (OP_DELETE.equalsIgnoreCase(op)) {
			RoleModelInt mod =ModelFactory.getInstance().getRoleModel();
			RoleDTO dbean = new RoleDTO();

			if (ids != null && ids.length > 0) {

				for (String id2 : ids) {
					int idnew = DataUtility.getInt(id2);
					dbean.setId(idnew);
					try {
						mod.delete(dbean);
						ServletUtility.setSuccessMessage("Role Deleted Successfully", request);
						// ServletUtility.forward(getView(), request, response);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} else {
				ServletUtility.setErrorMessage("Select Atleast One Record", request);
			}
		}

		RoleModelInt model = ModelFactory.getInstance().getRoleModel();
		RoleDTO bean = (RoleDTO) populateBean(request);

		List<RoleDTO> list = new ArrayList<RoleDTO>();
		List next=null;
		try {

			list = model.search(bean, pageNo, pageSize);
			next= model.search(bean, pageNo+1, pageSize);
			if ((list == null || list.size() == 0) && !OP_DELETE.equalsIgnoreCase(op)) {
				ServletUtility.setErrorMessage("No Role Found", request);
			}
			if(next==null || next.size()==0)
			{
				  request.setAttribute("nextListSize", "0");	
				}else
				{
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
		return ORSView.ROLE_LIST_VIEW;
	}

}