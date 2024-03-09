import domain.Pacient;
import domain.Programare;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import repository.*;
import javafx.fxml.FXML;
import service.PacientService;
import service.ProgramareService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class HelloApplication extends Application {


    TextField textFieldIdPacient = new TextField();
    TextField textFieldNume = new TextField();
    TextField textFieldPrenume = new TextField();
    TextField textFieldVarsta = new TextField();



    TextField textFieldIdProgramare = new TextField();
    TextField textFieldIdPacientProgramare = new TextField();
    TextField textFieldDate = new TextField();
    TextField textFieldOra = new TextField();
    TextField textFieldScop = new TextField();


    ObservableList<Pacient> pacienti = FXCollections.observableArrayList();
    ListView<Pacient> pacientiListView = new ListView<>(pacienti);

    @Override
    public void start(Stage stage) throws IOException, RepositoryException {


//        RepoGeneric<Pacient> pacientRepository = new TextFileRepositoryPacient<Pacient>("Pacienti.txt");
//        RepoGeneric<Programare> programreRepository = new TextFileRepositoryProgramare<Programare>("Programari.txt");

        RepoGeneric<Pacient> pacientRepository = new SQLRepositoryPacient();
        List<Pacient> pacienti = pacientRepository.getAll();
        RepoGeneric<Programare> programreRepository = new SQLRepositoryProgramare(pacienti);

        PacientService pacientService = new PacientService(pacientRepository);
        ProgramareService programareService = new ProgramareService(programreRepository);

        VBox mainVerticalBox = new VBox();
        mainVerticalBox.setPadding(new Insets(10));;
        Scene scene = new Scene(mainVerticalBox, 800, 800);


        ObservableList<Pacient> pacientii = FXCollections.observableArrayList(pacientService.getAll());
        ListView<Pacient> pacienttListView = new ListView<>(pacientii);
        pacienttListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Pacient pacient = pacienttListView.getSelectionModel().getSelectedItem();
                textFieldIdPacient.setText(Integer.toString(pacient.getId()));
                textFieldNume.setText(pacient.getNume());
                textFieldPrenume.setText(pacient.getPrenume());
                textFieldVarsta.setText(Integer.toString(pacient.getVarsta()));
            }
        }
        );
        pacienttListView.setPadding(new Insets(10));
        mainVerticalBox.getChildren().add(pacienttListView);


        ObservableList<Programare> programari = FXCollections.observableArrayList(programareService.getAll());
        ListView<Programare> programareListView = new ListView<>(programari);
        programareListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Programare programare = programareListView.getSelectionModel().getSelectedItem();
                Pacient pacient = pacienttListView.getSelectionModel().getSelectedItem();
                textFieldIdProgramare.setText(Integer.toString(programare.getId()));
                textFieldIdPacientProgramare.setText(Integer.toString(pacient.getId()));
//                textFieldNume.setText(pacient.getNume());
//                textFieldPrenume.setText(pacient.getPrenume());
//                textFieldVarsta.setText(Integer.toString(pacient.getVarsta()));
                textFieldDate.setText(programare.getData());
                textFieldOra.setText(programare.getOra());
                textFieldScop.setText(programare.getScopulProgramarii());
