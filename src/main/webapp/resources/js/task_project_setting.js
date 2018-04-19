var imgPath = [ "preview-blank-f9e6c665.png", "preview-weekly-ko-f26d30dd.png",
		"preview-people-ko-60882c1d.png", "preview-department-ko-8f933e90.png",
		"preview-todo-ko-d07476ee.png" ];
var tipArr = [
		"<strong>전체 엑세스</strong>: 모든 프로젝트 팀원은 프로젝트 안에 있는 모든 업무 보기, 수정이 가능합니다.",
		"<strong>제한 엑세스</strong>: 프로젝트 팀원은 업무, 제목, 설명, 위치, 시작일, 마감일, 배정에 대해 추가/수정을 할 수 없습니다.",
		"<strong>통제 엑세스</strong>: 프로젝트 팀원은 자신이 배정되어있거나 팔로워인 업무만 볼 수 있습니다." ];
var selval = [
		[ "상태 없음", "계획됨", "진행중", "완료됨", "보류", "취소" ],
		[ " ", "state_color_planed", "state_color_proceeding",
				"state_color_completed", " ", " " ],
		[ " ", "background-color: #ffb024; color:#fff; border:none;",
				"background-color: #62c276; color:#fff; border:none;",
				"background-color: #27b6ba; color:#fff; border:none;",
				"background-color:#E0E0E0; color:#9E9E9E; border:none;",
				"background-color:#E0E0E0; color:#9E9E9E; border:none;" ] ];
var locker_btn_text = [ "프로젝트 보관", "프로젝트 보관 해제" ];
var locker_text = [ "완료 혹은 더 이상 사용하지 않는 프로젝트를 보관함으로 옮깁니다.",
		"이 프로젝트를 보관에서 해제합니다." ];

