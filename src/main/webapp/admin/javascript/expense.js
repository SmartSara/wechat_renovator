/**
 * Created by zhus1 on 2015/4/4.
 */
$(function () {
    var id = getURLParameter("id");
    _initialNav();
    _initialPage();
});

function _initialPage(id) {
    $.ajax({
        type: "get",
        url: "/service/" + id,
        dataType: "json",
        success: function (data) {
            $("#expenseTemplate").tmpl(data).appendTo("#expenseList");
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

function getURLParameter(key) {
    var reg = new RegExp("(^|&)" + key + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}