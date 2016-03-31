package br.projetointegrador.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.projetointegrador.DAO.DaoUsuario;
import br.projetointegrador.DAO.GenericDao;
import br.projetointegrador.model.Profissional;

public class DaoUsuarioImpl extends GenericDao implements DaoUsuario{
	
	public void save(Profissional usuario) throws SQLException{
		String sql = "INSERT INTO profissional (nome, email, senha) VALUES (?,?,?)";	
		PreparedStatement st = null;
		try {
			openConn();
			st = conn.prepareStatement(sql);
			st.setString(1, usuario.getNome());
			st.setString(2, usuario.getEmail());
			st.setString(3, usuario.getSenha());
			st.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(st != null)
				st.close();
			closeConn();
		}	
	}
	
	public void update(Profissional usuario) throws SQLException{
		String sql = "UPDATE profissional SET nome=?, email=?, senha=? WHERE id=?";
		PreparedStatement st =null;
		try {
			openConn();
			st = conn.prepareStatement(sql);
			st.setString(1, usuario.getNome());
			st.setString(2, usuario.getEmail());
			st.setString(3, usuario.getSenha());
			st.setInt(4, usuario.getId());
			st.execute();
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			if(st != null)
				st.close();
			closeConn();
		}
	}
	
	public void remove(Profissional usuario) throws SQLException{
		remove(usuario.getId());
	}
	
	public void remove(Integer id) throws SQLException{
		String  sql = "DELETE from profissional WHERE id = ?";
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
	
	public Profissional get(int id) throws SQLException{
		String sql = "SELECT * FROM profissional WHERE id=?";
		PreparedStatement st = null;
		try {
			openConn();
			st = conn.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet resultSet = st.executeQuery();
			
			if( resultSet.next() ){
				int idRs = resultSet.getInt("id");
				String nome = resultSet.getString("nome");
				String email = resultSet.getString("email");
				String senha = resultSet.getString("senha");
				
				Profissional usuario = new Profissional();
				usuario.setId(idRs);
				usuario.setNome(nome);
				usuario.setEmail(email);
				usuario.setSenha(senha);
				
				return usuario;
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
	
	public List<Profissional> list() throws SQLException{
		String sql = "SELECT * FROM profissional";
		PreparedStatement st = null;
		
		try {
			openConn();
			st = conn.prepareStatement(sql);
			ResultSet resultSet = st.executeQuery();
			List<Profissional> usuarios = new ArrayList<Profissional>();
			
			while( resultSet.next() ){
				
				int id = resultSet.getInt("id");
				String nome = resultSet.getString("nome");
				String email = resultSet.getString("email");
				String senha = resultSet.getString("senha");
				
				Profissional u = new Profissional();
				u.setId(id);
				u.setNome(nome);
				u.setEmail(email);
				u.setSenha(senha);
				usuarios.add(u);
			}
			
			return usuarios;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(st != null)
			st.close();
			conn.close();
		}
		return list();
	}
	
	public boolean autenticaUsuario(String email, String senha) throws SQLException{
		String sql = "SELECT email, senha FROM profissional WHERE email=?";
		PreparedStatement st = null;
		boolean aut = false;
		
		try{
			openConn();
			st = conn.prepareStatement(sql);
			st.setString(1, email);
			ResultSet resultSet = st.executeQuery();
			
			
			if( resultSet.next() ){
				if(email.equals(resultSet.getString("email")) && senha.equals(resultSet.getString("senha"))){
					aut = true;
				}
			}	
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(st != null)
				st.close();
			conn.close();
		}
		return aut;
	}
}
