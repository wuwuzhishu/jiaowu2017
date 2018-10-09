<%@page import="model.Teacher"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	//String name = (String)session.getAttribute("abc");
	Teacher teacher = (Teacher)session.getAttribute("teacher");
%>
管理员登录成功，欢迎你，<%= teacher.getTecName() %>，生日：<%= teacher.getTecBirth() %>
</body>
</html>