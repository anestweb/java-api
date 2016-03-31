package br.projetointegrador.DAO;

import java.sql.SQLException;
import java.util.List;

import br.projetointegrador.model.Profissional;

public interface DaoUsuario {
	
	public void save(Profissional usuario) throws SQLException;
	
	public void update(Profissional usuario) throws SQLException;
	
	public void remove(Profissional usuario) throws SQLException;
	
	public void remove(Integer id) throws SQLException;
	
	public Profissional get(int id) throws SQLException;
	
	public List<Profissional> list() throws SQLException;
	
	public boolean autenticaUsuario(String email, String senha) throws SQLException;
}
