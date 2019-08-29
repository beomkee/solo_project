<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>직원 메인</title>
<div class="dashboard-wrapper">
	<div class="influence-profile">
		<div class="container-fluid dashboard-content ">
			<div class="row">
				<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
					<div class="page-header">
						<h3 class="mb-2">${user.name}'sDashBoard</h3>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-xl-3 col-lg-3 col-md-5 col-sm-12 col-12">
					<div class="card">
						<div class="card-body">
							<div class="user-avatar text-center d-block">
								<a href="<%=request.getContextPath()%>/profile/e_profile.do">
									<img src="<%=request.getContextPath()%>/assets/img/user/${LOGINED_ID}.jpg" alt="User Avatar" class="rounded-circle user-avatar-xxl">
								</a>
							</div>
							<div class="text-center">
								<h2 class="font-24 mb-0">${user.name}</h2>
								<p>${user.id}</p>
							</div>
						</div>
						<div class="card-body border-top">
							<div class="">
								<ul class="list-unstyled mb-0">
									<li class="mb-2">회사정보</li>
									<li class="mb-2">
										<i class="fas fa-fw fa-industry mr-2"></i>${user.f_num}
									</li>
									<li class="mb-2">
										<i class="fas fa-fw fa-sitemap mr-2"></i>${user.pl_num}
									</li>
									<li class="mb-0">
										<i class="fas fa-fw fa-id-badge mr-2"></i>${user.manager_num}
									</li>
									<li class="mb-2">&nbsp;</li>
									<li class="mb-2">개인정보</li>
									<li class="mb-2">
										<i class="fas fa-fw fa-envelope mr-2"></i>${user.email}
									</li>
									<li class="mb-0">
										<i class="fas fa-fw fa-phone mr-2"></i>${user.tel}
									</li>
									<li class="mb-0">
										<i class="fas fa-fw fa-tag mr-2"></i>${user.birth}
									</li>
								</ul>
							</div>
						</div>
						<div class="card-body border-top">
							<p>공장 위치</p>
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
					</div>
				</div>
				<div class="col-xl-9 col-lg-9 col-md-7 col-sm-12 col-12">
					<div class="influence-profile-content pills-regular">
						<div class="tab-content" id="pills-tabContent">
							<div class="tab-pane fade show active" id="pills-campaign" role="tabpanel" aria-labelledby="pills-campaign-tab">
								<div class="row">
									<div class="col-xl-3 col-lg-3 col-md-6 col-sm-12 col-12">
										<div class="card border-3 border-top border-top-primary">
											<div class="card-body">
												<h5 class="text-muted">
													<a href="<%=request.getContextPath()%>/profile/works.do"> 최근 업무 </a>
												</h5>
												<div class="metric-value d-inline-block">
													<h1 class="mb-1">${lastWort}</h1>
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
													<a href="<%=request.getContextPath()%>/profile/message.do"> 읽지 않은 쪽지 </a>
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
								<!-- =========== 공지 요약 =========== -->
								<div class="row">
									<div class="card-body">
										<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
											<div class="card">
												<h5 class="card-header">최근 공지 사항</h5>
												<div class="card-body p-0">
													<div class="table-responsive">
														<table class="table">
															<thead class="bg-light">
																<tr class="border-0" style="text-align: center">
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
									</div>
								</div>
								<!-- =========== 안읽은 쪽지 =========== -->
								<div class="row">
									<div class="card-body">
										<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
											<div class="card">
												<h5 class="card-header">읽지 않은 쪽지</h5>
												<div class="card-body p-0">
													<div class="table-responsive">
														<table class="table">
															<c:if test="${unReadMs == 0 }">
																<thead class="bg-light">
																	<tr>
																		<th scope="col" style="text-align: center">읽지않은 쪽지가 없습니다</th>
																	</tr>
																</thead>
															</c:if>
															<c:if test="${unReadMs != 0 }">
																<thead>
																	<tr class="bg-light">
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
															</c:if>
															<tr>
																<td colspan="8">
																	<a href="<%=request.getContextPath()%>/profile/message.do" class="btn btn btn-outline-warning btn-sm float-right">Message Details</a>
																</td>
															</tr>
															</tbody>
														</table>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<!-- =========== 최근 업무 =========== -->
								<div class="row">
									<div class="card-body">
										<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
											<div class="card">
												<h5 class="card-header">최근 나의 업무</h5>
												<div class="card-body p-0">
													<div class="table-responsive">
														<c:if test="${pl eq 'mf'}">
															<table class="table">
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
																	<tr>
																		<td colspan="8">
																			<a href="<%=request.getContextPath()%>/profile/works.do" class="btn btn btn-outline-warning btn-sm float-right">Works Details</a>
																		</td>
																	</tr>
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
																	<tr>
																		<td colspan="8">
																			<a href="<%=request.getContextPath()%>/profile/works.do" class="btn btn btn-outline-warning btn-sm float-right">Works Details</a>
																		</td>
																	</tr>
																</tbody>
															</table>
														</c:if>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<!-- =========== 보낸 이메일 =========== -->
								<div class="row">
									<div class="card-body">
										<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
											<div class="card">
												<h5 class="card-header">최근 보낸 이메일</h5>
												<div class="card-body p-0">
													<div class="table-responsive">
														<table class="table">
															<thead class="bg-light">
																<tr class="border-0" style="text-align: center">
																	<th scope="col">#</th>
																	<th scope="col" style="text-align: center">from</th>
																	<th scope="col" style="text-align: center">to</th>
																	<th scope="col" width="40%" style="text-align: center">내용</th>
																	<th scope="col" style="text-align: center">보낸일자</th>
																</tr>
															</thead>
															<tbody>
																<c:forEach var="email" items="${emails}">
																	<tr>
																		<th style="text-align: center" scope="row">${email.num}</th>
																		<td style="text-align: center">${email.writer}</td>
																		<td style="text-align: center">${email.to}</td>
																		<td>
																			<a href="<%=request.getContextPath() %>/email/eContent.do?num=${email.num}">${email.title}</a>
																		</td>
																		<td style="text-align: center">${email.reg_date}</td>
																	</tr>
																</c:forEach>
																<tr>
																	<td colspan="8">
																		<a href="<%=request.getContextPath()%>/email/sendList.do" class="btn btn btn-outline-warning btn-sm float-right">Email Details</a>
																	</td>
																</tr>
															</tbody>
														</table>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>