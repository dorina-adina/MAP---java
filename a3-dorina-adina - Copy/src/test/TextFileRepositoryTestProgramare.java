//package src.test;//package test;
//
//import src.domain.Entity;
//import src.domain.Pacient;
//import src.domain.Programare;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import src.repository.RepositoryException;
//import src.repository.TextFileRepositoryProgramare;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//
//public class TextFileRepositoryTestProgramare {
//    private static final String File = "C:/Users/User/Documents/GitHub/a3-dorina-adina/src/tempFileTestProgramare.txt";
//    //    private ProgramareFactory entityFactory;
//    private TextFileRepositoryProgramare<Entity> fileRepository;
//
//    @Before
//    public void setUp() throws FileNotFoundException, RepositoryException {
//
////        entityFactory = new ProgramareFactory();
//        fileRepository = new TextFileRepositoryProgramare<>(File);
//
//    }
//
//    @After
//    public void tearDown() {
//        java.io.File file = new File(File);
//        if (file.exists()) {
//            file.delete();
//        }
//    }
//
//    @Test
//    public void testAddAndGetAll() throws RepositoryException {
//        Pacient pacient = new Pacient(1, "Pop", "Pavel", 11);
//
//        Programare progrmare = new Programare(1, pacient,"12-11-2023", "12:40", "extractie");
//        Programare progrmare2 = new Programare(2, pacient,"12-12-2023", "17:00", "extractie");
//
//        fileRepository.add(progrmare);
//        fileRepository.add(progrmare2);
//
//        try {
//            fileRepository.add(progrmare);
//            throw new IOException("Error saving file ");
//        } catch (IOException e) {
//            assertEquals("Error saving file ", e.getMessage());
//        }
//
//        List<Programare> programari = (List<Programare>) fileRepository.getAll();
//
//        assertEquals(4, programari.size());
//        assert progrmare == programari.get(2);
//        assertEquals(progrmare2, programari.get(3));
//
//        assert progrmare == fileRepository.find(1);
//
//        Programare programareNoua = new Programare(2, pacient,"01-01-2023", "13:00", "extractie");
//
//        fileRepository.update(programareNoua);
//        assert programareNoua == programari.get(3);
//
//        try {
//            Programare programareNoua1 = new Programare(7, pacient,"19-12-2023", "18:00", "extractie");
//            fileRepository.update(programareNoua1);
//            throw new IOException("Error saving file ");
//        } catch (IOException e) {
//            assertEquals("Error saving file ", e.getMessage());
//        }
//        fileRepository.remove(4);
//        fileRepository.remove(3);
//        fileRepository.remove(2);
//        fileRepository.remove(1);
//
//        assertEquals(0, programari.size());
//
//        try {
//            fileRepository.remove(9);
//            throw new IOException("Error saving file ");
//        } catch (IOException e) {
//            assertEquals("Error saving file ", e.getMessage());
//        }
//
//    }
//}
