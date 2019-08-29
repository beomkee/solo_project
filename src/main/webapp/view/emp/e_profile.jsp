<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<title>개인 정보 상세</title>
<div class="dashboard-wrapper">
	<div class="container-fluid dashboard-content">
		<div class="row">
			<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12"></div>
		</div>
		<div class="row">
			<div style="width: 847px; margin-left: auto; margin-right: auto">
				<c:if test="${stat.notEq}">
					<div class="alert alert-warning alert-dismissible fade show"  role="alert">비밀번호가 일치하지 않습니다
						<a href="#" class="close" data-dismiss="alert" aria-label="Close">
                        	<span aria-hidden="true">&times;</span>
                        </a>
                    </div>
				</c:if>
				<c:if test="${stat.success}">
					<div class="alert alert-success alert-dismissible fade show" role="alert">수정되었습니다.
					<a href="#" class="close" data-dismiss="alert" aria-label="Close">
                        	<span aria-hidden="true">&times;</span>
                        </a>
                    </div>
				</c:if>
				<div class="card">
					<h5 class="card-header">${user.name}'s Profile</h5>
					<div class="card-body">
						<form action="<%=request.getContextPath()%>/profile/changePW.do" method="post">
							<input type="hidden" name="id" id="id" value="${user.id}" />
							<table class="table table-hover">
								<tbody>
									<tr>
										<td>ID</td>
										<td>${user.id}</td>
									</tr>
									<tr>
										<td>PW</td>
										<td>
											<input type="password" name="passwd" class="form-control form-control-sm"
												placeholder="비밀번호를 변경하려면 입력하세요" required="required"
											>
										</td>
									</tr>
									<tr>
										<td scope="row">PW-CHECK</td>
										<td>
											<input type="password" name="passwdCheck" class="form-control form-control-sm"
												required="required"
											>
										</td>
									</tr>
									<tr>
										<td scope="row">NAME</td>
										<td>${user.name}</td>
									</tr>
									<tr>
										<td scope="row">EMAIL</td>
										<td>${user.email}</td>
									</tr>
									<tr>
										<td scope="row">TEL</td>
										<td>
											<select class="form-control-small" name="tel1" id="input-select">
												<option selected="selected">010</option>
												<option>011</option>
												<option>017</option>
												<option>019</option>
											</select>
											- <input type="text" name="tel2" class="form-control-small" size="4"
												value="${user.tel.substring(4,8)}" required="required"
											>- <input type="text" name="tel3" class="form-control-small" size="4"
												value="${user.tel.substring(9)}" required="required"
											>
										</td>
									</tr>
									<tr>
										<td scope="row">BIRTH</td>
										<td>${user.birth}</td>
									</tr>
									<tr>
										<td scope="row">MANAGER_NUM</td>
										<td>${user.manager_num}</td>
									</tr>
									<tr>
										<td scope="row">F_NUM</td>
										<td>${user.f_num}</td>
									</tr>
									<tr>
										<td scope="row">PL_NUM</td>
										<td>${user.pl_num}</td>
									</tr>
									<tr>
										<td colspan="2" style="text-align: right;">
											<input type="submit" class="btn btn-outline-primary" value="수정" />
										</td>
									</tr>
								</tbody>
							</table>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>