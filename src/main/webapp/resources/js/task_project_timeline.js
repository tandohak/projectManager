$(function() {
	var now = new Date();
	var year = now.getFullYear();
	var month = now.getMonth() - 4;
	var date = now.getDate();

	if (month <= 0) {
		year--;
		month += 12;
	}

	if (month < 10) {
		month = "0" + month;
	}
	var startDate = year + "-" + month + "-" + date;
	month++;
	var nowDate = year + "-" + (month) + "-" + date;
	console.log(nowDate);

	$("#timeline").timeline({
		type : "bar",
		scale : "months",
		startDatetime : startDate,
		datetimeFormat : {
			full : "j M Y", // or "Y/m/d" etc.
			year : "Y",
			month : "M Y", // or "F" etc.
			day : "D, j M", // or "j" etc.
			years : "Y",
			months : "F",
			days : "j",
			meta : "Y/m/d H:i",
			metato : ""
		},
		showPointer : true,
		range : 11,
		rows : 17,
		rowHeight : 40,
		height : "auto",
		minGridPer : 2,
		minGridSize : 30,
		rangeAlign : "center",
		langsDir : "/projectManager/resources/js/langs/"
	});

	$("#timeline").on()
	$("#timeline").timeline('show');

	// var data = <li data-timeline-node="{ start:'2018-04-20
	// 10:00',end:'2018-04-28 13:00',content:'Event Here' }">이벤트 a</li>;

	var node_id = 0;
	$.openTaskSetting = function() {
		console.info("인덱스 : " + $('.timeline-node.active').index());
		console.info("아이디 : " + $('.timeline-node.active').attr("id"));
		console.info($('.timeline-node.active').data().taskno);
		var taskno = $('.timeline-node.active').data().taskno;
		node_id = $('.timeline-node.active').attr("id").replace("evt-", "");

		$
				.ajax({
					url : "/projectManager/taskList/select/task/" + taskno,
					type : "get",
					dataType : "json",
					success : function(res) {
						var task = res.task
						var member = res.member;

						console.log(task);
						console.log(member);

						var sideDisplay = $("#side_task_setting")
								.css("display");

						if (sideDisplay == "none") {
							$("#side_task_setting").css("display", "block");
						} else {
							if (Number($("#side_task_setting").attr(
									"data-taskno")) == task.taskno) {
								$("#side_task_setting").css("display", "none");
							}
						}

						$("#side_task_setting")
								.attr("data-taskno", task.taskno);
						$("#task_name_InputText").val(task.taskname);

						var regDate = new Date(task.regDate);
						var writer = task.writer;
						var p = "# 생성자 " + writer;
						$("#setting_task_addmember_member").empty();
						for (var i = 0; i < member.length; i++) {
							var source = $("#addTaskMemTemplate").html();
							var t_fn = Handlebars.compile(source);

							$("#setting_task_addmember_member").append(
									t_fn(member[i]));

						}

						p += " • 작성일 " + (regDate.getMonth() + 1) + "월 "
								+ regDate.getDate();

						$("#task_name_InputText").siblings(".head_cnt").html(p);

						var explanation = task.explanation;
						if (explanation == null)
							explanation = "";

						$("#task_explanation_InputText")
								.val(explanation.trim());

						var mySimpleDateFormatter = new simpleDateFormat(
								'yyyy-MM-dd kk:mm');
						$("#side_task_setting  .date input").val("");
						$("#side_task_setting  .date input").css("display",
								"none");
						$("#side_task_setting  .date button").removeClass(
								"insertDate");

						if (task.startDate != null) {
							$("#task_startDate input").val(
									mySimpleDateFormatter.format(new Date(
											task.startDate)));
							$("#task_startDate input").css("display", "block");
							$("#task_startDate button").addClass("insertDate");
						}

						if (task.endDate != null) {
							$("#task_endDate input").val(
									mySimpleDateFormatter.format(new Date(
											task.endDate)));
							$("#task_endDate input").css("display", "block");
							$("#task_endDate button").addClass("insertDate");
						}

						if (task.finishDate != null) {
							$("#task_finishDate input").val(
									mySimpleDateFormatter.format(new Date(
											task.finishDate)));
							$("#task_finishDate input").css("display", "block");
							$("#task_finishDate button").addClass("insertDate");
						}

					}
				});

	};
  
	$("#task_startDate input").change(function() {
		var taskno =  $("#side_task_setting").attr("data-taskno");
		var startDate = $(this).val();
		var endDate = $("#task_endDate input").val();
		var label = $("#task_name_InputText").val();
		console.log(node_id);
		$("#timeline").timeline('updateEvent', [ {
			eventId : Number(node_id),
			start : startDate,
			end : endDate,
			row : Number(node_id),
			label : label,
			extend:{'taskno':taskno},
			callback:'$.openTaskSetting()'
		/*
		 * bgColor : '#aaaab0', color : '#d70035'
		 */
		} ]);
	})
      
	$("#task_endDate input").change(function() {
		var taskno =  $("#side_task_setting").attr("data-taskno");
		var startDate = $("#task_startDate input").val();
		var endDate = $(this).val();
		var label = $("#task_name_InputText").val();
		console.log(node_id);
		$("#timeline").timeline('updateEvent', [ {
			eventId : Number(node_id),
			start : startDate,
			end : endDate,
			row : Number(node_id),
			label : label,
			extend:{'taskno':taskno},
			callback:'$.openTaskSetting()'
		/*
		 * bgColor : '#aaaab0', color : '#d70035'
		 */
		} ]); 
	})

	$("#task_name_InputText").change(function() {
		var startDate = $("#task_startDate input").val();
		var endDate = $("#task_endDate input").val();
		var label = $(this).val();
		var taskno =  $("#side_task_setting").attr("data-taskno"); 
		console.log(node_id);   
		$(".tasks li a[data-taskno='"+taskno+"']").html(label);

		$("#timeline").timeline('updateEvent', [ {
			eventId : Number(node_id),
			start : startDate,
			end : endDate,
			row : Number(node_id),
			label : label,  
			extend:{'taskno':taskno},
			callback:'$.openTaskSetting()'
		/*
		 * bgColor : '#aaaab0', color : '#d70035'
		 */  
		}]);   
	})     
	  
	/*작업 삭제 버튼*/  
	$("#delete_task_btn_timeline").click(function(){
		var taskno = $("#side_task_setting").attr("data-taskno");
		$.ajax({ 
			url : "/projectManager/taskList/delete/task/" + taskno,
			type : "delete",  
			dataType : "text",
			success : function(res) {
				console.log(res);  
				console.log(node_id);   
				if(res=="success"){    
					$(".task_item[data-taskno='"+taskno+"']").remove();  
					$("#timeline").timeline('removeEvent', [ Number(node_id) ]);
					$(".tasks li a[data-taskno='"+taskno+"']").parent("li").remove();
					
				}  
			}            
		})   
	})      
	 $(".task_item").click(function(){
		 var taskno = $(this).attr("data-taskno");
		   
		 $(".timeline-node[data-taskno='"+taskno+"']").trigger("click");
	 })
})