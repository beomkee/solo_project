<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<title>직원 등록</title>
<div class="dashboard-wrapper">
	<div class="container-fluid dashboard-content">
		<div class="row">
			<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12"></div>
		</div>
		<div class="row">
			<div style="width: 847px; margin-left: auto; margin-right: auto">
				<c:if test="${stat.noteq}">
					<div class="alert alert-warning alert-dismissible fade show" role="alert">
						비밀번호가 일치하지 않습니다
						<a href="#" class="close" data-dismiss="alert" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</a>
					</div>
				</c:if>
				<c:if test="${stat.existid}">
					<div class="alert alert-warning alert-dismissible fade show" role="alert">
						이미 존재하는 아이디 입니다
						<a href="#" class="close" data-dismiss="alert" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</a>
					</div>
				</c:if>
				<div class="card">
					<h5 class="card-header">직원등록</h5>
					<div class="card-body">
						<form action="<%=request.getContextPath()%>/manager/insertEmp.do" method="post">
							<table class="table table-hover">
								<tbody>
									<tr>
										<td>ID</td>
										<td>
											<input type="text" name="empid" class="form-control form-control-sm" maxlength="5" required="required">
										</td>
									</tr>
									<tr>
										<td>PW</td>
										<td>
											<input type="password" name="passwd" id="pass1" class="form-control form-control-sm" required="required">
										</td>
									</tr>
									<tr>
										<td scope="row">PW-CHECK</td>
										<td>
											<input type="password" name="passwdCheck" id="pass2" class="form-control form-control-sm" required="required">
										</td>
									</tr>
									<tr>
										<td scope="row">NAME</td>
										<td>
											<input type="text" name="name" class="form-control form-control-sm" required="required">
										</td>
									</tr>
									<tr>
										<td scope="row">EMAIL</td>
										<td>
											<input type="email" name="email" class="form-control form-control-sm" required="required">
										</td>
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
											-
											<input type="text" name="tel2" class="form-control-small" size="4" maxlength="4" required="required">
											-
											<input type="text" name="tel3" class="form-control-small" size="4" maxlength="4" required="required">
										</td>
									</tr>
									<tr>
										<td scope="row">BIRTH</td>
										<td>
											<input type="date" name="birth" class="form-control form-control-sm" required="required">
										</td>
									</tr>
									<tr>
										<td scope="row">MANAGER_NUM</td>
										<td>
											<input type="text" name="manager_num" class="form-control form-control-sm" value="${user.id}" readonly="readonly">
										</td>
									</tr>
									<tr>
										<td scope="row">F_NUM</td>
										<td>
										<input type="text" name="f_num" class="form-control form-control-sm" value="${user.f_num}" readonly="readonly">
										</td>
									</tr>
									<tr>
										<td scope="row">PL_NUM</td>
										<td>
											<select class="form-control-small" name="pl_num" id="input-select">
												<option></option>
												<option value="s_1">s_1</option>
												<option value="s_2">s_2</option>
												<option value="s_3">s_3</option>
												<option value="p_1">p_1</option>
												<option value="p_2">p_2</option>
												<option value="p_3">p_3</option>
											</select>
										</td>
									</tr>
									<tr>
										<td colspan="2" style="text-align: right;">
											<input type="submit" class="btn btn-outline-primary" value="등록" />
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
