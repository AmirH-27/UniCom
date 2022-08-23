<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Hello WebSocket</title>--%>
<%--    &lt;%&ndash;    <link href="/webjars/bootstrap/css/bootstrap.min.css" rel="stylesheet">&ndash;%&gt;--%>
<%--    &lt;%&ndash;    <link href="/main.css" rel="stylesheet">&ndash;%&gt;--%>
<%--    &lt;%&ndash;    <script src="/webjars/jquery/jquery.min.js"></script>&ndash;%&gt;--%>
<%--    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.0.3/sockjs.js"></script>--%>
<%--    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>--%>
<%--    <script type="text/javascript">--%>

<%--        var stompClient = null;--%>

<%--        function setConnected(connected) {--%>
<%--            $("#connect").prop("disabled", connected);--%>
<%--            $("#disconnect").prop("disabled", !connected);--%>
<%--            if (connected) {--%>
<%--                $("#conversation").show();--%>
<%--            } else {--%>
<%--                $("#conversation").hide();--%>
<%--            }--%>
<%--            $("#greetings").html("");--%>
<%--        }--%>

<%--        function connect() {--%>
<%--            var socket = new SockJS('/ws');--%>
<%--            stompClient = Stomp.over(socket);--%>
<%--            stompClient.connect({}, function (frame) {--%>
<%--                setConnected(true);--%>
<%--                console.log('Connected: ' + frame);--%>
<%--                stompClient.subscribe('/topic/messages', function (greeting) {--%>
<%--                    showGreeting(JSON.parse(greeting.body).content);--%>
<%--                });--%>
<%--            });--%>
<%--        }--%>

<%--        function disconnect() {--%>
<%--            if (stompClient !== null) {--%>
<%--                stompClient.disconnect();--%>
<%--            }--%>
<%--            setConnected(false);--%>
<%--            console.log("Disconnected");--%>
<%--        }--%>

<%--        function sendName() {--%>
<%--            stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));--%>
<%--        }--%>

<%--        function showGreeting(message) {--%>
<%--            $("#greetings").append("<tr><td>" + message + "</td></tr>");--%>
<%--        }--%>

<%--        $(function () {--%>
<%--            $("form").on('submit', function (e) {--%>
<%--                e.preventDefault();--%>
<%--            });--%>
<%--            $("#connect").click(function () {--%>
<%--                connect();--%>
<%--            });--%>
<%--            $("#disconnect").click(function () {--%>
<%--                disconnect();--%>
<%--            });--%>
<%--            $("#send").click(function () {--%>
<%--                sendName();--%>
<%--            });--%>
<%--        });--%>
<%--    </script>--%>

<%--</head>--%>
<%--<body>--%>
<%--<noscript><h2 style="color: #ff0000">Seems your browser doesn't support Javascript! Websocket relies on Javascript being--%>
<%--    enabled. Please enable--%>
<%--    Javascript and reload this page!</h2></noscript>--%>
<%--<div id="main-content" class="container">--%>
<%--    <div class="row">--%>
<%--        <div class="col-md-6">--%>
<%--            <form class="form-inline">--%>
<%--                <div class="form-group">--%>
<%--                    <label for="connect">WebSocket connection:</label>--%>
<%--                    <button id="connect" class="btn btn-default" type="submit">Connect</button>--%>
<%--                    <button id="disconnect" class="btn btn-default" type="submit" disabled="disabled">Disconnect--%>
<%--                    </button>--%>
<%--                </div>--%>
<%--            </form>--%>
<%--        </div>--%>
<%--        <div class="col-md-6">--%>
<%--            <form class="form-inline">--%>
<%--                <div class="form-group">--%>
<%--                    <label for="name">What is your name?</label>--%>
<%--                    <input type="text" id="name" class="form-control" placeholder="Your name here...">--%>
<%--                </div>--%>
<%--                <button id="send" class="btn btn-default" type="submit">Send</button>--%>
<%--            </form>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    <div class="row">--%>
<%--        <div class="col-md-12">--%>
<%--            <table id="conversation" class="table table-striped">--%>
<%--                <thead>--%>
<%--                <tr>--%>
<%--                    <th>Greetings</th>--%>
<%--                </tr>--%>
<%--                </thead>--%>
<%--                <tbody id="greetings">--%>
<%--                </tbody>--%>
<%--            </table>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>

<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%--<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>--%>
<%--<%@ page isELIgnored="false" %>--%>

<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Hello WebSocket</title>--%>
<%--    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.0.3/sockjs.js"></script>--%>
<%--    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>--%>
<%--    <script type="text/javascript">--%>
<%--        var stompClient = null;--%>

<%--        function setConnected(connected) {--%>
<%--            document.getElementById('connect').disabled = connected;--%>
<%--            document.getElementById('disconnect').disabled = !connected;--%>
<%--            document.getElementById('conversationDiv').style.visibility = connected ? 'visible' : 'hidden';--%>
<%--            document.getElementById('response').innerHTML = '';--%>
<%--        }--%>

