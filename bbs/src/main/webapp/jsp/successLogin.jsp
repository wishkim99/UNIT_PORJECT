<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>

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
	
	<h2 style="text-align:center; background-color: #eeeeee" >유머 게시판</h2>
	<table>
	<th style="background-color: #eeeeee; text-align: center;">번호</th>
						<th style="background-color: #eeeeee; text-align: center;">제목</th>
						<th style="background-color: #eeeeee; text-align: center;">작성자</th>
						<th style="background-color: #eeeeee; text-align: center;">작성일</th>
						<th style="background-color: #eeeeee; text-align: center;">조회수</th>
	    <tbody>
    <%
    Class.forName("com.mysql.jdbc.Driver");
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
     
    try{
        String jdbcDriver = "jdbc:mysql://localhost:3306/humor?characterEncoding=UTF-8&serverTimezone=UTC";
        String dbUser = "root";
        String dbPwd = "1234";
         
        conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPwd);
         
        pstmt = conn.prepareStatement("select * from writing");
         
        rs = pstmt.executeQuery();
         
        while(rs.next()){

    %>
        <tr>
            <td><%= rs.getString("id") %></td>
             <td><%= rs.getString("title") %></td>
            <td><%= rs.getString("writer") %></td>
            <td><%= rs.getString("writedate") %></td>
            <td><%= rs.getString("cnt") %></td>
        </tr>
    <%
            }
        }catch(SQLException se){
            se.printStackTrace();
        }finally{
            if(rs != null) rs.close();
            if(pstmt != null) pstmt.close();
            if(conn != null) conn.close();
        }
    %>
    </tbody>
			
	</table>

	<hr>
	<a href="/bbs/jspsrc/login.jsp">글 작정</a>
	<a href="/bbs/jspsrc/join.jsp">내 글 수정하기</a>
	<a href="/bbs/jspsrc/join.jsp">내 글 삭제하기</a>
	<!-- <input type="submit" value="등록"> <input type="reset" value="재작성"> -->
</body>
</html>