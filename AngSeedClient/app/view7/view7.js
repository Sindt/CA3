'use strict';

angular.module('myApp.view7', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view7', {
                    templateUrl: 'view7/view7.html'
                });
            }])

        .controller("View7Controller", ['$scope', '$http', function ($scope, $http) {

                $scope.addUserAsJSON = function () {
                    // Writing it to the server
                    //		

                    var res = $http.post('api/user/registration', $scope.newuser);
                    res.success(function (data, status, headers, config) {
                        $scope.message = data;
                        alert("You are registered. Go to the Login Section to log in.");
                        $scope.newuser.username = '';
                        $scope.newuser.password = '';
                    });
                    res.error(function (data, status, headers, config) {
                        alert("failure message: " + JSON.stringify({data: data}));
                    });
//		 Making the fields empty


                };

            }]);

         