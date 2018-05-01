<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TASK MANAGER</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/index.css?a=a">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/login.css?a=aa">

<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
 <script src="${pageContext.request.contextPath}/resources/js/join.js?a=2"></script>
</head>
<body>
	<div class="container-fluid">
		<div id="contentsWrap" class="joinBox"> 
			<div id="content_innerBox">
				<h2 class="tit_h2">가입하기</h2>   
				<p class="txtCenter cFont_gray">${wvo.name}에 참여합니다.</p>  
				
				<form action="${pageContext.request.contextPath}/emailAuth" method="post" id="emailPost">
					<input type="email" class="input" name="email" id="email" placeholder="이메일 주소 입력">
					<input type="submit" value="가입하기" class="btn">
				</form>
 				
				<div id="bar" class="row">  
					<span class="bar"><i></i></span> <span class="txt">또는</span> <span
						class="bar"><i></i></span>
				</div>

				<a href="${googleSignIn }" class="btn">Google로 가입하기</a> 
				 
				<div class="row" style="font-size:13px;"> 
					<p class="cFont_gray txtCenter">
						이미 계정이 있으십니까? <a href="#" class="cFont" id="loginOpen">로그인</a>
					</p> 
				</div>    
			</div>  
		</div>
		
		<div id="contentsWrap" class="loginBox" style="display: none;">
			<div id="content_innerBox">
				<h2 class="tit_h2">로그인</h2>
				<p class="txtCenter cFont_gray">${wvo.name}에 참여합니다.</p>  
				
				<form action="${pageContext.request.contextPath}/user/loginPost" method="post">
					<input type="email" class="input" name="email" id="email" placeholder="이메일 주소">
					<input type="password" class="input" name="password" id="pwd" placeholder="비밀번호">
					<input type="submit" value="로그인" class="btn">
				</form>  
				
				<c:if test="${res!=null }">  
					<div class="row">
						<c:if test="${res == 'notExtistId'}">
							<span class="wranTxt">아이디가 존재하지 않습니다.</span>
						</c:if>
						<c:if test="${res == 'wrongPass'}">
							<span class="wranTxt">비밀번호가 같지 않습니다.</span>
						</c:if>
					</div>
   				</c:if>
				
				<div id="bar" class="row">  
					<span class="bar"><i></i></span> <span class="txt">또는</span> <span
						class="bar"><i></i></span>
				</div>

				<a href="${googleSignIn }" class="btn">Google로 로그인</a>
				<div id="linkGroup" class="row">
					<a href="#" class="link_a">비밀번호 변경</a>
				</div>    
			</div>  
		</div>  
		
		<script>
			$("#loginOpen").click(function(e){
				e.preventDefault();
				$(".loginBox").css("display","block");
				$(".joinBox").css("display","block");
			})
		</script>
	</div>
</body>
</html>
