<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/concept-master/assets/vendor/bootstrap/css/bootstrap.min.css">
    <link href="<%= request.getContextPath() %>/concept-master/assets/vendor/fonts/circular-std/style.css" rel="stylesheet">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/concept-master/assets/libs/css/style.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/concept-master/assets/vendor/fonts/fontawesome/css/fontawesome-all.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/concept-master/assets/vendor/charts/chartist-bundle/chartist.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/concept-master/assets/vendor/charts/morris-bundle/morris.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/concept-master/assets/vendor/fonts/material-design-iconic-font/css/materialdesignicons.min.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/concept-master/assets/vendor/charts/c3charts/c3.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/concept-master/assets/vendor/fonts/flag-icon-css/flag-icon.min.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/concept-master/assets/vendor/select2/css/select2.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/concept-master/assets/vendor/summernote/css/summernote-bs4.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/concept-master/assets/vendor/fonts/material-design-iconic-font/css/materialdesignicons.min.css">
    <script src="<%= request.getContextPath() %>/concept-master/jquery-3.3.1.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Guriddo_jqGrid_JS_5.3.2/css/ui.jqgrid.css"/>
	<script type="text/javascript" src="<%=request.getContextPath()%>/Guriddo_jqGrid_JS_5.3.2/js/jquery.jqGrid.min.js" ></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery-ui-1.12.1.custom/jquery-ui.css" >
	<script type="text/javascript" src="<%=request.getContextPath()%>/jquery-ui-1.12.1.custom/jquery-ui.js" ></script>
	<script src="<%=request.getContextPath()%>/Guriddo_jqGrid_JS_5.3.2/js/i18n/grid.locale-kr.js"></script>
<%-- <script src="<%=request.getContextPath()%>/concept-master/jqgrid/commonGrid.js"></script>--%>
<script src="<%=request.getContextPath()%>/concept-master/customJs/gridTableJS.js"></script>
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
			<div style="width: 847px; margin-left: auto; margin-right: auto">
				<div class="card">
					<h5 class="card-header">생산정보 검색</h5>
					<div class="card-body">
						<table class="table table-bordered">
							<tbody>
								<tr>
									<td class="bg-light text-dark">생산번호</td>
									<td colspan="5">
										<input type="text" name="mf_num" id="mf_num" class="form-control-small">
									</td>
								</tr>
								<tr>
									<td class="bg-light text-dark">공장번호</td>
									<td>
										<select class="form-control form-control-sm" name="f_num" id="f_num">
                                              <option></option>
                                              <option value="sw_12345">sw_12345</option>
                                              <option value="yi_45678">yi_45678</option>
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
                                              <option value="12345">12345</option>
                                        </select>
									</td>
								</tr>
								<tr>
									<td class="bg-light text-dark">상품번호</td>
									<td>
										<input type="text" name="p_num" id="p_num" class="form-control-small">
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
					<div class="card-body">
						<input type="button" value="그리드실행" onclick="grid();">
						<table id="gridTable" class="table table-bordered">
						</table>
						<div id="gridPaging"></div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div style="width: 847px; margin-left: auto; margin-right: auto">
				<div class="card">
					<h5 class="card-header">생산 분석</h5>
					<div class="card-body">
						<canvas id="chartjs_bar"></canvas>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		var colNames = "mf_num,f_num,pl_num,e_id,p_num,mf_count,mf_date";
		var tableName = "manufactures";
		var options = "0,2,1,0,2,1,0";
		$(document).ready(function() {
			
		});
		
		function grid() {
			$.ajax({                          
		        type: "POST",
		        url: "<%=request.getContextPath()%>/drawGrid.do",
				data : {"colNames" 	:	colNames,
						"tableName" : 	tableName,
						"options"	:	options},
				datatype : "json",
				success : function(result) {
					<%-- console.log(result);
					console.log("<%= request.getParameter("data") %>"); --%>
					<% String data = (String)request.getAttribute("data"); %>
					console.log(<%= data %>);
					console.log(${data});
					console.log(${param});
					/* var json = JSON.parse(result);
					list = json;
					$("#gridTable").clearGridData();
					makeTable('gridTable', json); */
				}
			});
		}
	</script>
	