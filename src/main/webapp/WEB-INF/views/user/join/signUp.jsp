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
	href="${pageContext.request.contextPath}/resources/css/index.css?a=5">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/login.css?a=5"> 
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
 <script src="${pageContext.request.contextPath}/resources/js/join.js?a=4"></script>
</head>  
<body>
	<div class="container">
		<input type="file" id="img_input" style="display: none;">
		<div id="contentsWrap" class="profileBox">
			<div id="content_innerBox">  
				<h2 class="tit_h2">프로필을 등록해 주세요.</h2>

				<div id="row">
					<div id="photoBox">
						<img src="#" id="photo"> <span>사진 업데이트</span>
					</div>
				</div>
					<input type="email" class="input" name="email" id="email"
						value="${user.email}" readonly="readonly">  

					<div class="row">
						<span class="sTit half">성</span> <span class="sTit half">이름</span>
						<input type="text" class="input half" name="firstName" id="firstName" placeholder="성" value="${user.firstName}"> 
						<input type="text" class="input half" name="lastName" id="lastName" placeholder="이름" value="${user.lastName}">
					</div>
  					
  					<c:if test="${key != null}">
						<div class="row"> 
							<span class="sTit">비밀번호</span> 
							<input type="password" class="input full" name="password" id="password" placeholder="비밀번호"> 
							<input	type="password" class="input full" name="rePassword" id="rePassword" placeholder="비밀번호 확인">
						</div>
					</c:if>  
					<input type="button" value="다음 : 워크스페이스 만들기  >" class="btn" onclick="${key!=null ? 'proBtnEmail()' : 'proBtnGoogle()'}">
					
					<div class="row txtCenter">  
						<a  href="${pageContext.request.contextPath}/user/login" class="txt_Btn">뒤로 가기</a> 
					</div>  
			</div>
		</div>  
		
		<div id="contentsWrap" class="workspaceBox">
			<div id="content_innerBox">
				<h2 class="tit_h2" style="margin-bottom: 10px;">워크 스페이스 생성하세요.</h2>
					<div class="row">
						<p class="cFont" style="text-align: center">
							워크스페이스는 회사, 팀, 부서  또는 개인이 될 수 있습니다.
						</p>
					</div>
  
					<div class="row">
						<span class="sTit">워크스페이스 이름</span> 
						<input	type="text" class="input full" name="workspaceName"  id="workspaceName" placeholder="예 ) ABC 주식회사, 개발 팀..">
					</div>
  
					<input type="button" value="다음 : 팀원 초대하기  >" class="btn work_btn">
					 
					<div class="row txtCenter">  
						<a  href="#" class="txt_Btn  work_backBtn">뒤로 가기</a> 
					</div>  
			</div> 
		</div> 

		<div id="contentsWrap" class="inviteBox">
			<div id="content_innerBox">
				<h2 class="tit_h2" >팀원을 초대하세요.</h2>

				<div class="row">
					<p class="cFont" style="margin:0; margin-bottom: 5px; color:rgba(0,0,0,0.6);">링크를 공유하여 <span id="wname"></span>에 팀원을 초대하세요.</p>
					<input type="text"	class="input full" name="inviteLink" id="inviteLink" placeholder="inviteLink 생성" readonly="readonly">
				</div>
				
				<div id="bar" class="row"> 
					<span class="bar"><i></i></span> 
					<span class="txt">또는</span> 
					<span class="bar"><i></i></span>
				</div>
				
				<div class="row txtCenter" >
					<a  href="#" class="txt_Btn" onclick="inviteEmailBtn()">이메일로 초대하기</a> 
				</div>
				
				<div class="row" id="inviteEmailBox">
					<div class="row" id="inviteInputWrap">
						<div class="invite_item">
							<input type="email" class="input full" placeholder="초대할 이메일을 입력해주세요.">
						</div>
						
					</div>
					<div class="row">  
						<a href="#" class="btn half" onclick="addInput()">추가</a>  
						<a href="#" class="btn half" onclick="inviteEamilTrans()">전송</a>    
					</div>   
				</div>
				
				<a href="#" type="button" class="btn" id="startTask">TaskManager 시작하기  ></a>
			</div>  
		</div>   
	</div>
</body>
</html>
