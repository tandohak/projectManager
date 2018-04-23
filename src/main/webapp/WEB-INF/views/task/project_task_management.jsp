<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="include/header.jsp"%>  
<link rel="stylesheet"	href="${pageContext.request.contextPath}/resources/css/task_project_select.css?a=xx">
<link rel="stylesheet"	href="${pageContext.request.contextPath}/resources/css/task_setting.css?a=xx22">   
<link rel="stylesheet"	href="${pageContext.request.contextPath}/resources/css/modal.css?a=5xx">           
<link rel="stylesheet"	href="${pageContext.request.contextPath}/resources/css/bootstrap-datepicker3.min.css?a=xx">  
<script src="${pageContext.request.contextPath}/resources/js/bootstrap-datepicker.min.js?a=xx"></script>      
<script src="${pageContext.request.contextPath}/resources/js/bootstrap-datepicker.kr.min.js?b=xx"></script>          
<script	src="${pageContext.request.contextPath}/resources/js/SimpleDateFormat.js"></script>                 
<script src="${pageContext.request.contextPath}/resources/js/task_project_select.js?b=52aa222a2"></script>        
<script src="${pageContext.request.contextPath}/resources/js/task_project_setting.js?b=522a2aa222"></script>  
<script src="${pageContext.request.contextPath}/resources/js/handlerbars_registerHelper.js?b=a2a2a222"></script>     
<script src="${pageContext.request.contextPath}/resources/js/jquery-ui.min.js"></script>    
<script>                 
	var wcode = "${wcode}";                  
	var loginMem = {                         
			 mno : ${loginMem.mno },        
			 firstName : "${loginMem.firstName}" ,          
			 lastName :  "${loginMem.lastName }" ,     
			 photoPath : "${loginMem.photoPath}" ,   
			 memAssGrade : "${loginMem.memGrade}",
			 memGrade : "${loginMem.memGrade}",
			 massno : ${loginMem.massno }
	};                 
	var pno = ${projectVO.pno};      
