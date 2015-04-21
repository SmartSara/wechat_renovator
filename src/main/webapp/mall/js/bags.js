/**
 * Created by tangld on 2015/4/21.
 */
/**
 * Created by darlingtld on 2015/4/18.
 */
var bagModule = angular.module('BagModule', []);

bagModule.controller('bagController', function ($scope, $http, $location, $rootScope) {
    var limit = 15;

    $http.get('/product/list/bag/' + limit).success(function (data, status, headers, config) {
        $scope.items = data;
        $scope.items.sort(function (a, b) {
            return b.ts - a.ts;
        });
        var start = 0;
        $scope.itemBagShow = $scope.items.slice(start, start + 6);
        $scope.itemOthers = $scope.items.slice($scope.items.length - 4, $scope.items.length);
    });


})