$(function() {
	/* 세팅 드랍 메뉴 */
	$("#setting_drop_menu_admin .memList")
			.on(
					"click",
					".mem_li",
					function(e) {
						e.preventDefault();
						var spanDisplay = $(this).find("span").css("display");

						var pno = $("#side_project_setting").attr("data-pno");

						if (spanDisplay == "none") {
							$(this).find("span").css("display", "block");

							var mem = {
								mno : $(this).attr("data-mno"),
								firstName : $(this).attr("data-firstName"),
								lastName : $(this).attr("data-lastName"),
								photoPath : $(this).attr("data-photoPath")
							};
							var source = $("#addMemTemplate_projectSetting")
									.html();
							var t_fn = Handlebars.compile(source);
							$("#setting_addmember_admin").append(t_fn(mem));

							$("#setting_addmember_member .addMem_item").each(
									function(i, obj) {
										var mno = $(this).attr("data-mno");

										if (mno == mem.mno) {
											$(this).remove();
										}
									})

							$
									.ajax({
										url : "/projectManager/project/update/memAssGrade/"
												+ pno + "/" + mem.mno + "/" + 3,
										type : "patch",
										dataType : "text",
										success : function(res) {
											console.log(res);
										}
									})
						} else {
							var chckCreater = false;
							var delmno = $(this).attr("data-mno");

							$("#setting_addmember_admin .addMem_item")
									.each(
											function(i, obj) {
												var mno = $(this).attr(
														"data-mno");
												var maker = $(
														"#side_project_setting")
														.attr("data-maker");

												if (mno == delmno) {

													if (maker == mno) {
														chckCreater = true;
														return false;
													}

													$
															.ajax({
																url : "/projectManager/project/update/memAssGrade/"
																		+ pno
																		+ "/"
																		+ mno
																		+ "/"
																		+ 1,
																type : "patch",
																dataType : "text",
																success : function(
																		res) {
																	console
																			.log(res);
																}
															})

													$(this).remove();
												}
											});

							if (chckCreater) {
								return false;
							}

							var mem = {
								mno : $(this).attr("data-mno"),
								firstName : $(this).attr("data-firstName"),
								lastName : $(this).attr("data-lastName"),
								photoPath : $(this).attr("data-photoPath")
							};

							var source = $("#addMemTemplate_projectSetting")
									.html();
							var t_fn = Handlebars.compile(source);
							$("#setting_addmember_member").append(t_fn(mem));

							$(this).find("span").css("display", "none");
							$(this).parent(".addMem_item").remove();
						}
					});

	$("#setting_drop_menu_member .memList").on(
			"click",
			".mem_li",
			function(e) {
				e.preventDefault();
				var spanDisplay = $(this).find("span").css("display");
				var pno = $("#side_project_setting").attr("data-pno");

				if (spanDisplay == "none") {
					var maker = $("#side_project_setting").attr("data-maker");

					if ($(this).attr("data-mno") == maker)
						return false;

					$(this).find("span").css("display", "block");

					var mem = {
						mno : $(this).attr("data-mno"),
						firstName : $(this).attr("data-firstName"),
						lastName : $(this).attr("data-lastName"),
						photoPath : $(this).attr("data-photoPath")
					};

					$("#setting_addmember_admin .addMem_item").each(
							function(i, obj) {
								var mno = $(this).attr("data-mno");

								if (mno == mem.mno) {
									$(this).remove();
								}
							})

					$.ajax({
						url : "/projectManager/project/update/memAssGrade/"
								+ pno + "/" + mem.mno + "/" + 1,
						type : "patch",
						dataType : "text",
						success : function(res) {
							console.log(res);
						}
					})

					var source = $("#addMemTemplate_projectSetting").html();
					var t_fn = Handlebars.compile(source);
					$("#setting_addmember_member").append(t_fn(mem));
				} else {
					var delmno = $(this).attr("data-mno");
					$(this).find("span").css("display", "none");

					$("#setting_addmember_member .addMem_item").each(
							function(i, obj) {
								var mno = $(this).attr("data-mno");
								if (mno == delmno) {
									$(this).remove();
								}
							});

					$.ajax({
						url : "/projectManager/project/delete/memAssGrade/"
								+ pno + "/" + delmno,
						type : "patch",
						dataType : "text",
						success : function(res) {
							console.log(res);
						}
					})

					$(this).parent(".addMem_item").remove();
				}
			});

	$("#setting_addmember_admin").on(
			"click",
			".delMem",
			function(e) {
				e.preventDefault();
				var delmno = $(this).parent(".addMem_item").attr("data-mno");
				var pno = $("#side_project_setting").attr("data-pno");
				$(".memList li").each(function(i, obj) {
					var mno = $(this).find(".mem_li").attr("data-mno");
					if (mno == delmno) {
						$(this).find("span").css("display", "none");
					}
				});

				$("#setting_addmember_member").append(
						$(this).parent(".addMem_item"));

				$.ajax({
					url : "/projectManager/project/update/memAssGrade/" + pno
							+ "/" + delmno + "/" + 1,
					type : "patch",
					dataType : "text",
					success : function(res) {
						console.log(res);
					}
				})
			})

	$("#setting_addmember_member").on(
			"click",
			".delMem",
			function(e) {
				e.preventDefault();
				var delmno = $(this).parent(".addMem_item").attr("data-mno");
				var pno = $("#side_project_setting").attr("data-pno");

				$(".memList li").each(function(i, obj) {
					var mno = $(this).find(".mem_li").attr("data-mno");
					if (mno == delmno) {
						$(this).find("span").css("display", "none");
					}
				});

				$(this).parent(".addMem_item").remove();
				
				$.ajax({
					url : "/projectManager/project/delete/memAssGrade/" + pno
							+ "/" + delmno,
					type : "patch",
					dataType : "text",
					success : function(res) {
						console.log(res);
					}
				})
			})
	/* 세팅 드랍 메뉴 끝 */

	/*-- 프로젝트 세팅 관련   스크립트 시작 --*/
	/* 세팅 스크롤 막기 */
	$("#side_project_setting .sideSettingClose").click(function(e) {
		e.preventDefault();
		$("#side_project_setting").toggle();
	})

	$('.settingContent')
			.on(
					'scroll touchmove mousewheel',
					function(event) {

						if ($("#selectStated").css("display") == "block"
								|| $("#select_project_authority").css("display") == "block"
								|| $("#sandbox-container").css("display") == "block"
								|| $("#setting_drop_menu_admin").css("display") == "block"
								|| $("#setting_drop_menu_member")
										.css("display") == "block") {
							event.preventDefault();
							event.stopPropagation();
							return false;
						}

					});

	/* 공개 여부 설정 스위치 */
	$("#contentWrap").on(
			"click",
			".setting_switch_btn",
			function() {
				var float = $(".setting_switch_btn .handler").css("float");
				var pno = $("#side_project_setting").attr("data-pno");

				if (float == "right") {
					$(".setting_switch_btn .handler").css("float", "left");
					$(".on-label").toggle();
					$(".off-label").toggle();
					$(this).removeClass("on_switch");
					$.ajax({
						url : "/projectManager/project/update/visibility/"
								+ pno + "/false",
						type : "put",
						dataType : "text",
						success : function(res) {
							console.log(res);

						}
					})
				}
				if (float == "left") {
					$(".setting_switch_btn .handler").css("float", "right");
					$(".on-label").toggle();
					$(".off-label").toggle();
					$(this).addClass("on_switch");
					$.ajax({
						url : "/projectManager/project/update/visibility/"
								+ pno + "/true",
						type : "put",
						dataType : "text",
						success : function(res) {
							console.log(res);

						}
					})
				}
			})

	/* 프로젝트 상태 */
	$("#project_state").click(function() {
		var obj = $(this).offset();
		console.log("left: " + obj.left + "px, top: " + obj.top + "px");
		var top = obj.top - 64;
		$("#selectStated").css("top", top + "px");
		$("#selectStated").toggle();
	})
	/* 프로젝트 권한 */
	$("#project_access_authority")
			.click(
					function() {
						var obj = $(this).offset();
						console.log("left: " + obj.left + "px, top: " + obj.top
								+ "px");

						if (obj.top < 800)
							var top = obj.top - 64;
						else {
							var thisHeight = $(this).css("height");
							console.log(thisHeight)
							var top = obj.top - 171 - 23
									- Number(thisHeight.replace("px", ""));
						}

						$("#select_project_authority").css("top", top + "px");
						$("#select_project_authority").toggle();
					})
	/* 프로젝트 상태 샐렉트 리스트 */
	$("#selectStated .custom_select_item").click(
			function() {
				var status = Number($(this).attr("data-value"));
				$("#project_state").removeAttr("data-value");
				$("#project_state").attr("data-value", status);

				var text = selval[0][status] + "<i class='color_pic "
						+ selval[1][status] + "'></i>";
				$("#project_state .custom_selected_value").html(text);

				var pno = $("#side_project_setting").attr("data-pno");
				$(".pj_item_state[data-pno='" + pno + "']").attr("style",
						selval[2][status]);
				$(".pj_item_state[data-pno='" + pno + "']").text(
						selval[0][status])
				$("#selectStated").toggle();

				$.ajax({
					url : "/projectManager/project/update/status/" + pno + "/"
							+ status,
					type : "put",
					dataType : "text",
					success : function(res) {
						console.log(res);

					}
				})

			})

	/* 프로젝트 권한 샐렉트 리스트 */
	$("#select_project_authority .custom_select_item").click(
			function() {
				var val = $(this).attr("data-value");
				var text = $(this).text();

				var pno = $("#side_project_setting").attr("data-pno");
				$("#project_access_authority").siblings("p").html(tipArr[val]);
				$("#project_access_authority .custom_selected_value")
						.html(text);
				$("#project_access_authority").removeAttr("data-value");
				$("#project_access_authority").attr("data-value", val);
				$("#select_project_authority").toggle();

				$.ajax({
					url : "/projectManager/project/update/authority/" + pno
							+ "/" + val,
					type : "put",
					dataType : "text",
					success : function(res) {
						console.log(res);

					}
				})
			})

	/* 데이트 피커 */
	/*
	 * $("#startDate").datepicker({ language: "kr", todayHighlight: true,
	 * format: "yyyy-mm-dd" }); $("#endDate").datepicker({ language: "kr",
	 * todayHighlight: true, format: "yyyy-mm-dd" });
	 * $("#finishDate").datepicker({ language: "kr", todayHighlight: true,
	 * format: "yyyy-mm-dd" });
	 */

	$("#startDate").click(function() {
		dropDatePicker($(this));
	})

	$("#endDate").click(function() {
		dropDatePicker($(this));
	})

	$("#finishDate").click(function() {
		dropDatePicker($(this));
	})
	
	$("#startDate input").change(function() {
		$(this).css("display", "block");
		$(this).siblings("button").addClass("insertDate");

		var dateStr = $(this).val();

		var pno = $("#side_project_setting").attr("data-pno");

		$.ajax({
			url : "/projectManager/project/update/startDate/" + pno,
			type : "put",
			data : dateStr,
			dataType : "text",
			success : function(res) {
				console.log(res);
			}
		})
	})
	$("#endDate input").change(function() {
		$(this).css("display", "block");
		$(this).siblings("button").addClass("insertDate");

		var dateStr = $(this).val();

		var pno = $("#side_project_setting").attr("data-pno");

		$.ajax({
			url : "/projectManager/project/update/endDate/" + pno,
			type : "put",
			data : dateStr,
			dataType : "text",
			success : function(res) {
				console.log(res);
			}
		})
	})

	$("#finishDate input").change(function() {
		$(this).css("display", "block");
		$(this).siblings("button").addClass("insertDate");

		var dateStr = $(this).val();

		var pno = $("#side_project_setting").attr("data-pno");

		$.ajax({
			url : "/projectManager/project/update/finishDate/" + pno,
			type : "put",
			data : dateStr,
			dataType : "text",
			success : function(res) {
				console.log(res);
			}
		})
	})
		
	/* 프로젝트 타이틀 설정 */
	$("#project_name_InputText").click(function() {
		$(this).removeAttr("readonly");
		$(this).focus();
	})

	$("#project_name_InputText").focusout(function() {
		$(this).attr("readonly", "readonly");
		var title = $(this).val();
		console.log(title);
		var pno = $("#side_project_setting").attr("data-pno");
		$(".pj_item[data-pno=" + pno + "] .pj_title").html(title);
		$("#pj_title").html(title);
		$.ajax({
			url : "/projectManager/project/update/title/" + pno,
			type : "put",
			data : title,
			dataType : "text",
			success : function(res) {
				console.log(res);

			}
		})

	})

	$("#project_name_InputText").keydown(function(key) {
		if (key.keyCode == 13) {
			$(this).attr("readonly", "readonly");
			$(this).focusout();
		}
	});

	/* 프로젝트 설명 설정 */
	$("#explanation_InputText").click(function() {
		$(this).removeAttr("readonly");
		$(this).focus();
	})

	$("#explanation_InputText").keydown(function(key) {
		if (key.keyCode == 13) {
			$(this).attr("readonly", "readonly");
		}
	});

	$("#explanation_InputText").focusout(function() {
		$(this).attr("readonly", "readonly");
		var explanation = $(this).val();
		if ($(this).val() == "" || $(this).val() == null) {
			explanation = " ";
		}
		var pno = $("#side_project_setting").attr("data-pno");

		$.ajax({
			url : "/projectManager/project/update/explanation/" + pno,
			type : "put",
			data : explanation,
			dataType : "text",
			success : function(res) {
				console.log(res);
			}
		})
	})

	/* 프로젝트 설정 : 보관 버튼 */
	$("#project_locker .setting_btn").click(
			function() {
				var locker = $(this).attr("data-value");
				var pno = $("#side_project_setting").attr("data-pno");

				if (locker == 0) {
					$("#project_locker .setting_btn").attr("data-value", "1");
					$("#project_locker .setting_btn").addClass("active");
					$("#project_locker .setting_btn").html(locker_btn_text[1]);
					$("#project_locker p").html(locker_text[1]);
					$.ajax({
						url : "/projectManager/project/update/locker/" + pno
								+ "/true",
						type : "put",
						dataType : "text",
						success : function(res) {
							console.log(res);
						}
					})
				} else if (locker == 1) {
					$("#project_locker .setting_btn").attr("data-value", "0");
					$("#project_locker .setting_btn").removeClass("active");
					$("#project_locker .setting_btn").html(locker_btn_text[0]);
					$("#project_locker p").html(locker_text[0]);
					$.ajax({
						url : "/projectManager/project/update/locker/" + pno
								+ "/false",
						type : "put",
						dataType : "text",
						success : function(res) {
							console.log(res);

						}
					})
				}
			})

	$(".radioWrap").click(function(e) {
		$("input[name='visibility']").removeAttr("checked");
		$(this).find("input[name='visibility']").attr("checked", "checked");
	})

	$(".closeDropDownBtn").click(function(e) {
		e.preventDefault();
		$(this).parents(".dropdown_menu_setting").toggle();
	})
    
	$(".dropdown_menu_setting input[type='text']").keyup(
			function() {
				var name = $(this).val();
				$.ajax({
					url : "/projectManager/member/memList/search?wcode="
							+ wcode + "&name=" + name,
					type : "get",
					dataType : "json",
					success : function(res) {
						var source = $("#setting_admin_template").html();
						var t_fn = Handlebars.compile(source);
						$(".memList").html(t_fn(res));
					}
				})
			})
			
	$("#sandbox-container .add_date").click(function() {  
		var selectDate = $("#sandbox-container .selectDate").text();
		var hour = $("#sandbox-container .hour").val();
		var minute = $("#sandbox-container .minute").val();

		$target_set.find("input").val(selectDate + " " + hour + ":" + minute);
		$target_set.find("input").change();
		$("#sandbox-container").css("display", "none");
	})
	/*-- 프로젝트 세팅 관련   스크립트 끝 --*/ 
	$('#sandbox-container div.datePicker_cst').datepicker({
		language : "kr",
		todayHighlight : true,
		clearBtn : true
	}).on("changeDate", function(e) {
		var monthYear = $("#sandbox-container div.datePicker_cst .datepicker-switch").html();
		console.log(monthYear);   
	 
		var month = monthYear.substring("0","1");
		if(month < 10){
			month = "0" + month;
		}
		var year = monthYear.substring("2");
		            
		var date = $("#sandbox-container div.datePicker_cst .active").html();
		
		if(date==null || date==undefined){
			date = $("#sandbox-container div.datePicker_cst .today").html();
		}     
		if(date < 10){
			date = "0" + date;
		}  
		console.log(year+"-"+month+"-"+date);     
		$("#sandbox-container .selectDate").html(year+"-"+month+"-"+date);
	});        
	               
	$("#sandbox-container div.datePicker_cst .clear").css("display","none");
	    
	$("#sandbox-container .del_date").click(function() {
		$target_set.find("input").val("");  
		$target_set.find("input").css("display","none");
		$target_set.find("button").removeClass("insertDate"); 
		$("#sandbox-container").css("display", "none");
		
		var targetId = $target_set.attr("id");
		console.log(targetId); 
		if(targetId != undefined){   
			if(targetId.indexOf("task") <= -1){//프로젝트 설정
				var pno = $("#side_project_setting").attr("data-pno");
				$.ajax({ 
					url : "/projectManager/project/update/"+targetId+"/" + pno,
					type : "put",  
					data : "del",
					dataType : "text",  
					success : function(res) { 
						console.log(res);
					}           
				})       
			}else if(targetId.indexOf("task") > -1){//task 설정 
				var taskno = $("#side_task_setting").attr("data-taskno");
				targetId = targetId.replace("task_","");
				 
				$.ajax({      
					url : "/projectManager/taskList/update/"+targetId +"/"+ taskno,
					type : "put",  
					data : "del",
					dataType : "text",  
					success : function(res) { 
						console.log(res);
					}           
				})  	
			}              
		}  
		     
	})                   
})// 제워키리 끝     
var $target_set = null; 
var dropDatePicker = function($target) { 
	$("#sandbox-container div.datePicker_cst .clear").trigger("click");
	
	$target_set = $target;
	var targetId = $target.attr("id");
	console.log(targetId); 
	if(targetId == "startDate")
		$("#sandbox-container .datepicker_title strong").html("시작일 설정");
	else if(targetId == "endDate")
		$("#sandbox-container .datepicker_title strong").html("마감일 설정");
	else if(targetId == "finishDate")
		$("#sandbox-container .datepicker_title strong").html("실제 완료일 설정");
	else if(targetId == undefined)
		$("#sandbox-container .datepicker_title strong").html("마감일 설정");
	    
	
	var obj = $target.offset();
	// console.log("left: " + obj.left + "px, top: " + obj.top + "px");

	var width = $target.find("input").css("width").replace("px", "");
	// console.log(width);
	$("#sandbox-container").css("top", (obj.top + 35) + "px");
	if (obj.left > 700) {
		$("#sandbox-container").css("left", (obj.left - 100) + "px");
	} else if (obj.left > 800) {
		$("#sandbox-container").css("left", (obj.left - 200) + "px");
	} else if (obj.left > 900) {
		$("#sandbox-container").css("left", (obj.left - 300) + "px");
	} else if (obj.left > 1000) {
		$("#sandbox-container").css("left", (obj.left - 400) + "px");
	} else {
		$("#sandbox-container").css("left", (obj.left) + "px");
	}
 
	 
	if ($("#sandbox-container").css("display") == "block") { 
		$("#sandbox-container").css("display", "none");
	} else {    
		$("#sandbox-container").css("display", "block"); 
		
	 
	}  
 
	
	$("#sandbox-container .close_datepicker").click(function(){
		$("#sandbox-container").css("display", "none");
	}) 
	            
	$("#sandbox-container .hour").keyup(function(){
		var reg = /^[0-9]{0,2}$/; 
		if(!reg.test($(this).val())){ 
			$(this).val("00");   
		}   
	})    
	
	$("#sandbox-container .hour").focusout(function(){
		var  num = $(this).val();
		var reg = /^[0-9]{0,2}$/; 
		if(!reg.test($(this).val())){ 
			$(this).val("00");     
		}   
		 
		if(num > 23){ 
			$(this).val("00");      
		}
		
		if(num<10){  
			$(this).val("0"+Number(num));
		}   
	})      
	    
	$("#sandbox-container .minute").keyup(function(){
		var reg = /^[0-9]{0,2}$/; 
		if(!reg.test($(this).val())){ 
			$(this).val("00");   
		}   
	})           
	     
	$("#sandbox-container .minute").focusout(function(){
		var reg = /^[0-9]{0,2}$/; 
		if(!reg.test($(this).val())){ 
			$(this).val("00");   
		}     
		var  num = $(this).val();
		if(num > 59){
			$(this).val("00");   
			var hour = Number($("#sandbox-container .hour").val())+1;
			if(hour < 10){
				hour = "0" + hour;
			} 
			$("#sandbox-container .hour").val(hour);  
		}
		
		if(num<10){  
			$(this).val("0"+Number(num));
		}   
	})  
}       