</script>   
<%@ include file="include/sideBar.jsp"%>           
<div id="contentWrap">      
		<nav class="navbar navbar-default"     
			style="border-radius: 0px; margin-bottom: 0px;"> 
			<div class="container-fluid" style="padding: 0px;">
				<ul class="nav navbar-nav navbar-left">
					<li><a href="#" data-toggle="dropdown"> <span
							class="glyphicon glyphicon-menu-left"></span>
					</a></li>
	   
					<li class="navbar-text"><strong  id="pj_title" style="color: #333;">${projectVO.title }</strong>
					</li>   
				</ul>      
	 			   
				<ul class="nav navbar-nav navbar-left" id="nav_task_tabs" style="margin-left: 34.5%;">       
					<li class="tab_active"><a href="${pageContext.request.contextPath }/task/${wcode}/project/${projectVO.pno}">업무 </a></li>
					<li><a href="${pageContext.request.contextPath }/task/${wcode}/project/${projectVO.pno}/timeline" > 타임라인 </a></li> 
					<li><a href="${pageContext.request.contextPath }/task/${wcode}/project/${projectVO.pno}/analytics" > 분석 </a></li>
					<li><a href="${pageContext.request.contextPath }/task/${wcode}/project/${projectVO.pno}" > 파일 </a></li>
				</ul>  
				  
				<ul class="nav navbar-nav navbar-right" style="margin-right: 0px;">
					<li><a href="#" id='project_setting_btn' data-pno='${projectVO.pno }'> <span
							class="glyphicon glyphicon-cog"></span>
					</a></li>   
				</ul>
			</div>  
		</nav>    	
	<div id="container">  
		<!-- 업무 리스트 시작 --> 
		<div class="contentBox">  
			<div class="taskBox_wrap"> 
				<c:if test = "${taskList != null }">  
				<c:forEach var="item" items="${taskList }">   
			 	<div class="taskBox" data-order="${item.list_order}" data-tlno="${item.tlno}">
			 		<div class="taskBox_header">  
			 			<div class="taskBox_header_wrap">
				 			<input type="text" readonly="readonly" value="${item.name }" class="taskList_name">	
				 			<a href="#" class="add_task_btn"><span class="glyphicon glyphicon-plus"></span></a>  
				 			<div class="dropdown">
				 				<a href="#" class="dropdown-toggle" data-toggle="dropdown">
				 					<span class="glyphicon glyphicon-option-vertical"></span>
				 				</a>     
							    <ul class="dropdown-menu" style="left:-132px;"> 
							      <li><a href="#" class="deleteListBtn" data-tlno="${item.tlno}">리스트 삭제</a></li>
							    </ul>    
							 </div>    
						 </div>        
						 <div class="taskBox_header_addTask" style="display: none;"> 
						 	<textarea rows="1" cols="36"></textarea>  
						 	<div class="taskBox_header_addBtnWrap"> 
						 		<a href="#" class="addMemberDropMenu">       
						 			<span class="glyphicon glyphicon-user">
						 				<i class="counter">1</i> 
						 			</span>    
						 		</a>  
						 		<a href="#" class="addDatepickerOpen">
						 			<span class="glyphicon glyphicon-calendar"></span> 
						 			<input type="text" readonly="readonly" class="task_endDate">
						 		</a>    
						 	</div>
						 	<div class="taskBox_header_addBtnWrap">
						 		 <button class="add_task_cancle">취소</button>  
						 		 <button class="add_task_make" data-tlno="${item.tlno}">만들기</button>
						 	</div>
						 </div>          
			 		</div>
			 		<c:if test="${tasks !=null}">        
			 			<% int length = 0; %>  
			 			<% boolean finished = false; %> 
			 			
					 	<div class="taskBox_body">
					 	<div class="processing_task_box">
			 			<c:forEach items="${tasks }" var="task">   
			 			<c:if test="${task.tlno == item.tlno }">
			 				<c:if test='${task.status == 0}'>
			 					<% length++; %> 
			 					<c:choose>
			 						<c:when test="${task.colorLabel == 'purple' }">
			 							<c:set var="labelColor" value="task_label_purple" />
			 						</c:when>
			 						<c:when test="${task.colorLabel == 'blue' }">
			 							<c:set var="labelColor" value="task_label_blue" />
			 						</c:when>
			 						<c:when test="${task.colorLabel == 'skyblue' }">
			 							<c:set var="labelColor" value="task_label_skyblue" />
			 						</c:when>
			 						<c:when test="${task.colorLabel == 'green' }">
			 							<c:set var="labelColor" value="task_label_green" />
			 						</c:when>
			 						<c:when test="${task.colorLabel == 'amber' }">
			 							<c:set var="labelColor" value="task_label_amber" />
			 						</c:when>
			 						<c:when test="${task.colorLabel == 'pink' }">
			 							<c:set var="labelColor" value="task_label_pink" />
			 						</c:when>
			 						<c:when test="${task.colorLabel == 'red' }">
			 							<c:set var="labelColor" value="task_label_red" />
			 						</c:when>
			 						<c:when test="${task.colorLabel == 'orange' }">
			 							<c:set var="labelColor" value="task_label_orange" />
			 						</c:when>
			 						<c:when test="${task.colorLabel == 'brown' }">
			 							<c:set var="labelColor" value="task_label_brown" />
			 						</c:when>
			 						<c:otherwise> 
			 							<c:set var="labelColor" value="" />
			 						</c:otherwise>
			 					</c:choose>  
					 			<div class="task_item ${labelColor }" data-taskno="${task.taskno }">        
					 				<input type="checkbox" class="finish_task">
					 				<span class="task_name">${task.taskname }</span>
					 			</div>
					 		</c:if>    
					 		<c:if test="${task.status == 1 }">  
					 			<% finished = true; %> 
					 		</c:if> 
				 		</c:if>         
				 		</c:forEach>       
				 		</div>         
			 			<c:set var="finished" value="<%=finished %>"/>  
				 		<div class="finish-title" data-taskno="${task.taskno }" 	<c:if test="${finished==true}"> style="display: block;"</c:if> >    
					 		 	완료된 업무    	
						</div>     
				 		<div class="finished_task_box">
					 		<c:forEach items="${tasks }" var="task">   
				 			<c:if test="${task.tlno == item.tlno }">    
				 				<c:if test='${task.status == 1}'>
					 				<c:choose>
				 						<c:when test="${task.colorLabel == 'purple' }">
				 							<c:set var="labelColor" value="task_label_purple" />
				 						</c:when>
				 						<c:when test="${task.colorLabel == 'blue' }">
				 							<c:set var="labelColor" value="task_label_blue" />
				 						</c:when>
				 						<c:when test="${task.colorLabel == 'skyblue' }">
				 							<c:set var="labelColor" value="task_label_skyblue" />
				 						</c:when>
				 						<c:when test="${task.colorLabel == 'green' }">
				 							<c:set var="labelColor" value="task_label_green" />
				 						</c:when>
				 						<c:when test="${task.colorLabel == 'amber' }">
				 							<c:set var="labelColor" value="task_label_amber" />
				 						</c:when>
				 						<c:when test="${task.colorLabel == 'pink' }">
				 							<c:set var="labelColor" value="task_label_pink" />
				 						</c:when>
				 						<c:when test="${task.colorLabel == 'red' }">
				 							<c:set var="labelColor" value="task_label_red" />
				 						</c:when>
				 						<c:when test="${task.colorLabel == 'orange' }">
				 							<c:set var="labelColor" value="task_label_orange" />
				 						</c:when>
				 						<c:when test="${task.colorLabel == 'brown' }">
				 							<c:set var="labelColor" value="task_label_brown" />
				 						</c:when>
				 						<c:otherwise>    
				 							<c:set var="labelColor" value="" />
				 						</c:otherwise>
				 					</c:choose>   
						 			<div class="task_item finished ${labelColor }" data-taskno="${task.taskno }">    
						 				<input type="checkbox" class="finish_task" checked="checked">  
						 				<span class="task_name">${task.taskname }</span>
						 			</div>     
						 		</c:if>      
					 		</c:if>    
					 		</c:forEach>     
						</div>
				 		</div>          
				 		
				 		<c:set var="length" value="<%= length %>"/> 
				 		<div class="taskBox_footer" <c:if test="${length > 0 }">style="display:block;"</c:if>> 
					 		<span>진행중인 업무 <i style="font-style: normal;">${length}</i>개</span> 
				 		</div> 
			 		</c:if>
			 	</div>
			 	</c:forEach>
			 	</c:if>
			 	<!-- c:if 끝 -->
			 	<div class="taskBox taskBox_add">     
			 		<button type="button" class="addTaskList_btn"><span class="glyphicon glyphicon-plus"></span> 업무 추가하기</button>
			 		<div class="addTaskListWrap">
				 		<input type="text" placeholder="업무이름" > 
				 		<a href="#" class="cancle_addTaskList"><span class="glyphicon glyphicon-remove"></span></a>
			 		</div>    
			 	</div>
		 	</div>
		 	<ul class="dropdown_menu_setting" id="member_search_dropdown">
				<li><span class="dropTit">멤버</span> <a href="#"
					class="closeDropDownBtn"><span
						class="glyphicon glyphicon-remove"></span></a></li>
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
					<ul class="memList">
	
					</ul>
				</li>
			</ul>
		</div>
		<!-- 업무 리스트 끝 -->  
		<!-- 프로젝트 설정 --> 
		<div class="sideSetting" id="side_project_setting"
			style="display: none;" data-pno="${projectVO.pno }">
			<a href="#" class="sideSettingClose"><span
				class="glyphicon glyphicon-remove"></span></a>
	
			<div class="settingHeader">
				<div class="set_title inputText">
					<input type="text" value="프로젝트명" readonly="readonly"
						id="project_name_InputText"> <span	class="glyphicon glyphicon-pencil"></span>
					<p class="head_cnt">#7 작성자 TE ST • 작성일 4월 11일 • 몇 초 전에 업데이트됨</p>
				</div>
	
				<ul class="toggleWrap">
					<li class="selectSetting"><a href="#">설정</a></li>
					<li><a href="#">모든 활동</a></li>
				</ul>
			</div>
			<div class="settingBody">
				<div class="settingContent">
					<div class="settingBlock">
						<p>
							<span class="glyphicon glyphicon-text-size"
								style="font-size: 10px;"></span> 설명
						</p>
						<div class="inputText">
							<input type="text" readonly="readonly" id="explanation_InputText"
								placeholder="설명 추가하기"> <span
								class="glyphicon glyphicon-pencil"></span>
						</div>
					</div>
					<div class="settingBlock">
						<div class="left_block_tit">
							<p>프로젝트 상태</p>
						</div>
						<div class="right_block_con">
							<button class="custom_select_btn" id="project_state"
								data-value="0">
								<span class="custom_selected_value">상태없음<i
									class="color_pic"></i></span> <span
									class="glyphicon glyphicon-menu-down"></span>
							</button>
						</div>
					</div>
					<div class="settingBlock">
						<div class="settingInnerBox">
							<div class="left_block_tit">
								<p>시작일</p>
							</div>  
							<div class="right_block_con">  
								<div class="date" id="startDate">  
									<input type="text" readonly="readonly" >
									<button class="setting_btn input-group-addon">+</button>
								</div>  
							</div> 
						</div> 
						<div class="settingInnerBox">
							<div class="left_block_tit">
								<p>마감일</p>
							</div>
							<div class="right_block_con">
								<div class="date" id="endDate">
									<input type="text" readonly="readonly" />
									<button class="setting_btn input-group-addon">+</button>
								</div>
							</div>
						</div>
						<div class="settingInnerBox">
							<div class="left_block_tit">
								<p>실제 완료일</p>
							</div>
							<div class="right_block_con">
								<div class="date" id="finishDate">
									<input type="text" readonly="readonly" />
									<button class="setting_btn input-group-addon">+</button>
								</div>
							</div>
						</div>
					</div>
					<div class="settingBlock">
						<div class="settingInnerBox">
							<div class="left_block_tit">
								<p>프로젝트 관리자</p>
							</div>
							<div class="right_block_con">
								<button class="setting_btn" onclick="dropMenuOpen('admin')">+</button>
								<div class="addMemberBox" id="setting_addmember_admin">
									<%-- <div class="addMem_item" data-mno="${loginMem.mno }">${loginMem.firstName} ${loginMem.lastName }  
														<img id="userPic" class="pic" src="${pageContext.request.contextPath}/resources/img/user_icon_b.png" style="width: 25px; height: 25px;"/>
														<a href="#" class="delMem">x</a>
													</div> --%>
								</div>
							</div>
						</div>
					</div>
					<div class="settingBlock">
						<div class="settingInnerBox">
							<div class="left_block_tit">
								<p>프로젝트 팀원</p>
							</div>
							<div class="right_block_con">
								<button class="setting_btn" onclick="dropMenuOpen('member')">+</button>
								<div class="addMemberBox" id="setting_addmember_member">
									<%-- <div class="addMem_item" data-mno="${loginMem.mno }">${loginMem.firstName} ${loginMem.lastName }  
														<img id="userPic" class="pic" src="${pageContext.request.contextPath}/resources/img/user_icon_b.png" style="width: 25px; height: 25px;"/>
														<a href="#" class="delMem">x</a> 
													</div>   --%>
								</div>
							</div>
						</div>
					</div>
					<div class="settingBlock">
						<div class="left_block_tit">
							<p>공개 프로젝트</p>
						</div>
						<div class="right_block_con">
							<button class="setting_switch_btn">
								<div class="handler"></div>
								<div class="on-label">켬</div>
								<div class="off-label">끔</div>
							</button>
							<p>오직 추가된 팀원만이 이 프로젝트를 볼 수 있습니다.</p>
						</div>
					</div>
					<div class="settingBlock">
						<div class="left_block_tit">
							<p>권한 설정</p>
						</div>
						<div class="right_block_con">
							<button class="custom_select_btn" id="project_access_authority"
								data-value="1">
								<span class="custom_selected_value">프로젝트 팀원은 전체 엑세스 권한을
									가집니다.</span> <span class="glyphicon glyphicon-menu-down"></span>
							</button>
	  
							<p>
								<strong>전체 엑세스</strong>: 모든 프로젝트 팀원은 프로젝트 안에 있는 모든 업무 보기, 수정이
								가능합니다.
							</p>
						</div>
					</div>
					<div class="settingBlock">
						<div class="left_block_tit">
							<p>프로젝트 보관</p>
						</div>
						<div class="right_block_con" id="project_locker">
							<button class="setting_btn" data-value="0">프로젝트 보관</button>
							<br>
							<p>완료 혹은 더 이상 사용하지 않는 프로젝트를 보관함으로 옮깁니다.</p>
						</div>
					</div>
				</div>
			</div>
			<ul class="custom_select" id="selectStated">
				<li class="custom_select_item" data-value="1">계획됨<span
					class='color_pic state_color_planed'></span></li>
				<li class="custom_select_item" data-value="2">진행중<span
					class='color_pic state_color_proceeding'></span></li>
				<li class="custom_select_item" data-value="3">완료됨<span
					class='color_pic state_color_completed'></span></li>
				<li class="custom_select_item" data-value="4">보류</li>
				<li class="custom_select_item" data-value="5">취소</li>
				<li class="custom_select_item" data-value="0">상태 없음</li>
			</ul>
			
			<ul class="custom_select" id="select_project_authority">
				<li class="custom_select_item " data-value="0">프로젝트 팀원은 전체 엑세스
					권한을 가집니다.</li>
				<li class="custom_select_item " data-value="1">프로젝트 팀원은 제한 엑세스
					권한을 가집니다.</li>
				<li class="custom_select_item " data-value="2">프로젝트 팀원은 통제 엑세스
					권한을 가집니다.</li>
			</ul>
			<ul class="dropdown_menu_setting" id="setting_drop_menu_admin">
				<li><span class="dropTit">멤버</span> <a href="#"
					class="closeDropDownBtn"><span
						class="glyphicon glyphicon-remove"></span></a></li>
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
					<ul class="memList">
	
					</ul>
				</li>
			</ul>
	
			<ul class="dropdown_menu_setting" id="setting_drop_menu_member">
				<li><span class="dropTit">멤버</span> <a href="#"
					class="closeDropDownBtn"><span
						class="glyphicon glyphicon-remove"></span></a></li>
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
					<ul class="memList">
						
					</ul>
				</li>
			</ul>  
			 
		</div> 
		<!-- 프로젝트 설정 end -->
		
		<!-- 업무 설정  start-->
		<div class="sideSetting" id="side_task_setting"	style="display: none;">
			<a href="#" class="sideSettingClose"><span
				class="glyphicon glyphicon-remove"></span></a>
	
			<div class="settingHeader">
				<div class="set_title inputText">  
					<input type="text" value="업무명" readonly="readonly" id="task_name_InputText" style="width: 90%;"> 
					<span class="glyphicon glyphicon-pencil" style="right: 60px; "></span>
					<p class="head_cnt">#7 작성자 TE ST • 작성일 4월 11일 • 몇 초 전에 업데이트됨</p>
					<a href="#" id="delete_task_btn" style="position: absolute; top: 4px; right: 10px;"> 
						<span class="glyphicon glyphicon-trash"></span>      
					</a>   
				</div>   
	      
				<ul class="toggleWrap">  
					<li class="selectSetting" ><a href="#">설정</a></li>  
					<li ><a href="#"  >파일 & 링크</a></li>  
				</ul>      
			</div>
			<div class="settingBody">  
				<div class="settingContent"> 
					<div class="settingBlock">
						<p>
							<span class="glyphicon glyphicon-text-size"
								style="font-size: 10px;"></span> 설명
						</p>
						<div class="inputText">
							<input type="text" readonly="readonly" id="task_explanation_InputText" placeholder="설명 추가하기"> 
							<span class="glyphicon glyphicon-pencil"></span>
						</div>
					</div>  
					<div class="settingBlock">
						<div class="settingInnerBox">    
							<div class="left_block_tit">
								<p>시작일</p>
							</div>    
							<div class="right_block_con">  
								<div class="date" id="task_startDate">  
									<input type="text" readonly="readonly" >
									<button class="setting_btn input-group-addon">+</button>
								</div>  
							</div> 
						</div> 
						<div class="settingInnerBox">
							<div class="left_block_tit">
								<p>마감일</p> 
							</div>
							<div class="right_block_con">
								<div class="date" id="task_endDate">
									<input type="text" readonly="readonly" />
									<button class="setting_btn input-group-addon">+</button>
								</div>
							</div>
						</div>
						<div class="settingInnerBox">
							<div class="left_block_tit">
								<p>실제 완료일</p>
							</div>
							<div class="right_block_con">
								<div class="date" id="task_finishDate">
									<input type="text" readonly="readonly" />
									<button class="setting_btn input-group-addon">+</button>  
								</div>
							</div>
						</div>
						<div class="settingInnerBox">
							<div class="left_block_tit">
								<p>배정된 멤버</p>
							</div> 
							<div class="right_block_con">
								<button class="setting_btn" id="task_setting_addMember">+</button>
								<div class="addMemberBox" id="setting_task_addmember_member">
								
								</div>
							</div>  
						</div>
						<div class="settingInnerBox">
							<div class="left_block_tit">
								<p>색상 라벨</p>
							</div>  
							<div class="right_block_con" id="color-label">    
								<div class="color_label_btn" >
									<div class="color_label_picker color_label_purple"></div>
								</div>
								<div class="color_label_btn">
									<div class="color_label_picker color_label_blue"></div>
								</div>
								<div class="color_label_btn">  
									<div class="color_label_picker color_label_skyblue"></div>
								</div>
								<div class="color_label_btn">  
									<div class="color_label_picker color_label_green"></div>
								</div> 
								<div class="color_label_btn">
									<div class="color_label_picker color_label_amber"></div>
								</div>
								<div class="color_label_btn"> 
									<div class="color_label_picker color_label_pink"></div>
								</div>
								<div class="color_label_btn">
									<div class="color_label_picker color_label_red"></div>
								</div>
								<div class="color_label_btn">
									<div class="color_label_picker color_label_orange"></div>
								</div>
								<div class="color_label_btn">
									<div class="color_label_picker color_label_brown"></div>   
								</div> 
							</div>  
						</div>
					</div> 		 
			</div> 
			   
			 
		</div> 
		<!-- 업무 설정 end -->
		
	</div>  
	</div>   
