package br.com.locadora.model;

import java.util.Date;

public class Locacao {

	private Integer id;

	private Date dataLocacao;

	private Date dataDevolucao;

	private Integer idCliente;

	private String finalizado;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataLocacao() {
		return dataLocacao;
	}

	public void setDataLocacao(Date dataLocacao) {
		this.dataLocacao = dataLocacao;
	}

	public Date getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getFinalizado() {
		return finalizado;
	}

	public void setFinalizado(String finalizado) {
		this.finalizado = finalizado;
	}

	@Override
	public String toString() {
		return "Locacao [id=" + id + ", dataLocacao=" + dataLocacao + ", dataDevolucao=" + dataDevolucao
				+ ", idCliente=" + idCliente + ", finalizado=" + finalizado + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataDevolucao == null) ? 0 : dataDevolucao.hashCode());
		result = prime * result + ((dataLocacao == null) ? 0 : dataLocacao.hashCode());
		result = prime * result + ((finalizado == null) ? 0 : finalizado.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idCliente == null) ? 0 : idCliente.hashCode());
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
		Locacao other = (Locacao) obj;
		if (dataDevolucao == null) {
			if (other.dataDevolucao != null)
				return false;
		} else if (!dataDevolucao.equals(other.dataDevolucao))
			return false;
		if (dataLocacao == null) {
			if (other.dataLocacao != null)
				return false;
		} else if (!dataLocacao.equals(other.dataLocacao))
			return false;
		if (finalizado == null) {
			if (other.finalizado != null)
				return false;
		} else if (!finalizado.equals(other.finalizado))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idCliente == null) {
			if (other.idCliente != null)
				return false;
		} else if (!idCliente.equals(other.idCliente))
			return false;
		return true;
	}

	public Locacao(Integer id, Date dataLocacao, Date dataDevolucao, Integer idCliente, String finalizado) {
		super();
		this.id = id;
		this.dataLocacao = dataLocacao;
		this.dataDevolucao = dataDevolucao;
		this.idCliente = idCliente;
		this.finalizado = finalizado;
	}

	public Locacao() {
	}

}
