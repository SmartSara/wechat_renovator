/**
 * Created by darlingtld on 2015/4/18.
 */
var mallModule = angular.module('MallModule', []);

mallModule.controller('homeController', function ($scope, $http, $location, $rootScope) {
    var limit = 15;

    $http.get('/product/list/' + limit).success(function (data, status, headers, config) {
        $scope.items = data;
        $scope.items.sort(function (a, b) {
            return b.ts - a.ts;
        });
        var start = 0;
        $scope.itemLatest = $scope.items.slice(start, start + 3);
        $scope.itemMainShow = $scope.items.slice(start + 3, start + 9);
        $scope.itemOthers = $scope.items.slice($scope.items.length - 4, $scope.items.length);
        $('.banner').css('background', 'url(/repository/images/banner.png) no-repeat center center');
        $('.banner').css('background-size', 'cover');
        $('.in-left').css('background', 'url(/repository/images/luxury_img_a.jpg) 0px 0px');
    });


})