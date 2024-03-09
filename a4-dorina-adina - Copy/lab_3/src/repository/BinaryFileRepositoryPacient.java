import domain.Entity;
import domain.Pacient;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BinaryFileRepositoryPacient<T extends Entity> extends RepoGeneric<Pacient> {
    private String fileName;

    public BinaryFileRepositoryPacient(String fileName) {
//        super();
        this.fileName = fileName;
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

    private void readFromFile() {

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            Object obj = ois.readObject();

            if (obj instanceof List<?>) {
                List<?> deserializedList = (List<?>) obj;

                if (!deserializedList.isEmpty() && deserializedList.get(0) instanceof Pacient) {
                    this.entitati = (List<Pacient>) obj;
                } else {
                    System.err.println("Unexpected object type in the file.");
                }
            }
        } catch (EOFException e) {
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    private void writeInFile() throws IOException {
        try {
            try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
                outputStream.writeObject(new ArrayList<>(entitati.stream().toList()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}