import UI;
import Pacient;
import Programare;
import src.repository.*;
import PacientService;
import ProgramareService;
import src.test.*;

import java.io.FileNotFoundException;

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