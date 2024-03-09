import domain.Pacient;
import domain.Programare;
import org.junit.jupiter.api.Test;
import repository.RepoGeneric;
import repository.RepositoryException;
import service.PacientService;
import service.ProgramareService;

import java.util.List;

public class ServiceProgramareTest {
    protected static RepoGeneric<Programare> programareRepository = new RepoGeneric<>();

    protected ProgramareService programareService = new ProgramareService(programareRepository);

    @Test
    public void testAddProgramare() throws RepositoryException {

            Pacient pacient = new Pacient(1, "Pop", "Nicu", 22);
            Programare programare = new Programare(1, pacient, "2023-11-29", "12:40", "extractie");
            programareService.addProgramare(programare);
            List<Programare> programari = (List<Programare>) programareService.getAll();
            assert programari.size() == 1;
            assert programari.get(0).getPacient() == pacient;
            assert programari.get(0).getId() == 1;
            assert "2023-11-29".equals(programari.get(0).getData());
            assert "12:40".equals(programari.get(0).getOra());
            assert "extractie".equals(programari.get(0).getScopulProgramarii());

            pacient = new Pacient(1, "Negoita", "Raul", 23);
            Programare programare1 = new Programare(1, pacient, "2023-12-30", "15:50", "extractie");
            programareService.addProgramare(programare1);
            programari = (List<Programare>) programareService.getAll();
            assert programari.size() == 1;
//            assert programari.get(0).getPacient() == pacient;
            assert programari.get(0).getId() == 1;
            assert "2023-11-29".equals(programari.get(0).getData());
            assert "12:40".equals(programari.get(0).getOra());
            assert "extractie".equals(programari.get(0).getScopulProgramarii());

    }

    @Test
    public void testUpdateProgramare() throws RepositoryException {

            Pacient pacient = new Pacient(1, "Pop", "Nicu", 22);
            Programare programare = new Programare(1, pacient, "2023-11-29", "12:40", "extractie");
            programareService.addProgramare(programare);
            Programare programareNoua = new Programare(1, pacient,"2023-12-10", "12:10", "dertartraj");
            programareService.updateProgramare(programareNoua);
            List<Programare> programari = (List<Programare>) programareService.getAll();
            assert programari.size() == 1;
            assert programari.get(0).getId() == 1;
            assert programari.get(0).getPacient() == pacient;
            assert "2023-12-10".equals(programari.get(0).getData());
            assert "12:10".equals(programari.get(0).getOra());
            assert "dertartraj".equals(programari.get(0).getScopulProgramarii());

            pacient = new Pacient(1, "Pop", "Nicu", 22);
            Programare programareNoua2 = new Programare(2, pacient, "2024-01-10", "15:50", "extractie");
            programareService.updateProgramare(programareNoua2);

    }

    @Test
    public void testRemoveProgramare() throws RepositoryException {

            Pacient pacient = new Pacient(1, "Pop", "Nicu", 22);
            Programare programare = new Programare(1, pacient, "2023-11-29", "12:40", "extractie");
            programareService.addProgramare(programare);
            programareService.removeProgramare(1);
            List<Programare> programari = (List<Programare>) programareService.getAll();
            assert programari.size() == 0;

            programareService.removeProgramare(2);

    }

    @Test
    public void testFindProgramare() throws RepositoryException {

            Pacient pacient = new Pacient(1, "Pop", "Nicu", 22);
            Programare programare = new Programare(1, pacient, "2023-11-29", "12:40", "extractie");
            programareService.addProgramare(programare);
            Programare programareGasita = programareService.findProgramare(1);
            assert programareGasita.getId() == 1;

            programareService.findProgramare(2);

    }

    @Test
    public void testToateProgramarile() throws RepositoryException {
        Pacient pacient = new Pacient(1, "Pop", "Nicu", 22);
        Programare programare = new Programare(1, pacient, "2023-11-29", "12:40", "extractie");
        programareService.addProgramare(programare);
        List<Programare> programari = (List<Programare>) programareService.getAll();
        assert programari.size() == 1;
        assert programari.get(0).getId() == 1;
        assert programari.get(0).getPacient() == pacient;
        assert "2023-11-29".equals(programari.get(0).getData());
        assert "12:40".equals(programari.get(0).getOra());
        assert "extractie".equals(programari.get(0).getScopulProgramarii());
    }

}


