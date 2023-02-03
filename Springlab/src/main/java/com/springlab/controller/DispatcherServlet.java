package com.springlab.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.springlab.member.MemberDAO;
import com.springlab.member.MemberDTO;


//@WebServlet("/DispatcherServlet")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public DispatcherServlet() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		process(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		process(request, response);
	}
	
	
	public void process (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet, doPost 메소드에서 받는 내용을 처리
		
		String url = request.getRequestURL().toString();  //URL 정보를 게더링 .toString()
		System.out.println(url);
			
		String uri = request.getRequestURI();			//boardweb/getBoardList.do
			System.out.println("uri : " + uri);
			
		String path = uri.substring(uri.lastIndexOf("/"));	//getBoardList.do
			System.out.println("path : " + path);
			
		
		//2. 클라이언트 요청 정보에 따라서 적절하게 분기 처리함.
		
		if (path.equals("/login.do")) {
			String id = request.getParameter("id");
			String pass = request.getParameter("pass");
			
			MemberDTO dto = new MemberDTO();
			MemberDAO dao = new MemberDAO();
			
			dto.setId(id);
			dto.setPass(pass);
			
			boolean passs = dao.loginMB(dto);
			
			if(passs == true) {
				response.sendRedirect("getMemberList.do");
				System.out.println("아이디와 패스워드 일치");
	
			}else {
				response.sendRedirect("login.jsp");
				System.out.println("아이디와 패스워드 불일치");
			}
			
			

		}else if (path.equals("/insertMember.do")) {
			
			String id = request.getParameter("id");
			String pass = request.getParameter("pass");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			int age = Integer.parseInt(request.getParameter("age"));
			int weight = Integer.parseInt(request.getParameter("weight"));
			
//			System.out.println(id);
//			System.out.println(pass);
//			System.out.println(name);
//			System.out.println(email);
//			System.out.println(age);
//			System.out.println(weight);
			
		
			
			MemberDTO dto = new MemberDTO();
			MemberDAO dao = new MemberDAO();
			
			dto.setId(id);
			dto.setPass(pass);
			dto.setName(name);
			dto.setEmail(email);
			dto.setAge(age);
			dto.setWeight(weight);
			
			dao.insertMB(dto);
			
			response.sendRedirect("getMemberList.do");
			
		}else if (path.equals("/getMemberList.do")) {
					
			System.out.println("게시판 정보 출력");
					
			MemberDTO dto = new MemberDTO();
			MemberDAO dao = new MemberDAO();
					
			List<MemberDTO> MemberList = dao.getMBList(dto);
					
			HttpSession session = request.getSession();
			session.setAttribute("MemberList" , MemberList);
					
			response.sendRedirect("getMemberList.jsp");
					
	
		}else if (path.equals("/getMember.do")) {
			
			System.out.println("정보 상세 내용보기");
			
			String idx = request.getParameter("idx");
			System.out.println("idx 변수 값 : " + idx);
			
			MemberDTO dto = new MemberDTO();
			MemberDAO dao = new MemberDAO();
			
			dto.setIdx(Integer.parseInt(idx));
		
			MemberDTO member = dao.getMB(dto);
			
			HttpSession session = request.getSession();
		
			session.setAttribute("member" , member);
			
			response.sendRedirect("getMember.jsp");
			
		
		}else if (path.equals("/updateMember.do")) {
			
			System.out.println("정보 수정 처리 ");
			
			String id = request.getParameter("id");
			String pass = request.getParameter("pass");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			int age = Integer.parseInt(request.getParameter("age"));
			Double weight = Double.parseDouble(request.getParameter("weight"));
			String idx = request.getParameter("idx");
			
			System.out.println(idx);
			System.out.println(id);
			System.out.println(name);
			
			
			
			MemberDTO dto = new MemberDTO();
			MemberDAO dao = new MemberDAO();
			
			
			dto.setId(id);
			dto.setPass(pass);
			dto.setName(name);
			dto.setEmail(email);
			dto.setAge(age);
			dto.setWeight(weight);
			dto.setIdx(Integer.parseInt(idx));
		
			dao.updateMB(dto);
			
			response.sendRedirect("getMemberList.do");
				
			
		}else if (path.equals("/deleteMember.do")) {
			
			System.out.println("정보 삭제 처리 ");
			
			String idx = request.getParameter("idx");
	
			
			MemberDTO dto = new MemberDTO();
			MemberDAO dao = new MemberDAO();
			
			dto.setIdx(Integer.parseInt(idx));
	
			dao.deleteMB(dto);
			
			response.sendRedirect("getMemberList.do");
					

			}else if (path.equals("/logout.do")) {
			
			System.out.println("사용자 로그 아웃 처리");
		}
		
			
	}

}