<%--<html>--%>
<%--<body style="margin: 35px">--%>
<%--<input id="messageField" type="text">--%>
<%--<input onclick="sendMsg();" value="send" type="button">--%>
<%--<div id="msg-box" style="width:500px; height: 400px; background: #eee; overflow:auto;"></div>--%>

<%--<script>--%>
<%--  var webSocket = new WebSocket("ws://localhost:8080/myHandler");--%>
<%--  var msgField = document.getElementById("messageField");--%>
<%--  var divMsg = document.getElementById("msg-box");--%>
<%--  function sendMsg() {--%>
<%--    var msgToSend = msgField.value;--%>
<%--    webSocket.send(msgToSend);--%>
<%--    divMsg.innerHTML += "<div style='color:green'>Client> " + msgToSend +--%>
<%--            "</div>"--%>
<%--    msgField.value = "";--%>
<%--  };--%>
<%--  webSocket.onmessage = function(message) {--%>
<%--    divMsg.innerHTML += "Server> : " + message.data;--%>
<%--  };--%>
<%--  webSocket.onopen = function() {--%>
<%--    console.log("connection opened");--%>
<%--  };--%>
<%--  webSocket.onclose = function() {--%>
<%--    console.log("connection closed");--%>
<%--  };--%>
<%--  webSocket.onerror = function wserror(message) {--%>
<%--    console.log("error: " + message);--%>
<%--  };--%>

<%--  document.getElementById("messageField")--%>
<%--          .addEventListener("keyup", function(event) {--%>
<%--            event.preventDefault();--%>
<%--            if (event.keyCode === 13) {--%>
<%--              sendMsg();--%>
<%--            }--%>
<%--          });--%>
<%--</script>--%>
<%--</body>--%>
<%--</html>--%>