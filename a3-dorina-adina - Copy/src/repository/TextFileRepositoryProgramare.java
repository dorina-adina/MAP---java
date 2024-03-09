import src.domain.*;

import java.io.*;

public class TextFileRepositoryProgramare<T extends Entity> extends RepoGeneric<Programare> {
    private String fileName;
//    private ProgramareFactory entityFactory;

    public TextFileRepositoryProgramare(String fileName) throws FileNotFoundException, RepositoryException {
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
    public Programare find(int id) throws RepositoryException {
        return super.find(id);
    }

    @Override
    public void update(Programare programareNoua) throws RepositoryException {
        try {
            super.update(programareNoua);
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
    public void add(Programare programare) throws RepositoryException {
        super.add(programare);
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
//            Programare entity = entityFactory.createEntity(line);
//            add((Programare) entity);
////        scanner.close();}
//        }
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String linie;
            while ((linie = br.readLine()) != null) {

                Programare entitate = convertReadLineToEntity(linie);

                entitati.add(entitate);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeInFile() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Programare entitate : entitati) {
                String linie = entitate.getId() + "," + entitate.getPacient() + "," + entitate.getData() + "," + entitate.getOra() + "," + entitate.getScopulProgramarii() + ",";
                bw.write(linie);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Programare convertReadLineToEntity(String linie){

        String[] parts = linie.split(",");
        Programare programare = null;

        int idProgramare = Integer.parseInt(parts[0]);

        Pacient pacient;
        int idPacient = Integer.parseInt(parts[1]);
        String nume = parts[2];
        String prenume = parts[3];
        int varsta = Integer.parseInt(parts[4]);
        pacient = new Pacient(idPacient, nume, prenume, varsta);

        String data = parts[5];
        String ora = parts[6];
        String scop = parts[7];

        programare = new Programare(idProgramare, pacient, data, ora, scop);
        return programare;
    }
}


