<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<title>관리자 메인</title>
<div class="dashboard-wrapper">
	<div class="dashboard-ecommerce">
		<div class="container-fluid dashboard-content ">
			<div class="row">
				<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
					<div class="page-header">
						<h2 class="pageheader-title">관리자 Dashboard</h2>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
					<div class="card influencer-profile-data">
						<div class="card-body">
							<div class="row">
								<div class="col-xl-2 col-lg-4 col-md-4 col-sm-4 col-12">
									<div class="text-center">
										<img src="<%= request.getContextPath() %>/concept-master/img/user/${user.id}.jpg" alt="User Avatar" class="rounded-circle user-avatar-lg">
									</div>
								</div>
								<div class="col-xl-10 col-lg-8 col-md-8 col-sm-8 col-12">
									<div style="margin-top: 1%; float: left">
										<div class="user-avatar-name">
											<h2 class="mb-1">${user.name }</h2>
										</div>
										<span class="d-xl-inline-block d-block mb-2">
											<i class="fa fa-map-marker-alt mr-2 text-primary "></i>${user.f_num}</span>
										<span class="mb-2 ml-xl-4 d-xl-inline-block d-block">${user.pl_num} </span>
										<span class="mb-2 ml-xl-4 d-xl-inline-block d-block">${user.id} </span>
										<span class=" mb-2 d-xl-inline-block d-block ml-xl-4">${user.email} </span>
										<span class=" mb-2 d-xl-inline-block d-block ml-xl-4">${user.tel} </span>
										<span class=" mb-2 d-xl-inline-block d-block ml-xl-4">${user.birth } </span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="ecommerce-widget">
				<div class="row">
					<div class="col-xl-3 col-lg-3 col-md-6 col-sm-12 col-12">
						<div class="card border-3 border-top border-top-primary">
							<div class="card-body">
								<h5 class="text-muted">
									<a href="<%=request.getContextPath()%>/manager/empList.do"> 담당 직원 </a>
								</h5>
								<div class="metric-value d-inline-block">
									<h1 class="mb-1">${myEmps}</h1>
								</div>
							</div>
						</div>
					</div>
					<div class="col-xl-3 col-lg-3 col-md-6 col-sm-12 col-12">
						<div class="card border-3 border-top border-top-primary">
							<div class="card-body">
								<h5 class="text-muted">
									<a href="<%=request.getContextPath()%>/profile/message.do">읽지 않은 쪽지 </a>
								</h5>
								<div class="metric-value d-inline-block">
									<h1 class="mb-1">${unReadMs}</h1>
								</div>
							</div>
						</div>
					</div>
					<div class="col-xl-3 col-lg-3 col-md-6 col-sm-12 col-12">
						<div class="card border-3 border-top border-top-primary">
							<div class="card-body">
								<h5 class="text-muted">
									<a href="<%=request.getContextPath()%>/email/sendList.do"> 최근 보낸 이메일 </a>
								</h5>
								<div class="metric-value d-inline-block">
									<h1 class="mb-1">${lastEmail }</h1>
								</div>
								<div class="metric-label d-inline-block float-right text-success font-weight-bold">
									<span class="ml-1">7월</span>
								</div>
							</div>
						</div>
					</div>
					<div class="col-xl-3 col-lg-3 col-md-6 col-sm-12 col-12">
						<div class="card border-3 border-top border-top-primary">
							<div class="card-body">
								<h5 class="text-muted">
									<a href="<%=request.getContextPath()%>/notice/nList.do"> 최근공지 </a>
								</h5>
								<div class="metric-value d-inline-block">
									<h1 class="mb-1">${lastNotice}</h1>
								</div>
								<div class="metric-label d-inline-block float-right text-success font-weight-bold">
									<span class="ml-1">7월</span>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xl-9 col-lg-12 col-md-6 col-sm-12 col-12">
						<div class="card">
							<h5 class="card-header">최근 공지 사항</h5>
							<div class="card-body p-0">
								<div class="table-responsive">
									<table class="table">
										<thead class="bg-light">
											<tr class="border-0">
												<th class="border-0">#</th>
												<th class="border-0">내용</th>
												<th class="border-0">등록일</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="notice" items="${notices}">
												<tr>
													<th style="text-align: center">${notice.n_num }</th>
													<td>
														<a href="<%=request.getContextPath() %>/notice/nContent.do?nNum=${notice.n_num}&pageNum=1">${notice.title}</a>
													</td>
													<td style="text-align: center">${notice.mod_date}</td>
												</tr>
											</c:forEach>
											<tr>
												<td colspan="8">
													<a href="<%=request.getContextPath()%>/notice/nList.do" class="btn btn btn-outline-warning btn-sm float-right">Notice Details</a>
												</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
					<div class="col-xl-3 col-lg-6 col-md-12 col-sm-12 col-12">
						<div class="product-thumbnail">
							<h5 class="card-header">공장 정보</h5>
							<div class="product-img-head">
								<c:if test="${user.f_num eq 'sw_12345' }">
									<div id="daumRoughmapContainer1563933846962" class="root_daum_roughmap root_daum_roughmap_landing" style="width: 100%; height: 200px"></div>
									<script charset="UTF-8" class="daum_roughmap_loader_script" src="https://ssl.daumcdn.net/dmaps/map_js_init/roughmapLoader.js"></script>
									<script charset="UTF-8">
										new daum.roughmap.Lander({
											"timestamp" : "1563933846962",
											"key" : "ugvu",
										}).render();
									</script>
								</c:if>
								<c:if test="${user.f_num eq 'yi_45678' }">
									<div id="daumRoughmapContainer1563934045852" class="root_daum_roughmap root_daum_roughmap_landing" style="width: 100%; height: 200px"></div>
									<script charset="UTF-8" class="daum_roughmap_loader_script" src="https://ssl.daumcdn.net/dmaps/map_js_init/roughmapLoader.js"></script>
									<script charset="UTF-8">
										new daum.roughmap.Lander({
											"timestamp" : "1563934045852",
											"key" : "ugw3",
										}).render();
									</script>
								</c:if>
							</div>
							<div class="product-content">
								<div class="product-content-head">
									<h3 class="product-title">${fac.f_name}</h3>
									<div class="product-price">${fac.f_num}</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="card">
					<div class="card-header" id="headingEight">
						<h5 class="mb-0">
							<button onclick="getChartData()" class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseEight" aria-expanded="false"
								aria-controls="collapseEight"
							>
								<span class="fas fa-angle-down mr-3"></span>
								데이터 분석
							</button>
						</h5>
					</div>
					<div id="collapseEight" class="collapse" aria-labelledby="headingEight" data-parent="#accordion3">
						<div class="card-body">
							<div class="row">
								<div class="col-xl-6 col-lg-6 col-md-6 col-sm-12 col-12">
									<div class="card">
										<h5 class="card-header">공장 최근 6개월 생산 현황</h5>
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
				<div class="row">
					<c:forEach var="emp" items="${emps}">
						<div class="col-xl-3 col-lg-6 col-md-6 col-sm-12 col-12">
							<div class="card campaign-card text-center">
								<div class="card-body">
									<div class="campaign-img">
										<img src="<%= request.getContextPath() %>/concept-master/img/user/${emp.id}.jpg" alt="user" class="user-avatar-xl">
									</div>
									<div class="campaign-info">
										<h3 class="mb-1">${emp.name}</h3>
										<p class="mb-3">${emp.id}</p>
										<p class="mb-1">${emp.f_num}</p>
										<p class="mb-1">${emp.pl_num}</p>
										<p class="mb-1">${emp.passwd}실적:
											<span class="text-dark font-medium ml-2">${emp.contract_date}</span>
										</p>
										<p class="mb-1">${emp.email}</p>
										<p class="mb-1">${emp.tel}</p>
										<p class="mb-1">${emp.birth}</p>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
				<!-- <div class="row">
					<div class="col-xl-5 col-lg-6 col-md-6 col-sm-12 col-12">
						<div class="card">
							<h5 class="card-header">Sales By Social Source</h5>
							<div class="card-body p-0">
								<ul class="social-sales list-group list-group-flush">
									<li class="list-group-item social-sales-content">
										<span class="social-sales-icon-circle facebook-bgcolor mr-2">
											<i class="fab fa-facebook-f"></i>
										</span>
										<span class="social-sales-name">Facebook</span>
										<span class="social-sales-count text-dark">120 Sales</span>
									</li>
									<li class="list-group-item social-sales-content">
										<span class="social-sales-icon-circle twitter-bgcolor mr-2">
											<i class="fab fa-twitter"></i>
										</span>
										<span class="social-sales-name">Twitter</span>
										<span class="social-sales-count text-dark">99 Sales</span>
									</li>
									<li class="list-group-item social-sales-content">
										<span class="social-sales-icon-circle instagram-bgcolor mr-2">
											<i class="fab fa-instagram"></i>
										</span>
										<span class="social-sales-name">Instagram</span>
										<span class="social-sales-count text-dark">76 Sales</span>
									</li>
									<li class="list-group-item social-sales-content">
										<span class="social-sales-icon-circle pinterest-bgcolor mr-2">
											<i class="fab fa-pinterest-p"></i>
										</span>
										<span class="social-sales-name">Pinterest</span>
										<span class="social-sales-count text-dark">56 Sales</span>
									</li>
									<li class="list-group-item social-sales-content">
										<span class="social-sales-icon-circle googleplus-bgcolor mr-2">
											<i class="fab fa-google-plus-g"></i>
										</span>
										<span class="social-sales-name">Google Plus</span>
										<span class="social-sales-count text-dark">36 Sales</span>
									</li>
								</ul>
							</div>
							<div class="card-footer text-center">
								<a href="#" class="btn-primary-link">View Details</a>
							</div>
						</div>
						==============================================================
						end social source 
						==============================================================
					</div>
					<div class="col-xl-4 col-lg-6 col-md-6 col-sm-12 col-12">
						==============================================================
						sales traffice source 
						==============================================================
						<div class="card">
							<h5 class="card-header">Sales By Traffic Source</h5>
							<div class="card-body p-0">
								<ul class="traffic-sales list-group list-group-flush">
									<li class="traffic-sales-content list-group-item ">
										<span class="traffic-sales-name">Direct</span>
										<span class="traffic-sales-amount">
											$4000.00
											<span class="icon-circle-small icon-box-xs text-success ml-4 bg-success-light">
												<i class="fa fa-fw fa-arrow-up"></i>
											</span>
											<span class="ml-1 text-success">5.86%</span>
										</span>
									</li>
									<li class="traffic-sales-content list-group-item">
										<span class="traffic-sales-name">
											Search
											<span class="traffic-sales-amount">
												$3123.00
												<span class="icon-circle-small icon-box-xs text-success ml-4 bg-success-light">
													<i class="fa fa-fw fa-arrow-up"></i>
												</span>
												<span class="ml-1 text-success">5.86%</span>
											</span>
										</span>
									</li>
									<li class="traffic-sales-content list-group-item">
										<span class="traffic-sales-name">
											Social
											<span class="traffic-sales-amount ">
												$3099.00
												<span class="icon-circle-small icon-box-xs text-success ml-4 bg-success-light">
													<i class="fa fa-fw fa-arrow-up"></i>
												</span>
												<span class="ml-1 text-success">5.86%</span>
											</span>
										</span>
									</li>
									<li class="traffic-sales-content list-group-item">
										<span class="traffic-sales-name">
											Referrals
											<span class="traffic-sales-amount ">
												$2220.00
												<span class="icon-circle-small icon-box-xs text-danger ml-4 bg-danger-light">
													<i class="fa fa-fw fa-arrow-down"></i>
												</span>
												<span class="ml-1 text-danger">4.02%</span>
											</span>
										</span>
									</li>
									<li class="traffic-sales-content list-group-item ">
										<span class="traffic-sales-name">
											Email
											<span class="traffic-sales-amount">
												$1567.00
												<span class="icon-circle-small icon-box-xs text-danger ml-4 bg-danger-light">
													<i class="fa fa-fw fa-arrow-down"></i>
												</span>
												<span class="ml-1 text-danger">3.86%</span>
											</span>
										</span>
									</li>
								</ul>
							</div>
							<div class="card-footer text-center">
								<a href="#" class="btn-primary-link">View Details</a>
							</div>
						</div>
					</div>
					==============================================================
					end sales traffice source 
					==============================================================
					==============================================================
					sales traffic country source 
					==============================================================
					<div class="col-xl-3 col-lg-12 col-md-6 col-sm-12 col-12">
						<div class="card">
							<h5 class="card-header">Sales By Country Traffic Source</h5>
							<div class="card-body p-0">
								<ul class="country-sales list-group list-group-flush">
									<li class="country-sales-content list-group-item">
										<span class="mr-2">
											<i class="flag-icon flag-icon-us" title="us" id="us"></i>
										</span>
										<span class="">United States</span>
										<span class="float-right text-dark">78%</span>
									</li>
									<li class="list-group-item country-sales-content">
										<span class="mr-2">
											<i class="flag-icon flag-icon-ca" title="ca" id="ca"></i>
										</span>
										<span class="">Canada</span>
										<span class="float-right text-dark">7%</span>
									</li>
									<li class="list-group-item country-sales-content">
										<span class="mr-2">
											<i class="flag-icon flag-icon-ru" title="ru" id="ru"></i>
										</span>
										<span class="">Russia</span>
										<span class="float-right text-dark">4%</span>
									</li>
									<li class="list-group-item country-sales-content">
										<span class=" mr-2">
											<i class="flag-icon flag-icon-in" title="in" id="in"></i>
										</span>
										<span class="">India</span>
										<span class="float-right text-dark">12%</span>
									</li>
									<li class="list-group-item country-sales-content">
										<span class=" mr-2">
											<i class="flag-icon flag-icon-fr" title="fr" id="fr"></i>
										</span>
										<span class="">France</span>
										<span class="float-right text-dark">16%</span>
									</li>
								</ul>
							</div>
							<div class="card-footer text-center">
								<a href="#" class="btn-primary-link">View Details</a>
							</div>
						</div>
					</div>
					==============================================================
					end sales traffice country source 
					==============================================================
				</div> -->
			</div>
		</div>
	</div>
	<script>
		var label1 = "1분기";
		var label2 = "2분기";
		var getDataUrl = "facData.do";
		var fac = "${user.f_num}";
		$(document).ready(function() {
			getChartData();
		});
	</script>
	<script src="<%=request.getContextPath()%>/concept-master/assets/vendor/charts/charts-bundle/Chart.bundle.js"></script>
	<script src="<%=request.getContextPath()%>/concept-master/customJs/customChart.js"></script>