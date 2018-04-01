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

		if (!checkInput) {
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

		$(".profileBox").css("display", "none");
		$(".workspaceBox").css("display", "block");
	});

	$(".work_btn").click(function(e) {
		$(".workspaceBox input[type]").each(function(i, obj) {
			if (!checkFrom($(this))) {
				checkInput = false;
				return false;
			}
		});
		
		var email = $("#email").val();
		var firstName = $("#firstName").val();
		var lastName = $("#lastName").val();
		var password = $("#password").val();
		var workspaceName = $("#workspaceName").val();
		console.log(workspaceName);  
//		var photoPath = $("#img").attr("date-imgpath");
		
		$.ajax({
			url : "/projectManager/register/create/"+workspaceName,
			type:"put",
			headers:{"Content-Type":"application/json"},
			dataType:"json",  
			data : JSON.stringify({
				"email" : email,
				"password" : password,
				"firstName" : firstName,
				"lastName" : lastName
			}), 
			success:function(res) {
				console.log(res);   
				if(res != "fail"){
					$("#inviteLink").val("http://localhost:8080/projectManager/user/invite/"+res.wvo.wcode);
					$("#wname").val(res.wvo.name);
				}else{
					alert("워크스페이스 생성에 실패하였습니다.");
					return;
				}
			} 
		})
		  
		// ajax를 이용하여 db에 아아디 + 워크스페이스 생성 + 초대
		
		$(".workspaceBox").css("display", "none");
		$(".inviteBox").css("display", "block");
	})

});

function proBtnEmail(e) {
	var checkInput = true;
	$(".profileBox input[type]").each(function(i, obj) {
		if (!checkFrom($(this))) {
			checkInput = false;
			return false;
		}
	});

	if (!checkInput) {
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

	$(".profileBox").css("display", "none");
	$(".workspaceBox").css("display", "block");
};

function proBtnGoogle(e) {
	var checkInput = true;
	$(".profileBox input[type]").each(function(i, obj) {
		if (!checkFrom($(this))) {
			checkInput = false;
			return false;
		}
	});

	if (!checkInput) {
		return false;
	}
	
	$(".profileBox").css("display", "none");
	$(".workspaceBox").css("display", "block");
};

function checkFrom(input) {
	if (input.val() == "") {
		alert("모두 입력하세요");
		input.focus(); 
		return false;
	}
	return true;
};