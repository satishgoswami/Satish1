package in.co.rays.ORSproject3.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.ORSproject3.dto.BaseDTO;
import in.co.rays.ORSproject3.dto.TimetableDTO;
import in.co.rays.ORSproject3.model.CourseModelInt;
import in.co.rays.ORSproject3.model.ModelFactory;
import in.co.rays.ORSproject3.model.SubjectModelInt;
import in.co.rays.ORSproject3.model.TimetableModelInt;
import in.co.rays.ORSproject3.utility.DataUtility;
import in.co.rays.ORSproject3.utility.DataValidator;
import in.co.rays.ORSproject3.utility.PropertyReader;
import in.co.rays.ORSproject3.utility.ServletUtility;


/**
 * * Timetable functionality Controller. Performs operation for add, update and get
 * Timetable
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

@WebServlet(name = "TimetableCtl", urlPatterns = { "/ctl/TimetableCtl" })
public class TimetableCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	protected void preload(HttpServletRequest request) {

		System.out.println("in preload");

		CourseModelInt cmodel =ModelFactory.getInstance().getCourseModel();
		try {
			List list1 = cmodel.list();
			request.setAttribute("courseList", list1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		SubjectModelInt smodel =ModelFactory.getInstance().getSubjectModel();
		try {
			List list2 = smodel.list();
			request.setAttribute("subjectList", list2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected boolean validate(HttpServletRequest request) {
		
		boolean pass=true;
		
		if(DataValidator.isNull(request.getParameter("courseid"))){
			request.setAttribute("courseName1",PropertyReader.getValue("error.require","Course Name"));
		   pass= false;
		}
		if(DataValidator.isNull(request.getParameter("subjectid"))){
			request.setAttribute("subjectName1",PropertyReader.getValue("error.require","Subject Name"));
		   pass= false;
		}
		if(DataValidator.isNull(request.getParameter("sem"))){
			request.setAttribute("sem1",PropertyReader.getValue("error.require","Semester"));
		   pass= false;
		}
		
		if(DataValidator.isNull(request.getParameter("examtime"))){
			request.setAttribute("examtime1",PropertyReader.getValue("error.require","Examtime"));
		   pass= false;
		}
		if(DataValidator.isNull(request.getParameter("examdate"))){
			request.setAttribute("examdate1",PropertyReader.getValue("error.require","Examdate"));
		   pass= false;
		}
		System.out.println("VALUE OF PASS IS"+pass);
		return pass;
	}
	/* (non-Javadoc)
	 * @see in.co.controller.BaseCtl#populateBean(javax.servlet.http.HttpServletRequest)
	 */
	protected BaseDTO populateBean(HttpServletRequest request) {

		System.out.println("in populateBean");
		
		TimetableDTO bean = new TimetableDTO();
		
		bean.setId(DataUtility.getInt(request.getParameter("id")));
		bean.setCourseId(DataUtility.getInt(request.getParameter("courseid")));
		bean.setCourseName(DataUtility.getString(request.getParameter("courseName")));
		System.out.println("Course name is"+request.getParameter("courseName"));
		bean.setSubId(DataUtility.getInt(request.getParameter("subjectid")));
		System.out.println(bean.getSubId()+"------------------pagal--------");
		bean.setSubName(DataUtility.getString(request.getParameter("subjectName")));
		bean.setSemester(DataUtility.getString(request.getParameter("sem")));
		bean.setExamTime(DataUtility.getString(request.getParameter("examtime")));
		bean.setExamDate(DataUtility.getDate(request.getParameter("examdate")));
		populateDTO(bean, request);
		
		return bean;
	}
	

	/**
	 * Contains display logic
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		long id =DataUtility.getLong(request.getParameter("id"));
		
		TimetableModelInt model =ModelFactory.getInstance().getTimetableModel();
		TimetableDTO bean;
		if(id>0){
			try {
				bean=model.findByPK(id);
				ServletUtility.setBean(bean, request);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		ServletUtility.forward(getView(), request, response);
	}

	/**
	 * Contains Submit logics
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		long id =DataUtility.getLong(request.getParameter("id"));
		String op =DataUtility.getString(request.getParameter("operation"));
		
		TimetableModelInt model =ModelFactory.getInstance().getTimetableModel();
		if(OP_SAVE.equalsIgnoreCase(op)){
			TimetableDTO bean1 = (TimetableDTO)populateBean(request);
			try {
				id= model.add(bean1);
				ServletUtility.setSuccessMessage("Timetable Added Successfullly", request);
			} catch (Exception e) {
				e.printStackTrace();
				ServletUtility.setErrorMessage("Timetable Already Exist", request);
				ServletUtility.setBean(bean1, request);
			}
			
			ServletUtility.forward(getView(), request, response);
		}
		
		else if(op.equals("Update")){
			TimetableDTO bean2 = (TimetableDTO)populateBean(request);
			if(id>0){
				
				try {
					model.update(bean2);
					ServletUtility.setSuccessMessage("Timetable Updated Successfullly", request);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					ServletUtility.setErrorMessage("Timetable Already Exist", request);
				}
			}
			ServletUtility.setBean(bean2, request);
			ServletUtility.forward(getView(), request, response);
		}
		
		else if(op.equals("Reset")){
			ServletUtility.redirect(ORSView.TIMETABLE_CTL, request, response);
			return;
		}
		else if(OP_CANCEL.equalsIgnoreCase(op)){
			ServletUtility.redirect(ORSView.TIMETABLE_LIST_CTL, request, response);
		    return;
		}
	}

	@Override
	protected String getView() {
		return ORSView.TIMETABLE_VIEW;
	}

}
