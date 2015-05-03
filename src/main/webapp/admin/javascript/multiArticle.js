var articles = [];

var pushMessageType =  "MULTI_ARTICLE";

var expandNo = -1;

var addArticleInPushMessages=false;

var warningDialogEle='<div class="modal fade delDialog" tabindex="-1" role="dialog" aria-labelledby="title" aria-hidden="false"> <div class="modal-dialog"> <div class="modal-content"> <div class="modal-header"> <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> <h4 class="modal-title" id="title">提醒</h4> </div> <div class="modal-body text-center">####</div> <div class="modal-footer"> <button type="button" class="btn btn-default" data-dismiss="modal">OK</button> </div> </div> </div>';

var _product;

var isUpdateArticle = false ;

$(function() {
	_initialPage();
	_initEvent();
	_initialNav();
});

function _initialPage() {
	
	
	initPushMessageContainer();
	
	initDateTimePicker();
	
	$("#editor").wysiwyg();
}


function _initialNav() {
    $('#nav').load("nav.html", function () {
        $("#multiArticleNav").addClass("active");
    });
}

function initPushMessageContainer(){
//	$.ajax("../../pushMessage/MULTI_ARTICLE/preview").done(function(result){
//		$("#pushMessageContainer").append($("#pushMessageTemplate").render(result));
//	
		$.ajax("../../pushMessage/MULTI_ARTICLE/preview").done(function(result){
			 $("#pushMessageTaskTemplate").tmpl(result).appendTo("#pushMessageTaskContainer tbody");
			 _product = result;
			 _pageTable();
		
});
}

function initDateTimePicker(){
	$('#scheduledTimeDialog .datetimepicker').datetimepicker({"minDate":new Date()}).next().on("click",
			function() {
				if ($._data(this, "events").click.length > 1 || $("#sendRightNowCheck").prop("checked"))
					return
				else
					$(this).prev().focus();
			});
	$('#modifyScheduledTimeDialog .datetimepicker').datetimepicker({"minDate":new Date()}).next().on("click",
			function() {
		if ($._data(this, "events").click.length > 1)
			return
			else
				$(this).prev().focus();
	});
}



function _initEvent() {

	initAddEvent();

	initSaveBtnEvent();
	
	initUpdateArticleBtnBtnEvent();
	
	initCancelBtnEvent();
	
	initSingleMaterialsEvnent();
	
	initPushMessageTaskContainerEvent();

	initCoverBtnEvent();

	initCoverEvent();
	
	initPreviewEvent();
	
//	initExpandPreviewEvent();
	
	initScheduleTimeEvent();
	
	initModifyScheduledTimeDialogEvent();

}

function initAddEvent() {

//	$("#addItem").mouseover(function() {
//		$("#addIcon").hide();
//		$("#multiAdd").show();
//	}).mouseout(function() {
//		$("#addIcon").show();
//		$("#multiAdd").hide();
//	});

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
			$("#alreadySingleMaterials").empty();
			$("#singleMaterials").html($("#singleArticleAddTemplate").render(data));
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			console.log(textStatus);
			console.log(XMLHttpRequest.status);
			console.log(XMLHttpRequest.responseText);
		}
	});	
}


function initSaveBtnEvent() {
	$(" #saveBtn").click(
			function() {

				if (isUpdateArticle == false) {

					if (addArticleInPushMessages == false) {

						$("#addDialog").modal("hide");
						$("#scheduledTimeDialog").modal("show");
					} else {
						// //增加某一个task的article

						var pushMessageTaskId = $("#addDialog").data(
								"pushMessageTaskId");
						var msgs = $("#alreadySingleMaterials").find(
								".singleArticle").map(function() {
							return $(this).data("id");
						}).get().join(",");
						var data = {
							pushMessageTaskId : pushMessageTaskId,
							msgs : msgs
						};
						$.ajax({
							url : "../../pushMessage/update/msgs/",
							data : data,
							success : function() {
								location.reload();
							}
						})

					}
				}else{
					$("#addDialog").modal("hide");
				}

			})
}

