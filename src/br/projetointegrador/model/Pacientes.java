package br.projetointegrador.model;

public class Pacientes {
	private Integer id;
	private String nomeCompleto;
	private String cpf;
	private String rg;
	private String sexo;
	private String dataNascimento;
	private Integer profissional;
	private Integer local;
	
	public void setNomeCompleto(String nomeCompleto){
		this.nomeCompleto = nomeCompleto;
	}
	
	public void setCpf(String cpf){
		this.cpf = cpf;
	}
	
	public void setRg(String rg){
		this.rg = rg;
	}
	
	public void SetSexo(String sexo){
		this.sexo = sexo;
	}
	
	public void SetDataNascimento(String dataNascimento){
		this.dataNascimento = dataNascimento;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNomeCompleto(){
		return nomeCompleto;
	}
	
	public String getCpf(){
		return cpf;
	}
	
	public String getRg(){
		return rg;
	}
	
	public String getSexo(){
		return sexo;
	}
	
	public String getDataNascimento(){
		return dataNascimento;
	}

	public Integer getId() {
		return id;
	}

	public Integer getProfissional() {
		return profissional;
	}

	public void setProfissional(Integer profissional) {
		this.profissional = profissional;
	}

	public Integer getLocal() {
		return local;
	}

	public void setLocal(Integer local) {
		this.local = local;
	}
}
