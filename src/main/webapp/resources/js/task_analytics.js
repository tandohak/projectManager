$(function(){
	
	/*경과 시간*/
	console.log(startDate);
	
	elapsedTimeFun(); 
	
	/*draw chart*/
	
	// 나에게 배정된 업무
	google.charts.load('current', {'packages':['corechart']});
    google.charts.setOnLoadCallback(drawChartAssignment);
  
    function drawChartAssignment() {
 
      var data = google.visualization.arrayToDataTable(data_assignment);   
  
      var options = {    
        pieHole: 0.87,      
        pieSliceTextStyle: 'none',
        legend: 'none',
        colors: ['#27B6BA', '#E95E51', '#FFB024', '#B0B4BB'] 
      };   
 
      var chart = new google.visualization.PieChart(document.getElementById('assignment_donut_chart'));
      chart.draw(data, options);
    }
    
    // 나에게 배정된 업무
	google.charts.load('current', {'packages':['corechart']});
    google.charts.setOnLoadCallback(drawChartMakeMe);

    function drawChartMakeMe() {
   
      var data = google.visualization.arrayToDataTable(data_makeMe);   
  
      var options = {    
        pieHole: 0.87,      
        pieSliceTextStyle: 'none',
        legend: 'none',
        colors: ['#27B6BA', '#E95E51', '#FFB024', '#B0B4BB'] 
      };   
 
      var chart = new google.visualization.PieChart(document.getElementById('makeMe_donut_chart'));
      chart.draw(data, options);
    }
    
	
	//데이터 피커 토글
	$("#analytics_startDate").click(function() {
		dropDatePicker($(this));
	})  
	//삭제 버튼 클릭
	$("#analytics_startDate .analytics_date_delbtn").click(function(e){
		e.stopPropagation();
		$target_set = $("#analytics_startDate");  
		$("#sandbox-container .del_date").trigger("click");
	}) 
	
	$("#analytics_endDate").click(function() {
		dropDatePicker($(this));
	})  
	//삭제 버튼 클릭
	$("#analytics_endDate .analytics_date_delbtn").click(function(e){
		e.stopPropagation();
		$target_set = $("#analytics_endDate");  
		$("#sandbox-container .del_date").trigger("click");
	}) 
	
	$("#analytics_finishDate").click(function() {
		dropDatePicker($(this));
	})  
	    
	//삭제 버튼 클릭
	$("#analytics_finishDate .analytics_date_delbtn").click(function(e){
		e.stopPropagation();
		$target_set = $("#analytics_finishDate");  
		$("#sandbox-container .del_date").trigger("click");
	})     
	  
	$("#analytics_startDate input").change(function(){
 
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
				startDate = new Date(dateStr);
				
				elapsedTimeFun(); 
				
				dataStr = dateStr.split("-");
				var month = dataStr[1];
				
				var date = dataStr[2].split(" ");
			 	
				$("#analytics_startDate .analytics_date").html(month+"월 " + date[0] + "일");
				$("#analytics_startDate .analytics_date_addBtn").css("display","none");
				$("#analytics_startDate .analytics_date_wrap").css("display","inline-block");
			}  
		})   
	})    
	
	$("#startDate input").change(function(){
		var dateStr = $(this).val();
		dataStr = dateStr.split("-"); 
		var month = dataStr[1];
		
		var date = dataStr[2].split(" ");
	 	
		$("#analytics_startDate .analytics_date").html(month+"월 " + date[0] + "일");
		$("#analytics_startDate .analytics_date_addBtn").css("display","none");
		$("#analytics_startDate .analytics_date_wrap").css("display","inline-block");
	})          
	
	$("#analytics_endDate input").change(function() {

		var dateStr = $(this).val();
		endDate = new Date(dateStr);
    
		var pno = $("#side_project_setting").attr("data-pno");

		$.ajax({
			url : "/projectManager/project/update/endDate/" + pno,
			type : "put",
			data : dateStr,
			dataType : "text",
			success : function(res) {
				console.log(res);
				dataStr = dateStr.split("-");
				var month = dataStr[1];
				
				elapsedTimeFun(); 
				
				var date = dataStr[2].split(" ");
				
				$("#analytics_endDate .analytics_date").html(month+"월 " + date[0] + "일");
				$("#analytics_endDate .analytics_date_addBtn").css("display","none");
				$("#analytics_endDate .analytics_date_wrap").css("display","inline-block");
			}
		})
	})  
	  
	$("#endDate input").change(function(){

		var dateStr = $(this).val();
		dataStr = dateStr.split("-"); 
		var month = dataStr[1];
		
		var date = dataStr[2].split(" ");
	 	
		$("#analytics_endDate .analytics_date").html(month+"월 " + date[0] + "일");
		$("#analytics_endDate .analytics_date_addBtn").css("display","none");
		$("#analytics_endDate .analytics_date_wrap").css("display","inline-block");
	})
	
	$("#analytics_finishDate input").change(function() {

		var dateStr = $(this).val();

		var pno = $("#side_project_setting").attr("data-pno");

		$.ajax({
			url : "/projectManager/project/update/finishDate/" + pno,
			type : "put",
			data : dateStr,
			dataType : "text",
			success : function(res) {
				console.log(res);
				dataStr = dateStr.split("-");
				var month = dataStr[1];
				 
				var date = dataStr[2].split(" ");
				 
				$("#analytics_finishDate .analytics_date").html(month+"월 " + date[0] + "일");
				$("#analytics_finishDate .analytics_date_addBtn").css("display","none");
				$("#analytics_finishDate .analytics_date_wrap").css("display","inline-block");
			}  
		})    
	})  
	
	$("#finishDate input").change(function(){

		var dateStr = $(this).val();
		dataStr = dateStr.split("-"); 
		var month = dataStr[1];
		
		var date = dataStr[2].split(" ");
	 	
		$("#analytics_finishDate .analytics_date").html(month+"월 " + date[0] + "일");
		$("#analytics_finishDate .analytics_date_addBtn").css("display","none");
		$("#analytics_finishDate .analytics_date_wrap").css("display","inline-block");
	})
	 
	
})
var elapsedTimeFun = function(){
	var fullDay = 0; // 총 프로젝트 기간
	//생성 날짜
	
	//경과 시간  
	var elapsedTimeStr = "-"; 
	var elapsedTime = 0;
	if(startDate != null){  
		var nowDate = new Date();
		var ms = nowDate  - startDate;
		
		var s =  ms/1000;
		var m =  s/60;
		var h =  m/60;
		
		if(h >= 24){ 
			elapsedTime = Math.floor(h/24);  
			elapsedTimeStr = elapsedTime+"일"; 
		} 
	}    
	//남은 시간    
	var timeRemainingStr = "-";
	var timeRemaining = 0;
	if(endDate != null){  
		var ms = 0;
		if(startDate != null){  
			ms = endDate  - startDate;  
		}else{
			ms = endDate  - regDate;
		} 
		 
		var s =  ms/1000;
		var m =  s/60;
		var h =  m/60;
		
		if(h >= 24){  
			fullDay  = Math.floor(h/24);  
			timeRemaining =(fullDay-elapsedTime);  
			
			if(timeRemaining>0){
				timeRemainingStr = timeRemaining+"일";
			} 
		}else{
			timeRemainingStr = "1일"; 
		}  
	}  
	     
	//퍼센트
	var elapsedTimePercent = Math.round((elapsedTime/(timeRemaining+elapsedTime))*100); //경과시간 퍼센트
	var timeRemainingPercent =  Math.round((timeRemaining/(timeRemaining+elapsedTime))*100);; //남은시간 퍼센트
	    
	if(elapsedTime>0){
		elapsedTimeStr += "("+ elapsedTimePercent + "%)"
	}
	  
	if(timeRemaining>0 && startDate !=null){
		timeRemainingStr += "("+ timeRemainingPercent + "%)"
	}   
	 
	$("#elapsedTime").html(elapsedTimeStr); 
	$("#timeRemaining").html(timeRemainingStr); 
}     
  
