package br.com.locadora.controller;

import java.util.List;

import br.com.locadora.exception.RuntimeException;
import br.com.locadora.model.Cliente;
import br.com.locadora.service.ClienteService;

public class ClienteController {

	ClienteService service = new ClienteService();

	public void cadastrarCliente(Cliente cliente) throws Exception {
		service.cadastrarCliente(cliente);
	}

	public void deletarCliente(Integer idCliente) throws Exception {
		service.deletarCliente(idCliente);
	}

	public boolean existeLocacaoCliente(Integer idCliente) throws Exception {
		return service.existeLocacaoCliente(idCliente);
	}
	
	public Cliente listarClienteId(Integer idCliente) throws Exception {
		return service.buscarClientePorId(idCliente);
	}

	public List<Cliente> listarClientes() throws Exception {
		List<Cliente> clientes = null;
		
		try {
			clientes = service.listarClientes();
		}catch (RuntimeException e) {
			e.printStackTrace();
		}
		return clientes;
	}

	@Override
	public String toString() {
		return "ClienteController [service=" + service + "]";
	}

	public void atualizarCliente(Cliente cliente) throws Exception {
		service.atualizarCliente(cliente);
	}

	public boolean existeCliente(Integer idCliente) throws Exception {
		return service.existeCliente(idCliente);
	}

	
}
