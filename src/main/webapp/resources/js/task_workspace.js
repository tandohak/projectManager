$(function(){
	$("#inviteInputWrap").on("click",".delBtn",function(){
		 $(this).parent(".invite_item").remove(); 
	 });
	 
	$("#inviteMemDialog").click(function(){
		$("#inviteInputWrap input[type='email']").each(function(i,obj){
			$(this).val("");	
			if(i!=0){
				$(this).remove();
			}
		}); 
	}) 
	 
	$("#deletMember").on("click",".reinvite",function(e){
		e.preventDefault();
		
		var emails = [];
	
		emails.push($(this).attr("data-target-email")); 	
		 
		var datas = {
				"emails":emails,//이메일배열
				"inviter":$(this).attr("data-inviter"),//초대자
				"wcode":$(this).attr("data-wcode"),//초대 워크스페이스
				"maker":$(this).attr("data-maker")
		} 
		reInviteEmail(datas);
	})
	
	$(".delInvite").click(function(e){
		e.preventDefault();
		 
		var invitee = $(this).attr("data-email");
		var $li = $(this).parents("li");
		$.ajax({
			url:"/projectManager/invite/delete?invitee="+invitee,
			data:invitee,
			dataType : "text",
			type:"delete",
			success : function(result){
				console.log(result); 
				if(result=="success"){
					alert("삭제하였습니다.");
					$li.remove();
				}else{
					alert("삭제에 실패 하였습니다.");
				} 
			}  
		})
	})
	
	$(".mem_setting").click(function(e){
		e.preventDefault();  
		$(".dropdown_menu_setting").not($(this).siblings(".dropdown_menu_setting")).css("display","none");
		$(this).siblings(".dropdown_menu_setting").toggle();  
	}) 
	 
	// 멤버 등급 1:일반멤버 , 2:가입대기 , 3:관리자, 4:삭제멤버, 99:총관리자
	//관리자 등급 ==> 일반 멤버 등급     
	$("#memberList").on("click",".memGrade_admin",function(){
		var mno = $(this).attr("data-mno");
		var data ={
				"mno":mno,
				"memGrade":3
		};
		
		$(this).html("멤버로 변경");
		$(this).attr("class","memGrade_normal");
		var $parent = $(this).parents(".mem_settingWrap");
		var $li = $parent.parent("li");
		 
		$li.find(".memItem span:eq(1)").text("관리자");
		$li.find("strong").text("");
 
		$("#adminList").append($li);  
		  
		$("#normalList>li:eq(0)").find("strong").text("멤버"); 
		$("#standByList>li:eq(0)").find("strong").text("가입대기"); 
		 
		$(".dropdown_menu_setting").css("display","none");
		updateMember(data); 
	})   
	       
	$("#memberList").on("click",".memGrade_normal",function(){
		var mno = $(this).attr("data-mno");
		var data ={
				"mno":mno,
				"memGrade":1  
		};  
		     
		$(this).html("관리자로 변경");
		$(this).attr("class","memGrade_admin");
		var $parent = $(this).parents(".mem_settingWrap");
		var $li = $parent.parent("li");
		$li.find(".memItem span:eq(1)").text("멤버");
		$li.find("strong").text(""); 
		
		console.log($li.index()); 
		 
		$("#normalList").append($li); 
		if($("#normalList>li").length <= 0){
			$li.find("strong").text("멤버");
		}
		  
		$("#normalList").append($li); 

		$(".dropdown_menu_setting").css("display","none");
		updateMember(data);
		 
		$("#normalList>li:eq(0)").find("strong").text("멤버"); 
		$("#standByList>li:eq(0)").find("strong").text("가입대기"); 	
	})   
	 
	$(".memGrade_delete").click(function(){
		var mno = $(this).attr("data-mno");
		var email = $(this).attr("data-email");
		var inviter = $(this).attr("data-inviter");
		var wcode = $(this).attr("data-wcode");
		var maker = $(this).attr("data-maker");
		
		var data ={  
				"mno":mno,
				"memGrade":4
		};  
		
		$(".dropdown_menu_setting").css("display","none"); 
		updateMember(data);
		
		var $li = $("<li>");
		var $div = $("<div>");
		$div.append("<span class='glyphicon glyphicon-refresh invitePic'></span>");
		$div.append("<span>"+email+"</span>");
		var $div2 = $("<div>");
		var a = $("<a href='#' class='reinvite'>");
		a.attr("data-target-email",email);
		a.attr("data-wcode",wcode);
		a.attr("data-inviter",inviter);   
		a.attr("data-maker",maker);
		  
		a.append('<span class="glyphicon glyphicon-refresh" style="font-size: 12px"></span> &nbsp;다시 초대하기');
		$div2.append(a); 
		
		$li.append($div);
		$li.append($div2);
		 
		$(this).parents("li").remove();
		
		$("#deletMember .mList").append($li);

		$("#normalList>li:eq(0)").find("strong").text("멤버"); 
		$("#standByList>li:eq(0)").find("strong").text("가입대기"); 	
	})
})    

