<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="model.vo.WritingVO, model.vo.MemberVO, java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 - 글내용</title>
<style type="text/css">
#title {
	height: 16;
	font-family: '돋움';
	font-size: 12;
	text-align: center;
}

table {
	width: 100%;
	height: 200px;
}
</style>
</head>
<body>

	<br>
	<b><font size="6" color="gray">게시글 내용</font></b>
	<br>
	<%
	WritingVO vo = (WritingVO) request.getAttribute("updatevo");
	%>
	<a href="/bbs/board">메인으로 가기</a>
	<form method="post" action="/bbs/board">
	    <input type="hidden" name="action" value="update">
		<input type="hidden" name="id" value="${updatevo.id}"> 
		<b><font size="6" color="gray">게시글 hhh내용</font></b>
		<table width="700" border="3" bordercolor="lightgray" align="center">
			<tr>
				<td id="title">작성자</td>

				<td><%=vo.getWriter()%></td>
			</tr>
			<tr>
				<td id="title">제 목</td>
				<td><input name="title" type="text" size="70" maxlength="100"
					value="${updatevo.title}" /></td>
			</tr>
			<tr>
				<td id="title">내 용</td>
				<td><textarea name="content" cols="72" rows="20">${updatevo.content}</textarea>
				</td>
			</tr>
		</table>
		<input type="submit" value="수정"> <input type="reset"
			value="재작성">
	</form>


</body>
</html>