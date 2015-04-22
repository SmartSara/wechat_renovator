/**
 * Created by zhus1 on 2015/4/22.
 */

$(function () {
    _initialNav();
    _initialPage();
});

function _initialNav() {
    $('#nav').load("nav.html", function () {
        $("#userNav").addClass("active");
    });
}

function _initialPage(){
    $("#category").select2();
}


function _submit(){
    alert("hi");
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
        url: "/product/" + "add/details",
        contentType: "application/json",
        data: JSON.stringify(productDetails),
        success: function (data) {
            location.reload();
        },
        error: function (data) {
            alert(data.status);
        }
    });
}