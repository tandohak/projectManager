/* 회원가입 스크립트 파일  */
var regId = /^[a-z0-9]{6,15}$/;
var regPw = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{8,20}$/;
var regEmail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/;
var existId = false;
var formData = null;

$(function() {
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
		
		var photoPath = imgUpload();
		console.log(photoPath);
		
		$.ajax({
			url : "/projectManager/register/create/"+workspaceName,
			type:"put",
			headers:{"Content-Type":"application/json"},
			dataType:"json",  
			data : JSON.stringify({
				"email" : email,
				"password" : password,
				"firstName" : firstName,
				"lastName" : lastName,
				"photoPath" : photoPath
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
				
		$(".workspaceBox").css("display", "none");
		$(".inviteBox").css("display", "block");
	})

	
	$("#photoBox").click(function(){
		$("#img_input").trigger('click');
	})
	
	$("#img_input").on("change", function(e) {
		formData = new FormData();
		
		var files =  e.target.files;
		var file = files[0];
		console.log(file);

		var reader = new FileReader();
		reader.addEventListener("load", function() {
			$("#photo").attr("src", reader.result);
		}, false);

		if (file) {
			reader.readAsDataURL(file);
		}
		formData.append("files", file);
	});
	
	 $("#emailPost").submit(function(){
		 var res;
			if(!checkFrom($("#email"))){ 
				return false;  
			}   
			
			var email =  $("#email").val();
			if (!regEmail.test(email)) {
				alert("이메일 형식에 맞춰주세요.");
				$("#email").focus();
				return false;  
			}
			
			$.ajax({
				url : "/projectManager/register/exists",
				contentType: "application/json",
				data : email,
				type : "post",
				dataType : "text",
				async:false,
				success : function(result) {
					res = result;
				}
			});
			
			if(res=="exists"){
				alert("이미 가입한 아이디입니다.");
				return false;
			}
			
		});
	 $(".work_backBtn").click(function(){
		$(".profileBox").css("display", "block");
		$(".workspaceBox").css("display", "none");
	 })
});  


function imgUpload(){
	var path;
	$.ajax({
		url : "/projectManager/uploadDrag",
		data : formData,
		processData : false,
		contentType : false,
		type : "post",
		dataType : "json",
		async:false,
		success : function(result) {
			console.log(result);
			if(result == null){
				alert("전송 실패");
			}else{
				path = result[0];
				formData = null;
			}
		}
	});
	
	return path; 
}  

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

