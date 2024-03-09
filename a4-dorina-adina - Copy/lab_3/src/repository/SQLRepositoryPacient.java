import domain.Entity;
import domain.Pacient;
import domain.Programare;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SQLRepositoryPacient extends RepoGeneric<Pacient> {
    private static final String JDBC_URL =
            "jdbc:sqlite:pacient.db";

    private Connection conn = null;

    public SQLRepositoryPacient() {
        openConnection();
        createSchema();
    }



    protected void openConnection() {
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


    protected void closeConnection() {
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    void createSchema() {
        try {
            try (final Statement stmt = conn.createStatement()) {
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS pacienti(idPacient int, nume varchar(40), prenume varchar(100), varsta int);");
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] createSchema : " + e.getMessage());
        }
    }


    void initTables() {
        try {
            try (PreparedStatement statement = conn.prepareStatement("INSERT INTO pacienti VALUES (?, ?, ?, ?)")) {
                Random random = new Random();
                for (int i = 0; i < 100; i++) {
                    int id = i + 1;
                    String nume = generateRandomNume();
                    String prenume = generateRandomPrenume();
                    int varsta = generateRandomVarsta();
                    statement.setInt(1, id);
                    statement.setString(2, nume);
                    statement.setString(3, prenume);
                    statement.setInt(4, varsta);
                    statement.executeUpdate();
                }
            }

//            try (PreparedStatement statement = conn.prepareStatement("INSERT INTO programari VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {
//                Random random = new Random();
//                for (int i = 0; i < 100; i++) {
//                    int id = i + 1;
//                    Pacient pacient = randomPacient();
//                    String dataP = generatedRandomDataProgram();
//                    String ora = generateRandomOra();
//                    String scop = generatedRandomScop();
//                    statement.setInt(1, id);
//                    statement.setInt(2, pacient.getId());
//                    statement.setString(3, pacient.getNume());
//                    statement.setString(4, pacient.getPrenume());
//                    statement.setInt(5, pacient.getVarsta());
//                    statement.setString(6, dataP);
//                    statement.setString(7, ora);
//                    statement.setString(8, scop);
//                    statement.executeUpdate();
//                }
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(Pacient p) {
        try {
            try (PreparedStatement statement = conn.prepareStatement("INSERT INTO pacienti VALUES (?, ?, ?, ?)")) {
                statement.setInt(1, p.getId());
                statement.setString(2, p.getNume());
                statement.setString(3, p.getPrenume());
                statement.setInt(4, p.getVarsta());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(int id) {
        try {
            try (PreparedStatement statement = conn.prepareStatement("DELETE FROM pacienti WHERE idPacient=?")) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Pacient pacient) {
        try (PreparedStatement stmt = conn.prepareStatement("UPDATE pacienti SET nume = ?, prenume = ?, varsta = ? WHERE idPacient = ?;")) {
//            stmt.setInt(1, pacient.getId());
            stmt.setString(1, pacient.getNume());
            stmt.setString(2, pacient.getPrenume());
            stmt.setInt(3, pacient.getVarsta());
            stmt.setInt(4, pacient.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Pacient find(int id) {
        String sql = "SELECT * FROM pacienti WHERE idPacient = ?";

        try (
                PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int idPacient = resultSet.getInt("idPacient");
                    String nume = resultSet.getString("nume");
                    String prenume = resultSet.getString("prenume");
                    int varsta = resultSet.getInt("varsta");

                    return new Pacient(idPacient, nume, prenume, varsta);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

@Override
    public List<Pacient> getAll() {
        ArrayList<Pacient> pacienti = new ArrayList<>();
        try {
            try (PreparedStatement statement = conn.prepareStatement("SELECT * from pacienti"); ResultSet rs = statement.executeQuery();) {
                while (rs.next()) {
                    Pacient p = new Pacient(rs.getInt("idPacient"), rs.getString("nume"), rs.getString("prenume"),
                            rs.getInt("varsta"));
                    pacienti.add(p);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return pacienti;

    }

//    public ArrayList<Programare> getAllProgramari() {
//        ArrayList<Programare> programari = new ArrayList<>();
//
//        try {
//            try (PreparedStatement statement = conn.prepareStatement("SELECT * from programari"); ResultSet rs = statement.executeQuery();) {
//                while (rs.next()) {
//                    int idProgramare = rs.getInt("idProgramare");
//                    int idPacient = rs.getInt("idPacient");
//                    String nume = rs.getString("nume");
//                    String prenume = rs.getString("prenume");
//                    int varsta = rs.getInt("varsta");
//                    String dataP = rs.getString("dataP");
//                    String ora = rs.getString("ora");
//                    String scop = rs.getString("scopProgramare");
//
//                    Pacient pacient = new Pacient(idPacient, nume, prenume, varsta);
//                    Programare p = new Programare(idProgramare, pacient, dataP, ora, scop);
//                    programari.add(p);
//                }
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//
//        return programari;
//
//    }

//    void updateSalesTransaction() {
//        try {
//            conn.setAutoCommit(false);
//
//            try (PreparedStatement updateSales = conn.prepareStatement("UPDATE sales SET number_of_books = ? WHERE title = ?");
//                 PreparedStatement updateTotal = conn.prepareStatement("UPDATE sales SET total = total + ? WHERE title = ?")) {
//                updateSales.setInt(1, 20);
//                updateSales.setString(2, "Open");
//                updateSales.executeUpdate();
//
//                int bookPrice = 55;
//                updateTotal.setInt(1, 20 * bookPrice);
//                updateTotal.setString(2, "Open");
//                updateTotal.executeUpdate();
//
//                conn.commit();
//                conn.setAutoCommit(true);
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//    }

    public static void main(String[] args) {
        SQLRepositoryPacient db_example = new SQLRepositoryPacient();
        db_example.openConnection();
        db_example.createSchema();
        db_example.initTables();

        ArrayList<Pacient> pacientiList = (ArrayList<Pacient>) db_example.getAll();
        for (Pacient pacient : pacientiList)
            System.out.println(pacient);


//        ArrayList<Programare> programariList = db_example.getAllProgramari();
//        for (Programare programare : programariList)
//            System.out.println(programare);

        db_example.closeConnection();
    }

    private String generateRandomPrenume() {
        String[] nume = {"Andrei", "Alexandru", "Ionut", "Gabriel", "Stefan", "Maria", "Elena", "Ioana", "Andreea", "Alexandra", "Ana", "David", "Mihai", "Florin", "Daniel", "Denisa", "Bianca", "Georgiana", "Matei", "Florina"};

        Random random = new Random();
        String randomNume = nume[random.nextInt(nume.length)];

        return randomNume;
    }

    private String generateRandomNume() {
        String[] nume = {"Pop", "Popa", "Ionescu", "Dumitru", "Stoica", "Stan", "Gheorghe", "Rusu", "Munteanu", "Matei", "Constantin", "Serban", "Moldovan", "Petrescu", "Hagi", "Stanciu", "Muresan", "Barbu", "Dragomir", "Craciun"};

        Random random = new Random();
        String randomNume = nume[random.nextInt(nume.length)];

        return randomNume;
    }

    private Integer generateRandomVarsta() {
        Integer[] varsta = {23, 40, 22, 11, 5, 34, 55, 67, 89, 12, 32, 52, 48, 17, 37, 15, 7, 9, 10, 28};

        Random random = new Random();
        Integer randomVarsta = varsta[random.nextInt(varsta.length)];

        return randomVarsta;
    }

}

