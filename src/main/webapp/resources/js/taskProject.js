
function inviteEamilTrans(e){
	var emails = [];
	//이메일 초대 검사
	$("#inviteInputWrap input[type='email']").each(function(i,obj){
		if(!checkFrom($(this))){ 
			return false;
		}   
		
		var email =  $(this).val();
		if (!regEmail.test(email)) {
			alert("이메일 형식에 맞춰주세요.");
			$("#email").focus();
			return false;  
		}
		
		emails.push(email); 	
	});
	
	
	var datas = {
			"emails":emails,//이메일배열
			"inviter":$(this).attr("data-inviter"),//초대자
			"wcode":$(this).attr("data-wcode"),//초대 워크스페이스
			"maker":$(this).attr("data-maker")
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
 
$(function(){
	$("#inviteInputWrap").on("click",".delBtn",function(){
		 $(this).parent(".invite_item").remove(); 
	 });
})