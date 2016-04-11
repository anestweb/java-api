package br.projetointegrador.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GenericDao {

    protected Connection conn;

    protected void openConn() {
        try {
            if (conn == null || (conn != null && conn.isClosed())) {
                conn = new ConnectionFactory().getConnection();
            }
        } catch (SQLException e) {
            Logger.getLogger(GenericDao.class.getName()).log(Level.SEVERE, 
                    "Não foi possível estabelecer uma conexão com o banco de dados.", e);
        }
    }

    protected void closeConn() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            Logger.getLogger(GenericDao.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
