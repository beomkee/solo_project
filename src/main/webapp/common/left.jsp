<%@page import="service.LeftMenuService"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
	$(document).ready(function() {
		$.ajax({
			url		:"${pageContext.request.contextPath}/leftMenu",
			method	: "POST",
			data	: {},
			success	: function(data){
				alert("asd");
				console.log(data);
				$.each(data, function(i, m){
					var menu = '';
					if(m.depth == 1) {
							menu += '<li class="nav-item">'+
										'<a class="nav-link" href="#" data-toggle="collapse" aria-expanded="false" data-target="#submenu-'+m.num+'" aria-controls="submenu-'+m.num+'">'+
									'<i class="fas fa-fw fa-inbox"></i>'+m.text+'</a>'+
									'<div id="submenu-'+m.num+'" class="collapse submenu" style="">'+
									'<ul class="nav flex-column">';	
						$.each(data, function(j,submenu){
							if(submenu.parent_num == m.num){
								menu += 
									'<li class="nav-item">'+
										'<a class="nav-link" href="${pageContext.request.contextPath}/'+submenu.url+'">'+submenu.text+'</a>'+
									'</li>'+
									'</ul>'+
								'</div>'+
							'</li>';
							}
						}) 
					}
					$("#menus").append(menu);
				})
			}
		});
	});
</script>
<div class="nav-left-sidebar sidebar-dark">
	<div class="menu-list">
		<nav class="navbar navbar-expand-lg navbar-light">
			<a class="d-xl-none d-lg-none" href="#">Dashboard</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav flex-column" id="menus">
					<li class="nav-divider">Menu</li>
					<li class="nav-item">
						<a class="nav-link" href="<%=request.getContextPath()%>/main?${division}">
							<i class="fa fa-fw fa-user-circle"></i>
							Dashboard
						</a>
					</li>
				</ul>
			</div>
		</nav>
	</div>
</div>
