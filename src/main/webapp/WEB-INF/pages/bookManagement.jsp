<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>

<head>
	<title>Book Management</title>

	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>

	<script src="/assets/app.js"></script>

	<script src="/assets/controller/bookController.js"></script>
	<script src="/assets/service/bookService.js"></script>
	<script src='https://www.google.com/recaptcha/api.js'></script>

</head>
<body ng-app="myApp" >
<div ng-controller="BookController as ctrl">

	<div class="modal" id="pleaseWaitDialog" data-backdrop="static" data-keyboard="false" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Please wait!</h4>
			</div>
			<div class="modal-body">
				<div class="progress">
					<div class="progress-bar progress-bar-success progress-bar-striped active" role="progressbar"
						 aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width:100%; height: 40px">
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

	<div class="jumbotron text-center">
		<h1>Welcome to Book Management Screen</h1>
		<p>
		</p>
	</div>
	<div align="center">
		<button type="button" class="btn btn-primary" data-toggle="modal" ng-click="ctrl.openSaveForm()">Add A Book</button>
	</div>

	<!-- The Modal -->
	<div class="modal" id="addBookModal" data-backdrop="static" >
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">{{!ctrl.book.id ? 'Add a Book' : 'Update a Book'}}</h4>
				</div>


				<!-- Modal body -->
				<div class="modal-body">
					<form ng-submit="ctrl.save()" name="saveForm">
						<input type="hidden" ng-model="ctrl.book.id" />

					<div class="form-group" >
						<label for="bookName">Book Name:</label>
						<input type="text" ng-model="ctrl.book.bookname" ng-required="!disabled" ng-disabled="disabled" ng-class="{error: showMsgs}" class="form-control"  id="bookName" placeholder="Enter a book name" name="bookName"  >
						<p ng-show="bookNameErrorMessage" style="color: red">This field is required</p>

					</div>
					<div class="form-group">
						<label for="authorName">Author Name:</label>
						<input type="text" ng-model="ctrl.book.authorname" class="form-control" id="authorName" placeholder="Enter author name" name="authorName" required >
						<p ng-show="authornameErrorMessage" style="color: red">This field is required</p>


					</div>
						<div class="form-group"  ng-show="show">
							<div class="g-recaptcha" id="gCaptcha" data-callback="recaptchaCallback" ng-model="ctrl.book.captcha" data-sitekey="6LearocUAAAAABPL-V6wD46o1PhxkdZhqVNE-Ktv"></div>
							<p ng-show="recaptchaErrorMessage" style="color: red">Captcha Error</p>

						</div>

					</form>
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-success"  ng-click="submit()">{{!ctrl.book.id ? 'Add' : 'Update'}}</button>

					<button type="button" class="btn btn-danger" ng-click="ctrl.hideSaveForm()"-dismiss="modal">Close</button>
				</div>

			</div>
		</div>
	</div>
	<div class="panel panel-default" style="margin-top: 5%" align="center">
		<!-- Default panel contents -->
		<div class="panel-heading"><span class="lead">List of Books </span></div>
		<div class="tablecontainer" >
			<table class="table table-hover" >
				<thead>
				<tr>

					<th>Book Name</th>
					<th>Author Name</th>
					<th>Operations</th>

					<th width="20%"></th>
				</tr>
				</thead>
				<tbody>
				<tr ng-repeat="u in ctrl.books">
					<td><span ng-bind="u.bookname"></span></td>
					<td><span ng-bind="u.authorname"></span></td>
					<td>
						<button type="button" ng-click="ctrl.open(u.id)" class="btn btn-success custom-width">Edit</button>
						<button type="button" ng-click="ctrl.deleteBook(u.id)" class="btn btn-danger custom-width">Remove</button>
					</td>
				</tr>
				</tbody>
			</table>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-sm-4">

			</div>
			<div class="col-sm-4">

			</div>
			<div class="col-sm-4">

			</div>
		</div>
	</div>
</div>


</body>
</html>
