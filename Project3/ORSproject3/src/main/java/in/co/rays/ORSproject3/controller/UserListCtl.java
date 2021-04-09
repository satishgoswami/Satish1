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
import in.co.rays.ORSproject3.dto.UserDTO;
import in.co.rays.ORSproject3.exception.ApplicationException;
import in.co.rays.ORSproject3.model.ModelFactory;
import in.co.rays.ORSproject3.model.RoleModelInt;
import in.co.rays.ORSproject3.model.UserModelInt;
import in.co.rays.ORSproject3.utility.DataUtility;
import in.co.rays.ORSproject3.utility.PropertyReader;
import in.co.rays.ORSproject3.utility.ServletUtility;

/**
 * User List functionality Controller. Performs operation for list, search and
 * delete operations of User
 *
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@WebServlet(name = "UserListCtl", urlPatterns = { "/ctl/UserListCtl" })
public class UserListCtl extends BaseCtl {
	private static Logger log = Logger.getLogger(UserListCtl.class);

@Override
protected void preload(HttpServletRequest request) {
	RoleModelInt model = ModelFactory.getInstance().getRoleModel();
	try {
		List list = model.list(0, 0);
		request.setAttribute("role_list", list);
	} catch (ApplicationException e) {
		e.printStackTrace();
	}

}
	
	
	
	
	
	@Override
	protected BaseDTO populateBean(HttpServletRequest request) {
		UserDTO bean = new UserDTO();

		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));

		bean.setRoleId(DataUtility.getLong(request.getParameter("rname")));

		bean.setLogin(DataUtility.getString(request.getParameter("login")));

		return bean;
	}

	/**
	 * Contains Display logics
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("UserListCtl doGet Start");
		System.out.println("inside UserListCTL doGet");
		UserModelInt model = ModelFactory.getInstance().getUserModel();
		UserDTO bean = (UserDTO) populateBean(request);
		System.out.println(bean.getFirstName());
		List list = new ArrayList();

		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
		List next=null;

		try {
			list = model.search(bean, pageNo, pageSize);
			next = model.search(bean, pageNo+1, pageSize);
			System.out.println("11111111111111" + bean.getLogin());
			
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
			e.printStackTrace();
			ServletUtility.handleException(e, request, response);
			return;
		}
		log.debug("UserListCtl doPOst End");

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
		System.out.println(pageSize);
		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;
		
		String op = DataUtility.getString(request.getParameter("operation"));

		// get the selected checkbox ids array for delete list
		String[] ids = request.getParameterValues("ids");
		

		if (OP_SEARCH.equalsIgnoreCase(op)) {
			pageNo = 1;
		} else if (OP_NEXT.equalsIgnoreCase(op)) {
			pageNo++;
			System.out.println("page number increases by 1-----" + pageNo);
		} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
			pageNo--;
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.USER_LIST_CTL, request, response);
			return;
		}

		else if (OP_NEW.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.USER_CTL, request, response);
			return;
		} else if (OP_DELETE.equalsIgnoreCase(op)) {
			UserModelInt mod = ModelFactory.getInstance().getUserModel();
			pageNo = 1;
			if (ids != null && ids.length > 0) {
				System.out.println(ids.length+"-----"+ids[0]);
				UserDTO deletebean = new UserDTO();
				for (String id : ids) {
					
					int id2 = DataUtility.getInt(id);
					deletebean.setId(id2);
					try {
						mod.delete(deletebean);
						ServletUtility.setSuccessMessage("Record Deleted Successfully", request);
					} catch (ApplicationException e) {
						ServletUtility.handleException(e, request, response);
						e.printStackTrace();
					}
					
				}
			} else {
				ServletUtility.setErrorMessage("Select at least one record", request);
			}
		}

		
		UserModelInt model = ModelFactory.getInstance().getUserModel();
		UserDTO bean = (UserDTO) populateBean(request);
		System.out.println(bean.getRoleId()+bean.getRoleName()+"-----------ssssssssssd");
		List list =new ArrayList();
		List next=null;
		try {
			list = model.search(bean, pageNo, pageSize);
			next= model.search(bean, pageNo+1, pageSize);
			if ((list == null || list.size() == 0) && !OP_DELETE.equalsIgnoreCase(op)) {
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

	log.debug("UserListCtl post ended");	
	}


	@Override
	protected String getView() {
		return ORSView.USER_LIST_VIEW;
	}

}