package com.codeseek.footballmanagement.dao.interfaces;

import java.util.Optional;

public interface Dao<E> {

    void create(E entity);

    void update(E entity);

    void remove(E entity);

    Optional<E> findById(Long id);
}