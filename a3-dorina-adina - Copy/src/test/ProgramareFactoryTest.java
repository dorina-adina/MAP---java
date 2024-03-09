//
//
//import domain.Programare;
//import domain.ProgramareFactory;
//import org.junit.jupiter.api.Test;
//import repository.RepositoryException;
//
//import java.util.Objects;
//
//public class ProgramareFactoryTest {
//    ProgramareFactory programareFactory = new ProgramareFactory();
//    @Test
//    public void testCreateEntity() throws RepositoryException {
//        String line = "1,1,Mihailescu,Pavel,34,12-11-2023,12:40,extractie";
//        Programare programare = programareFactory.createEntity(line);
//        assert programare.getId() == 1;
//        assert Objects.equals(programare.getData(), "12-11-2023");
//        assert Objects.equals(programare.getOra(), "12:40");
//        assert Objects.equals(programare.getScopulProgramarii(), "extractie");
//    }
//}
