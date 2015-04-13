/**
 * Created by darlingtld on 2015/4/12.
 */
$(function(){
    (function ($) {
        $.getUrlParam = function (name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) return unescape(r[2]); return null;
        }
    })(jQuery);

    var openId = $.getUrlParam('open_id');

    $('#submit').click(function(){
        var userInfo={};
        userInfo.username=$('#username').val();
        userInfo.mobile=$('#mobile').val();
        userInfo.email=$('#email').val();
        userInfo.openId=openId;
        //userInfo.password=$('#password').val();

        $.ajax({
            type: "POST",
            url: "/user/account/bind",
            data: userInfo,
            success: function () {
                window.location.href="bind_success.html"
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log(textStatus);
                console.log(XMLHttpRequest.status);
                console.log(XMLHttpRequest.responseText);
            }
        });
    });
})