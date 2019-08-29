<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<body>
	<div class="dashboard-main-wrapper">
		<div class="dashboard-header">
			<nav class="navbar navbar-expand-lg bg-white fixed-top">
				<a class="navbar-brand" href="#">
					<img class="MainLogo" style="height: 37px; margin-left: 40px;" src="<%=request.getContextPath()%>/assets/dow_mainLogo.png" />
				</a>
				<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
					aria-expanded="false" aria-label="Toggle navigation"
				>
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse " id="navbarSupportedContent">
							<!--========================= 채팅 폼 =============================-->
							<span class="badge badge-info" style="margin-left: 90%">
								<a onclick="window.open('<%=request.getContextPath()%>/common/chattingCSS.jsp?name=${user.name }','','fullscreen=no,location=no,status=no,resizable=no,scrollbars=no,left=30,top=600,width=415,height=310,toolbar=no,menubar=no')">
									<p style="cursor:pointer">채팅참여하기</p>
								</a>
							</span>
					<ul class="navbar-nav ml-auto navbar-right-top">
						<li class="nav-item dropdown nav-user">
							<a class="nav-link nav-user-img" href="#" id="navbarDropdownMenuLink2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								<img src="<%= request.getContextPath() %>/assets/img/user/${sessionScope.user.id }.jpg" alt=""
									class="user-avatar-md rounded-circle"
								>
							</a>
							<div class="dropdown-menu dropdown-menu-right nav-user-dropdown" aria-labelledby="navbarDropdownMenuLink2">
								<div class="nav-user-info">
									<h5 class="mb-0 text-white nav-user-name">${sessionScope.user.name }님로그인환영합니다</h5>
								</div>
								<a class="dropdown-item" href="<%=request.getContextPath()%>/profile/e_profile.do">
									<i class="fas fa-user mr-2"></i>개인정보
								</a>
								<a class="dropdown-item" href="<%=request.getContextPath()%>/">
									<i class="fas fa-power-off mr-2"></i>Logout
								</a>
							</div>
						</li>
					</ul>
				</div>
			</nav>
		</div>