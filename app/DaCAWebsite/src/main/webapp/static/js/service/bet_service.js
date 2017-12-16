/**
 * Created by Ana on 20-Dec-16.
 */
'use strict';

angular.module('myApp').factory('BetService', ['$http', '$q', function($http, $q){

    var USER_REST_SERVICE_URI = 'http://localhost:8080/TradingNetworksWebsite/user/bet/';

    var factory = {
        getBetsByUser: getBetsByUser
    };

    return factory;

    function getBetsByUser(username) {
        console.log('Fetch bets for user with username: ' + username);
        var deferred = $q.defer();
        $http.get(USER_REST_SERVICE_URI + username)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while getting best for User with username');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }
}]);