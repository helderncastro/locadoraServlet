app.controller('FilmesController',function($scope,$http){
	$scope.inserting = false;
	$scope.filmeEdit = {};
    
	$scope.buscaTodosfilmes = function(){
	    $http.get("filmeServlet").then(function(response){
	        
	        $scope.filmes = response.data;

	    },function(err){
	        alert('Erro ao chamar a API '+ err);
	    });
	}

    
    $scope.salvarFilme = function(filme){
        $http({
            url: 'filmeServlet',
            method: "POST",
            data:  filme 
        }).then(function(response){
        	$scope.limpaForm();
            $scope.buscaTodosfilmes();
            
        },function(err){
            alert('Erro ao chamar a API '+ err);
        });
    }
    
    $scope.deleteFilme = function(idFilme){
        $http({
            url: 'filmeServlet?idFilme='+idFilme,
            method: "DELETE"
        }).then(function(response){
            $scope.buscaTodosFilmes();
            
        },function(err){
            alert('Erro ao chamar a API '+ err);
        });
    }

    $scope.editarFilme = function(filme){
        $scope.filmeEdit = angular.copy(filme);
        $scope.insertMode(true);
    }

    $scope.insertMode = function(newValue){
    	$scope.inserting = newValue;
    }
    
    $scope.limpaForm = function(){
    	$scope.filmeEdit = {};
    }
    $scope.buscaTodosFilmes();

});