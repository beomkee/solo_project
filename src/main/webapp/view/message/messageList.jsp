<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>받은 쪽지함</title>
<div class="dashboard-wrapper">
	<div class="container-fluid dashboard-content">
		<div class="row">
			<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
				<div class="card">
					<h5 class="card-header">받은 쪽지함</h5>
					<div class="card-body">
						<table class="table">
							<thead>
								<tr>
									<th scope="col" style="text-align: center">To.</th>
									<th scope="col" width="60%" style="text-align: center">내용</th>
									<th scope="col" style="text-align: center">받은날짜</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="ms" items="${mss}">
									<tr>
										<td style="text-align: center">${ms.to}</td>
										<td>
											<c:if test="${ms.readcount == 0}">
												<span class="badge badge-secondary">unRead</span>
											</c:if>
											<a href="<%=request.getContextPath() %>/profile/messageDetial.do?num=${ms.num}">${ms.title}</a>
										</td>
										<td style="text-align: center">${ms.reg_date}</td>
									</tr>
								</c:forEach>
								<tr>
									<td colspan="8">
										<a href="<%=request.getContextPath()%>/profile/sendMessage.do" class="btn btn btn-outline-warning btn-sm float-right">쪽지 보내기</a>
									</td>
								</tr>
							</tbody>
						</table>
						<%-- <div style="text-align: center; margin-top: 10px">
							<c:if test="${startPage > bottomLine }">
								<a href="<%=request.getContextPath() %>/notice/nList.do?pageNum=${startPage - bottomLine}">[이전]</a>
							</c:if>
							<c:forEach var="i" begin="${startPage }" end="${endPage}">
								<a href="<%=request.getContextPath() %>/notice/nList.do?pageNum=${i}">[${i}] </a>
							</c:forEach>
							<c:if test="${endPage < pageCount}">
								<a href="<%=request.getContextPath() %>/notice/nList.do?pageNum=${startPage + bottomLine}">[다음]</a>
							</c:if>
						</div>
						<c:if test="${division eq 'M' }" >
							<div style="text-align: right; margin-top: 10px">
								<a href="<%=request.getContextPath() %>/notice/nWrite.do">[글쓰기]</a>
							</div>
						</c:if> --%>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>