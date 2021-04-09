package in.co.rays.ORSproject3.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.ORSproject3.utility.ServletUtility;

/**
 * @author '
 *
 */

@WebServlet (name="ErrorCtl" ,urlPatterns="/ErrorCtl")
public class ErrorCtl extends BaseCtl{
private static final long serialVersionUID = 1L;
       
    
	/* 
	 * contains view logic
	 * */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServletUtility.forward(getView(), request, response);
		
	}
	/* 
	 * contains submit logic
	 * */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		return ORSView.ERROR_VIEW;
	}

}
