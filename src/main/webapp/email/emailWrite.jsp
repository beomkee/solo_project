<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/concept-master/smartediter/js/service/HuskyEZCreator.js" charset="utf-8"></script>
	<div class="container-fluid dashboard-content">
		<div class="row">
			<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
				<div class="email-head">
					<div class="email-head-title">
						새로운 이메일 작성
						<span class="icon mdi mdi-edit"></span>
					</div>
				</div>
				<form action="main/sendEmail.do" method="post">
					<div class="email-compose-fields">
						<div class="to">
							<div class="form-group row pt-0">
								<label class="col-md-1 control-label">To:</label>
								<div class="col-md-11">
									<select class="js-example-basic-multiple" name="receiver" id="receiver" required="required" multiple="multiple">
										<c:forEach var="email" items="${emailList}">
											<option value="${email}">${email}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						<div class="to cc">
							<div class="form-group row pt-2">
								<label class="col-md-1 control-label">Cc</label>
								<div class="col-md-11">
									<select class="js-example-basic-multiple" name="cc" id="cc" multiple="multiple">
										<c:forEach var="email" items="${emailList}">
											<option value="${email}">${email}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						<div class="subject">
							<div class="form-group row pt-2">
								<label class="col-md-1 control-label">제목</label>
								<div class="col-md-11">
									<input class="form-control" name="e_title" id="e_title" type="text" required="required">
								</div>
							</div>
						</div>
					</div>
					<div class="email editor">
						<div class="col-md-12 p-0">
							<div class="form-group">
								<textarea class="form-control" name="e_content" id="e_content" style="width: 100%; height: 302px; min-width: 610px; display: none;"></textarea>
							</div>
						</div>
						<div class="email action-send">
							<div class="col-md-12 ">
								<div class="form-group" style="text-align: right">
									<input type="submit" class="btn btn-primary btn-space" onclick="submitContents(this);" value="Send">
									<button class="btn btn-secondary btn-space" type="button">
										<i class="icon s7-close"></i> Cancel
									</button>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	function aaaa(){
		console.log($('#receiver option:selected').val());
		console.log($('#e_title').val());
		console.log($('#e_content').val());
		confirm("aaaa");
	}
	
var oEditors = [];

var sLang = "ko_KR";	

nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "e_content",
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
		oEditors.getById["e_content"].exec("UPDATE_CONTENTS_FIELD", []); // 에디터의 내용이 textarea에 적용됩니다.
		// 에디터의 내용에 대한 값 검증은 이곳에서 document.getElementById("n_content").value를 이용해서 처리하면 됩니다.
		try {
			elClickedObj.form.submit();
		} catch (e) {
		}
	}
</script>