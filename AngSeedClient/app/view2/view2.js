'use strict';
angular.module('myApp.view2', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view2', {
                    templateUrl: 'view2/view2.html',
                    controller: "View2Controller"
                });
            }])
        .controller('View2Controller', function ($http, $scope) {
            var self = this;
            $http({
                method: 'GET',
                url: 'api/user'
            }).then(function successCallback(response) {
                self.data = response.data;
            }, function errorCallback(response) {
                self.error = response.status + " Noooo! " + response.statusText + "  upps";
            });
        });

                