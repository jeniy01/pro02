package com.myshop.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Oracle11 {
	static String driver = "oracle.jdbc.driver.OracleDriver";
	static String url = "jdbc:oracle:thin:@localhost:1521:xe";
	static String user = "system";
	static String pass = "1234";
	
	//공지사항 관련 SQL
	final static String NOTICE_SELECT_ALL = "select * from notice order by idx desc";
	final static String NOTICE_SELECT_ONE = "select * from notice where idx=?";
	final static String NOTICE_READCOUNT_UPDATE = "update notice set readcnt=readcnt+1 where idx=?";
	final static String INSERT_NOTICE = "insert into notice values (noti_seq.nextval, ?, ?, ?, ?, default, default)";
	final static String UPDATE_NOTICE = "update notice set title=?, content=?, file1=?, resdate=sysdate where idx=?";
	final static String UPDATE_NOTICE2 = "update notice set title=?, content=?, resdate=sysdate where idx=?";
	final static String DELETE_NOTICE = "delete from notice where idx=?";
	
	//회원 관련 SQL
	final static String USER_SELECT_ALL = "select * from user1 order by rdate desc";
	final static String USER_LOGIN =  "select * from user1 where id=?";
	final static String USER_VISIT_COUNT = "update user1 set visited=visited+1 where id=?";
	final static String USER_POINT_COUNT = "update user1 set point=point+5 where id=?";
	final static String INSERT_USER = "insert into user1(id, pw, name, tel, addr, email) values (?,?,?,?,?,?)";
	final static String UPDATE_USER = "update user1 set pw=?, name=?, tel=?, addr=?, email=? where id=?";
	final static String UPDATE_USER2 = "update user1 set name=?, tel=?, addr=?, email=? where id=?";
	final static String DELETE_USER = "delete from user1 where id=?";
	final static String USER_SELECT_TEL = "select tel from user1 where id=?";
	final static String UPDATE_PW_RESET = "update user1 set pw=? where id=?";
	
	//상품 관련 SQL
	final static String PRODUCT_CATENAME_SELECT = "select * from category where cate=?";
	final static String PRODUCT_SELECT_ALL = "select * from product order by cate desc";
	final static String PRODUCT_SELECT = "select * from product where gcode=?";
	final static String PRODUCT_SOLDOUT_SELECT = "select * from product where amount<=0";
	final static String PRODUCT_CATE_SELECT = "select * from product where cate=?";
	final static String PRODUCT_CATE_SELECT2 = "select * from product where cate like ?||'%'";
	final static String PRODUCT_CATE_SELECT3 = "select * from product where cate like concat(?, '%')";
	final static String FIRST_CATEGORY_SELECT = "select distinct substr(cate,1,2) as ct, categroup from category group by substr(cate,1,2), categroup order by ct";
	final static String SECOND_CATEGORY_SELECT = "select cate, catename from category where cate like ?||'%' order by cate";
	final static String GCODE_GENERATOR = "select gcode from (select * from product where cate=? order by gcode desc) where rownum = 1";
	final static String INSERT_PRODUCT = "insert into product values(?,?,?,?,?,?,?,?,?,?)";
	final static String RECEIPT_PRODUCT = "update product set amount=amount+?, price=? where gcode=?";
	final static String UPDATE_PRODUCT = "update product set amount=amount+?, price=? where gcode=?";
	final static String UPDATE_PRODUCT2 = "update product set gname=?, gram=?, price=?, mung=?, amount=?, pic1=?, pic2=?, pic3=? where gcode=?";
	final static String SALES_PRODUCT = "update product set amount=amount-? where gcode=?";
	final static String DELETE_PRODUCT = "delete from product where gcode=?";
	final static String NOT_SALES_PRODUCT = "select * from product where gcode not in (select gcode from buy)";
	
	//장바구니 관련 SQL
	final static String BASKET_SELECT_ALL = "select * from basket order by bnum desc";
	final static String BASKET_SELECT_ALL2 = "select basket.bnum as bnum, basket.id as id, user1.name as name, basket.gcode as gcode, product.gname as gname, basket.amount as amount, product.price as price from basket, user1, product where basket.id=user1.id and basket.gcode=product.gcode";
	final static String BASKET_SELECT_BYID = "select * from basket where id=?";
	final static String BASKET_SELECT_BYID2 = "select basket.bnum as bnum, basket.id as id, user1.name as name, basket.gcode as gcode, product.gname as gname, basket.amount as amount, product.price as price from basket, user1, product where basket.id=user1.id and basket.gcode=product.gcode and basket.id=?";
	final static String BASKET_SELECT_BYPRODUCT = "select * from basket where gcode=?";
	final static String BASKET_SELECT_BYBNUM = "select * from basket where bnum=?";
	final static String INSERT_BASKET = "insert into basket values (?,?,?,?)";
	final static String DELETE_BASKET = "delete from basket where bnum=?";
	final static String BNUM_GENERATOR = "select bnum from (select bnum from basket order by bnum desc) where rownum = 1";
	
	//상품 판매 관련 SQL
	final static String OCODE_GENERATOR = "select ocode from (select * from buy order by ocode desc) where rownum = 1";
	final static String PNUM_GENERATOR = "select pnum from (select * from payment order by pnum desc) where rownum = 1";
	final static String ADD_SALES = "insert into buy values (?,?,?,?,?,default,?,?,?,?,?)";
	final static String ADD_PAYMENT = "insert into payment values (?,?,?,?,?,?,default)";
	final static String BUY_TRANS_BASKET = "delete from basket where bnum=?";
	final static String BYID_BUY_LIST = "select * from buy where id=? order by ocode desc";
	final static String BYID_BUY = "select * from buy where id=? and ocode=?";
	final static String PAY_LIST = "select * from payment order by pnum desc";
	final static String BYOCODE_PAY = "select * from payment where ocode=? order by pnum desc";
	final static String SALES_LIST = "select buy.ocode as ocode, buy.id as id, buy.gcode as gcode, buy.amount as amount, buy.price as price, buy.odate as odate, buy.ostate as ostate, buy.tel as tel, buy.dname as dname, buy.addr as addr, buy.dcode as dcode,payment.pnum as pnum, payment.ptype as ptype, payment.ptnum as ptnum from buy, payment where payment.ocode=buy.ocode order by buy.ocode";
	final static String SALES_INFO = "select buy.ocode as ocode, buy.id as id, buy.gcode as gcode, buy.amount as amount, buy.price as price, buy.odate as odate, buy.ostate as ostate, buy.tel as tel, buy.dname as dname, buy.addr as addr, buy.dcode as dcode,payment.pnum as pnum, payment.ptype as ptype, payment.ptnum as ptnum from buy, payment where payment.ocode=buy.ocode and buy.ocode=? order by buy.ocode";
	final static String BYID_SALES_LIST = "select buy.ocode as ocode, buy.id as id, buy.gcode as gcode, buy.amount as amount, buy.price as price, buy.odate as odate, buy.ostate as ostate, buy.tel as tel, buy.dname as dname, buy.addr as addr, buy.dcode as dcode,payment.pnum as pnum, payment.ptype as ptype, payment.ptnum as ptnum from buy, payment where payment.ocode=buy.ocode and buy.id=? order by buy.ocode";
	final static String BYID_GET_SALE = "select buy.ocode as ocode, buy.id as id, buy.gcode as gcode, buy.amount as amount, buy.price as price, buy.odate as odate, buy.ostate as ostate, buy.tel as tel, buy.dname as dname, buy.addr as addr, buy.dcode as dcode, payment.pnum as pnum, payment.ptype as ptype, payment.ptnum as ptnum from buy, payment where payment.ocode=buy.ocode and id=? and buy.ocode=?";
	final static String UPDATE_SURVEY = "update buy set dname=?, dcode=?, ostate=? where ocode=?";
	final static String DELETE_BUY = "delete from buy where ocode=?";
	final static String DELETE_PAYMENT = "delete from payment where ocode=?";
	final static String RETURN_PRODUCT = "update product set amount=amount+? where gcode=?";
	final static String RETURN_SALES = "update buy set ostate=? where ocode=?";
	final static String OK_SALES = "update buy set ostate=? where ocode=?";
	
	//리뷰 관련 SQL
	final static String REVIEW_PRODUCT = "select * from product where gcode = (select gcode from buy where ocode=?)";
	final static String RCODE_GENERATOR = "select rcode from (select * from review order by rcode desc) where rownum = 1";
	final static String ADD_REVIEW = "insert into review values (?,?,?,default,?,?)";
	final static String GCODEBY_REVIEW = "select * from review where ocode=(select ocode from buy where gcode=?) order by rcode desc";
	final static String RCODEBY_REVIEW = "select * from review where rcode=?";
	final static String ALL_REVIEW = "select * from review order by rcode desc";
	final static String UPDATE_REVIEW = "update review set resdate=sysdate, rcontent=?, rpoint=? where id=? and rcode=?";
	final static String DELETE_REVIEW = "delete from review where rcode=?";
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, user, pass);
		return con;
	}
	public static void close(PreparedStatement pstmt, Connection con){
		if(pstmt!=null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void close(ResultSet rs, PreparedStatement pstmt, Connection con){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(pstmt!=null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
