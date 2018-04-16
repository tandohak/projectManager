$(function(){
	/* 프로젝트 설정 버튼 클릭 : 설정 토글 */    
	$("#project_setting_btn").click(function(e){
		e.preventDefault();   
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
})