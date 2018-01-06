'use strict';

angular.module('myApp').factory('UserService', ['$http', '$q', function($http, $q){

    var USER_REST_SERVICE_URI = 'http://localhost:8080/TradingNetworksWebsite/user/';
    var CREATE = "create/";

    var factory = {
        fetchAllUsers: fetchAllUsers,
        getUserByUsername: getUserByUsername,
        createUser: createUser,
        updateUser: updateUser,
        deleteUser: deleteUser
    };

    return factory;

    function fetchAllUsers() {
        var deferred = $q.defer();
        $http.get(USER_REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Users');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function getUserByUsername(username) {
        console.log('Fetch user with username: ' + username);
        var deferred = $q.defer();
        $http.get(USER_REST_SERVICE_URI + username)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while getting User with username');
                    deferred.reject(errResponse);
                }
            );  
        return deferred.promise;
    }

    function createUser(user) {
        var deferred = $q.defer();

        //log the user that has to be created
        console.log(user);

        $http.post(USER_REST_SERVICE_URI + CREATE, user)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating User');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }


    function updateUser(user, username) {
        var deferred = $q.defer();
        $http.put(USER_REST_SERVICE_URI + username, user)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating User');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function deleteUser(username) {
        var deferred = $q.defer();
        $http.delete(USER_REST_SERVICE_URI + username)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting User');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

}]);