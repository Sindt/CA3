'use strict';

angular.module('myApp.view5', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view5', {
                    templateUrl: 'view5/view5.html'
                });
            }])
        .controller("View5Ctrl", ["$http", "$scope", function ($http, $scope) {
                var self = this;
                $scope.getUsers = function () {
                    $http({
                        method: "GET",
                        url: "api/admin/users"
                    }).then(function succesCallback(response) {
                        self.data = response.data;
                    }).then(function errorCallback(response) {
                        self.error = "Du laver fejl, du lugter";
                    })
                };
                $scope.getUsers();

                $scope.deleteUser = function (users) {
                    $http({
                        method: "DELETE",
                        url: "api/admin/user/" + users.id
                    }).then(function succesCallback(response) {
                        $scope.getUsers();

                    });


                };
            }]);