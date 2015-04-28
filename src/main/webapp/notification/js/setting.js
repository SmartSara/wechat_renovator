/**
 * Created by tangld on 2015/4/28.
 */
$(function () {

    $('#dtbox').DateTimePicker({
        dateFormat: 'yyyy-MM-dd',
        shortDayNames: ["日", "一", "二", "三", "四", "五", "六"],
        fullDayNames: ["周日", "周一", "周二", "周三", "周四", "周五", "周六"],
        shortMonthNames: ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"],
        fullMonthNames: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
        titleContentDate: "设置生日",
        setButtonContent: "确定",
        clearButtonContent: "取消",
        formatHumanDate: function (date) {
            return date.yyyy + "-" + date.month + "-" + date.dd;
        }
    });

    var openId = $.getUrlParam('open_id');

    $('#submit').click(function () {
        var userInfo = {};
        userInfo.openId = openId;
        userInfo.birthday=$('#birthday').val();

        $.ajax({
            type: "POST",
            url: "/user/account/birthday",
            data: userInfo,
            success: function (data, status) {
                $('.container').html("<h3 class='text-center header_msg'>设置成功</h3>");
            },
            error: function (data) {
                var errorMsg = data;
                $('.container').html("<h3 class='text-center header_msg'>" + errorMsg + "</h3>");
            }
        });
    });
})