//                pacienti.setAll(programare.getPacient());
                pacienti.add(programare.getPacient());
            }
        });
        programareListView.setPadding(new Insets(10));
        mainVerticalBox.getChildren().add(programareListView);



        GridPane gridPanePacient = new GridPane();
        Label labelIdPacient = new Label("ID_Pacient: ");
        labelIdPacient.setPadding(new Insets(10,0,10,0));

        Label labelNume = new Label("Nume: ");
        labelNume.setPadding(new Insets(10,0,10,0));

        Label labelPrenume = new Label("Prenume: ");
        labelPrenume.setPadding(new Insets(10,0,10,0));

        Label labelVarsta = new Label("Varsta: ");
        labelVarsta.setPadding(new Insets(10,0,10,0));


        gridPanePacient.add(labelIdPacient, 0, 0);
        gridPanePacient.add(labelNume, 0, 1);
        gridPanePacient.add(labelPrenume, 0, 2);
        gridPanePacient.add(labelVarsta, 0, 3);

        gridPanePacient.add(textFieldIdPacient, 1, 0);
        gridPanePacient.add(textFieldNume, 1, 1);
        gridPanePacient.add(textFieldPrenume, 1, 2);
        gridPanePacient.add(textFieldVarsta, 1, 3);


        HBox buttonsPacientHorizontal = new HBox();

        Button buttonAddPacient = new Button("Add Pacient");
        buttonAddPacient.setPadding(new Insets(5));
        buttonsPacientHorizontal.getChildren().add(buttonAddPacient);

        Button buttonUpdatePacient = new Button("Update Pacient");
        buttonUpdatePacient.setPadding(new Insets(5));
        buttonsPacientHorizontal.getChildren().add(buttonUpdatePacient);

        Button buttonDeletePacient = new Button("Delete Pacient");
        buttonDeletePacient.setPadding(new Insets(5));

        buttonsPacientHorizontal.getChildren().add(buttonDeletePacient);


        mainVerticalBox.getChildren().add(gridPanePacient);
        mainVerticalBox.getChildren().add(buttonsPacientHorizontal);


        buttonAddPacient.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    int id = Integer.parseInt(textFieldIdPacient.getText());
                    String nume = textFieldNume.getText();
                    String prenume = textFieldPrenume.getText();
                    int varsta = Integer.parseInt(textFieldVarsta.getText());

                    Pacient pacient = new Pacient(id, nume, prenume, varsta);
                    pacientService.addPacient(pacient);
                    pacienti.add(pacientService.findPacient(id));


                }catch (Exception e)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText(e.getMessage());
                    alert.show();

                }
            }
        });

        buttonUpdatePacient.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    int id = Integer.parseInt(textFieldIdPacient.getText());
                    String nume = textFieldNume.getText();
                    String prenume = textFieldPrenume.getText();
                    int varsta = Integer.parseInt(textFieldVarsta.getText());

                    Pacient pacient = new Pacient(id, nume, prenume, varsta);
                    pacientService.updatePacient(pacient);
//                    pacienti.setAll(pacientService.getAll());
                    pacienti.addAll(pacientService.getAll());

                }catch (Exception e)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText(e.getMessage());
                    alert.show();

                }
            }
        });

        buttonDeletePacient.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    int id = Integer.parseInt(textFieldIdPacient.getText());
                    pacientService.removePacient(id);
//                    pacienti.setAll(pacientService.getAll());
                    pacienti.addAll(pacientService.getAll());

                }catch (Exception e)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText(e.getMessage());
                    alert.show();

                }
            }
        });

        GridPane gridPaneProgramare = new GridPane();
        Label labelIdProgramare = new Label("ID_Programare: ");
        labelIdProgramare.setPadding(new Insets(10,0,10,0));

        Label labelIdPacient1 = new Label("ID_Pacient: ");
        labelIdPacient1.setPadding(new Insets(10,0,10,0));

//        Label labelNume1 = new Label("Nume: ");
//        labelNume1.setPadding(new Insets(10,0,10,0));
//
//        Label labelPrenume1 = new Label("Prenume: ");
//        labelPrenume1.setPadding(new Insets(10,0,10,0));
//
//        Label labelVarsta1 = new Label("Varsta: ");
//        labelVarsta1.setPadding(new Insets(10,0,10,0));


        Label labelDate = new Label("Data: ");
        labelDate.setPadding(new Insets(10,0,10,0));


        Label labelOra = new Label("Ora: ");
        labelOra.setPadding(new Insets(10,0,10,0));

        Label labelScop = new Label("Scop: ");
        labelScop.setPadding(new Insets(10,0,10,0));


        gridPaneProgramare.add(labelIdProgramare, 0, 0);
        gridPaneProgramare.add(labelIdPacient1, 0, 1);
//        gridPaneProgramare.add(labelNume, 0, 2);
//        gridPaneProgramare.add(labelPrenume, 0, 3);
//        gridPaneProgramare.add(labelVarsta, 0, 4);

        gridPaneProgramare.add(labelDate, 0, 2);
        gridPaneProgramare.add(labelOra, 0, 3);
        gridPaneProgramare.add(labelScop, 0, 4);

        gridPaneProgramare.add(textFieldIdProgramare, 1, 0);
        gridPaneProgramare.add(textFieldIdPacientProgramare, 1, 1);
