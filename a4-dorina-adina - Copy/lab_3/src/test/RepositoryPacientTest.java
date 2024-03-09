import domain.Pacient;
import org.junit.jupiter.api.Test;
import repository.DuplicateException;
import repository.RepoGeneric;
import repository.RepositoryException;

import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class RepositoryPacientTest {
    protected RepoGeneric<Pacient> pacientRepository = new RepoGeneric<>();

    @Test
    public void testAddPacient() throws RepositoryException {
            Pacient pacient = new Pacient(1, "Pop", "Nicu", 22);
            pacientRepository.add(pacient);
            List<Pacient> pacienti = (List<Pacient>) pacientRepository.getAll();
            assert pacienti.size() == 1;
            assert pacienti.get(0).getId() == 1;
            assert "Pop".equals(pacienti.get(0).getNume());
            assert "Nicu".equals(pacienti.get(0).getPrenume());
            assert pacienti.get(0).getVarsta() == 22;

        try{
            Pacient pacient1 = new Pacient(1, "Negoita", "Raul", 23);
            pacientRepository.add(pacient1);
            pacienti = (List<Pacient>) pacientRepository.getAll();
            assert pacienti.size() == 1;
            assert pacienti.get(0).getId() == 1;
            assert "Pop".equals(pacienti.get(0).getNume());
            assert "Nicu".equals(pacienti.get(0).getPrenume());
            assert pacienti.get(0).getVarsta() == 22;
            throw new DuplicateException("Exista deja o entitate cu ID-ul respectiv");
        } catch(DuplicateException e) {
            assertEquals("Exista deja o entitate cu ID-ul respectiv", e.getMessage());}
    }

    @Test
    public void testUpdatePacient() throws RepositoryException {

        Pacient pacient = new Pacient(1, "Pop", "Nicu", 22);
        pacientRepository.add(pacient);
        Pacient pacientNou = new Pacient(1, "Popa", "Nicoleta", 22);
        pacientRepository.update(pacientNou);
        List<Pacient> pacienti = (List<Pacient>) pacientRepository.getAll();
        assert pacienti.size() == 1;
        assert pacienti.get(0).getId() == 1;
        assert "Popa".equals(pacienti.get(0).getNume());
        assert "Nicoleta".equals(pacienti.get(0).getPrenume());
        assert pacienti.get(0).getVarsta() == 22;

        try {
            Pacient pacientNou2 = new Pacient(2, "Popescu", "Mihai", 43);
            pacientRepository.update(pacientNou2);
            throw new RepositoryException("Entitatea cu ID-ul specificat nu exista");
        } catch (RepositoryException e) {
            assertEquals("Entitatea cu ID-ul specificat nu exista", e.getMessage());
        }
    }

    @Test
    public void testRemovePacient() throws RepositoryException {

            Pacient pacient = new Pacient(1, "Pop", "Nicu", 22);
            pacientRepository.add(pacient);
            pacientRepository.remove(1);
            List<Pacient> pacienti = (List<Pacient>) pacientRepository.getAll();
            assert pacienti.size() == 0;

        try{
            pacientRepository.remove(2);
            throw new RepositoryException("Entitatea cu ID-ul specificat nu exista");
        } catch (RepositoryException e) {
            assertEquals("Entitatea cu ID-ul specificat nu exista", e.getMessage());
        }
    }

    @Test
    public void testFindPacient() throws RepositoryException {

            Pacient pacient = new Pacient(1, "Pop", "Nicu", 22);
            pacientRepository.add(pacient);
            Pacient pacientGasit = pacientRepository.find(1);
            assert pacientGasit.getId() == 1;
            assert "Pop".equals(pacientGasit.getNume());
            assert "Nicu".equals(pacientGasit.getPrenume());
            assert pacientGasit.getVarsta() == 22;

        try{
            pacientRepository.find(2);
            throw new RepositoryException("Entitatea cu ID-ul specificat nu exista");
        } catch (RepositoryException e) {
            assertEquals("Entitatea cu ID-ul specificat nu exista", e.getMessage());
        }
    }

    @Test
    public void testTotiPacient() throws RepositoryException {
        Pacient pacient = new Pacient(1, "Pop", "Nicu", 22);
        pacientRepository.add(pacient);
        List<Pacient> pacienti = (List<Pacient>) pacientRepository.getAll();
        assert pacienti.size() == 1;
        assert pacienti.get(0).getId() == 1;
        assert "Pop".equals(pacienti.get(0).getNume());
        assert "Nicu".equals(pacienti.get(0).getPrenume());
        assert pacienti.get(0).getVarsta() == 22;
    }


}
