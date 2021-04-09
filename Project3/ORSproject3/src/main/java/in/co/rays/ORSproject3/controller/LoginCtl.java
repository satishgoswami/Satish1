package in.co.rays.ORSproject3.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import in.co.rays.ORSproject3.dto.BaseDTO;
import in.co.rays.ORSproject3.dto.RoleDTO;
import in.co.rays.ORSproject3.dto.UserDTO;
import in.co.rays.ORSproject3.exception.ApplicationException;
import in.co.rays.ORSproject3.model.ModelFactory;
import in.co.rays.ORSproject3.model.RoleModelInt;
import in.co.rays.ORSproject3.model.UserModelInt;
import in.co.rays.ORSproject3.utility.DataUtility;
import in.co.rays.ORSproject3.utility.DataValidator;
import in.co.rays.ORSproject3.utility.PropertyReader;
import in.co.rays.ORSproject3.utility.ServletUtility;


/**
 * Login functionality Controller. Performs operation for Login
 *
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@WebServlet(name = "LoginCtl", urlPatterns = { "/LoginCtl" })
public class LoginCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	public static final String OP_REGISTER = "Register";
	public static final String OP_SIGN_IN = "SignIn";
	public static final String OP_SIGN_UP = "SignUp";
	public static final String OP_LOG_OUT = "logout";

	private static Logger log = Logger.getLogger(LoginCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("LoginCtl Method validate Started");

		boolean pass = true;

		String op = request.getParameter("operation");
		if (OP_SIGN_UP.equals(op) || OP_LOG_OUT.equals(op)) {
			return pass;
		}

		String login = request.getParameter("login");

		if (DataValidator.isNull(login)) {
			request.setAttribute("login", PropertyReader.getValue("error.require", "Login Id"));
			pass = false;
		} else if (!DataValidator.isEmail(login)) {
			request.setAttribute("login", PropertyReader.getValue("error.email", "Invaild "));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("password"))) {
			request.setAttribute("password", PropertyReader.getValue("error.require", "Password"));
			pass = false;
		} /*else if (!DataValidator.isPassword(request.getParameter("password"))) {
			request.setAttribute("password", PropertyReader.getValue("error.password", " "));
			pass = false;
		}*/

		log.debug("LoginCtl Method validate Ended");

		return pass;
	}

	@Override
	protected BaseDTO populateBean(HttpServletRequest request) {

		log.debug("LoginCtl Method populatebean Started");

		UserDTO bean = new UserDTO();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setLogin(DataUtility.getString(request.getParameter("login")));
		bean.setPassword(DataUtility.getString(request.getParameter("password")));

		log.debug("LoginCtl Method populatebean Ended");

		return bean;
	}

	/**
	 * Display Login form
	 *
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug(" Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));

		HttpSession session = request.getSession();

		System.out.println("operation value in doget loginctl" + op);

		if (OP_LOG_OUT.equals(op)) {
			session = request.getSession();
			ServletUtility.setSuccessMessage("Logged Out Successfully", request);
			session.invalidate();
			ServletUtility.forward(getView(), request, response);
			return;
		}

		if(session.getAttribute("user")!=null){
			ServletUtility.redirect(ORSView.WELCOME_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);
		log.debug("dogetend");

	}

	/**
	 * Submitting or login action performing
	 *
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug(" Method doPost Started");
		HttpSession session = null;
		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		UserModelInt model =ModelFactory.getInstance().getUserModel();
		RoleModelInt role =ModelFactory.getInstance().getRoleModel();

		long id = DataUtility.getLong(request.getParameter("id"));
		System.out.println(id);

		if (OP_SIGN_IN.equalsIgnoreCase(op)) {
			UserDTO bean = (UserDTO) populateBean(request);

			try {
				bean = model.authenticate(bean.getLogin(), bean.getPassword());

				if (bean != null) {
					session = request.getSession(true);
					session.setAttribute("user", bean);
					long rollId = bean.getRoleId();

					// ServletUtility.forward(ORSView.WELCOME_VIEW, request,
					// response);
					RoleDTO roleBean = role.findByPK(rollId);

					if (roleBean != null) {
						session.setAttribute("role", roleBean.getName());
					}

					// code for uri

					String str = (String) session.getAttribute("URI");
					System.out.println("str..........." + str);

					if (str == null) {
						System.out.println("inside if of str login ctl");
						ServletUtility.redirect(ORSView.WELCOME_CTL, request, response);
						return;
					} else {
						System.out.println("inside else of str login ctl");
						ServletUtility.redirect(str, request, response);
						return;
					}

				} else {
					bean = (UserDTO) populateBean(request);
					ServletUtility.setBean(bean, request);
					ServletUtility.setErrorMessage("Invalid Loginid & Password", request);
					/* ServletUtility.forward(getView(), request, response); */
				}

			} catch (ApplicationException e) {
				e.printStackTrace();
				request.getAttribute("exception");
				ServletUtility.handleException(e, request, response);
				return;
			}

		}  else if (OP_SIGN_UP.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.USER_REGISTRATION_CTL, request, response);
			return;

		}

		ServletUtility.forward(getView(), request, response);

		log.debug("UserCtl Method doPost Ended");
	}

	@Override
	protected String getView() {
		System.out.println("login=" + ORSView.LOGIN_VIEW);
		return ORSView.LOGIN_VIEW;
	}
}
