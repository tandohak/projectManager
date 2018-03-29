<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/index.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/login.css">
</head>
<body>
	<div class="container-fluid">
		<div id="loginBox">
			<div id="loginWrap">
				<h2 class="tit_h2">${email}</h2> 
				<h3 class="tit_h2"> 주소로 이메일을 보냈습니다.</h2> 
				<form action="${pageContext.request.contextPath}/emailAuth" method="post" id="f1">
					<p style="text-align: center;">이메일이 도착하지 않았습니까? <a href="join" class="link_a" id="retransmission">다시 보내기</a></p>
					<input type="hidden" class="input" name="email" id="email" value="${email}">
				</form> 
			</div>  
		</div>  
	</div>
	<script>
		$("#retransmission").click(function(){
			$("#f1").submit();
		})
	</script>
</body>
</html>
