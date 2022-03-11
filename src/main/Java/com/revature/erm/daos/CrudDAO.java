package com.revature.erm.daos;

import java.util.List;

public interface CrudDAO<T> {
    void save(T newObject);
    T getById(String id);
    List<T> getAll(); // TODO return a dynamically sizeable
    void update(T updatedObject);

}
