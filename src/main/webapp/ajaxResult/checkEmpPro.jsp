<%@page import="model.LoginUser"%>
<%@page import="dao.UserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String checkId = request.getParameter("checkId");	
	UserDao manager = UserDao.getInstance();
	LoginUser info = manager.getUserInfo(checkId);
%>
<%= info.toString() %>
