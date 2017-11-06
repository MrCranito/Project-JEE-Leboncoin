(function () {
    'use strict'
    
    angular
        .module("supsale")
        .controller('salesController', salesController)

        salesController.$inject = ['$scope', 'salesService'];
        
        function salesController($scope, salesService){
            function getAllSales() {
                salesService.getAllSales(function(data){
                    $scope.listSales = data;
                })
            }
            
            RestangularProvider.setBaseUrl("http://localhost:8080/Supsale/webresources");
        }
})

