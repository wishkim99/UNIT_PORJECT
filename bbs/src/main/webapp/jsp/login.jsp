<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width", initial-scale"="1">
<link rel="stylesheet" href="css/bootstrap.css">
<title>유머 갤러리</title>
</head>
<body>


  		<div class="jumbotron" style="padding-top: 20px;">
  			<form method="post" action="/bbs/board">
  			<input type="hidden" name="action" value="login">
  				<h3 style="text-align:center" >로그인 화면</h3>
  				<hr>

  				<div class="form-group">
  					<input type="text" placeholder="아이디" name="id" maxlength="20">
  				</div>
  				<div class="form-group">
  					<input type="password" placeholder="비밀번호" name="password" maxlength="20">
  					<input type="submit" value="로그인">
  				</div>
  			</form>
  		</div>

  		<a href="/bbs/board">메인화면으로</a>
  		<a href="signup.jsp">회원가입</a>
  	</div>

</body>
</html>