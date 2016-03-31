package br.projetointegrador.DAO;

import java.sql.SQLException;
import java.util.List;

import br.projetointegrador.model.Local;

public interface DaoLocal {
	
	public void save(Local local) throws SQLException;
	
	public void update(Local local) throws SQLException;
	
	public void remove(Local local) throws SQLException;
	
	public void remove(Integer id) throws SQLException;
	
	public Local get(int id) throws SQLException;
	
	public List<Local> list() throws SQLException;	
}
