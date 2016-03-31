package br.projetointegrador.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.projetointegrador.model.Local;

public class DaoLocalImpl extends GenericDao implements DaoLocal{

	@Override
	public void save(Local local) throws SQLException {
		String sql = "INSERT INTO local (nome_curto, cnpj, nome_longo) VALUES (?,?,?)";	
		PreparedStatement st = null;
		try {
			openConn();
			st = conn.prepareStatement(sql);
			st.setString(1, local.getNomeCurto());
			st.setString(2, local.getCnpj());
			st.setString(3, local.getNomeLongo());
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
	public void update(Local local) throws SQLException {
		String sql = "UPDATE local SET nome_curto=?, cnpj=?, nome_longo=? WHERE id=?";
		PreparedStatement st =null;
		try {
			openConn();
			st = conn.prepareStatement(sql);
			st.setString(1, local.getNomeCurto());
			st.setString(2, local.getCnpj());
			st.setString(3, local.getNomeLongo());
			st.setInt(8, local.getId());
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
	public void remove(Local local) throws SQLException {
		remove(local.getId());
		
	}

	@Override
	public void remove(Integer id) throws SQLException {
		String  sql = "DELETE from local WHERE id = ?";
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
	public Local get(int id) throws SQLException {
		String sql = "SELECT * FROM local WHERE id=?";
		PreparedStatement st = null;
		try {
			openConn();
			st = conn.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet resultSet = st.executeQuery();
			
			if( resultSet.next() ){
				int idRs = resultSet.getInt("id");
				String nomeCurto = resultSet.getString("nome_curto");
				String cnpj = resultSet.getString("cnpj");
				String nomeLongo = resultSet.getString("nome_longo");
				
				Local local = new Local();
				local.setId(idRs);
				local.setCnpj(cnpj);
				local.setNomeCurto(nomeCurto);
				local.setNomeLongo(nomeLongo);
			
				return local;
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
	public List<Local> list() throws SQLException {
		String sql = "SELECT * FROM local";
		PreparedStatement st = null;
		
		try {
			openConn();
			st = conn.prepareStatement(sql);
			ResultSet resultSet = st.executeQuery();
			List<Local> local = new ArrayList<Local>();
			
			while( resultSet.next() ){
				
				int id = resultSet.getInt("id");
				String nomeCurto = resultSet.getString("nomeCurto");
				String cnpj = resultSet.getString("cnpj");
				String nomeLongo = resultSet.getString("nomeLongo");
				
				Local l = new Local();
				l.setId(id);
				l.setCnpj(cnpj);
				l.setNomeCurto(nomeCurto);
				l.setNomeLongo(nomeLongo);
				
				local.add(l);
			}
			
			return local;
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
