/**
 * Created by tangld on 2015/4/21.
 */
/**
 * Created by darlingtld on 2015/4/18.
 */
var leatherModule = angular.module('LeatherModule', []);

leatherModule.controller('leatherController', function ($scope, $http, $location, $rootScope) {
    var limit = 15;

    $http.get('/product/list/leather/' + limit).success(function (data, status, headers, config) {
        $scope.items = data;
        $scope.items.sort(function (a, b) {
            return b.ts - a.ts;
        });
        var start = 0;
        $scope.itemLeatherShow = $scope.items.slice(start, start + 6);
        $scope.itemOthers = $scope.items.slice($scope.items.length - 4, $scope.items.length);
    });


})