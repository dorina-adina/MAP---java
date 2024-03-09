import src.domain.Pacient;
import src.domain.Programare;
import org.junit.jupiter.api.Test;
import src.repository.DuplicateException;
import src.repository.RepoGeneric;
import src.repository.RepositoryException;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class RepositoryProgramareTest {
    protected RepoGeneric<Programare> programareRepository = new RepoGeneric<>();

    @Test
    public void testAddProgramare() throws RepositoryException {

            Pacient pacient = new Pacient(1, "Pop", "Nicu", 22);
            Programare programare = new Programare(1, pacient, "2023-11-29", "12:40", "extractie");
            programareRepository.add(programare);
            List<Programare> programari = (List<Programare>) programareRepository.getAll();
            assert programari.size() == 1;
            assert programari.get(0).getPacient() == pacient;
            assert programari.get(0).getId() == 1;
            assert "2023-11-29".equals(programari.get(0).getData());
            assert "12:40".equals(programari.get(0).getOra());
            assert "extractie".equals(programari.get(0).getScopulProgramarii());


        try{
            Pacient pacient1 = new Pacient(2, "Negoita", "Raul", 23);
            Programare programare1 = new Programare(1, pacient1, "2023-12-30", "15:50", "extractie");
            programareRepository.add(programare1);
            programari = (List<Programare>) programareRepository.getAll();
            assert programari.size() == 1;
            assert programari.get(0).getId() == 1;
            assert "2023-11-29".equals(programari.get(0).getData());
            assert "12:40".equals(programari.get(0).getOra());
            assert "extractie".equals(programari.get(0).getScopulProgramarii());
            throw new DuplicateException("Exista deja o entitate cu ID-ul respectiv");
        } catch(DuplicateException e) {
            assertEquals("Exista deja o entitate cu ID-ul respectiv", e.getMessage());}
    }


    @Test
    public void testUpdateProgramare() throws RepositoryException {

            Pacient pacient = new Pacient(1, "Pop", "Nicu", 22);
            Programare programare = new Programare(1, pacient, "2023-11-29", "12:40", "extractie");
            programareRepository.add(programare);
            Programare programareNoua = new Programare(1, pacient,"2023-12-10", "12:10", "dertartraj");
            programareRepository.update(programareNoua);
            List<Programare> programari = (List<Programare>) programareRepository.getAll();
            assert programari.size() == 1;
            assert programari.get(0).getId() == 1;
            assert programari.get(0).getPacient() == pacient;
            assert "2023-12-10".equals(programari.get(0).getData());
            assert "12:10".equals(programari.get(0).getOra());
            assert "dertartraj".equals(programari.get(0).getScopulProgramarii());


        try{
            pacient = new Pacient(1, "Pop", "Nicu", 22);
            Programare programareNoua2 = new Programare(2, pacient, "2024-01-10", "15:50", "extractie");
            programareRepository.update(programareNoua2);
            throw new RepositoryException("Entitatea cu ID-ul specificat nu exista");
        } catch (RepositoryException e) {
            assertEquals("Entitatea cu ID-ul specificat nu exista", e.getMessage());
        }
    }

    @Test
    public void testRemoveProgramare() throws RepositoryException {

            Pacient pacient = new Pacient(1, "Pop", "Nicu", 22);
            Programare programare = new Programare(1, pacient, "2023-11-29", "12:40", "extractie");
            programareRepository.add(programare);
            programareRepository.remove(1);
            List<Programare> programari = (List<Programare>) programareRepository.getAll();
            assert programari.size() == 0;

        try{
            programareRepository.remove(2);
            throw new RepositoryException("Entitatea cu ID-ul specificat nu exista");
        } catch (RepositoryException e) {
            assertEquals("Entitatea cu ID-ul specificat nu exista", e.getMessage());
        }
    }

    @Test
    public void testFindProgramare() throws RepositoryException {

            Pacient pacient = new Pacient(1, "Pop", "Nicu", 22);
            Programare programare = new Programare(1, pacient, "2023-11-29", "12:40", "extractie");
            programareRepository.add(programare);
            Programare programareGasita = programareRepository.find(1);
            assert programareGasita.getId() == 1;
            assert programareGasita.getPacient() == pacient;
            assert "2023-11-29".equals(programareGasita.getData());
            assert "12:40".equals(programareGasita.getOra());
            assert "extractie".equals(programareGasita.getScopulProgramarii());

        try{
            programareRepository.find(2);
            throw new RepositoryException("Entitatea cu ID-ul specificat nu exista");
        } catch (RepositoryException e) {
            assertEquals("Entitatea cu ID-ul specificat nu exista", e.getMessage());
        }
    }

    @Test
    public void testToateProgramarile() throws RepositoryException {
        Pacient pacient = new Pacient(1, "Pop", "Nicu", 22);
        Programare programare = new Programare(1, pacient, "2023-11-29", "12:40", "extractie");
        programareRepository.add(programare);
        List<Programare> programari = (List<Programare>) programareRepository.getAll();
        assert programari.size() == 1;
        assert programari.get(0).getId() == 1;
        assert programari.get(0).getPacient() == pacient;
        assert "2023-11-29".equals(programari.get(0).getData());
        assert "12:40".equals(programari.get(0).getOra());
        assert "extractie".equals(programari.get(0).getScopulProgramarii());
    }

}

