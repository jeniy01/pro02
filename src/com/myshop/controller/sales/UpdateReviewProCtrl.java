package com.myshop.controller.sales;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myshop.dto.Review;
import com.myshop.model.ReviewDAO;

@WebServlet("/UpdateReviewPro.do")
public class UpdateReviewProCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		ReviewDAO dao = new ReviewDAO();
		Review rev = new Review();
		
		String rcode = request.getParameter("rcode");		
		rev.setRcode(rcode);
		
		String id = request.getParameter("id"); 
		rev.setId(id);
		
		String ocode = request.getParameter("ocode");
		rev.setOcode(ocode);
		rev.setRcontent(request.getParameter("rcontent"));
		if(request.getParameter("new_rpoint")==null) {
			rev.setRpoint(Integer.parseInt(request.getParameter("rpoint")));
		} else {
			rev.setRpoint(Integer.parseInt(request.getParameter("new_rpoint")));	
		}
		
		int cnt = dao.updateReview(rev);
		if(cnt>0){
			response.sendRedirect("MySalesList.do?id="+id);
		} else {
			response.sendRedirect("UpdateReview.do?rcode="+rcode);
		}
	}
}
