package in.co.rays.ORSproject3.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.connection.ConnectionProvider;
import org.hibernate.engine.SessionFactoryImplementor;

import in.co.rays.ORSproject3.utility.HibDataSource;
import in.co.rays.ORSproject3.utility.JDBCDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;


/**
 * @author sir
 *
 */
@WebServlet(name="JasperCtl",urlPatterns={"/ctl/JasperCtl"})
public class JasperCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	/**
	 * Contains display logic
	 */   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		Connection conn=null;
		
		JasperReport jr=null;
		JasperDesign jd=null;
		System.out.println("inside jasper ctl");
		
		try{
			
			ResourceBundle rb=ResourceBundle.getBundle("in.co.rays.ORSproject3.bundle.system");
			String database=rb.getString("DATABASE");
	       if("Hibernate".equalsIgnoreCase(database)){
				Session session = HibDataSource.getSession();
				SessionFactoryImplementor sessionFactoryImplementation = (SessionFactoryImplementor) session.getSessionFactory();
				ConnectionProvider connectionProvider = sessionFactoryImplementation.getConnectionProvider();
			
				try{
					conn= connectionProvider.getConnection();
				}catch(SQLException e){
					e.printStackTrace();
				}
				
	       }
	       
	       if("JDBC".equalsIgnoreCase(database)){
				conn=JDBCDataSource.getConnection();
				}
	       
		   HashMap map = new HashMap();
		   String path= getServletContext().getRealPath("/WEB-INF/");
		   jd= JRXmlLoader.load(path+"/Mraksheet.jrxml");
		   jr= JasperCompileManager.compileReport(jd);
		   
		   byte[] byteStream=JasperRunManager.runReportToPdf(jr,map,conn);
		
		   java.io.OutputStream  os=response.getOutputStream();
		    response.setContentType("application/pdf");
		    response.setContentLength(byteStream.length);
		    os.write(byteStream,0, byteStream.length);
	       
	       
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
     * Contains Submit logics
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.JASPER_CTL;
	}

}
