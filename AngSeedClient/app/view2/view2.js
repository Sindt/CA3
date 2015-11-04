'use strict';
angular.module('myApp.view2', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view2', {
                    templateUrl: 'view2/view2.html',
                    controller: "View2Controller"
                });
            }])
        .controller('View2Controller', function ($http, $scope) {
            $http({
                method: 'GET',
                URL: 'api/user'
            }).then(function succesCallback(res) {
                $scope.data = res.data.message;
            }).then(function errorCallBack(res) {
                $scope.error = res.status + ": " + res.data.statusText;
            });
        });

                