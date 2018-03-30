/* 회원가입 스크립트 파일  */
var regId = /^[a-z0-9]{6,15}$/;
var regPw = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{8,20}$/;
var existId = false;

$(function() {
	$(".pro_btn").click(function(e) {
		var checkInput = true;
		$(".profileBox input[type]").each(function(i, obj) {
			if (!checkFrom($(this))) {
				checkInput = false;
				return false;
			}
		});
		
		if(!checkInput){
			return false;
		}
		
		if (!regPw.test($("#password").val())) {
			alert("비밀번호는 8~20자 영어,숫자, 특수문자만 가능합니다.");
			$("#password").focus();
			return false;
		}

		var pw = $("#password").val();
		var pwck = $("#rePassword").val();
		if (!(pw == pwck)) { 
			alert("비밀번호가 다릅니다.");
			$("#password").focus();
			return false;
		} 
		
		$(".profileBox").css("display","none");
		$(".workspaceBox").css("display","block");
	});  
	$(".work_btn").click(function(e){
		$(".workspaceBox input[type]").each(function(i, obj) {
			if (!checkFrom($(this))) {
				checkInput = false;
				return false;
			}
		});
		
		//ajax를 이용하여 db에 아아디 + 워크스페이스 생성 + 초대 
		
		$(".workspaceBox").css("display","none");
		$(".inviteBox").css("display","block");
	})
});

function checkFrom(input) {
	if (input.val() == "") {
		alert("모두 입력하세요");
		input.focus();
		return false;
	}
	return true;
};