var app=angular.module('Locadora',[]);



app.controller('LocadoraController',function($scope,$http){
    
$scope.paginaAtual = 'partials/clientes.html';

$scope.setPaginaAtual = function(url){

	$scope.paginaAtual = url;
}

});