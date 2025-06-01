package com.example.spels.service;

import java.util.Collection;

public interface CrudService<T> {
    T getById(Integer id);
    Collection<T> getAll();
    void create(T item);
    void update(T item);
    void deleteById(Integer id);
}
