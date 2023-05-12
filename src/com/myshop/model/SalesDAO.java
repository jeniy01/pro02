package com.myshop.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.myshop.dto.Buy;
import com.myshop.dto.Payment;

public class SalesDAO {
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public int addSales(Buy buy, Payment pay, String bnum){
		int cnt = 0;
		try {
			con = Oracle11.getConnection();
			con.setAutoCommit(false);	//트랜잭션 처리시 오토커밋을 비활성화
			pstmt = con.prepareStatement(Oracle11.ADD_SALES);
			pstmt.setString(1, buy.getOcode());
			pstmt.setString(2, buy.getId());
			pstmt.setString(3, buy.getGcode());
			pstmt.setInt(4, buy.getAmount());
			pstmt.setInt(5, buy.getPrice());
			pstmt.setString(6, buy.getOstate());
			pstmt.setString(7, buy.getTel());
			pstmt.setString(8, buy.getDname());
			pstmt.setString(9, buy.getAddr());
			pstmt.setString(10, buy.getDcode());
			cnt = pstmt.executeUpdate();

			pstmt = con.prepareStatement(Oracle11.ADD_PAYMENT);
			pstmt.setString(1, pay.getPnum());
			pstmt.setString(2, pay.getId());
			pstmt.setString(3, pay.getGcode());
			pstmt.setString(4, pay.getPtype());
			pstmt.setString(5, pay.getPtnum());
			pstmt.setInt(6, pay.getPprice());
			cnt = cnt + pstmt.executeUpdate();
			
			pstmt = con.prepareStatement(Oracle11.SALES_PRODUCT);
			pstmt.setInt(1, buy.getAmount());
			pstmt.setString(2, buy.getGcode());
			cnt = cnt + pstmt.executeUpdate();
			
			if(bnum!=null){
				pstmt = con.prepareStatement(Oracle11.DELETE_BASKET);
				pstmt.setString(1, bnum);
				cnt = cnt + pstmt.executeUpdate();
			}
			
			con.commit();	//수동 커밋
			con.setAutoCommit(true);	//오토커밋 활성화
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(pstmt, con);
		}
		return cnt;
	}
	
	public String getOcodeGenerator(){
		String ocode = "";
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.OCODE_GENERATOR);
			rs = pstmt.executeQuery();
			if(rs.next()){
				ocode = rs.getString("ocode");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(rs, pstmt, con);
		}
		
		int tmp = Integer.parseInt(ocode) + 1;
		ocode = tmp + "";
		return ocode;
	}
	
	public String getPnumGenerator(){
		String pnum = "";
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.PNUM_GENERATOR);
			rs = pstmt.executeQuery();
			if(rs.next()){
				pnum = rs.getString("pnum");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(rs, pstmt, con);
		}
		
		int tmp = Integer.parseInt(pnum) + 1;
		pnum = tmp + "";
		return pnum;
	}
}
