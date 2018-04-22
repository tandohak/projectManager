$(function(){
	$("#analytics_startDate").click(function() {
		dropDatePicker($(this));
	})   
	
	$("#analytics_endDate").click(function() {
		dropDatePicker($(this));
	})  
	
	$("#analytics_finishDate").click(function() {
		dropDatePicker($(this));
	})  
	
	$("#analytics_startDate input").change(function(){
		$(this).css("display", "block");
		$(this).siblings("button").addClass("insertDate");

		var dateStr = $(this).val();
		console.log(dateStr); 
		var pno = $("#side_project_setting").attr("data-pno");

		$.ajax({
			url : "/projectManager/project/update/startDate/" + pno,
			type : "put",
			data : dateStr,
			dataType : "text",
			success : function(res) {
				console.log(res);
				var  dataStr = dateStr.split("-");
				var month = dataStr[1];
				if(month<10){
					month = month.replace("0","");
				}
				var date = dataStr[2].split(" ");
				 
				$("#analytics_startDate .analytics_date").html(month+"월 " + date[0] + "일");
				$("#analytics_startDate .analytics_date_addBtn").css("display","none");
				$("#analytics_startDate .analytics_date_wrap").css("display","inline-block");
			}  
		})   
	})            
	
	$("#analytics_endDate input").change(function() {
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
				var  dataStr = dateStr.split("-");
				var month = dataStr[1];
				if(month<10){
					month = month.replace("0","");
				}
				var date = dataStr[2].split(" ");
				 
				$("#analytics_endDate .analytics_date").html(month+"월 " + date[0] + "일");
				$("#analytics_endDate .analytics_date_addBtn").css("display","none");
				$("#analytics_endDate .analytics_date_wrap").css("display","inline-block");
			}
		})
	})
	  
	$("#analytics_finishDate input").change(function() {
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
				var  dataStr = dateStr.split("-");
				var month = dataStr[1];
				if(month<10){
					month = month.replace("0","");
				}
				var date = dataStr[2].split(" ");
				 
				$("#analytics_finishDate .analytics_date").html(month+"월 " + date[0] + "일");
				$("#analytics_finishDate .analytics_date_addBtn").css("display","none");
				$("#analytics_finishDate .analytics_date_wrap").css("display","inline-block");
			}  
		})    
	}) 
	
})  
    