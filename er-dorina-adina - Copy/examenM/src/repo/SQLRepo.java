package repo;
import domain.Reteta;
import repo.Repo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SQLRepo extends Repo<Reteta> {
    private static final String JDBC_URL = "jdbc:sqlite:reteta.db";
    private Connection conn;

    public SQLRepo() {
        openConnection();
        createSchema();
    }

    protected void openConnection() {
        try {
            conn = DriverManager.getConnection(JDBC_URL);
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
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS retete(nume VARCHAR(40), timp INT, ingrediente TEXT)");
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] createSchema : " + e.getMessage());
        }
    }

    void initTables() {
        try {
            try (PreparedStatement statement = conn.prepareStatement("INSERT INTO retete VALUES (?, ?, ?)")) {
                Random random = new Random();
                for (int i = 0; i < 5; i++) {
                    String nume = generateRandomNume();
                    int timp = generateRandomTimp();
                    List<String> ingrediente = generateRandomIngrediente();
                    String ingredienteSerialized = serializeIngrediente(ingrediente);

                    statement.setString(1, nume);
                    statement.setInt(2, timp);
                    statement.setString(3, ingredienteSerialized);
                    statement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(Reteta p) {
        try {
            try (PreparedStatement statement = conn.prepareStatement("INSERT INTO retete VALUES (?, ?, ?)")) {
                statement.setString(1, p.getNume());
                statement.setInt(2, p.getTimpGatire());
                String ingredienteSerialized = serializeIngrediente(p.getIngrediente());
                statement.setString(3, ingredienteSerialized);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Reteta find(String nume) {
        String sql = "SELECT * FROM retete WHERE nume= ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, nume);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int timp = resultSet.getInt("timp");
                    String ingredienteSerialized = resultSet.getString("ingrediente");
                    List<String> ingrediente = deserializeIngrediente(ingredienteSerialized);

                    return new Reteta(nume, timp, ingrediente);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Reteta> getAll() {
        List<Reteta> retete = new ArrayList<>();
        try {
            try (PreparedStatement statement = conn.prepareStatement("SELECT * from retete"); ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    String nume = rs.getString("nume");
                    int timp = rs.getInt("timp");
                    String ingredienteSerialized = rs.getString("ingrediente");
                    List<String> ingrediente = deserializeIngrediente(ingredienteSerialized);

                    Reteta reteta = new Reteta(nume, timp, ingrediente);
                    retete.add(reteta);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return retete;
    }

    private String generateRandomNume() {
        String[] nume = {"Reteta1", "Reteta2", "Reteta3", "Reteta4", "Reteta5"};
        Random random = new Random();
        return nume[random.nextInt(nume.length)];
    }

    private int generateRandomTimp() {
        Random random = new Random();
        return random.nextInt(60);
    }

    private List<String> generateRandomIngrediente() {
        List<String> ingrediente = new ArrayList<>();
        String[] ingredienteArray = {"linte", "ulei de masline", "sare", "piper", "ceapa", "usturoi", "faina", "drojdie", "apa", "mozzarella", "salata", "oua", "smantana", "e" +
                "mmental"};

        Random random = new Random();
        int numIngrediente = random.nextInt(5) + 1;
        for (int i = 0; i < numIngrediente; i++) {
            int index = random.nextInt(ingredienteArray.length);
            ingrediente.add(ingredienteArray[index]);
        }
        return ingrediente;
    }

    private String serializeIngrediente(List<String> ingrediente) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String ingredient : ingrediente) {
            stringBuilder.append(ingredient).append(",");
        }
        return stringBuilder.toString();
    }

    private List<String> deserializeIngrediente(String ingredienteSerialized) {
        String[] ingredienteArray = ingredienteSerialized.split(",");
        List<String> ingrediente = new ArrayList<>();
        for (String ingredient : ingredienteArray) {
            ingrediente.add(ingredient.trim());
        }
        return ingrediente;
    }

    public static void main(String[] args) {
        SQLRepo db_example = new SQLRepo();
        db_example.initTables();

        List<Reteta> retete = db_example.getAll();
        for (Reteta reteta : retete) {
            System.out.println(reteta);
        }
    }
}
