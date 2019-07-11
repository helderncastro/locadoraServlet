package br.com.locadora.service;

import java.util.List;

import br.com.locadora.model.ItemLocacao;
import br.com.locadora.repository.ItemLocacaoRepository;

public class ItemLocacaoService {

	ItemLocacaoRepository repository = new ItemLocacaoRepository();
	
	public List<ItemLocacao> listarItLocacoes() throws Exception {
		return repository.listarItLocacoes();
	}
	
	public void cadastrarItLocacao(ItemLocacao itLocacao) throws Exception {
		repository.cadastrarItLocacao(itLocacao);
	}
	
	public void deletarItLocacao(Integer idItLocacao) throws Exception {
		
		repository.deletarItLocacao(idItLocacao);
	}
	
	public ItemLocacao listarItLocacaoId(Integer idItLocacao) throws Exception {
		return repository.listarItLocacaoId(idItLocacao);
	}
	
	public void atualizarItLocacao(ItemLocacao itemLocacao) throws Exception {
		repository.atualizarItLocacao(itemLocacao);
	}
	
	public boolean existeItLocacao(Integer idItLocacao)throws Exception {
		return repository.existeItLocacao(idItLocacao);
	}
	
	
}
