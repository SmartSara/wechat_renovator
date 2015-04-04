/**
 * Created by zhus1 on 2015/4/4.
 */

$(function () {
    _initialNav();
});

function _initialNav(){
    $('#nav').load("nav.html", function() {
        $("#productNav").addClass("active");
    });
}