/* 세팅 드랍 매뉴 오픈 */
var dropMenuOpen = function(type) {
	$.ajax({
		url : "/projectManager/member/memList?wcode=" + wcode,
		type : "get",
		dataType : "json",
		success : function(res) {
			if (type == "admin") {
				var source = $("#setting_admin_template").html();
				var t_fn = Handlebars.compile(source);
				$("#setting_drop_menu_admin .memList").html(t_fn(res));
			} else {
				var source = $("#setting_member_template").html();
				var t_fn = Handlebars.compile(source);
				$("#setting_drop_menu_member .memList").html(t_fn(res));
			}
		}
	})

	$(".dropdown_menu_setting input").val("");
	// 위치 조정
	if (type == "admin") {
		var obj = $("#setting_addmember_admin").prev(".setting_btn").offset();
		console.log("left: " + obj.left + "px, top: " + obj.top + "px");
  
		$("#setting_drop_menu_admin").css("top", (obj.top - 69) + "px");
		$("#setting_drop_menu_admin").css("left", 159 + "px");  
		$("#setting_drop_menu_admin").toggle();
	} else if (type == "member") {
		var obj = $("#setting_addmember_member").prev(".setting_btn").offset();
		console.log("left: " + obj.left + "px, top: " + obj.top + "px"); 
		var top = obj.top - 69;
		if (628 < obj.top) {
			top -= 345;  
		}          
		$("#setting_drop_menu_member").css("left", 159 + "px");
		$("#setting_drop_menu_member").css("top", top + "px");
		$("#setting_drop_menu_member").toggle();
	}  
}

/* 세팅 관련 끝 */
