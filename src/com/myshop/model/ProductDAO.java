package com.myshop.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.myshop.dto.Product;
import com.myshop.vo.CategoryVO;

public class ProductDAO {
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	//상품 상세 보기
	public Product getProduct(String gcode){
		Product pro = new Product();
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.PRODUCT_SELECT);
			pstmt.setString(1, gcode);
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
	
	//전체 상품 목록 불러오기
	public ArrayList<Product> getProductList(){
		ArrayList<Product> proList = new ArrayList<Product>();
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.PRODUCT_SELECT_ALL);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Product pro = new Product();
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
				proList.add(pro);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(rs, pstmt, con);
		}
		return proList;
	}
	
	//카테고리별 제품목록 로딩
	public ArrayList<Product> getCateProductList(String cate){
		ArrayList<Product> proList = new ArrayList<Product>();
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.PRODUCT_CATE_SELECT);
			pstmt.setString(1, cate);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Product pro = new Product();
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
				proList.add(pro);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(rs, pstmt, con);
		}
		return proList;
	}
	
	//카테고리별 제품목록 로딩
	public ArrayList<Product> getAdminCateProductList(String cate){
		ArrayList<Product> proList = new ArrayList<Product>();
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.PRODUCT_CATE_SELECT2);
			pstmt.setString(1, cate);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Product pro = new Product();
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
				proList.add(pro);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(rs, pstmt, con);
		}
		return proList;
	}
	
	//전체 상품 목록 불러오기
	public ArrayList<Product> getSoldoutProductList(){
		ArrayList<Product> proList = new ArrayList<Product>();
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.PRODUCT_SOLDOUT_SELECT);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Product pro = new Product();
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
				proList.add(pro);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(rs, pstmt, con);
		}
		return proList;
	}
	
	//카테고리 로딩
	public HashMap<String, String> getCategory(String cate){
		HashMap<String, String> cateMap = new HashMap<String, String>();
		String cateGroup = "";
		String cateName = "";
		if(cate.length()==2){
			cate = cate + "01";
		}
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.PRODUCT_CATENAME_SELECT);
			pstmt.setString(1, cate);
			rs = pstmt.executeQuery();
			if(rs.next()){
				cateGroup = "catename";
				cateName = rs.getString("catename");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(rs, pstmt, con);
		}
		cateMap.put(cateGroup, cateName);
		return cateMap;
	}
	
	//대분류 코드 반환
	public ArrayList<CategoryVO> getFirstCategoryList(){
		ArrayList<CategoryVO> cateList = new ArrayList<CategoryVO>();
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.FIRST_CATEGORY_SELECT);
			rs = pstmt.executeQuery();
			while(rs.next()){
				CategoryVO cate = new CategoryVO();
				cate.setCt(rs.getString("ct"));
				cate.setCategroup(rs.getString("categroup"));
				cateList.add(cate);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(rs, pstmt, con);
		}
		return cateList;
	}
	
	//중분류 코드 반환
	public ArrayList<CategoryVO> getSecondCategoryList(String ct){
		ArrayList<CategoryVO> cateList = new ArrayList<CategoryVO>();
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.SECOND_CATEGORY_SELECT);
			pstmt.setString(1, ct);
			rs = pstmt.executeQuery();
			while(rs.next()){
				CategoryVO cate = new CategoryVO();
				cate.setCate(rs.getString("cate"));
				cate.setCatename(rs.getString("catename"));
				cateList.add(cate);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(rs, pstmt, con);
		}
		return cateList;
	}
	
	//상품 코드 발생기
	public String getProductCodeGenerator(String cate){
		String gcode = "";
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.GCODE_GENERATOR);
			pstmt.setString(1, cate);
			rs = pstmt.executeQuery();
			if(rs.next()){
				gcode = rs.getString("gcode").substring(4);
			} else {
				gcode = "0";
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(rs, pstmt, con);
		}
		int tmp = 0;
		if(gcode==null){
			gcode = tmp + "0001";
		} else {
			tmp = Integer.parseInt(gcode) + 1;
			if(tmp>=1000){
				gcode = tmp + "";
			} else if(tmp>=100) {
				gcode = "0" + tmp;
			} else if(tmp>=10) {
				gcode = "00" + tmp;
			} else {
				gcode = "000" + tmp;
			}			
		}
		return gcode;
	}

	//상품 등록 처리
	public int insertProduct(Product pro) {
		int cnt = 0;
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.INSERT_PRODUCT);
			pstmt.setString(1, pro.getGcode());
			pstmt.setString(2, pro.getGname());
			pstmt.setString(3, pro.getGram());
			pstmt.setInt(4, pro.getPrice());
			pstmt.setString(5, pro.getMung());
			pstmt.setInt(6, pro.getAmount());
			pstmt.setString(7, pro.getPic1());
			pstmt.setString(8, pro.getPic2());
			pstmt.setString(9, pro.getPic3());
			pstmt.setString(10, pro.getCate());
			cnt = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) { //오라클 JDBC 클래스가 없거나 경로가 다른 경우 발생
			e.printStackTrace();
		} catch (SQLException e){	//sql 구문이 틀린 경우 발생
			e.printStackTrace();			
		} catch (Exception e){	//알 수 없는 예외인 경우 발생
			e.printStackTrace();
		}
		Oracle11.close(pstmt, con);
		return cnt;
	}

	//입고 처리
	public int receiptProduct(String gcode, int amount, int price) {
		int cnt = 0;
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.RECEIPT_PRODUCT);
			pstmt.setInt(1, amount);
			pstmt.setInt(2, price);
			pstmt.setString(3, gcode);
			cnt = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) { //오라클 JDBC 클래스가 없거나 경로가 다른 경우 발생
			e.printStackTrace();
		} catch (SQLException e){	//sql 구문이 틀린 경우 발생
			e.printStackTrace();			
		} catch (Exception e){	//알 수 없는 예외인 경우 발생
			e.printStackTrace();
		}
		Oracle11.close(pstmt, con);
		return cnt;
	}

	//제품 정보 변경
	public int updateProduct(Product pro) {
		int cnt =0 ;
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.UPDATE_PRODUCT2);
			pstmt.setString(1, pro.getGname());
			pstmt.setString(2, pro.getGram());
			pstmt.setInt(3, pro.getPrice());
			pstmt.setString(4, pro.getMung());
			pstmt.setInt(5, pro.getAmount());
			pstmt.setString(6, pro.getPic1());
			pstmt.setString(7, pro.getPic2());
			pstmt.setString(8, pro.getPic3());
			pstmt.setString(9, pro.getGcode());
			cnt = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) { //오라클 JDBC 클래스가 없거나 경로가 다른 경우 발생
			e.printStackTrace();
		} catch (SQLException e){	//sql 구문이 틀린 경우 발생
			e.printStackTrace();			
		} catch (Exception e){	//알 수 없는 예외인 경우 발생
			e.printStackTrace();
		}
		Oracle11.close(pstmt, con);
		return cnt;
	}

	//제품 삭제
	public int deleteProduct(String gcode) {
		int cnt =0 ;
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.DELETE_PRODUCT);
			pstmt.setString(1, gcode);
			cnt = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) { //오라클 JDBC 클래스가 없거나 경로가 다른 경우 발생
			e.printStackTrace();
		} catch (SQLException e){	//sql 구문이 틀린 경우 발생
			e.printStackTrace();			
		} catch (Exception e){	//알 수 없는 예외인 경우 발생
			e.printStackTrace();
		}
		Oracle11.close(pstmt, con);
		return cnt;
	}
	
	//판매 처리
	public int salesProduct(String gcode, int amount){
		int cnt =0 ;
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.SALES_PRODUCT);
			pstmt.setInt(1, amount);
			pstmt.setString(2, gcode);
			cnt = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) { //오라클 JDBC 클래스가 없거나 경로가 다른 경우 발생
			e.printStackTrace();
		} catch (SQLException e){	//sql 구문이 틀린 경우 발생
			e.printStackTrace();			
		} catch (Exception e){	//알 수 없는 예외인 경우 발생
			e.printStackTrace();
		}
		Oracle11.close(pstmt, con);
		return cnt;
	}
	
	public ArrayList<Product> notSalesList() {
		ArrayList<Product> nList = new ArrayList<Product>();
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.NOT_SALES_PRODUCT);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Product pro = new Product();
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
				nList.add(pro);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(rs, pstmt, con);
		}
		return nList;
	}
}
