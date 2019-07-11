package br.com.locadora.controller;

import java.sql.SQLException;
import java.util.List;

import br.com.locadora.model.Locacao;
import br.com.locadora.service.LocacaoService;

public class LocacaoController {

	LocacaoService service = new LocacaoService();
	
	public List<Locacao> listarLocacoes() throws Exception {

		return service.listarLocacoes();

	}

	public void cadastrarLocacao(Locacao locacao) throws Exception {

		service.cadastrarLocacao(locacao);
	}

	public void deletarLocacao(Integer idLocacao) throws Exception {
		service.deletarLocacao(idLocacao);
	}

	public boolean existePeloMenosUmFilme(Integer idLocacao) throws SQLException {
		return service.existePeloMenosUmFilme(idLocacao);
	}
	
	public Locacao listarLocacaoId(Integer idLocacao) throws Exception {
		return service.listarLocacaoId(idLocacao);
	}

	public boolean existeLocacao(Integer idLocacao) throws Exception {
		return service.existeLocacao(idLocacao);
	}

	public void atualizarLocacao(Locacao locacao) throws Exception {
		service.atualizarLocacao(locacao);
	}

	
}
