<%--
  Created by IntelliJ IDEA.
  User: virgil
  Date: 04.01.2017
  Time: 23:22
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" ng-app="myApp">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Event games</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <!-- Bootstrap-select -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.1/css/bootstrap-select.min.css">

    <!-- Custom -->
    <link rel="stylesheet" href="<c:url value='/static/css/events_page.css' />">

</head>
<body>

<!-- The navbar -->
<%@ include file="/static/partials/navbar.component.html" %>

<!-- The content -->
<div class="container-fluid" id="content" ng-controller="EventsController as ec">
    <!-- The left side of the page -->
    <div class="col-md-2 col-lg-2 sidebar" id="left">
        <ul class="nav nav-sidebar">
            <li>
                <a href="#">Football</a>
            </li>
            <li>
                <a href="#">Tennis</a>
            </li>
            <li>
                <a href="#">Handball</a>
            </li>
            <li>
                <a href="#">Basketball</a>
            </li>
            <li>
                <a href="#">Rugby</a>
            </li>
            <li>
                <a href="#">Hockey</a>
            </li>
            <li>
                <a href="#">Motorsport</a>
            </li>
            <li>
                <a href="#">Horse racing</a>
            </li>
            <li>
                <a href="#">American football</a>
            </li>
            <li>
                <a href="#">Polo</a>
            </li>
            <li>
                <a href="#">Darts</a>
            </li>
            <li>
                <a href="#">Winter sports</a>
            </li>

            <li>
                <a href="#">Rugby</a>
            </li>
            <li>
                <a href="#">Hockey</a>
            </li>
            <li>
                <a href="#">Motorsport</a>
            </li>
            <li>
                <a href="#">Horse racing</a>
            </li>
            <li>
                <a href="#">American football</a>
            </li>
            <li>
                <a href="#">Polo</a>
            </li>
            <li>
                <a href="#">Darts</a>
            </li>
            <li>
                <a href="#">Winter sports</a>
            </li>
        </ul>
    </div>

    <!-- The main part of the page -->
    <div class="col-md-8 col-md-offset-2 col-lg-8 col-lg-offset-2" id="center">

        <!-- Following events panel -->
        <div class="panel panel-default">
            <div class="panel-heading">
                <table class="table table-hover table-responsive">
                    <tr>
                        <!-- Event name -->
                        <div class="col-md-12 col-lg-12">
                            <h3 class="text-center"><strong>{{ ec.selectedEvent.name }}</strong></h3>
                        </div>
                    </tr>
                    <tr>
                        <!-- Competition -->
                        <div class="col-md-12 col-lg-12">
                            <h5 class="text-center">{{ ec.selectedEvent.competition }}</h5>
                        </div>
                    </tr>
                    <tr>
                        <div class="col-md-12 col-lg-12">
                            <!-- Date&time of the start of the event -->
                            <time class="center-block text-center"
                                  datetime="{{ ec.selectedEvent.dateFormattedString }}">{{ ec.selectedEvent.dateFormattedString }}</time>

                        </div>
                    </tr>
                </table>
            </div>
            <div class="panel-body">

                <!-- All games and their options -->
                <div class="col-md-12 col-lg-12" ng-repeat="game in ec.selectedEvent.games | toArray: false | orderBy: 'rank'">
                    <div class="panel panel-default" id="{{ game.name }}">
                        <div class="panel-heading">
                            <strong>{{ game.name }}</strong>
                        </div>

                        <div class="panel-body">
                            <table class="table table-hover table-responsive">
                                <thead>
                                <tr>
                                    <th class="col-md-8 col-lg-8" style="display: table-cell; vertical-align: middle;">
                                        Selection
                                    </th>
                                    <th class="col-md-2 col-lg-2 text-center" style="display: table-cell; vertical-align: middle;">
                                        For
                                    </th>
                                    <th class="col-md-2 col-lg-2 text-center" style="display: table-cell; vertical-align: middle;">
                                        Against
                                    </th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr ng-repeat="option in game.options">
                                    <td class="col-md-8 col-lg-8" style="display: table-cell; vertical-align: middle;">
                                        <span>{{ option.name }}</span>

                                    </td>
                                    <td class="col-md-2 col-lg-2" style="display: table-cell; vertical-align: middle; padding: 0;">
                                        <button type="button" class="btn btn-success col-md-12 col-lg-12">1.23</button>
                                    </td>
                                    <td class="col-md-2 col-lg-2" style="display: table-cell; vertical-align: middle; padding: 0;">
                                        <button type="button" class="btn btn-danger col-md-12 col-lg-12">2.34</button>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
             </div>
        </div>
    </div>

    <!-- The right side of the page -->
    <div class="col-md-2 col-lg-2" id="right">
        Right side
    </div>

</div><!-- /.container -->

<!-- The footer of the page -->
<%@ include file="/static/partials/footer.component.html" %>


<!-- Javascript -->
<!-- jQuery -->
<script src="//code.jquery.com/jquery-2.0.3.min.js"></script>

<!-- Angular -->
<script src="//code.angularjs.org/1.2.20/angular.js"></script>
<script src="//code.angularjs.org/1.2.20/angular-route.js"></script>
<script src="//code.angularjs.org/1.2.13/angular-cookies.js"></script>

<!-- Bootstrap -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<!-- livestamp (https://mattbradley.github.io/livestampjs/) -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.17.1/moment.js" type="text/javascript"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/livestamp/1.1.2/livestamp.js" type="text/javascript"></script>

<!-- Bootstrap-select -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.1/js/bootstrap-select.min.js"></script>

<!-- Controllers and services -->
<script src="<c:url value='/static/js/app.js' />"></script>
<script src="<c:url value='/static/js/service/authentication.service.js' />"></script>
<script src="<c:url value='/static/js/service/flash.service.js' />"></script>
<script src="<c:url value='/static/js/service/user_service.js' />"></script>
<script src="<c:url value='/static/js/service/friends_service.js' />"></script>
<script src="<c:url value='/static/js/service/events_service.js' />"></script>

<script src="<c:url value='/static/js/controller/events.controller.js' />"></script>

</body>
</html>