<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/concept-master/smartediter/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<title>쪽지 보내기</title>
<div class="dashboard-wrapper">
	<div class="container-fluid dashboard-content">
		<div class="row">
			<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
				<c:if test="${stat != null}">
					<c:if test="${stat eq 'fail'}">
						<div class="alert alert-warning alert-dismissible fade show"  role="alert">전송에 실패했습니다
							<a href="#" class="close" data-dismiss="alert" aria-label="Close">
	                        	<span aria-hidden="true">&times;</span>
	                        </a>
	                    </div>
					</c:if>
					<c:if test="${stat eq 'success'}">
						<div class="alert alert-success alert-dismissible fade show" role="alert">쪽지를 전송 했습니다.
						<a href="#" class="close" data-dismiss="alert" aria-label="Close">
	                        	<span aria-hidden="true">&times;</span>
	                        </a>
	                    </div>
					</c:if>
				</c:if>
				<div class="page-header">
					<h2 class="pageheader-title">쪽지 보내기</h2>
				</div>
			</div>
		</div>
		<div class="row">
			<div style="width: 847px; margin-left: auto; margin-right: auto" id="main">
				<div class="card">
					<h5 class="card-header">쪽지 쓰기</h5>
					<div class="card-body">
						<form action="<%=request.getContextPath() %>/profile/sendMessage.do" method="post">
							<table class="table table-bordered">
								<tbody>
									<tr>
										<td class="bg-light text-dark" width="20%">받는사람</td>
										<td>
											<select class="js-example-basic-multiple" name="receiver" id="receiver" required="required" multiple="multiple">
												<c:forEach var="user" items="${users}">
													<option value="${user}">${user}</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td colspan=2>
											<input class="form-control" type="text" name="title" required="required" >
										</td>
									</tr>
									<tr>
										<td class="bg-light text-dark" colspan=2>
											<textarea class="form-control" name="content" id="content" style="width: 100%; height: 302px; min-width: 610px; display: none;"></textarea>
										</td>
									</tr>
								</tbody>
							</table>
							<div style="text-align: right; margin-top: 10px">
								<input type="submit" onclick="submitContents(this);" class="btn btn-outline-primary btn-xs" value="전송">
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<script>
		var oEditors = [];

		var sLang = "ko_KR";	

		nhn.husky.EZCreator.createInIFrame({
			oAppRef: oEditors,
			elPlaceHolder: "content",
			sSkinURI: "<%=request.getContextPath()%>/concept-master/smartediter/SmartEditor2Skin.html",
						htParams : {
							bUseToolbar : true, // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
							bUseVerticalResizer : true, // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
							bUseModeChanger : false, // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
							//bSkipXssFilter : true,		// client-side xss filter 무시 여부 (true:사용하지 않음 / 그외:사용)
							//aAdditionalFontList : aAdditionalFontSet,		// 추가 글꼴 목록
							fOnBeforeUnload : function() {
							},
							I18N_LOCALE : sLang
						},
						fOnAppLoad : function() {
						},
						fCreator : "createSEditor2"
					});

			function submitContents(elClickedObj) {
				oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []); 
				try {
					elClickedObj.form.submit();
				} catch (e) {
				}
			}
		</script>