function addPushMessageTask(){

	var pushMessageTask =  {};
	pushMessageTask.type = "MULTI_ARTICLE";
	pushMessageTask.msg = articles.join(",");
	if($("#sendRightNowCheck").is(":checked")){
		pushMessageTask.scheduledTime = moment().add('minutes',2)._d;
	}else{
		pushMessageTask.scheduledTime=$('#scheduledTimeDialog .datetimepicker').data("DateTimePicker").getDate()._d;
	}
//	pushMessageTask.scheduledTime = new Date();

	console.log(pushMessageTask.scheduledTime);
	$.ajax({
		type : "post",
		url : "../../pushMessage/add",
		data : JSON.stringify(pushMessageTask),
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
}

function initUpdateArticleBtnBtnEvent(){
	$("#updateArticleBtn").click(function(){
		
		var url; 
		var formData =  new FormData();
		var id = $("#editDailog").data("id");
		var title = $("#editDailog #title").val();
		var content = $("#editDailog #editor").html();
		
		
		var checkResult = checkUpdateInputs();
		if(checkResult){
			var dialog=  warningDialogEle.replace("####",checkResult);
			$(dialog).modal("show");
			return ;
		}
		
		formData.append("id",id);
		formData.append("title",title);
		formData.append("content",content);
		//no new cover image uploaded ,do nothing
		if($("#editDailog #cover").get(0).files.length!=0){
			formData.append("cover",$("#editDailog #cover").get(0).files[0]);
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
				var url = "../../pushMessage/MULTI_ARTICLE/preview/"+$("#addDialog").data("pushMessageTaskId");
				$.ajax(url).done(function(data){
					$("#singleMaterials").html($("#singleArticleEditTemplate").render(data));
//					$("#addDialog").modal("show").data("pushMessageTaskId",id);
					$("#editDailog").modal("hide");
					doClearWork();
				})
			}
		})
		
	});
}

function initCancelBtnEvent(){
	$("#cancleBtn").click(function(){
		doClearWork();
	});
}

