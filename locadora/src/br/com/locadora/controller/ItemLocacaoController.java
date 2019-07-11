package br.com.locadora.controller;

import java.util.List;

import br.com.locadora.model.ItemLocacao;
import br.com.locadora.service.ItemLocacaoService;

public class ItemLocacaoController {
	
	ItemLocacaoService service = new ItemLocacaoService();
	
	public List<ItemLocacao> listarItLocacoes() throws Exception {
		return service.listarItLocacoes();
	}
	
	public void cadastrarItLocacao(ItemLocacao itLocacao) throws Exception {
		service.cadastrarItLocacao(itLocacao);
	}
	
	public void deletarItLocacao(Integer idItLocacao) throws Exception {
		service.deletarItLocacao(idItLocacao);
	}
	
	public ItemLocacao listarItLocacaoId(Integer idItLocacao) throws Exception {
		return service.listarItLocacaoId(idItLocacao);
	}
	
	public void atualizarItLocacao(ItemLocacao itemLocacao) throws Exception {
		service.atualizarItLocacao(itemLocacao);
	}
	
	public boolean existeItLocacao(Integer idItLocacao)throws Exception {
		return service.existeItLocacao(idItLocacao);
	}

}
