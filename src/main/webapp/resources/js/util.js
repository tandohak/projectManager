var regId = /^[a-z0-9]{6,15}$/;
var regPw = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{8,20}$/;
var regEmail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/;

function checkFrom(input) {
	if (input.val() == "") {
		alert("모두 입력하세요");
		input.focus(); 
		return false;
	}
	return true;
};

$(function(){
	$("#makeWorkspaceBtn").click(function(e){
		e.preventDefault();
		var name = $("#makeWorkspace_name_input").val();
		var maker = $(this).attr("data-maker");
		var uno  = Number($(this).attr("data-uno"));
		if(name == "" || name == null){
			alert("워크스페이스 명을 입력하세요.");
			 $("#makeWorkspace_name_input").focus();  
			return false;
		}
		$.ajax({ 
			url : "/projectManager/register/create/workspace",
			type:"post",
			headers:{"Content-Type":"application/json"},
			dataType:"json",  
			async:false,
			data : JSON.stringify({
				"name" : name,
				"maker" : maker,
				"uno" : uno
			}), 
			success:function(res) {
				console.log(res);    
				if(res != "fail"){  
					location.replace("/projectManager/task/"+res.wcode)
				}else{ 
					alert("워크스페이스 생성에 실패하였습니다.");
					return;
				}
			}
		}) 
	})
})