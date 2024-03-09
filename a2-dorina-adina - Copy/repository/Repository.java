package repository;

import domain.Entity;

import java.util.Collection;

public interface Repository<T extends Entity>{
    public void add(T entity) throws DuplicateException;

    public void update(T entity) throws RepositoryException;

    public void remove(int id) throws RepositoryException;

    public T find(int id) throws RepositoryException;

    public Collection<T> getAll();

}

