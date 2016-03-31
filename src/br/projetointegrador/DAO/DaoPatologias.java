package br.projetointegrador.DAO;

import java.sql.SQLException;
import java.util.List;

import br.projetointegrador.model.Patologias;

public interface DaoPatologias {
	
	public void save(Patologias patologias) throws SQLException;
	
	public void update(Patologias patologias) throws SQLException;
	
	public void remove(Patologias patologias) throws SQLException;
	
	public void remove(Integer id) throws SQLException;
	
	public Patologias  get(int id) throws SQLException;
	
	public List<Patologias> list() throws SQLException;
}
