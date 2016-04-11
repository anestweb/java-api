package br.projetointegrador.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.projetointegrador.model.Pacientes;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoPacientesImpl extends GenericDao implements DaoPacientes {

    public void save(Pacientes pacientes) throws SQLException {
        String sql = "INSERT INTO pacientes (cpf, rg, data_nascimento, sexo, nome_completo, id_profissional, id_local) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement st = null;
        try {
            openConn();
            st = conn.prepareStatement(sql);
            st.setString(1, pacientes.getCpf());
            st.setString(2, pacientes.getRg());
            st.setString(3, pacientes.getDataNascimento());
            st.setString(4, pacientes.getSexo());
            st.setString(5, pacientes.getNomeCompleto());
            st.setInt(6, pacientes.getProfissional());
            st.setInt(7, pacientes.getLocal());
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
                st.close();
            }
            closeConn();
        }
    }

    public void update(Pacientes pacientes) throws SQLException {
        String sql = "UPDATE pacientes SET cpf=?, rg=?, data_nascimento=?, sexo=?, nome_completo=?, id_profissional=?, id_local=? WHERE id=?";
        PreparedStatement st = null;
        try {
            openConn();
            st = conn.prepareStatement(sql);
            st.setString(1, pacientes.getCpf());
            st.setString(2, pacientes.getRg());
            st.setString(3, pacientes.getDataNascimento());
            st.setString(4, pacientes.getSexo());
            st.setString(5, pacientes.getNomeCompleto());
            st.setInt(6, pacientes.getProfissional());
            st.setInt(7, pacientes.getLocal());
            st.setInt(8, pacientes.getId());
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
                st.close();
            }
            closeConn();
        }
    }

    public void remove(Pacientes pacientes) throws SQLException {
        remove(pacientes.getId());
    }

    public void remove(Integer id) throws SQLException {
        String sql = "DELETE from pacientes WHERE id = ?";
        PreparedStatement st = null;
        try {
            openConn();
            st = conn.prepareStatement(sql);
            st.setInt(1, id);
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
                st.close();
            }
            closeConn();
        }
    }

    public Pacientes get(int id) throws SQLException {
        String sql = "SELECT * FROM pacientes WHERE id=?";
        PreparedStatement st = null;
        try {
            openConn();
            st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet resultSet = st.executeQuery();

            if (resultSet.next()) {
                int idRs = resultSet.getInt("id");
                String nomeCompleto = resultSet.getString("nome_completo");
                String cpf = resultSet.getString("cpf");
                String rg = resultSet.getString("rg");
                String dataNascimento = resultSet.getString("data_nascimento");
                String sexo = resultSet.getString("sexo");
                Integer prof = resultSet.getInt("id_profissional");
                Integer local = resultSet.getInt("id_local");

                Pacientes pacientes = new Pacientes();
                pacientes.setId(idRs);
                pacientes.setCpf(cpf);
                pacientes.setNomeCompleto(nomeCompleto);
                pacientes.setRg(rg);
                pacientes.SetDataNascimento(dataNascimento);
                pacientes.SetSexo(sexo);
                pacientes.setProfissional(prof);
                pacientes.setLocal(local);

                return pacientes;
            }

        } catch (SQLException e) {
            Logger.getLogger(DaoPacientesImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (st != null) {
                st.close();
            }
            closeConn();
        }
        return null;
    }

    @Override
    public List<Pacientes> list() throws SQLException {
        String sql = "SELECT * FROM pacientes ORDER BY nome_completo";
        PreparedStatement st = null;
        List<Pacientes> pacientes = new ArrayList<>();

        try {
            openConn();
            st = conn.prepareStatement(sql);
            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nomeCompleto = resultSet.getString("nome_completo");
                String cpf = resultSet.getString("cpf");
                String rg = resultSet.getString("rg");
                String dataNascimento = resultSet.getString("data_nascimento");
                String sexo = resultSet.getString("sexo");
                Integer prof = resultSet.getInt("id_profissional");
                Integer local = resultSet.getInt("id_local");

                Pacientes p = new Pacientes();
                p.setId(id);
                p.setCpf(cpf);
                p.setNomeCompleto(nomeCompleto);
                p.setRg(rg);
                p.SetDataNascimento(dataNascimento);
                p.SetSexo(sexo);
                p.setProfissional(prof);
                p.setLocal(local);

                pacientes.add(p);
            }
        } catch (SQLException e) {
            Logger.getLogger(DaoPacientesImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (st != null) {
                st.close();
            }
            closeConn();
        }

        return pacientes;
    }
}
