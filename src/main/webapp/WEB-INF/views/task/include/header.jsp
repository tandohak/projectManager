<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">   
<link rel="stylesheet"	href="${pageContext.request.contextPath}/resources/css/index.css?a=8"> 
<link rel="stylesheet"	href="${pageContext.request.contextPath}/resources/css/task.css?a=8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>    
	<nav class="navbar navbar-light sticky-top flex-md-nowrap"  id="header"> 
			<ul class="nav navbar-nav">
				 <li>
				 	<a href="#" class="dropdown-toggle nav_addBtn" data-toggle="dropdown">
				 		 <span class="glyphicon glyphicon-plus"></span>
				 	</a> 
				 	<ul class="dropdown-menu">
			          <li><a href="#"></a></li>
			          <li><a href="#"></a></li>
			        </ul>
				 </li> 
			</ul>   
			<form class="navbar-form navbar-left">
			  <div class="input-group">  
			    <input type="text" class="form-control" placeholder="Search">
			    <div class="input-group-btn">
			      <button class="btn btn-default" type="submit">  
			        <i class="glyphicon glyphicon-search"></i>  
			      </button>
			    </div>
			  </div>
			</form>
			<ul class="nav navbar-nav navbar-right" style="margin-right: 0px;">  
				<li>  
					<a href="#" class="dropdown-toggle border_left border_right" data-toggle="dropdown">
						<span class="nav_btn_tit">${workVO.name}</span>  
						<span class="glyphicon glyphicon-chevron-down"></span> 
					</a>  
					<ul class="dropdown-menu">   
			          <li>
			          	<a href="${pageContext.request.contextPath}/task/${workVO.wcode}/workspace?memType=${memVo.memGrade}" class="nav_workspace">
			          		<img class="pic" src="${pageContext.request.contextPath}/resources/img/workspace_icon.png"/>
			          		<span class="nav_inner_wrap"> 
			          			<span class="tit_nav">${workVO.name}</span> 
			          			<span class="cFont_gray">멤버 관리,초대,설정 그 외 더보기...</span> 
			          		</span>
			          	</a>
			          </li>
			           <li>  
				        <div class="nav_innerBox"> 
				        	<div class="input-group">
							    <input type="text" class="form-control" placeholder="워크스페이스 검색"> 
					          	<div class="input-group-btn">  
							      <button class="btn btn-default" type="submit">  
							        <i class="glyphicon glyphicon-search"></i>
							      </button>
							    </div>
							 </div>
							 <ul class="nav_inner_resList">
							 	<c:forEach var="item" items="${memList }"> 
							 		<li> 
							 			<a href="${pageContext.request.contextPath}/task/${item.wcode }" data-wcode="${item.wcode }">
							          		<img class="pic" src="${pageContext.request.contextPath}/resources/img/workspace_icon.png"/>
							          		<span>  
							          			${item.name}
							          		</span>
							          	</a>  
							 		</li>
							 	</c:forEach>
							 </ul>
			          	</div>  
			          </li>
			          <li> 
			          	<a href="#"><span></span>새 워크스페이스 만들기</a>
			          </li>
			        </ul>
				</li>
				<li>     
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" id="userPicWrap"> 
						<c:if test="${login.photoPath=='' || login.photoPath == null}">
							<img id="userPic" class="pic" src="${pageContext.request.contextPath}/resources/img/user_icon.png"/>
						</c:if>      
						<c:if test="${login.photoPath!=''}">
							<img id="userPic" class="pic"  src="${pageContext.request.contextPath}/displayFile?filename=${login.photoPath}"/>
						</c:if>    
					</a>   
					<ul class="dropdown-menu">   
			          <li>
			          	<a href="#" id="nav_userInfo" data-uno="${login.uno}">    
			          		<c:if test="${login.photoPath==''}">
								<img class="pic" src="${pageContext.request.contextPath}/resources/img/user_icon_b.png"/>
							</c:if>      
							<c:if test="${login.photoPath!=''}">
								<img class="pic" src="${pageContext.request.contextPath}/displayFile?filename=${login.photoPath}"/>
							</c:if>  
							<span class="nav_inner_wrap">
				          		<span>${login.username}</span>    
				          		<span class="cFont_gray">${login.email}</span> 
			          		</span>    
			          	</a>    
			          </li>
			          <li><a href="${pageContext.request.contextPath}/user/logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
			        </ul>
				</li>
			</ul>
	</nav>