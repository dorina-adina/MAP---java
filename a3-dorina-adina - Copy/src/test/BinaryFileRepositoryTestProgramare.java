package src.test;//package test;

import src.domain.Pacient;
import src.domain.Programare;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import src.repository.BinaryFileRepositoryProgramare;
import src.repository.RepositoryException;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BinaryFileRepositoryTestProgramare {

       private static final String FILE_NAME = "C:/Users/User/Documents/GitHub/a3-dorina-adina/src/testDataProgramare.bin";
       private BinaryFileRepositoryProgramare<Programare> fileRepository;

       @Before
       public void setUp() {
           fileRepository = new BinaryFileRepositoryProgramare<>(FILE_NAME);
       }

       @After
       public void tearDown() {
           File file = new File(FILE_NAME);
           if (file.exists()) {
               file.delete();
           }
       }

       @Test
       public void testAddAndSave() throws RepositoryException, IOException, ClassNotFoundException {
           Pacient pacient = new Pacient(1, "Pop", "Pavel", 11);

           Programare progrmare = new Programare(1, pacient,"12-11-2023", "12:40", "extractie");
           Programare progrmare2 = new Programare(2, pacient,"12-12-2023", "17:00", "extractie");

           fileRepository.add(progrmare);
           fileRepository.add(progrmare2);

           try {
               fileRepository.add(progrmare);
               throw new IOException("Error saving file ");
           } catch (IOException e) {
               assertEquals("Error saving file ", e.getMessage());
           }

           List<Programare> programari = (List<Programare>) fileRepository.getAll();

           assertEquals(2, programari.size());
           assert progrmare == programari.get(0);
           assertEquals(progrmare2, programari.get(1));

           assert progrmare == fileRepository.find(1);

           Programare programareNoua = new Programare(2, pacient,"01-01-2023", "13:00", "extractie");

           fileRepository.update(programareNoua);
           assert programareNoua == programari.get(1);

           try {
               Programare programareNoua1 = new Programare(7, pacient,"19-12-2023", "18:00", "extractie");
               fileRepository.update(programareNoua1);
               throw new IOException("Error saving file ");
           } catch (IOException e) {
               assertEquals("Error saving file ", e.getMessage());
           }
           fileRepository.remove(2);
           fileRepository.remove(1);

           assertEquals(0, programari.size());

           try {
               fileRepository.remove(9);
               throw new IOException("Error saving file ");
           } catch (IOException e) {
               assertEquals("Error saving file ", e.getMessage());
           }

       }
}
