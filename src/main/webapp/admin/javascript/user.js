/**
 * Created by zhus1 on 2015/4/4.
 */

$(function () {
    _initialNav();
    _initialPage();
});

function _initialPage() {
    //init date-picker
    $("#searchBirthday").datepicker({
        language: "zh-CN",
        autoclose: true
    });

    //init table
    $.ajax({
        type: "get",
        url: "/user/list",
        dataType: "json",
        success: function (data) {
            $("#userTemplate").tmpl(data).appendTo("#userList");
            //pageTable("#pagination", "#packageList tr",num_per_page);
            $("#loading").addClass("hidden");
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log(textStatus);
            console.log(XMLHttpRequest.status);
            console.log(XMLHttpRequest.responseText);
        }
    });
}


function _initialNav() {
    $('#nav').load("nav.html", function () {
        $("#userNav").addClass("active");
    });
}

$(document).on("click", ".update-user", function () {
    var username = $(this).data('username');
    $(".modal-body #username").val(username);
    var mobile = $(this).data('mobile');
    $(".modal-body #mobile").val(mobile);
    var address = $(this).data('address');
    $(".modal-body #address").val(address);
    var birthday = formatDate(new Date($(this).data('birthday')), 'yyyy-MM-dd');
    $(".modal-body #birthday").val(birthday);
    var money = $(this).data('money');
    $(".modal-body #money").val(money);
    var userId = $(this).data('id');
    $(".modal-body #userId").val(userId);
    var openId = $(this).data('openid');
    $(".modal-body #openId").val(openId);
});

function _searchUser(){
    var birth = $("#searchBirthday").val().replace("年","-").replace("月","-").replace("日","");
    var params = "name="+ $("#searchUsername").val() + "&mobile="+ $("#searchMobile").val() + "&address="+ $("#searchAddress").val()
        + "&birthday=" + birth + "&balance=" + $("#searchBalance").val();
    $.ajax({
        type: "get",
        url: "/user/search?" + params,
        contentType: "application/json",
        success: function (data) {
            $("#userTemplate").tmpl(data).appendTo("#userList");
            //pageTable("#pagination", "#packageList tr",num_per_page);
            $("#loading").addClass("hidden");
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log(textStatus);
            console.log(XMLHttpRequest.status);
            console.log(XMLHttpRequest.responseText);
        }
    });
}

function updateUser() {
    var user = {};
    user.id = $("#userId").val();
    user.name = $("#username").val();
    user.contact = $("#mobile").val();
    user.address = $("#address").val();
    user.birthday = $("#birthday").val();
    user.balance = $("#money").val();
    user.openId = $("#openId").val();
    $.ajax({
        type: "post",
        url: "/user/update",
        contentType: "application/json",
        data: JSON.stringify(user),
        success: function (data) {
            $("#loading").addClass("hidden");
            location.reload();
        },
        error: function (data) {
            $("#loading").addClass("hidden");
            alert(data.status);
        }
    });
}
function _deleteUserById(id) {
    BootstrapDialog.show({
        title: '删除用户信息',
        message: '<div class="alert alert-danger" role="alert"><span class="label label-warning">Warning</span> 确认删除该用户？</div>',
        buttons: [{
            label: '删除',
            cssClass: 'btn-warning',
            action: function (dialogItself) {
                $("#loading").removeClass("hidden");
                $.ajax({
                    type: "post",
                    url: "/user/delete?id=" + id,
                    success: function (data) {
                        $("#loading").addClass("hidden");
                        dialogItself.close();
                        location.reload();
                    },
                    error: function (data) {
                        $("#loading").addClass("hidden");
                        alert(data.status);
                    }
                });
            }
        }, {
            label: '取消',
            cssClass: 'btn-primary',
            action: function (dialogItself) {
                dialogItself.close();
            }
        }]
    });
}