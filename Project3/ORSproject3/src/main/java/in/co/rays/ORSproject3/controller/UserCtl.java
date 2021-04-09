package in.co.rays.ORSproject3.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.ORSproject3.dto.BaseDTO;
import in.co.rays.ORSproject3.dto.RoleDTO;
import in.co.rays.ORSproject3.dto.UserDTO;
import in.co.rays.ORSproject3.exception.ApplicationException;
import in.co.rays.ORSproject3.exception.DuplicateRecordException;
import in.co.rays.ORSproject3.exception.RecordNotFoundException;
import in.co.rays.ORSproject3.model.ModelFactory;
import in.co.rays.ORSproject3.model.RoleModelInt;
import in.co.rays.ORSproject3.model.UserModelInt;
import in.co.rays.ORSproject3.utility.DataUtility;
import in.co.rays.ORSproject3.utility.DataValidator;
import in.co.rays.ORSproject3.utility.PropertyReader;
import in.co.rays.ORSproject3.utility.ServletUtility;

/**
 * * User functionality Controller. Performs operation for add, update and get
 * User
 *
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@WebServlet(name = "UserCtl", urlPatterns = { "/ctl/UserCtl" })
public class UserCtl extends BaseCtl {

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(UserCtl.class);

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
	protected boolean validate(HttpServletRequest request) {

		log.debug("UserCtl Method validate Started");
		System.out.println("Validate Started");
		boolean pass = true;

		long id = DataUtility.getInt(request.getParameter("id"));

		if (DataValidator.isNull(request.getParameter("firstName"))) {
			request.setAttribute("firstName", PropertyReader.getValue("error.require", "First Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("firstName"))) {
			request.setAttribute("firstName", "Please Enter Valid First Name");
			pass = false;
		}

	
		if (DataValidator.isNull(request.getParameter("lastName"))) {
			request.setAttribute("lastName", PropertyReader.getValue("error.require", "Last Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("lastName"))) {
			request.setAttribute("lastName", "Please Enter Valid Last Name");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("login"))) {
			request.setAttribute("login", PropertyReader.getValue("error.require", "Login Id"));
			pass = false;
		} else if (!DataValidator.isEmail(request.getParameter("login"))) {
			request.setAttribute("login", PropertyReader.getValue("error.email", "Invaild "));
			pass = false;
		}

		
		if(id>0){}else{
		if (DataValidator.isNull(request.getParameter("password"))) {
			request.setAttribute("password", PropertyReader.getValue("error.require", ""));
			pass = false;
			System.out.println("pass---------------->"+pass);
		} else if (!DataValidator.isPassword(request.getParameter("password"))) {
			request.setAttribute("password", PropertyReader.getValue("error.password", ""));
			pass = false;
			
		}

		
		
		if (DataValidator.isNull(request.getParameter("confirmPassword"))) {
			request.setAttribute("confirmPassword", PropertyReader.getValue("error.require", "Confirm Password"));
			pass = false;
		}
		
		}
		
		if (DataValidator.isNull(request.getParameter("address"))) {
			request.setAttribute("address", PropertyReader.getValue("error.require", "Address"));
			pass = false;
		}
		
		
		else if (!DataValidator.isAddress(request.getParameter("address"))) {
			request.setAttribute("address", "Invalid Address");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("gender"))) {
			request.setAttribute("gender", PropertyReader.getValue("error.require", "Gender"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("dob"))) {
			request.setAttribute("dob", PropertyReader.getValue("error.require", "Date of Birth"));
			pass = false;
		} else if (!DataValidator.isDate(request.getParameter("dob"))) {
			request.setAttribute("dob", PropertyReader.getValue("error.date", "Date of Birth"));
			pass = false;
		} else if (!DataValidator.isValidAge(request.getParameter("dob"))) {
			request.setAttribute("dob", PropertyReader.getValue("error.date", "Age"));
			pass = false;
		}

		if (!request.getParameter("password").equals(request.getParameter("confirmPassword"))
				&& !"".equals(request.getParameter("confirmPassword"))) {
			ServletUtility.setErrorMessage("Password & Confirm  Password  must be same!!", request);
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("mobile"))) {
			request.setAttribute("mobile", PropertyReader.getValue("error.require", "Mobile No"));
			pass = false;

		} else if (!DataValidator.isMobileNo(request.getParameter("mobile"))) {
			request.setAttribute("mobile", PropertyReader.getValue("error.mobile", "Invalid"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("roleId"))) {
			request.setAttribute("role", PropertyReader.getValue("error.require", "Role Name"));
			pass = false;
		}

		log.debug("UserCtl Method validate Ended");
		return pass;
	}

	@Override
	protected BaseDTO populateBean(HttpServletRequest request) {

		log.debug("UserCtl Method populatebean Started");

		long id = DataUtility.getInt(request.getParameter("id"));
		UserDTO bean = null;
		try {
			bean = new UserDTO();
			bean.setId(DataUtility.getLong(request.getParameter("id")));
			bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
			bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
			bean.setLogin(DataUtility.getString(request.getParameter("login")));
			bean.setPassword(DataUtility.getString(request.getParameter("password")));
			bean.setConfirmPassword(DataUtility.getString(request.getParameter("confirmPassword")));
			bean.setAddress(DataUtility.getString(request.getParameter("address")));
			bean.setMobileNo(DataUtility.getString(request.getParameter("mobile")));
			bean.setGender(DataUtility.getString(request.getParameter("gender")));
			String role = request.getParameter("roleId");
			String trim = role.trim();
			RoleModelInt rmode=ModelFactory.getInstance().getRoleModel();
			RoleDTO rbean=rmode.findByPK(DataUtility.getLong(trim));
			bean.setRoleName(DataUtility.getString(rbean.getName()));
			
			bean.setRoleId(DataUtility.getLong(trim));
			bean.setDob(DataUtility.getDate(request.getParameter("dob")));
			populateDTO(bean, request);
			System.out.println("populate end");
			log.debug("UserCtl Method populatebean Ended");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}

	/**
	 * Contains DIsplay logics
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("UserCtl doGet started");
		log.debug("UserCtl Method doGet Started");

		long id = DataUtility.getLong(request.getParameter("id"));
		String op = DataUtility.getString(request.getParameter("operation"));
		// get model
		UserModelInt model = ModelFactory.getInstance().getUserModel();
		UserDTO bean = null;

		if (id > 0 || op != null) {

			try {
				bean = model.findByPk(id);

			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} catch (RecordNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ServletUtility.setBean(bean, request);
		}

		ServletUtility.forward(getView(), request, response);
		log.debug("UserCtl Method doGet Ended");
	}

	/**
	 * Contains Submit logics
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("UserCtl Method doPost Started");
		System.out.println("inside doPost");

		UserDTO bean = (UserDTO) populateBean(request);

		UserModelInt model = ModelFactory.getInstance().getUserModel();

		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op)) {

			try {
				System.out.println("data tends to be added");
				model.add(bean);
				System.out.println("data is added");
				ServletUtility.setSuccessMessage("User Added Successfully", request);
				ServletUtility.forward(getView(), request, response);
			} catch (DuplicateRecordException e) {

				e.printStackTrace();
				ServletUtility.setErrorMessage("User Already Exist", request);
				ServletUtility.setBean(bean, request);
				ServletUtility.forward(getView(), request, response);

			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
			}

		}

		else if (op.equals("Update")) {
			UserModelInt model1 = ModelFactory.getInstance().getUserModel();
			UserDTO bean1 = (UserDTO) populateBean(request);

			System.out.println("password=" + bean1.getPassword());

			try {

				System.out.println("id is" + bean1.getId());

				model.update(bean1);
				ServletUtility.setSuccessMessage("User Updated Successfully", request);

			} catch (Exception e) {
				e.printStackTrace();
			}
			ServletUtility.setBean(bean1, request);
			ServletUtility.forward(getView(), request, response);
		}

		if (OP_RESET.equalsIgnoreCase(op)) {
			System.out.println("inside reset");
			ServletUtility.redirect(ORSView.USER_CTL, request, response);
			return;
		}

		else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.USER_LIST_CTL, request, response);
			return;
		}

		log.debug("UserCtl Method doPostEnded");
	}

	@Override
	protected String getView() {
		return ORSView.USER_VIEW;
	}

}
