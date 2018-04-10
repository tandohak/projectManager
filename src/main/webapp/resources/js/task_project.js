$(function() {
	var imgPath = [ "preview-blank-f9e6c665.png",
			"preview-weekly-ko-f26d30dd.png", "preview-people-ko-60882c1d.png",
			"preview-department-ko-8f933e90.png",
			"preview-todo-ko-d07476ee.png" ];

	$(".mem_add_btn a").click(function(e) {
		e.preventDefault();

		$.ajax({
			url : "/projectManager/member/memList?wcode=" + wcode,
			type : "get",
			dataType : "json",
			success : function(res) {
				var source = $("#template").html();
				var t_fn = Handlebars.compile(source);
				$("#memList").html(t_fn(res));
			}
		})
		$(".dropdown_menu_setting input").val("");
		$(".dropdown_menu_setting").toggle();
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
						var source = $("#template").html();
						var t_fn = Handlebars.compile(source);
						$("#memList").html(t_fn(res));
					}
				})
			})

	$("#memList").on("click", ".mem_li", function(e) {
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
			$(".addMemberBox").append(t_fn(mem));
		} else {
			var delmno = $(this).attr("data-mno");
			$(this).find("span").css("display", "none");

			$(".addMemberBox .addMem_item").each(function(i, obj) {
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
				$(".modal_inner_box input").val("");
				$(".modal_inner_box input[name='visibility']").removeAttr(
						"checked");
				$(".modal_inner_box input[name='visibility']").eq(0).attr(
						"checked", "checked");

				var source = $("#addMemTemplate").html();
				$(".dropdown_menu_setting").css("display", "none");
				var t_fn = Handlebars.compile(source);
				$(".addMemberBox").html(t_fn(loginMem));

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

		$("#memList li").each(function(i, obj) {
			var mno = $(this).find(".mem_li").attr("data-mno");
			if (mno == delmno) {
				$(this).find("span").css("display", "none");
			}
		});

		$(this).parent(".addMem_item").remove();
	})

	$("#addPjNextBtn").click(function() {
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
})

Handlebars.registerHelper('checkPhotoPath', function(options) {
	if (options == "" || options == null) {
		return "resources/img/user_icon_b.png";
	} else {
		return "displayFile?filename=" + options;
	}
});

Handlebars.registerHelper('checkSelectMember', function(value) {
	var res = "";
	var size = $(".addMem_item").length;
	for (var i = 0; i < size; i++) {
		var mno = $(".addMem_item").eq(i).attr("data-mno");
		if (mno == value) {
			res = 'display:inline-block;';
			break;
		}
	}
	return res;
});