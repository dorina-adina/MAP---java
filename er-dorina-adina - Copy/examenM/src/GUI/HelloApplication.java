package GUI;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import domain.Reteta;
import repo.Repo;
import repo.SQLRepo;
import service.Service;

public class HelloApplication extends Application {

    TextField textFieldNume = new TextField();
    TextField textFieldTimp = new TextField();
    TextArea textAreaIngrediente = new TextArea();

    ObservableList<Reteta> retete = FXCollections.observableArrayList();
    ListView<Reteta> reteteListView = new ListView<>(retete);

    @Override
    public void start(Stage stage) {
        Repo<Reteta> retetaRepository = new SQLRepo();
        Service<Reteta> retetaService = new Service<>(retetaRepository);

        VBox mainVerticalBox = new VBox();
        mainVerticalBox.setPadding(new Insets(10));
        Scene scene = new Scene(mainVerticalBox, 800, 800);

        retete.addAll(retetaService.getAll());
        reteteListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Reteta reteta = reteteListView.getSelectionModel().getSelectedItem();
                if (reteta != null) {
                    textFieldNume.setText(reteta.getNume());
                    textFieldTimp.setText(String.valueOf(reteta.getTimpGatire()));
                    textAreaIngrediente.setText(String.join("\n", reteta.getIngrediente()));
                } else {
                    textFieldNume.clear();
                    textFieldTimp.clear();
                    textAreaIngrediente.clear();
                }
            }
        });
        Comparator<Reteta> retetaComparator = new Comparator<Reteta>() {
            @Override
            public int compare(Reteta r1, Reteta r2) {
                int comparatieIngrediente = Integer.compare(r1.getIngrediente().size(), r2.getIngrediente().size());
                if (comparatieIngrediente != 0) {
                    return comparatieIngrediente;
                }
                return r1.getNume().compareTo(r2.getNume());
            }
        };

        retete.sort(retetaComparator);
        reteteListView.setPadding(new Insets(10));
        mainVerticalBox.getChildren().add(reteteListView);

        GridPane gridPaneReteta = new GridPane();
        Label labelNume = new Label("Nume: ");
        labelNume.setPadding(new Insets(10, 0, 10, 0));

        Label labelTimp = new Label("Timp de gatire: ");
        labelTimp.setPadding(new Insets(10, 0, 10, 0));

        Label labelIngrediente = new Label("Ingrediente: ");
        labelIngrediente.setPadding(new Insets(10, 0, 10, 0));

        gridPaneReteta.add(labelNume, 0, 0);
        gridPaneReteta.add(labelTimp, 0, 1);
        gridPaneReteta.add(labelIngrediente, 0, 2);

        gridPaneReteta.add(textFieldNume, 1, 0);
        gridPaneReteta.add(textFieldTimp, 1, 1);
        gridPaneReteta.add(textAreaIngrediente, 1, 2);

        HBox buttonsRetetaHorizontal = new HBox();

        Button buttonAddReteta = new Button("Add");
        buttonAddReteta.setPadding(new Insets(5));
        buttonsRetetaHorizontal.getChildren().add(buttonAddReteta);



        mainVerticalBox.getChildren().add(gridPaneReteta);
        mainVerticalBox.getChildren().add(buttonsRetetaHorizontal);

        buttonAddReteta.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    String nume = textFieldNume.getText();
                    int timp = Integer.parseInt(textFieldTimp.getText());
                    List<String> ingrediente = List.of(textAreaIngrediente.getText().split("\\n"));
                    Reteta reteta = new Reteta(nume, timp, ingrediente);
                    retetaService.add(reteta);
                    retete.add(reteta);
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText(e.getMessage());
                    alert.show();
                }
            }
        });


        Button buttonFiltrare = new Button("Filtrare");

        TextField textFieldFiltru = new TextField();

        Button buttonListaCumparaturi = new Button("Creare Lista Cumparaturi");

        mainVerticalBox.getChildren().addAll(buttonFiltrare, textFieldFiltru, buttonListaCumparaturi);

        reteteListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        textFieldFiltru.setOnAction(event -> {
            String[] ingredienteFiltru = textFieldFiltru.getText().split("; ");
            FilteredList<Reteta> filteredList = retete.filtered(reteta ->
                    Arrays.stream(ingredienteFiltru).allMatch(reteta.getIngrediente()::contains));
            reteteListView.setItems(filteredList);
        });

        buttonListaCumparaturi.setOnAction(event -> {
            ObservableList<Reteta> selectedItems = reteteListView.getSelectionModel().getSelectedItems();
            List<String> listaCumparaturi = selectedItems.stream()
                    .flatMap(reteta -> reteta.getIngrediente().stream())
                    .distinct()
                    .collect(Collectors.toList());
            ListView<String> listaCumparaturiView = new ListView<>(FXCollections.observableArrayList(listaCumparaturi));
            mainVerticalBox.getChildren().add(listaCumparaturiView);
        });



        stage.setTitle("Retete");
        stage.setScene(scene);
        stage.show();
    }



    public static void main(String[] args) {
        launch();
    }
}