function initSingleMaterialsEvnent(){
	//Add
	$("#singleMaterials").on("click",".add.singleArticle",function(){
		
		var selectedArticle =  $(this);
		if(selectedArticle.hasClass("checked")){
			if(addArticleInPushMessages == true){
				$("#alreadySingleMaterials").find(".selected").each(function(index,ele){
					if(selectedArticle.data("id") ==  $(ele).data("id")){
						ele.remove();
					}
				})
			}
			selectedArticle.removeClass("checked");
			removeItem(articles,$(this).data("id"));
			
		}else{
			if(addArticleInPushMessages == true){
				$("#alreadySingleMaterials").append($(this).clone().addClass("selected"));
			}
			articles.push($(this).data("id"));
			$(this).addClass("checked");
		}
	})
	
	
	//Remove
		$("#singleMaterials").on("click",".del",function(){
			
			var pushMessageTaskId = $("#addDialog").data("pushMessageTaskId");
			var singleArticle = $(this).parents(".singleArticle");
			var articleId = singleArticle.data("id");
			var url =  "../../pushMessage/del/"+pushMessageTaskId+"/"+articleId;
			
			var delDialogEle='<div class="modal fade delDialog" tabindex="-1" role="dialog" aria-labelledby="title" aria-hidden="false"> <div class="modal-dialog"> <div class="modal-content"> <div class="modal-header"> <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> <h4 class="modal-title" id="title">提醒</h4> </div> <div class="modal-body text-center">您确定要删除？</div> <div class="modal-footer"> <button type="button" class="no btn btn-default" data-dismiss="modal">取消</button><button type="button" class="ok btn btn-default" data-dismiss="modal">确定</button> </div> </div> </div>';
			$(delDialogEle).modal("show").on("click",".ok",function(){
				$.ajax(url).done(
						function(data){
							if(data=="del"){
								location.reload();
							}
							singleArticle.remove();
						});
			});
			
		}
	)
		//Edit
	$("#singleMaterials").on("click",".edit",function(event){
		event.stopPropagation();
//		var pushMessageTaskId = $("#addDialog").data("pushMessageTaskId");
		var singleArticle = $(this).parents(".singleArticle");
		var articleId = singleArticle.data("id");
//		var url =  "../../pushMessage/del/"+pushMessageTaskId+"/"+articleId;
		$.ajax("../../material/article/"+articleId).done(function(data){
			$("#editDailog").data("id",data.id);
			$("#editDailog #title").val(data.title);
			$("#editDailog #coverPreview").attr("src","/pic/"+data.cover).show();
			$("#editDailog #editor").html(data.content);
			$("#editDailog").modal("show");
		});
		
	}
)

	
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

function initPreviewEvent(){
	$("#pushMessageContainer").on('mouseover',".preview",function(){
		$(this).find(".glyphicon").show();
	}).on("mouseout",".preview",function(){
		$(this).find(".glyphicon").hide();
	}).on("click",".glyphicon",function(){
		var i =  $(this);
		var preview =$(this).parents(".preview");
		var pushMessageTaskId= preview.attr("id");
		var url  ;
		if(i.hasClass("edit")){
			url = "../../pushMessage/MULTI_ARTICLE/preview/"+pushMessageTaskId;
			$.ajax(url).done(function(data){
				$("#singleMaterials").empty().html($("#singleArticleEditTemplate").render(data));
				$("#alreadySingleMaterials").empty();
				$("#addDialog").modal("show").data("pushMessageTaskId",pushMessageTaskId);
			})
		}else if(i.hasClass("add")){
			url = "../../pushMessage/MULTI_ARTICLE/preview/"+pushMessageTaskId;
			$.ajax(url).done(function(data){
				$("#alreadySingleMaterials").html($("#singleArticleAddTemplate").render(data));
				getArticleMaterials(pushMessageTaskId);
			})
			
			addArticleInPushMessages = true;
			
		}else if (i.hasClass("del")){
			
			var delDialogEle='<div class="modal fade delDialog" tabindex="-1" role="dialog" aria-labelledby="title" aria-hidden="false"> <div class="modal-dialog"> <div class="modal-content"> <div class="modal-header"> <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> <h4 class="modal-title" id="title">提醒</h4> </div> <div class="modal-body text-center">您确定要删除？</div> <div class="modal-footer"> <button type="button" class="no btn btn-default" data-dismiss="modal">取消</button><button type="button" class="ok btn btn-default" data-dismiss="modal">确定</button> </div> </div> </div>';
			$(delDialogEle).modal("show").on("click",".ok",function(){
				$.ajax(url).done(
						function(data){			
						url = "../../pushMessage/delete/MULTI_ARTICLE/"+pushMessageTaskId;
						$.ajax(url).done(function(data){
							//Dialog
							$.ajax(url).done(function(data){
								preview.remove();
							});
						})});
			});
			
		}
	})
	
}


function initPushMessageTaskContainerEvent(){
	$("#pushMessageTaskContainer").on("click",".add",function(){

		isUpdateArticle = false;
		var pushMessageTaskId = $(this).data("id");
		var url = "../../pushMessage/MULTI_ARTICLE/preview/"+pushMessageTaskId;
		$.ajax(url).done(function(data){
			$("#alreadySingleMaterials").html($("#singleArticleAddTemplate").render(data));
			getArticleMaterials(pushMessageTaskId);
			$("#singleMaterials").addClass("alreadySelectedMaterials");
		})
		
		addArticleInPushMessages = true;
	})
	
	$("#pushMessageTaskContainer").on("click",".del",function(){
		url = "../../pushMessage/delete/MULTI_ARTICLE/"+$(this).data("id");
		var trEle = $(this).parents("tr");
		//Dialog
		isUpdateArticle = false;
		var delDialogEle='<div class="modal fade delDialog" tabindex="-1" role="dialog" aria-labelledby="title" aria-hidden="false"> <div class="modal-dialog"> <div class="modal-content"> <div class="modal-header"> <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> <h4 class="modal-title" id="title">提醒</h4> </div> <div class="modal-body text-center">您确定要删除？</div> <div class="modal-footer"> <button type="button" class="no btn btn-default" data-dismiss="modal">取消</button><button type="button" class="ok btn btn-default" data-dismiss="modal">确定</button> </div> </div> </div>';
		$(delDialogEle).modal("show").on("click",".ok",function(){
			$.ajax(url).done(function(data){
				trEle.remove();
			})
		});
		
	})
	$("#pushMessageTaskContainer").on("click",".edit",function(){
		isUpdateArticle = true;
		var pushMessageTaskId = $(this).data("id");
		var url = "../../pushMessage/MULTI_ARTICLE/preview/"+pushMessageTaskId;
		$.ajax(url).done(function(data){
			$("#singleMaterials").empty().html($("#singleArticleEditTemplate").render(data));
			$("#alreadySingleMaterials").empty();
			$("#addDialog").modal("show").data("pushMessageTaskId",pushMessageTaskId);
		})
	})
	
		$("#pushMessageTaskContainer").on("click",".modifyTime",function(){
			var pushMessageTaskId = $(this).data("id");
			$("#modifyScheduledTimeDialog").modal("show").data("pushMessageTaskId",pushMessageTaskId);
		})
	
}


function getArticleMaterials(pushMessageTaskId){
	
	$.ajax({
		type : "post",
		url : "../../material/article/list",
		success : function(data) {
			$("#singleMaterials").empty().append($("#singleArticleAddTemplate").render(data));
			$("#addDialog").modal("show").data("pushMessageTaskId",pushMessageTaskId);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
		}
	});	
}



function initExpandPreviewEvent(){
	$("#expandPushMessage").on("click",".preview",function(){
		
		var articleId = $(this).data("id");
		
		$.ajax("../../material/article/"+articleId).done(function(data){
			console.log(data);
			$("#editDailog").data("id",data.id);
			$("#editDailog #title").val(data.title);
			$("#editDailog #coverPreview").attr("src","/pic/"+data.cover).show();
			$("#editDailog #editor").html(data.content);
			$("#editDailog").modal("show");
		});
		
		
	});
	
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

function initScheduleTimeEvent(){
	
	$("#sendRightNowCheck").change(function(){
		if($(this).is(":checked")){
			$("#scheduledTime").val("").prop("readonly",true);
			$('.datetimepicker').data("DateTimePicker").disable();
		}else{
			$("#scheduledTime").prop("readonly",false);
			$('.datetimepicker').data("DateTimePicker").enable();
		}
	}).click();
	
	$("#saveScheduledTimeBtn").click(function(){
		addPushMessageTask()
	})
	
	$("#cancelScheduledTimeBtn").click(function(){
		$("#addDialog").modal("show");
	})
		
	
}

function initModifyScheduledTimeDialogEvent(){
	
	$("#modifyScheduledTimeBtn").click(function(){
		var  scheduledTime=$('#modifyScheduledTimeDialog .datetimepicker').data("DateTimePicker").getDate()._d;

		if(scheduledTime == null){
			alert("请选择时间");
			return ;
		}
		
		var pushMessageTaskId = $("#modifyScheduledTimeDialog").data("pushMessageTaskId");
		
		var data =  {pushMessageTaskId : pushMessageTaskId,modifyScheduledTime : scheduledTime};
		$.ajax({
		type : "post",
		url : "../../pushMessage/update/scheduledTime",
		data: data,
		success : function(result) {
			location.reload();
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			console.log(textStatus);
			console.log(XMLHttpRequest.status);
			console.log(XMLHttpRequest.responseText);
		}
	});
	});	
}

function _pageTable(numPerPage) {
    $("#pagination").pagination(_product.length, {
        callback: pageSelectCallback,
        items_per_page: numPerPage,
        prev_text: "上一页",
        next_text: "下一页"
    });
}
function pageSelectCallback(page_index) {
    var numPerPage = $("#numPerPage").val();
    $("#productList tr").hide();
    if (page_index == 0) {
        $("#productList tr:lt(" + numPerPage + ")").show();
    } else {
        $("#productList tr:gt(" + (page_index * numPerPage - 1) + ")").show();
        $("#productList tr:gt(" + ((page_index + 1) * numPerPage - 1) + ")").hide();
    }
}

function checkUpdateInputs(){
	
	if($("#title").val() == ''){
		return "标题不为空";
	}

	var inputFiles =  $("#editDailog #cover").get(0).files;
	if(inputFiles.length > 0){
		if(inputFiles[0].name.match(".gif")){
			return "输入图片格式不允许";
		}
	}
	
	if($("#editor").html() == ''){
		return "消息内容不为空";
	}
}