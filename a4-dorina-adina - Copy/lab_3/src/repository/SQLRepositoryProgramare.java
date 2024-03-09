import domain.Entity;
import domain.Pacient;
import domain.Programare;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SQLRepositoryProgramare extends RepoGeneric<Programare> {
    private List<Pacient> pacientiList;

    public SQLRepositoryProgramare(List<Pacient> pacientiList) {

        this.pacientiList = pacientiList;
        openConnection();
        createSchema();
    }


//    private SQLRepositoryPacient<Pacient> repoPacient;
//
//    public SQLRepositoryProgramare(SQLRepositoryPacient<Pacient> repoPacient) {
//        this.repoPacient = repoPacient;
//    }

    private static final String JDBC_URL =
            "jdbc:sqlite:programare.db";

    private Connection conn = null;

//    public SQLRepositoryProgramare() {
//        openConnection();
//        createSchema();
//    }

    /**
     * Gets a connection to the database.
     * If the underlying connection is closed, it creates a new connection. Otherwise, the current instance is returned.
     */
    private void openConnection() {
        try {
            // with DriverManager
//            if (conn == null || conn.isClosed())
//                conn = DriverManager.getConnection(JDBC_URL);

            // with DataSource
            SQLiteDataSource ds = new SQLiteDataSource();
            ds.setUrl(JDBC_URL);
            if (conn == null || conn.isClosed())
                conn = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes the underlying connection to the in-memory SQLite instance.
     */
    private void closeConnection() {
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates the sample schema for the database.
     */
    void createSchema() {
        try {
            try (final Statement stmt = conn.createStatement()) {
//        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS pacienti(idPacient int, nume varchar(40), prenume varchar(100), varsta int);");
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS programari(idProgramare int, idPacient int, nume varchar(40), prenume varchar(100), varsta int, dataP varchar(20), ora varchar(10), scopProgramare varchar(40));");
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] createSchema : " + e.getMessage());
        }
    }

    /**
     * Adds some entries in the table books;
     */
    void initTables() {
//        final String[] books = new String[]{
//                "Heather Morris|The Tattooist of Auschwitz|250",
//                "Andre Agassi|Open|300",
//                "Aaron Courville, Ian Goodfellow, Yoshua Bengio|Deep Learning|700"
//        };

//        try {
//        try (PreparedStatement statement = conn.prepareStatement("INSERT INTO pacienti VALUES (?, ?, ?, ?)")) {
//        Random random = new Random();
//        for (int i = 0; i < 100; i++) {
//        int id = i + 1;
//        String nume = generateRandomNume();
//        String prenume = generateRandomPrenume();
//        int varsta = generateRandomVarsta();
//        statement.setInt(1, id);
//        statement.setString(2, nume);
//        statement.setString(3, prenume);
//        statement.setInt(4, varsta);
//        statement.executeUpdate();
//        }
//        }

        try (PreparedStatement statement = conn.prepareStatement("INSERT INTO programari VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {
            Random random = new Random();
            for (int i = 0; i < 100; i++) {
                int id = i + 1;
                Pacient pacient = randomPacient();
                String dataP = generatedRandomDataProgram();
                String ora = generateRandomOra();
                String scop = generatedRandomScop();
                statement.setInt(1, id);
                statement.setInt(2, pacient.getId());
                statement.setString(3, pacient.getNume());
                statement.setString(4, pacient.getPrenume());
                statement.setInt(5, pacient.getVarsta());
                statement.setString(6, dataP);
                statement.setString(7, ora);
                statement.setString(8, scop);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void add(Programare p) {
        try {
            try (PreparedStatement statement = conn.prepareStatement("INSERT INTO programari VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {
                statement.setInt(1, p.getId());
                statement.setInt(2, p.getPacient().getId());
                statement.setString(3, p.getPacient().getNume());
                statement.setString(4, p.getPacient().getPrenume());
                statement.setInt(5, p.getPacient().getVarsta());
                statement.setString(6, p.getData());
                statement.setString(7, p.getOra());
                statement.setString(8, p.getScopulProgramarii());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(int id) {
        try {
            try (PreparedStatement statement = conn.prepareStatement("DELETE FROM programari WHERE idProgramare=?")) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void update(Programare programare) {
        try (PreparedStatement stmt = conn.prepareStatement("UPDATE programari SET idPacient = ?, nume = ?, prenume = ?, varsta = ?, dataP = ?, ora = ?, scopProgramare = ? WHERE idProgramare = ?;")) {
//            stmt.setInt(1, programare.getId());
            stmt.setInt(1, programare.getPacient().getId());
            stmt.setString(2, programare.getPacient().getNume());
            stmt.setString(3, programare.getPacient().getPrenume());
            stmt.setInt(4, programare.getPacient().getVarsta());
            stmt.setString(5, programare.getData());
            stmt.setString(6, programare.getOra());
            stmt.setString(7, programare.getScopulProgramarii());
            stmt.setInt(8, programare.getId());


            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Programare find(int id) {
        String sql = "SELECT * FROM programari WHERE idProgramare = ?";

        try (
                PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int idProgramare = resultSet.getInt("idProgramare");
                    int idPacient = resultSet.getInt("idPacient");
                    String nume = resultSet.getString("nume");
                    String prenume = resultSet.getString("prenume");
                    int varsta = resultSet.getInt("varsta");
                    String data = resultSet.getString("dataP");
                    String ora = resultSet.getString("ora");
                    String scop = resultSet.getString("scopProgramare");
                    Pacient pacient = new Pacient(idPacient, nume, prenume, varsta);
                    return new Programare(idProgramare, pacient, data, ora, scop);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    //@Override
//public List<Programare> getAll() {
////        ArrayList<Pacient> pacienti = new ArrayList<>();
//        ArrayList<Programare> programari = new ArrayList<>();
//        try {
//        try (PreparedStatement statement = conn.prepareStatement("SELECT * from programari"); ResultSet rs = statement.executeQuery();) {
//        while (rs.next()) {
//        Pacient p = new Pacient(rs.getInt("idPacient"), rs.getString("nume"), rs.getString("prenume"),
//        rs.getInt("varsta"));
//        pacienti.add(p);
//        }
//        }
//        } catch (SQLException ex) {
//        ex.printStackTrace();
//        }
//
//        return pacienti;
//
//        }
    @Override
    public List<Programare> getAll() {
        ArrayList<Programare> programari = new ArrayList<>();

        try {
            try (PreparedStatement statement = conn.prepareStatement("SELECT * from programari"); ResultSet rs = statement.executeQuery();) {
                while (rs.next()) {
                    int idProgramare = rs.getInt("idProgramare");
                    int idPacient = rs.getInt("idPacient");
                    String nume = rs.getString("nume");
                    String prenume = rs.getString("prenume");
                    int varsta = rs.getInt("varsta");
                    String dataP = rs.getString("dataP");
                    String ora = rs.getString("ora");
                    String scop = rs.getString("scopProgramare");

                    Pacient pacient = new Pacient(idPacient, nume, prenume, varsta);
                    Programare p = new Programare(idProgramare, pacient, dataP, ora, scop);
                    programari.add(p);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return programari;

    }


    public static void main(String[] args) {
        SQLRepositoryPacient dbPacient = new SQLRepositoryPacient();
        dbPacient.openConnection();
        dbPacient.createSchema();
        dbPacient.initTables();

        ArrayList<Pacient> pacientiList = (ArrayList<Pacient>) dbPacient.getAll();
        dbPacient.closeConnection();

        SQLRepositoryProgramare db_example = new SQLRepositoryProgramare(pacientiList);
        db_example.openConnection();
        db_example.createSchema();
        db_example.initTables();


        ArrayList<Programare> programariList = (ArrayList<Programare>) db_example.getAll();
        for (Programare programare : programariList)
            System.out.println(programare);

        db_example.closeConnection();
    }


    private Pacient randomPacient() {

        if (pacientiList != null && !pacientiList.isEmpty()) {
            Random random = new Random();
            return pacientiList.get(random.nextInt(pacientiList.size()));
        } else {
            return null;
        }
    }

    private String generatedRandomDataProgram() {
        String[] data = {"2023-11-10", "2024-02-11", "2024-01-24", "2024-07-12", "2024-05-10", "2023-12-19", "2024-05-25", "2024-02-12", "2024-11-17", "2023-12-28"};

        Random random = new Random();
        String randomData = data[random.nextInt(data.length)];

        return randomData;
    }

    private String generateRandomOra() {
        String[] ora = {"10:30", "12:40", "17:20", "14:50", "18:00", "15:11", "08:30", "11:11", "16:00", "17:00"};

        Random random = new Random();
        String randomOra = ora[random.nextInt(ora.length)];

        return randomOra;
    }

    private String generatedRandomScop() {
        String[] scop = {"consult", "carii", "aparat_dentar", "extractie", "albire"};

        Random random = new Random();
        String randomScop = scop[random.nextInt(scop.length)];

        return randomScop;
    }
}
