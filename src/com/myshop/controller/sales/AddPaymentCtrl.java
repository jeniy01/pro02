package com.myshop.controller.sales;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myshop.dto.Buy;
import com.myshop.dto.Payment;
import com.myshop.model.SalesDAO;

@WebServlet("/AddPayment.do")
public class AddPaymentCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		int amount = Integer.parseInt(request.getParameter("amount"));
		String id = request.getParameter("id");
		String gcode = request.getParameter("gcode");
		String bnum = request.getParameter("bnum");
		String cate = request.getParameter("cate");
		
		Payment pay = new Payment();
		Buy buy = new Buy();
		SalesDAO dao = new SalesDAO();
		
		buy.setOcode(dao.getOcodeGenerator());
		buy.setId(id);
		buy.setGcode(gcode);
		buy.setAmount(amount);
		buy.setPrice(Integer.parseInt(request.getParameter("payamount")));
		buy.setTel(request.getParameter("tel"));
		buy.setAddr(request.getParameter("address1")+" "+request.getParameter("address2"));
		buy.setOstate("배송전");
		buy.setDname("");
		buy.setDcode("");

		pay.setPnum(dao.getPnumGenerator());
		pay.setId(request.getParameter("id"));
		pay.setGcode(buy.getOcode());
		pay.setPtype(request.getParameter("ptype"));
		pay.setPtnum(request.getParameter("ptnum"));
		pay.setPprice(Integer.parseInt(request.getParameter("payamount")));
		
		int cnt = dao.addSales(buy, pay, bnum);
		if(cnt>=3){
			System.out.println("트랜잭션 처리 성공");
			response.sendRedirect("ProductList.do?cate="+cate);
		} else {
			System.out.println("트랜잭션 처리 실패");
			response.sendRedirect("AddSales.do?bnum="+bnum+"&gcode="+gcode+"&amount="+amount+"&id="+id);
		}
	}
}
