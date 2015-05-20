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
    //init datepicker
    $("#searchTs").datepicker({
        language: "zh-CN",
        autoclose: true
    });

    //init table
    $.ajax({
        type: "get",
        url: app + "/service/list",
        dataType: "json",
        success: function (data) {
            $("#orderTemplate").tmpl(data).appendTo("#orderList");
            _order = data;
            _pageTable(10);
            $("#loading").addClass("hidden");
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log(textStatus);
            console.log(XMLHttpRequest.status);
            console.log(XMLHttpRequest.responseText);
        }
    });

    $("#pagination").pagination(15, {
        items_per_page: 10
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
        url: app + "/service/update",
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

function _searchService() {
    var ts = $("#searchTs").val().replace("年", "-").replace("月", "-").replace("日", "");
    var params = "order_id=" + $("#searchOrderId").val() + "&type=" + $("#searchType").val() + "&price=" + $("#searchPrice").val()
        + "&ts=" + ts + "&username=" + $("#searchUsername").val() + "&mobile=" + $("#searchMobile").val() + "&product_name=" + $("#searchProduct").val();
    $.ajax({
        type: "get",
        url: app + "/service/search?" + params,
        contentType: "application/json",
        success: function (data) {
            $("#orderList tr").empty();
            $("#orderTemplate").tmpl(data).appendTo("#orderList");
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
                    url: app + "/service/delete?id=" + id,
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

$(document).on("click", ".sort", function () {
    if ($(this).hasClass("price")) {
        if ($(this).hasClass("desc")) {
            $(this).removeClass("desc");
            _order.sort(function (a, b) {
                return a.price - b.price;
            });
        } else {
            $(this).addClass("desc");
            _order.sort(function (a, b) {
                return b.price - a.price;
            });
        }
    } else if ($(this).hasClass("orderId")) {
        if ($(this).hasClass("desc")) {
            $(this).removeClass("desc");
            _order.sort(function (a, b) {
                return a.orderId - b.orderId;
            });
        } else {
            $(this).addClass("desc");
            _order.sort(function (a, b) {
                return b.orderId - a.orderId;
            });
        }
    } else if ($(this).hasClass("ts")) {
        if ($(this).hasClass("desc")) {
            $(this).removeClass("desc");
            _order.sort(function (a, b) {
                return a.ts - b.ts;
            });
        } else {
            $(this).addClass("desc");
            _order.sort(function (a, b) {
                return b.ts - a.ts;
            });
        }
    }
    $("#orderList tr").empty();
    $("#orderTemplate").tmpl(_order).appendTo("#orderList");
});

function _pageTable(numPerPage) {
    $("#pagination").pagination(_order.length, {
        callback: pageSelectCallback,
        items_per_page: numPerPage,
        prev_text: "上一页",
        next_text: "下一页"
    });
}
function pageSelectCallback(page_index) {
    var numPerPage = $("#numPerPage").val();
    $("#orderList tr").hide();
    if (page_index == 0) {
        $("#orderList tr:lt(" + numPerPage + ")").show();
    } else {
        $("#orderList tr:gt(" + (page_index * numPerPage - 1) + ")").show();
        $("#orderList tr:gt(" + ((page_index + 1) * numPerPage - 1) + ")").hide();
    }
}