//        gridPaneProgramare.add(textFieldNume, 1, 2);
//        gridPaneProgramare.add(textFieldPrenume, 1, 3);
//        gridPaneProgramare.add(textFieldVarsta, 1, 4);
        gridPaneProgramare.add(textFieldDate, 1, 2);
        gridPaneProgramare.add(textFieldOra, 1, 3);
        gridPaneProgramare.add(textFieldScop, 1,4);



        HBox buttonsProgramariHorizontal = new HBox();
        buttonsProgramariHorizontal.setPadding(new Insets(10));

        Button buttonAddProgramare = new Button("Add Programare");
        buttonAddProgramare.setPadding(new Insets(5));

        Button buttonUpdateProgramare = new Button("Update Programare");
        buttonUpdateProgramare.setPadding(new Insets(5));

        Button buttonDeleteProgramare = new Button("Delete Programare");
        buttonDeleteProgramare.setPadding(new Insets(5));



        buttonsProgramariHorizontal.getChildren().add(buttonAddProgramare);
        buttonsProgramariHorizontal.getChildren().add(buttonUpdateProgramare);
        buttonsProgramariHorizontal.getChildren().add(buttonDeleteProgramare);

        mainVerticalBox.getChildren().add(gridPaneProgramare);
        mainVerticalBox.getChildren().add(buttonsProgramariHorizontal);


        buttonAddProgramare.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    int id = Integer.parseInt(textFieldIdProgramare.getText());

                    int idP = Integer.parseInt(textFieldIdPacientProgramare.getText());
//                    String nume = textFieldNume.getText();
//                    String prenume = textFieldPrenume.getText();
//                    int varsta = Integer.parseInt(textFieldVarsta.getText());

                    Pacient pacient;
                    pacient = pacientService.findPacient(idP);
                    String date = textFieldDate.getText();
                    String ora = textFieldOra.getText();
                    String scop = textFieldScop.getText();
                    Programare programare = new Programare(id, pacient, date, ora, scop);
                    programareService.addProgramare(programare);
                    programari.add(programareService.findProgramare(id));
                    pacienti.clear();

                }catch (Exception e)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText(e.getMessage());
                    alert.show();

                }
            }
        });

        buttonUpdateProgramare.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    int id = Integer.parseInt(textFieldIdProgramare.getText());

                    int idP = Integer.parseInt(textFieldIdPacientProgramare.getText());
//                    String nume = textFieldNume.getText();
//                    String prenume = textFieldPrenume.getText();
//                    int varsta = Integer.parseInt(textFieldVarsta.getText());

