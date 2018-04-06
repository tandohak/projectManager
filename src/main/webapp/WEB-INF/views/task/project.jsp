<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="include/header.jsp" %>
<link rel="stylesheet"	href="${pageContext.request.contextPath}/resources/css/task_project.css"> 
 

<%@ include file="include/sideBar.jsp" %>
			<div id="contentWrap">   
			<nav class="navbar navbar-default" style="border-radius:0px;">  
			  <div class="container-fluid" style="padding:0px;"> 
			  <ul class="nav navbar-nav sticky-top"> 
					<li>  
						 <a href="#" class="dropdown-toggle" data-toggle="dropdown">
						 	전체 프로젝트(0)<i>▼</i> 
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
					 
					<a href="#"><i>▼</i> 내 프로젝트 (0)</a>
					
					<div>
					
					</div>
				</div> 
			</div>   
		</div>  
 	</div> 
</body>  
</html>     