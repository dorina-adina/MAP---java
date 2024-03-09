package repo;

import domain.Reteta;

import java.util.ArrayList;
import java.util.List;

public class Repo <T extends Reteta> {
    protected List<T> entitati = new ArrayList<>();
    public void add(T entity) {
        entitati.add(entity);
    }

    public T find(String nume) {
            for (T entity : entitati) {
                if (entity.getNume() == nume) {
                    return (T) entity;
                }
            }
        return null;
    }



    public List<T> getAll() {
        return entitati;
    }
}
