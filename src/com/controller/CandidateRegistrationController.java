package com.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.algorithm.MD5;
import com.bean.CandidateBean;
import com.bean.UserBean;
import com.dao.CandidateDao;
import com.dao.UserDao;
import com.mail.RegisterMail;

@MultipartConfig(maxFileSize = 10485760)
@WebServlet("/CandidateRegistrationController")
public class CandidateRegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SAVE_DIR="upload";
	InputStream image=null;		
String imageName="";
	String path=null;
	File upload=null;
    public CandidateRegistrationController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out=response.getWriter();
		
		String name=request.getParameter("name1");
		System.out.println("Candiadate Name:"+name);
		String address=request.getParameter("address1");
		String email=request.getParameter("email1");
		String mobileno=request.getParameter("mobileno1");
		String dob=request.getParameter("dob1");
		String password=request.getParameter("password1");
		String adhar=request.getParameter("adhar1");
		String status="InActive";
		CandidateDao db=new CandidateDao();
		Part part=request.getPart("file");
		
		if(part!=null)
		{
			image=part.getInputStream();
			System.out.println("is size:"+image.available());
			imageName=db.extractFileName(part);
			System.out.println("name:"+imageName);
		}
		
		MD5 md5=new MD5();
		String hashvalue=md5.generate(name);
		String filename=name+".txt";
	      File myObj = new File("E:\\Nodes\\Candidate\\"+filename+hashvalue);
	      if (myObj.createNewFile()) {
	        System.out.println("File created: " + myObj.getName());
	      } else {
	        System.out.println("File already exists.");
	      }
	      
	      FileWriter myWriter = new FileWriter("E:\\Nodes\\Candidate\\"+filename+hashvalue);
	      myWriter.write("Candidate Details-"+"Name:"+name+",Address:"+address+",Email:"+email+",Mobile No:"+mobileno+",Adhar No:"+adhar+"");
	      myWriter.close();
	      
	     
		CandidateBean bean=new CandidateBean();
		bean.setName(name);
		bean.setAddress(address);
		bean.setEmail(email);
		bean.setMobileno(mobileno);
		bean.setDob(dob);
		bean.setPassword(password);
		bean.setAdhar(adhar);
		bean.setStatus(status);
        bean.setImage(image);
		
		bean.setImage_name(imageName);
		
		
		try {
			if(db.alreadyUser(email))
			{
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Candidate already exists')");
				out.println("location='CandidateLogin.jsp';");
				out.println("</script>");
				
			}
			else
			{
			if(db.InsertUserData(bean))
			{
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Registration Successful')");
				out.println("location='CandidateLogin.jsp';");
				out.println("</script>");
				
				RegisterMail mail=new RegisterMail();
				mail.emailutility(email);
			}
			else
			{
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Registration UnSuccessful')");
				out.println("location='CandidateRegister.jsp';");
				out.println("</script>");
			}
}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
