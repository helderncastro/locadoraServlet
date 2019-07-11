package br.com.locadora.service;

import java.sql.SQLException;
import java.util.List;

import br.com.locadora.model.Locacao;
import br.com.locadora.repository.LocacaoRepository;

public class LocacaoService {

	LocacaoRepository repository = new LocacaoRepository();

	public List<Locacao> listarLocacoes() throws Exception {

		return repository.listarLocacoes();

	}

	public void cadastrarLocacao(Locacao locacao) throws Exception {

		repository.cadastrarLocacao(locacao);
	}

	public void deletarLocacao(Integer idLocacao) throws Exception {

		repository.deletarLocacao(idLocacao);
	}

	public boolean existePeloMenosUmFilme(Integer idLocacao) throws SQLException {
		return repository.existePeloMenosUmFilme(idLocacao);
	}
	
	public Locacao listarLocacaoId(Integer idLocacao) throws Exception {
		return repository.listarLocacaoId(idLocacao);
	}

	public boolean existeLocacao(Integer idLocacao) throws Exception {
		return repository.existeLocacao(idLocacao);
	}

	public void atualizarLocacao(Locacao locacao) throws Exception {
		repository.atualizarLocacao(locacao);
	}

}
