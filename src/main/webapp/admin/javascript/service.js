/**
 * Created by zhus1 on 2015/4/4.
 */
var _order;

$(function () {
    _initialNav();
    _initialPage();
});

function _initialNav() {
    $('#nav').load("nav.html", function () {
        $("#serviceNav").addClass("active");
    });
}

function _initialPage() {
    $.ajax({
        type: "get",
        url: "/service/list",
        dataType: "json",
        success: function (data) {
            $("#orderTemplate").tmpl(data).appendTo("#orderList");
            _order = data;
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

$(document).on("click", ".update-order", function () {
    //_order = $(this).data('order');

    var _thisOrder = _getServiceBySerivceId($(this).data('id'));
    $(".modal-body #serviceId").val($(this).data('id'));
    $(".modal-body #orderId").val(_thisOrder.orderId);
    $(".modal-body #orderType").val(_thisOrder.type);
    $(".modal-body #orderPrice").val(_thisOrder.price);
    $(".modal-body #orderTs").val(formatDate(new Date(_thisOrder.ts), 'yyyy-MM-dd hh:mm:ss'));
});

function _updateOrder() {
    var service = _getServiceBySerivceId($("#serviceId").val());
    service.orderId = $("#orderId").val();
    service.type = $("#orderType").val();
    service.price = $("#orderPrice").val();
    service.ts = $("#orderTs").val();
    $.ajax({
        type: "post",
        url: "/service/update",
        contentType: "application/json",
        data: JSON.stringify(service),
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

function _deleteOrderById(id) {
    BootstrapDialog.show({
        title: '删除订单信息',
        message: '<div class="alert alert-danger" role="alert"><span class="label label-warning">Warning</span> 确认删除该订单？</div>',
        buttons: [{
            label: '删除',
            cssClass: 'btn-warning',
            action: function (dialogItself) {
                $("#loading").removeClass("hidden");
                $.ajax({
                    type: "post",
                    url: "/service/delete?id=" + id,
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

function _getServiceBySerivceId(serviceId) {
    for (var i = 0; i < _order.length; i++) {
        if (_order[i].id == serviceId) {
            return _order[i];
        }
    }
}