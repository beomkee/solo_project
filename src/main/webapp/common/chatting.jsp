<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="<%=request.getContextPath()%>/concept-master/jquery-3.3.1.js"></script>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<meta charset="UTF-8">
<title>채팅하기</title>
</head>
<style>
.chat-body {
	width: 100%;
	height: 200px;
	border: 1px solid grey;
	overflow: auto;
	background-color: #c7e1fc;
	margin-top: 150px;
}

#me {
	position: relative;
	left: 100px;
}

#you {
	position: relative;
	left: 10px;
}
</style>
<body>
<fieldset>
	<div class="chat-body" id="messageWindow"></div>
	<input class="form-control-small form-control-sm" style="width: 79%;" id="inputMessage" type="text" />
	<input class="btn btn-outline-info btn-xs" type="submit" value="send" onclick="send()" />
</fieldset>
</body>
<script type="text/javascript">
	$('#inputMessage').keydown(function(e) {
	    if (e.keyCode == 13) {
	    	send();
	    }
	});

    var textarea = document.getElementById("messageWindow");
    var webSocket = new WebSocket('ws://211.63.89.78:9080<%=request.getContextPath()%>/weball');
	var inputMessage = document.getElementById('inputMessage');

	webSocket.onerror = function(event) {
		onError(event)
	};

	webSocket.onopen = function(event) {
		onOpen(event)
	};

	webSocket.onmessage = function(event) {
		onMessage(event)
	};

	function onMessage(event) {
		textarea.innerHTML += "<div  id='you'  class='w3-white "
				+ "w3-border w3-round-large w3-padding-small' "
				+ "style='width:" + (event.data.length * 12) + "px;'>"
				+ event.data + "</div><br>";
		textarea.scrollTop = textarea.scrollHeight;
	}

	function onOpen(event) {
		textarea.innerHTML += "연결 성공<br>";
		webSocket.send("${user.name}:입장 하였습니다");
	}

	function onError(event) {
		//alert(event.data);   
	}

	function send() {
		textarea.innerHTML += "<div  class='w3-yellow w3-border "
				+ "alert alert-warning' " + " id='me' style='width: 55%'>"
				+ inputMessage.value
				+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div><br>";
		textarea.scrollTop = textarea.scrollHeight;
		webSocket.send("${user.name}:" + inputMessage.value);
		inputMessage.value = "";
	}
</script>
</html>