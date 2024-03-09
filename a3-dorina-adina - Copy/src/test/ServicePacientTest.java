import src.domain.Pacient;
import org.junit.jupiter.api.Test;
import src.repository.RepoGeneric;
import src.repository.RepositoryException;
import src.service.PacientService;

import java.util.List;

public class ServicePacientTest {
    protected static RepoGeneric<Pacient> pacientRepository = new RepoGeneric<>();

    protected PacientService pacientService = new PacientService(pacientRepository);



    @Test
    public void testAddPacient() throws RepositoryException {

        Pacient pacient = new Pacient(1, "Pop", "Pavel", 22);
        pacientService.addPacient(pacient);
        List<Pacient> pacienti = (List<Pacient>) pacientService.getAll();
        assert pacienti.size() == 1;
        assert pacienti.get(0).getId() == 1;
//        assert Objects.equals(pacienti.get(0).getNume(), "Pop");
//        assert Objects.equals(pacienti.get(0).getPrenume(), "Pavel");
        assert pacienti.get(0).getVarsta() == 22;

        Pacient pacient1 = new Pacient(1, "Negoita", "Raul", 23);
        pacientService.addPacient(pacient1);
        pacienti = (List<Pacient>) pacientService.getAll();
        assert pacienti.size() == 1;
        assert pacienti.get(0).getId() == 1;
//        assert Objects.equals(pacienti.get(0).getNume(), "Pop");
//        assert Objects.equals(pacienti.get(0).getPrenume(), "Pavel");
        assert pacienti.get(0).getVarsta() == 22;
    }

    @Test
    public void testUpdatePacient() throws RepositoryException {

            Pacient pacient = new Pacient(1, "Pop", "Nicu", 22);
            pacientService.addPacient(pacient);
            Pacient pacientNou = new Pacient(1, "Popa", "Nicoleta", 22);
            pacientService.updatePacient(pacientNou);
            List<Pacient> pacienti = (List<Pacient>) pacientService.getAll();
            assert pacienti.size() == 1;
            assert pacienti.get(0).getId() == 1;
            assert "Popa".equals(pacienti.get(0).getNume());
            assert "Nicoleta".equals(pacienti.get(0).getPrenume());
            assert pacienti.get(0).getVarsta() == 22;

            Pacient pacientNou2 = new Pacient(2,"Popescu", "Mihai", 43);
            pacientService.updatePacient(pacientNou2);

    }

    @Test
    public void testRemovePacient() throws RepositoryException {

            Pacient pacient = new Pacient(1, "Pop", "Nicu", 22);
            pacientService.addPacient(pacient);
            pacientService.removePacient(1);
            List<Pacient> pacienti = (List<Pacient>) pacientService.getAll();
            assert pacienti.size() == 0;

            pacientService.removePacient(2);

    }

    @Test
    public void testFindPacient() throws RepositoryException {

            Pacient pacient = new Pacient(1, "Pop", "Nicu", 22);
            pacientService.addPacient(pacient);
            Pacient pacientGasit = pacientService.findPacient(1);
            assert pacientGasit.getId() == 1;
            assert "Pop".equals(pacientGasit.getNume());
            assert "Nicu".equals(pacientGasit.getPrenume());
            assert pacientGasit.getVarsta() == 22;

            pacientService.findPacient(2);

    }

    @Test
    public void testTotiPacient() throws RepositoryException {
        Pacient pacient = new Pacient(1, "Pop", "Nicu", 22);
        pacientService.addPacient(pacient);
        List<Pacient> pacienti = (List<Pacient>) pacientService.getAll();
        assert pacienti.size() == 1;
        assert pacienti.get(0).getId() == 1;
//        assert Objects.equals(pacienti.get(0).getNume(), "Pop");
//        assert Objects.equals(pacienti.get(0).getPrenume(), "Pavel");
        assert pacienti.get(0).getVarsta() == 22;
    }

}
