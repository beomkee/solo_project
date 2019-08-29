<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>업무 현황</title>
<div class="dashboard-wrapper">
	<div class="container-fluid dashboard-content">
		<div class="row">
			<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
				<div class="page-header">
					<h2 class="pageheader-title">나의 업무 현황</h2>
				</div>
			</div>
		</div>
		<div class="row">
			<div style="width: 847px; margin-left: auto; margin-right: auto" id="main">
				<div class="card">
					<h5 class="card-header">업무 등록</h5>
					<div class="card-body">
						<form action="<%=request.getContextPath() %>/profile/insertWorks.do" method="post" >
						<input type="hidden" name="pl" value="${pl}">
						<c:if test="${pl eq 'mf'}">
							<table class="table table-bordered">
								<tbody>
									<tr>
										<td class="bg-light text-dark">생산번호</td>
										<td colspan="5">
											<input type="text" name="mf_num" id="mf_num" class="form-control-small" value="${maxMf +1}" readonly="readonly">
										</td>
									</tr>
									<tr>
										<td class="bg-light text-dark">공장번호</td>
										<td>
											<input type="text" name="f_num" id="f_num" class="form-control-small" value="${fac}" readonly="readonly">
										</td>
										<td class="bg-light text-dark">생산라인</td>
										<td>
											<input type="text" name="pl_num" id="pl_num" class="form-control-small" value="${pl_num}" readonly="readonly">
										</td>
										<td class="bg-light text-dark">담당직원</td>
										<td>
											<input type="text" name="e_id" id="e_id" class="form-control-small" value="${user.id}" readonly="readonly">
										</td>
									</tr>
									<tr>
										<td class="bg-light text-dark">상품번호</td>
										<td>
											<select class="form-control form-control-sm" name="p_num" id="p_num" required="required">
												<option></option>
												<c:forEach var="product" items="${products}">
													<option value="${product}">${product}</option>
												</c:forEach>
											</select>
										</td>
										<td class="bg-light text-dark">생산수량</td>
										<td>
											<input type="number" min="1000" step="500" name="mf_count" id="mf_count" class="form-control-small" required="required">
										</td>
										<td class="bg-light text-dark">생산일자</td>
										<td>
											<input type="date" name="mf_date" id="mf_date" class="form-control-small" required="required">
										</td>
									</tr>
								</tbody>
							</table>
						</c:if>
						<c:if test="${pl eq 'sale'}">
							<table class="table table-bordered">
								<tbody>
									<tr>
										<td class="bg-light text-dark">판매번호</td>
										<td colspan="5">
											<input type="text" name="s_num" id="s_num" class="form-control-small" value="${maxSale+1}" readonly="readonly">
										</td>
									</tr>
									<tr>
										<td class="bg-light text-dark">생산번호</td>
										<td>
											<input type="text" name="mf_num" id="mf_num" class="form-control-small" required="required">
										</td>
										<td class="bg-light text-dark">고객번호</td>
										<td>
											<select class="form-control form-control-sm" name="c_id" id="c_id" required="required">
												<option></option>
												<option value="33333">33333</option>
												<option value="34444">34444</option>
												<option value="35555">35555</option>
											</select>
										</td>
										<td class="bg-light text-dark">담당직원</td>
										<td>
											<input type="text" name="e_id" id="e_id" class="form-control-small" value="${user.id}" readonly="readonly">
										</td>
									</tr>
									<tr>
										<td class="bg-light text-dark">상품번호</td>
										<td>
											<select class="form-control form-control-sm" name="p_num" id="p_num" required="required">
												<option></option>
												<c:forEach var="product" items="${products}">
													<option value="${product}">${product}</option>
												</c:forEach>
											</select>
										</td>
										<td class="bg-light text-dark">판매수량</td>
										<td>
											<input type="number" min="1000" step="500" name="s_contract_sum" id="s_contract_sum" class="form-control-small" required="required">
										</td>
										<td class="bg-light text-dark">판매일자</td>
										<td>
											<input type="date" name="s_obtain_date" id="s_obtain_date" class="form-control-small" required="required">
										</td>
									</tr>
								</tbody>
							</table>
						</c:if>
						<div style="text-align: right; margin-top: 10px">
							<input type="submit" class="btn btn-outline-success btn-xs" value="등록">
							<input type="reset"  class="btn btn-outline-light btn-xs" value="초기화">
						</div>
						</form>
					</div>
				</div>
				<div class="card">
					<h5 class="card-header">${pl}정보</h5>
					<div class="card-body">
						<c:if test="${pl eq 'mf'}">
							<table class="table table-bordered">
								<thead class="bg-light">
									<tr class="border-0" style="text-align: center">
										<th class="border-0">#</th>
										<th class="border-0">공장번호</th>
										<th class="border-0">라인번호</th>
										<th class="border-0">상품번호</th>
										<th class="border-0">생산량</th>
										<th class="border-0">생산일자</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="mf" items="${works}">
										<tr>
											<th style="text-align: center">${mf.mf_num}</th>
											<td style="text-align: center">${mf.f_num}</td>
											<td style="text-align: center">${mf.pl_num}</td>
											<td style="text-align: center">${mf.p_num}</td>
											<td style="text-align: center">${mf.mf_count}</td>
											<td style="text-align: center">${mf.mf_date}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</c:if>
						<c:if test="${pl eq 'sale'}">
							<table class="table">
								<thead class="bg-light">
									<tr class="border-0" style="text-align: center">
										<th class="border-0">#</th>
										<th class="border-0">생산번호</th>
										<th class="border-0">상품번호</th>
										<th class="border-0">판매량</th>
										<th class="border-0">판매일자</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="sale" items="${works}">
										<tr>
											<th style="text-align: center">${sale.s_num}</th>
											<td style="text-align: center">${sale.mf_num}</td>
											<td style="text-align: center">${sale.p_num}</td>
											<td style="text-align: center">${sale.s_contract_sum}</td>
											<td style="text-align: center">${sale.s_obtain_date}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>