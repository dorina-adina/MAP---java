//package test;
//import domain.Pacient;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import repository.BinaryFileRepositoryPacient;
//import repository.RepositoryException;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//
//public class BinaryFileRepositoryTestPacient {
//    private static final String FILE_NAME = "C:/Users/User/IdeaProjects/lab_3/src/testDataPacient.bin";
//    private BinaryFileRepositoryPacient<Pacient> binaryRepo;
//
//    @Before
//    public void setUp() {
//        binaryRepo = new BinaryFileRepositoryPacient<>(FILE_NAME);
//    }
//
//    @After
//    public void tearDown() {
//        File file = new File(FILE_NAME);
//        if (file.exists()) {
//            file.delete();
//        }
//    }
//
//    @Test
//    public void testAddAndSave() throws RepositoryException, IOException, ClassNotFoundException {
//        Pacient pacient = new Pacient(1, "Mihailescu", "Pavel", 22);
//        Pacient pacient2 = new Pacient(2, "Miha", "Pavel", 20);
//
//        binaryRepo.add(pacient);
//        binaryRepo.add(pacient2);
//
//        try{
//            binaryRepo.add(pacient);
//            throw new IOException("Error saving file ");
//        } catch(IOException e) {
//            assertEquals("Error saving file ", e.getMessage());}
//
//        List<Pacient> pacienti = (List<Pacient>) binaryRepo.getAll();
//
//        assertEquals(2, pacienti.size());
//        assert pacient == pacienti.get(0);
//        assertEquals(pacient2, pacienti.get(1));
//
//        assert pacient == binaryRepo.find(1);
//
//        Pacient pacientNou = new Pacient(2, "Lucaci", "Aurel", 22);
//
//        binaryRepo.update(pacientNou);
//        assert pacientNou == pacienti.get(1);
//
//        try{
//            Pacient pacientNou1 = new Pacient(7, "Lucaci", "Aurel", 22);
//            binaryRepo.update(pacientNou1);
//            throw new IOException("Error saving file ");
//        } catch(IOException e) {
//            assertEquals("Error saving file ", e.getMessage());}
//
//        binaryRepo.remove(2);
//        binaryRepo.remove(1);
//
//        assertEquals(0, pacienti.size());
//
//        try{
//            binaryRepo.remove(9);
//            throw new IOException("Error saving file ");
//        } catch(IOException e) {
//            assertEquals("Error saving file ", e.getMessage());}
//
//    }
//}
//
//
