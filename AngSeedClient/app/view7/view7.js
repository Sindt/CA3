'use strict';

angular.module('myApp.view7', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view7', {
                    templateUrl: 'view7/view7.html'
                });
            }])
        
        .controller("View7Controller", ['$scope', '$http', function($scope, $http) {
	
        $scope.users = [];
	
	$scope.addUserAsJSON = function(){		
		$scope.users.push({ 'username':$scope.newuser.username, 'password': $scope.newuser.password});
		// Writing it to the server
		//		
		var dataObj = {
				username : $scope.newuser.username,
				password : $scope.newuser.password,
		};	
		var res = $http.post('/api/user/registration', dataObj);
		res.success(function(data, status, headers, config) {
			$scope.message = data;
		});
		res.error(function(data, status, headers, config) {
			alert( "failure message: " + JSON.stringify({data: data}));
		});		
		// Making the fields empty
		//
		$scope.newuser.username='';
		$scope.newuser.password='';
	};
}]);

         