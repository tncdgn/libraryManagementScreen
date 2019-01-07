/**
 * Created by tncdgn on 5.01.2019.
 */
'use strict';

angular.module('myApp').factory('BookService', ['$http', '$q', function($http, $q){
    var factory = {

        saveBooks: saveBooks,
        getAllBooks:getAllBooks,
        updateBook:updateBook,
        deleteBook:deleteBook

    };
    return factory


    function saveBooks(book) {

        var SAVE_URL="/books/save"

        var deferred = $q.defer();
        showPleaseWaitDialog()
        $http.post(SAVE_URL, book)
            .then(
            function (response) {

                deferred.resolve(response.data);
                hidePleaseWaitDialog()

            },
            function(errResponse){
                console.error('Error while creating User');
                deferred.reject(errResponse);
                hidePleaseWaitDialog()
            }
        );
        return deferred.promise;
    }

    function getAllBooks() {
        var BOOKLIST_URL="/books/getAllBooks"

        var deferred = $q.defer();
        showPleaseWaitDialog()
        $http.get(BOOKLIST_URL)
            .then(
            function (response) {
                deferred.resolve(response.data);
                hidePleaseWaitDialog()
            },
            function(errResponse){
                console.error('Error while fetching Books');
                deferred.reject(errResponse);
                hidePleaseWaitDialog()
            }
        );
        return deferred.promise;
    }


    function updateBook(book) {
        var UPDATE_URI="/books/updateBook"
        showPleaseWaitDialog()
        var deferred = $q.defer();
        $http.put(UPDATE_URI, book)
            .then(
            function (response) {
                deferred.resolve(response.data);
                hidePleaseWaitDialog()
            },
            function(errResponse){
                console.error('Error while updating User');
                deferred.reject(errResponse);
                hidePleaseWaitDialog()
            }
        );
        return deferred.promise;
    }
    function deleteBook(id) {
        var DELETE_URL="/books/deleteBook/"
        showPleaseWaitDialog()

        var deferred = $q.defer();
        $http.delete(DELETE_URL+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
                hidePleaseWaitDialog()
            },
            function(errResponse){
                console.error('Error while deleting User');
                deferred.reject(errResponse);
                hidePleaseWaitDialog()
            }
        );
        return deferred.promise;
    }
    function showPleaseWaitDialog() {
        var element = angular.element('#pleaseWaitDialog');
        element.modal('show');
    }

    function hidePleaseWaitDialog() {
        var element = angular.element('#pleaseWaitDialog');
        element.modal('hide');
    }

}]);