package in.co.rays.ORSproject3.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.ORSproject3.dto.BaseDTO;
import in.co.rays.ORSproject3.dto.RoleDTO;
import in.co.rays.ORSproject3.exception.ApplicationException;
import in.co.rays.ORSproject3.exception.DuplicateRecordException;
import in.co.rays.ORSproject3.model.ModelFactory;
import in.co.rays.ORSproject3.model.RoleModelInt;
import in.co.rays.ORSproject3.utility.DataUtility;
import in.co.rays.ORSproject3.utility.DataValidator;
import in.co.rays.ORSproject3.utility.PropertyReader;
import in.co.rays.ORSproject3.utility.ServletUtility;

/**
 * Role functionality Controller. Performs operation for add, update and get
 * Role
 *
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@WebServlet(name = "RoleCtl", urlPatterns = { "/ctl/RoleCtl" })
public class RoleCtl extends BaseCtl {

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(RoleCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("RoleCtl Method validate Started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("name"))) {
			request.setAttribute("name", "Enter Vaild Name");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("description"))) {
			request.setAttribute("description", PropertyReader.getValue("error.require", "Description"));
			pass = false;
		}

		log.debug("RoleCtl Method validate Ended");

		return pass;
	}

	@Override
	protected BaseDTO populateBean(HttpServletRequest request) {

		log.debug("RoleCtl Method populatebean Started");

		RoleDTO bean = new RoleDTO();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));

		populateDTO(bean, request);

		log.debug("RoleCtl Method populatebean Ended");

		return bean;
	}

	/**
	 * Contains Display logics
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("RoleCtl Method doGet Started");

		System.out.println("In do get");

		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		RoleModelInt model = ModelFactory.getInstance().getRoleModel();

		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0 || op != null) {
			RoleDTO bean;
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
		log.debug("RoleCtl Method doGetEnded");
	}

	/**
	 * Contains Submit logics
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("RoleCtl Method doGet Started");

		System.out.println("In do get");

		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		RoleModelInt model = ModelFactory.getInstance().getRoleModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op)) {

			RoleDTO bean;
			bean = (RoleDTO) populateBean(request);

			try {
				model.add(bean);
				ServletUtility.setSuccessMessage("Role Added Successfully", request);
			} catch (DuplicateRecordException e) {
				e.printStackTrace();
				ServletUtility.setErrorMessage("Role Already Exist", request);
				ServletUtility.setBean(bean, request);
			} catch (ApplicationException e) {
				e.printStackTrace();
			}

		} else if (op.equals("Update")) {
			RoleDTO bean1 = (RoleDTO) populateBean(request);

			try {
				model.update(bean1);
				ServletUtility.setSuccessMessage("Role Updated Successfully", request);
				ServletUtility.setBean(bean1, request);
			} catch (DuplicateRecordException e) {
				e.printStackTrace();
				ServletUtility.setErrorMessage("Role Already Exist", request);
				ServletUtility.setBean(bean1, request);
			} catch (ApplicationException e) {
				e.printStackTrace();
			}
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.ROLE_LIST_CTL, request, response);
			return;
		}

		else if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.ROLE_CTL, request, response);
			return;

		}

		ServletUtility.forward(getView(), request, response);

		log.debug("RoleCtl Method doPOst Ended");
	}

	@Override
	protected String getView() {
		return ORSView.ROLE_VIEW;
	}

}