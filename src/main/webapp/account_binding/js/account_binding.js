/**
 * Created by darlingtld on 2015/4/12.
 */
(function ($) {
    $.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]);
        return null;
    }
})(jQuery);

$(function () {

    var openId = $.getUrlParam('open_id');

    $('#submit').click(function () {
        var userInfo = {};
        userInfo.username = $('#username').val();
        userInfo.mobile = $('#mobile').val();
        userInfo.email = $('#email').val();
        userInfo.openId = openId;
        //userInfo.password=$('#password').val();

        $.ajax({
            type: "POST",
            url: "/user/account/bind",
            data: userInfo,
            success: function (data, status) {
                $('.container').html("<h3 class='text-center header_msg'>会员账号绑定成功</h3>");
            },
            error: function (data) {
                var errorMsg = data;
                $('.container').html("<h3 class='text-center header_msg'>" + errorMsg + "</h3>");
            }
        });
        $('#submit').val("绑定成功，若无跳转，请关闭此页面");
    });
})