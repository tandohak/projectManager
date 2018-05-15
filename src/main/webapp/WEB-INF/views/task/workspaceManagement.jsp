<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="include/header.jsp" %>
<link rel="stylesheet"	href="${pageContext.request.contextPath}/resources/css/task_workspace.css?a=9"> 
<script src="${pageContext.request.contextPath}/resources/js/task_workspace.js?a=9"></script>
<%@ include file="include/sideBar.jsp" %>  
		<script> 
			var memType = ${memType};     
		</script>   
			<div id="contentWrap"> 
				<div class="contentBox">  
					<h1 class="sub_tit">${workVO.name} 워크스페이스</h1>
						<ul class="nav nav-tabs">  
					    <li class="active"><a data-toggle="tab" href="#setting">워크스페이스 설정</a></li>
					    <li><a data-toggle="tab" href="#member">워크스페이스 멤버</a></li>
					  </ul>
					
					  <div class="tab-content bgcolor_fff tabContent">   
					    <div id="setting" class="tab-pane fade in active">  
					    	<h2 class="sub_cnt_tit">일반 설정</h2>
					    	<div class="form-group">
					    		 <label for="email" class='margin_bottom_15'>워크스페이스 명</label> 
					    		<input type="text" class="form-control margin_bottom_15"  id="workName" value="${workVO.name}" data-wcode="${workVO.wcode }" style="width: 300px;">  
					    		<button class="btn btn_cst margin_bottom_15" onclick="workNameChange()">변경사항 저장</button>
					    	</div>   
					    </div>      
					    <div id="member" class="tab-pane fade">  
							<div id="inner_tab_wrap">
						    	<ul class="nav nav-tabs nav-pills nav_tab_inner" >
								  <li class="active"><a data-toggle="tab" href="#memberList">전체멤버</a></li>
								  <li><a data-toggle="tab" href="#inviteMember">초대 중인 멤버</a></li>
								  <li><a data-toggle="tab" href="#deletMember">삭제된 멤버</a></li>  
								</ul>
								<button id='inviteMemDialog' data-toggle="modal" data-target="#inviteModal">멤버 초대하기</button> 
							</div> 
							   
							<div class="tab-content"> 
							  <div id="memberList" class="tab-pane fade in active"> 
							    <ul class="mList" id="adminList">
							    	<c:forEach var="mem" items="${workMembers}">
								    	<c:if test="${mem.memGrade == 99}">
								    	<li>							    		
								    		<strong><c:if test="${mem.memGrade == 99}"> 관리자 </c:if></strong>
								    		
								    		<a href="#">
								    			<c:if test="${mem.photoPath==''}">
								    				<img src="/projectManager/resources/img/user_icon_b.png"/>
								    			</c:if> 
								    			<c:if test="${mem.photoPath!=''}"> 
													<img id="userPic" class="pic"  src="${pageContext.request.contextPath}/${mem.photoPath}"/>
								    			</c:if>  
								    			<span>${mem.firstName} ${mem.lastName }</span>
								    			<span>관리자</span>   
								    		</a>
								    	</li>  
								    	</c:if>  
							    	</c:forEach>   
							    	<c:forEach var="mem" items="${workMembers}">
								    	<c:if test="${mem.memGrade == 3}">
								    	<li>							    		
								    		<strong></strong>
								    		
								    		<a href="#" class="memItem">
								    			<c:if test="${mem.photoPath==''}">
								    				<img src="/projectManager/resources/img/user_icon_b.png"/>
								    			</c:if> 
								    			<c:if test="${mem.photoPath!=''}">  
													<img id="userPic" class="pic"  src="${pageContext.request.contextPath}/${mem.photoPath}"/>
								    			</c:if>  
								    			<span>${mem.firstName} ${mem.lastName }</span>
								    			<span>관리자</span>   
								    		</a>    
								    		<c:if test="${memVo.memGrade == 99 || memVo.memGrade == 3}">   
								    			<div class="mem_settingWrap">
								    				<a href="#" class="mem_setting"><span class="glyphicon glyphicon-cog"></span></a>
								    				<ul class="dropdown_menu_setting">
								    					<li><a href="#"  class="memGrade_normal" data-mno="${mem.mno }">멤버로 변경</a></li> 
								    					<li><a href="#" class="memGrade_delete"   data-mno="${mem.mno }" data-email="${mem.email }" data-inviter="${login.email}" data-wcode="${workVO.wcode}" data-maker="${workVO.maker}">워크스페이스에서 제거</a></li>    
								    				</ul>  
								    			</div>
								    		</c:if>
								    	</li>   
								    	</c:if>
							    	</c:forEach> 
							     </ul>
							     <ul class="mList" id="normalList">  
							    	<% int i = 0; %>  
							    	<c:forEach var="mem" items="${workMembers}">
							    		<c:set var="i" value="<%=i %>"></c:set> 
								    	<c:if test="${mem.memGrade == 1}">
								    	<li>
								    		<strong><c:if test="${i == 0}"> 멤버 </c:if> </strong>
								    		<% i++; %>
								    		<a href="#" class="memItem">
								    			<c:if test="${mem.photoPath==''}">
								    				<img src="/projectManager/resources/img/user_icon_b.png"/>
								    			</c:if>
								    			<c:if test="${mem.photoPath!=''}">
													<img id="userPic" class="pic"  src="${pageContext.request.contextPath}/${mem.photoPath}"/>
								    			</c:if> 
								    			<span>${mem.firstName} ${mem.lastName }</span>
								    			<span> 멤버 </span>   
								    		</a>   
								    		 
								    		<c:if test="${memVo.memGrade == 99 || memVo.memGrade == 3}"> 
								    			<div class="mem_settingWrap">
								    				<a href="#" class="mem_setting" data-mno=""><span class="glyphicon glyphicon-cog"></span></a>
								    				<ul class="dropdown_menu_setting">
								    					<li><a href="#"  class="memGrade_admin" data-mno="${mem.mno }">관리자로 변경</a></li> 
								    					<li><a href="#" class="memGrade_delete" data-mno="${mem.mno }" data-email="${mem.email }">워크스페이스에서 제거</a></li>   
								    				</ul>
								    			</div>
								    		</c:if>
								    	</li>    
								    	</c:if>  
							    	</c:forEach>
							     </ul>
							     <ul class="mList" id="standByList"> 
							    	<% i = 0; %>  
							    	<c:forEach var="mem" items="${workMembers}">
							    		<c:set var="i" value="<%=i %>"></c:set> 
								    	<c:if test="${mem.memGrade == 2}">
								    	<li> 
								    		<strong><c:if test="${i == 0}"> 가입대기 </c:if> </strong>
								    		<% i++; %> 
								    		<a href="#" class="memItem">
								    			<c:if test="${mem.photoPath==''}">
								    				<img src="/projectManager/resources/img/user_icon_b.png"/>
								    			</c:if>
								    			<c:if test="${mem.photoPath!=''}">
													<img id="userPic" class="pic"  src="${pageContext.request.contextPath}/${mem.photoPath}"/>
								    			</c:if> 
								    			<span>${mem.firstName} ${mem.lastName }</span>
								    			<span> 가입대기 </span>   
								    		</a>   
								    		  
								    		<c:if test="${memVo.memGrade == 99 || memVo.memGrade == 3 }"> 
								    			<div class="mem_settingWrap">
								    				<a href="#" class="mem_setting" data-mno=""><span class="glyphicon glyphicon-cog"></span></a>
								    				<ul class="dropdown_menu_setting">
								    					<li><a href="#" class="memGrade_normal" data-mno="${mem.mno }">가입 승인</a></li> 
								    					<li><a href="#" class="memGrade_delete" data-mno="${mem.mno }"  data-email="${mem.email }">워크스페이스에서 제거</a></li>    
								    				</ul>
								    			</div> 
								    		</c:if>
								    	</li>
								    	</c:if>  
							    	</c:forEach> 
							    </ul>
							    
							  </div>
							  <div id="inviteMember" class="tab-pane fade inviteList">
							  	<ul class="mList">  
							    	<c:if test="${inviteMembers != null}">
							    	<c:forEach var="mem" items="${inviteMembers}">
							    	<li>		
							    		<div>   
							    			<span class="glyphicon glyphicon-refresh invitePic"></span>
							    			<span>${mem.invitee}</span> 
							    		</div>
							    		<div> 
							    			<a href="#" class="reinvite" data-target-email="${mem.invitee}" data-inviter="${login.email}" data-wcode="${workVO.wcode}" data-maker="${workVO.maker}">  
								    			<span class="glyphicon glyphicon-refresh" style="font-size: 12px"></span> &nbsp;다시 초대하기 
								    		</a> 
								    		&nbsp; 
								    		<a href="#" class="delInvite" data-email='${mem.invitee}'> 
								    			<span class="glyphicon glyphicon-trash"></span>
								    		</a> 
							    		</div>      
							    		
							    	</li> 
							    	</c:forEach>
							    	</c:if>
							    </ul>    
							  </div>   
							  <div id="deletMember" class="tab-pane fade inviteList">
							  <ul class="mList">  
							    	<c:forEach var="mem" items="${workMembers}">
							    	<c:if test="${mem.memGrade == 4}">
							    	<li>		
							    		<div> 
							    			<span class="glyphicon glyphicon-refresh invitePic"></span>
							    			<span>${mem.email}</span>
							    		</div>    
							    		<div>
							    			<a href="#" class="reinvite" data-target-email='${mem.email}' data-inviter="${login.email}" data-wcode="${workVO.wcode}" data-maker="${workVO.maker}">  
								    			<span class="glyphicon glyphicon-refresh" style="font-size: 12px"></span> &nbsp;다시 초대하기
								    		</a>   
							    		</div>    
							    	</li>
							    	</c:if>
							    	</c:forEach>  
							    </ul> 
							  </div>
							</div>
					    </div>
					  </div>  
				</div>  
			</div> 
		</div>  
 	</div> 
 	
 	 <!-- Modal -->
  <div class="modal fade" id="inviteModal" role="dialog">
    <div class="modal_inner_box">
    	<h2 class="tit_h2" >팀원을 초대하세요.</h2>

				<div class="inn_row"> 
					<p class="cFont" style="margin:0; margin-bottom: 5px; color:rgba(0,0,0,0.6);">링크를 공유하여 <strong>${workVO.name }</strong>에 팀원을 초대하세요.</p>
					<input type="text"	class="input full" name="inviteLink" id="inviteLink" value="http://hongyoonpyo.cafe24.com${pageContext.request.contextPath }/task/${workVO.wcode}/join" readonly="readonly">
				</div>    
				
				<div id="bar" class="inn_row"> 
					<span class="bar"><i></i></span> 
					<span class="txt">또는</span> 
					<span class="bar"><i></i></span>
				</div>
				
				<div class="inn_row txtCenter" >
					<p style="color:#337ab7; font-weight: bold;">이메일로 초대하기</p> 
				</div>  
				
				<div class="inn_row" id="inviteEmailBox">
					<div class="inn_row" id="inviteInputWrap"> 
						<div class="invite_item">
							<input type="email" class="input full" placeholder="초대할 이메일을 입력해주세요.">
						</div> 
					</div>    
					<div class="inn_row" style="text-align: center; margin-top: 10px;"> 
						<a href="#" onclick="addInput()" style="font-size: 12px; "><span class="glyphicon glyphicon-plus"></span> 추가하기</a>  
					</div>  
					    
				</div>      
				<div class="inn_row">
					<a href="#" class="btnCtm" style="width: 25%;" onclick="inviteEamilTrans()" id="inviteEamilTransBtn" data-inviter="${login.email}" data-wcode="${workVO.wcode}" data-maker="${workVO.maker}">전송</a>    
				</div>   
    </div>  
  </div>  
  
</body>  
</html>     