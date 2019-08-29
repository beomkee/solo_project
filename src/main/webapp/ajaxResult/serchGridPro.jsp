<%@page import="java.util.List"%>
<%@page import="model.LoginUser"%>
<%@page import="dao.UserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String serchVar = request.getParameter("serchVar");
	String target = request.getParameter("target");
	String colum = request.getParameter("colum");
	UserDao db = UserDao.getInstance();
	List<LoginUser> ecList = db.serchEmpCusList(colum, serchVar, target);
%>
<%= ecList %>