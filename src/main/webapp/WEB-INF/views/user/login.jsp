<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TASK MANAGER</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/index.css?A=a">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/login.css?a=aa">
<script
  src="https://code.jquery.com/jquery-3.3.1.slim.js"
  integrity="sha256-fNXJFIlca05BIO2Y5zh1xrShK3ME+/lYZ0j+ChxX2DA="
  crossorigin="anonymous"></script>
</head>
<body>
	<div class="container-fluid"> 
		<div id="contentsWrap" class="loginBox">
			<div id="content_innerBox">
				<h2 class="tit_h2">로그인</h2>
				<form action="loginPost" method="post">
					<input type="email" class="input" name="email" id="email" placeholder="이메일 주소">
					<input type="password" class="input" name="password" id="pwd" placeholder="비밀번호">
					<input type="submit" value="로그인" class="btn">
				</form>  
				
				<c:if test="${res!=null }">
					<div class="row">
						<c:if test="${res == 'notExtistId'}">
							<span class="wranTxt">아이디/비밀번호를 확인하세요.</span>
						</c:if>
						<c:if test="${res == 'wrongPass'}">
							<span class="wranTxt">아이디/비밀번호를 확인하세요.</span>
						</c:if>
					</div>
   				</c:if>
				
				<div id="bar" class="row">  
					<span class="bar"><i></i></span> <span class="txt">또는</span> <span
						class="bar"><i></i></span>
				</div>

				<a href="${googleSignIn }" class="btn">Google로 로그인</a>
				<div id="linkGroup" class="row">
					<a href="join" class="link_a">회원가입</a> 
					<a href="#" class="link_a" style="display: none;">비밀번호 변경</a>
				</div>      
			</div>  
		</div>
	</div>      
</body>
</html>
