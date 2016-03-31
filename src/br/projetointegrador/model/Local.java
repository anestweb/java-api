package br.projetointegrador.model;

public class Local {
	private Integer id;
	private String nomeCurto;
	private String nomeLongo;
	private String cnpj;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNomeCurto() {
		return nomeCurto;
	}
	public void setNomeCurto(String nomeCurto) {
		this.nomeCurto = nomeCurto;
	}
	public String getNomeLongo() {
		return nomeLongo;
	}
	public void setNomeLongo(String nomeLongo) {
		this.nomeLongo = nomeLongo;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
}
