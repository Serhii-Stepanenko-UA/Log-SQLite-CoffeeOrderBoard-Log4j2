package org.example.app.repository;

import java.util.List;
import java.util.Optional;

public interface AppRepository<T> {
    String create(T obj);
    Optional<List<T>> read();
    Optional<T> readByNum(Long id);
    String update(T obj);
    String delete(Long id);
}
