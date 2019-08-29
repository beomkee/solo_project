<%@page import="JSON.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="<%=request.getContextPath()%>/concept-master/customJs/commonGrid.js"></script>
<title>판매 현황</title>
<div class="dashboard-wrapper">
	<div class="container-fluid dashboard-content">
		<div class="row">
			<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
				<div class="page-header">
					<h2 class="pageheader-title">판매 현황</h2>
				</div>
			</div>
		</div>
		<div class="row">
			<div style="width: 847px; margin-left: auto; margin-right: auto" id="main">
				<div class="card">
					<h5 class="card-header">판매정보 검색</h5>
					<div class="card-body">
						<table class="table table-bordered">
							<tbody>
								<tr>
									<td class="bg-light text-dark">판매번호</td>
									<td >
										<input type="text" name="s_num" id="s_num" class="form-control-small" value="" readonly="readonly">
									</td>
									<td class="bg-light text-dark">거래처</td>
									<td colspan="3">
										<input type="text" name="c_id" id="c_id" class="form-control-small" required="required">
									</td>
								</tr>
								<tr>
									<td class="bg-light text-dark">생산번호</td>
									<td>
										<select class="form-control form-control-sm" name="mf_num" id="mf_num" required="required">
											<option></option>
											<c:forEach var="mf_num" items="${mf_nums}">
												<option value="${mf_num}">${mf_num}</option>
											</c:forEach>
										</select>
									</td>
									<td class="bg-light text-dark">담당직원</td>
									<td>
										<select class="form-control form-control-sm" name="e_id" id="e_id" required="required">
											<option></option>
											<c:forEach var="e_id" items="${e_ids}">
												<option value="${e_id}">${e_id}</option>
											</c:forEach>
										</select>
									</td>
									<td class="bg-light text-dark">상품번호</td>
									<td>
										<select class="form-control form-control-sm" name="p_num" id="p_num">
											<option></option>
											<c:forEach var="product" items="${products}">
												<option value="${product}">${product}</option>
											</c:forEach>
										</select>
									</td>
								</tr>
								<tr>
									<td class="bg-light text-dark">판매일자</td>
									<td>
										<input type="date" name="s_obtain_date" id="s_obtain_date" class="form-control-small">
									</td>
									<td class="bg-light text-dark">판매수량</td>
									<td>
										<input type="number" min="1000" step="500" name="s_contract_sum" id="s_contract_sum" class="form-control-small">
									</td>
									<td class="bg-light text-dark">완료일</td>
									<td>
										<input type="date" name="s_complete_date" id="s_complete_date" class="form-control-small">
									</td>
								</tr>
							</tbody>
						</table>
						<div style="text-align: right; margin-top: 10px">
							<input type="button" onclick="search()" class="btn btn-outline-primary btn-xs" value="검색">
							<input type="button" onclick="insertUpdate()" class="btn btn-outline-success btn-xs" value="수정">
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
<!--======================= JqGrid 스크립트 =======================  -->
		<script>
			//그리드 속성=====================
			var colNames = ${colNames};
			var colModel = ${colModel};
			var data = ${data};
			
			var fac = "";
			var id = "e_id";
			var columLength = colNames.length - 1;
			var s_num,mf_num,e_id,c_id,p_num,s_obtain_date,s_contract_sum,s_complete_date;

			// 그리드항목 검색폼 전환==========
			function dubleClick(values) {
				$('#s_num').val(values[0]);
				$('#mf_num').val(values[1]);
				$('#e_id').val(values[2]);
				$('#c_id').val(values[3]);
				$('#p_num').val(values[4]);
				$('#s_obtain_date').val(values[5]);
				$('#s_contract_sum').val(values[6]);
				$('#s_complete_date').val(values[7]);
			}

			// 검색폼 초기화===================
			function reset() {
				$('#s_num').val('');
				$('#mf_num').val('');
				$('#e_id').val('');
				$('#c_id').val('');
				$('#p_num').val('');
				$('#s_obtain_date').val('');
				$('#s_contract_sum').val('');
				$('#s_complete_date').val('');
				makeTable('gridTable', list, colNames, colModel);
			}

			// 검색폼 데이터 세팅==============
			function setData() {
				s_num = $('#s_num').val();
				mf_num = $('#mf_num option:selected').val();
				e_id = $('#e_id option:selected').val();
				c_id = $('#tel1 c_id').val();
				p_num = $('#p_num option:selected').val();
				s_obtain_date = $('#s_obtain_date').val();
				s_contract_sum = $('#s_contract_sum').val();
				s_complete_date = $('#s_complete_date').val();
				dataSet = s_num + "," + mf_num + "," + e_id + "," + c_id + ","
						+ p_num + "," + s_obtain_date + "," + s_contract_sum + "," + s_complete_date;
			}

			// 검색폼 입력여부 확인============
			function isFilled() {
				setData();
				if (s_num == null || mf_num == null || e_id == null || c_id == null
						|| p_num == null || s_obtain_date == null
						|| s_contract_sum == null || s_complete_date == null  || s_num == "" || mf_num == ""
						|| e_id == "" || c_id == "" || p_num == ""
						|| s_obtain_date == "" || s_contract_sum == "" || s_complete_date == "") {
					return false;
				} else {
					return true;
				}
			}
		</script>
<!--======================= BarChart 스크립트 =======================  -->
<script src="<%=request.getContextPath()%>/concept-master/assets/vendor/charts/charts-bundle/Chart.bundle.js"></script>
<script src="<%=request.getContextPath()%>/concept-master/customJs/manufacture.js"></script>
<script src="<%=request.getContextPath()%>/concept-master/customJs/customChart.js"></script>