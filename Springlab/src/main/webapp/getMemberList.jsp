<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.List" %>
<%@ page import = "com.springlab.member.MemberDTO" %>
    
<!-- 세션에 저장된 boardList를 끄집어 낸다.  -->
<%
	//session 은 내장객체이므로 별도로 import없이 사용가능
	
	List<MemberDTO> MemberList = (List) session.getAttribute("MemberList");

%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<center>
	<h1> 정보 출력 페이지 </h1>
	<hr> 
	
	<h3> 환영합니다. <a href = "logout.do">Log-Out</a></h3>
	
		<!--  서버에서 담은 List에 저장된 DTO를 끄집어 내서 출력 -->
		<table border = "1" cellspacing = "0" cellpadding = "0" width = "700">
			<tr>
				<th bgcolor = "orange" width = "100"> 번호 </th>
				<th bgcolor = "orange" width = "100"> 아이디 </th>
				<th bgcolor = "orange" width = "100"> 비밀번호 </th>
				<th bgcolor = "orange" width = "100"> 이름 </th>
				<th bgcolor = "orange" width = "100"> 이메일 </th>
				<th bgcolor = "orange" width = "100"> 나이 </th>
				<th bgcolor = "orange" width = "100"> 무게 </th>
			</tr>
		
			<!-- tr을 for 문으로 루프를 돌리면서 List의 DTO 값을 끄집어 내서 출력 -->
		
			<%
				for (MemberDTO dto : MemberList) {
			%>
		
			<tr>
				<td align = "center"> <%= dto.getIdx() %> </td>
				<td> 
					<a href ="getMember.do?idx=<%= dto.getIdx() %>">
				<%= dto.getId() %> 
					</a>
				</td>
				<td align = "center"> <%= dto.getPass() %></td>
				<td align = "center"> <%= dto.getName() %></td>
				<td align = "center"> <%= dto.getEmail() %></td>
				<td align = "center"> <%= dto.getAge() %></td>
				<td align = "center"> <%= dto.getWeight() %></td>
			</tr>
			
			
			<%
				}
			%>	
		</table>
		
		<p />
		<a href = "insertMember.jsp"> 회원등록 </a>
		
	</from>
	
	
</center>

</body>
</html></html>