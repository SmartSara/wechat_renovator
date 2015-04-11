var articles = [];

var pushMessageType =  "MULTI_ARTICLE";

$(function() {
	// _initialNav();
	_initialPage();
	_initEvent();
});

function _initialPage() {
	
	
	initPushMessageContainer();
	
	$("#editor").wysiwyg();
}

function initPushMessageContainer(){
	$.ajax("../../pushMessage/MULTI_ARTICLE/preview").done(function(result){
		$("#pushMessageContainer").append($("#pushMessageTemplate").render(result));
});
}

function _initEvent() {

	initAddEvent();

	initSaveBtnEvent();
	
	initCancelBtnEvent();
	
	initSingleMaterialsEvnent();

	initCoverBtnEvent();

	initCoverEvent();

}

function initAddEvent() {

	$("#addItem").mouseover(function() {
		$("#addIcon").hide();
		$("#multiAdd").show();
	}).mouseout(function() {
		$("#addIcon").show();
		$("#multiAdd").hide();
	});

	// event

	$("#multiAdd").click(function() {
		getArticleList();
		$("#addDialog").modal("show");
	});

}

function getArticleList(){
	
	$.ajax({
		type : "post",
		url : "../../material/article/list",
		success : function(data) {
			$("#singleMaterials").html($("#singleArticleTemplate").render(data));
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			console.log(textStatus);
			console.log(XMLHttpRequest.status);
			console.log(XMLHttpRequest.responseText);
		}
	});	
}

function initSaveBtnEvent() {
	$("#saveBtn").click(function() {
		
		var notifaction =  {};
		notifaction.type = "MULTI_ARTICLE";
		notifaction.msg = articles.join(";");
		notifaction.scheduledTime = new Date();
		$.ajax({
			type : "post",
			url : "../../pushMessage/add",
			data : JSON.stringify(notifaction),
			contentType : "application/json",
			success : function(data) {
				location.reload();
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
				console.log(XMLHttpRequest.status);
				console.log(XMLHttpRequest.responseText);
			}
		});
	})
}

function initCancelBtnEvent(){
	$("#cancleBtn").click(function(){
		doClearWork();
	});
}

function initSingleMaterialsEvnent(){
	$("#singleMaterials").on("click",".singleArticle",function(){
		if($(this).hasClass("checked")){
			$(this).removeClass("checked");
			removeItem(articles,$(this).data("id"));
		}else{
			articles.push($(this).data("id"));
			$(this).addClass("checked");
		}
	})
}

function removeItem(articles,item){
	var index =  articles.indexOf(item);
	articles.splice(index,1);
}

function initCoverEvent() {
	$("#cover").change(function() {
		readURL(this);
	});
}

function initCoverBtnEvent() {
	$("#coverBtn").click(function() {
		$("#cover").trigger('click');
	});
}

function readURL(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();

		reader.onload = function(e) {
			$('#coverPreview').show().attr('src', e.target.result);
		}

		reader.readAsDataURL(input.files[0]);
	}
}

function initMaterialsContain(cover, title, articleId) {

		$('#materials')
				.prepend(
						$("<div class='col-md-2'/>").data("articleId",
								articleId).append($("<div/>").text(title))
								.append(
										$('<a class="thumbnail"/>').append(
												$("<img/>").attr('src',
														"/pic/"+cover))));
}

function pushInMaterialsContain(cover, title, articleId) {

	var reader = new FileReader();
	reader.onload = function(e) {
		$('#materials')
				.prepend(
						$("<div class='col-md-2'/>").data("articleId",
								articleId).append($("<div/>").text(title))
								.append(
										$('<a class="thumbnail"/>').append(
												$("<img/>").attr('src',
														e.target.result))));
	};
	reader.readAsDataURL(cover);
}

function doClearSelectedWork() {
	$("#singleMaterials").find(".checked").removeClass("checked");
}

function doClearWork() {
	$("#title").val("");
	var cover = $("#cover");
	var replacement;
	cover.replaceWith(replacement = cover.clone(true));
	$("#coverPreview").hide();
}