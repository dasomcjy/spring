<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert Board (새글 등록)</title>
</head>
<body>

<center>
	<h1> 정보 등록 </h1>
	<form action  = "insertMember.do" method = "post">
		<table border = "1" cellspacing="0" cellpadding="0">
			<tr> <td bgcolor="orange" width = "70" align = "center"> id </td> 
				<td> <input type = "text" name = "id"> </td> 
			</tr>
			<tr> <td bgcolor="orange" align = "center"> 비밀번호 </td> 
				<td> <input type = "text" name = "pass"> </td> 
			</tr>
			<tr> <td bgcolor="orange" align = "center"> 이름 </td> 
				<td> <input type = "text" name = "name"> </td> 
			</tr>
			<tr> <td bgcolor="orange" align = "center"> 이메일 </td> 
				<td> <input type = "text" name = "email"> </td> 
			</tr>
			<tr> <td bgcolor="orange" align = "center"> 나이 </td> 
				<td> <input type = "text" name = "age"> </td> 
			</tr>
			<tr> <td bgcolor="orange" align = "center"> 몸무게 </td> 
				<td> <input type = "text" name = "weight"> </td> 
			</tr>

			<tr> <td colspan = "2" align = "right"> <input type="submit" value="새정보등록"></td>  
			</tr>
		</table>
	</form>
	<p/> <hr>
		<a href = "getMemberList.do"> 글 목록 바로가기 </a>

</center>

</body>
</html>