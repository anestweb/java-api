package br.projetointegrador.DAO;

import java.sql.SQLException;
import java.util.List;

import br.projetointegrador.model.Pacientes;

public interface DaoPacientes {
 
	public void save(Pacientes pacientes) throws SQLException;
	
	public void update(Pacientes pacientes) throws SQLException;
	
	public void remove(Pacientes pacientes) throws SQLException;
	
	public void remove(Integer id) throws SQLException;
	
	public Pacientes get(int id) throws SQLException;
	
	public List<Pacientes> list() throws SQLException;
}
