/**
 * Created by zhus1 on 2015/4/4.
 */
app = '/wechat_renovator';
function formatDate(date, format) {
    var o = {
        "M+": date.getMonth() + 1, //month
        "d+": date.getDate(),    //day
        "h+": date.getHours(),   //hour
        "m+": date.getMinutes(), //minute
        "s+": date.getSeconds(), //second
        "q+": Math.floor((date.getMonth() + 3) / 3),  //quarter
        "S": date.getMilliseconds() //millisecond
    }
    if (/(y+)/.test(format)) format = format.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o) if (new RegExp("(" + k + ")").test(format))
        format = format.replace(RegExp.$1,
            RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
    return format;
}

function getURLParameter(key) {
    var reg = new RegExp("(^|&)" + key + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}

function paginationEnter(evt, val) {
    var evt = evt ? evt : (window.event ? window.event : null);//兼容IE和FF
    if (evt.keyCode == 13) {
        if (val < 1) {
            alert("请输入大于0的数！");
            return;
        }
        _pageTable($("#numPerPage").val());
    }
}