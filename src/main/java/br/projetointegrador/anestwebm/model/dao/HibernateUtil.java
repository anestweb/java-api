package br.projetointegrador.anestwebm.model.dao;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Jonathan Souza <jonathan.ralison@gmail.com>
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;
    
    static {
        try {
            sessionFactory = new Configuration()
                    .configure("META-INF/hibernate.cfg.xml")
                    .buildSessionFactory();
        } catch (Exception ex) {
            String msg = "Falhou ao criar a SessionFactory: " + ex.getMessage() + ".";
            Logger.getLogger(HibernateUtil.class.getName()).log(Level.SEVERE, msg, ex);
            throw new RuntimeException(msg);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
