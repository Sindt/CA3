'use strict';
angular.module('myApp.view3', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view3', {
                    templateUrl: 'view3/view3.html',
                    controller: 'View3Ctrl'
                });
            }])

        .controller('View3Ctrl', ["$http", function ($http) {
                var self = this;
                $http({
                    method: 'GET',
                    url: 'http://cvrapi.dk/api?vat=3167%208021&country=dk'
                }).then(function successCallback(response) {
                    self.cvr = response.data;
                }, function errorCallback(response) {
                    self.error = response.status + " Noooo! " + response.statusText + "  upps";
                    // called asynchronously if an error occurs
                    // or server returns response with an error status.
                });

                self.status = {
                    isFirstOpen: true,
                    isFirstDisabled: false
                };
            }]);