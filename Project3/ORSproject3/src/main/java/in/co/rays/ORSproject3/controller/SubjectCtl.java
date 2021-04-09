package in.co.rays.ORSproject3.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.ORSproject3.dto.BaseDTO;
import in.co.rays.ORSproject3.dto.SubjectDTO;
import in.co.rays.ORSproject3.exception.ApplicationException;
import in.co.rays.ORSproject3.exception.DuplicateRecordException;
import in.co.rays.ORSproject3.model.CourseModelInt;
import in.co.rays.ORSproject3.model.ModelFactory;
import in.co.rays.ORSproject3.model.SubjectModelInt;
import in.co.rays.ORSproject3.utility.DataUtility;
import in.co.rays.ORSproject3.utility.DataValidator;
import in.co.rays.ORSproject3.utility.PropertyReader;
import in.co.rays.ORSproject3.utility.ServletUtility;


/**
 * Subject functionality Controller. Performs operation for add, update, delete
 * and get Subject
 *
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@WebServlet(name = "SubjectCtl", urlPatterns = "/ctl/SubjectCtl")
public class SubjectCtl extends BaseCtl {

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(SubjectCtl.class);

	@Override
	protected void preload(HttpServletRequest request) {

		CourseModelInt model =ModelFactory.getInstance().getCourseModel();

		try {
			List list = model.list();

			request.setAttribute("CourseList", list);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("CourseId"))) {
			request.setAttribute("CourseName", PropertyReader.getValue("error.require", "Course Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("SubjectName"))) {
			request.setAttribute("SubjectName", PropertyReader.getValue("error.require", "Subject Name"));
			pass = false;
		} else if (!DataValidator.isName1(request.getParameter("SubjectName"))) {
			request.setAttribute("SubjectName", "Invalid Subject Name");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("Description"))) {
			request.setAttribute("Description", PropertyReader.getValue("error.require", "Description"));
			pass = false;
		}

		return pass;
	}

	@Override
	protected BaseDTO populateBean(HttpServletRequest request) {

		SubjectDTO bean = new SubjectDTO();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setCourseId(DataUtility.getInt(request.getParameter("CourseId")));
		bean.setSubjectName(DataUtility.getStringData(request.getParameter("SubjectName")));
		bean.setDescription(DataUtility.getStringData(request.getParameter("Description")));
		populateDTO(bean, request);
		return bean;
	}

	/* 
	 * contains View logic
	 *  */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		long id = DataUtility.getLong(request.getParameter("id"));
		String op = DataUtility.getString(request.getParameter("operation"));

		SubjectModelInt model =ModelFactory.getInstance().getSubjectModel();

		if (id > 0) {
			SubjectDTO bean;

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

	/* 
	 * Contains submit logic
	 * */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		long id = DataUtility.getLong(request.getParameter("id"));
		String op = DataUtility.getString(request.getParameter("operation"));

		SubjectModelInt model =ModelFactory.getInstance().getSubjectModel();

		if (OP_SAVE.equalsIgnoreCase(op)) {
			SubjectDTO bean = (SubjectDTO) populateBean(request);

			try {
				long id1 = model.add(bean);

				if (id1 > 0) {
					ServletUtility.setSuccessMessage("Data Added Successfully", request);

				}

			} catch (ApplicationException e) {
				e.printStackTrace();
			} catch (DuplicateRecordException e) {
				ServletUtility.setErrorMessage("Record already exist", request);
				ServletUtility.setBean(bean, request);
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			ServletUtility.forward(getView(), request, response);
		}

		else if (OP_UPDATE.equalsIgnoreCase(op)) {
			SubjectDTO bean = (SubjectDTO) populateBean(request);
			System.out.println(bean.getCourseId());
			try {
				SubjectModelInt model1 =ModelFactory.getInstance().getSubjectModel();
				model1.update(bean);
				ServletUtility.setSuccessMessage("Subject Updated Successfully", request);
			}  catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DuplicateRecordException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ServletUtility.setBean(bean, request);
			ServletUtility.forward(getView(), request, response);
		}

		else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.SUBJECT_CTL, request, response);
			return;
		}

		else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.SUBJECT_LIST_CTL, request, response);
			return;
		}

	}

	@Override
	protected String getView() {
		return ORSView.SUBJECT_VIEW;
	}

}
