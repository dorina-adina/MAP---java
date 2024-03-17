package test;

import domain.Entity;
import domain.Pacient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import repository.RepositoryException;
import repository.TextFileRepositoryPacient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class TextFileRepositoryTestPacient {
   private static final String File = "C:/Users/User/IdeaProjects/lab_3/src/tempFileTestPacient.txt";
//    private PacientFactory entityFactory;
   private TextFileRepositoryPacient<Entity> fileRepository;

   @Before
   public void setUp() throws FileNotFoundException, RepositoryException {

//        entityFactory = new PacientFactory();
       fileRepository = new TextFileRepositoryPacient<Entity>(File);

   }

   @After
   public void tearDown() {
       File file = new File(File);
       if (file.exists()) {
           file.delete();
       }
   }

   @Test
   public void testAddAndGetAll() throws RepositoryException, IOException {
       Pacient pacient = new Pacient(1,"Pop", "Pavel", 11);
       Pacient pacient2 = new Pacient(2, "Popa", "Pavel", 33);

       fileRepository.add(pacient);
       fileRepository.add(pacient2);

       try{
           fileRepository.add(pacient);
           throw new IOException("Error saving file ");
       } catch(IOException e) {
           assertEquals("Error saving file ", e.getMessage());}

       List<Pacient> pacienti = (List<Pacient>) fileRepository.getAll();

       assertEquals(4, pacienti.size());
       assert pacient == pacienti.get(2);
       assertEquals(pacient2, pacienti.get(3));

       assert pacient == fileRepository.find(1);

       Pacient pacientNou = new Pacient(3, "Lucaci", "Aurel", 22);

       fileRepository.update(pacientNou);
       assert pacientNou == pacienti.get(3);

       try{
           Pacient pacientNou1 = new Pacient(7, "Lucaci", "Aurel", 22);
           fileRepository.update(pacientNou1);
           throw new IOException("Error saving file ");
       } catch(IOException e) {
           assertEquals("Error saving file ", e.getMessage());}

       fileRepository.remove(3);
       fileRepository.remove(2);
       fileRepository.remove(1);
       fileRepository.remove(4);

       assertEquals(0, pacienti.size());

       try{
           fileRepository.remove(9);
           throw new IOException("Error saving file ");
       } catch(IOException e) {
           assertEquals("Error saving file ", e.getMessage());}

   }
}


//package test;
//
//import domain.Entity;
//import domain.IEntityFactory;
//import domain.Pacient;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import repository.RepositoryException;
//import repository.TextFileRepository;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.List;
//import static org.junit.Assert.assertEquals;
//
//public class TextFileRepositoryTest {
//    private static final String File = "C:/Users/User/IdeaProjects/lab_3/src/tempFileTestPacient.txt";
//
//    private TextFileRepository<Entity> fileRepository;
//    private IEntityFactory<Entity> entityFactory;
//
//    @Before
//    public void setUp() throws FileNotFoundException, RepositoryException {
//        entityFactory = new IEntityFactory<Entity>() {
//            @Override
//            public Entity createEntity(String line) throws RepositoryException {
//                return null;
//            }
//        };
//        fileRepository = new TextFileRepository<>(File,entityFactory);
//
//    }
//
//    @After
//    public void tearDown() {
//        File file = new File(File);
//        if (file.exists()) {
//            file.delete();
//        }
//    }
//
//    @Test
//    public void testAddAndGetAll() throws RepositoryException {
//        Pacient pacient = new Pacient(1,"Pop", "Pavel", 11);
//        Pacient pacient2 = new Pacient(2, "Popa", "Pavel", 33);
//
//
//        // Add entities to the repository
//        fileRepository.add(pacient);
//        fileRepository.add(pacient2);
//
//        // Get all entities from the repository
//        List<Entity> pacienti = (List<Entity>) fileRepository.getAll();
//
//        // Check if the retrieved entities match the added ones
//        assertEquals(2, pacienti.size());
//        assertEquals(pacient, pacienti.get(0));
//        assertEquals(pacient2, pacienti.get(1));
//    }
//}
