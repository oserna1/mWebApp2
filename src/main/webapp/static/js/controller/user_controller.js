(function(){

	'use strict';

	angular.module('myApp').controller('UserController',UserController); 
	UserController.$inject = ['$scope','UserService'];
	function UserController ($scope,UserService) {
	    var self = this;
	    self.user={id:null,username:'',email:'', password:''};
	    self.users=[];
	
	    self.submit = submit;
	    self.edit = edit;
	    self.remove = remove;
	    self.reset = reset;
	    self.selectedId = selectedId;
	
	
	    fetchAllUsers();
	
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
	    
	    function selectedId(id){
	    	console.log("selected user id is: ", id);
	    	UserService.uId = id;
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
	
	    function submit() {
	        if(self.user.id===null){
	            console.log('Saving New User', self.user);
	            createUser(self.user);
	        }else{
	            updateUser(self.user, self.user.id);
	            console.log('User updated with id ', self.user.id);
	        }
	        reset();
	    }
	
	    function edit(id){
	        console.log('id to be edited', id);
	        for(var i = 0; i < self.users.length; i++){
	            if(self.users[i].id === id) {
	                self.user = angular.copy(self.users[i]);
	                break;
	            }
	        }
	    }
	
	    function remove(id){
	        console.log('id to be deleted', id);
	        if(self.user.id === id) {
	            reset();
	        }
	        deleteUser(id);
	    }
	
	
	    function reset(){
	        self.user={id:null,username:'',email:'',password:''};
	        $scope.myForm.$setPristine(); 
	    }
	}
})();
		