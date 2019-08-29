<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>공장 정보</title>
<div class="dashboard-wrapper">
	<div class="container-fluid dashboard-content">
		<div class="row">
			<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
				<div class="page-header">
					<h2 class="pageheader-title">공장 관리</h2>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
				<div class="card">
					<h5 class="card-header">나의 공장 생산 내역</h5>
					<div class="card-body">
						<table class="table table-bordered">
							<thead>
								<tr>
									<th scope="col">#</th>
									<th scope="col">공장번호</th>
									<th scope="col">라인번호</th>
									<th scope="col">담당직원</th>
									<th scope="col">상품번호</th>
									<th scope="col">생산수량</th>
									<th scope="col">생산일자</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="f_mf" items="${f_mfs}">
									<tr>
										<th style="text-align: center">${f_mf.mf_num }</th>
										<td style="text-align: center">${f_mf.f_num}</td>
										<td style="text-align: center">${f_mf.pl_num }</td>
										<td style="text-align: center">${f_mf.e_id }</td>
										<td style="text-align: center">${f_mf.p_num }</td>
										<td style="text-align: center">${f_mf.mf_count }</td>
										<td style="text-align: center">${f_mf.mf_date }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<div style="text-align: center; margin-top: 10px">
							<c:if test="${startPage > bottomLine }">
								<a href="<%=request.getContextPath() %>/com/fac.do?pageNum=${startPage - bottomLine}">[이전]</a>
							</c:if>
							<c:forEach var="i" begin="${startPage }" end="${endPage}">
								<a href="<%=request.getContextPath() %>/com/fac.do?pageNum=${i}">[${i}] </a>
							</c:forEach>
							<c:if test="${endPage < pageCount}">
								<a href="<%=request.getContextPath() %>/com/fac.do?pageNum=${startPage + bottomLine}">[다음]</a>
							</c:if>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
				<div class="card-body">
					<div class="row">
						<div class="col-xl-6 col-lg-6 col-md-6 col-sm-12 col-12">
							<div class="card">
								<h5 class="card-header">분기 별 공장 생산 분석</h5>
								<div class="card-body">
									<canvas id="chartjs_line"></canvas>
								</div>
							</div>
						</div>
						<div class="col-xl-6 col-lg-6 col-md-6 col-sm-12 col-12">
							<div class="card">
								<h5 class="card-header">라인별 분기 생산 분석</h5>
								<div class="card-body">
									<canvas id="chartjs_bar"></canvas>
								</div>
							</div>
						</div>
						<div class="col-xl-6 col-lg-6 col-md-6 col-sm-12 col-12">
							<div class="card">
								<h5 class="card-header">지난달 생산량 상위 직원</h5>
								<div class="card-body">
									<canvas id=chartjs_doughnut></canvas>
								</div>
							</div>
						</div>
						<div class="col-xl-6 col-lg-6 col-md-6 col-sm-12 col-12">
							<div class="card">
								<h5 class="card-header">총 상품 생산량 상위 직원</h5>
								<div class="card-body">
									<canvas id="chartjs_polar"></canvas>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	

<script>
//Chart 속성=====================
var label1 = "1분기";
var label2 = "2분기";
var getDataUrl = "facData.do";
var fac = "${f_num}";
$(document).ready(function() {
	getChartData();
});
</script>
<script src="<%=request.getContextPath()%>/concept-master/assets/vendor/charts/charts-bundle/Chart.bundle.js"></script>
<script src="<%=request.getContextPath()%>/concept-master/customJs/manufacture.js"></script>
<script src="<%=request.getContextPath()%>/concept-master/customJs/customChart.js"></script>



	
	