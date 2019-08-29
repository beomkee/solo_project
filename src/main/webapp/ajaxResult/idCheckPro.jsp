<%@page import="dao.UserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%
	String id = request.getParameter("id");
	UserDao db = UserDao.getInstance();
	boolean check = db.idValidationCheck(id);
%>
<%= check %>