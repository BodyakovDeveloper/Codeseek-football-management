package com.codeseek.footballmanagement.dao;

import com.codeseek.footballmanagement.util.HibernateSessionFactoryUtil;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;
import java.util.function.Function;

@Component
@Log4j2
public class GenericDao {

    public void genericOperation(Consumer<Session> consumer) {

        Transaction transaction = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            consumer.accept(session);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public <T> T genericRead(Function<Session, T> function) {

        Transaction transaction = null;
        T dataFromDatabase;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            dataFromDatabase = function.apply(session);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }

        return dataFromDatabase;
    }
}