import domain.Pacient;
import domain.Programare;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class ProgramareTest {
    @Test
    public void testProgramare() {
        Pacient pacient = new Pacient(1, "Pop", "Nicu", 22);
        Programare programare = new Programare(1, pacient, "2023-11-29", "12:40", "extractie");
        assert programare.getId() == 1;
        assert programare.getPacient() == pacient;
        assert "2023-11-29".equals(programare.getData());
        assert "12:40".equals(programare.getOra());
        assert "extractie".equals(programare.getScopulProgramarii());

        Pacient pacient1 = new Pacient(1, "Ciurtin", "Enache", 77);
        programare.setPacient(pacient1);
        programare.setData("2024-02-02");
        programare.setOra("18:30");
        programare.setScopulProgramarii("control");
        assert programare.getPacient() == pacient1;
        assert "2024-02-02".equals(programare.getData());
        assert "18:30".equals(programare.getOra());
        assert "control".equals(programare.getScopulProgramarii());

        String afisat = "Programare{ ID = 1, pacient: Pacient{ ID = 1, nume = Ciurtin, prenume = Enache, varsta = 77 }, data: 2024-02-02, ora : 18:30, scopul programarii: control}";
        assertEquals(afisat,programare.toString());
    }
}
