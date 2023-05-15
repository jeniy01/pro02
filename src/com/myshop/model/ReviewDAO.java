package com.myshop.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.myshop.dto.Product;
import com.myshop.dto.Review;

public class ReviewDAO {
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	//해당 상품의 이용후기 불러오기
	public Product getProduct(String ocode){
		Product pro = new Product();
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.REVIEW_PRODUCT);
			pstmt.setString(1, ocode);
			rs = pstmt.executeQuery();
			if(rs.next()){
				pro.setGcode(rs.getString("gcode"));
				pro.setGname(rs.getString("gname"));
				pro.setGram(rs.getString("gram"));
				pro.setPrice(rs.getInt("price"));
				pro.setMung(rs.getString("mung"));
				pro.setAmount(rs.getInt("amount"));
				pro.setPic1(rs.getString("pic1"));
				pro.setPic2(rs.getString("pic2"));
				pro.setPic3(rs.getString("pic3"));
				pro.setCate(rs.getString("cate"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(rs, pstmt, con);
		}
		return pro;
	}
	
	//결제 번호 생성
	public String getRcodeGenerator(){
		String rcode = "";
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.RCODE_GENERATOR);
			rs = pstmt.executeQuery();
			if(rs.next()){
				rcode = rs.getString("rcode");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(rs, pstmt, con);
		}
		
		int tmp = Integer.parseInt(rcode) + 1;
		rcode = tmp + "";
		return rcode;
	}
	
	//리뷰 등록하기
	public int addReview(Review rev){
		int cnt = 0;
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.ADD_REVIEW);
			pstmt.setString(1, rev.getRcode());
			pstmt.setString(2, rev.getId());
			pstmt.setString(3, rev.getOcode());
			pstmt.setString(4, rev.getRcontent());
			pstmt.setInt(5, rev.getRpoint());
			cnt = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(pstmt, con);
		}
		SalesDAO dao = new SalesDAO();
		cnt = cnt + dao.okSales(rev.getOcode());
		return cnt;
	}
	
	
	//해당 상품의 리뷰 불러오기
	public ArrayList<Review> getPcodeByReview(String pcode){
		ArrayList<Review> rList = new ArrayList<Review>();
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.PCODEBY_REVIEW);
			pstmt.setString(1, pcode);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Review rev = new Review();
				rev.setRcode(rs.getString("rcode"));
				rev.setId(rs.getString("id"));
				rev.setOcode(rs.getString("ocode"));
				rev.setResdate(rs.getString("resdate"));
				rev.setRcontent(rs.getString("rcontent"));
				rev.setRpoint(rs.getInt("rpoint"));
				rList.add(rev);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(rs, pstmt, con);
		}
		return rList;
	}
	
	//리뷰 한 건 불러오기
	public Review getRcodeByReview(String rcode){
		Review rev = new Review();
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.RCODEBY_REVIEW);
			pstmt.setString(1, rcode);
			rs = pstmt.executeQuery();
			if(rs.next()){
				rev.setRcode(rs.getString("rcode"));
				rev.setId(rs.getString("id"));
				rev.setOcode(rs.getString("ocode"));
				rev.setResdate(rs.getString("resdate"));
				rev.setRcontent(rs.getString("rcontent"));
				rev.setRpoint(rs.getInt("rpoint"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(rs, pstmt, con);
		}
		return rev;
	}
	
	//리뷰 수정하기
	public int updateReview(Review rev){
		int cnt = 0;
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.UPDATE_REVIEW);
			pstmt.setString(1, rev.getRcontent());
			pstmt.setInt(2, rev.getRpoint());
			pstmt.setString(3, rev.getId());
			pstmt.setString(4, rev.getRcode());
			
			cnt = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(pstmt, con);
		}
		return cnt;
	}
	
	public ArrayList<Review> getAllReview(){
		ArrayList<Review> rList = new ArrayList<Review>();
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.ALL_REVIEW);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Review rev = new Review();
				rev.setRcode(rs.getString("rcode"));
				rev.setId(rs.getString("id"));
				rev.setOcode(rs.getString("ocode"));
				rev.setResdate(rs.getString("resdate"));
				rev.setRcontent(rs.getString("rcontent"));
				rev.setRpoint(rs.getInt("rpoint"));
				rList.add(rev);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(rs, pstmt, con);
		}
		return rList;
	}
}
