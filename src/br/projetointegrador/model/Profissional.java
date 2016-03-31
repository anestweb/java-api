package br.projetointegrador.model;

public class Profissional {
	private Integer id;
	private String email;
	private String senha;
	private String nome;
	
	public Profissional(){
		id = 0;
		email = null;
		senha = null;
		nome = null;
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setNome(String nome){
		this.nome = nome;
	}
	
	public String getNome(){
		return nome;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public String getEmail(){
		return email;
	}
	
	public void setSenha(String senha){
		this.senha = senha;
	}
	
	public String getSenha(){
		return senha;
	}
}
