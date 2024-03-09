//import domain.IEntityFactory;
//import domain.Pacient;
//import org.junit.jupiter.api.Test;
//import domain.PacientFactory;
//import repository.RepositoryException;
//
//import java.util.Objects;
//
//public class PacientFactoryTest {
//    PacientFactory pacientFactory = new PacientFactory();
//    @Test
//    public void testCreateEntity() throws RepositoryException {
//       String line = "1,Pop,Pavel,22";
//       Pacient pacient1 = pacientFactory.createEntity(line);
//       assert pacient1.getId() == 1;
//       assert Objects.equals(pacient1.getNume(), "Pop");
//       assert Objects.equals(pacient1.getPrenume(), "Pavel");
//       assert pacient1.getVarsta() == 22;
//    }
//}
