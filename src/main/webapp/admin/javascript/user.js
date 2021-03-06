/**
 * Created by zhus1 on 2015/4/4.
 */

var _user;

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
        url: app + "/user/list",
        dataType: "json",
        success: function (data) {
            $("#userTemplate").tmpl(data).appendTo("#userList");
            _user = data;
            _pageTable(10);
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

function _searchUser() {
    var birth = $("#searchBirthday").val().replace("年", "-").replace("月", "-").replace("日", "");
    var params = "name=" + $("#searchUsername").val() + "&mobile=" + $("#searchMobile").val() + "&address=" + $("#searchAddress").val()
        + "&birthday=" + birth + "&balance=" + $("#searchBalance").val();
    $.ajax({
        type: "get",
        url: app + '/user/search?' + params,
        contentType: "application/json",
        success: function (data) {
            $("#userList tr").empty();
            $("#userTemplate").tmpl(data).appendTo("#userList");
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
        url: app + "/user/update",
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
                    url: app + "/user/delete?id=" + id,
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

$(document).on("click", ".sort", function () {
    if ($(this).hasClass("birthday")) {
        if ($(this).hasClass("desc")) {
            $(this).removeClass("desc");
            _user.sort(function (a, b) {
                return a.birthday - b.birthday;
            });
        } else {
            $(this).addClass("desc");
            _user.sort(function (a, b) {
                return b.birthday - a.birthday;
            });
        }
    } else if ($(this).hasClass("balance")) {
        if ($(this).hasClass("desc")) {
            $(this).removeClass("desc");
            _user.sort(function (a, b) {
                return a.balance - b.balance;
            });
        } else {
            $(this).addClass("desc");
            _user.sort(function (a, b) {
                return b.balance - a.balance;
            });
        }
    }
    $("#userList tr").empty();
    $("#userTemplate").tmpl(_user).appendTo("#userList");
});

function _pageTable(numPerPage) {
    $("#pagination").pagination(_user.length, {
        callback: pageSelectCallback,
        items_per_page: numPerPage,
        prev_text: "上一页",
        next_text: "下一页"
    });
}
function pageSelectCallback(page_index) {
    var numPerPage = $("#numPerPage").val();
    $("#userList tr").hide();
    if (page_index == 0) {
        $("#userList tr:lt(" + numPerPage + ")").show();
    } else {
        $("#userList tr:gt(" + (page_index * numPerPage - 1) + ")").show();
        $("#userList tr:gt(" + ((page_index + 1) * numPerPage - 1) + ")").hide();
    }
}