<%@page import="JSON.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="<%=request.getContextPath()%>/concept-master/customJs/commonGrid.js"></script>
<title>생산 현황</title>
<div class="dashboard-wrapper">
	<div class="container-fluid dashboard-content">
		<div class="row">
			<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
				<div class="page-header">
					<h2 class="pageheader-title">생산 현황</h2>
				</div>
			</div>
		</div>
		<div class="row">
			<div style="width: 847px; margin-left: auto; margin-right: auto" id="main">
				<div class="card">
					<h5 class="card-header">생산정보 검색</h5>
					<div class="card-body">
						<table class="table table-bordered">
							<tbody>
								<tr>
									<td class="bg-light text-dark">생산번호</td>
									<td colspan="5">
										<input type="text" name="mf_num" id="mf_num" class="form-control-small" value=" --- " readonly="readonly">
									</td>
								</tr>
								<tr>
									<td class="bg-light text-dark">공장번호</td>
									<td>
										<select class="form-control form-control-sm" name="f_num" id="f_num">
											<option></option>
											<c:forEach var="fac" items="${facs}">
												<option value="${fac}">${fac}</option>
											</c:forEach>
										</select>
									</td>
									<td class="bg-light text-dark">생산라인</td>
									<td>
										<select class="form-control form-control-sm" name="pl_num" id="pl_num">
											<option></option>
											<option value="p_1">p_1</option>
											<option value="p_2">p_2</option>
											<option value="p_3">p_3</option>
										</select>
									</td>
									<td class="bg-light text-dark">담당직원</td>
									<td>
										<select class="form-control form-control-sm" name="e_id" id="e_id">
											<option></option>
											<c:forEach var="emp" items="${emps}">
												<option value="${emp}">${emp}</option>
											</c:forEach>
										</select>
									</td>
								</tr>
								<tr>
									<td class="bg-light text-dark">상품번호</td>
									<td>
										<select class="form-control form-control-sm" name="p_num" id="p_num">
											<option></option>
											<c:forEach var="product" items="${products}">
												<option value="${product}">${product}</option>
											</c:forEach>
										</select>
									</td>
									<td class="bg-light text-dark">생산수량</td>
									<td>
										<input type="number" min="1000" step="500" name="mf_count" id="mf_count" class="form-control-small">
									</td>
									<td class="bg-light text-dark">생산일자</td>
									<td>
										<input type="date" name="mf_date" id="mf_date" class="form-control-small">
									</td>
								</tr>
							</tbody>
						</table>
						<div style="text-align: right; margin-top: 10px">
							<input type="button" onclick="search()" class="btn btn-outline-primary btn-xs" value="검색">
							<input type="button" onclick="insertUpdate()" class="btn btn-outline-success btn-xs" value="등록/수정">
							<input type="button" onclick="reset()" class="btn btn-outline-light btn-xs" value="초기화">
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div style="width: 847px; margin-left: auto; margin-right: auto">
				<div class="card">
					<h5 class="card-header">생산 LIST</h5>
					<div class="card-body" id="gridbody">
						<table id="gridTable" class="table table-bordered">
						</table>
						<div id="gridPaging"></div>
					</div>
				</div>
			</div>
		</div>
		<div class="card">
		<div class="card-header" id="headingEight">
			<h5 class="mb-0">
				<button onclick="getChartData()" class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseEight" aria-expanded="false" aria-controls="collapseEight">
					<span class="fas fa-angle-down mr-3"></span>
					생산 데이터 분석
				</button>
			</h5>
		</div>
		<div id="collapseEight" class="collapse" aria-labelledby="headingEight" data-parent="#accordion3">
			<div class="card-body">
				<div class="row">
					<div class="col-xl-6 col-lg-6 col-md-6 col-sm-12 col-12">
						<div class="card">
							<h5 class="card-header">공장 별 최근 6개월 생산 현황</h5>
							<div class="card-body">
								<canvas id="chartjs_line"></canvas>
							</div>
						</div>
					</div>
					<div class="col-xl-6 col-lg-6 col-md-6 col-sm-12 col-12">
						<div class="card">
							<h5 class="card-header">생산라인별 총 생산 현황</h5>
							<div class="card-body">
								<canvas id="chartjs_bar"></canvas>
							</div>
						</div>
					</div>
					<div class="col-xl-6 col-lg-6 col-md-6 col-sm-12 col-12">
						<div class="card">
							<h5 class="card-header">지난달 상품 생산량 상위6</h5>
							<div class="card-body">
								<canvas id=chartjs_doughnut></canvas>
							</div>
						</div>
					</div>
					<div class="col-xl-6 col-lg-6 col-md-6 col-sm-12 col-12">
						<div class="card">
							<h5 class="card-header">총 상품 생산량 상위6</h5>
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
<!--======================= JqGrid 스크립트 =======================  -->
<script>
	//그리드 속성=====================
	var colNames = ${colNames};
	var colModel = ${colModel};
	var data = ${data};
	var fac = "";
</script>
<!--======================= BarChart 스크립트 =======================  -->
<script src="<%=request.getContextPath()%>/concept-master/assets/vendor/charts/charts-bundle/Chart.bundle.js"></script>
<script src="<%=request.getContextPath()%>/concept-master/customJs/manufacture.js"></script>
<script src="<%=request.getContextPath()%>/concept-master/customJs/customChart.js"></script>