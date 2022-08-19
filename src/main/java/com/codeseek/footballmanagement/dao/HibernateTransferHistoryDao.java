package com.codeseek.footballmanagement.dao;

import com.codeseek.footballmanagement.dao.interfaces.TransferHistoryDao;
import com.codeseek.footballmanagement.model.TransferHistory;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class HibernateTransferHistoryDao implements TransferHistoryDao {
    private final GenericDao genericDao;

    @Override
    public void create(TransferHistory transferHistory) {
        genericDao.genericOperation(session -> session.save(transferHistory));
    }

    @Override
    public void update(TransferHistory transferHistory) {
        genericDao.genericOperation(session -> session.update(transferHistory));
    }

    @Override
    public void remove(TransferHistory transferHistory) {
        genericDao.genericOperation(session -> session.remove(transferHistory));
    }

    @Override
    public Optional<TransferHistory> findById(Long id) {
        return genericDao.genericRead(session -> {
            Query query = session.createQuery("from TransferHistory where id=:id");
            query.setParameter("id", id);
            return Optional.ofNullable((TransferHistory) query.uniqueResult());
        });
    }

    @SuppressWarnings("unchecked")
    public List<TransferHistory> getTransferHistory() {
        return genericDao.genericRead(session -> (List<TransferHistory>) session.createQuery("FROM TransferHistory ").list());
    }
}