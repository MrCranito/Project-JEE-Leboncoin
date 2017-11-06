(function () {
    'use strict'
    
    angular
        .module("supsale",[
            'restangular'
        ])
        .config(configure)

        configure.$inject = ['$scope'];
        
        function configure($scope){
            
            RestangularProvider.setBaseUrl("http://localhost:8080/Supsale/webresources");
        }
})

