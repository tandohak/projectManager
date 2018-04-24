var jobAssList = [];
var taskLabeArr = [ "task_label_purple", "task_label_blue",
		"task_label_skyblue", "task_label_green", "task_label_amber",
		"task_label_pink", "task_label_red", "task_label_orange",
		"task_label_brown" ];

var timelineLabelArr = [  
		[ "color_label_purple", "color_label_blue", "color_label_skyblue",
				"color_label_green", "color_label_amber", "color_label_pink",
				"color_label_red", "color_label_orange", "color_label_brown" ],
		[ "#a876cc", "#4882ab", "#3caee2", "#62c276", "#ffb024", "#f75496",
				"#e95e51", "#fa7f29", "#a0744c" ] ];
$(function() {
	/* 프로젝트 설정 버튼 클릭 : 설정 토글 */
	$("#project_setting_btn") 
			.click(
					function(e) {
						e.preventDefault();
						var pno = $(this).attr("data-pno");
						$
								.ajax({
									url : "/projectManager/project/select/"
											+ pno,
									type : "get",
									dataType : "json",
									success : function(res) {
										var project = res.project;
										var member = res.member;
										console.log(project);
										console.log(member);
										var sideDisplay = $(
												"#side_project_setting").css(
												"display");

										if (sideDisplay == "none") {
											$("#side_project_setting").css(
													"display", "block");
										} else {
											if (Number($(
													"#side_project_setting")
													.attr("data-pno")) == project.pno) {
												$("#side_project_setting").css(
														"display", "none");
											}
										}

										$("#side_project_setting").attr(
												"data-pno", project.pno);
										$("#side_project_setting").attr(
												"data-maker", project.maker);
										$("#project_name_InputText").val(
												project.title);

										var regDate = new Date(project.regDate);
										var maker = project.maker;
										var p = "# 작성자 ";
										$("#setting_addmember_admin").empty();
										$("#setting_addmember_member").empty();
										for (var i = 0; i < member.length; i++) {
											if (maker == member[i].mno) {
												p += member[i].firstName + " "
														+ member[i].lastName;
											}

											var source = $(
													"#addMemTemplate_projectSetting")
													.html();
											var t_fn = Handlebars
													.compile(source);
											if (member[i].memAssGrade == 99
													|| member[i].memAssGrade == 3)
												$("#setting_addmember_admin")
														.append(t_fn(member[i]));
											else
												$("#setting_addmember_member")
														.append(t_fn(member[i]));

										}

										p += " • 작성일 "
												+ (regDate.getMonth() + 1)
												+ "월 " + regDate.getDate();

										$(".head_cnt").html(p);

										var explanation = project.explanation;
										$("#explanation_InputText").val(
												explanation.trim());

										var status = selval[0][project.status]
												+ "<i class='color_pic "
												+ selval[1][project.status]
												+ "'></i>";
										$(
												"#project_state .custom_selected_value")
												.html(status);

										var mySimpleDateFormatter = new simpleDateFormat(
												'yyyy-MM-dd kk:mm');
										$("#side_project_setting  .date input")
												.val("");
										$("#side_project_setting  .date input")
												.css("display", "none");
										$("#side_project_setting  .date button")
												.removeClass("insertDate");

										if (project.startDate != null) {
											$("#startDate input")
													.val(
															mySimpleDateFormatter
																	.format(new Date(
																			project.startDate)));
											$("#startDate input").css(
													"display", "block");
											$("#startDate button").addClass(
													"insertDate");
										}

										if (project.endDate != null) {
											$("#endDate input")
													.val(
															mySimpleDateFormatter
																	.format(new Date(
																			project.endDate)));
											$("#endDate input").css("display",
													"block");
											$("#endDate button").addClass(
													"insertDate");
										}

										if (project.finishDate != null) {
											$("#finishDate input")
													.val(
															mySimpleDateFormatter
																	.format(new Date(
																			project.finishDate)));
											$("#finishDate input").css(
													"display", "block");
											$("#finishDate button").addClass(
													"insertDate");
										}

										if (project.visibility) {
											$(
													"#side_project_setting .setting_switch_btn .handler")
													.css("float", "right");
											$("#side_project_setting .on-label")
													.css("display", "block");
											$(
													"#side_project_setting .off-label")
													.css("display", "none");
											$(
													"#side_project_setting .setting_switch_btn")
													.addClass("on_switch");
										} else {
											$(
													"#side_project_setting .setting_switch_btn .handler")
													.css("float", "left");
											$("#side_project_setting .on-label")
													.css("display", "none");
											$(
													"#side_project_setting .off-label")
													.css("display", "block");
											$(
													"#side_project_setting .setting_switch_btn")
													.removeClass("on_switch");
										}

										switch (project.authority) {
										case 0:
											$("#project_access_authority")
													.siblings("p").html(
															tipArr[0]);
											var text = $(
													"#select_project_authority .custom_select_item")
													.eq(0).text();
											$(
													"#project_access_authority .custom_selected_value")
													.html(text);
											$("#project_access_authority")
													.removeAttr("data-value");
											$("#project_access_authority")
													.attr("data-value", 0);
											break;
										case 1:
											$("#project_access_authority")
													.siblings("p").html(
															tipArr[1]);
											var text = $(
													"#select_project_authority .custom_select_item")
													.eq(1).text();
											$(
													"#project_access_authority .custom_selected_value")
													.html(text);
											$("#project_access_authority")
													.removeAttr("data-value");
											$("#project_access_authority")
													.attr("data-value", 1);
											break;
										case 2:
											$("#project_access_authority")
													.siblings("p").html(
															tipArr[2]);
											var text = $(
													"#select_project_authority .custom_select_item")
													.eq(2).text();
											$(
													"#project_access_authority .custom_selected_value")
													.html(text);
											$("#project_access_authority")
													.removeAttr("data-value");
											$("#project_access_authority")
													.attr("data-value", 2);
											break;
										}

										if (project.locker) {
											$("#project_locker .setting_btn")
													.attr("data-value", "1");
											$("#project_locker .setting_btn")
													.addClass("active");
											$("#project_locker .setting_btn")
													.html(locker_btn_text[1]);
											$("#project_locker p").html(
													locker_text[1])
										} else {
											$("#project_locker .setting_btn")
													.attr("data-value", "0");
											$("#project_locker .setting_btn")
													.removeClass("active");
											$("#project_locker .setting_btn")
													.html(locker_btn_text[0]);
											$("#project_locker p").html(
													locker_text[0])
										}
									}
								})
					})

	/* 작업 생성 */
	$(".taskBox_wrap").on("click", ".add_task_btn", function() {
		var parent = $(this).parents(".taskBox_header");
		jobAssList = [];
		$(".addMemberDropMenu .counter").css("display", "none");

		if (parent.css("overflow") == "hidden") {// 토글 오프
			$(".taskBox_header").removeAttr("style");
			$(".addDatepickerOpen input").css("display", "none");
			$(".addDatepickerOpen input").val("");
			parent.find("textarea").val("");
			$(".taskBox_wrap .taskBox_header_addTask").css("display", "none");
		} else {// 토글 온
			$(".taskBox_header").removeAttr("style");
			$(".addDatepickerOpen input").css("display", "none");
			$(".taskBox_wrap .taskBox_header_addTask").css("display", "none");
			parent.css("overflow", "hidden");
			parent.find(".taskBox_header_addTask").css("display", "block");
		}

	})

	/* 작업에 멤버 추가 */
	$("#addTask_member_dropdown .memList").on("click", ".mem_li", function(e) {
		e.preventDefault();
		var spanDisplay = $(this).find("span").css("display");

		if (spanDisplay == "none") {
			$(this).find("span").css("display", "block");
			/*
			 * var mem = { mno : $(this).attr("data-mno"), massno :
			 * $(this).attr("data-massno") };
			 */
			jobAssList.push($(this).attr("data-massno"));
			console.log(jobAssList);
		} else {
			$(this).find("span").css("display", "none");
			console.log($(this).attr("data-massno"));
			for (var i = 0; i < jobAssList.length; i++) {
				if (jobAssList[i] == $(this).attr("data-massno")) {
					jobAssList.splice(i, 1);
				}
			}

		}

		if (jobAssList.length > 0) {
			$(".addMemberDropMenu .counter").html(jobAssList.length);
			$(".addMemberDropMenu .counter").css("display", "block");
		} else {
			$(".addMemberDropMenu .counter").css("display", "none");
		}

	});
	 // 드랍매뉴 검색  
	$("#addTask_member_dropdown input[type='text']").keyup(
			function() {
				var name = $(this).val();
				$.ajax({
					url : "/projectManager/member/memAssList/search?pno="
							+ pno + "&name=" + name,
					type : "get",
					dataType : "json",
					success : function(res) {
						var source = $("#addTask_member_member_template").html();
						var t_fn = Handlebars.compile(source);
						$("#addTask_member_dropdown .memList").html(t_fn(res));
					}
				})      
			})
			
	$(".taskBox_wrap").on("click", ".addMemberDropMenu", function() {
		$.ajax({
			url : "/projectManager/member/memAssList/" + pno,
			type : "get",
			dataType : "json",
			success : function(res) {
				var source = $("#addTask_member_member_template").html();
				var t_fn = Handlebars.compile(source);
				$("#addTask_member_dropdown .memList").html(t_fn(res));
			}
		})
		$("#addTask_member_dropdown .dropdown_menu_setting input").val("");
		// 위치 조정

		var obj = $(this).offset();
		console.log("left: " + obj.left + "px, top: " + obj.top + "px");

		$("#addTask_member_dropdown").css("top", (obj.top + 34) + "px");
		$("#addTask_member_dropdown").css("left", obj.left + "px");
		$("#addTask_member_dropdown").toggle();

	});  
	// 테스크 생성 취소
	$(".taskBox_wrap").on("click", ".add_task_cancle", function() {
		$(".taskBox_header").removeAttr("style");
		$(".addDatepickerOpen input").css("display", "none");
		$(".addDatepickerOpen input").val("");
		$(".taskBox_wrap textarea").val("");
		$(".taskBox_wrap .taskBox_header_addTask").css("display", "none");
	})
	// 테스크 데이트피커
	$(".taskBox_wrap").on("click", ".addDatepickerOpen", function() {
		dropDatePicker($(this));
	})

	$(".taskBox_wrap").on("change", ".addDatepickerOpen input", function() {
		$(this).css("display", "inline-block");
	});

	// 테스크 추가   
	$(".taskBox_wrap").on(
			"click",
			".add_task_make",
			function() {
				var $parent = $(this).parents(".taskBox_header_addTask");
				var taskBox = $parent.parents(".taskBox");
				var taskname = $parent.find("textarea").val();
				var endDate = $parent.find(".task_endDate").val();
				var tlno = Number($(this).attr("data-tlno"));
				   
				if (taskname == "" || taskname == null) {
					alert("업무명을 입력하세요.")
					return false;
				}
				console.log(loginMem.firstName);
				console.log(loginMem.lastName);
				var datas = {
					"jobAssList" : jobAssList,
					"taskname" : taskname,
					"endDate" : endDate,
					"tlno" : tlno,
					"writer" : loginMem.firstName + " " + loginMem.lastName,
					"massno" : loginMem.massno
				}
 
				$.ajax({
					url : "/projectManager/taskList/register/taskmake",
					data : JSON.stringify(datas),
					contentType : "application/json",
					type : "post",
					success : function(res) {
						if (res != null) {
							console.log(res);
							var source = $("#addTask_template").html();
							var t_fn = Handlebars.compile(source);
							taskBox.find(".taskBox_body .processing_task_box")
									.append(t_fn(res));
							taskBox.find(".taskBox_footer").css("display",
									"block");
							var task_count = taskBox.find(".taskBox_footer i");
							var count = Number(task_count.text()) + 1;

							task_count.html(count);
						} else {
							alert("실패");
						}
					}
				})

				jobAssList = [];
				$(".taskBox_header").removeAttr("style");
				$(".addDatepickerOpen input").css("display", "none");
				$(".addDatepickerOpen input").val("");
				$(".taskBox_wrap textarea").val("");
				$(".taskBox_wrap .taskBox_header_addTask").css("display",
						"none");
			})

	// 업무 리스트 생성
	$(".addTaskList_btn").click(function() {
		$(this).css("display", "none");
		$(".addTaskListWrap").css("display", "block");
		$(".addTaskListWrap input").focus();
	})
	$(".cancle_addTaskList").click(function() {
		$(".addTaskList_btn").css("display", "block");
		$(".addTaskListWrap").css("display", "none");
		$(".addTaskListWrap input").val("");
	});

	$(".addTaskListWrap input").focusout(function() {
		if ($(this).val() == "") {
			$(".cancle_addTaskList").trigger("click");
			return false;
		}
		var taskListName = $(this).val();
		var list_order = $(".taskBox").length - 1;

		var datas = {
			"name" : taskListName,
			"pno" : pno,
			"list_order" : list_order
		};

		$.ajax({
			url : "/projectManager/taskList/register/taskListmake",
			data : JSON.stringify(datas),
			contentType : "application/json",
			type : "post",
			success : function(res) {
				console.log(res);
				if (res != null) {
					console.log(res);
					var source = $("#addTaskList_template").html();
					var t_fn = Handlebars.compile(source);
					$(".taskBox_wrap .taskBox_add").before(t_fn(res));
					$(".cancle_addTaskList").trigger("click");
				} else {
					alert("실패");
				}
			}
		})
	})

	$(".taskBox_wrap").on("click", ".deleteListBtn", function() {
		var tlno = Number($(this).attr("data-tlno"));
		var parent = $(this).parents(".taskBox");
		$.ajax({
			url : "/projectManager/taskList/delete/taskList/" + tlno,
			type : "delete",
			success : function(res) {
				console.log(res);
				if (res == "success") {
					parent.remove();
				}
			}
		})

	})

	/* 업무 체크박스 클릭시 완료 */
	$(".taskBox_wrap")
			.on(
					"click",
					".finish_task",
					function(e) {
						e.stopPropagation();
						var $task_item = $(this).parent(".task_item");
						var $body = $task_item.parents(".taskBox_body");
						var taskno = Number($task_item.attr("data-taskno"));
						var $footer = $body.parents(".taskBox").find(
								".taskBox_footer");
						var count = Number($footer.find("i").text());

						if ($(this).is(":checked")) {
							$
									.ajax({
										url : "/projectManager/taskList/update/taskStatus",
										data : JSON.stringify({
											"taskno" : taskno,
											"status" : 1
										}),
										type : "put",
										dataType : "text",
										headers : {
											"Content-Type" : "application/json"
										},
										contentType : "application/json",
										success : function(res) {
											console.log(res);
											if (res == "success") {
												if ($body.find(".finished").length <= 0) {
													$body.find(".finish-title")
															.css("display",
																	"block");
												}

												$task_item.addClass("finished");
												$body
														.find(
																".finished_task_box")
														.append($task_item);
												$footer.find("i").html(
														count - 1);
											}
										}
									}) 
						} else {
							$
									.ajax({
										url : "/projectManager/taskList/update/taskStatus",
										data : JSON.stringify({
											"taskno" : taskno,
											"status" : 0
										}),
										type : "patch",
										dataType : "text",
										headers : {
											"Content-Type" : "application/json"
										},
										contentType : "application/json",
										success : function(res) {
											console.log(res);
											if (res == "success") {
												if ($body.find(".finished").length == 1) {
													$body.find(".finish-title")
															.css("display",
																	"none");
												}

												$task_item
														.removeClass("finished");
												$body.find(
														".processing_task_box")
														.append($task_item);
												$footer.find("i").html(
														count + 1);
											}
										}
									})
						}

					})

	$("#side_task_setting .sideSettingClose").click(function() {
		$("#side_task_setting").css("display", "none");
	})

	/* 업무 클릭 : 설정 토글 */
	$(".taskBox_wrap").on("click",".task_item",function() {
			var taskno = $(this).attr("data-taskno");
			$.ajax({
				url : "/projectManager/taskList/select/task/"+ taskno,
				type : "get",
				dataType : "json",
				success : function(res) {
					var task = res.task
					var member = res.member;
					
					console.log(task);
					console.log(member);
					var sideDisplay = $("#side_task_setting").css("display");

					if (sideDisplay == "none") {
						$("#side_task_setting").css("display", "block");
					} else {
						if (Number($("#side_task_setting").attr("data-taskno")) == task.taskno) {
							$("#side_task_setting").css("display", "none");
						}
					}

					$("#side_task_setting").attr("data-taskno", task.taskno);
					$("#task_name_InputText").val(task.taskname);

					var regDate = new Date(task.regDate);
					var writer = task.writer;
					var p = "# 생성자 " + writer;
					$("#setting_task_addmember_member").empty();
					for (var i = 0; i < member.length; i++) {
						var source = $("#addTaskMemTemplate").html();
						var t_fn = Handlebars.compile(source);

						$("#setting_task_addmember_member").append(t_fn(member[i]));

					}

					p += " • 작성일 "+ (regDate.getMonth() + 1)+ "월 " + regDate.getDate();

					$("#task_name_InputText").siblings(".head_cnt").html(p);

					var explanation = task.explanation;
					if (explanation == null)
						explanation = "";

					$("#task_explanation_InputText").val(explanation.trim());

					var mySimpleDateFormatter = new simpleDateFormat('yyyy-MM-dd kk:mm');
					
					$("#side_task_setting  .date input").val("");
					$("#side_task_setting  .date input").css("display", "none");
					$("#side_task_setting  .date button").removeClass("insertDate");

					if (task.startDate != null) {
						$("#task_startDate input").val(mySimpleDateFormatter.format(new Date(task.startDate)));
						$("#task_startDate input").css("display", "block");
						$("#task_startDate button").addClass("insertDate");
					}

					if (task.endDate != null) {
						$("#task_endDate input").val(mySimpleDateFormatter.format(new Date(task.endDate)));
						$("#task_endDate input").css("display", "block");
						$("#task_endDate button").addClass("insertDate");
					}

					if (task.finishDate != null) {
						$("#task_finishDate input").val(mySimpleDateFormatter.format(new Date(task.finishDate)));
						$("#task_finishDate input").css("display", "block");
						$("#task_finishDate button").addClass("insertDate");
					}

					$(".color_label_btn").removeClass("color_label_pick");
					if (task.colorLabel != null|| task.colorLabel != "") {
						for (var i = 0; i < $(".color_label_picker").length; i++) {
							var pickerClass = $(".color_label_picker").eq(i).attr("class")
							pickerClass = pickerClass.substring(pickerClass.lastIndexOf("_") + 1);
							
							console.log(pickerClass + " / " + task.colorLabel);
							if (pickerClass == task.colorLabel) {
								$(".color_label_picker").eq(i).parent(".color_label_btn").addClass("color_label_pick");
							}
					}
				}
			}
		});  
	});    

	// 업무 시작날 설정
	$("#task_startDate").click(function() {
		dropDatePicker($(this));
	})

	// 업무 마감날 설정
	$("#task_endDate").click(function() {
		dropDatePicker($(this));
	})

	// 업무 완료일 설정
	$("#task_finishDate").click(function() {
		dropDatePicker($(this));
	})

	$("#task_startDate input").change(function() {
		$(this).css("display", "block");
		$(this).siblings("button").addClass("insertDate");

		var dateStr = $(this).val();

		var taskno = $("#side_task_setting").attr("data-taskno");
		$.ajax({
			url : "/projectManager/taskList/update/startDate/" + taskno,
			type : "put",
			data : dateStr,
			dataType : "text",
			success : function(res) {
				console.log(res);
			}
		})
	})
	$("#task_endDate input").change(function() {
		$(this).css("display", "block");
		$(this).siblings("button").addClass("insertDate");

		var dateStr = $(this).val();
		var taskno = $("#side_task_setting").attr("data-taskno");

		$.ajax({
			url : "/projectManager/taskList/update/endDate/" + taskno,
			type : "put",
			data : dateStr,
			dataType : "text",
			success : function(res) {
				console.log(res);
			}
		})

	})

	$("#task_finishDate input").change(function() {
		$(this).css("display", "block");
		$(this).siblings("button").addClass("insertDate");

		var dateStr = $(this).val();
		var dateStr = $(this).val();
		var taskno = $("#side_task_setting").attr("data-taskno");

		$.ajax({
			url : "/projectManager/taskList/update/finishDate/" + taskno,
			type : "put",
			data : dateStr,
			dataType : "text",
			success : function(res) {
				console.log(res);
			}
		})
	})

	$("#task_setting_addMember").click(function() {
			$.ajax({
				url : "/projectManager/member/memAssList/" + pno,
				type : "get",
				dataType : "json",
				success : function(res) {
					var source = $("#addTask_member_setting_template").html();
					var t_fn = Handlebars.compile(source);
					$("#addTask_member_setting_dropdown .memList").html(t_fn(res));
				}
			})
			$("#addTask_member_setting_dropdown input").val("");
			// 위치 조정
			var obj = $(this).offset();
			console.log("left: " + obj.left + "px, top: " + obj.top + "px");
			$("#addTask_member_setting_dropdown").css("top",(obj.top + 34) + "px");
			$("#addTask_member_setting_dropdown").css("left",obj.left + "px");
			$("#addTask_member_setting_dropdown").toggle();

	});
 
	/* 작업 설정에 멤버 추가 */
	$("#addTask_member_setting_dropdown .memList").on(
			"click",
			".mem_li",
			function(e) {
				e.preventDefault();
				var spanDisplay = $(this).find("span").css("display");
				var taskno = $("#side_task_setting").attr("data-taskno");

				if (spanDisplay == "none") {
					$(this).find("span").css("display", "block");

					var mem = {
						mno : $(this).attr("data-mno"),
						massno : $(this).attr("data-massno"),
						firstName : $(this).attr("data-firstName"),
						lastName : $(this).attr("data-lastName"),
						photoPath : $(this).attr("data-photoPath")
					};
					console.log("--------");
					console.log("taskno" + taskno);
					console.log("massno" + mem.massno);
					$.ajax({
						url : "/projectManager/taskList/insert/jobassignment/",
						data : JSON.stringify({
							"taskno" : taskno,
							"massno" : mem.massno
						}),
						headers : {
							"Content-Type" : "application/json"
						},
						contentType : "application/json",
						type : "post",
						dataType : "text",
						success : function(res) {
							console.log(res);
						}
					})

					var source = $("#addTaskMemTemplate").html();
					var t_fn = Handlebars.compile(source);
					$("#setting_task_addmember_member").append(t_fn(mem));
				} else {
					var delmassno = $(this).attr("data-massno");
					$(this).find("span").css("display", "none");
					console.log($(this).attr("data-massno"));
					$("#setting_task_addmember_member .addMem_item").each(
							function(i, obj) {
								var massno = $(this).attr("data-massno");
								if (massno == delmassno) {
									$(this).remove();
								}
							});

					$.ajax({
						url : "/projectManager/taskList/delete/jobassignment/",
						data : JSON.stringify({
							"taskno" : taskno,
							"massno" : $(this).attr("data-massno")
						}),
						headers : {
							"Content-Type" : "application/json"
						},
						contentType : "application/json",
						type : "delete",
						dataType : "text",
						success : function(res) {
							console.log(res);
						}
					})
				}
			});

	$("#setting_task_addmember_member").on("click", ".delMem", function(e) {
		e.preventDefault();
		var delmassno = $(this).parent(".addMem_item").attr("data-massno");
		var taskno = $("#side_task_setting").attr("data-taskno");
		$(".memList li").each(function(i, obj) {
			var massno = $(this).find(".mem_li").attr("data-massno");
			if (massno == delmassno) {
				$(this).find("span").css("display", "none");
			}
		});

		$(this).parent(".addMem_item").remove();

		$.ajax({
			url : "/projectManager/taskList/delete/jobassignment/",
			data : JSON.stringify({
				"taskno" : taskno,
				"massno" : delmassno
			}),
			headers : {
				"Content-Type" : "application/json"
			},
			contentType : "application/json",
			type : "delete",
			dataType : "text",
			success : function(res) {
				console.log(res);
			}
		})

	});

	/* 프로젝트 타이틀 설정 */
	$("#task_name_InputText").click(function() {
		$(this).removeAttr("readonly");
		$(this).focus();
	})

	$("#task_name_InputText").focusout(function() {
		$(this).attr("readonly", "readonly");
		var title = $(this).val();
		console.log(title);
		var taskno = $("#side_task_setting").attr("data-taskno");
		$(".task_item[data-taskno=" + taskno + "] .task_name").html(title);

		$.ajax({
			url : "/projectManager/taskList/update/title/" + taskno,
			type : "put",
			data : title,
			dataType : "text",
			success : function(res) {
				console.log(res);

			}
		})

	})

	$("#task_name_InputText").keydown(function(key) {
		if (key.keyCode == 13) {
			$(this).attr("readonly", "readonly");
			$(this).focusout();
		}
	});

	/* 작업 설명 설정 */
	$("#task_explanation_InputText").click(function() {
		$(this).removeAttr("readonly");
		$(this).focus();
	})

	$("#task_explanation_InputText").keydown(function(key) {
		if (key.keyCode == 13) {
			$(this).attr("readonly", "readonly");
		}
	});

	$("#task_explanation_InputText").focusout(function() {
		$(this).attr("readonly", "readonly");
		var explanation = $(this).val();
		if ($(this).val() == "" || $(this).val() == null) {
			explanation = " ";
		}

		var taskno = $("#side_task_setting").attr("data-taskno");

		$.ajax({
			url : "/projectManager/taskList/update/explanation/" + taskno,
			type : "put",
			data : explanation,
			dataType : "text",
			success : function(res) {
				console.log(res);
			}
		})
	})

	/* 작업 정렬 변경 */
	$(".taskBox_wrap").sortable(
			{
				axis : "x",
				containment : "parent",
				update : function(event, ui) {
					var order = $(this).sortable('toArray', {
						attribute : 'data-order'
					});
					console.log(order);
					var size = $(".taskBox").length - 1;

					for (var i = 0; i < size; i++) {
						$(".taskBox").eq(i).attr("data-order", i);
						var tlno = $(".taskBox").eq(i).attr("data-tlno");
						console.log("tlno : " + tlno + " / order :" + i);

						$.ajax({
							url : "/projectManager/taskList/update/taskList/"+ tlno + "/" + i,
							type : "patch",
							dataType : "text",
							success : function(res) {
								console.log(res);
							}
						});
					}
					;
				}
			});
   
	/* 작업 삭제 버튼 */
	$("#delete_task_btn").click(function() {
		var taskno = $("#side_task_setting").attr("data-taskno");
		$.ajax({
			url : "/projectManager/taskList/delete/task/" + taskno,
			type : "delete",
			dataType : "text",
			success : function(res) {
				console.log(res);
				if (res == "success") {
					$(".task_item[data-taskno='" + taskno + "']").remove();
				}
			}
		})
	})

	/* 작업 컬러 라벨 */
	$("#color-label .color_label_btn").click(
			function() {
				var taskno = $("#side_task_setting").attr("data-taskno");
				var pic = $(this).attr("class");
				console.log(pic);
				if (pic.indexOf("color_label_pick") > 0) {// 선택된 라벨 해제
					$(this).removeClass("color_label_pick");
					$.ajax({
						url : "/projectManager/taskList/update/task/label/"+ taskno,
						type : "patch",
						data : " ",
						dataType : "text",
						success : function(res) {
							console.log(res);
							for (var i = 0; i < taskLabeArr.length; i++) {
								$(".task_item[data-taskno='" + taskno + "']")
										.removeClass(taskLabeArr[i]);
							}
						}
					})
				} else {// 라벨 선택
					$(".color_label_btn").removeClass("color_label_pick");
					$(this).addClass("color_label_pick");
					var color = $(this).find("div").attr("class");

					color = color.split(" ");
					color = color[1].split("_");
					console.log(color[2]);
					$.ajax({
						url : "/projectManager/taskList/update/task/label/"+ taskno,
						type : "patch",
						data : color[2],
						dataType : "text",
						success : function(res) {  
							console.log(res);  

							for (var i = 0; i < taskLabeArr.length; i++) {
								if (taskLabeArr[i].indexOf(color[2]) > 0) {
									$(".task_item[data-taskno='" + taskno+ "']").addClass(taskLabeArr[i]);
								} else {
									$(".task_item[data-taskno='" + taskno+ "']").removeClass(taskLabeArr[i]);
								}
							}
						}  
					})
				}

			})
})