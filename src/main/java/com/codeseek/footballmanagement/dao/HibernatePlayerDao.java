package com.codeseek.footballmanagement.dao;

import com.codeseek.footballmanagement.dao.interfaces.PlayerDao;
import com.codeseek.footballmanagement.model.Player;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class HibernatePlayerDao implements PlayerDao {
    private final GenericDao genericDao;

    public void create(Player player) {
        genericDao.genericOperation(session -> session.save(player));
    }

    public void update(Player player) {
        genericDao.genericOperation(session -> session.update(player));
    }

    public void remove(Player player) {
        genericDao.genericOperation(session -> session.delete(player));
    }

    @Override
    public Optional<Player> findById(Long id) {
        return genericDao.genericRead(session -> {
            Query query = session.createQuery("FROM Player WHERE id=:id");
            query.setParameter("id", id);
            return Optional.ofNullable((Player) query.uniqueResult());
        });
    }

    @SuppressWarnings("unchecked")
    public List<Player> findAllPlayers() {
        return (List<Player>) genericDao.genericRead(session -> session.createQuery("FROM Player ").list());
    }
}