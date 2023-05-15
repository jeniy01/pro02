package com.myshop.controller.sales;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myshop.dto.Product;
import com.myshop.model.ReviewDAO;
import com.myshop.model.SalesDAO;
import com.myshop.vo.SalesVO;

@WebServlet("/AddResultUserReview.do")
public class AddResultUserReviewCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ocode = request.getParameter("ocode");
		
		ReviewDAO dao = new ReviewDAO();
		Product pro = dao.getProduct(ocode);
		
		SalesDAO sdao = new SalesDAO();
		SalesVO sale = sdao.getSales(ocode);
		
		String msg = "이용후기를 작성합니다.";
		request.setAttribute("msg", msg);
		request.setAttribute("pro", pro);
		request.setAttribute("sale", sale);
		
		//디스패치로 view를 생성하여 noticeList.jsp로 요청 받은 notiList를 포워드
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/review/addReview.jsp");
		view.forward(request, response);
	}
}
