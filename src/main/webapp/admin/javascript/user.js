/**
 * Created by zhus1 on 2015/4/4.
 */

$(function () {
    _initialNav();
    _initialPage();
});

function _initialPage() {
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
    var money = $(this).data('money');
    $(".modal-body #money").val(money);
});


function updateUserById(mobile) {
    $.ajax({
        type: "post",
        url: "/hermes-agent/job/cancel/" + mobile,
        success: function (data) {
            $("#loading").addClass("hidden");
            dialogItself.close();
            _refreshList(true);
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
                    url: "/hermes-agent/job/cancel/" + id,
                    success: function (data) {
                        $("#loading").addClass("hidden");
                        dialogItself.close();
                        _refreshList(true);
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