</div>   
<!-- div end --> 
<ul class="dropdown_menu_setting" id="addTask_member_setting_dropdown"> 
		<li><span class="dropTit">멤버</span> 
			<a href="#"	class="closeDropDownBtn">
			<span class="glyphicon glyphicon-remove"></span></a></li>
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
					<ul class="memList">
	
					</ul>
				</li>
			</ul>
<!-- 멤버추가 end -->   
<ul class="dropdown_menu_setting" id="addTask_member_dropdown">
		<li><span class="dropTit">멤버</span> 
			<a href="#"	class="closeDropDownBtn">
			<span class="glyphicon glyphicon-remove"></span></a></li>
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
					<ul class="memList">
	
					</ul>
				</li>
			</ul>
<!-- dateTimePicker -->    
	<div id="sandbox-container" style="display: none;"> 
			<div class="datepicker_title">   
				<strong>일정 선택</strong> 
				<a href="#" class="close_datepicker"><span class="glyphicon glyphicon-remove"></span></a>
			</div> 
			<div> 
			 	<div class="datePicker_cst"></div>
			 	<div class="dateTimePicker">
			 		<div class="selectDate">
			 			2018-04-17
			 		</div>  
			 		<div class="timePicker_wrap">      
			 			<input type="text" class="minute selectTimeInput" value="00"/>  
			 			 : 
			 			<input type="text"class="hour selectTimeInput" value="00"/>
			 		</div>
 			 		<div class="timePicker_btn_wrap">   
			 			<button class="del_date">지우기</button>  
			 			<button class="add_date">추가</button>
			 		</div> 
			 	</div>
			 </div>   
		</div> 
    
	<!-- handlerbars 템플릿 -->         
	<script id="addTaskList_template" type="text/x-handlerbars-template">
		<div class="taskBox" data-order="{{list_order}}" data-tlno="{{tlno}}">
			 		<div class="taskBox_header"> 
			 			<div class="taskBox_header_wrap">  
				 			<input type="text" readonly="readonly" value="{{name }}" class="taskList_name">	
				 			<a href="#" class="add_task_btn"><span class="glyphicon glyphicon-plus"></span></a>  
				 			<div class="dropdown">
				 				<a href="#" class="dropdown-toggle" data-toggle="dropdown">
				 					<span class="glyphicon glyphicon-option-vertical"></span>
				 				</a>     
							    <ul class="dropdown-menu" style="left:-132px;">
							      <li><a href="#">이동... ></a></li>
							      <li><a href="#">이메일로 업무 작성</a></li>
							      <li><a href="#">리스트 삭제</a></li>
							    </ul>    
							 </div>  
						 </div>       
						 <div class="taskBox_header_addTask" style="display: none;">
						 	<textarea rows="1" cols="36"></textarea>
						 	<div class="taskBox_header_addBtnWrap">
						 		<a href="#" class="addMemberDropMenu">     
						 			<span class="glyphicon glyphicon-user">
						 				<i class="counter">1</i> 
						 			</span>    
						 		</a>
						 		<a href="#" class="addDatepickerOpen">
						 			<span class="glyphicon glyphicon-calendar"></span> 
						 			<input type="text" readonly="readonly" class="task_endDate">
						 		</a>    
						 	</div>
						 	<div class="taskBox_header_addBtnWrap">
						 		 <button class="add_task_cancle">취소</button>  
						 		 <button class="add_task_make" data-tlno="{{tlno}}">만들기</button>
						 	</div>
						 </div>          
			 		</div>
				<div class="taskBox_body">  
			 	</div>
			 	<div class="taskBox_footer" >  
					 <span>진행중인 업무 <i style="font-style: normal;">0</i>개</span> 
				 </div> 
		 	</div>        
	</script>
	
	<script id="addTask_template" type="text/x-handlerbars-template">
		<div class="task_item" data-taskno="{{taskno}}">  
			<input type="checkbox" class="finish_task">  
			<span class="task_name">{{taskname }}</span>
		</div>         
	</script>
	
	<script id="addTask_member_setting_template" type="text/x-handlerbars-template">
		{{#each.}}
		<li>  
			<a href="#" class="mem_li" data-mno="{{mno}}"  data-massno="{{massno}}" data-firstName="{{firstName }}" data-lastName="{{lastName }}" data-photoPath="{{photoPath }}"> 
				<img id="userPic" class="pic" src="${pageContext.request.contextPath}/{{checkPhotoPath photoPath}}" style="width: 25px; height: 25px;"/>
				{{firstName}} {{lastName}}   
				<span class="glyphicon glyphicon-ok" style="{{check_setting_task_member massno}}"></span>
			</a>  
		</li> 
		{{/each}}               
	</script>       
	
	<script id="addTask_member_member_template" type="text/x-handlerbars-template">
		{{#each.}}
		<li>  
			<a href="#" class="mem_li" data-mno="{{mno}}"  data-massno="{{massno}}" data-firstName="{{firstName }}" data-lastName="{{lastName }}" data-photoPath="{{photoPath }}"> 
				<img id="userPic" class="pic" src="${pageContext.request.contextPath}/{{checkPhotoPath photoPath}}" style="width: 25px; height: 25px;"/>
				{{firstName}} {{lastName}}   
				<span class="glyphicon glyphicon-ok" style="{{check_task_member massno}}"></span>
			</a>  
		</li> 
		{{/each}}          
	</script> 
	<script id="addTaskMemTemplate" type="text/x-handlerbars-template">
		<div class="addMem_item" data-mno="{{mno }}"  data-massno="{{massno}}" data-firstName="{{firstName }}" data-lastName="{{lastName }}" data-photoPath="{{photoPath }}">{{firstName}} {{lastName }}  
			<img id="userPic" class="pic" src="${pageContext.request.contextPath}/{{checkPhotoPath photoPath}}" style="width: 25px; height: 25px;"/>
			<a href="#" class="delMem">x</a>
		</div> 
	</script> 
	<script id="setting_admin_template" type="text/x-handlerbars-template">
		{{#each.}}
		<li>  
			<a href="#" class="mem_li" data-mno="{{mno}}"  data-firstName="{{firstName }}" data-lastName="{{lastName }}" data-photoPath="{{photoPath }}"> 
				<img id="userPic" class="pic" src="${pageContext.request.contextPath}/{{checkPhotoPath photoPath}}" style="width: 25px; height: 25px;"/>
				{{firstName}} {{lastName}}   
				<span class="glyphicon glyphicon-ok" style="{{check_setting_admin mno}}"></span>
			</a>     
		</li> 
		{{/each}}   
	</script>
 
	 
	<script id="setting_member_template" type="text/x-handlerbars-template">
		{{#each.}}
		<li>  
			<a href="#" class="mem_li" data-mno="{{mno}}"  data-firstName="{{firstName }}" data-lastName="{{lastName }}" data-photoPath="{{photoPath }}"> 
				<img id="userPic" class="pic" src="${pageContext.request.contextPath}/{{checkPhotoPath photoPath}}" style="width: 25px; height: 25px;"/>
				{{firstName}} {{lastName}}   
				<span class="glyphicon glyphicon-ok" style="{{check_setting_member mno}}"></span>
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
	<script id="addMemTemplate_projectSetting" type="text/x-handlerbars-template">
		<div class="addMem_item" data-mno="{{mno }}" data-firstName="{{firstName }}" data-memAssGrade="{{memAssGrade }}"data-lastName="{{lastName }}" data-photoPath="{{photoPath }}">{{firstName}} {{lastName }}  
		<img id="userPic" class="pic" src="${pageContext.request.contextPath}/{{checkPhotoPath photoPath}}" style="width: 25px; height: 25px;"/>
			<a href="#" class="delMem" style="{{checkMemAssGrade memAssGrade}}">x</a>
		</div>         
	</script>
</body>    
</html>
