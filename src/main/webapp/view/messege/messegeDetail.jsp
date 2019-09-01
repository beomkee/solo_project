<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/concept-master/smartediter/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<title>받은 쪽지함</title>
<div class="dashboard-wrapper">
	<div class="container-fluid dashboard-content">
		<div class="row">
			<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
				<div class="card">
					<h5 class="card-header">쪽지 세부</h5>
					<div class="card-body">
						<form action="notice/write.do" method="post">
							<table class="table table-bordered">
								<tbody>
									<tr>
										<td class="bg-light text-dark" colspan="2">${ms.from}님이 보낸 쪽지</td>
									</tr>
									<tr>
										<td class="bg-light text-dark" width="15%" style="text-align: center">${ms.reg_date}</td>
										<td class="bg-light text-dark" style="text-align: center">${ms.title}</td>
									<tr>
									</tr>
										<td colspan="2" height="200px" >${ms.content}</td>
								</tbody>
							</table>
							<div style="float: left; margin-top: 10px">
								<c:if test="${ms.num != minNum }">
									<input type="button" class="btn btn-outline-dark"
										onclick="location.href='<%= request.getContextPath() %>/profile/messageDetial.do?num=${pre}'" value="이전">
								</c:if>
								<c:if test="${ms.num != maxNum }">
									<input type="button" class="btn btn-outline-dark"
										onclick="location.href='<%= request.getContextPath() %>/profile/messageDetial.do?num=${next}" value="다음">
								</c:if>
							</div>
							<div style="text-align: right; margin-top: 10px">
								<input type="button" onclick="location.href='<%= request.getContextPath() %>/profile/message.do'" class="btn btn-outline-brand" value="목록보기">
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
