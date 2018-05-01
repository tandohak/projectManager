<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/index.css?a=a">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/login.css?a=a">
</head>
<script
  src="https://code.jquery.com/jquery-3.3.1.slim.js"
  integrity="sha256-fNXJFIlca05BIO2Y5zh1xrShK3ME+/lYZ0j+ChxX2DA="
  crossorigin="anonymous"></script>
<body>
	<div class="container-fluid">
		<div id="contentsWrap" class="joinBox">
			<div id="loginWrap">
				<h2 class="tit_h2">${email}</h2> 
				<h3 class="tit_h2"> 주소로 이메일을 보냈습니다.</h3> 
				<form action="${pageContext.request.contextPath}/emailAuth" method="post" id="f1">
					<p style="text-align: center;">이메일이 도착하지 않았습니까? 
					<a href="" class="link_a" id="retransmission">다시 보내기</a></p>
					<input type="hidden" class="input" name="email" id="email" value="${email}">
				</form> 
			</div>  
		</div>   
	</div>  
	<script>
		$("#retransmission").click(function(e){
			e.preventDefault();
			$("#f1").submit();
		});
	</script>  
</body>
</html>
