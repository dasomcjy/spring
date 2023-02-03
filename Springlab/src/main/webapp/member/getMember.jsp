<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import = "com.springlab.member.MemberDTO" %>

<%
	//session 에 저장된 값을 가지고 온다. 
		//board : DB의 select 한 레코드를 저장하고 있는 dto 
	MemberDTO member = (MemberDTO) session.getAttribute("member");
%>    
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 상세 페이지</title>
</head>
<body>
<center>
	<h1> 정보 상세 페이지 </h1>
	<a href = "login.jsp"> Log-in</a>
	<hr>
	<!-- 폼의 value에 출력 -->
	
	<form action = "updateMember.do" method = "post">
		<!-- updateBoard.do 페이지로 넘길때 seq -->
	    <!-- 출력구문에는 나타나지 않고 변수값 넘길때 사용 -->		
		<input type = "hidden" name = "idx" value = "<%= member.getIdx() %>" >
		
		<table border = "1" cellspacing ="0" cellpadding="0">
			<tr> 
				<td bgcolor="orange" width="80"> id </td>
				<td> <input type = "text" name="id" value ="<%= member.getId() %>"> </td>
			</tr>
			<tr> 
				<td bgcolor="orange" > 비밀번호 </td>
				<td> <input type = "text" name="pass" value ="<%= member.getId() %>"> </td>
			</tr>
			<tr> 
				<td bgcolor="orange" > 이름 </td>
				<td> <input type = "text" name="name" value ="<%= member.getName() %>"> </td>
			</tr>
			<tr> 
				<td bgcolor="orange"> 이메일 </td>
				<td> <input type = "text" name="email" value ="<%= member.getEmail() %>"> </td>
			</tr>
			<tr> 
				<td bgcolor="orange"> 나이 </td>
				<td> <input type = "text" name="age" value ="<%= member.getAge() %>"> </td>
			</tr>
			<tr> 
				<td bgcolor="orange"> 무게 </td>
				<td> <input type = "text" name="weight" value ="<%= member.getWeight() %>"> </td>
			</tr>
			<tr> 
				<td bgcolor="orange"> 날짜 </td>
				<td> <%= member.getRegdate() %>  </td>
			</tr>
			<tr> 
				<td bgcolor="orange"> 조회수 </td>
				<td> <%= member.getCnt()%> </td>
			</tr>
			<tr> 
				<td colspan = "2" align="right"> <input type = "submit" value="글 수정"> </td>
			</tr>
		</table>
	</form>
	<p>
	<a href = "insertMember.jsp"> 글등록 </a>  &nbsp; &nbsp; &nbsp;
	<a href = "deleteMember.do?idx=<%= member.getIdx() %>"> 글삭제 </a>  &nbsp; &nbsp; &nbsp;
	<a href = "getMemberList.do">글 목록</a>
	
	
	
	
	
	
</center>
</body>
</html>