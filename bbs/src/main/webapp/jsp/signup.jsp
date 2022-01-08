<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="java.io.PrintWriter" %>

출처: https://group11.tistory.com/9 [Jo's]
<%@ page
	import="model.vo.WritingVO, model.vo.MemberVO, java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<title>JSP 게시판 웹 사이트</title>
</head>
<body>
	<form method="post" action="/bbs/board">
		<input type="hidden" name="action" value="signup">
		<h3 style="text-align: center;">회원가입 화면</h3>
		<hr>
		<input type="text" placeholder="아이디" name="id" maxlength="20">
		<br> <input type="password" placeholder="비밀번호" name="password"
			maxlength="20"><br> <input type="text" placeholder="이름"
			name="name" maxlength="20"><br> <input type="text"
			placeholder="핸드폰번호" name="phone" maxlength="20"><br>
				<input type="submit" value="회원가입">
</form>


</body>
</html>