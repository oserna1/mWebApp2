(function(){
	'use strict';

	angular.module('myApp').factory('UserService', UserService);
	UserService.$inject = ['$http'];
	function UserService($http){
		var REST_SERVICE_URI = 'http://localhost:8080/mWebApp2/user/';

		var factory = {
				createUser: createUser,
				updateUser: updateUser,
				fetchAllUsers: fetchAllUsers,
				deleteUser: deleteUser,
		};

		return factory;

		function createUser(user) {
			return $http.post(REST_SERVICE_URI, user);

		}

		function updateUser(user, id) {
			return $http.put(REST_SERVICE_URI+id, user);
		}

		function fetchAllUsers() {
			return $http.get(REST_SERVICE_URI);
		}

		function deleteUser(id){
			return $http.delete(REST_SERVICE_URI+id);
		}

	}

})();