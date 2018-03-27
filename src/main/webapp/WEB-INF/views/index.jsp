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
</head>
<body>
	<div class="container-fluid">
		<div id="loginBox">
			<div id="loginformWrap">
				<h2 class="tit_h2">로그인</h2>
				<form>
					<input type="email" class="form-control" id="email"  
						placeholder="이메일 주소"> <input type="password"  
						class="form-control" id="pwd" placeholder="비밀번호">

					<input type="submit" value="로그인">
					
					<span class="bar"></span> <span>또는</span> <span class="bar"></span>

					<a href="#" class="btn">로그인</a> 
				</form>
			</div>
		</div>
	</div>
</body>
</html>
