/**
 * Created by tangld on 2015/4/28.
 */
var appointmentModule = angular.module('AppointmentModule', []);

appointmentModule.controller('appointmentController', function ($scope, $http, $location, $rootScope) {

    var openId = $.getUrlParam('open_id');
    $http.get('/service/list/open_id/' + openId).success(function (data, status, headers, config) {
        $scope.services = data;
    });


});