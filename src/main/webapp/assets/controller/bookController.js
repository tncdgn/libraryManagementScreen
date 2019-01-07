/**
 * Created by tncdgn on 5.01.2019.
 */
'use strict';

angular.module('myApp').controller('BookController', ['$scope', 'BookService', function ($scope, BookService, $window) {
    var self = this;
    self.book = {id: null, bookname: '', authorname: '', captchaResponse: ''};
    self.books = [];


    self.save = save,
    self.clear = clear,
    self.updateBook = updateBook;
    self.deleteBook = deleteBook;
    self.open = open;
    self.hideSaveForm = hideSaveForm
    self.openSaveForm = openSaveForm


    getAllBooks()

    $scope.submit = function (form) {

        if (validateSaveForm(self.book)) {
            console.info("Error in validation")
        }
        else {
            if (self.book.id === null) {
                console.log('Saving New book', self.book);
                save(self.book);
            } else {
                updateBook(self.book);
                console.log('book updated with id ', self.book.id);
            }

        }

    };


    function save(book) {

        book.captchaResponse = grecaptcha.getResponse()
        BookService.saveBooks(book).then(
            function (data) {

                if (data.hasError) {
                    angular.forEach(data.errorMessage, function (value, key) {
                        console.log(key + ': ' + value);
                        $scope[value] = true
                    });
                }
                else {

                    getAllBooks()
                    hideSaveForm()
                }
            },


            function (errResponse) {
                console.error('Error while creating book');
            }
        );
    }

    function clear() {

        self.book = {id: null, bookname: '', authorname: '', captchaResponse: ''};
        $scope.authornameErrorMessage = false;
        $scope.bookNameErrorMessage = false;
        $scope.recaptchaErrorMessage = false;
        grecaptcha.reset()
        $scope.saveForm.$setPristine();
    }

    function open(id) {

        for (var i = 0; i < self.books.length; i++) {
            if (self.books[i].id === id) {
                self.book = angular.copy(self.books[i]);
                break;
            }
        }
        openSaveForm()
    }

    function getAllBooks() {

        BookService.getAllBooks()
            .then(
            function (d) {
                self.books = d;
            },
            function (errResponse) {
                console.error('Error while fetching Books');
            }
        );
    }

    function updateBook(book) {
        if (validateSaveForm(self.book)) {
            console.info("Error in validation")
        }
        else {
            BookService.updateBook(book)
                .then(

                function (data) {

                    if (data.hasError) {
                        angular.forEach(data.errorMessage, function (value, key) {
                            console.log(key + ': ' + value);
                            $scope[value] = true
                        });
                    }
                    else {

                        getAllBooks()
                        hideSaveForm()
                    }
                },

                function (errResponse) {
                    console.error('Error while updating Book');
                }
            );
        }

    }

    function deleteBook(id) {
        if (self.book.id === id) {//clean form if the user to be deleted is shown there.
            clear();
        }
        if (confirm("Are you sure to delete this data?")) {
            BookService.deleteBook(id)
                .then(
                getAllBooks,
                clear(),
                function (errResponse) {
                    console.error('Error while deleting Book');
                }
            );
        }

    }


    function openSaveForm() {

        if (self.book.id == '' || self.book.id == null) {
            $scope.show = true;
        }
        else {
            $scope.show = false;
        }
        var element = angular.element('#addBookModal');
        element.modal('show');

    }

    function hideSaveForm() {
        var element = angular.element('#addBookModal');
        element.modal('hide');
        clear()
    }

    function validateSaveForm(book) {
        var element = angular.element('#gCaptcha');


        $scope.bookNameErrorMessage = false
        $scope.authornameErrorMessage = false
        $scope.recaptchaErrorMessage = false;
        var hasError = false;
        if (self.book.bookname == '' || self.book.bookname == undefined) {
            $scope.bookNameErrorMessage = true;
            hasError = true
        }

        if (self.book.authorname == '' || self.book.authorname == undefined) {
            $scope.authornameErrorMessage = true;
            hasError = true
        }

        if (grecaptcha.getResponse() == 0 && $scope.show == true) {
            $scope.recaptchaErrorMessage = true;
            hasError = true
        }
        return hasError;
    }

}]);
