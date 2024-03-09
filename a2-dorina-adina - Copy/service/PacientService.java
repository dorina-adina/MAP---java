package service;

import domain.Pacient;

import repository.*;

import java.util.Collection;

public class PacientService {
    protected RepoGeneric<Pacient> pacientRepository = new RepoGeneric<>();

    public void addPacient(Pacient pacient) throws DuplicateException {
        pacientRepository.add(pacient);
    }

    public void updatePacient(Pacient pacinetNew) throws RepositoryException {
        pacientRepository.update(pacinetNew);
    }

    public void removePacient(int id) throws RepositoryException {
        pacientRepository.remove(id);
    }

    public Pacient findPacient(int id) throws RepositoryException {
        return pacientRepository.find(id);
    }
    public Collection<Pacient> getAll() {
        return pacientRepository.getAll();
    }

}
