<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page import="model.vo.WritingVO, java.util.ArrayList"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
div {
   text-align: center;
}

td {
   border-bottom: 1px dotted green;
}

tr:hover {
   background-color: pink;
   font-weight: bold;
}

td:nth-child(3) {
   width: 400px
}
</style>
</head>
<body>

   <h2 style="text-align: center; background-color: #eeeeee">유머 게시판</h2>
   <div id="writing">
      <table id="bList" width="800" border="3" bordercolor="lightgray">
         <tr heigh="30">
            <td>글번호</td>
            <td>작성자</td>
            <td>제목</td>
            <td>작성일</td>
            <td>조회수</td>
         </tr>
         <%
         String state = (String) session.getAttribute("state");
            ArrayList<WritingVO> list = (ArrayList<WritingVO>) request.getAttribute("list");
            if (list != null) {
         %>
         <%
         for (WritingVO vo : list) {
         %>
         <tr>
            <td><%=vo.getId()%></td>
            <td><%=vo.getWriter()%></td>
            <%  if (!state.equals("member")) {%>
            <td><a href="javascript:alert('로그인 하세요! 아이디 없을 시 회원가입 먼저!!');"><%=vo.getTitle()%></a></td>
            <%}else{ %>
            <td><a href='/bbs/board?action=select&id=<%=vo.getId()%>'><%=vo.getTitle()%></a></td>
            <%} %>
            <td><%=vo.getWriteDate()%></td>
            <td><%=vo.getCnt()%></td>
            <br>
         </tr>
         <%
         }

         }
           
         %>
     
      </table>
   </div>
	<%
	String state1 = (String)session.getAttribute("state");
	if(state1.equals("member")){
	%>
		<a href="/bbs/jsp/insertForm.jsp">글작성</a>
	<% 	
	} else {
	%>
	<a href="/bbs/jsp/login.jsp">로그인</a>
	<a href="/bbs/jsp/signup.jsp">회원가입</a>
	
	<%
	}
	%>
	
   <!-- <input type="submit" value="등록"> <input type="reset" value="재작성"> -->
</body>
</html>