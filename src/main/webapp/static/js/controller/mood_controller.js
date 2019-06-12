(function(){

	'use strict';

	angular.module('myApp').controller('MoodController',MoodController); 
	MoodController.$inject = ['$scope','MoodService','UserService'];
	function MoodController ($scope,MoodService, UserService) {
		
	    var self = this;
	    self.mood={id:null,moodRange:null,description:'', ts:null, uid: null};
	    self.moods=[];
	    
	    self.user={id:null,username:'',email:'', password:''};
	    self.users=[];
	    self.userId = null;
	
	    self.submit = submit;
	    self.edit = edit;
	    self.remove = remove;
	    self.reset = reset;
	    self.fetchMoodsByUid = fetchMoodsByUid;
	    self.setUid = setUid;
	    
	    self.submitUser = submitUser
	    self.editUser = editUser
	    self.removeUser = removeUser
	    self.resetUser = resetUser
	
	    
	    fetchAllUsers();
	    
	    function setUid(uid){
	    	self.userId = uid;
	    }
	
	    function fetchMoodsByUid(uid){
	        MoodService.fetchAllMoods(uid)
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
	    	mood.uid = self.userId;
	        MoodService.createMood(mood)
	            .then((response) => {
	            	fetchMoodsByUid(self.userId);
	            })
	            .catch((errResponse) => {
	                console.error('Error while creating Mood');
	            });
	    }
	
	    function updateMood(mood, id){
	        MoodService.updateMood(mood, id)
	            .then((response) => {
	            	fetchMoodsByUid(self.userId);
	            })
	            .catch((errResponse) => {
	                console.error('Error while updating Mood');
	            });
	    }
	
	    function deleteMood(id){
	        MoodService.deleteMood(id)
	            .then((response) => {
	            	fetchMoodsByUid(self.userId);
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
	    
	    function fetchAllUsers(){
	        UserService.fetchAllUsers()
	            .then((d) => {
	                self.users = d.data;
	            })
	            .catch((errResponse) => {
	                console.error('Error while fetching Users');
	            });
	    } 
	
	    function createUser(user){
	        UserService.createUser(user)
	            .then((response) => {
	            	console.log(response.data);
	            	fetchAllUsers();
	            })
	            .catch((errResponse) => {
	                console.error('Error while creating User');
	            });
	    }
	
	    function updateUser(user, id){
	        UserService.updateUser(user, id)
	            .then((response) => {
	            	fetchAllUsers();
	            })
	            .catch((errResponse) => {
	                console.error('Error while updating User');
	            });
	    }
	
	    function deleteUser(id){
	        UserService.deleteUser(id)
	            .then((response) => {
	            	fetchAllUsers();
	            })
	            .catch((errResponse) => {
	                console.error('Error while deleting User');
	            });
	    }
	
	    function submitUser() {
	        if(self.user.id===null){
	            console.log('Saving New User', self.user);
	            createUser(self.user);
	        }else{
	            updateUser(self.user, self.user.id);
	            console.log('User updated with id ', self.user.id);
	        }
	        reset();
	    }
	
	    function editUser(id){
	        console.log('id to be edited', id);
	        for(var i = 0; i < self.users.length; i++){
	            if(self.users[i].id === id) {
	                self.user = angular.copy(self.users[i]);
	                break;
	            }
	        }
	    }
	
	    function removeUser(id){
	        console.log('id to be deleted', id);
	        if(self.user.id === id) {
	            reset();
	        }
	        deleteUser(id);
	    }
	
	
	    function resetUser(){
	        self.user={id:null,username:'',email:'',password:''};
	        $scope.myForm.$setPristine(); 
	    }
	}
})();
		