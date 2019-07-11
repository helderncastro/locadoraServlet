package br.com.locadora.controller;

import java.util.List;

import br.com.locadora.exception.RuntimeException;
import br.com.locadora.model.Filme;
import br.com.locadora.service.FilmeService;

public class FilmeController {

	FilmeService service = new FilmeService();

	public void cadastrarFilme(Filme filme) throws Exception {
		service.cadastrarFilme(filme);
	}

	public void deletarFilme(Integer idFilme) throws Exception {
		service.deletarFilme(idFilme);
	}

	public boolean existeFilmeLocado(Integer idFilme) throws Exception {
		return service.existeFilmeLocado(idFilme);
	}
	
	public Filme listarFilmeId(Integer idFilme) throws Exception {
		return service.listarFilmeId(idFilme);
	}

	public void atualizarFilme(Filme filme) throws Exception {
		service.atualizarFilme(filme);
	}

	public List<Filme> listarFilmesDisponiveis() throws Exception {
		return service.listarFilmesDisponiveis();

	}

	public boolean existeFilme(Integer idFilme) throws Exception {
		return service.existeFilme(idFilme);
	}
	
	public List<Filme> listarFilmesLocados() throws Exception {
		return service.listarFilmesLocados();

	}

	public List<Filme> listarFilmes() throws Exception {
		return service.listarFilmes();
	}

	public int totalFilmesLocados(Integer idFilme) throws RuntimeException {
		return service.totalFilmesLocados(idFilme);
	}
	
}
