package com.myshop.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.myshop.dto.Buy;
import com.myshop.dto.Payment;
import com.myshop.vo.SalesVO;

public class SalesDAO {
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	//판매+결제 등록 처리
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
	
	//주문(판매)코드 생성
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
	
	//결제 번호 생성
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
	
	//관리자의 판매+결제 전체 목록 로딩
	public ArrayList<SalesVO> getSalesList(){
		ArrayList<SalesVO> salesList = new ArrayList<SalesVO>();
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.SALES_LIST);
			rs = pstmt.executeQuery();
			while(rs.next()){
				SalesVO sale = new SalesVO();
				sale.setOcode(rs.getString("ocode"));
				sale.setId(rs.getString("id"));
				sale.setGcode(rs.getString("gcode"));
				sale.setAmount(rs.getInt("amount"));
				sale.setPrice(rs.getInt("price"));
				sale.setOdate(rs.getString("odate"));
				sale.setOstate(rs.getString("ostate"));
				sale.setTel(rs.getString("tel"));
				sale.setDname(rs.getString("dname"));
				sale.setAddr(rs.getString("addr"));
				sale.setDcode(rs.getString("dcode"));
				sale.setPnum(rs.getString("pnum"));
				sale.setPtype(rs.getString("ptype"));
				sale.setPtnum(rs.getString("ptnum"));
				salesList.add(sale);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(rs, pstmt, con);
		}
		return salesList;
	}
	
	//관리자의 특정 판매 데이터 로딩 
	public SalesVO getSales(String ocode){
		SalesVO sale = new SalesVO();
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.SALES_INFO);
			pstmt.setString(1, ocode);
			rs = pstmt.executeQuery();
			if(rs.next()){
				sale.setOcode(rs.getString("ocode"));
				sale.setId(rs.getString("id"));
				sale.setGcode(rs.getString("gcode"));
				sale.setAmount(rs.getInt("amount"));
				sale.setPrice(rs.getInt("price"));
				sale.setOdate(rs.getString("odate"));
				sale.setOstate(rs.getString("ostate"));
				sale.setTel(rs.getString("tel"));
				sale.setDname(rs.getString("dname"));
				sale.setAddr(rs.getString("addr"));
				sale.setDcode(rs.getString("dcode"));
				sale.setPnum(rs.getString("pnum"));
				sale.setPtype(rs.getString("ptype"));
				sale.setPtnum(rs.getString("ptnum"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(rs, pstmt, con);
		}
		return sale;
	}
	
	//특정 사용자의 구매 목록 로딩
	public ArrayList<SalesVO> getByIdSalesList(String id){
		ArrayList<SalesVO> salesList = new ArrayList<SalesVO>();
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.BYID_SALES_LIST);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()){
				SalesVO sale = new SalesVO();
				sale.setOcode(rs.getString("ocode"));
				sale.setId(rs.getString("id"));
				sale.setGcode(rs.getString("gcode"));
				sale.setAmount(rs.getInt("amount"));
				sale.setPrice(rs.getInt("price"));
				sale.setOdate(rs.getString("odate"));
				sale.setOstate(rs.getString("ostate"));
				sale.setTel(rs.getString("tel"));
				sale.setDname(rs.getString("dname"));
				sale.setAddr(rs.getString("addr"));
				sale.setDcode(rs.getString("dcode"));
				sale.setPnum(rs.getString("pnum"));
				sale.setPtype(rs.getString("ptype"));
				sale.setPtnum(rs.getString("ptnum"));
				salesList.add(sale);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(rs, pstmt, con);
		}
		return salesList;
	}
	
	//특정 사용자의 특정 판매 데이터 로딩
	public SalesVO getByIdSales(String id, String ocode){
		SalesVO sale = new SalesVO();
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.BYID_SALES_LIST);
			pstmt.setString(1, id);
			pstmt.setString(2, ocode);
			rs = pstmt.executeQuery();
			while(rs.next()){
				sale.setOcode(rs.getString("ocode"));
				sale.setId(rs.getString("id"));
				sale.setGcode(rs.getString("gcode"));
				sale.setAmount(rs.getInt("amount"));
				sale.setPrice(rs.getInt("price"));
				sale.setOdate(rs.getString("odate"));
				sale.setOstate(rs.getString("ostate"));
				sale.setTel(rs.getString("tel"));
				sale.setDname(rs.getString("dname"));
				sale.setAddr(rs.getString("addr"));
				sale.setDcode(rs.getString("dcode"));
				sale.setPnum(rs.getString("pnum"));
				sale.setPtype(rs.getString("ptype"));
				sale.setPtnum(rs.getString("ptnum"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(rs, pstmt, con);
		}
		return sale;
	}
	
	//판매 정보 목록만 로딩
	public ArrayList<Buy> getByIdBuyList(String id){
		ArrayList<Buy> buyList = new ArrayList<Buy>();
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.BYID_BUY_LIST);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Buy buy = new Buy();
				buy.setOcode(rs.getString("ocode"));
				buy.setId(rs.getString("id"));
				buy.setGcode(rs.getString("gcode"));
				buy.setAmount(rs.getInt("amount"));
				buy.setPrice(rs.getInt("price"));
				buy.setOdate(rs.getString("odate"));
				buy.setOstate(rs.getString("ostate"));
				buy.setTel(rs.getString("tel"));
				buy.setDname(rs.getString("dname"));
				buy.setAddr(rs.getString("addr"));
				buy.setDcode(rs.getString("dcode"));
				buyList.add(buy);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(rs, pstmt, con);
		}
		return buyList;
	}
	
	//모든 결제 정보 목록만 로딩
	public ArrayList<Payment> getByPayList(){
		ArrayList<Payment> payList = new ArrayList<Payment>();
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.PAY_LIST);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Payment pay = new Payment();
				pay.setPnum(rs.getString("pnum"));
				pay.setId(rs.getString("id"));
				pay.setGcode(rs.getString("ocode"));
				pay.setPtype(rs.getString("ptype"));
				pay.setPtnum(rs.getString("ptnum"));
				pay.setPprice(rs.getInt("pprice"));
				pay.setPdate(rs.getString("pdate"));
				payList.add(pay);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(rs, pstmt, con);
		}
		return payList;
	}
	
	
	//특정 사용자의 특정 결제 정보
	public Payment getByIdPay(String ocode){
		Payment pay = new Payment();
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.BYOCODE_PAY);
			pstmt.setString(1, ocode);
			rs = pstmt.executeQuery();
			if(rs.next()){
				pay.setPnum(rs.getString("pnum"));
				pay.setId(rs.getString("id"));
				pay.setGcode(rs.getString("ocode"));
				pay.setPtype(rs.getString("ptype"));
				pay.setPtnum(rs.getString("ptnum"));
				pay.setPprice(rs.getInt("pprice"));
				pay.setPdate(rs.getString("pdate"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(rs, pstmt, con);
		}
		return pay;
	}
	
	public int surveyUpdate(Buy buy){
		int cnt = 0;
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.UPDATE_SURVEY);
			pstmt.setString(1, buy.getDname());
			pstmt.setString(2, buy.getDcode());
			pstmt.setString(3, buy.getOstate());
			pstmt.setString(4, buy.getOcode());
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

	public int cancleSales(String ocode, String gcode, int amount) {
		int cnt = 0;
		try {
			con = Oracle11.getConnection();
			con.setAutoCommit(false);	//트랜잭션 처리시 오토커밋을 비활성화
			pstmt = con.prepareStatement(Oracle11.DELETE_BUY);
			pstmt.setString(1, ocode);
			cnt = pstmt.executeUpdate();

			pstmt = con.prepareStatement(Oracle11.DELETE_PAYMENT);
			pstmt.setString(1, ocode);
			cnt = cnt + pstmt.executeUpdate();
			
			pstmt = con.prepareStatement(Oracle11.RETURN_PRODUCT);
			pstmt.setInt(1, amount);
			pstmt.setString(2, gcode);
			cnt = cnt + pstmt.executeUpdate();
			
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

	public int returnSales(String ocode) {
		int cnt = 0;
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.RETURN_SALES);
			pstmt.setString(1, ocode);
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

	public int okSales(String ocode) {
		int cnt = 0;
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.OK_SALES);
			pstmt.setString(1, ocode);
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
}
