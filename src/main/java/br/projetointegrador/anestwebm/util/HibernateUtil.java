package br.projetointegrador.anestwebm.util;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Jonathan Souza <jonathan.ralison@gmail.com>
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    private static final ServiceRegistry serviceRegistry;
    private static String defaultDbUrl = "jdbc:mysql://anestwebp:anestwebp@"
            + "localhost:3306/anestwebp?zeroDateTimeBehavior=convertToNull";

    static {
        try {
            Configuration cfg = new Configuration().configure("META-INF/hibernate.cfg.xml");
            cfg = HibernateUtil.setConnectionProps(cfg);

            serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(cfg.getProperties()).build();

            sessionFactory = cfg.buildSessionFactory(serviceRegistry);
        } catch (Exception ex) {
            String msg = "Falhou ao criar a SessionFactory: " + ex.getMessage() + ".";
            Logger.getLogger(HibernateUtil.class.getName()).log(Level.SEVERE, msg, ex);
            throw new RuntimeException(msg);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private static Configuration setConnectionProps(Configuration cfg) {
        String envDbUrl = System.getenv("DATABASE_URL");
        envDbUrl = envDbUrl == null || envDbUrl.isEmpty() ? defaultDbUrl : envDbUrl;
        Matcher matcher = Pattern.compile("^(.+)://([^:]+):([^@]+)@(.+)$").matcher(envDbUrl);
        if (matcher.matches()) {
            String url = matcher.group(1) + "://" + matcher.group(4);
            cfg.setProperty("hibernate.connection.url", url);
            cfg.setProperty("hibernate.connection.username", matcher.group(2));
            cfg.setProperty("hibernate.connection.password", matcher.group(3));
        }
        return cfg;
    }
}
