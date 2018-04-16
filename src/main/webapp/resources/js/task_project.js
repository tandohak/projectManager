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
	var login_mem_pj_grade = 1;
	
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
	  
	var click_setting = false;
	$(".pjList").on("click",".setting_pj_btn",function(){  
		click_setting = true;
	}); 
	         
	//pj_item - 프로젝트 클릭시 해당 페이지로 이동
	$(".pjList").on("click",".pj_item",function(){ 
		if(click_setting){
			click_setting = false;
			return;
		}
		
		var id = $(this).attr("id");
		console.log(id);  
		if("addProjectBtn" == id){
			console.log("스탑");
			return;
		}
		 
		var pno = $(this).attr("data-pno");  
		location.href = "/projectManager/task/"+wcode+"/project/"+pno; 
	});
});// 제이쿼리 끝
