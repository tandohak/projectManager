<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/index.css?a=2">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/login.css?a=2">

<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
 <script src="${pageContext.request.contextPath}/resources/js/join.js?a=2"></script>
</head>
<body>
	<div class="container-fluid">
		<div id="contentsWrap" class="joinBox"> 
			<div id="content_innerBox">
				<h2 class="tit_h2">회원가입</h2>  
				
				<form action="${pageContext.request.contextPath}/emailAuth" method="post" id="emailPost">
					<input type="email" class="input" name="email" id="email" placeholder="이메일 주소 입력">
					<input type="submit" value="가입하기" class="btn">
				</form>   
 
				<div id="bar" class="row">  
					<span class="bar"><i></i></span> <span class="txt">또는</span> <span
						class="bar"><i></i></span>
				</div>

				<a href="${googleSignIn }" class="btn">Google로 가입하기</a>
				<div class="row">
					<p class="cFont">
						위의 "가입하기" 버튼을 누르면 "TaskManager"의 이용약관와 개인 정보 정책에 동의합니다.
					</p>
				</div>    
			</div>  
		</div>  
	</div>
</body>
</html>
