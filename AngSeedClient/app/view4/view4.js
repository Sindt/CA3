'use strict';
angular.module('myApp.view4', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view4', {
                    templateUrl: 'view4/view4.html',
                    controller: "View4Ctrl"
                });
            }])

        .controller("View4Ctrl", ["$http", function ($http) {
                var self = this;
                $http({
                    method: "GET",
                    url: "api/currency/dailyrates"
                }).then(function succesCallback(response) {
                    self.data = response.data;
                }).then(function errorCallback(response) {
                    self.error = response.status;
                })
            }]);