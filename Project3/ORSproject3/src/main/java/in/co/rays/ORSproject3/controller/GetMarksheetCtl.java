package in.co.rays.ORSproject3.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.ORSproject3.dto.BaseDTO;
import in.co.rays.ORSproject3.dto.MarksheetDTO;
import in.co.rays.ORSproject3.exception.ApplicationException;
import in.co.rays.ORSproject3.model.MarksheetModelInt;
import in.co.rays.ORSproject3.model.ModelFactory;
import in.co.rays.ORSproject3.utility.DataUtility;
import in.co.rays.ORSproject3.utility.DataValidator;
import in.co.rays.ORSproject3.utility.PropertyReader;
import in.co.rays.ORSproject3.utility.ServletUtility;


/**
 * Get Marksheet functionality Controller. Performs operation for Get Marksheet
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

@WebServlet(name = "GetMarksheetCtl", urlPatterns = { "/ctl/GetMarksheetCtl" })
public class GetMarksheetCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(GetMarksheetCtl.class);
	
	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("GetMarksheetCTL Method validate Started");
		System.out.println("inside vaildate");
		boolean pass = true;
		
		if (DataValidator.isNull(request.getParameter("rollNo"))) {
			request.setAttribute("rollno1", PropertyReader.getValue("error.require", "Roll No"));
			System.out.println("1");
			pass = false;
	
		}
		
		
		else if(!DataValidator.isRollNo(request.getParameter("rollNo"))){
			request.setAttribute("rollno1","Enter Valid roll No");
			System.out.println("2");
			pass=false;
		}
		
		log.debug("GetMarksheetCTL Method validate Ended");
		System.out.println("inlast invalidate ");
		return pass;
	}

	@Override
	protected BaseDTO populateBean(HttpServletRequest request) {
		log.debug("GetMarksheetCtl Method populatebean Begin");
		System.out.println("inside basebean");
		MarksheetDTO bean = new MarksheetDTO();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setRollNo(DataUtility.getString(request.getParameter("rollNo")));

		bean.setName(DataUtility.getString(request.getParameter("name")));

		bean.setPhysics(DataUtility.getInt(request.getParameter("physics")));

		bean.setChemistry(DataUtility.getInt(request.getParameter("chemistry")));

		bean.setMaths(DataUtility.getInt(request.getParameter("maths")));

		log.debug("GetMarksheetCtl Method populatebean Ended");
		
		return bean;
	}

	/**
	 * Contains display logic
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ServletUtility.forward(getView(), request, response);
	}

	/**
	 * Contains Submit logics
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("inside dopost method");

		log.debug("MarksheetCtl Method doGet Begin");

		MarksheetModelInt model =ModelFactory.getInstance().getMarksheetModel();
		

		MarksheetDTO bean = (MarksheetDTO) populateBean(request);

		String op = DataUtility.getString(request.getParameter("operation"));
		//MarksheetBean rbean=null;
		System.out.println("operation in " + op);
		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_GO.equalsIgnoreCase(op)) {

			try {
				bean = model.findByRollNo(bean.getRollNo());
				System.out.println(bean);
				//System.out.println("bean value is------------->" + bean.getRollNo() + " " + bean.getName());

				if (bean!=null) {
					 System.out.println("if part of roll no");
					ServletUtility.setBean(bean, request);
					//ServletUtility.setSuccessMessage("Marksheet Found", request);
					ServletUtility.forward(getView(), request, response);
				}
				else {
					 System.out.println("else part");
					ServletUtility.setErrorMessage("No Record found", request);
					ServletUtility.setBean(bean, request);
					ServletUtility.forward(getView(), request, response);
				}
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

			
		}

		else if(OP_RESET.equalsIgnoreCase(op)){
			System.out.println("reset per");
			ServletUtility.redirect(ORSView.GET_MARKSHEET_CTL, request, response);
			return;
		}
		
		log.debug("MarksheetCtl Method doGet Ended");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.GET_MARKSHEET_VIEW;
	}

}
