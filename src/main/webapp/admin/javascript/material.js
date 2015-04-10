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
		result.forEach(function(entry){
			initMaterialsContain(entry.cover,entry.title,entry.id);
		})
	});
	
	$("#editor").wysiwyg();
}

function _initEvent() {

	initAddEvent();

	initSaveBtnEvent();
	
	initCancelBtnEvent();

	initCoverBtnEvent();

	initCoverEvent();

}

function initAddEvent() {

	$("#addItem").mouseover(function() {
		$("#addIcon").hide();
		$("#optionsIcon").show();
	}).mouseout(function() {
		$("#addIcon").show();
		$("#optionsIcon").hide();
	});

	// event

	$("#singleAdd").click(function() {
		$("#addDialog").modal("show");
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
			url : "../../material/" + materialNo + "/article/add",
			contentType : false,
			data : formData,
			contentType : false,
			processData : false,
			success : function(data) {
				materialNo = data;
				var articleId = data.articleId;
				pushInMaterialsContain(cover, title, articleId);
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
				.prepend(
						$("<div class='col-md-2'/>").data("articleId",
								articleId).append($("<div/>").text(title))
								.append(
										$('<a class="thumbnail"/>').append(
												$("<img/>").attr('src',
														"data:image/png;base64,"+cover))));
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