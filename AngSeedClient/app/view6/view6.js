'use strict';

angular.module('myApp.view6', ['ngRoute'])
        .config(function ($httpProvider) {
            $httpProvider.interceptors.push('authInterceptor');
        })

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view6', {
                    templateUrl: 'view6/view6.html'
                });
            }]);
