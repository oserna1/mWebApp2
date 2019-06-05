(function(){

	'use strict';

	angular.module('myApp').controller('MoodController',MoodController); 
	MoodController.$inject = ['$scope','MoodService','UserService'];
	function MoodController ($scope,MoodService, UserService) {
	    var self = this;
	    self.mood={id:null,moodRange:null,description:'', ts:null, uid: null};
	    self.moods=[];
	
	    self.submit = submit;
	    self.edit = edit;
	    self.remove = remove;
	    self.reset = reset;
	    self.fetchMoodsByUid = fetchMoodsByUid;
	
	
	    
	
	    function fetchMoodsByUid(){
	        MoodService.fetchAllMoods(UserService.uId)
	            .then((d)=>{
	                self.moods = d.data;
	            })
	            .catch((errResponse) => {
	                console.error(errResponse);
	            });
	    } 
	    
	    function submit() {
	        if(self.mood.id===null){
	            console.log('Saving New Mood', self.mood);
	            createMood(self.mood);
	        }else{
	            updateMood(self.mood, self.mood.id);
	            console.log('Mood updated with id ', self.mood.id);
	        }
	        reset();
	    }
	
	    function createMood(mood){
	    	self.mood.uid = UserService.uId;
	        MoodService.createMood(mood)
	            .then((response) => {
	            	fetchMoodsByUid();
	            })
	            .catch((errResponse) => {
	                console.error('Error while creating Mood');
	            });
	    }
	
	    function updateMood(mood, id){
	        MoodService.updateMood(mood, id)
	            .then((response) => {
	            	fetchMoodsByUid();
	            })
	            .catch((errResponse) => {
	                console.error('Error while updating Mood');
	            });
	    }
	
	    function deleteMood(id){
	        MoodService.deleteMood(id)
	            .then((response) => {
	            	fetchMoodsByUid();
	    		})
	            .catch((errResponse) => {
	                console.error('Error while deleting Mood');
	            });
	    }
	
	    function edit(id){
	        console.log('id to be edited', id);
	        for(var i = 0; i < self.moods.length; i++){
	            if(self.moods[i].id === id) {
	                self.mood = angular.copy(self.moods[i]);
	                break;
	            }
	        }
	    }
	
	    function remove(id){
	        console.log('id to be deleted', id);
	        if(self.mood.id === id) {//clean form if the mood to be deleted is shown there.
	            reset();
	        }
	        deleteMood(id);
	    }
	
	
	    function reset(){
	    	self.mood={id:null,moodRange:null,description:'', ts:null, uid: null};
	    	$scope.myForm.$setPristine(); //reset Form
	    } 
	}
})();
		