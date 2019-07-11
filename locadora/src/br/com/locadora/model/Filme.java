package br.com.locadora.model;

import java.math.BigDecimal;
import java.util.Date;

public class Filme {

	private String classificacao;

	private Date data;

	private int id;

	private String nome;

	private int quantidade;

	private BigDecimal valor;

	public Filme() {
	}

	public String getClassificacao() {
		return classificacao;
	}

	public Date getData() {
		return data;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((classificacao == null) ? 0 : classificacao.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + id;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + quantidade;
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Filme other = (Filme) obj;
		if (classificacao == null) {
			if (other.classificacao != null)
				return false;
		} else if (!classificacao.equals(other.classificacao))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (quantidade != other.quantidade)
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}

	public Filme(String classificacao, Date data, int id, String nome, int quantidade, BigDecimal valor) {
		super();
		this.classificacao = classificacao;
		this.data = data;
		this.id = id;
		this.nome = nome;
		this.quantidade = quantidade;
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Filme [classificacao=" + classificacao + ", data=" + data + ", id=" + id + ", nome=" + nome
				+ ", quantidade=" + quantidade + ", valor=" + valor + "]";
	}

}
