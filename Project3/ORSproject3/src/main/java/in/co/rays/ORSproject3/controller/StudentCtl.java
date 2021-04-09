package in.co.rays.ORSproject3.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.ORSproject3.dto.BaseDTO;
import in.co.rays.ORSproject3.dto.StudentDTO;
import in.co.rays.ORSproject3.exception.ApplicationException;
import in.co.rays.ORSproject3.exception.DuplicateRecordException;
import in.co.rays.ORSproject3.model.CollegeModelInt;
import in.co.rays.ORSproject3.model.ModelFactory;
import in.co.rays.ORSproject3.model.StudentModelInt;
import in.co.rays.ORSproject3.utility.DataUtility;
import in.co.rays.ORSproject3.utility.DataValidator;
import in.co.rays.ORSproject3.utility.PropertyReader;
import in.co.rays.ORSproject3.utility.ServletUtility;

/**
 * Student functionality Controller. Performs operation for add, update, delete
 * and get Student
 *
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@WebServlet(name = "StudentCtl", urlPatterns = { "/ctl/StudentCtl" })
public class StudentCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(StudentCtl.class);

	@Override
	protected void preload(HttpServletRequest request) {
		CollegeModelInt model = ModelFactory.getInstance().getCollegeModel();
		try {
			List l = model.list(0, 0);
			request.setAttribute("collegeList", l);
		} catch (ApplicationException e) {
			log.error(e);
		}

	}

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("StudentCtl Method validate Started");
		System.out.println("StCTL validate");
		boolean pass = true;

		String op = DataUtility.getString(request.getParameter("operation"));
		String email = request.getParameter("email");
		String dob = request.getParameter("dob");

		if (DataValidator.isNull(request.getParameter("collegeId"))) {
			request.setAttribute("collegeId", PropertyReader.getValue("error.require", "College Name"));
			pass = false;
		}

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

		if (DataValidator.isNull(request.getParameter("mobile"))) {
			request.setAttribute("mobile", PropertyReader.getValue("error.require", "Mobile No"));
			pass = false;
		} else if (!DataValidator.isMobileNo(request.getParameter("mobile"))) {
			request.setAttribute("mobile", PropertyReader.getValue("error.mobile", "Invalid"));
			pass = false;
		}
		if (DataValidator.isNull(email)) {
			request.setAttribute("email", PropertyReader.getValue("error.require", "Email "));
			pass = false;
		} else if (!DataValidator.isEmail(email)) {
			request.setAttribute("email", PropertyReader.getValue("error.email", "InVaild "));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("collegeId"))) {
			request.setAttribute("collegeId", PropertyReader.getValue("error.require", "College Name"));
			pass = false;
		}
		if (DataValidator.isNull(dob)) {
			request.setAttribute("dob", PropertyReader.getValue("error.require", "Date Of Birth"));
			pass = false;
		} else if (!DataValidator.isValidAge(dob)) {
			request.setAttribute("dob", PropertyReader.getValue("error.date", "Age"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("address"))) {
			request.setAttribute("address", PropertyReader.getValue("error.require", "Address"));
			pass = false;
		} else if (!DataValidator.isAddress(request.getParameter("address"))) {
			request.setAttribute("address", "Invalid Address");
			pass = false;
		}

		log.debug("StudentCtl Method validate Ended");

		return pass;
	}

	@Override
	protected BaseDTO populateBean(HttpServletRequest request) {

		log.debug("StudentCtl Method populatebean Started");
		System.out.println("stctl populate");
		StudentDTO bean = new StudentDTO();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
		bean.setMobileNo(DataUtility.getString(request.getParameter("mobile")));
		bean.setEmail(DataUtility.getString(request.getParameter("email")));
		bean.setDob(DataUtility.getDate(request.getParameter("dob")));
		bean.setCollegeId(DataUtility.getLong(request.getParameter("collegeId")));
		bean.setCollegeName(DataUtility.getString(request.getParameter("collegeName")));
		bean.setAddress(DataUtility.getString(request.getParameter("address")));		

		populateDTO(bean, request);

		log.debug("StudentCtl Method populatebean Ended");

		return bean;
	}

	/**
	 * Contains Display logics
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("StudentCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));

		// get model

		StudentModelInt model =ModelFactory.getInstance().getStudentModel();
		
		if (id > 0 || op != null) {
			StudentDTO bean;
			try {
				bean = model.findByPK(id);
				ServletUtility.setBean(bean, request);
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forward(getView(), request, response);
		log.debug("StudentCtl Method doGett Ended");
	}

	/**
	 * Contains Submit logics
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("StudentCtl Method doPost Started");
		System.out.println("STCTL dopost");
		String op = DataUtility.getString(request.getParameter("operation"));

		// get model

		StudentModelInt model = ModelFactory.getInstance().getStudentModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op)) {

			StudentDTO bean1 = (StudentDTO) populateBean(request);
			long pk = 0;

			try {
				pk = model.add(bean1);
				bean1.setId(pk);
				ServletUtility.setSuccessMessage("Student Added Successfully", request);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				e.printStackTrace();
			} catch (DuplicateRecordException e) {
				// TODO Auto-generated catch block
				ServletUtility.setErrorMessage("Student Already Exist", request);
				ServletUtility.setBean(bean1, request);
				e.printStackTrace();
			}

			ServletUtility.forward(getView(), request, response);
		}

		else if (op.equals("Update")) {

			StudentDTO bean2 = (StudentDTO) populateBean(request);
			if (id > 0) {
				try {
					model.update(bean2);

					ServletUtility.setSuccessMessage("Student Updated Successfully", request);
				} catch (ApplicationException e) {
					e.printStackTrace();
					ServletUtility.handleException(e, request, response);
				} catch (DuplicateRecordException e) {
					e.printStackTrace();
					ServletUtility.setErrorMessage("Student Already Exist", request);
				}

			}
			ServletUtility.setBean(bean2, request);
			ServletUtility.forward(getView(), request, response);
		return;
		}

		else if (OP_CANCEL.equalsIgnoreCase(op)) {
			System.out.println("cancel worked");
			ServletUtility.redirect(ORSView.STUDENT_LIST_CTL, request, response);

		} else if (OP_RESET.equalsIgnoreCase(op)) {
			System.out.println("reset worked");
			ServletUtility.redirect(ORSView.STUDENT_CTL, request, response);
			return;

		}
	}

	@Override
	protected String getView() {
		return ORSView.STUDENT_VIEW;
	}

}