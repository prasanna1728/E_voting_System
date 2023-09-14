package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.CandidateBean;
import com.dao.CandidateDao;
import com.mail.EmailSend;

@WebServlet("/CandidateLoginController")
public class CandidateLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public CandidateLoginController() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		
		CandidateDao dao=new CandidateDao();
		
		CandidateBean bean=new CandidateBean();
		
		bean=dao.Check(email, password);
		
		if((bean.getEmail()!=null && bean.getEmail()!="") && (bean.getPassword()!=null && bean.getPassword()!=""))
		{
			System.out.println("Candidate Status:"+bean.getStatus());
			if(bean.getStatus().equals("Active"))
			{
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Login Sucessful...')");
				out.println("location='CandidateHome.jsp';");
				out.println("</script>");
				HttpSession session=request.getSession();  
		        session.setAttribute("emailID",email);
		        session.setAttribute("name",bean.getName());
				}
			else {
				request.setAttribute("ErrMsg", "Wait for activation of your account!...");
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Wait For Activation')");
				out.println("location='CandidateLogin.jsp';");
				out.println("</script>");
			}
		}
		else
		{
	     out.println("<script type=\"text/javascript\">");
		 out.println("alert('Invalid Login Details')");
		 out.println("location='CandidateLogin.jsp';");
		out.println("</script>");
			
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
