<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/concept-master/smartediter/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<title>공지 사항</title>
<div class="dashboard-wrapper">
	<div class="container-fluid dashboard-content">
		<div class="row">
			<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
				<div class="card">
					<h5 class="card-header">공지 수정</h5>
					<div class="card-body">
						<form action="notice/nUpdate.do" method="post">
						<input type="hidden" name="writer" value="${user.id}">
							<table class="table table-bordered">
								<tbody>
									<tr>
										<td class="bg-light text-dark">공지번호</td>
										<td colspan="5">
											<input type="text" name="n_num" id="n_num" class="form-control-small" value="${notice.n_num}" readonly="readonly">
										</td>
									</tr>
									<tr>
										<td class="bg-light text-dark">제목</td>
										<td>
											<input type="text" name="n_title" id="n_title" value="${notice.title}" class="form-control">
										</td>
									</tr>
									<tr>
										<td class="bg-light text-dark" colspan=2 style="text-align: center">내용</td>
									</tr>
									<tr>
										<td colspan=2>
											<textarea class="form-control" name="n_content" id="n_content" style="width: 100%; height: 412px; min-width: 610px; display: none;"></textarea>
										</td>
								</tbody>
							</table>
							<div style="text-align: right; margin-top: 10px">
								<input type="submit" onclick="submitContents(this);" class="btn btn-outline-primary" value="수정"> 
								<input type="button"  onclick="location.href='<%= request.getContextPath() %>/notice/nList.do?pageNum?${pageNum}'" class="btn btn-outline-success" value="목록보기" > 
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
var oEditors = [];
var sLang = "ko_KR";	

nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "n_content",
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
					pasteHTML();
				},
				fCreator : "createSEditor2"
			});
			
function submitContents(elClickedObj) {
	oEditors.getById["n_content"].exec("UPDATE_CONTENTS_FIELD", []);	// 에디터의 내용이 textarea에 적용됩니다.
	
	// 에디터의 내용에 대한 값 검증은 이곳에서 document.getElementById("n_content").value를 이용해서 처리하면 됩니다.
	try {
		elClickedObj.form.submit();
	} catch(e) {}
}
function pasteHTML() {
	var sHTML = '${notice.content}';
	oEditors.getById["n_content"].exec("PASTE_HTML", [sHTML]);
}
</script>






