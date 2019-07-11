package br.com.locadora.model;

public class ItemLocacao {

	private Integer id;

	private Integer idFilme;

	private Integer idLocacao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdFilme() {
		return idFilme;
	}

	public void setIdFilme(Integer idFilme) {
		this.idFilme = idFilme;
	}

	public Integer getIdLocacao() {
		return idLocacao;
	}

	public void setIdLocacao(Integer idLocacao) {
		this.idLocacao = idLocacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idFilme == null) ? 0 : idFilme.hashCode());
		result = prime * result + ((idLocacao == null) ? 0 : idLocacao.hashCode());
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
		ItemLocacao other = (ItemLocacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idFilme == null) {
			if (other.idFilme != null)
				return false;
		} else if (!idFilme.equals(other.idFilme))
			return false;
		if (idLocacao == null) {
			if (other.idLocacao != null)
				return false;
		} else if (!idLocacao.equals(other.idLocacao))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ItemLocacao [id=" + id + ", idFilme=" + idFilme + ", idLocacao=" + idLocacao + "]";
	}

	public ItemLocacao() {

	}

	public ItemLocacao(Integer id, Integer idFilme, Integer idLocacao) {
		super();
		this.id = id;
		this.idFilme = idFilme;
		this.idLocacao = idLocacao;
	}

}
