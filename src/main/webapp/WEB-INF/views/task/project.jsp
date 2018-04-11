<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="include/header.jsp" %>
<link rel="stylesheet"	href="${pageContext.request.contextPath}/resources/css/task_project.css?a=16"> 
<link rel="stylesheet"	href="${pageContext.request.contextPath}/resources/css/modal.css?a=16">  
<script src="${pageContext.request.contextPath}/resources/js/task_project.js?a=6"></script> 
<script> 
	var wcode = "${wcode}";  
	var loginMem = { 
			 mno : ${loginMem.mno },
			 firstName : "${loginMem.firstName}" ,
			 lastName :  "${loginMem.lastName }" ,
			 photoPath : "${loginMem.photoPath}" 
	};    
	  
</script>
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
							<c:if test="${projectList != null }">
								<c:forEach var="item" items="${projectList }">
									<div class="pj_item">
										<div class="pj_item_header">
											<p><i></i>${item.pj.title }</p> 
											<div class="pj_item_setting"> 
												<a href="#" class="setting_pj_btn" data-pno="${item.pj.pno }"><span class="glyphicon glyphicon-cog"></span></a>
											</div>  
										</div>
										<div class='pj_item_footer'>
											<p class="pj_item_state">상태 없음</p>
											<div class="pj_item_progress">
												<div class="pj_item_taskState">
													<span class="percent">0% 완료</span>
													<span class="taskCnt">${item.countFinish}/${item.countAll}개 업무</span>
												</div> 
												<div class="percentbar"> 
													<div class="progressBar" style="width: ${(item.countFinish/item.countAll)*100}%;"></div>
												</div>
											</div>
										</div>   
									</div>
								</c:forEach>  
							</c:if>
							<div class="pj_item" id="addProjectBtn" data-toggle="modal" data-target="#addProjectModal">
								<p> 
									<span class="glyphicon glyphicon-plus"></span> 새 프로젝트
								</p>  
							</div> 
							<script id="pj_item_template" type="text/x-handlerbars-template">
								<div class="pj_item">
									<div class="pj_item_header">
										<p><i></i>{{title}}</p> 
										<div class="pj_item_setting"> 
											<a href="#" class="setting_pj_btn" data-pno="{{pno}}"><span class="glyphicon glyphicon-cog"></span></a>
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
							</script> 
						</div>  
					</div>
				</div> 
				
				<!-- 프로젝트 설정 -->
			 	<div class="sideSetting">
			 		<a href="#" class="sideSettingClose"><span  class="glyphicon glyphicon-remove"></span></a>
			 		 
			 			<div class="settingHeader"> 
			 				<div class="set_title inputText">
			 					<input type="text" value="프로젝트명" readonly="readonly">
			 					<span class="glyphicon glyphicon-pencil"></span>
			 					<p>
				 					#7 작성자 TE ST • 작성일 4월 11일 • 몇 초 전에 업데이트됨
				 				</p> 
			 				</div>  
			 				 
			 				  
			 				<ul class="toggleWrap">
			 					<li class="selectSetting"><a href="#">설정</a></li>
			 					<li><a href="#">모든 활동</a></li>
			 				</ul>
			 			</div> 
			 			<div class="settingBody">
			 				<div class="settingContent"> 
			 					<div class="settingBlock">   
				 					<p><span class="glyphicon glyphicon-text-size" style="font-size: 10px;"></span> 설명 </p> 
				 					<div class="inputText">
					 					<input type="text" value="프로젝트명" readonly="readonly">
					 					<span class="glyphicon glyphicon-pencil"></span>
					 				</div>    
			 					</div>
			 					<div class="settingBlock">   
				 					<div class="left_block_tit">
				 						<p>프로젝트 상태</p>
				 					</div>
				 					<div class="right_block_con">
				 						<select>
				 						</select>
				 					</div>
			 					</div>
			 				</div>
			 			</div>
			 	</div>
			</div>   
		</div>   		
 	</div>    
 	  
 	
 	 
 	<!-- Modal -->  
  <div class="modal fade" id="addProjectModal" role="dialog">
    <div class="modal_inner_box">
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
					
				</ul>  
			</li>      
		</ul>  
		<div class='modalWrap'>
    	<div class="boxWrap"> 
		   <div class="boxInnerWrap">   	 
		    	<h2 class="tit_h2" >새 프로젝트</h2>
				<div class="inn_row"> 
					<span class="sTit">제목</span>
					<input type="text"	class="input full" id="project_title" style="margin-top: 0px" placeholder="예)웹사이트 디자인">
				</div>
				
				<div class="inn_row">  
					<span class="sTit">설명<span class="cFont_light_gray">(선택사항)</span></span>
					<input type="text"	class="input full" id="project_explan" style="margin-top: 0px">
				</div>
				 
				 <div class="inn_row">   
					<span class="sTit">공개 범위 설정</span>
					<div class="visibility">
						<div class="radioWrap">
							<input type="radio" name="visibility" class="visi_radio" checked="checked" value="0">
							<div class="txtWrap"> 
								<span><i></i>비공개</span><br> 
								<span>추가된 멤버만 엑세스 가능</span>
							</div>
						</div>   
						<div class="radioWrap">   
							<input type="radio" name="visibility" class="visi_radio" value="1">
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
					</div> 
					<div class="addMemberBox">     
						<%-- <div class="addMem_item" data-mno="${loginMem.mno }">${loginMem.firstName} ${loginMem.lastName }  
							<c:if test="${login.photoPath=='' || login.photoPath == null}"> 
								<img id="userPic" class="pic" src="${pageContext.request.contextPath}/resources/img/user_icon_b.png" style="width: 25px; height: 25px;"/>
							</c:if>      
							<c:if test="${login.photoPath!=''}"> 
								<img id="userPic" class="pic"  src="${pageContext.request.contextPath}/displayFile?filename=${login.photoPath}" style="width: 25px; height: 25px;"/>
							</c:if> 
							<a href="#" class="delMem">x</a>
						</div> --%>
					</div>
			</div>
			<script id="template" type="text/x-handlerbars-template">
				{{#each.}}
				<li>  
					<a href="#" class="mem_li" data-mno="{{mno}}"  data-firstName="{{firstName }}" data-lastName="{{lastName }}" data-photoPath="{{photoPath }}"> 
						<img id="userPic" class="pic" src="${pageContext.request.contextPath}/{{checkPhotoPath photoPath}}" style="width: 25px; height: 25px;"/>
						{{firstName}} {{lastName}}   
						<span class="glyphicon glyphicon-ok" style="{{checkSelectMember mno}}"></span>
					</a>  
				</li> 
				{{/each}}   
			</script>       
			<script id="addMemTemplate" type="text/x-handlerbars-template">
				<div class="addMem_item" data-mno="{{mno }}" data-firstName="{{firstName }}" data-lastName="{{lastName }}" data-photoPath="{{photoPath }}">{{firstName}} {{lastName }}  
					<img id="userPic" class="pic" src="${pageContext.request.contextPath}/{{checkPhotoPath photoPath}}" style="width: 25px; height: 25px;"/>
					<a href="#" class="delMem">x</a>
				</div> 
			</script>
			<div class="inn_row"> 
				<a href="#" class="btnCtm" id="addPjNextBtn" style="width: 25%;">다음:템플릿 선택 →</a> 
			</div> 
		</div>  
		<div class="boxInnerWrap">
			 	<h2 class="tit_h2" >프로젝트 템플릿 선택</h2>
			 	
				<div class="inn_row">  
					<div class="choose_box">
						<div class="choose_img_box selected"><img src='${pageContext.request.contextPath}/resources/img/empty_b.png'/></div>
						<span class="sTit">없음</span> 
					</div>   
					<div class="choose_box"> 
						<div class="choose_img_box"><img src='${pageContext.request.contextPath}/resources/img/day.png'/></div>
						<span class="sTit">평일</span> 
					</div>
					<div class="choose_box"> 
						<div class="choose_img_box"><img src='${pageContext.request.contextPath}/resources/img/person.png'/></div>
						<span class="sTit">개인</span>
					</div> 
					<div class="choose_box"> 
						<div class="choose_img_box"><img src='${pageContext.request.contextPath}/resources/img/department.png'/></div>
						<span class="sTit">부서</span> 
					</div> 
					<div class="choose_box"> 
						<div class="choose_img_box"><img src='${pageContext.request.contextPath}/resources/img/kanban.png'/></div>
						<span class="sTit">칸반(Kanban)</span>
					</div> 
				</div>
				
				<div class="inn_row">
					<img id="prvImg" src='${pageContext.request.contextPath}/resources/img/preview-blank-f9e6c665.png' style="width: 583.188px;">
				</div>  
				<div class="inn_row modal_btn_wrap">  
					<a href="#" id="addPjPrevBtn">← 뒤로</a>
					<a href="#" class="btnCtm"  id="makeProject" style="width: 25%;">프로젝트 만들기</a> 
				</div>  
		</div>    
		<div style="clear:both;"></div> 
		</div>
   		</div>  
    </div>
  </div>   
</body>  
</html>       