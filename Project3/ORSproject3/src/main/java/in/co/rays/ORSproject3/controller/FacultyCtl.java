package in.co.rays.ORSproject3.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.rule.Mode;

import in.co.rays.ORSproject3.dto.BaseDTO;
import in.co.rays.ORSproject3.dto.FacultyDTO;
import in.co.rays.ORSproject3.exception.ApplicationException;
import in.co.rays.ORSproject3.exception.DatabaseException;
import in.co.rays.ORSproject3.model.CollegeModelInt;
import in.co.rays.ORSproject3.model.CourseModelInt;
import in.co.rays.ORSproject3.model.FacultyModelInt;
import in.co.rays.ORSproject3.model.ModelFactory;
import in.co.rays.ORSproject3.model.SubjectModelInt;
import in.co.rays.ORSproject3.utility.DataUtility;
import in.co.rays.ORSproject3.utility.DataValidator;
import in.co.rays.ORSproject3.utility.PropertyReader;
import in.co.rays.ORSproject3.utility.ServletUtility;

@WebServlet(name = "FacultyCtl", urlPatterns = "/ctl/FacultyCtl")
public class FacultyCtl extends BaseCtl {

	@Override
	protected void preload(HttpServletRequest request) {

		CollegeModelInt cmodel = ModelFactory.getInstance().getCollegeModel();
		try {
			List list = cmodel.list(0, 0);
			request.setAttribute("collegelist", list);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

		CourseModelInt model = ModelFactory.getInstance().getCourseModel();
		try {
			List listt = model.list(0, 0);
			request.setAttribute("courselist", listt);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

		SubjectModelInt smodel = ModelFactory.getInstance().getSubjectModel();
		try {
			List list2 = smodel.list(0, 0);
			request.setAttribute("subjectlist", list2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected boolean validate(HttpServletRequest request) {

		System.out.println("inside validation");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("fname"))) {
			request.setAttribute("fname", PropertyReader.getValue("error.require", "First Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("fname"))) {
			request.setAttribute("fname", "Invalid First Name");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("lname"))) {
			request.setAttribute("lname", PropertyReader.getValue("error.require", "Last Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("lname"))) {
			request.setAttribute("lname", "Invalid Last Name");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("gender"))) {
			request.setAttribute("gender", PropertyReader.getValue("error.require", "Gender"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("login"))) {
			request.setAttribute("login1", PropertyReader.getValue("error.require", "Emailid"));
			pass = false;
		} else if (!DataValidator.isEmail(request.getParameter("login"))) {
			request.setAttribute("login1", "Invalid Emailid");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("address"))) {
			request.setAttribute("address1", PropertyReader.getValue("error.require", "Address"));
			pass = false;
		} else if (!DataValidator.isAddress(request.getParameter("address"))) {
			request.setAttribute("address1", "Invalid Address");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("doj"))) {
			request.setAttribute("doj1", PropertyReader.getValue("error.require", "Date Of Joining"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("qual"))) {
			request.setAttribute("qual1", PropertyReader.getValue("error.require", "Qualification"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("mobile"))) {
			request.setAttribute("mobile1", PropertyReader.getValue("error.require", "Mobile No"));
			pass = false;
		} else if (!DataValidator.isMobileNo(request.getParameter("mobile"))) {
			request.setAttribute("mobile1", "Invalid Mobile No");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("collegeid"))) {
			request.setAttribute("collegeid", PropertyReader.getValue("error.require", "College Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("courseid"))) {
			request.setAttribute("courseid", PropertyReader.getValue("error.require", "Course Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("subjectid"))) {
			request.setAttribute("subjectid", PropertyReader.getValue("error.require", "Subject Name"));
			pass = false;
		}
		System.out.println("pass=" + pass);
		return pass;
	}

	protected BaseDTO populateBean(HttpServletRequest request) {

		FacultyDTO bean = new FacultyDTO();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setFirstName(DataUtility.getString(request.getParameter("fname")));
		bean.setLastName(DataUtility.getString(request.getParameter("lname")));
		bean.setGender(DataUtility.getString(request.getParameter("gender")));
		bean.setEmailId(DataUtility.getString(request.getParameter("login")));
		bean.setAddress(DataUtility.getString(request.getParameter("address")));
		bean.setDateOfJoining(DataUtility.getDate(request.getParameter("doj")));
		bean.setQualification(DataUtility.getString(request.getParameter("qual")));
		bean.setMobileNo(DataUtility.getString(request.getParameter("mobile")));
		bean.setCollegeId(DataUtility.getInt(request.getParameter("collegeid")));
		// bean.setCollegeName(DataUtility.getString(request.getParameter("collegeName")));
		bean.setCourseId(DataUtility.getInt(request.getParameter("courseid")));
		// bean.setCourseName(DataUtility.getString(request.getParameter("courseName")));
		bean.setSubjectId(DataUtility.getInt(request.getParameter("subjectid")));
		System.out.println(" subject id is" + request.getParameter("subjectid"));
		System.out.println(" subject name is" + request.getParameter("subjectName"));
		// bean.setSubjectName(DataUtility.getString(request.getParameter("subjectName")));
		populateDTO(bean, request);

		return bean;
	}

	/*
	 * Contains view logic
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		long id = DataUtility.getLong(request.getParameter("id"));
		FacultyDTO bean = null;
		if (id > 0) {

			FacultyModelInt model = ModelFactory.getInstance().getFacultyModel();
			try {
				bean = model.findByPK(id);

			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				e.printStackTrace();
			}
			ServletUtility.setBean(bean, request);
		}

		ServletUtility.forward(getView(), request, response);
	}

	/*
	 * Contains submit logic
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		long id = DataUtility.getLong(request.getParameter("id"));
		String op = DataUtility.getString(request.getParameter("operation"));

		FacultyModelInt model =ModelFactory.getInstance().getFacultyModel();
		FacultyDTO bean = null;

		if (OP_SAVE.equalsIgnoreCase(op)) {
			bean = (FacultyDTO) populateBean(request);
			try {
				model.add(bean);
				ServletUtility.setSuccessMessage("Faculty Added Successfullly", request);
				// ServletUtility.forward(getView(), request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ServletUtility.setErrorMessage("Faculty Already Exist", request);
				ServletUtility.setBean(bean, request);
			}

			ServletUtility.forward(getView(), request, response);
		}

		else if (op.equals("Update")) {

			FacultyDTO bean1 = (FacultyDTO) populateBean(request);

			try {
				model.update(bean1);
				ServletUtility.setBean(bean1, request);
				ServletUtility.setSuccessMessage("Faculty Updated Successfullly", request);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			ServletUtility.forward(getView(), request, response);

		}

		else if (op.equals("Reset")) {
			ServletUtility.redirect(ORSView.FACULTY_CTL, request, response);
			return;
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.FACULTY_LIST_CTL, request, response);
			return;
		}

	}

	@Override
	protected String getView() {
		return ORSView.FACULTY_VIEW;
	}

}