function updateMember(datas){
	$.ajax({
		url:"/projectManager/member/update",
		type : "patch",
		data : JSON.stringify(datas),
		contextType:"application/json",
		headers:{"Content-Type":"application/json"},
		dataType : "text",
		async:false,
		success : function(result) {
			console.log(result);
		}
	});  
}

function inviteEamilTrans(){
	var emails = [];
	var chck = true; 
	
	//이메일 초대 검사 
	$("#inviteInputWrap input[type='email']").each(function(i,obj){
		if(!checkFrom($(this))){ 
			chck=false; 
			return false;
		}   
		
		var email =  $(this).val(); 
		if (!regEmail.test(email)) {
			alert("이메일 형식에 맞춰주세요.");
			$("#email").focus();
			chck=false;
			return false;  
		}
		
		emails.push(email); 	
	});
	
	if(!chck){
		return false;
	}
	var datas = {
			"emails":emails,//이메일배열
			"inviter":$("#inviteEamilTransBtn").attr("data-inviter"),//초대자
			"wcode":$("#inviteEamilTransBtn").attr("data-wcode"),//초대 워크스페이스
			"maker":$("#inviteEamilTransBtn").attr("data-maker")
	} 
	
	$.ajax({
		url : "/projectManager/invite/inviteTeam",
		data : JSON.stringify(datas),
		contextType:"application/json",
		headers:{"Content-Type":"application/json"},
		type : "post",
		dataType : "text",
		async:false,
		success : function(result) {
			console.log(result);
			if(result=="fail"){
				alert("실패하였습니다.");
			}else{
				alert("이메일 전송에 성공 하였습니다..");
				$("#inviteModal").trigger("click"); 
				$("#inviteInputWrap input[type='email']").each(function(i,obj){
					$(this).val("");	
					if(i!=0){
						$(this).remove();
					}
				}); 
			} 
		}  
	}); 
} 
 
var reInviteEmail  = function(datas){ 
	$.ajax({
		url : "/projectManager/invite/inviteTeam",
		data : JSON.stringify(datas),
		contextType:"application/json",
		headers:{"Content-Type":"application/json"},
		type : "post",
		dataType : "text",
		async:false,
		success : function(result) {
			console.log(result);
			if(result=="fail"){
				alert("실패하였습니다.");
			}else{
				alert("이메일 전송에 성공 하였습니다..");
				$("#inviteModal").trigger("click"); 
				$("#inviteInputWrap input[type='email']").each(function(i,obj){
					$(this).val("");	
					if(i!=0){
						$(this).remove();
					}
				}); 
			} 
		}  
	});  
} 

function addInput(e){   
	var $input = $("<input type='email'>");
	$input.attr("placeholder","초대할 이메일을 입력해주세요.");
	$input.addClass("input");
	$input.addClass("full");
	
	var $delBtn = $("<a href='#'>");
	$delBtn.addClass("delBtn");  
	$delBtn.append("<span class='glyphicon glyphicon-remove'></span>");
	
	var $divWrap = $("<div>");
	$divWrap.addClass("invite_item");
	$divWrap.append($input); 
	$divWrap.append($delBtn);
	
	$("#inviteInputWrap").append($divWrap);
}
 