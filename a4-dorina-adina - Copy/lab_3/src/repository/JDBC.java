//package repository;
//
//import domain.Entity;
//import org.sqlite.SQLiteDataSource;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.Random;
//
////class Pacient{
////    protected int id;
////    private final String nume;
////    private final String prenume;
////    private final int varsta;
////
////    public Pacient(int id,String nume, String prenume, int varsta) {
////        this.id = id;
////        this.nume = nume;
////        this.prenume = prenume;
////        this.varsta = varsta;
////    }
////
////    @Override
////    public String toString() {
////        return "Pacient{" +
////                "id_pacient=" + id + '\'' +
////                "nume=" + nume + '\'' +
////                ", prenume=" + prenume + '\'' +
////                ", varsta=" + varsta +
////                '}';
////    }
////
////    public int getId(){
////        return id;
////    }
////
////    public String getNume() {
////        return nume;
////    }
////
////    public int getVarsta() {
////        return varsta;
////    }
////
////    public String getPrenume() {
////        return prenume;
////    }
////}
////
////class Programare extends Entity{
////
////    protected int idP;
////    private final String data;
////    private final String ora;
////    private final String scopProgramare;
////    private final Pacient pacient;
////
////    public Programare(int idP, Pacient pacient, String data, String ora, String scopProgramare) {
////        super(idP);
////        this.pacient = pacient;
////        this.data = data;
////        this.ora = ora;
////        this.scopProgramare = scopProgramare;
////    }
////
////    @Override
////    public String toString() {
////        return "Programare{" +
////                "id_programare=" + idP + '\'' +
////                "pacient=" + pacient + '\'' +
////                ", data=" + data + '\'' +
////                ", ora=" + ora + '\'' +
////                ", scop=" + scopProgramare +
////                '}';
////    }
////
////    public int getId(){
////        return idP;
////    }
////
////    public Pacient getPacient(){
////        return pacient;
////    }
////
////    public String getData() {
////        return data;
////    }
////
////    public String getScopulProgramarii() {
////        return scopProgramare;
////    }
////
////    public String getOra() {
////        return ora;
////    }
////}
//
//
//public class JDBC {
//
//    private static final String JDBC_URL =
//            "jdbc:sqlite:test.db";
//
//    private Connection conn = null;
//
//    /**
//     * Gets a connection to the database.
//     * If the underlying connection is closed, it creates a new connection. Otherwise, the current instance is returned.
//     */
//    void openConnection() {
//        try {
//            // with DriverManager
////            if (conn == null || conn.isClosed())
////                conn = DriverManager.getConnection(JDBC_URL);
//
//            // with DataSource
//            SQLiteDataSource ds = new SQLiteDataSource();
//            ds.setUrl(JDBC_URL);
//            if (conn == null || conn.isClosed())
//                conn = ds.getConnection();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * Closes the underlying connection to the in-memory SQLite instance.
//     */
//    private void closeConnection() {
//        try {
//            if (conn != null)
//                conn.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * Creates the sample schema for the database.
//     */
//    void createSchema() {
//        try {
//            try (final Statement stmt = conn.createStatement()) {
//                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS pacienti(idPacient int, nume varchar(40), prenume varchar(100), varsta int);");
//                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS programari(idProgramare int, pacient Pacient, dataP varchar(20), ora varchar(10), scopProgramare varchar(40));");
//            }
//        } catch (SQLException e) {
//            System.err.println("[ERROR] createSchema : " + e.getMessage());
//        }
//    }
//
//    /**
//     * Adds some entries in the table books;
//     */
//    void initTables() {
////        final String[] books = new String[]{
////                "Heather Morris|The Tattooist of Auschwitz|250",
////                "Andre Agassi|Open|300",
////                "Aaron Courville, Ian Goodfellow, Yoshua Bengio|Deep Learning|700"
////        };
//
//        try {
//            try (PreparedStatement statement = conn.prepareStatement("INSERT INTO pacienti VALUES (?, ?, ?, ?)")) {
//                Random random = new Random();
//                for (int i = 0; i < 100; i++) {
//                    int id = i + 1;
//                    String nume = generateRandomNume();
//                    String prenume = generateRandomPrenume();
//                    int varsta = generateRandomVarsta();
//                    statement.setInt(1, id);
//                    statement.setString(2, nume);
//                    statement.setString(3, prenume);
//                    statement.setInt(4, varsta);
//                    statement.executeUpdate();
//                }
//            }
//
////            try (PreparedStatement statement = conn.prepareStatement("INSERT INTO sales VALUES (?, ?, ?)")) {
////                statement.setString(1, "Open");
////                statement.setInt(2, 0);
////                statement.setInt(3, 0);
////                statement.executeUpdate();
////            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * Add the given book to the database.
//     */
////    void addPacient(Pacient p) {
////        try {
////            try (PreparedStatement statement = conn.prepareStatement("INSERT INTO pacienti VALUES (?, ?, ?)")) {
////                statement.setString(1, b.getNume());
////                statement.setString(2, b.getPrenume());
////                statement.setInt(3, b.getVarsta());
////                statement.executeUpdate();
////            }
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
////    }
//
////    /**
////     * Remove the book with the given title from table books.
////     *
////     * @param title
////     */
////    void removeBookByTitle(String title) {
////        try {
////            try (PreparedStatement statement = conn.prepareStatement("DELETE FROM books WHERE title=?")) {
////                statement.setString(1, title);
////                statement.executeUpdate();
////            }
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
////    }
//
//    /**
//     * Get all book from table books in the database.
//     *
//     * @return: an ArrayList with Book objects.
//     */
//    ArrayList<Pacient> getAll() {
//        ArrayList<Pacient> pacienti = new ArrayList<>();
//
//        try {
//            try (PreparedStatement statement = conn.prepareStatement("SELECT * from pacienti"); ResultSet rs = statement.executeQuery();) {
//                while (rs.next()) {
//                    Pacient p = new Pacient(rs.getInt("idPacient"), rs.getString("nume"), rs.getString("prenume"),
//                            rs.getInt("varsta"));
//                    pacienti.add(p);
//                }
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//
//        return pacienti;
//    }
//
////    void updateSalesTransaction() {
////        try {
////            conn.setAutoCommit(false);
////
////            try (PreparedStatement updateSales = conn.prepareStatement("UPDATE sales SET number_of_books = ? WHERE title = ?");
////                 PreparedStatement updateTotal = conn.prepareStatement("UPDATE sales SET total = total + ? WHERE title = ?")) {
////                updateSales.setInt(1, 20);
////                updateSales.setString(2, "Open");
////                updateSales.executeUpdate();
////
////                int bookPrice = 55;
////                updateTotal.setInt(1, 20 * bookPrice);
////                updateTotal.setString(2, "Open");
////                updateTotal.executeUpdate();
////
////                conn.commit();
////                conn.setAutoCommit(true);
////            }
////        } catch (SQLException ex) {
////            ex.printStackTrace();
////        }
////    }
//
//    public static void main(String[] args) {
//        JDBC db_example = new JDBC();
//        db_example.openConnection();
//        db_example.createSchema();
//        db_example.initTables();
//
////        Book b = new Book("Jordan Ellenberg", "How not to be wrong. The hidden maths of everday life", 470);
////        db_example.addBook(b);
////
////        db_example.removeBookByTitle("Deep Learning");
////
////        db_example.updateSalesTransaction();
//
//        ArrayList<Pacient> pacientiList = db_example.getAll();
//        for (Pacient pacient : pacientiList)
//            System.out.println(pacient);
//
//        db_example.closeConnection();
//    }
//    private String generateRandomPrenume() {
////        String[] names = {"John", "Jane", "Bob", "Alice", "Charlie", "Eve", "David", "Grace"};
//        String[] nume = {"Andrei", "Alexandru", "Ionut", "Gabriel", "Stefan", "Maria", "Elena", "Ioana", "Andreea", "Alexandra", "Ana", "David", "Mihai", "Florin", "Daniel", "Denisa", "Bianca", "Georgiana", "Matei", "Florina"};
//
//        Random random = new Random();
//        String randomNume = nume[random.nextInt(nume.length)];
//
//        return randomNume;
//    }
//
//    private String generateRandomNume() {
////        String[] names = {"John", "Jane", "Bob", "Alice", "Charlie", "Eve", "David", "Grace"};
//        String[] nume = {"Pop", "Popa", "Ionescu", "Dumitru", "Stoica", "Stan", "Gheorghe", "Rusu", "Munteanu", "Matei", "Constantin", "Serban", "Moldovan", "Petrescu", "Hagi", "Stanciu", "Muresan", "Barbu", "Dragomir", "Craciun"};
//
//        Random random = new Random();
//        String randomNume = nume[random.nextInt(nume.length)];
//
//        return randomNume;
//    }
//
//    private Integer generateRandomVarsta() {
////        String[] names = {"John", "Jane", "Bob", "Alice", "Charlie", "Eve", "David", "Grace"};
//        Integer[] varsta = {23, 40, 22, 11, 5, 34, 55, 67, 89, 12, 32, 52, 48, 17, 37, 15, 7, 9, 10, 28};
//
//        Random random = new Random();
//        Integer randomVarsta = varsta[random.nextInt(varsta.length)];
//
//        return randomVarsta;
//    }
//}
//
//
//
//
//
//
//
//
////package repository;
////
////import domain.Pacient;
////import org.sqlite.SQLiteDataSource;
////
////import java.sql.*;
////import java.util.ArrayList;
////
////public class JDBC {
////
////        private static final String JDBC_URL =
////                "jdbc:sqlite:test.db";
////
////        private Connection conn = null;
////
////        /**
////         * Gets a connection to the database.
////         * If the underlying connection is closed, it creates a new connection. Otherwise, the current instance is returned.
////         */
////        private void openConnection() {
////            try {
////                // with DriverManager
//////            if (conn == null || conn.isClosed())
//////                conn = DriverManager.getConnection(JDBC_URL);
////
////                // with DataSource
////                SQLiteDataSource ds = new SQLiteDataSource();
////                ds.setUrl(JDBC_URL);
////                if (conn == null || conn.isClosed())
////                    conn = ds.getConnection();
////            } catch (SQLException e) {
////                e.printStackTrace();
////            }
////        }
////
////        /**
////         * Closes the underlying connection to the in-memory SQLite instance.
////         */
////        private void closeConnection() {
////            try {
////                if (conn != null)
////                    conn.close();
////            } catch (SQLException e) {
////                e.printStackTrace();
////            }
////        }
////
////        /**
////         * Creates the sample schema for the database.
////         */
////        void createSchema() {
////            try {
////                try (final Statement stmt = conn.createStatement()) {
////                    stmt.executeUpdate("CREATE TABLE IF NOT EXISTS patients(id int, nume String, prenume String, varsta int);");
////                    stmt.executeUpdate("CREATE TABLE IF NOT EXISTS programari(id int, idPacient int, numePacient String, prenumePacient String, varstaPacient String, dataProgramarii String, ora String, scopul_programarii String);");
////                }
////            } catch (SQLException e) {
////                System.err.println("[ERROR] createSchema : " + e.getMessage());
////            }
////        }
////
////        /**
////         * Adds some entries in the table books;
////         */
////        void initTables() {
////            final String[] books = new String[]{
////                    "1,Rus,Bogdan,15",
////                    "2,Marcu,Andrei,20"
////            };
////
////            try {
////                try (PreparedStatement statement = conn.prepareStatement("INSERT INTO books VALUES (?, ?, ?, ?)")) {
////                    for (String s : books) {
////                        String[] tokens = s.split("[,]");
////                        statement.setInt(1, Integer.parseInt(tokens[0]));
////                        statement.setString(2, tokens[1]);
////                        statement.setString(3, tokens[2]);
////                        statement.setInt(4, Integer.parseInt(tokens[3]));
////                        statement.executeUpdate();
////                    }
////                }
////
////                try (PreparedStatement statement = conn.prepareStatement("INSERT INTO sales VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {
////                    statement.setString(1, "Open");
////                    statement.setInt(2, 0);
////                    statement.setInt(3, 0);
////                    statement.executeUpdate();
////                }
////            } catch (SQLException e) {
////                e.printStackTrace();
////            }
////        }
////
////        /**
////         * Add the given book to the database.
////         */
////        void addPacient(Pacient p) {
////            try {
////                try (PreparedStatement statement = conn.prepareStatement("INSERT INTO pacients VALUES (?, ?, ?, ?)")) {
////                    statement.setInt(1, p.getId());
////                    statement.setString(2, p.getNume());
////                    statement.setString(3, p.getPrenume());
////                    statement.setInt(3, p.getVarsta());
////                    statement.executeUpdate();
////                }
////            } catch (SQLException e) {
////                e.printStackTrace();
////            }
////        }
////
////        /**
////         * Remove the book with the given title from table books.
////         *
////         * @param id
////         */
////        void removePacientById(int id) {
////            try {
////                try (PreparedStatement statement = conn.prepareStatement("DELETE FROM pacients WHERE id=?")) {
////                    statement.setInt(1, id);
////                    statement.executeUpdate();
////                }
////            } catch (SQLException e) {
////                e.printStackTrace();
////            }
////        }
////
////        /**
////         * Get all book from table books in the database.
////         *
////         * @return: an ArrayList with Book objects.
////         */
////        ArrayList<Pacient> getAll() {
////            ArrayList<Pacient> pacienti = new ArrayList<>();
////
////            try {
////                try (PreparedStatement statement = conn.prepareStatement("SELECT * from pacients"); ResultSet rs = statement.executeQuery();) {
////                    while (rs.next()) {
////                        Pacient pacient = new Book(rs.getString("authors"), rs.getString("title"),
////                                rs.getInt("pages"));
////                        books.add(b);
////                    }
////                }
////            } catch (SQLException ex) {
////                ex.printStackTrace();
////            }
////
////            return books;
////        }
////
////        void updateSalesTransaction() {
////            try {
////                conn.setAutoCommit(false);
////
////                try (PreparedStatement updateSales = conn.prepareStatement("UPDATE sales SET number_of_books = ? WHERE title = ?");
////                     PreparedStatement updateTotal = conn.prepareStatement("UPDATE sales SET total = total + ? WHERE title = ?")) {
////                    updateSales.setInt(1, 20);
////                    updateSales.setString(2, "Open");
////                    updateSales.executeUpdate();
////
////                    int bookPrice = 55;
////                    updateTotal.setInt(1, 20 * bookPrice);
////                    updateTotal.setString(2, "Open");
////                    updateTotal.executeUpdate();
////
////                    conn.commit();
////                    conn.setAutoCommit(true);
////                }
////            } catch (SQLException ex) {
////                ex.printStackTrace();
////            }
////        }
////}
