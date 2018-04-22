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
						 
						$(".color_label_btn").removeClass("color_label_pick");
						if(task.colorLabel!=null || task.colorLabel != ""){
							for(var i=0; i<$(".color_label_picker").length; i++){
								var pickerClass = $(".color_label_picker").eq(i).attr("class")  
								pickerClass =pickerClass.substring(pickerClass.lastIndexOf("_")+1);   
								console.log(pickerClass+ " / "+ task.colorLabel);
								if(pickerClass == task.colorLabel){
									$(".color_label_picker").eq(i).parent(".color_label_btn").addClass("color_label_pick");
								}
							} 
						}      
					}
				});

	};
  
	$("#task_startDate input").change(function() {
		changeNode();
	})
      
	$("#task_endDate input").change(function() {
		changeNode();
	})

	$("#task_name_InputText").change(function() {
		changeNode();
	})     
	
	var changeNode = function(){
		var startDate = $("#task_startDate input").val();
		var endDate = $("#task_endDate input").val();
		var label = $("#task_name_InputText").val();
		var taskno =  $("#side_task_setting").attr("data-taskno"); 
		console.log(node_id);   
		$(".tasks li a[data-taskno='"+taskno+"']").html(label);
		
		var colorLabel = $(".color_label_pick div").attr("class");
		
		var bgColor = "#dddddd";
		
		if(colorLabel != undefined){
			colorLabel = colorLabel.split(" ");   
			var i = jQuery.inArray(colorLabel[1],timelineLabelArr[0]);
			bgColor = timelineLabelArr[1][i]; 
		}  
		
		$("#timeline").timeline('updateEvent', [ {
			eventId : Number(node_id), 
			start : startDate,
			end : endDate,  
			row : Number(node_id),
			label : label,  
			extend:{'taskno':taskno},
			callback : '$.openTaskSetting()', 
			bgColor :bgColor ,
			color : "#ffffff"
		}]);   
	}   
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
	  
	 //컬러 라벨  
	 $("#color-label-timeline .color_label_btn").click(
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
								$(".task_item[data-taskno='" + taskno + "']").removeClass(taskLabeArr[i]);
							}
							changeNode();
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
							changeNode();
						}  
					})
				}

			})
})