<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<c:if test="${memVo.memGrade ==2}">   
	<div style="position: absolute; top:0;z-index:999; left:0; width: 100%; height: 100%; background: rgba(0,0,0,0.5); overflow: hidden;">
		<p class="txtCenter cFont_white" style="line-height: 100vh;" >
		가입 대기중입니다. 워크스페이스 관리자에게 문의하세요.
		<a href="${pageContext.request.contextPath }/task/" style="color:#fff;"><strong>내 워크스페이스로 이동</strong></a> 
		</p>     
	</div>        
</c:if>   
<c:if test="${memVo.memGrade ==4}">   
	<div style="position: absolute; top:0;z-index:999; left:0; width: 100%; height: 100%; background: rgba(0,0,0,0.5); overflow: hidden;">
		<p class="txtCenter cFont_white" style="line-height: 100vh;" >
		삭제된 멤버입니다. 워크스페이스 관리자에게 문의하세요.
		<a href="${pageContext.request.contextPath }/task/" style="color:#fff;"><strong>내 워크스페이스로 이동</strong></a> 
		</p>     
	</div>        
</c:if>  
<div class="container-fluid">
		<div class="row">
			<!-- Sidebar navigation -->
			<nav class="d-none d-md-block bg-light sidebar">
				<div class="sidebar-sticky"> 
            <ul class="nav flex-column"> 
              <li class="nav-item"> 
                <a class="nav-link active" href="#">
					 <span class="glyphicon glyphicon-bell"></span>
                </a>
				<span class="nav_btn_balloon"><i class="arr">◀</i>알림</span>
              </li>   
              <li class="nav-item"> 
                <a class="nav-link active" href="#">  
					 <span class="glyphicon glyphicon-stats"></span>   
                </a> 
                <span class="nav_btn_balloon"><i class="arr">◀</i>전체 개요</span> 
              </li>
              <li class="nav-item"> 
                <a class="nav-link active" href="#">
					 <span class="glyphicon glyphicon-folder-open"></span>
                </a>
                <span class="nav_btn_balloon"><i class="arr">◀</i>내 프로젝트</span>
              </li>
              <li class="nav-item"> 
                <a class="nav-link active" href="#">
					 <span class="glyphicon glyphicon-comment"></span>
                </a> 
				<span class="nav_btn_balloon"><i class="arr">◀</i>메시지</span> 
              </li>
            </ul>  
          </div>  
			</nav>
	