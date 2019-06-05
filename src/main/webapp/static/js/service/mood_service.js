(function(){
	'use strict';

	angular.module('myApp').factory('MoodService', MoodService);
	MoodService.$inject = ['$http'];
	function MoodService($http){
		var REST_SERVICE_URI = 'http://localhost:8080/mWebApp2/track/';

		var factory = {
				createMood: createMood,
				updateMood: updateMood,
				fetchAllMoods: fetchAllMoods,
				deleteMood: deleteMood
		};

		return factory;
		

		function createMood(mood) {
			return $http.post(REST_SERVICE_URI, mood);
		}

		function updateMood(mood, id) {
			return $http.put(REST_SERVICE_URI+id, mood);
		}

		function fetchAllMoods(id) {
			return $http.get(REST_SERVICE_URI+id);
		}

		function deleteMood(id){
			return $http.delete(REST_SERVICE_URI+id);
		}

	}

})();