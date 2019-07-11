app.controller('ClientesController',function($scope,$http){
	$scope.inserting = false;
	$scope.clienteEdit = {};
    
	$scope.buscaTodosClientes = function(){
	    $http.get("clienteServlet").then(function(response){
	        
	        $scope.clientes = response.data;

	    },function(err){
	        alert('Erro ao chamar a API '+ err);
	    });
	}

    
    $scope.salvarCliente = function(cliente){
        $http({
            url: 'clienteServlet',
            method: "POST",
            data:  cliente 
        }).then(function(response){
        	$scope.limpaForm();
            $scope.buscaTodosClientes();
            
        },function(err){
            alert('Erro ao chamar a API '+ err);
        });
    }
    
    $scope.deleteCliente = function(idCliente){
        $http({
            url: 'clienteServlet?idCliente='+idCliente,
            method: "DELETE"
        }).then(function(response){
            $scope.buscaTodosClientes();
            
        },function(err){
            alert('Erro ao chamar a API '+ err);
        });
    }

    $scope.editarCliente = function(cliente){
        $scope.clienteEdit = angular.copy(cliente);
        $scope.insertMode(true);
    }

    $scope.insertMode = function(newValue){
    	$scope.inserting = newValue;
    }
    
    $scope.limpaForm = function(){
    	$scope.clienteEdit = {};
    }
    $scope.buscaTodosClientes();

});