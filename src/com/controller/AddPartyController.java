package com.controller;

import java.io.File;

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

import com.bean.CandidateBean;
import com.bean.PartyBean;
import com.dao.AdminDao;
import com.dao.CandidateDao;
import com.mail.RegisterMail;

@MultipartConfig(maxFileSize = 10485760)
@WebServlet("/AddPartyController")
public class AddPartyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	InputStream image=null;		
	String imageName="";
		String path=null;
		File upload=null;
		
    public AddPartyController() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
        PrintWriter out=response.getWriter();
		
		String name=request.getParameter("partyname");
		System.out.println("partyname+"+name);
		String founder=request.getParameter("founder");
		String pre=request.getParameter("pre");
		
		AdminDao dao=new AdminDao();
		Part part=request.getPart("symbol");
		
		if(part!=null)
		{
			image=part.getInputStream();
			System.out.println("is size:"+image.available());
			imageName=dao.extractFileName(part);
			System.out.println("name:"+imageName);
		}
		
		
		PartyBean bean=new PartyBean();
		
		bean.setPartyname(name);
		bean.setFounder(founder);
		bean.setPre(pre);
        bean.setImage(image);
		

		
		
		if(dao.AddParty(bean))
		{
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Add Party Information')");
			out.println("location='AddParty.jsp';");
			out.println("</script>");
			
			
		}
		else
		{
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Fail.....')");
			out.println("location='AddParty.jsp';");
			out.println("</script>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
