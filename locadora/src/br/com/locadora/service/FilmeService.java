package br.com.locadora.service;

import java.sql.SQLException;
import java.util.List;

import br.com.locadora.exception.BusinessException;
import br.com.locadora.exception.RuntimeException;
import br.com.locadora.model.Filme;
import br.com.locadora.repository.FilmeRepository;

public class FilmeService {

	FilmeRepository repository = new FilmeRepository();

	public void cadastrarFilme(Filme filme) throws RuntimeException, BusinessException {
		try {
			if (filme.getNome() == null || filme.getNome().isEmpty()) {
				throw new BusinessException("O Nome do filme não pode ficar em branco");
			}
			
			repository.cadastrarFilme(filme);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao cadastrar o filme.", e);
		
		}
	}

	public boolean existeFilmeLocado(Integer idFilme) throws SQLException {
		
		return repository.existeFilmeLocado(idFilme);
	}
	
	public void deletarFilme(Integer idFilme) throws RuntimeException, BusinessException {
		try {
			if (! existeFilme(idFilme)) {
				throw new BusinessException("Filme selecionado não existe.");
			}
			
			if (existeFilmeLocado(idFilme)) {
				throw new BusinessException("O Filme não pode ser apagado porque tem Locações");
			}
			
			
			repository.deletarFilme(idFilme);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Filme listarFilmeId(Integer idFilme) throws RuntimeException{
		try {
			return repository.listarFilmeId(idFilme);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao listar o filme ", e);
		}
	}

	public void atualizarFilme(Filme filme) throws RuntimeException{
		try {
			repository.atualizarFilme(filme);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Filme> listarFilmesDisponiveis() throws RuntimeException{
		try {
			return repository.listarFilmesDisponiveis();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("erro ao listar o filme", e);
		}

	}

	public List<Filme> listarFilmesLocados() throws RuntimeException {
		try {
			return repository.listarFilmesLocados();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException ("Erro ao listar o Filme", e);
		}
	}

	public List<Filme> listarFilmes() throws RuntimeException {
		try {
			return repository.listarFilmes();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao listar o filme", e);
		}
	}

	public boolean existeFilme(Integer idFilme) throws RuntimeException{
		try {
			return repository.existeFilme(idFilme);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Não consegui encontrar o filme",e);
		}
	}
	
	public int totalFilmesLocados(Integer idFilme) throws RuntimeException {
		try {
			return repository.totalFilmesLocados(idFilme);
		} catch (SQLException e) {
			throw new RuntimeException("Não consegui encontrar locação para o filme ",e);
		}
	}
}
