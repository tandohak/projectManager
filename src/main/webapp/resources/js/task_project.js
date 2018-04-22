$(function() {
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
	  
	/* 프로젝트 설정 버튼 클릭 : 설정 토글 */
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

				var explanation = project.explanation;  
				$("#explanation_InputText").val(explanation.trim());
				
				var status = selval[0][project.status]+"<i class='color_pic "+selval[1][project.status]+"'></i>";
				$("#project_state .custom_selected_value").html(status);
				   
				var mySimpleDateFormatter = new simpleDateFormat('yyyy-MM-dd kk:mm');
				$("#side_project_setting  .date input").val("");
				$("#side_project_setting  .date input").css("display","none");    
				$("#side_project_setting  .date button").removeClass("insertDate");
				     
				if(project.startDate!=null){  
					$("#startDate input").val(mySimpleDateFormatter.format(new Date(project.startDate)));
					$("#startDate input").css("display","block");
					$("#startDate button").addClass("insertDate");
				}
				
				if(project.endDate!=null){ 
					$("#endDate input").val(mySimpleDateFormatter.format(new Date(project.endDate)));
					$("#endDate input").css("display","block");
					$("#endDate button").addClass("insertDate");
				} 
				      
				if(project.finishDate!=null){ 
					$("#finishDate input").val(mySimpleDateFormatter.format(new Date(project.finishDate)));
					$("#finishDate input").css("display","block");
					$("#finishDate button").addClass("insertDate");
				}  
 
				     
				if(project.visibility){
					$("#side_project_setting .setting_switch_btn .handler").css("float","right"); 
					$("#side_project_setting .on-label").css("display","block");
					$("#side_project_setting .off-label").css("display","none");
					$("#side_project_setting .setting_switch_btn").addClass("on_switch");
				}else{
					$("#side_project_setting .setting_switch_btn .handler").css("float","left"); 
					$("#side_project_setting .on-label").css("display","none");
					$("#side_project_setting .off-label").css("display","block");
					$("#side_project_setting .setting_switch_btn").removeClass("on_switch");     
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
				}else{
					$("#project_locker .setting_btn").attr("data-value","0");
					$("#project_locker .setting_btn").removeClass("active");
					$("#project_locker .setting_btn").html(locker_btn_text[0]); 
					$("#project_locker p").html(locker_text[0])  
				} 
			}        
		})     
	})
	
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
