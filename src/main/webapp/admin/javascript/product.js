/**
 * Created by zhus1 on 2015/4/4.
 */
var _product;
var _saveOrUpdate;

$(function () {
    _initialNav();
    _initialPage();
});

function _initialNav() {
    $('#nav').load("nav.html", function () {
        $("#productNav").addClass("active");
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
        url: "/product/list",
        dataType: "json",
        success: function (data) {
            $("#productTemplate").tmpl(data).appendTo("#productList");
            //pageTable("#pagination", "#packageList tr",num_per_page);
            _product = data;
            $("#loading").addClass("hidden");
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log(textStatus);
            console.log(XMLHttpRequest.status);
            console.log(XMLHttpRequest.responseText);
        }
    });
}

$(document).on("click", ".update-product", function () {
    _saveOrUpdate = "update";
    var _thisProduct = _getProductByProductId($(this).data('id'));
    $(".modal-body #productId").val($(this).data('id'));
    $(".modal-body #productName").val(_thisProduct.name);
    $(".modal-body #productDescription").val(_thisProduct.description);
    $(".modal-body #productPrice").val(_thisProduct.price);
    $(".modal-body #productDiscount").val(_thisProduct.discount);
});


function _saveOrUpdateProduct() {
    var product = {};
    if (_saveOrUpdate == "update") {
        product.id = $("#productId").val();
    } else {
        _saveOrUpdate = "add";
    }
    product.name = $("#productName").val();
    product.description = $("#productDescription").val();
    product.price = $("#productPrice").val();
    product.discount = $("#productDiscount").val();
    product.ts = new Date();

    $.ajax({
        type: "post",
        url: "/product/" + _saveOrUpdate,
        contentType: "application/json",
        data: JSON.stringify(product),
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

function _searchProduct(){
    var ts = $("#searchTs").val().replace("年","-").replace("月","-").replace("日","");
    var params = "name="+ $("#searchName").val() + "&desc="+ $("#searchDescription").val() + "&price="+ $("#searchPrice").val()
        + "&ts=" + ts + "&discount=" + $("#searchDiscount").val();
    $.ajax({
        type: "get",
        url: "/product/search?" + params,
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

function _deleteProductById(id) {
    BootstrapDialog.show({
        title: '删除产品信息',
        message: '<div class="alert alert-danger" role="alert"><span class="label label-warning">Warning</span> 确认删除该产品？</div>',
        buttons: [{
            label: '删除',
            cssClass: 'btn-warning',
            action: function (dialogItself) {
                $("#loading").removeClass("hidden");
                $.ajax({
                    type: "post",
                    url: "/product/delete?id=" + id,
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

function _getProductByProductId(productId) {
    for (var i = 0; i < _product.length; i++) {
        if (_product[i].id == productId) {
            return _product[i];
        }
    }
}