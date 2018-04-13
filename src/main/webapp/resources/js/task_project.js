$(function() {
	var imgPath = [ "preview-blank-f9e6c665.png",
			"preview-weekly-ko-f26d30dd.png", "preview-people-ko-60882c1d.png",
			"preview-department-ko-8f933e90.png",
			"preview-todo-ko-d07476ee.png" ];
	var tipArr = ["<strong>전체 엑세스</strong>: 모든 프로젝트 팀원은 프로젝트 안에 있는 모든 업무 보기, 수정이 가능합니다.",
		          "<strong>제한 엑세스</strong>: 프로젝트 팀원은 업무, 제목, 설명, 위치, 시작일, 마감일, 배정에 대해 추가/수정을 할 수 없습니다.",
		          "<strong>통제 엑세스</strong>: 프로젝트 팀원은 자신이 배정되어있거나 팔로워인 업무만 볼 수 있습니다."];
	var selval = [["상태 없음","계획됨","진행중","완료됨","보류","취소"],
	               [" ","state_color_planed","state_color_proceeding","state_color_completed"," "," "],
	               [" "
	                ,"background-color: #ffb024; color:#fff; border:none;"
	                ,"background-color: #62c276; color:#fff; border:none;"
	                ,"background-color: #27b6ba; color:#fff; border:none;"
	                ,"background-color:#E0E0E0; color:#9E9E9E; border:none;"
	                ,"background-color:#E0E0E0; color:#9E9E9E; border:none;"]];
	var locker_btn_text = ["프로젝트 보관","프로젝트 보관 해제"]  
	var locker_text = ["완료 혹은 더 이상 사용하지 않는 프로젝트를 보관함으로 옮깁니다.","이 프로젝트를 보관에서 해제합니다."]
 
	$(".mem_add_btn a").click(function(e) {  
		e.preventDefault();

		$.ajax({
			url : "/projectManager/member/memList?wcode=" + wcode,
			type : "get",
			dataType : "json",
			success : function(res) {
				var source = $("#template").html();
				var t_fn = Handlebars.compile(source);
				$("#newProject_drop_menu .memList").html(t_fn(res));
			}
		})  
		$("#newProject_drop_menu input").val("");
		$("#newProject_drop_menu").toggle();
	})

	$("#newProject_drop_menu input[type='text']").keyup(
			function() {
				var name = $(this).val();
				$.ajax({
					url : "/projectManager/member/memList/search?wcode="
							+ wcode + "&name=" + name,
					type : "get",
					dataType : "json",
					success : function(res) {
						var source = $("#template").html();
						var t_fn = Handlebars.compile(source);
						$(".memList").html(t_fn(res));
					}
				})
			})
  
	$("#newProject_drop_menu .memList").on("click", ".mem_li", function(e) {
		e.preventDefault();
		var spanDisplay = $(this).find("span").css("display");
		console.log(spanDisplay);
		if (spanDisplay == "none") {
			$(this).find("span").css("display", "block");
  
			var mem = {
				mno : $(this).attr("data-mno"),
				firstName : $(this).attr("data-firstName"),
				lastName : $(this).attr("data-lastName"),
				photoPath : $(this).attr("data-photoPath")
			};
			var source = $("#addMemTemplate").html();
			var t_fn = Handlebars.compile(source);
			$("#newProjectMemBox").append(t_fn(mem));
		} else {
			var delmno = $(this).attr("data-mno");
			$(this).find("span").css("display", "none");
			
			$("#newProjectMemBox .addMem_item").each(function(i, obj) {
				var mno = $(this).attr("data-mno");
				if (mno == delmno) { 
					$(this).remove();  
				}  
			});
			
			
			$(this).parent(".addMem_item").remove();
		}
	});
	
	/*세팅 드랍 메뉴*/
	$("#setting_drop_menu_admin .memList").on("click", ".mem_li", function(e) {
		e.preventDefault();
		var spanDisplay = $(this).find("span").css("display");
		console.log(spanDisplay);
		
		if (spanDisplay == "none") {
			$(this).find("span").css("display", "block");
			
			var mem = {
				mno : $(this).attr("data-mno"),
				firstName : $(this).attr("data-firstName"),
				lastName : $(this).attr("data-lastName"),
				photoPath : $(this).attr("data-photoPath")
			};
			var source = $("#addMemTemplate_projectSetting").html();
			var t_fn = Handlebars.compile(source);
			$("#setting_addmember_admin").append(t_fn(mem));
			 
			
		} else {    
			var chckCreater = false;
			var delmno = $(this).attr("data-mno");  
			  
			$("#setting_addmember_admin .addMem_item").each(function(i, obj) {
				var mno = $(this).attr("data-mno");
				var maker = $("#side_project_setting").attr("data-maker");  
				
				if (mno == delmno) {
					
					if(maker == mno){
						chckCreater = true;
						return false;  
					}  
					
					$(this).remove();
				} 
			});
			   
			if(chckCreater){
				return false;
			}
			
			$(this).find("span").css("display", "none"); 
			$(this).parent(".addMem_item").remove();
		}
	});  
	
	$("#setting_drop_menu_member .memList").on("click", ".mem_li", function(e) {
		e.preventDefault();
		var spanDisplay = $(this).find("span").css("display");
		console.log(spanDisplay);
		if (spanDisplay == "none") {
			$(this).find("span").css("display", "block");
			
			var mem = {
				mno : $(this).attr("data-mno"),
				firstName : $(this).attr("data-firstName"),
				lastName : $(this).attr("data-lastName"),
				photoPath : $(this).attr("data-photoPath")
			};   
			var source = $("#addMemTemplate_projectSetting").html();
			var t_fn = Handlebars.compile(source);
			$("#setting_addmember_member").append(t_fn(mem));     
		} else {
			var delmno = $(this).attr("data-mno");
			$(this).find("span").css("display", "none");
   
			$("#setting_addmember_member .addMem_item").each(function(i, obj) {
				var mno = $(this).attr("data-mno");
				if (mno == delmno) {
					$(this).remove();
				}
			});
 
			$(this).parent(".addMem_item").remove();
		}
	}); 
	/*세팅 드랍 메뉴 끝*/
	
	$("#addProjectBtn").click(
			function(e) {
				e.preventDefault(); 
				$(".modal_inner_box input").not("input[type='radio']").val("");
				$(".modal_inner_box input[name='visibility']").removeAttr(
						"checked");
				$(".modal_inner_box input[name='visibility']").eq(0).attr(
						"checked", "checked");

				var source = $("#addMemTemplate").html();
				$("#newProject_drop_menu").css("display", "none");
				var t_fn = Handlebars.compile(source);
				$("#newProjectMemBox").html(t_fn(loginMem));

				$(".boxWrap").css("margin-left", "0px");
				$(".choose_img_box img").each(function(i, obj) {
					var tempSrc = $(this).attr("src");
					tempSrc = tempSrc.replace("_b", "");
					$(this).attr("src", tempSrc);
				}); 
				$("#prvImg").attr("src",
						"/projectManager/resources/img/" + imgPath[0]);
				var tempSrc = $(".choose_img_box:eq(0)").find("img")
						.attr("src");
				tempSrc = tempSrc.replace(".png", "_b.png");
				$(".choose_img_box:eq(0)").find("img").attr("src", tempSrc);
				$(".choose_img_box").removeClass("selected");
				$(".choose_img_box").eq(0).addClass("selected");
			})

	$(".closeDropDownBtn").click(function(e) {
		e.preventDefault();
		$(this).parents(".dropdown_menu_setting").toggle();
	})  

	$(".radioWrap").click(function(e) {
		$("input[name='visibility']").removeAttr("checked");
		$(this).find("input[name='visibility']").attr("checked", "checked");
	}) 

	$(".modal_inner_box").on("click", ".delMem", function(e) {
		e.preventDefault();
		var delmno = $(this).parent(".addMem_item").attr("data-mno");

		$(".memList li").each(function(i, obj) {
			var mno = $(this).find(".mem_li").attr("data-mno");
			if (mno == delmno) {
				$(this).find("span").css("display", "none");
			}
		});

		$(this).parent(".addMem_item").remove();
	})

	$("#addPjNextBtn").click(function() {

		if (!checkFrom($("#project_title"))) {
			return false;
		}

		$(".boxWrap").animate({
			marginLeft : '-=648',
		}, 400);
	})
	$("#addPjPrevBtn").click(function() {
		$(".boxWrap").animate({
			marginLeft : '0',
		}, 400);
	})
	$(".choose_img_box").click(
			function() {
				$(".choose_img_box").removeClass("selected");
				$(this).addClass("selected");
				var imgSrc = $(this).find("img").attr("src");
				imgSrc = imgSrc.replace(".png", "_b.png");
				console.log(imgSrc);

				$(".choose_img_box img").each(function(i, obj) {
					var tempSrc = $(this).attr("src");
					tempSrc = tempSrc.replace("_b", "");
					$(this).attr("src", tempSrc);
				});
				var idx = $(this).parent("div").index();
				$("#prvImg").attr("src",
						"/projectManager/resources/img/" + imgPath[idx]);
				$(this).find("img").attr("src", imgSrc);
			})

	$("#makeProject").click(function(){
		var title = $("#project_title").val();
		var explanation = $("#project_explan").val(); 
		var visibility = $(".visibility input:checked").val();
		var mnolist = new Array();   
		 
		$(".addMem_item").each(function(i,obj){
			mnolist.push(Number($(this).attr("data-mno")));
		}) 
		var template = $(".selected").parents(".choose_box").index();
		
		var datas = {
				"title":title,// 타이틀
				"explanation":explanation,// 초대자
				"visibility":visibility,// 초대 워크스페이스
				"wcode":wcode,
				"memList":mnolist,
				"template":Number(template),
				"makerMno":loginMem.mno
		} 
		   
		$.ajax({
			url:"/projectManager/project/make",
			data : JSON.stringify(datas),
			contextType:"application/json",
			headers:{"Content-Type":"application/json"},
			type : "post",
			dataType : "json",   
			success : function(result) {
				console.log(result);
				
				var source = $("#pj_item_template").html();
				var t_fn = Handlebars.compile(source);
				var length = $(".pj_item").length-1;
				$(".pj_item").eq(length).before(t_fn(result));
				  
				$("#addProjectModal").trigger("click");  
			}  
		}) 
		 
	});
	/*-- 프로젝트 세팅 관련   스크립트 시작 --*/
	/* 세팅 스크롤 막기 */
	$("#side_project_setting .sideSettingClose").click(function(e){
		e.preventDefault();      
		$("#side_project_setting").toggle();   
	})  
	 
	$('.settingContent').on('scroll touchmove mousewheel', function(event) {
 
		  if($("#selectStated").css("display")=="block"||
		     $("#select_project_authority").css("display")=="block"||
		     $(".datepicker").css("display")=="block"||
		     $("#setting_drop_menu_admin").css("display")=="block"||
		     $("#setting_drop_menu_member").css("display")=="block"
		  	){
			  event.preventDefault();
			  event.stopPropagation();   
			  return false;		  
		  }	   
		  
	});
	
	/*공개 여부 설정 스위치*/    
	$("#contentWrap").on("click",".setting_switch_btn",function(){
		var float= $(".setting_switch_btn .handler").css("float");
		if(float=="right"){
			$(".setting_switch_btn .handler").css("float","left"); 
			$(".on-label").toggle();
			$(".off-label").toggle();
			$(this).removeClass("on_switch");
		}   
		if(float=="left"){ 
			$(".setting_switch_btn .handler").css("float","right"); 
			$(".on-label").toggle();
			$(".off-label").toggle();
			$(this).addClass("on_switch");
		} 
	})
	 
	/* 프로젝트 상태 */
	$("#project_state").click(function(){
		var obj = $(this).offset();
		console.log("left: " + obj.left + "px, top: " + obj.top + "px");
		var top = obj.top -13;       
		$("#selectStated").css("top",top+"px");
		$("#selectStated").toggle(); 
	})  
	/* 프로젝트 권한 */
	$("#project_access_authority").click(function(){
		var obj = $(this).offset();
		console.log("left: " + obj.left + "px, top: " + obj.top + "px");
		 
		if(obj.top < 800) 
			var top = obj.top -13;    
		else{  
			var thisHeight = $(this).css("height");  
			console.log(thisHeight)      
			var top = obj.top -120 -23 - Number(thisHeight.replace("px","")) ;
		}       
		    
		$("#select_project_authority").css("top",top+"px");
		$("#select_project_authority").toggle(); 
	})   
	/* 프로젝트 상태 샐렉트 리스트*/
	$("#selectStated .custom_select_item").click(function(){
		var status = Number($(this).attr("data-value"));
		$("#project_state").removeAttr("data-value");
		$("#project_state").attr("data-value",status);  
		 
		var text = selval[0][status]+"<i class='color_pic "+selval[1][status]+"'></i>";
		$("#project_state .custom_selected_value").html(text);
		
		var pno = $("#side_project_setting").attr("data-pno");
		$(".pj_item_state[data-pno='"+pno+"']").attr("style",selval[2][status]);
		$(".pj_item_state[data-pno='"+pno+"']").text(selval[0][status])
		$("#selectStated").toggle();      
		                
		$.ajax({       
			url:"/projectManager/project/update/status/"+pno+"/"+status,
			type:"put",
			dataType : "text",   
			success:function(res){
				console.log(res); 
				
			}   
		}) 
		   
	})
	  
	/* 프로젝트 권한 샐렉트 리스트*/
	$("#select_project_authority .custom_select_item").click(function(){
		var val = $(this).attr("data-value");
		var text = $(this).text();        
		
		$("#project_access_authority").siblings("p").html(tipArr[val]);  
		$("#project_access_authority .custom_selected_value").html(text);
   
		$("#project_access_authority").removeAttr("data-value");
		$("#project_access_authority").attr("data-value",val);   
		$("#select_project_authority").toggle();  
	}) 
	 
	/* 데이트 피커 */ 
	$("#startDate").datepicker({
		language: "kr",
	    todayHighlight: true,
	    format: "yyyy-mm-dd"
	});	
	$("#endDate").datepicker({
		language: "kr",
	    todayHighlight: true,
	    format: "yyyy-mm-dd"
	});  
	$("#finishDate").datepicker({  
		language: "kr",
	    todayHighlight: true,
	    format: "yyyy-mm-dd"  
	});  
		
	$("#startDate input").change(function(){
		$(this).css("display","block"); 
		$(this).siblings("button").addClass("insertDate");
		$(".datepicker").remove(); 
		var dateStr = $(this).val();   
		 
		var pno = $("#side_project_setting").attr("data-pno");
		            
		$.ajax({
			url:"/projectManager/project/update/startDate/"+pno,
			type:"put",
			data:dateStr,
			dataType : "text",   
			success:function(res){
				console.log(res); 
			}
		})
	})         
	$("#endDate input").change(function(){
		$(this).css("display","block");  
		$(this).siblings("button").addClass("insertDate");
		$(".datepicker").remove(); 
		var dateStr = $(this).val();   
		 
		var pno = $("#side_project_setting").attr("data-pno");
		            
		$.ajax({
			url:"/projectManager/project/update/endDate/"+pno,
			type:"put",
			data:dateStr,
			dataType : "text",   
			success:function(res){
				console.log(res); 
			}
		})
	})
	              
	$("#finishDate input").change(function(){  
		$(this).css("display","block");  
		$(this).siblings("button").addClass("insertDate");
		$(".datepicker").remove(); 
		var dateStr = $(this).val();   
		 
		var pno = $("#side_project_setting").attr("data-pno");
		            
		$.ajax({
			url:"/projectManager/project/update/finishDate/"+pno,
			type:"put",
			data:dateStr,
			dataType : "text",   
			success:function(res){
				console.log(res); 
			}
		})
	}) 
	
	/*프로젝트 타이틀 설정*/
	$("#project_name_InputText").click(function(){
		$(this).removeAttr("readonly");  
		$(this).focus();  
	}) 
	    
	$("#project_name_InputText").focusout(function(){
		$(this).attr("readonly","readonly");  
		var title = $(this).val(); 
		console.log(title);  
		var pno = $("#side_project_setting").attr("data-pno"); 
		$(".pj_item[data-pno="+pno+"] .pj_title").html(title);
		
		$.ajax({
			url:"/projectManager/project/update/title/"+pno,
			type:"put",
			data:title,
			dataType : "text",   
			success:function(res){
				console.log(res); 
				
			} 
		})       
		
	})      
	     
	$("#project_name_InputText").keydown(function(key) {
		if (key.keyCode == 13) {
			$(this).attr("readonly","readonly"); 
		} 
	});

	/*프로젝트 설명 설정*/
	$("#explanation_InputText").click(function(){
		$(this).removeAttr("readonly");  
		$(this).focus();  
	})  
	 
	$("#explanation_InputText").keydown(function(key) {
		if (key.keyCode == 13) {
			$(this).attr("readonly","readonly"); 
		} 
	});
	
	$("#explanation_InputText").focusout(function(){
		$(this).attr("readonly","readonly");  
		var explanation = $(this).val(); 
		var pno = $("#side_project_setting").attr("data-pno"); 
		 
		$.ajax({
			url:"/projectManager/project/update/explanation/"+pno,
			type:"put",
			data:explanation,
			dataType : "text",   
			success:function(res){
				console.log(res); 
			} 
		})  
	})  
	
	/*프로젝트 설정 : 보관 버튼*/
	$("#project_locker .setting_btn").click(function(){
		var locker = $(this).attr("data-value");
		if(locker == 0){
			$("#project_locker .setting_btn").attr("data-value","1");
			$("#project_locker .setting_btn").addClass("active");
			$("#project_locker .setting_btn").html(locker_btn_text[1]); 
			$("#project_locker p").html(locker_text[1]);
		}else if(locker == 1){  
			$("#project_locker .setting_btn").attr("data-value","0");
			$("#project_locker .setting_btn").removeClass("active");
			$("#project_locker .setting_btn").html(locker_btn_text[0]); 
			$("#project_locker p").html(locker_text[0]);
		}    
	})     
	/*프로젝트 설정 버튼 클릭 : 설정 토글*/
	$(".pjList").on("click",".setting_pj_btn",function(){
		var pno = $(this).attr("data-pno");
		$.ajax({   
			url: "/projectManager/project/select/"+pno,
			type:"get",
			dataType:"json",
			success: function(res){ 
				var project = res.project;
				var member = res.member;     
				console.log(project);   
				console.log(member);
				var sideDisplay= $("#side_project_setting").css("display");
				
				if(sideDisplay=="none"){
					$("#side_project_setting").css("display","block");
				}else{   
					if(Number($("#side_project_setting").attr("data-pno"))==project.pno){
						$("#side_project_setting").css("display","none");
					} 
				}  
				       
				$("#side_project_setting").attr("data-pno",project.pno);  
				$("#side_project_setting").attr("data-maker",project.maker); 
				$("#project_name_InputText").val(project.title); 
				
				var regDate = new Date(project.regDate);
				var maker = project.maker;
				var p = "# 작성자 ";
				$("#setting_addmember_admin").empty();  
				$("#setting_addmember_member").empty();    
				for(var i=0; i<member.length; i++){
					if(maker == member[i].mno){ 
						p += member[i].firstName + " " + member[i].lastName;
					}
					
					var source = $("#addMemTemplate_projectSetting").html(); 
					var t_fn = Handlebars.compile(source); 
					if(member[i].memAssGrade==99 || member[i].memAssGrade == 3)
					$("#setting_addmember_admin").append(t_fn(member[i]));
					else
					$("#setting_addmember_member").append(t_fn(member[i])); 
				} 
				     
				p += " • 작성일 " + (regDate.getMonth()+1) + "월 " + regDate.getDate();
				
				$(".head_cnt").html(p);   
				  
				$("#explanation_InputText").val(project.explanation);
				
				var status = selval[0][project.status]+"<i class='color_pic "+selval[1][project.status]+"'></i>";
				$("#project_state .custom_selected_value").html(status);
				
				var mySimpleDateFormatter = new simpleDateFormat('yyyy-MM-dd');
				$(".date input").val("");
				$(".date input").css("display","none");    
				
				if(project.startDate!=null){  
					$("#startDate input").val(mySimpleDateFormatter.format(new Date(project.startDate)));
					$("#startDate input").css("display","block");
				}  
				
				if(project.endDate!=null){ 
					$("#endDate input").val(mySimpleDateFormatter.format(new Date(project.endDate)));
					$("#endDate input").css("display","block");
				} 
				   
				if(project.finishDate!=null){ 
					$("#finishDate input").val(mySimpleDateFormatter.format(new Date(project.finishDate)));
					$("#finishDate input").css("display","block");
				}  
				   
				if(project.visibility){
					$(".setting_switch_btn").trigger("click");
				}
				
				switch (project.authority) {
					case 0:
						$("#project_access_authority").siblings("p").html(tipArr[0]);  
						var text = $("#select_project_authority .custom_select_item").eq(0).text();
						$("#project_access_authority .custom_selected_value").html(text);
						$("#project_access_authority").removeAttr("data-value");
						$("#project_access_authority").attr("data-value",0);   
						break;
					case 1: 
						$("#project_access_authority").siblings("p").html(tipArr[1]);  
						var text = $("#select_project_authority .custom_select_item").eq(1).text();
						$("#project_access_authority .custom_selected_value").html(text);
						$("#project_access_authority").removeAttr("data-value");
						$("#project_access_authority").attr("data-value",1);   			
						break;   
					case 2:    
						$("#project_access_authority").siblings("p").html(tipArr[2]);
						var text = $("#select_project_authority .custom_select_item").eq(2).text(); 
						$("#project_access_authority .custom_selected_value").html(text);
						$("#project_access_authority").removeAttr("data-value");
						$("#project_access_authority").attr("data-value",2);   
						break;
				} 
				
				if(project.locker){
					$("#project_locker .setting_btn").attr("data-value","1");
					$("#project_locker .setting_btn").addClass("active");
					$("#project_locker .setting_btn").html(locker_btn_text[1]); 
					$("#project_locker p").html(locker_text[1])
				}   
			}       
		})     
	})    
	
	 
	
	/*-- 프로젝트 세팅 관련   스크립트 끝 --*/
});// 제이쿼리 끝

