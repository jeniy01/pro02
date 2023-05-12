package com.myshop.controller.user;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myshop.dto.User;
import com.myshop.model.UserDAO;

@WebServlet("/UserDetail.do")
public class UserDetailCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		UserDAO dao = new UserDAO();
		ArrayList<User> userList = new ArrayList<User>();
		try {
			userList = dao.getUserList();
		} catch (InvalidKeyException | NoSuchPaddingException
				| NoSuchAlgorithmException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | BadPaddingException
				| IllegalBlockSizeException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("userList", userList);
		
		//디스패치로 view를 생성하여 userList.jsp로 요청 받은 userList를 포워드
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/admin/userDetail.jsp");
		view.forward(request, response);
		/*String id = request.getParameter("id");
		UserDAO dao = new UserDAO();
		User user = new User();
		String msg = "";
		try {
			user = dao.myInfo(id);
		} catch (InvalidKeyException | NoSuchPaddingException
				| NoSuchAlgorithmException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | BadPaddingException
				| IllegalBlockSizeException e) {
			e.printStackTrace();
		}
		msg = "회원 정보를 로딩하였습니다.";
		request.setAttribute("user", user);
		request.setAttribute("msg", msg);

		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/admin/userDetail.jsp");
		view.forward(request, response);*/
		/*HttpSession ses = request.getSession();
		String id = (String) ses.getAttribute("sid");
		
		UserDAO dao = new UserDAO();
		User user = new User();
		String msg = "";
		try {
			user = dao.myInfo(id);
		} catch (InvalidKeyException | NoSuchPaddingException
				| NoSuchAlgorithmException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | BadPaddingException
				| IllegalBlockSizeException e) {
			e.printStackTrace();
		}
		if(user==null){
			ses.invalidate();
			msg = "현재 로그인이 되어 있지 않습니다. 로그인 하시기 바랍니다.";
			response.sendRedirect("UserLogin.do?msg="+msg);	
		} else {
			msg = "현재 회원 정보를 로딩하였습니다.";
			request.setAttribute("user", user);
			request.setAttribute("msg", msg);

			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/admin/userDetail.jsp");
			view.forward(request, response);
		}*/
		/*request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		UserDAO dao = new UserDAO();
		ArrayList<User> userList2 = new ArrayList<User>();
		try {
			userList2 = dao.getUserList();
		} catch (InvalidKeyException | NoSuchPaddingException
				| NoSuchAlgorithmException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | BadPaddingException
				| IllegalBlockSizeException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("userList2", userList2);
		
		//디스패치로 view를 생성하여 userList.jsp로 요청 받은 userList를 포워드
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/admin/userDetail.jsp");
		view.forward(request, response);*/
	}
}