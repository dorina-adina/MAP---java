import domain.Pacient;

import repository.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PacientService {
    protected static RepoGeneric<Pacient> pacientRepository = new RepoGeneric<>();

    public PacientService(RepoGeneric<Pacient> pacientRepository) {
        this.pacientRepository = pacientRepository;
    }


    public void addPacient(Pacient pacient) throws RepositoryException {
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
    public List<Pacient> getAll() {
        return pacientRepository.getAll();
    }
}
