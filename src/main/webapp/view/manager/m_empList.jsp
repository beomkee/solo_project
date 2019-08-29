<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="<%=request.getContextPath()%>/concept-master/customJs/commonGrid.js"></script>
<title>직원 관리</title>
<div class="dashboard-wrapper">
	<div class="container-fluid dashboard-content">
		<div class="row">
			<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
				<div class="page-header">
					<h2 class="pageheader-title">직원 관리</h2>
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
									<td class="bg-light text-dark">직원번호</td>
									<td>
										<input type="text" name="id" id="id" class="form-control-small" value="" readonly="readonly">
									</td>
									<td class="bg-light text-dark">비밀번호</td>
									<td>
										<input type="password" name="passwd" id="passwd" class="form-control-small" value="" readonly="readonly">
									</td>
									<td class="bg-light text-dark">매니저</td>
									<td>
										<input type="text" name="manage" id="manage" class="form-control-small" value="${user.id }" readonly="readonly">
									</td>
								</tr>
								<tr>
									<td class="bg-light text-dark">직원이름</td>
									<td>
										<input type="text" name="name" id="name" class="form-control-small">
									</td>
									<td class="bg-light text-dark">공장번호</td>
									<td>
										<input type="text" name="f_num" id="f_num" value="${user.f_num}" class="form-control-small" readonly="readonly">
									</td>
									<td class="bg-light text-dark">라인번호</td>
									<td>
										<select class="form-control form-control-sm" name="pl_num" id="pl_num">
											<option></option>
											<option value="p_1">p_1</option>
											<option value="p_2">p_2</option>
											<option value="p_3">p_3</option>
											<option value="s_1">s_1</option>
											<option value="s_2">s_2</option>
											<option value="s_3">s_3</option>
										</select>
									</td>
								</tr>
								<tr>
									<td class="bg-light text-dark">이메일</td>
									<td>
										<input type="email" name="email" id="email" class="form-control-small">
									</td>
									<td class="bg-light text-dark">전화번호</td>
									<td>
										<select class="form-control-small" name="tel1" id="input-select">
											<option value="010" selected="selected">010</option>
											<option value="011">011</option>
											<option value="017">017</option>
											<option value="019">019</option>
										</select>
										-
										<input type="text" name="tel2" class="form-control-small" size="4" maxlength="4" required="required">
										-
										<input type="text" name="tel3" class="form-control-small" size="4" maxlength="4" required="required">
									</td>
									<td class="bg-light text-dark">생년월일</td>
									<td>
										<input type="date" name="birth" id="birth" class="form-control-small">
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
					<h5 class="card-header">직원 LIST</h5>
					<div class="card-body" id="gridbody">
						<table id="gridTable" class="table table-bordered">
						</table>
						<div id="gridPaging"></div>
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

			var id = "e_id";
			var columLength = colNames.length - 1;
			var id, passwd, name, tel, birth, manager_num, f_num, pl_num, email;

			// 그리드항목 검색폼 전환==========
			function dubleClick(values) {
				$('#id').val(values[0]);
				$('#passwd').val(values[1]);
				$('#name').val(values[2]);
				var tel2 = values[3].substring(4, 8);
				var tel3 = values[3].substring(9);
				$('#tel2').val(tel2);
				$('#tel3').val(tel3);
				$('#birth').val(values[4]);
				//$('#manager_num').val(values[5]);
				$('#f_num').val(values[6]);
				$('#pl_num').val(values[7]);
				$('#email').val(values[8]);
			}

			// 검색폼 초기화===================
			function reset() {
				$('#id').val('');
				$('#passwd').val('');
				$('#name').val('');
				$('#pl_num').val('');
				$('#email').val('');
				$('#tel1').val('010');
				$('#tel2').val('');
				$('#tel3').val('');
				$('#birth').val('');
				makeTable('gridTable', list, colNames, colModel);
			}

			// 검색폼 데이터 세팅==============
			function setData() {
				id = $('#id').val();
				passwd = $('#passwd').val();
				name = $('#name').val();
				tel = $('#tel1 option:selected').val() + $('#tel2').val()
						+ $('#tel3').val();
				birth = $('#birth').val();
				manager_num = $('#manage').val();
				f_num = $('#f_num').val();
				pl_num = $('#pl_num option:selected').val();
				email = $('#email').val();
				dataSet = id + "," + passwd + "," + name + "," + tel + ","
						+ birth + "," + manager_num + "," + birth + "," + f_num
						+ "," + pl_num + "," + email;
			}

			// 검색폼 입력여부 확인============
			function isFilled() {
				setData();
				if (id == null || passwd == null || name == null || tel == null
						|| birth == null || manager_num == null
						|| f_num == null || pl_num == null || email == null
						|| email == null || id == "" || passwd == ""
						|| name == "" || tel == "" || birth == ""
						|| manager_num == "" || f_num == "" || pl_num == ""
						|| email == "") {
					return false;
				} else {
					return true;
				}
			}
		</script>