<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/index.css?A=2">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/login.css?a=2">
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
				<form>
					<input type="email" class="input" id="email" placeholder="이메일 주소">
					<input type="password" class="input" id="pwd" placeholder="비밀번호">
					<input type="submit" value="로그인" class="btn">
				</form>
   
				<div id="bar" class="row">  
					<span class="bar"><i></i></span> <span class="txt">또는</span> <span
						class="bar"><i></i></span>
				</div>

				<a href="${googleSignIn }" class="btn">Google로 로그인</a>
				<div id="linkGroup" class="row">
					<a href="join" class="link_a">회원가입</a> 
					<a href="#" class="link_a">비밀번호 변경</a>
				</div>    
			</div>  
		</div>
	</div>     
</body>
</html>