/*세팅 드랍 매뉴 오픈*/
var dropMenuOpen = function(type) {
		$.ajax({
			url : "/projectManager/member/memList?wcode=" + wcode,
			type : "get",
			dataType : "json", 
			success : function(res) {
				if(type=="admin"){ 
					var source = $("#setting_admin_template").html();
					var t_fn = Handlebars.compile(source);   
					$("#setting_drop_menu_admin .memList").html(t_fn(res));
				}else{
					var source = $("#setting_member_template").html();
					var t_fn = Handlebars.compile(source); 
					$("#setting_drop_menu_member .memList").html(t_fn(res));
				}  
			} 
		})           
		
		$(".dropdown_menu_setting input").val("");
		//위치 조정 
		if(type=="admin"){   
			var obj = $("#setting_addmember_admin").prev(".setting_btn").offset();
			console.log("left: " + obj.left + "px, top: " + obj.top + "px");
			            
			$("#setting_drop_menu_admin").css("top", (obj.top-17)+"px");
			$("#setting_drop_menu_admin").toggle();   
		} else if(type=="member"){  
			var obj = $("#setting_addmember_member").prev(".setting_btn").offset();
			console.log("left: " + obj.left + "px, top: " + obj.top + "px");
			var top = obj.top-17;     
			if(628<obj.top){
				top -= 347; 
			}          
			  
			$("#setting_drop_menu_member").css("top", top+"px");
			$("#setting_drop_menu_member").toggle();   
		}
	}       
 

