package agh.ics.proj1.gui;

import agh.ics.proj1.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {
    private final Config config = new Config()
            .mapDimensions(10, 10)
            .startingGrass(20)
            .startingAnimals(3)
            .animalInitialEnergy(10)
            .animalMoveEnergyCost(1)
            .dailyGrass(2);
    private final TextField simulationStepDelay = new TextField(config.simulationStepDelay + "");
    private final TextField mapWidth = new TextField(config.mapWidth + "");
    private final TextField mapHeight = new TextField(config.mapHeight + "");
    private final TextField grassEnergy = new TextField(config.grassEnergy + "");
    private final TextField animalInitialEnergy = new TextField(config.animalInitialEnergy + "");
    private final TextField animalMoveEnergyCost = new TextField(config.animalMoveEnergyCost + "");
    private final TextField genomeLength = new TextField(config.genomeLength + "");
    private final TextField energyToBreed = new TextField(config.energyToBreed + "");
    private final TextField startingGrass = new TextField(config.startingGrass + "");
    private final TextField startingAnimals = new TextField(config.startingAnimals + "");
    private final TextField dailyGrass = new TextField(config.dailyGrass + "");
    private final ChoiceBox<MapVariant> mapVariant = new ChoiceBox<>(FXCollections.observableArrayList(MapVariant.GLOBE,
            MapVariant.HELL_BORDER));
    private final ChoiceBox<GrassVariant> grassVariant = new ChoiceBox<>(FXCollections.observableArrayList(GrassVariant.GREEN_EQUATOR,
            GrassVariant.TOXIC_CORPSES));
    private final ChoiceBox<GeneticVariant> geneticVariant = new ChoiceBox<>(FXCollections.observableArrayList(GeneticVariant.TOTAL_RANDOMNESS,
            GeneticVariant.SMALL_CORRECTION));
    private final ChoiceBox<MoveVariant> moveVariant = new ChoiceBox<>(FXCollections.observableArrayList(MoveVariant.TOTAL_DETERMINISM,
            MoveVariant.A_BIT_CRAZY));

    @Override
    public void init() {
        mapVariant.setValue(config.mapVariant);
        grassVariant.setValue(config.grassVariant);
        geneticVariant.setValue(config.geneticVariant);
        moveVariant.setValue(config.moveVariant);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox vbox = new VBox();

        GridPane gridPane = new GridPane();
        gridPane.add(new Label("Czas trwania 1 tury (ms)"), 0, 0);
        gridPane.add(simulationStepDelay, 1, 0);
        gridPane.add(new Label("Szerokość mapy"), 0, 1);
        gridPane.add(mapWidth, 1, 1);
        gridPane.add(new Label("Wysokość mapy"), 0, 2);
        gridPane.add(mapHeight, 1, 2);
        gridPane.add(new Label("Energia, którą daje trawa"), 0, 3);
        gridPane.add(grassEnergy, 1, 3);
        gridPane.add(new Label("Początkowa energia zwierzaka"), 0, 4);
        gridPane.add(animalInitialEnergy, 1, 4);
        gridPane.add(new Label("Energia potrzebna do poruszania się"), 0, 5);
        gridPane.add(animalMoveEnergyCost, 1, 5);
        gridPane.add(new Label("Energia potrzebna do rozmnożenia"), 0, 6);
        gridPane.add(energyToBreed, 1, 6);
        gridPane.add(new Label("Długość genomu"), 0, 7);
        gridPane.add(genomeLength, 1, 7);
        gridPane.add(new Label("Początkowa ilość trawy"), 0, 8);
        gridPane.add(startingGrass, 1, 8);
        gridPane.add(new Label("Początkowa ilość zwierząt"), 0, 9);
        gridPane.add(startingAnimals, 1, 9);
        gridPane.add(new Label("Dzienny wzrost w ilości trawy"), 0, 10);
        gridPane.add(dailyGrass, 1, 10);
        gridPane.add(new Label("Wariant mapy"), 0, 11);
        gridPane.add(mapVariant, 1, 11);
        gridPane.add(new Label("Wariant trawy"), 0, 12);
        gridPane.add(grassVariant, 1, 12);
        gridPane.add(new Label("Wariant genetyczny"), 0, 13);
        gridPane.add(geneticVariant, 1, 13);
        gridPane.add(new Label("Wariant zachowania"), 0, 14);
        gridPane.add(moveVariant, 1, 14);


        vbox.getChildren().add(gridPane);

        Button launchSimulationButton = new Button("LAUNCH");
        launchSimulationButton.setOnAction(event -> {
            Stage stage = new Stage();
            SimulationWindow simulationWindow = new SimulationWindow(config);
            simulationWindow.start(stage);
        });
        vbox.getChildren().add(launchSimulationButton);

        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
