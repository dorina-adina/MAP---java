import src.domain.Pacient;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class PacientTest {
    @Test
    public void testPacient() {
        Pacient pacient = new Pacient(1, "Pop", "Nicu", 22);
        assert pacient.getId() == 1;
        assert "Pop".equals(pacient.getNume());
        assert "Nicu".equals(pacient.getPrenume());
        assert pacient.getVarsta() == 22;

        pacient.setNume("Poputa");
        pacient.setPrenume("Vasile");
        pacient.setVarsta(56);
        assert "Poputa".equals(pacient.getNume());
        assert "Vasile".equals(pacient.getPrenume());
        assert pacient.getVarsta() == 56;

        String afisat = "Pacient{ ID = 1, nume = Poputa, prenume = Vasile, varsta = 56 }";
        assertEquals(afisat,pacient.toString());

    }
}