/*세팅 관련 끝*/
   
Handlebars.registerHelper('checkMemAssGrade', function(options) {
	console.log(options); 
	if (options == 99) {
		return "display:none;";
	} else {
		return"display:inline-block;";
	}
}); 
     
Handlebars.registerHelper('checkPhotoPath', function(options) {
	if (options == "" || options == null) {
		return "resources/img/user_icon_b.png";
	} else {
		return "displayFile?filename=" + options;
	}
});
  
Handlebars.registerHelper('checkSelectMember', function(value) {
	var res = "";
	var size = $("#newProjectMemBox .addMem_item").length;
	for (var i = 0; i < size; i++) {
		var mno = $("#newProjectMemBox .addMem_item").eq(i).attr("data-mno");
		if (mno == value) {
			res = 'display:inline-block;';
			break;
		}
	}
	return res;
});       
           
Handlebars.registerHelper('check_setting_admin', function(value) {
	var res = "";
	var size = $("#setting_addmember_admin .addMem_item").length;
	for (var i = 0; i < size; i++) {
		var mno = $("#setting_addmember_admin .addMem_item").eq(i).attr("data-mno");
		if (mno == value) {
			res = 'display:inline-block;';
			break;
		}
	}
	return res;
});

Handlebars.registerHelper('check_setting_member', function(value) {
	var res = "";
	var size = $("#setting_addmember_member .addMem_item").length;
	for (var i = 0; i < size; i++) {
		var mno = $("#setting_addmember_member .addMem_item").eq(i).attr("data-mno");
		if (mno == value) {
			res = 'display:inline-block;';
			break;
		}
	}
	return res;
}); 