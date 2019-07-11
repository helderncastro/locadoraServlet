	package br.com.locadora.conexao;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.locadora.controller.ClienteController;
import br.com.locadora.controller.FilmeController;
import br.com.locadora.controller.ItemLocacaoController;
import br.com.locadora.controller.LocacaoController;
import br.com.locadora.model.Cliente;
import br.com.locadora.model.Filme;
import br.com.locadora.model.ItemLocacao;
import br.com.locadora.model.Locacao;

public class Execute {

	public static void main(String[] args) throws Exception {
		// inserirFilme();
		 listarFilmesDisponiveis();
		//deletarFilme();

		// inserirCliente();
		//listarClientes();
		// listarClientes();
		// deletarCliente();

		// inserirLocacao();
		// deletarLocacao();
		// atualizarLocacao();
		// existeLocacao(23);
		// listarLocacao();
	
		// inserirItemLocacao();

		System.out.println("executei ");

	}

	public static void inserirItemLocacao() throws Exception {
		ItemLocacao it = new ItemLocacao();
		it.setId(1);
		it.setIdLocacao(23);
		
		new ItemLocacaoController().cadastrarItLocacao(it);
	}
	
	public static void listarLocacao() throws Exception {
		List<Locacao> locacoes = new LocacaoController().listarLocacoes();

		for (Locacao locacao : locacoes) {

			System.out.println(locacao);
		}
	}
	
	public static void existeLocacao(Integer idLocacao) throws Exception {
		Boolean existe;
		existe = new LocacaoController().existeLocacao(idLocacao);

		System.out.println(existe);

	}

	public static void atualizarLocacao() throws Exception {
		Locacao locacao = new Locacao();

		Date dataLocacao = new Date();
		Calendar dataDevolucao = Calendar.getInstance();
		dataDevolucao.setTime(new Date());
		dataLocacao = new Date();
		dataDevolucao.add(dataDevolucao.DAY_OF_MONTH, 5);

		locacao.setDataDevolucao(dataDevolucao.getTime());
		locacao.setDataLocacao(dataLocacao);
		locacao.setFinalizado("N");
		locacao.setIdCliente(2);
		locacao.setId(21);

		new LocacaoController().atualizarLocacao(locacao);

	}

	public static void deletarLocacao() throws Exception {
		new LocacaoController().deletarLocacao(1);
	}

	public static void inserirLocacao() throws Exception {

		Locacao locacao = new Locacao();

		Date dataLocacao = new Date();
		Calendar dataDevolucao = Calendar.getInstance();
		dataDevolucao.setTime(new Date());
		dataLocacao = new Date();
		dataDevolucao.add(dataDevolucao.DAY_OF_MONTH, 3);

		locacao.setDataDevolucao(dataDevolucao.getTime());
		locacao.setDataLocacao(dataLocacao);
		locacao.setFinalizado("N");
		locacao.setIdCliente(1);

		new LocacaoController().cadastrarLocacao(locacao);

	}

	public static void existeFilme(Integer idFilme) throws Exception {
		boolean existe = new FilmeController().existeFilme(2);
		System.out.println(existe);
	}

	public static void listarClientes() throws Exception {
		List<Cliente> clientes = new ClienteController().listarClientes();

		for (Cliente cliente : clientes) {

			System.out.println(cliente);
		}
	}

	public static void listarFilmesDisponiveis() throws Exception {
	
		List<Filme> filmes = new FilmeController().listarFilmesLocados();

		for (Filme filme : filmes) {
			System.out.println(filme.getNome());
		}
	}

	public static void deletarCliente() throws Exception {
		new ClienteController().deletarCliente(1);
	}

	public static void alterarCliente() throws Exception {

		Cliente cliente = new Cliente();
		cliente.setId(2);
		cliente.setNome("Helder Nogueira");
		cliente.setCpf("959.479.306-68");
		cliente.setTipoLogradouro("Av");
		cliente.setLogradouro("dos Ferreiras");
		cliente.setNumero(475);
		cliente.setComplemento("Casa 271");
		cliente.setBairro("Jardim Califórnia");
		cliente.setCidade("Uberlândia");
		cliente.setCep("39.406-136");
		cliente.setUf("MG");
		cliente.setTelefone("(34)99300-2091");
		cliente.setEmail("hnc2709@yahoo.com.br");

		new ClienteController().atualizarCliente(cliente);

	}

	public static void inserirCliente() throws Exception {

		Cliente cliente = new Cliente();
		cliente.setNome("Helder Nogueira");
		cliente.setCpf("959.479.306-68");
		cliente.setTipoLogradouro("Av");
		cliente.setLogradouro("dos Ferreiras");
		cliente.setNumero(475);
		cliente.setComplemento("Casa 271");
		cliente.setBairro("Jardim Califórnia");
		cliente.setCidade("Uberlândia");
		cliente.setCep("39.406-136");
		cliente.setUf("MG");
		cliente.setTelefone("(34)99300-2091");
		cliente.setEmail("hnc2709@yahoo.com.br");

		new ClienteController().cadastrarCliente(cliente);
	}

	public static void deletarFilme() throws Exception {
		new FilmeController().deletarFilme(1);
	}
	
	public static void alterarfilme() throws Exception {
		Filme filme = new Filme();
		Date dataDoFilme = new Date();

		filme.setId(21);
		filme.setNome("Jamaica abaixo de Zero");
		filme.setData(dataDoFilme);
		filme.setQuantidade(5);
		filme.setClassificacao("Comédia");
		filme.setValor(new BigDecimal("15.00"));

		new FilmeController().atualizarFilme(filme);
		;
	}

	public static void inserirFilme() throws Exception {

		Date dataDoFilme = new Date();
		Filme filme = new Filme();

		filme.setValor(new BigDecimal("20.00"));
		filme.setNome("Um morto muito louco");
		filme.setData(dataDoFilme);
		new FilmeController().cadastrarFilme(filme);
	}

}
