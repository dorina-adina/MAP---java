package repository;

import domain.Entity;

import java.util.*;

public class RepoGeneric<T extends Entity> implements Repository<T> {
    protected List<T> entitati = new ArrayList<>();

    @Override
    public void add(T entity) throws DuplicateException {
        try {
            for (T entityi : entitati) {
                if (entityi.getId() == entity.getId()) {
                    throw new DuplicateException("Exista deja o entitate cu ID-ul respectiv");
                }
            }
        }
        catch (DuplicateException e){
            System.out.println(e);
            return;
        }
        entitati.add(entity);
    }

    @Override
    public void update(T entityNew) throws RepositoryException {
        Entity entityToUpdate = null;
        for (int i = 0; i < entitati.size(); i++) {
            if (entitati.get(i).getId() == entityNew.getId()) {
                entityToUpdate = entityNew;
                remove(entitati.get(i).getId());
                entitati.add((T) entityToUpdate);
                break;
            }
        }
        try{
            if (entityToUpdate == null) {
                throw new RepositoryException("Entitatea cu ID-ul specificat nu exista");
            }
        }
        catch(RepositoryException e){
            System.out.println(e);
            return;
        }
    }

    @Override
    public void remove(int id) throws RepositoryException {
        Entity entityToRemove = null;
        for (int i = 0; i < entitati.size(); i++) {
            if (entitati.get(i).getId() == id) {
                entityToRemove = entitati.get(i);
                entitati.remove(entityToRemove);
                break;
            }
        }
        try{
            if (entityToRemove == null) {
                throw new RepositoryException("Entitatea cu ID-ul specificat nu exista");
            }
        }
        catch(RepositoryException e){
            System.out.println(e);
            return;
        }
    }

    @Override
    public T find(int id) throws RepositoryException{
        try{
            for (Entity entity : entitati) {
                if (entity.getId() == id) {
                    return (T) entity;
                }
            }
            throw new RepositoryException("Entitatea cu ID-ul specificat nu exista");}
        catch (RepositoryException e){
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Collection<T> getAll() {
        return entitati;
    }
}

