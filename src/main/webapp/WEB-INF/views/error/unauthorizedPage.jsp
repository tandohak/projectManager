<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/index.css?A=3">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/login.css?a=3">
<script
  src="https://code.jquery.com/jquery-3.3.1.slim.js"
  integrity="sha256-fNXJFIlca05BIO2Y5zh1xrShK3ME+/lYZ0j+ChxX2DA="
  crossorigin="anonymous"></script>
</head>
<body>
	<div class="container-fluid"> 
		<div id="contentsWrap" class="loginBox">
			<div id="content_innerBox">
					<div class="row">
						<span class="wranTxt">권한이 없는 페이지입니다.</span>
						<a href="${pageContext.request.contextPath }/task/">홈으로 돌아가기</a>
					</div>
			</div>  
		</div>
	</div>     
</body>
</html>
