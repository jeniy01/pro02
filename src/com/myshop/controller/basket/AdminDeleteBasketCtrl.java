package com.myshop.controller.basket;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myshop.dto.Basket;
import com.myshop.model.BasketDAO;

@WebServlet("/AdminDeleteBasket.do")
public class AdminDeleteBasketCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bnum = request.getParameter("bnum");
		
		BasketDAO dao = new BasketDAO();
		Basket bas = dao.getBasketDetail(bnum);
		String id = bas.getId();
		int cnt = dao.deleteBasket(bnum);
		if(cnt==1){
			response.sendRedirect("AdminBasketList.do");
		} else {
			response.sendRedirect("AdminBasketList.do");
		}
	}

}
