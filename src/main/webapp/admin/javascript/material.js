/**
 * Created by zhus1 on 2015/4/4.
 */

var materialNo = 'blank';

$(function() {
	// _initialNav();
	_initialPage();
	_initEvent();
});

function _initialPage() {
	
	$.ajax("../../material/article/preview").done(function(result){
		$("#materials").append($("#singleArticleEditTemplate").render(result));
	});
	
	$("#editor").wysiwyg();
}

function _initEvent() {

	initAddEvent();

	initPreviewEvent();
	
	initSaveBtnEvent();
	
	initCancelBtnEvent();

	initCoverBtnEvent();

	initCoverEvent();
	
	initMaterialsEvent();
	
	initUpdateArticleBtnEvent();
	
}

function initAddEvent() {

	$("#addItem").mouseover(function() {
		$("#addIcon").hide();
		$("#singleAdd").show();
	}).mouseout(function() {
		$("#addIcon").show();
		$("#singleAdd").hide();
	});

	// event
	$("#singleAdd").click(function() {
		$("#addDialog").find("#updateBtn").hide();
		$("#addDialog").find("#addBtn").show();
		$("#addDialog").modal("show");
	});

}

function initPreviewEvent(){
	$("#materials").on('mouseover',".singleArticle",function(){
		$(this).find(".glyphicon").show();
	}).on("mouseout",".singleArticle",function(){
		$(this).find(".glyphicon").hide();
	});
}

function initSaveBtnEvent() {
	$("#saveBtn").click(function() {
	
		var formData = new FormData();
		var cover = $("#cover").get(0).files[0];
		var title = $("#title").val();
		var content = $("#editor").html();
		formData.append("title", title);
		formData.append("cover", cover);
		formData.append("content", content);
		$.ajax({
			type : "post",
			url : "../../material/article/add",
			data : formData,
			contentType : false,
			processData : false,
			success : function(data) {
				materialNo = data;
				var articleId = data.articleId;
				location.reload();
				$("#addDialog").modal("hide");
				doClearWork();
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
				.append(
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

function doClearWork() {
	$("#title").val("");
	var cover = $("#cover");
	var replacement;
	cover.replaceWith(replacement = cover.clone(true));
	$("#coverPreview").hide();
}

function initMaterialsEvent(){
	//Remove
		$("#materials").on("click",".del",function(){
			
			var articleId = $(this).parents(".singleArticle").data("id");
			var url =  "../../material/article/del/"+articleId;
			$.ajax(url).done(
					function(data){
						location.reload();
					});
		}
	)
		//Edit
	$("#materials").on("click",".edit",function(event){
		event.stopPropagation();
		var articleId = $(this).parents(".singleArticle").data("id");
		$.ajax("../../material/article/"+articleId).done(function(data){
			$("#addDialog").data("id",data.id);
			$("#addDialog").data("type","edit");
			$("#addDialog #title").val(data.title);
			$("#addDialog #coverPreview").attr("src","/pic/"+data.cover).show();
			$("#addDialog #editor").html(data.content);
			$("#addDialog").find("#saveBtn").hide();
			$("#addDialog").find("#updateBtn").show();
			$("#addDialog").modal("show");
		});
		
	}
)
}

function initUpdateArticleBtnEvent(){
	$("#updateBtn").click(function(){
		if($(this).parents("#addDialog").data("type")=="add"){
			return ;
		}
		var url; 
		var formData =  new FormData();
		var id = $("#addDialog").data("id");
		var title = $("#addDialog #title").val();
		var content = $("#addDialog #editor").html();
		
		formData.append("id",id);
		formData.append("title",title);
		formData.append("content",content);
		//no new cover image uploaded ,do nothing
		if($("#addDialog #cover").get(0).files.length!=0){
			formData.append("cover",$("#addDialog #cover").get(0).files[0]);
			url = "../../material/article/update";
		}else{
			url= "../../material/article/noCover/update";
		}
		//upload
		$.ajax({
			type: "post",
			url: url,
			contentType: false,
			data: formData,
			processData: false,
			success: function (data){
				location.reload();
			}
		})
		
	});
}
