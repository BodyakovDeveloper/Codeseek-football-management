package com.codeseek.footballmanagement.dao;

import com.codeseek.footballmanagement.dao.interfaces.TeamDao;
import com.codeseek.footballmanagement.model.Team;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class HibernateTeamDao implements TeamDao {
    private final GenericDao genericDao;

    public void create(Team team) {
        genericDao.genericOperation(session -> session.save(team));
    }

    public void update(Team team) {
        genericDao.genericOperation(session -> session.update(team));
    }

    public void remove(Team team) {
        genericDao.genericOperation(session -> session.remove(team));
    }

    @Override
    public Optional<Team> findById(Long id) {
        return genericDao.genericRead(session -> {
            Query query = session.createQuery("FROM Team WHERE id=:id");
            query.setParameter("id", id);
            return Optional.ofNullable((Team) query.uniqueResult());
        });
    }

    @SuppressWarnings("unchecked")
    public List<Team> findAllTeams() {
        return genericDao.genericRead(session -> (List<Team>) session.createQuery("FROM Team").list());
    }
}
