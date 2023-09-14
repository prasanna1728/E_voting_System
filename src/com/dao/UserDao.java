package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.http.Part;

import com.bean.CandidateBean;
import com.bean.PartyBean;
import com.bean.UserBean;
import com.connection.DBConnection;
import java.io.FileReader;
import java.security.SecureRandom;

import au.com.bytecode.opencsv.CSVReader;

public class UserDao {
	String sql;
	PreparedStatement ps;
	ResultSet rs;

	Connection con;
	boolean flag = false;

	public boolean InsertUserData(UserBean userbean) {
		sql = "insert into tbl_user(name,address,email,mobileno,dob,password,adhar,status,image,imagename,area) values(?,?,?,?,?,?,?,?,?,?,?)";

		con = DBConnection.getConnection();

		try {
			ps = con.prepareStatement(sql);

			ps.setString(1, userbean.getName());
			ps.setString(2, userbean.getAddress());
			ps.setString(3, userbean.getEmail());
			ps.setString(4, userbean.getMobileno());
			ps.setString(5, userbean.getDob());
			ps.setString(6, userbean.getPassword());
			ps.setString(7, userbean.getAdhar());
			ps.setString(8, userbean.getStatus());
			ps.setBlob(9, userbean.getImage());
			ps.setString(10, userbean.getImage_name());
			ps.setString(11, userbean.getArea());
			
			int index = ps.executeUpdate();

			if (index > 0) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return flag;
	}

	public boolean castVote(CandidateBean bean) {
		sql = "insert into tbl_vote(party,area,cand,date,count,useremail) values(?,?,?,?,?,?)";

		con = DBConnection.getConnection();

		try {
			ps = con.prepareStatement(sql);

			ps.setString(1, bean.getCandidateparty());
			ps.setString(2, bean.getArea());
			ps.setString(3, bean.getName());
			ps.setString(4, bean.getDob());
			ps.setInt(5, bean.getCount());;
			ps.setString(6, bean.getEmail());;
			int index = ps.executeUpdate();

			if (index > 0) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return flag;
	}
	public String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		System.out.println("contentDisp:" + contentDisp);
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}
	
	public ArrayList<PartyBean> getCategory() throws SQLException {

		String sql = "select * from tbl_party";
		Connection con = DBConnection.getConnection();

		ArrayList<PartyBean> titleList = new ArrayList<PartyBean>();

		Statement st = con.createStatement();

		rs = st.executeQuery(sql);

		while (rs.next()) {
			PartyBean bean = new PartyBean();

			bean.setId(rs.getInt(1));
			bean.setPartyname(rs.getString(2));

			titleList.add(bean);
		}

		return titleList;
	}

	public ArrayList<CandidateBean> getCategory1() throws SQLException {

		String sql = "select * from tbl_candidatedetails";
		Connection con = DBConnection.getConnection();

		ArrayList<CandidateBean> titleList = new ArrayList<CandidateBean>();

		Statement st = con.createStatement();

		rs = st.executeQuery(sql);

		while (rs.next()) {
			CandidateBean bean = new CandidateBean();

			bean.setId(rs.getInt(1));
			bean.setName(rs.getString(2));

			titleList.add(bean);
		}

		return titleList;
	}
	public boolean update1(String email, String generatedotp) {
		// TODO Auto-generated method stub
		boolean flag=false;
		String sql = "INSERT INTO userlogin(email,otp) VALUES(?,?)";
		int update = 0;
		Connection connection = (Connection) DBConnection.getConnection();
		try {
			PreparedStatement pstmt=(PreparedStatement) connection.prepareStatement(sql);
			
			pstmt.setString(1, email);
			
			pstmt.setString(2, generatedotp);
		
			
			int index=pstmt.executeUpdate();
			if(index>0)
			{
				flag=true;
			}
		}
		catch (Exception ex) {
			System.out.println (ex);
		}
	
		return flag;
	}
	
	public String selectotp(String sql1) {
		// TODO Auto-generated method stub
		String genotp = "";
		Connection connection = (Connection) DBConnection.getConnection();
		try {
			Statement st=connection.createStatement();
			
		
			ResultSet rs = st.executeQuery(sql1);
			while(rs.next()){
				genotp = rs.getString(1);
			System.out.println(genotp);
			}
		}
		catch (Exception ex) {
			System.out.println (ex);
		}
		
		return genotp;
	}


	public String selectotp1(String sql2) {
		// TODO Auto-generated method stub
		String genotp = "";
		Connection connection = (Connection) DBConnection.getConnection();
		try {
			Statement st=connection.createStatement();
			
			ResultSet rs = st.executeQuery(sql2);
			while(rs.next()){
				genotp = rs.getString(1);
			System.out.println(genotp);
			}
		}
		catch (Exception ex) {
			System.out.println (ex);
		}
	
		return genotp;
	}
	
	public boolean alreadyUser(String email) throws SQLException {
		String sql = "select * from tbl_user where email=?";
		con = DBConnection.getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, email);
		rs = ps.executeQuery();
		if (rs.next()) {
			flag = true;
		}
		return flag;
	}

