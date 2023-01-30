package com.spring.controller;

import java.io.IOException;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet("/ActionController")
public class ActionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ActionController() {
        super();
 
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		process(request, response);
	}

	
	private void process (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 클라이언트 요청 정보를 추출해야함. path 변수에 할당.
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/"));
			System.out.println(uri);
			System.out.println(path);
			
		if (path.equals("/login.action")) {
			System.out.println("로그인 액션을 호출했습니다. ");
			
		}else if (path.equals("/logout.action")) {
			System.out.println("로그아웃 액션을 호출했습니다.");
		}
		
		
		
	}
	
}
