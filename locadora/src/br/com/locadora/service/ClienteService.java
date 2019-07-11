package br.com.locadora.service;

import java.sql.SQLException;
import java.util.List;

import br.com.locadora.exception.BusinessException;
import br.com.locadora.exception.RuntimeException;
import br.com.locadora.model.Cliente;
import br.com.locadora.repository.ClienteRepository;

public class ClienteService {

	ClienteRepository repository = new ClienteRepository();

	public void cadastrarCliente(Cliente cliente) throws RuntimeException, BusinessException {
		try {
			if (cliente.getCpf() == null || cliente.getCpf().isEmpty()) {
				throw new BusinessException("O CPF do cliente não pode ficar em branco");
			}

			repository.cadastrar(cliente);

		} catch (SQLException e) {
			throw new RuntimeException("Erro ao cadastrar cliente.", e);
		}
	}

	public void deletarCliente(Integer idCliente) throws RuntimeException, BusinessException {
		try {
			if (!repository.existeCliente(idCliente)) {
				throw new BusinessException("Cliente não encontrado");
			}

			if (existeLocacaoCliente(idCliente)) {
				throw new BusinessException("Cliente não pode ser apagado porque possui Locações");
			}

			repository.deletar(idCliente);

		} catch (SQLException e) {
			throw new RuntimeException("Erro ao deletar cliente.", e);
		}
	}

	public List<Cliente> listarClientes() throws RuntimeException {
		try {
			return repository.listarClientes();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao listar cliente.", e);
		}
	}

	public Cliente buscarClientePorId(Integer idCliente) throws RuntimeException, BusinessException {
		try {
			if (!repository.existeCliente(idCliente)) {
				throw new BusinessException("Cliente não encontrado");
			}

			return repository.listarClienteId(idCliente);
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao listar cliente.", e);
		}
	}

	public void atualizarCliente(Cliente cliente) throws RuntimeException, BusinessException {
		try {
			if (!repository.existeCliente(cliente.getId())) {
				throw new BusinessException("Cliente não encontrado");
			}

			if (cliente.getCpf() == null || cliente.getCpf().isEmpty()) {
				throw new BusinessException("O CPF do cliente não pode ficar em branco");
			}

			repository.atualizar(cliente);

		} catch (SQLException e) {
			throw new RuntimeException("Erro ao atualizar cliente.", e);
		}
	}

	public boolean existeCliente(Integer idCliente) throws RuntimeException {
		try {
			return repository.existeCliente(idCliente);
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao verificar o cliente.", e);
		}
	}

	public boolean existeLocacaoCliente(Integer idCliente) throws RuntimeException {
		try {
			return repository.existeLocacao(idCliente);
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao verificar se existe Locação do Cliente", e);
		}

	}

}
