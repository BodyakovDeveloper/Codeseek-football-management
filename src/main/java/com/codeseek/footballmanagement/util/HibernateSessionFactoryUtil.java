package com.codeseek.footballmanagement.util;

import com.codeseek.footballmanagement.model.Player;
import com.codeseek.footballmanagement.model.Team;
import com.codeseek.footballmanagement.model.TransferHistory;
import lombok.extern.log4j.Log4j2;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

@Log4j2
public class HibernateSessionFactoryUtil {

    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {

    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Team.class);
                configuration.addAnnotatedClass(Player.class);
                configuration.addAnnotatedClass(TransferHistory.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        return sessionFactory;
    }
}