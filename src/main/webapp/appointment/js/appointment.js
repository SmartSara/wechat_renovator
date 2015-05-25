/**
 * Created by tangld on 2015/4/30.
 */
var module = angular.module('AppointmentModule', []);

module.controller('appointmentController', function ($scope, $http) {
    $('.datetime').mobiscroll().datetime({
        theme: 'sense-ui',     // Specify theme like: theme: 'ios' or omit setting to use default
        mode: 'scroller',       // Specify scroller mode like: mode: 'mixed' or omit setting to use default
        lang: 'zh',       // Specify language like: lang: 'pl' or omit setting to use default
        minDate: new Date(),  // More info about minDate: http://docs.mobiscroll.com/2-14-0/datetime#!opt-minDate
        maxDate: new Date(2020, 1, 1, 1, 1),   // More info about maxDate: http://docs.mobiscroll.com/2-14-0/datetime#!opt-maxDate
        stepMinute: 5  // More info about stepMinute: http://docs.mobiscroll.com/2-14-0/datetime#!opt-stepMinute
    });

    var openId = $.getUrlParam('open_id');
    $http.get(app+'/service/list/open_id/' + openId).success(function (data, status, headers, config) {
        $scope.services = data;
    });

    var methodNames = ['预约自提', '寄送上门'];
    var defaultAddress = '雷诺维特自提站';
    var name = ['fetch', 'get'];
    $scope.method = {
        name: name[0],
        defaultName: methodNames[0],
        optionName: methodNames[1],
        hint: '',
        defaultAddress: defaultAddress
    }
    $scope.appointment = function (method) {
        if (method == methodNames[0]) {
            $scope.method.name = name[0];
            $scope.method.defaultName = methodNames[0];
            $scope.method.optionName = methodNames[1];
            $scope.method.hint = '';
            $scope.method.defaultAddress = defaultAddress;
        } else {
            $scope.method.name = name[1];
            $scope.method.defaultName = methodNames[1];
            $scope.method.optionName = methodNames[0];
            $scope.method.hint = '请填写寄送地址：例如：xx市xx区xx路xx号xx室 邮编：xxxxxxx';
            $scope.method.defaultAddress = ''
        }
    }

})

module.directive('fetchGet', function () {
    return {
        restrict: 'AE',
        scope: {},
        link: function (scope, element, attr) {
            scope.$watch(attr.fetchGet, function (value) {
                if (value == 'fetch') {
                    element.val('雷诺维特自提站');
                    element.attr('readonly', true);
                } else {
                    element.val('');
                    element.attr('readonly', false);
                }
            });
        }
    }
})
