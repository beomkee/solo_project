<%@page import="dao.UserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String deleteID = request.getParameter("deleteId");
	String target = request.getParameter("target");
	UserDao db = UserDao.getInstance();
	db.deleteEmpCus(deleteID, target);
%>