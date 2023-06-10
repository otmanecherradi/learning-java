package me.otmane.demo.utils;

import me.otmane.demo.models.Nationality;
import me.otmane.demo.models.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.List;
import java.util.Properties;

public class HibernateUtils {
    private static SessionFactory sessionFactory = null;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Properties props = new Properties();
                props.put(Environment.DRIVER, "org.postgresql.Driver");
                props.put(Environment.URL, "jdbc:postgresql://localhost:5432/school__hibernate_spring");
                props.put(Environment.USER, "docker");
                props.put(Environment.PASS, "docker");

                props.put(Environment.SHOW_SQL, true);
                props.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                props.put(Environment.HBM2DDL_AUTO, "update");

                final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                        .applySettings(props)
                        .build();

                Configuration cfg = new Configuration();
                cfg.addProperties(props);

                List<Class<?>> classList = List.of(User.class, Nationality.class);

                for (Class<?> klass : classList)
                    cfg.addAnnotatedClass(klass);

                sessionFactory = cfg.buildSessionFactory(registry);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
