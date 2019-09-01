<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<title>이메일</title>
<div class="dashboard-wrapper">
	<div class="container-fluid dashboard-content">
		<div class="row">
			<div class="col-xl-4 col-lg-6 col-md-6 col-sm-12 col-12" style="margin-left: auto; margin-right: auto;">
				<div class="card">
					<img class="card-img-top" src="<%= request.getContextPath()%>/concept-master/img/emailsuccess.png" alt="Card image cap">
					<div class="card-header">email send success</div>
					<div class="card-body">
						<p class="card-text">${receiver}으로이메일 전송이 완료되었습니다.</p>
					</div>
					<div class="card-footer p-0 text-center">
						<div class="card-footer-item card-footer-item-bordered">
							<a href="<%=request.getContextPath()%>/email/sendList.do" class="card-link">보낸 메일함으로 이동</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>