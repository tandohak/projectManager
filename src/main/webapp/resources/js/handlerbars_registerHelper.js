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

Handlebars.registerHelper('checkMemAssGrade', function(options) {
	console.log(options); 
	if (options == 99) {
		return "display:none;";
	} else {
		return"display:inline-block;";
	}
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
  
Handlebars.registerHelper('check_task_member', function(value) {
	var res = "";    
	var size = jobAssList.length;
	for (var i = 0; i < size; i++) {
		var massno = jobAssList[i];  
		  
		if (massno == value) { 
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