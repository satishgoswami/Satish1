package in.co.rays.ORSproject3.controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import in.co.rays.ORSproject3.dto.BaseDTO;
import in.co.rays.ORSproject3.dto.UserDTO;
import in.co.rays.ORSproject3.exception.ApplicationException;
import in.co.rays.ORSproject3.exception.DuplicateRecordException;
import in.co.rays.ORSproject3.exception.RecordNotFoundException;
import in.co.rays.ORSproject3.model.ModelFactory;
import in.co.rays.ORSproject3.model.UserModelInt;
import in.co.rays.ORSproject3.utility.DataUtility;
import in.co.rays.ORSproject3.utility.DataValidator;
import in.co.rays.ORSproject3.utility.PropertyReader;
import in.co.rays.ORSproject3.utility.ServletUtility;

/**
 * My Profile functionality Controller. Performs operation for update your
 * Profile
 *
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@WebServlet(name = "MyProfileCtl", urlPatterns = { "/ctl/MyProfileCtl" })
public class MyProfileCtl extends BaseCtl {

	public static final String OP_CHANGE_MY_PASSWORD = "ChangePassword";

	private static Logger log = Logger.getLogger(MyProfileCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("MyProfileCtl Method validate Started");

		boolean pass = true;

		String op = DataUtility.getString(request.getParameter("operation"));

		if (OP_CHANGE_MY_PASSWORD.equalsIgnoreCase(op) || op == null) {

			return pass;
		}

		if (DataValidator.isNull(request.getParameter("firstName"))) {
			System.out.println("firstName" + request.getParameter("firstName"));
			request.setAttribute("firstName", PropertyReader.getValue("error.require", "First Name"));
			pass = false;
		}
		else if(!DataValidator.isName(request.getParameter("firstName"))){
			request.setAttribute("firstName","Invalid First Name");
			pass=false;
		}

		if (DataValidator.isNull(request.getParameter("lastName"))) {
			request.setAttribute("lastName", PropertyReader.getValue("error.require", "Last Name"));
			pass = false;
		}
		else if(!DataValidator.isName(request.getParameter("lastName"))){
			request.setAttribute("lastName","Invalid Last Name");
			pass=false;
		}

		if (DataValidator.isNull(request.getParameter("gender"))) {
			request.setAttribute("gender", PropertyReader.getValue("error.require", "Gender"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("mobile"))) {
			request.setAttribute("mobile", PropertyReader.getValue("error.require", "Mobile No."));
			pass = false;
		}
		else if(!DataValidator.isMobileNo(request.getParameter("mobile"))){
			request.setAttribute("mobile","Invalid Mobile No");
			pass=false;
		}

		if (DataValidator.isNull(request.getParameter("dob"))) {
			request.setAttribute("Date", PropertyReader.getValue("error.require", "Date Of Birth"));
			pass = false;
		}
		else if(!DataValidator.isValidAge(request.getParameter("dob"))){
			System.out.println("yessssssssssssssssssssssss");
			request.setAttribute("Date",PropertyReader.getValue("error.date", "Age"));
			pass=false;
		}

		log.debug("MyProfileCtl Method validate Ended");

		return pass;

	}

	@Override
	protected BaseDTO populateBean(HttpServletRequest request) {
		log.debug("MyProfileCtl Method populatebean Started");

		UserDTO bean = new UserDTO();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setLogin(DataUtility.getString(request.getParameter("login")));

		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));

		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));

		bean.setMobileNo(DataUtility.getString(request.getParameter("mobile")));

		bean.setGender(DataUtility.getString(request.getParameter("gender")));

		bean.setDob(DataUtility.getDate(request.getParameter("dob")));
  System.out.println(request.getParameter("dob"));
		populateDTO(bean, request);

		return bean;
	}

	/**
	 * Display Concept for viewing profile page view
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		log.debug("MyprofileCtl Method doGet Started");

		UserDTO UserBean = (UserDTO) session.getAttribute("user");
		System.out.println("Login ID of User is "+ UserBean.getLogin());
		
		String op = DataUtility.getString(request.getParameter("operation"));
		long id = UserBean.getId();
		
		if (id > 0 || op != null) {
			System.out.println("in id > 0  condition");
			UserModelInt model =ModelFactory.getInstance().getUserModel();
			try {
				UserBean = model.findByPk(id);
				ServletUtility.setBean(UserBean, request);
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} catch (RecordNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		ServletUtility.forward(getView(), request, response);
		}
		log.debug("MyProfileCtl Method doGet Ended");
	}

	/**
	 * Submit Concept
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		log.debug("MyprofileCtl Method doPost Started");

		UserDTO UserBean = (UserDTO) session.getAttribute("user");
		long id = UserBean.getId();
		String op = DataUtility.getString(request.getParameter("operation"));
		UserDTO bean = (UserDTO) populateBean(request);
		// get model
		UserModelInt model =ModelFactory.getInstance().getUserModel();

		if (OP_SAVE.equalsIgnoreCase(op)) {
			try {
				if (id > 0) {
					UserBean.setFirstName(bean.getFirstName());
					UserBean.setLastName(bean.getLastName());
					UserBean.setGender(bean.getGender());
					UserBean.setMobileNo(bean.getMobileNo());
					UserBean.setDob(bean.getDob());
					model.update(UserBean);

				}
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Profile has been updated Successfully. ", request);
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Login id already exists", request);
			}
		} else if (OP_CHANGE_MY_PASSWORD.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.CHANGE_PASSWORD_CTL, request, response);
			return;

		}

		ServletUtility.forward(getView(), request, response);

		log.debug("MyProfileCtl Method doPost Ended");
	}

	@Override
	protected String getView() {
		return ORSView.MY_PROFILE_VIEW;
	}

}