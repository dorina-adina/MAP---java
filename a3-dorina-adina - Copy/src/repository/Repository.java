import Entity;

import java.util.List;

public interface Repository<T extends Entity>{
    public void add(T entity) throws RepositoryException;

    public void update(T entity) throws RepositoryException;

    public void remove(int id) throws RepositoryException;

    public T find(int id) throws RepositoryException;

    public List<T> getAll();

}

