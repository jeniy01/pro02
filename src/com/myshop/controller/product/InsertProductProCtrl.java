package com.myshop.controller.product;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myshop.dto.Product;
import com.myshop.model.ProductDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/InsertProductPro.do")
public class InsertProductProCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String savePath = "/product/img";	//업로드할 디렉토리
		int uploadFileSizeLimit = 10 * 1024 * 1024;	//업로드할 파일 크기 제한
		String encType = "UTF-8";		//멀티파트 데이터의 인코딩 설정
		ServletContext context = getServletContext();	//현재 서블릿(프로젝트)의 위치 저장
		String uploadFilePath = context.getRealPath(savePath);  //서버 상에 실제 업로드되는 디렉토리 지정
		System.out.println("지정된 업로드 디렉토리 : "+savePath);
		System.out.println("서버 상의 실제 업로드되는 디렉토리 : "+uploadFilePath);
		
		int n = 0;
		String[] fileName = new String[3];
		String[] oriFileName = new String[3];
		ProductDAO dao = new ProductDAO();
		Product pro = new Product();
		
		//MultipartRequest의 옵션 내용
		//1. request : 요청 받은 객체
		//2. uploadFilePath : 서버상의 실제 디렉토리
		//3. uploadFileSizeLimit : 최대 업로드 파일 크기
		//4. encType : 인코딩 방법
		//5. new DefaultFileRenamePolicy() : 동일한 이름이 존재하면 새로운 이름이 부여되며, 생략하면, 덮어쓰기 됨
		try {
			MultipartRequest multi = new MultipartRequest(request, uploadFilePath, 
					uploadFileSizeLimit, encType, new DefaultFileRenamePolicy());	
			
			Enumeration<?> files = multi.getFileNames();
			while(files.hasMoreElements()) {
				String file = (String) files.nextElement();
				fileName[n] = multi.getFilesystemName(file);
				//중복된 파일을 업로드할 경우 파일명이 바뀐다.
				oriFileName[n] = multi.getOriginalFileName(file);
				n++;
			}
			
			if (fileName[2] == null) { // 파일이 업로드 되지 않았을때
				System.out.print("파일1 업로드 실패");
			} else {
				pro.setPic1("img/"+fileName[2]);
			}
			
			if (fileName[1] == null) { // 파일이 업로드 되지 않았을때
				System.out.print("파일2 업로드 실패");
			} else {
				pro.setPic2("img/"+fileName[1]);
			}

			if (fileName[0] == null) { // 파일이 업로드 되지 않았을때
				System.out.print("파일3 업로드 실패");
			} else {
				pro.setPic3("img/"+fileName[0]);
			}
			
			pro.setGcode(multi.getParameter("gcode"));
			pro.setGname(multi.getParameter("gname"));
			pro.setGram(multi.getParameter("gram"));
			pro.setMung(multi.getParameter("mung"));
			pro.setCate(multi.getParameter("cate"));
			pro.setAmount(Integer.parseInt(multi.getParameter("amount")));
			pro.setPrice(Integer.parseInt(multi.getParameter("price")));
			
		} catch (Exception e) {
			System.out.print("예외 발생 : " + e);
		}
		
		int cnt = dao.insertProduct(pro);	
		if(cnt==0){ //상품 등록 실패
			String msg = "상품을 등록하지 못했습니다.";
			request.setAttribute("msg", msg);
			
			//디스패치로 view를 생성하여 noticeList.jsp로 요청 받은 notiList를 포워드
			RequestDispatcher view = request.getRequestDispatcher("InsertProduct.do");
			view.forward(request, response);
		} else { //상품등록 성공시 목록으로 가기
			response.sendRedirect("ProductList.do");
		}
	}
}