	public UserBean CheckUser(String email, String password) {

		UserBean bean = new UserBean();
		sql = "select * from tbl_user where email='" + email + "' and password='" + password + "' ";
		try {
			Statement stmt = DBConnection.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {

				bean.setName(rs.getString(2));
				bean.setAddress(rs.getString(3));
				bean.setEmail(rs.getString(4));
				bean.setMobileno(rs.getString(5));
				bean.setDob(rs.getString(6));
				bean.setPassword(rs.getString(7));
				bean.setStatus(rs.getString(9));
				bean.setArea(rs.getString(12));
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return bean;
	}

	public boolean UpdateUserStatus(int userId, String status) {
		if (status.equalsIgnoreCase("Inactive"))
			status = "Active";
		else
			status = "Inactive";

		String sql = "update tbl_user set status=? where id=?";

		try {
			ps = DBConnection.getConnection().prepareStatement(sql);
			ps.setString(1, status);
			ps.setInt(2, userId);

			int index = ps.executeUpdate();
			if (index > 0) {
				flag = true;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return flag;
	}

	public ResultSet SelectUser() {

		ResultSet rs = null;
		String sql = "Select * from tbl_user";
		try {
			PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(sql);
			rs = pstmt.executeQuery();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return rs;
	}
	
	public boolean TruncateTable() {

		boolean ss=false;
		String sql = "truncate table tbl_dataset";
		try {
			PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(sql);
			ss = pstmt.execute();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return ss;
	}

	public boolean DeleteUser(int id) {

		String sql = "delete from tbl_user where id='" + id + "'";
		Connection con = DBConnection.getConnection();
		try {
			ps = con.prepareStatement(sql);
			int index = ps.executeUpdate();
			if (index > 0) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return flag;
	}

	public boolean UpdateUserPassword(String oldpass, String newpass) {

		sql = "update tbl_user set password=? where password='" + oldpass + "'";

		Connection con = DBConnection.getConnection();

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, newpass);

			int index = ps.executeUpdate();

			if (index > 0) {
				flag = true;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return flag;
	}

	public String key()
	{
		
		char[] characterSet = "134".toCharArray();
		int length=3;
		int data=16;
		String filekey=null;
						
			 Random random1 = new SecureRandom();
			    char[] result = new char[length];
			    for (int i = 0; i < result.length; i++) {
			       
			        int randomCharIndex = random1.nextInt(characterSet.length);
			        result[i] = characterSet[randomCharIndex];
			    }
			    filekey=new String(result);
				return filekey;

	}
	
	public String key1()
	{
		
		char[] characterSet = "345".toCharArray();
		int length=2;
		int data=16;
		String filekey=null;
						
			 Random random1 = new SecureRandom();
			    char[] result = new char[length];
			    for (int i = 0; i < result.length; i++) {
			       
			        int randomCharIndex = random1.nextInt(characterSet.length);
			        result[i] = characterSet[randomCharIndex];
			    }
			    filekey=new String(result);
				return filekey;

	}
	
	public UserBean SelectUserPassword(String email) {

		UserBean bean = new UserBean();

		sql = "select * from tbl_user where email='" + email + "'";

		Connection con = DBConnection.getConnection();

		try {
			ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				bean.setName(rs.getString(2));
				bean.setAddress(rs.getString(3));
				bean.setEmail(rs.getString(4));
				bean.setMobileno(rs.getString(5));
				bean.setDob(rs.getString(6));
				bean.setPassword(rs.getString(7));
				bean.setStatus(rs.getString(9));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return bean;

	}

	public void updateCount(String party,String area,String cand)
	{
		 String sql="select count from tbl_vote where area='"+area+"' and cand='"+cand+"' ";
	        
	        Connection con1=DBConnection.getConnection();
	        
	        PreparedStatement ps;
			try {
				ps = con1.prepareStatement(sql);
				 ResultSet rs1=ps.executeQuery();
			        while(rs1.next())
			        {
			        	int count=rs1.getInt(1);
			        	count=count+1;
			        	
			        	 String sql1="update tbl_vote set count='"+count+"' where cand='"+cand+"'";
			        	    ps=con1.prepareStatement(sql1);
			        	    int index=ps.executeUpdate();
			        	    if(index>0)
			        	    {
			        	    	System.out.println("enter count");
			        	    }
			        }
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
	}
	
	public boolean alreadyVoter(String email) throws SQLException {
		String sql = "select * from tbl_vote where email=?";
		con = DBConnection.getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, email);
		rs = ps.executeQuery();
		if (rs.next()) {
			flag = true;
		}
		return flag;
	}
}
