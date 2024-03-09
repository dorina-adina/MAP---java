import Programare;
import src.repository.RepoGeneric;
import src.repository.RepositoryException;

import java.util.List;

public class ProgramareService {
    protected static RepoGeneric<Programare> programareRepository = new RepoGeneric<>();

    public ProgramareService(RepoGeneric<Programare> programareRepository) {
        this.programareRepository = programareRepository;
    }



    public void addProgramare(Programare programare) throws RepositoryException {
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


    public List<Programare> getAll() {
        return programareRepository.getAll();
    }
}

