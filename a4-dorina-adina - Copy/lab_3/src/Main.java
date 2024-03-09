import UI.UI;
import domain.Pacient;
import domain.Programare;
import repository.*;
import service.PacientService;
import service.ProgramareService;
import test.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static UI consola;

    public static void main(String[] args) throws RepositoryException, FileNotFoundException {
        System.out.println("AM TRECUT TESTELE CU SUCCES");

        String repositoryType = settings.getRepositoryType();

        PacientService pacientService = null;
        ProgramareService programareService = null;

        if ("binary".equals(repositoryType)) {
            RepoGeneric<Pacient> pacientRepo = new BinaryFileRepositoryPacient<>("Pacienti.bin");
            pacientService = new PacientService(pacientRepo);
            RepoGeneric<Programare> programareRepo = new BinaryFileRepositoryProgramare<>("Programari.bin");
            programareService = new ProgramareService(programareRepo);
        } else if ("text".equals(repositoryType)) {
//            PacientFactory pacientFactory = new PacientFactory();
//            ProgramareFactory programareFactory = new ProgramareFactory();
            RepoGeneric<Pacient> pacientRepo = new TextFileRepositoryPacient("Pacienti.txt");
            pacientService = new PacientService(pacientRepo);
            RepoGeneric<Programare> programareRepo = new TextFileRepositoryProgramare("Programari.txt");
            programareService = new ProgramareService(programareRepo);

        }else if("bazaDeDate".equals(repositoryType)) {

            RepoGeneric<Pacient> pacientRepo = new SQLRepositoryPacient();
            List<Pacient> pacienti = pacientRepo.getAll();
            pacientService = new PacientService(pacientRepo);
            RepoGeneric<Programare> programareRepo = new SQLRepositoryProgramare(pacienti);
            programareService = new ProgramareService(programareRepo);

        }
//        try (FileInputStream input = new FileInputStream("settings.properties")) {
//            properties.load(input);
//
//            // Read repository type
//            String repositoryType = properties.getProperty("Repository");
//            System.out.println("Repository Type: " + repositoryType);
//
//            // Read patients file
//            String patientsFile = properties.getProperty("Patients");
//            System.out.println("Patients File: " + patientsFile);
//
//            // Read appointments file
//            String appointmentsFile = properties.getProperty("Appointments");
//            System.out.println("Appointments File: " + appointmentsFile);
//
//            // Use the read values as needed in your application
//            // ...
//
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
        consola = new UI(pacientService,programareService);
        consola.meniu();

    }
}