package service;

import domain.Reteta;
import repo.Repo;

import java.util.List;

public class Service <T extends Reteta> {
    protected Repo<T> repo;

    public Service(Repo<T> repo) {
        this.repo = repo;
    }


    public void add(T entity) {
        repo.add(entity);
    }


    public T find(String nume) {
        return repo.find(nume);
    }
    public List<T> getAll() {
        return repo.getAll();
    }
}
