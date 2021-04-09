package in.co.rays.ORSproject3.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.ORSproject3.dto.BaseDTO;
import in.co.rays.ORSproject3.dto.TimetableDTO;
import in.co.rays.ORSproject3.exception.ApplicationException;
import in.co.rays.ORSproject3.model.CourseModelInt;
import in.co.rays.ORSproject3.model.ModelFactory;
import in.co.rays.ORSproject3.model.SubjectModelInt;
import in.co.rays.ORSproject3.model.TimetableModelInt;
import in.co.rays.ORSproject3.utility.DataUtility;
import in.co.rays.ORSproject3.utility.PropertyReader;
import in.co.rays.ORSproject3.utility.ServletUtility;


/**
 * Timetable List functionality Controller. Performs operation for list, search and
 * delete operations of Subject
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

@WebServlet(name = "TimetableListCtl", urlPatterns = { "/ctl/TimetableListCtl" })
public class TimetableListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	protected void preload(HttpServletRequest request) {

		CourseModelInt comodel= ModelFactory.getInstance().getCourseModel();
		
		try {
			List list1= comodel.list();
			request.setAttribute("courseList",list1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SubjectModelInt somodel = ModelFactory.getInstance().getSubjectModel();
		
		try {
			List list2 = somodel.list();
			request.setAttribute("subjectList",list2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected BaseDTO populateBean(HttpServletRequest request) {

		System.out.println("in populateBean");
		
		
		TimetableDTO bean = new TimetableDTO();
		
		//bean.setId(DataUtility.getInt(request.getParameter("id")));
		bean.setCourseId(DataUtility.getInt(request.getParameter("courseid")));
		//bean.setCourseName(DataUtility.getString(request.getParameter("courseName")));
		bean.setSubId(DataUtility.getInt(request.getParameter("subjectid")));
		//bean.setSubjectName(DataUtility.getString(request.getParameter("subjectName")));
		//bean.setSemester(DataUtility.getString(request.getParameter("sem")));
		//bean.setExamTime(DataUtility.getString(request.getParameter("examtime")));
		
		//if(!OP_DELETE.equalsIgnoreCase(op)){  
		
		bean.setExamDate(DataUtility.getDate(request.getParameter("examdate")));
		
		populateDTO(bean, request);
		
		return bean;
	}

	/**
	 * Contains display logic
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
   
		  System.out.println("inside do get method");
		  
			int pageNo= 1;
			int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
			TimetableModelInt model = ModelFactory.getInstance().getTimetableModel();
			
			TimetableDTO bean = new TimetableDTO();
			List list =new ArrayList();
			List next=null;
			try {
				list = model.search(bean, pageNo, pageSize);
				next= model.search(bean, pageNo+1, pageSize);
				if(list==null || list.size()==0){
					ServletUtility.setErrorMessage("no record found", request);
				}

				if(next==null || next.size()==0)
				{
				  request.setAttribute("nextListSize", "0");	
				}else
				{
				  request.setAttribute("nextListSize", next.size());		
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);

		
	}

	/**
	 * Contains Submit logics
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("inside do post");
		
		long id =DataUtility.getLong(request.getParameter("id"));
		String op = DataUtility.getString(request.getParameter("operation"));
		
		String ids[] = request.getParameterValues("ids");
		
		int pageNo= DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));
		
		pageNo = (pageNo==0)?1:pageNo;
		pageSize = (pageSize==0)?DataUtility.getInt(PropertyReader.getValue("page.size")):pageSize;
		
		if(OP_SEARCH.equalsIgnoreCase(op)){
			pageNo=1;
		}
		else if(OP_PREVIOUS.equalsIgnoreCase(op)){
			if(pageNo>1){
				pageNo--;
			}
			else{
				pageNo=1;
			}
		}
		else if(OP_NEXT.equalsIgnoreCase(op)){
			pageNo++;
		}
		
		else if(OP_NEW.equalsIgnoreCase(op)){
			ServletUtility.redirect(ORSView.TIMETABLE_CTL, request, response);
			return;
		}
		else if(OP_RESET.equalsIgnoreCase(op)){
			ServletUtility.redirect(ORSView.TIMETABLE_LIST_CTL, request, response);
		    return;
		}
		
		else if(OP_BACK.equalsIgnoreCase(op)){
			ServletUtility.redirect(ORSView.TIMETABLE_LIST_CTL, request, response);
			return;
		}
		else if(OP_DELETE.equalsIgnoreCase(op)){
		
			TimetableModelInt mod =ModelFactory.getInstance().getTimetableModel();
			TimetableDTO dbean = new TimetableDTO();
			if(ids!=null && ids.length>0){
				
				for(String id2:ids){
					
					int idnew= DataUtility.getInt(id2);
					dbean.setId(idnew);
					
					try {
						mod.delete(dbean);
						ServletUtility.setSuccessMessage("Record Deleted Successfully", request);
					
					} catch (ApplicationException e) {
						e.printStackTrace();
					}
				}
				
			}
			else{
				ServletUtility.setErrorMessage("Select Atleast One Record", request);
			}
			
			
		}
		
		TimetableModelInt model =ModelFactory.getInstance().getTimetableModel();
		
		TimetableDTO bean = (TimetableDTO)populateBean(request);
		
		List list =new ArrayList();
		List next=null;

		try {
			list = model.search(bean, pageNo, pageSize);
			next= model.search(bean, pageNo+1, pageSize);
			
			if(list==null || list.size()==0 && !OP_DELETE.equalsIgnoreCase(op)){
				ServletUtility.setErrorMessage("No Timetable found", request);
			}
			if(next==null || next.size()==0)
			{
			  request.setAttribute("nextListSize", "0");	
			}else
			{
			  request.setAttribute("nextListSize", next.size());		
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ServletUtility.setBean(bean, request);
		ServletUtility.setList(list, request);
		ServletUtility.setPageNo(pageNo, request);
		ServletUtility.setPageSize(pageSize, request);
		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		return ORSView.TIMETABLE_LIST_VIEW;
	}

}
