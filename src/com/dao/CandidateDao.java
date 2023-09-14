package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.Part;

import com.bean.CandidateBean;
import com.bean.PartyBean;
import com.connection.DBConnection;

public class CandidateDao {

	String sql;
	PreparedStatement ps;
	ResultSet rs;

	Connection con;
	boolean flag = false;

	public boolean InsertUserData(CandidateBean userbean) {
		sql = "insert into tbl_cand(name,address,email,mobileno,dob,password,adhar,status,image,imagename) values(?,?,?,?,?,?,?,?,?,?)";

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
	
	public boolean AddCandidateDetails(CandidateBean bean) {
		sql = "insert into tbl_candidatedetails(name,party,area,idno) values(?,?,?,?)";

		con = DBConnection.getConnection();

		try {
			ps = con.prepareStatement(sql);

			ps.setString(1, bean.getName());
			ps.setString(2, bean.getCandidateparty());
			ps.setString(3, bean.getArea());
			ps.setString(4, bean.getIdno());
			
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

	public boolean alreadyUser(String email) throws SQLException {
		String sql = "select * from tbl_cand where email=?";
		con = DBConnection.getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, email);
		rs = ps.executeQuery();
		if (rs.next()) {
			flag = true;
		}
		return flag;
	}

	public CandidateBean Check(String email, String password) {

		CandidateBean bean = new CandidateBean();
		sql = "select * from tbl_cand where email='" + email + "' and password='" + password + "' ";
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
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return bean;
	}

	public boolean UpdateCandidateStatus(int userId, String status) {
		if (status.equalsIgnoreCase("Inactive"))
			status = "Active";
		else
			status = "Inactive";

		String sql = "update tbl_cand set status=? where id=?";

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
		String sql = "Select * from tbl_cand";
		try {
			PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(sql);
			rs = pstmt.executeQuery();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return rs;
	}
	


	public boolean Deletecandidate(int id) {

		String sql = "delete from tbl_cand where id='" + id + "'";
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

		sql = "update tbl_cand set password=? where password='" + oldpass + "'";

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

}