//                    Pacient pacient = new Pacient(idP, nume, prenume, varsta);
                    Pacient pacient;
                    pacient = pacientService.findPacient(idP);

                    String date = textFieldDate.getText();
                    String ora = textFieldOra.getText();
                    String scop = textFieldScop.getText();
                    Programare programare = new Programare(id, pacient, date, ora, scop);
                    programareService.updateProgramare(programare);
                    programari.setAll(programareService.getAll());

                }catch (Exception e)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText(e.getMessage());
                    alert.show();

                }
            }
        });

        buttonDeleteProgramare.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    int id = Integer.parseInt(textFieldIdProgramare.getText());
                    programareService.removeProgramare(id);
                    programari.setAll(programareService.getAll());

                }catch (Exception e)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText(e.getMessage());
                    alert.show();

                }
            }
        });

        ListView resultArea = new ListView();
        resultArea.setPadding(new Insets(10));

        Button nrProgramariPerPacient = new Button("Numar programari per pacient");
        nrProgramariPerPacient.setPadding(new Insets(5));
        nrProgramariPerPacient.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    mainVerticalBox.getChildren().remove(resultArea);
                    resultArea.getItems().clear();
                    programareService.getAll().stream()
                            .collect(Collectors.groupingBy(Programare::getPacient, LinkedHashMap::new, Collectors.counting()))
                            .entrySet()
                            .stream()
                            .sorted(Map.Entry.<Pacient, Long>comparingByValue().reversed())
                            .forEach(pacient -> resultArea.getItems().add(pacient.getKey() + " --- numar programari: " + pacient.getValue()));
                    mainVerticalBox.getChildren().add(resultArea);
                }catch (Exception e)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText(e.getMessage());
                    alert.show();

                }
            }
        });

        Button nrProgramariPerLuna = new Button("Numar programari per luna");
        nrProgramariPerLuna.setPadding(new Insets(5));
        nrProgramariPerLuna.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    mainVerticalBox.getChildren().remove(resultArea);
                    resultArea.getItems().clear();
                    Collection<Programare> programari = programareService.getAll();
                    Map<String, Long> programariPerMonth = programari.stream()
                            .filter(p -> p.getData() != null)
                            .collect(Collectors.groupingBy(
                                    p -> LocalDate.parse(p.getData()).getMonth().toString(),
                                    Collectors.counting()
                            ));

                    EnumMap<Month, Long> result = new EnumMap<>(Month.class);
                    for (Month month : Month.values()) {
                        result.put(month, programariPerMonth.getOrDefault(month.toString(), 0L));
                    }

                    StringBuilder output = new StringBuilder();
                    result.entrySet().stream()
                            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                            .forEach(entry -> {
                                output.append(entry.getKey()).append(": ").append(entry.getValue()).append(" programari\n");
                                resultArea.getItems().add(entry.getKey() + ": " + entry.getValue() + " programari");
                            });
                    mainVerticalBox.getChildren().add(resultArea);
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText(e.getMessage());
                    alert.show();

                }
            }
            });

        Button raportZileTrecute = new Button("Nr zile trecute de la ultima programare");
        raportZileTrecute.setPadding(new Insets(5));
        raportZileTrecute.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    mainVerticalBox.getChildren().remove(resultArea);
                    resultArea.getItems().clear();
                    Collection<Programare> programari = programareService.getAll();
                    programari.stream()
                            .collect(Collectors.groupingBy(
                                    p -> p.getPacient().getNume() + " " + p.getPacient().getPrenume(),
                                    Collectors.collectingAndThen(
                                            Collectors.reducing((Programare p1, Programare p2) ->
                                                    p1.getData().compareTo(p2.getData()) >= 0 ? p1 : p2),
                                            (Optional<Programare> lastAppointment) ->
                                                    lastAppointment.map(appointment -> {
                                                        long zileTrecute = ChronoUnit.DAYS.between(LocalDate.parse(appointment.getData()), LocalDate.now());
                                                        Map<String, Object> resultMap = new HashMap<>();
                                                        resultMap.put("zileTrecute", zileTrecute);
                                                        resultMap.put("dataUltimaProgramare", appointment.getData());
                                                        return resultMap;
                                                    }).orElseGet(() -> {
                                                        Map<String, Object> resultMap = new HashMap<>();
                                                        resultMap.put("zileTrecute", 0L);
                                                        resultMap.put("dataUltimaProgramare", null);  // You can modify this to a default value if needed
                                                        return resultMap;})
                                    )
                                    ))
                                    .forEach((pacient, resultMap) ->
                                         resultArea.getItems().add(pacient + ": " + resultMap.get("zileTrecute") + " zile trecute, Data ultimei programari: " + resultMap.get("dataUltimaProgramare"))
                    );
                            mainVerticalBox.getChildren().add(resultArea);
                }catch (Exception e)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText(e.getMessage());
                    alert.show();

                }
            }
        });


        Button celeMaiAglomerateLuni = new Button("Cele mai aglomerate luni");
        celeMaiAglomerateLuni.setPadding(new Insets(5));
        celeMaiAglomerateLuni.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    mainVerticalBox.getChildren().remove(resultArea);
                    resultArea.getItems().clear();
                    Collection<Programare> orders = programareService.getAll();
                    programari.stream()
                            .map(p -> LocalDate.parse(p.getData()))
                            .collect(Collectors.groupingBy(
                                    LocalDate::getMonth,
                                    Collectors.counting()
                            ))
                            .entrySet().stream()
                            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                            .forEach(monthEntry -> resultArea.getItems().add(monthEntry.getKey() + ": " + monthEntry.getValue() + " programari"));
                    mainVerticalBox.getChildren().add(resultArea);
                }catch (Exception e)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText(e.getMessage());
                    alert.show();

                }
            }
        });



        GridPane gridPaneReports = new GridPane();
        gridPaneReports.add(nrProgramariPerPacient, 0, 0);
        gridPaneReports.add(nrProgramariPerLuna, 1, 0);
        gridPaneReports.add(raportZileTrecute, 2, 0);
        gridPaneReports.add(celeMaiAglomerateLuni, 3, 0);

        mainVerticalBox.getChildren().add(resultArea);
        mainVerticalBox.getChildren().add(gridPaneReports);


        stage.setTitle("Pacienti si programari");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
