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
import com.bean.UserBean;

import com.dao.UserDao;
import com.mail.RegisterMail;

@MultipartConfig(maxFileSize = 10485760)
@WebServlet("/UserRegistrationController")
public class UserRegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SAVE_DIR="upload";
	InputStream image=null;		
String imageName="";
	String path=null;
	File upload=null;
    public UserRegistrationController() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out=response.getWriter();
		
		String name=request.getParameter("name");
		String address=request.getParameter("address");
		String email=request.getParameter("email");
		String mobileno=request.getParameter("mobileno");
		String dob=request.getParameter("dob");
		String password=request.getParameter("password");
		String adhar=request.getParameter("adhar");
		String selectarea=request.getParameter("selectarea");
		String status="InActive";
		UserDao db=new UserDao();
		Part part=request.getPart("file");
		
		if(part!=null)
		{
			image=part.getInputStream();
			System.out.println("is size:"+image.available());
			imageName=db.extractFileName(part);
			System.out.println("name:"+imageName);
		}
		 //String savePath = "C:" + File.separator + SAVE_DIR+File.separator;
		/*upload=new File("D://upload/");
		if (!upload.isDirectory()) {
			upload.mkdir();
		}
		part.write(upload.getAbsolutePath()+"/"+imageName);
		path=new File(upload.getAbsoluteFile()+"/"+imageName).getAbsolutePath();*/
		
		//String filePath= savePath  + imageName;
	   /*  File f=new File(path);
		
		System.out.println("File Path>>>>>>>>>>"+path);
		String sha256Hash = null;
		try {
			sha256Hash = HashGeneratorUtils.generateSHA256(f);
		} catch (HashGenerationException e1) {
			
			e1.printStackTrace();
		}*/
		
		MD5 md5=new MD5();
		String hashvalue=md5.generate(name);
		String filename=name+".txt";
	      File myObj = new File("E:\\Nodes\\Voters\\"+filename+hashvalue);
	      if (myObj.createNewFile()) {
	        System.out.println("File created: " + myObj.getName());
	      } else {
	        System.out.println("File already exists.");
	      }
	      
	      FileWriter myWriter = new FileWriter("E:\\Nodes\\Voters\\"+filename+hashvalue);
	      myWriter.write("Voters Details-"+"Name:"+name+",Address:"+address+",Email:"+email+",Mobile No:"+mobileno+",Adhar No:"+adhar+",Voter Area:"+selectarea);
	      myWriter.close();
	      
	      
		
		UserBean bean=new UserBean();
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
		bean.setArea(selectarea);
		UserDao dao=new UserDao();

		try {
			if(db.alreadyUser(email))
			{
				out.println("<script type=\"text/javascript\">");
				out.println("alert('User already exists')");
				out.println("location='UserLogin.jsp';");
				out.println("</script>");
				
			}
			else
			{
			if(dao.InsertUserData(bean))
			{
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Registration Successful')");
				out.println("location='UserLogin.jsp';");
				out.println("</script>");
				
				RegisterMail mail=new RegisterMail();
				mail.emailutility(email);
			}
			else
			{
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Registration UnSuccessful')");
				out.println("location='UserRegister.jsp';");
				out.println("</script>");
			}
}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	private File File(String path2) {
		// TODO Auto-generated method stub
		return null;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
