package br.projetointegrador.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.projetointegrador.model.Patologias;

public class DaoPatologiasImpl extends GenericDao implements DaoPatologias {

	@Override
	public void save(Patologias patologias) throws SQLException {
		String sql = "INSERT INTO patologia (descricao) VALUES (?)";	
		PreparedStatement st = null;
		try {
			openConn();
			st = conn.prepareStatement(sql);
			st.setString(1, patologias.getDescricao());
			st.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(st != null)
				st.close();
			closeConn();
		}
		
	}

	@Override
	public void update(Patologias patologias) throws SQLException {
		String sql = "UPDATE patologia SET descricao=? WHERE id=?";
		PreparedStatement st =null;
		try {
			openConn();
			st = conn.prepareStatement(sql);
			st.setString(1, patologias.getDescricao());
			st.setInt(2, patologias.getId());
			st.execute();
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			if(st != null)
				st.close();
			closeConn();
		}
		
	}

	@Override
	public void remove(Patologias patologias) throws SQLException {
		remove(patologias.getId());
		
	}

	@Override
	public void remove(Integer id) throws SQLException {
		String  sql = "DELETE from patologia WHERE id = ?";
		PreparedStatement st = null;
		try {
			openConn();
			st = conn.prepareStatement(sql);
			st.setInt(1, id);
			st.execute();
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			if(st != null)
				st.close();
			closeConn();
		}
		
	}

	@Override
	public Patologias get(int id) throws SQLException {
		String sql = "SELECT * FROM patologia WHERE id=?";
		PreparedStatement st = null;
		try {
			openConn();
			st = conn.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet resultSet = st.executeQuery();
			
			if( resultSet.next() ){
				int idRs = resultSet.getInt("id");
				String descricao = resultSet.getString("descricao");
				
				Patologias patologias = new Patologias();
				patologias.setId(idRs);
				patologias.setDescricao(descricao);
			
				return patologias;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(st != null)
				st.close();
			closeConn();
		}
		return null;
	}

	@Override
	public List<Patologias> list() throws SQLException {
		String sql = "SELECT * FROM patologia";
		PreparedStatement st = null;
		
		try {
			openConn();
			st = conn.prepareStatement(sql);
			ResultSet resultSet = st.executeQuery();
			List<Patologias> patologias = new ArrayList<Patologias>();
			
			while( resultSet.next() ){
				
				int id = resultSet.getInt("id");
				String descricao = resultSet.getString("descricao");
				
				Patologias p = new Patologias();
				p.setId(id);
				p.setDescricao(descricao);
				
				patologias.add(p);
			}
			
			return patologias;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(st != null)
			st.close();
			conn.close();
		}
		return list();
	}

}
