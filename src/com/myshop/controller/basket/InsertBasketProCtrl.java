package com.myshop.controller.basket;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myshop.dto.Basket;
import com.myshop.model.BasketDAO;

@WebServlet("/InsertBasketPro.do")
public class InsertBasketProCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Basket bas = new Basket();
		bas.setGcode(request.getParameter("gcode"));
		bas.setId(request.getParameter("id"));
		bas.setAmount(Integer.parseInt(request.getParameter("amount")));
		
		BasketDAO dao = new BasketDAO();
		dao.insertBasket(bas);
		
		response.sendRedirect("MyBasket.do?id="+request.getParameter("id"));
	}
}
