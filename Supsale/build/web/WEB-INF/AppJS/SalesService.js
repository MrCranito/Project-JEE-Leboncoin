(function () {
    'use strict'
    
    angular
        .module("supsale")
        .factory("salesService", salesService)

        salesService.$inject = [];
        
        function salesService(){
            
            var service = {
                getAllSales : getAllSales
            }
            
            // LOGIC
            function getAllSales(callback) {
                var baseT = Restangular.all(listSale);
                
                baseT.getList().then(function(sales) {
                    callback(sales)
                })
                
            }
            
        }
})

