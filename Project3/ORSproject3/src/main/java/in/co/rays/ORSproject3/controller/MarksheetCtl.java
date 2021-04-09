package in.co.rays.ORSproject3.controller;


import java.io.IOException;
import java.util.List;

import javax.management.modelmbean.ModelMBean;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.ORSproject3.dto.BaseDTO;
import in.co.rays.ORSproject3.dto.MarksheetDTO;
import in.co.rays.ORSproject3.exception.ApplicationException;
import in.co.rays.ORSproject3.exception.DuplicateRecordException;
import in.co.rays.ORSproject3.model.MarksheetModelInt;
import in.co.rays.ORSproject3.model.ModelFactory;
import in.co.rays.ORSproject3.model.StudentModelHibImpl;
import in.co.rays.ORSproject3.model.StudentModelInt;
import in.co.rays.ORSproject3.utility.DataUtility;
import in.co.rays.ORSproject3.utility.DataValidator;
import in.co.rays.ORSproject3.utility.PropertyReader;
import in.co.rays.ORSproject3.utility.ServletUtility;

/**
 * Marksheet functionality Controller. Performs operation for add, update,
 * delete and get Marksheet
 *
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@WebServlet(name = "MarksheetCtl", urlPatterns = "/ctl/MarksheetCtl")

public class MarksheetCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(MarksheetCtl.class);

	protected void preload(HttpServletRequest request) {

		StudentModelInt model = ModelFactory.getInstance().getStudentModel();

		try {
			List list = model.list();
			request.setAttribute("StudentList", list);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("rollno"))) {
			request.setAttribute("rollno", PropertyReader.getValue("error.require", "RollNo"));
			pass = false;
		} else if (!DataValidator.isRollNo(request.getParameter("rollno"))) {
			request.setAttribute("rollno", "Invalid Roll No (Enter Roll no in format as  Ex. 101CS01)");
			pass = false;
		}

		/*
		 * else if(!DataValidator.isInteger(request.getParameter("rollno")))
		 * 
		 * { request.setAttribute("rollno", "Enter Roll No Only"); pass=false; }
		 */

		if (DataValidator.isNull(request.getParameter("studentid"))) {
			request.setAttribute("studentid1", PropertyReader.getValue("error.require", "Student Name"));
			pass = false;
			System.out.println("pass in student" + pass);
		}

		if (DataValidator.isNull(request.getParameter("physics"))) {
			request.setAttribute("physics1", PropertyReader.getValue("error.require", "Physics Marks"));
			pass = false;
			System.out.println("pass in physics" + pass);
		}

		else if (DataUtility.getInt(request.getParameter("physics")) > 100) {
			request.setAttribute("physics1", "Marks Can Not Be More Then 100");
			pass = false;
		}

		else if (DataUtility.getInt(request.getParameter("physics")) < 0) {
			request.setAttribute("physics1", "Marks Can Not Be Negative");
			pass = false;
		}

		else if (!DataValidator.isInteger(request.getParameter("physics"))
				&& DataValidator.isNotNull(request.getParameter("physics"))) {
			request.setAttribute("physics1", "Enter Number Only");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("chemistry"))) {
			request.setAttribute("chemistry", PropertyReader.getValue("error.require", "Chemistry Marks"));
			pass = false;

		}

		else if (DataUtility.getInt(request.getParameter("chemistry")) > 100) {
			request.setAttribute("chemistry", "Marks Can Not Be More Then 100");
			pass = false;
		}

		else if (DataUtility.getInt(request.getParameter("chemistry")) < 0) {
			request.setAttribute("chemistry", "Marks Can Not Be Negative");
			pass = false;
		} else if (!DataValidator.isInteger(request.getParameter("chemistry"))
				&& DataValidator.isNotNull(request.getParameter("chemistry"))) {
			request.setAttribute("chemistry", "Enter Number Only");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("math"))) {
			request.setAttribute("math", PropertyReader.getValue("error.require", "Maths Marks"));
			pass = false;

		}

		else if (DataUtility.getInt(request.getParameter("math")) > 100) {
			request.setAttribute("math", "Marks Can Not Be More Then 100");
			pass = false;
		}

		else if (DataUtility.getInt(request.getParameter("math")) < 0) {
			request.setAttribute("math", "Marks Can not be negative");
			pass = false;
		}

		else if (!DataValidator.isInteger(request.getParameter("math"))
				&& DataValidator.isNotNull(request.getParameter("math"))) {
			request.setAttribute("math", "Enter Number Only");
			pass = false;
		}
		return pass;
	}

	protected BaseDTO populateBean(HttpServletRequest request) {

		MarksheetDTO bean = new MarksheetDTO();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setRollNo(DataUtility.getString(request.getParameter("rollno")));
		bean.setStudentId(DataUtility.getLong(request.getParameter("studentid")));
		bean.setPhysics(DataUtility.getInt(request.getParameter("physics")));
		bean.setChemistry(DataUtility.getInt(request.getParameter("chemistry")));
		bean.setMaths(DataUtility.getInt(request.getParameter("math")));
		populateDTO(bean, request);
		return bean;
	}

	/**
	 * Contains display logic
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("inside do get");

		long id = DataUtility.getLong(request.getParameter("id"));
		String op = DataUtility.getString(request.getParameter("operation"));

		//System.out.println("do Get  id : " + id);
		//System.out.println("do Get  op : " + op);

		MarksheetModelInt model =ModelFactory.getInstance().getMarksheetModel();
		if (id > 0) {
			MarksheetDTO bean;
			try {
				bean = model.findByPK(id);
				
				ServletUtility.setBean(bean, request);
			} catch (Exception e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			}

		}
		ServletUtility.forward(getView(), request, response);
		return;
	}

	/**
	 * Contains Submit logics
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("inside dopost");

		log.debug("");

		MarksheetModelInt model =ModelFactory.getInstance().getMarksheetModel();

		String op = DataUtility.getString(request.getParameter("operation"));

		if (OP_SAVE.equalsIgnoreCase(op)) {

			MarksheetDTO bean = (MarksheetDTO) populateBean(request);
			System.out.println("roll no " + bean.getRollNo());

			
				try {
					model.add(bean);

					ServletUtility.setSuccessMessage("Marksheet Added Successfully", request);

				} catch (ApplicationException e) {
					ServletUtility.handleException(e, request, response);
					e.printStackTrace();
				} catch (DuplicateRecordException e) {
					e.printStackTrace();
					ServletUtility.setErrorMessage("Roll Number Already Exist", request);
					ServletUtility.setBean(bean, request);
				}
			

			ServletUtility.forward(getView(), request, response);
		}

		else if (op.equals("Update")) {

			MarksheetDTO bean1 = (MarksheetDTO) populateBean(request);

			long id = DataUtility.getLong(request.getParameter("id"));

			System.out.println("id in update" + id);
			if (id > 0) {
				try {
					MarksheetModelInt model1 =ModelFactory.getInstance().getMarksheetModel();
					model1.update(bean1);
					ServletUtility.setSuccessMessage("Marksheet Updated Successfully", request);
				} catch (Exception e) {
					e.printStackTrace();
					ServletUtility.setErrorMessage("No Record Found", request);
				}
			}
			ServletUtility.setBean(bean1, request);
			ServletUtility.forward(getView(), request, response);
		}

		else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.MARKSHEET_LIST_CTL, request, response);
		return;
		}

		else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.MARKSHEET_CTL, request, response);
			return;
		}

	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.MARKSHEET_VIEW;
	}

}
