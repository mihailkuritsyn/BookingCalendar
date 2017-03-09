angular.module('hello', ['ngRoute', 'ngAnimate', 'ngSanitize', 'ui.bootstrap']).config(function ($routeProvider, $httpProvider) {

    $routeProvider.when('/', {
        templateUrl: 'home.html',
        controller: 'meetingController',
        controllerAs: 'controller'
    }).otherwise('/');

}).controller('navigation',

    function ($rootScope, $http, $location, $route) {

        var self = this;

        self.tab = function (route) {
            return $route.current && route === $route.current.controller;
        };
    })

    .controller('meetingController', function ($scope, $http, $filter) {

        //TODO how to move this logic to view?
        var self = this;

        $scope.clear = function () {
            $scope.dt = null;
        };

        $scope.open1 = function () {
            $scope.popup1.opened = true;
        };

        $scope.open2 = function () {
            $scope.popup2.opened = true;
        };

        $scope.popup1 = {
            opened: false
        };

        $scope.popup2 = {
            opened: false
        };

        //------
        var getCalendar = function () {
            $http.get('booking_calendar/').then(function (response) {
                $scope.bookingCalendar = response.data.days;
            });
        }
        getCalendar();

        var defaultOfficeHoursStart = new Date();
        defaultOfficeHoursStart.setHours(9);
        defaultOfficeHoursStart.setMinutes(0);

        var defaultOfficeHoursEnd = new Date();
        defaultOfficeHoursEnd.setHours(17);
        defaultOfficeHoursEnd.setMinutes(30);

        self.request = {
            employeeId: "EMP01",
            startTime: null,
            duration: 2,
            submissionTime: null
        }

        self.bookingRequest = {
            officeHoursStart: defaultOfficeHoursStart,
            officeHoursEnd: defaultOfficeHoursEnd,
            meetingRequests: [self.request]
        }

        self.requestBooking = function () {
            self.request.startTime = $filter('date')(self.request.startTime, "yyyy-MM-dd HH:mm:ss");
            self.request.submissionTime = $filter('date')(new Date(), "yyyy-MM-dd HH:mm:ss");

            self.bookingRequest.officeHoursStart = $filter('date')(self.bookingRequest.officeHoursStart, "HH:mm:ss");
            self.bookingRequest.officeHoursEnd = $filter('date')(self.bookingRequest.officeHoursEnd, "HH:mm:ss");

            $http.put('booking_calendar/', self.bookingRequest).then(function (response) {
                $scope.bookingCalendar = response.data.days;
            });
        }
    })
;