package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestBookDAO;
import com.javaex.dao.GuestBookDAOImpl;
import com.javaex.vo.GuestBookDTO;

@WebServlet(description="게스트 북 서블릿", urlPatterns = "/guestbook2/*")
public class GuestbookServlet2 extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public GuestbookServlet2() { super(); };
	
	protected void doGet(HttpServletRequest request, 
						HttpServletResponse response) 
				throws ServletException, IOException {
		System.out.println("Guestbook2.doGet() 호출");
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		System.out.println(uri);
		int lastSlash = uri.lastIndexOf("/");
		String actionName = uri.substring(lastSlash);
		System.out.println(actionName);
		GuestBookDAO dao = new GuestBookDAOImpl();

		if(actionName.equals("/guestbook2")) {
	        System.out.println("actionName => " + actionName);
	        
	        List<GuestBookDTO> lists = dao.getList();
	        
	        request.setAttribute("lists", lists);
	        
	        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/guestbook2/index2.jsp");
	        rd.forward(request, response);
		} else if(actionName.equals("/add")) {
	        System.out.println("actionName => " + actionName);
	       
	        GuestBookDTO dto = new GuestBookDTO();

	        dto.setName(request.getParameter("name"));
	        dto.setPassword(request.getParameter("password"));
	        dto.setContent(request.getParameter("content"));
	        
	        dao.insert(dto);
	        
	        response.sendRedirect("/MustHaveJSP/guestbook2");
		} else if(actionName.equals("/delete")) {
	        System.out.println("actionName => " + actionName);
	        System.out.println("여기");
	        
	        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/guestbook2/deleteform.jsp");
	        rd.forward(request, response);
		} else if(actionName.equals("/deleteGuestbook")) {
		     
	        int no = Integer.parseInt(request.getParameter("no"));
	        String password = request.getParameter("password");
	        int result = dao.delete(no, password);
	        if(result == 1) {
	        	//response.sendRedirect("/MustHaveJSP/guestbook2");
	        	List<GuestBookDTO> lists = dao.getList();
	 	        request.setAttribute("lists", lists);
	        	RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/guestbook2/index2.jsp");
		        rd.forward(request, response);
	        } else {
	        	RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/guestbook2/deny.jsp");
		        rd.forward(request, response);
	        }
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
