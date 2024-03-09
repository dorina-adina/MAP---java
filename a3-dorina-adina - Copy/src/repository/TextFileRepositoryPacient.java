import Entity;
import Pacient;

import java.io.*;

public class TextFileRepositoryPacient<T extends Entity> extends RepoGeneric<Pacient> {
    private String fileName;
//    private PacientFactory entityFactory;

    public TextFileRepositoryPacient(String fileName) throws FileNotFoundException, RepositoryException {
        this.fileName = fileName;
//        this.entityFactory = entityFactory;
        this.fileName = fileName;
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        readFromFile();
    }

    @Override
    public Pacient find(int id) throws RepositoryException {
        return super.find(id);
    }

    @Override
    public void update(Pacient pacientNou) throws RepositoryException {
        try {
            super.update(pacientNou);
            writeInFile();
            throw new IOException("Error saving file ");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public void remove(int id) throws RepositoryException {
        super.remove(id);
        try {
            writeInFile();
            throw new IOException("Error saving file ");

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public void add(Pacient pacient) throws RepositoryException {
        super.add(pacient);
        try {
            writeInFile();
            throw new IOException("Error saving file ");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void readFromFile() throws FileNotFoundException, RepositoryException {
//        File file = new File(fileName);
//        Scanner scanner = new Scanner(file);
//        while (scanner.hasNextLine()) {
//            String line = scanner.nextLine();
//            Pacient entity = entityFactory.createEntity(line);
//            add((Pacient) entity);
//
////        scanner.close();}
//        }
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String linie;
            while ((linie = br.readLine()) != null) {
                Pacient pacient = convertReadLineToEntity(linie);
                entitati.add(pacient);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeInFile() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Pacient entitate : entitati) {
                String linie = convertEntityInLine(entitate);
                bw.write(linie);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private Pacient convertReadLineToEntity(String linie) {

        String[] parts = linie.split(",");

        int id = Integer.parseInt(parts[0]);
        String nume = parts[1];
        String prenume = parts[2];
        int varsta = Integer.parseInt(parts[3]);

        Pacient pacient = new Pacient(id, nume, prenume,varsta);
        return pacient;
    }

    private String convertEntityInLine(Pacient entitate) {
        String result = entitate.getId() + "," + entitate.getNume() + "," + entitate.getPrenume() + "," + entitate.getVarsta() + ",";
        return result;
    }

}

