package com.myshop.controller.sales;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myshop.dto.Buy;
import com.myshop.model.SalesDAO;

@WebServlet("/SurveyPro.do")
public class SurveyProCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ocode = request.getParameter("ocode");
		Buy buy = new Buy();
		buy.setOcode(request.getParameter("ocode"));
		buy.setDname(request.getParameter("dname"));
		buy.setDcode(request.getParameter("dcode"));
		buy.setOstate(request.getParameter("ostate"));
		
		SalesDAO dao = new SalesDAO();
		int cnt = dao.surveyUpdate(buy);
		if(cnt>0){
			response.sendRedirect("Survey.do");
		} else {
			response.sendRedirect("SurveyLoad.do?ocode="+ocode);
		}
	}
}
