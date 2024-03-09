import Entity;

import java.util.ArrayList;
import java.util.List;

public class RepoGeneric<T extends Entity> implements Repository<T> {
    protected List<T> entitati = new ArrayList<>();

    @Override
    public void add(T entity) throws RepositoryException {
        int ok = 1;
            for (T entityi : entitati) {
                if (entityi.getId() == entity.getId()) {
                    ok = 0;
                    break;
                }
            }
            try{
                if(ok == 0){
                    throw new DuplicateException("Exista deja o entitate cu ID-ul dat");
                }
            } catch (DuplicateException e){
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
    public List<T> getAll() {
        return entitati;
    }

//    @Override
//    public Iterator<T> iterator() {
//        return new ArrayList<T>(entitati).iterator();
//    }
}