<%--        function connect() {--%>
<%--            var socket = new SockJS('${pageContext.request.contextPath}/ws');--%>
<%--            stompClient = Stomp.over(socket);--%>
<%--            stompClient.connect({}, function(frame) {--%>
<%--                setConnected(true);--%>
<%--                console.log('Connected: ' + frame);--%>
<%--                stompClient.subscribe('${pageContext.request.contextPath}/topic/messages', function(greeting){--%>
<%--                    console.log(greeting);--%>
<%--                    showGreeting(JSON.parse(greeting.body).content);--%>
<%--                });--%>
<%--            });--%>
<%--        }--%>

<%--        function disconnect() {--%>
<%--            if (stompClient != null) {--%>
<%--                stompClient.disconnect();--%>
<%--            }--%>
<%--            setConnected(false);--%>
<%--            console.log("Disconnected");--%>
<%--        }--%>

<%--        function sendName() {--%>
<%--            var name = document.getElementById('name').value;--%>
<%--            stompClient.send("${pageContext.request.contextPath}/app/ws", {}, JSON.stringify({ 'name': name }));--%>
<%--        }--%>

<%--        function showGreeting(message) {--%>
<%--            var response = document.getElementById('response');--%>
<%--            var p = document.createElement('p');--%>
<%--            p.style.wordWrap = 'break-word';--%>
<%--            p.appendChild(document.createTextNode(message));--%>
<%--            response.appendChild(p);--%>
<%--        }--%>
<%--    </script>--%>
<%--</head>--%>
<%--<body onload="disconnect()">--%>
<%--<noscript><h2 style="color: #ff0000">Seems your browser doesn't support Javascript! Websocket relies on Javascript being enabled. Please enable--%>
<%--    Javascript and reload this page!</h2></noscript>--%>
<%--<div>--%>
<%--    <div>--%>
<%--        <button id="connect" onclick="connect();">Connect</button>--%>
<%--        <button id="disconnect" disabled="disabled" onclick="disconnect();">Disconnect</button>--%>
<%--    </div>--%>
<%--    <div id="conversationDiv">--%>
<%--        <label>What is your name?</label><input type="text" id="name" />--%>
<%--        <button id="sendName" onclick="sendName();">Send</button>--%>
<%--        <p id="response"></p>--%>
<%--    </div>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>

<!DOCTYPE html>
<html>
<head>
    <title>Hello WebSocket</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.0.3/sockjs.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
        <script type="text/javascript">
            var stompClient = null;

            function setConnected(connected) {
                $("#connect").prop("disabled", connected);
                $("#disconnect").prop("disabled", !connected);
                if (connected) {
                    $("#conversation").show();
                }
                else {
                    $("#conversation").hide();
                }
                $("#greetings").html("");
            }

            function connect() {
                var socket = new SockJS('/gs-guide-websocket');
                stompClient = Stomp.over(socket);
                stompClient.connect({}, function (frame) {
                    setConnected(true);
                    console.log('Connected: ' + frame);
                    stompClient.subscribe('/topic/greetings', function (greeting) {
                        showGreeting(JSON.parse(greeting.body).content);
                    });
                });
            }

            function disconnect() {
                if (stompClient !== null) {
                    stompClient.disconnect();
                }
                setConnected(false);
                console.log("Disconnected");
            }

            function sendName() {
                stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
            }

            function showGreeting(message) {
                $("#greetings").append("<tr><td>" + message + "</td></tr>");
            }

            $(function () {
                $("form").on('submit', function (e) {
                    e.preventDefault();
                });
                $( "#connect" ).click(function() { connect(); });
                $( "#disconnect" ).click(function() { disconnect(); });
                $( "#send" ).click(function() { sendName(); });
            });
        </script>
</head>
<body>
<noscript><h2 style="color: #ff0000">Seems your browser doesn't support Javascript! Websocket relies on Javascript being
    enabled. Please enable
    Javascript and reload this page!</h2></noscript>
<div id="main-content" class="container">
    <div class="row">
        <div class="col-md-6">
            <form class="form-inline">
                <div class="form-group">
                    <label for="connect">WebSocket connection:</label>
                    <button id="connect" class="btn btn-default" type="submit">Connect</button>
                    <button id="disconnect" class="btn btn-default" type="submit" disabled="disabled">Disconnect
                    </button>
                </div>
            </form>
        </div>
        <div class="col-md-6">
            <form class="form-inline">
                <div class="form-group">
                    <label for="name">What is your name?</label>
                    <input type="text" id="name" class="form-control" placeholder="Your name here...">
                </div>
                <button id="send" class="btn btn-default" type="submit">Send</button>
            </form>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <table id="conversation" class="table table-striped">
                <thead>
                <tr>
                    <th>Greetings</th>
                </tr>
                </thead>
                <tbody id="greetings">
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>