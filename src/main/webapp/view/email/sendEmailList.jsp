<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>이메일</title>
<div class="dashboard-wrapper">
	<div class="container-fluid dashboard-content">
		<div class="row">
			<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
				<div class="card">
					<h5 class="card-header">보낸 이메일</h5>
					<div class="card-body">
						<table class="table">
							<thead>
								<tr>
									<th scope="col">#</th>
									<th scope="col" style="text-align: center">from</th>
									<th scope="col" style="text-align: center">to</th>
									<th scope="col"  width="60%" style="text-align: center">내용</th>
									<th scope="col" style="text-align: center">보낸일자</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="email" items="${elist}">
									<tr>
										<th scope="row">${email.num}</th>
										<td style="text-align: center">${email.writer}</td>
										<td style="text-align: center">${email.to}</td>
										<td><a href="<%=request.getContextPath() %>/email/eContent.do?num=${email.num}">${email.title}</a></td>
										<td style="text-align: center">${email.reg_date}</td>
									</tr>
								</c:forEach>
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