<%@page import="model.LoginUser"%>
<%@page import="java.util.List"%>
<%@page import="dao.UserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String target = request.getParameter("target");
	UserDao db = UserDao.getInstance();
	List<LoginUser> ecList = db.getEmpCusList(target);
%>
<%= ecList %>