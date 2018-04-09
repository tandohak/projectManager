<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="include/header.jsp" %>
<link rel="stylesheet"	href="${pageContext.request.contextPath}/resources/css/task_project.css?a=9"> 
<link rel="stylesheet"	href="${pageContext.request.contextPath}/resources/css/modal.css?a=9">  
<%@ include file="include/sideBar.jsp" %>
			<div id="contentWrap">   
			<nav class="navbar navbar-default" style="border-radius:0px;">  
			  <div class="container-fluid" style="padding:0px;"> 
			  <ul class="nav navbar-nav sticky-top"> 
					<li>  
						 <a href="#" class="dropdown-toggle" data-toggle="dropdown">
						 	전체 프로젝트(0) <span class="glyphicon glyphicon-menu-down"></span> 
						 </a>    
						<ul class="dropdown-menu">
					       <li><a href="#">전체 프로젝트(0)</a></li>  
					       <li><a href="#">계획됨(0)</a></li>   
					       <li><a href="#">진행중(0)</a></li>
					       <li><a href="#">완료됨(0)</a></li> 
					       <li><a href="#">보관됨(0)</a></li>  
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
			  </div> 
			</nav>
				<div class="contentBox">
					<div class="projectBox"> 
						<div class="pjToggleBtn"> 
							<a href="#"><span class="glyphicon glyphicon-menu-down"></span> 내 프로젝트 (<span class="pjCnt">0</span>)</a>
						</div> 
						<div class="pjList"> 
							<div class="pj_item">
								<div class="pj_item_header">
									<p><i></i>프로젝트명</p>
									<div class="pj_item_setting">
										<a href="#"><span class="glyphicon glyphicon-cog"></span></a>
									</div>
								</div> 
								<div class='pj_item_footer'>
									<p class="pj_item_state">상태 없음</p>
									<div class="pj_item_progress">
										<div class="pj_item_taskState">
											<span class="percent">0% 완료</span>
											<span class="taskCnt">0/0개 업무</span>
										</div>
										<div class="percentbar">
											<div class="progressBar"></div>
										</div>
									</div>
								</div>
							</div> 
							<div class="pj_item" id="addProjectBtn" data-toggle="modal" data-target="#addProjectModal">
								<p> 
									<span class="glyphicon glyphicon-plus"></span> 새 프로젝트
								</p>  
							</div> 
						</div>  
					</div>
				</div> 
			</div>   
		</div>   
 	</div>    
 	 
 	<!-- Modal -->  
  <div class="modal fade" id="addProjectModal" role="dialog">
    <div class="modal_inner_box" style="">
    	<h2 class="tit_h2" >새 프로젝트</h2>
		<div class="inn_row"> 
			<span class="sTit">제목</span>
			<input type="text"	class="input full" name="name" style="margin-top: 0px" placeholder="예)웹사이트 디자인">
		</div>
		
		<div class="inn_row">  
			<span class="sTit">설명<span class="cFont_light_gray">(선택사항)</span></span>
			<input type="text"	class="input full" name="name" style="margin-top: 0px">
		</div>
		 
		 <div class="inn_row">   
			<span class="sTit">공개 범위 설정</span>
			<div class="visibility">
				<div class="radioWrap">
					<input type="radio" name="visibility" class="visi_radio">
					<div class="txtWrap">
						<span><i></i>비공개</span><br> 
						<span>추가된 멤버만 엑세스 가능</span>
					</div>
				</div>   
				<div class="radioWrap"> 
					<input type="radio" name="visibility" class="visi_radio">
					<div class="txtWrap"> 
						<span><i></i>공개</span><br>
						<span>모든 워크스페이스 멤버 엑세스 가능</span>
					</div>   
				</div> 
			</div> 
		</div>
		
		<div class="inn_row" id="addMemberWrap">   
			<span class="sTit">프로젝트 멤버</span>  
			<div class="mem_add_btn"> 
				<a href="#" class="btnCtm">+</a>
				<ul class="dropdown_menu_setting">
					<li><span class="dropTit">멤버</span> <a href="#" class="closeDropDownBtn"><span class="glyphicon glyphicon-remove"></span></a></li> 
					<li>  
						<div class="input-group">  
						    <input type="text" class="form-control" placeholder="Search">
						    <div class="input-group-btn">
						      <button class="btn btn-default" type="submit">  
						        <i class="glyphicon glyphicon-search"></i>  
						      </button>
						    </div>
						  </div>  
					</li>  
					<li>  
						<ul id="memList"> 
							<c:forEach var="mem" items="${workMembers}">
							<li>  
								<a href="#" class="mem_li" data-uno="${mem.uno }"> 
									<c:if test="${mem.photoPath=='' || mem.photoPath == null}"> 
										<img id="userPic" class="pic" src="${pageContext.request.contextPath}/resources/img/user_icon_b.png" style="width: 25px; height: 25px;"/>
									</c:if>      
									<c:if test="${mem.photoPath!=''}">  
										<img id="userPic" class="pic"  src="${pageContext.request.contextPath}/displayFile?filename=${login.photoPath}" style="width: 25px; height: 25px;"/>
									</c:if> 
									${mem.firstName} ${mem.lastName }  
									<span class="glyphicon glyphicon-ok" <c:if test="${mem.uno == login.uno}">style="display:inline-block;"</c:if> ></span>
								</a> 
							</li>   
							 </c:forEach>
						</ul>
					</li> 
				</ul>  
			</div>
			<div class="addMemberBox"> 
				<div class="addMem_item" data-uno="${login.uno }">${login.username }
					<c:if test="${login.photoPath=='' || login.photoPath == null}"> 
						<img id="userPic" class="pic" src="${pageContext.request.contextPath}/resources/img/user_icon_b.png" style="width: 25px; height: 25px;"/>
					</c:if>      
					<c:if test="${login.photoPath!=''}"> 
						<img id="userPic" class="pic"  src="${pageContext.request.contextPath}/displayFile?filename=${login.photoPath}" style="width: 25px; height: 25px;"/>
					</c:if> 
					<a href="#" class="delMem">x</a>
				</div> 
			</div> 
		</div>        
		       
		<script>   
			$(".mem_add_btn").click(function(e){
				e.preventDefault();    
				$(this).siblings(".dropdown_menu_setting").toggle();  
			}) 
		</script>
		<div class="inn_row"> 
			<a href="#" class="btnCtm" style="width: 25%;">다음:템플릿 선택 →</a> 
		</div>   
    </div>  
  </div>  
</body>  
</html>       