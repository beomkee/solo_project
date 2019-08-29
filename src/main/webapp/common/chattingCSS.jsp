<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="<%=request.getContextPath()%>/concept-master/jquery-3.3.1.js"></script>
<meta charset="UTF-8">
<title>사내 채팅 서비스</title>
</head>
<style>
.messages {
  width: 350px;
  height: 250px;
  margin: 0 auto;
  display: flex;
  flex-direction: column;
  font: 16px/1.3 sans-serif;
  overflow: auto;
  padding: 0;
  background: linear-gradient(rgb(255, 143, 178) 0%, rgb(167, 151, 255) 50%, rgb(0, 229, 255) 100%);
}

.messages:after {
  content: '';
  background: white;
  flex: 1;
  pointer-events: none;
}

.messages li {
  list-style-type: none;
  display: flex;
  flex-direction: row-reverse;
  overflow: hidden;
  border-style: solid;
  border-color: white;
  border-width: 2px 16px 2px 8px;
  flex: 0 0 auto;
}

.messages li.mine {
  flex-direction: row;
  border-right-width: 8px;
  border-left-width: 16px;
}

.messages li:first-child {
  border-top-width: 10px;
}

.messages li:last-child {
  border-bottom-width: 10px;
}

.messages li:before {
  content: '';
  flex: 1;
  background: white;
  pointer-events: none;
}

.messages li span {
  background: #eee;
  padding: 8px;
  position: relative;
}

.messages li span:before {
  content: '';
  position: absolute;
  left: -8px;
  top: -8px;
  bottom: -8px;
  right: -8px;
  border: 8px solid white;
  border-radius: 16px;
  pointer-events: none;
}

.messages li.mine span {
  background: none;
  color: white;
}
button{
  background:#1AAB8A;
  color:#fff;
  border:none;
  position:relative;
  height:20px;
  font-size:1em;
  padding:0 2em;
  cursor:pointer;
  transition:800ms ease all;
  outline:none;
}
button:hover{
  background:#fff;
  color:#1AAB8A;
}
button:before,button:after{
  content:'';
  position:absolute;
  top:0;
  right:0;
  height:2px;
  width:0;
  background: #1AAB8A;
  transition:400ms ease all;
}
button:after{
  right:inherit;
  top:inherit;
  left:0;
  bottom:0;
}
button:hover:before,button:hover:after{
  width:100%;
  transition:800ms ease all;
}
</style>
<body>
<fieldset>
	<ol class="messages" id="messageWindow">
	</ol>
	<input class="form-control-small form-control-sm" style="width: 69%;" id="inputMessage" type="text" />
	<button type="submit" onclick="send()">send</button>
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
		textarea.innerHTML += "<li><span>"
				+ event.data + "</span></li>";
		textarea.scrollTop = textarea.scrollHeight;
	}

	function onOpen(event) {
		textarea.innerHTML += "채팅에 접속했습니다<br>";
		webSocket.send("${user.name}:입장 하였습니다");
	}

	function onError(event) {
		//alert(event.data);   
	}

	function send() {
		var text = $('#inputMessage').val();
		if (text == '') {
			return false;
		}
		textarea.innerHTML += "<li class='mine'><span>"
				+ inputMessage.value
				+ "</span></li>";
		textarea.scrollTop = textarea.scrollHeight;
		webSocket.send("${user.name}:" + inputMessage.value);
		inputMessage.value = "";
	}
</script>
</html>