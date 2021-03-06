/**
 * Created by zhus1 on 2015/4/22.
 */

$(function () {
    _initialNav();
    _initialPage();
});

function _initialNav() {
    $('#nav').load(app+"/admin/html/nav.html", function () {
        $("#productEditNav").addClass("active");
    });
}

function _initialPage(){
    $("#category").select2();
}


function _submit(){
    //alert("hi");
    var productDetails = {};
    productDetails.html = UM.getEditor('editor').getContent();
    productDetails.name = $("#name").val();
    productDetails.description = $("#desc").val();
    productDetails.category = $("#category option:selected").text();
    productDetails.price = $("#price").val();
    productDetails.discount = $("#discount").val();
    productDetails.ts = new Date();

    $.ajax({
        type: "post",
        url: app+"/product/" + "add/details",
        contentType: "application/json",
        data: JSON.stringify(productDetails),
        success: function (data) {
            //location.reload();
            $('body').html('新添商品成功！');
        },
        error: function (data) {
            alert('新添商品成功！');
        }
    });
}