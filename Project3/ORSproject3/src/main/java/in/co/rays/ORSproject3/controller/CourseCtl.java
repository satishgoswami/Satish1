package in.co.rays.ORSproject3.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.ORSproject3.dto.BaseDTO;
import in.co.rays.ORSproject3.dto.CourseDTO;
import in.co.rays.ORSproject3.exception.ApplicationException;
import in.co.rays.ORSproject3.exception.DuplicateRecordException;
import in.co.rays.ORSproject3.model.CourseModelInt;
import in.co.rays.ORSproject3.model.ModelFactory;
import in.co.rays.ORSproject3.utility.DataUtility;
import in.co.rays.ORSproject3.utility.DataValidator;
import in.co.rays.ORSproject3.utility.PropertyReader;
import in.co.rays.ORSproject3.utility.ServletUtility;


/**
 * * User functionality Controller. Performs operation for add, update and get
 * Course
 *
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

@WebServlet(name = "CourseCtl", urlPatterns = { "/ctl/CourseCtl" })

public class CourseCtl extends BaseCtl {

	private static final long serialVersionID = 1L;

	private static Logger log = Logger.getLogger(CourseCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("CourseCtl Method validate Started");
		System.out.println("Validate Started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("CourseName"))) {
			request.setAttribute("CourseName", PropertyReader.getValue("error.require", "Course name"));
			pass = false;
		} /*else if (!DataValidator.isName1(request.getParameter("CourseName"))) {
			request.setAttribute("CourseName", "Invalid course name");
			pass=false;
		}*/

		if (DataValidator.isNull(request.getParameter("Duration"))) {
			request.setAttribute("Duration", PropertyReader.getValue("error.require", "Duration"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("Description"))) {
			request.setAttribute("Description", PropertyReader.getValue("error.require", "Description"));
			pass = false;
		}
		/*else if (!DataValidator.isName1(request.getParameter("Description"))) {
			request.setAttribute("Description", "Invalid Description");
			pass = false;
		}
*/

		log.debug("CourseCtl Method validate Ended");
		return pass;
	}

	protected BaseDTO populateBean(HttpServletRequest request) {
		log.debug("CourseCtl populateBean started");

		CourseDTO bean = null;

		bean = new CourseDTO();
		bean.setCourseName(DataUtility.getString(request.getParameter("CourseName")));
		bean.setDuration(DataUtility.getString(request.getParameter("Duration")));
		bean.setDescription(DataUtility.getString(request.getParameter("Description")));
		//bean.setId(DataUtility.getLong("id"));
		populateDTO(bean, request);
		System.out.println("populate end");
		log.debug("CourseCtl Method populatebean Ended");

		return bean;
	}

	/**
	 * Contains DIsplay logics
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("CourseCtl doGet started");
		log.debug("CourseCtl Method doGet Started");

		long id = DataUtility.getLong(request.getParameter("id"));
		String op = DataUtility.getString(request.getParameter("operation"));
		// get model
		CourseModelInt model = ModelFactory.getInstance().getCourseModel();
		CourseDTO bean = null;

		if (id >0) {

			System.out.println("inside id>0");
			try {
			bean=model.findByPK(id);
			
			System.out.println("name is"+bean.getCourseName());
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.setBean(bean, request);
		ServletUtility.forward(getView(), request, response);
		log.debug("CourseCtl ended");
	}

	/**
	 * Contains Submit logics
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("CourseCtl Method doPost Started");
		System.out.println("inside doPost");

		CourseDTO bean = (CourseDTO) populateBean(request);
		CourseModelInt model =ModelFactory.getInstance().getCourseModel();

		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op)) {
			try {
				model.add(bean);
				ServletUtility.setSuccessMessage("Course added successfully", request);
				ServletUtility.forward(getView(), request, response);
			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
			} catch (DuplicateRecordException e) {
				e.printStackTrace();
				ServletUtility.setErrorMessage("User Already Exist", request);
				ServletUtility.setBean(bean, request);
				ServletUtility.forward(getView(), request, response);
			}
		}

		else if(OP_UPDATE.equalsIgnoreCase(op)){
			
			CourseDTO bean1=(CourseDTO) populateBean(request);
			CourseModelInt model1=ModelFactory.getInstance().getCourseModel();
			
				try {
					model1.update(bean1);
					ServletUtility.setSuccessMessage("Course Updated Successfully", request);	
				} catch (ApplicationException e) {
					e.printStackTrace();
					//ServletUtility.handleException(e, request, response);
				} catch (DuplicateRecordException e) {
					e.printStackTrace();
				}
		ServletUtility.setBean(bean1, request);
		ServletUtility.forward(getView(), request, response);	
	
		}

	if(OP_RESET.equalsIgnoreCase(op)){
	ServletUtility.redirect(ORSView.COURSE_CTL, request, response);
	return;
	}
	if(OP_CANCEL.equalsIgnoreCase(op)){
		ServletUtility.redirect(ORSView.COURSE_LIST_CTL, request, response);
		return;
	}
	
	log.debug("CourseCtl ended");
	}

	@Override
	protected String getView() {
		return ORSView.COURSE_VIEW;
	}

}
