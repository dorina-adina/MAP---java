package service;

import domain.Programare;

import repository.DuplicateException;
import repository.RepoGeneric;
import repository.RepositoryException;

import java.util.Collection;

public class ProgramareService {
    protected RepoGeneric<Programare> programareRepository = new RepoGeneric<>();

    public void addProgramare(Programare programare) throws DuplicateException {
        programareRepository.add(programare);
    }

    public void updateProgramare(Programare programareNew) throws RepositoryException {
        programareRepository.update(programareNew);
    }

    public void removeProgramare(int id) throws RepositoryException {
        programareRepository.remove(id);
    }

    public Programare findProgramare(int id) throws RepositoryException {
        return programareRepository.find(id);
    }


    public Collection<Programare> getAll() {
        return programareRepository.getAll();
    }
}

