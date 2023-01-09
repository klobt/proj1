package agh.ics.proj1.gui;

import agh.ics.proj1.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class SimulationWindow implements ChangeListener<GrassTile> {
    private final Config config;
    private final Simulation simulation;
    private final GridPane gridPane = new GridPane();
    private final VBox stats = new VBox();
    private final int cellSize = 20;
    private Thread simulationThread = null;

    public SimulationWindow(Config config) {
        this.config = config;
        simulation = new Simulation(config);
    }

    public void start(Stage stage) {
        fillGrid();

        BorderPane borderPane = new BorderPane();

        borderPane.setLeft(stats);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(gridPane);
        scrollPane.setMaxWidth(400);
        scrollPane.setMaxHeight(400);
        borderPane.setCenter(scrollPane);

        Button resumeButton = new Button("wznów");
        resumeButton.setDisable(true);
        Button pauseButton = new Button("pauza");
        pauseButton.setOnAction((ActionEvent event) -> {
            pauseButton.setDisable(true);
            resumeButton.setDisable(false);
            simulationThread.interrupt();
            simulationThread = null;
        });
        resumeButton.setOnAction((ActionEvent event) -> {
            resumeButton.setDisable(true);
            pauseButton.setDisable(false);
            startSimulationThread();
        });
        HBox controlPanel = new HBox();
        controlPanel.getChildren().add(resumeButton);
        controlPanel.getChildren().add(pauseButton);
        borderPane.setBottom(controlPanel);

        Scene scene = new Scene(borderPane, 640, 480);
        stage.setScene(scene);
        stage.show();
        startSimulationThread();
    }

    private void startSimulationThread() {
        simulationThread = new Thread(() -> {
            while (true) {
                try {
                    simulation.step();
                    Thread.sleep(config.simulationStepDelay);
                } catch (InterruptedException exception) {
                    break;
                }
            }
        });
        simulationThread.start();
    }

    private void fillGrid() {
        gridPane.getChildren().clear();
        gridPane.setGridLinesVisible(true);
        gridPane.getRowConstraints().clear();
        gridPane.getRowConstraints().add(new RowConstraints(cellSize));
        gridPane.getColumnConstraints().clear();
        gridPane.getColumnConstraints().add(new ColumnConstraints(cellSize));
        for (GrassTile tile : simulation.globeMap) {
            tile.addListener(this);
            tile.notifyOfChange();
        }
    }

    private void updateStats() {
        stats.getChildren().clear();
        stats.getChildren().add(new Label("Urodziło się: " + simulation.statBorn));
        stats.getChildren().add(new Label("Umarło się: " + simulation.statDead));
        stats.getChildren().add(new Label("Trawy urosło: " + simulation.statGrassGrown));
        stats.getChildren().add(new Label("Trawy zjedzono: " + simulation.statGrassEaten));
        stats.getChildren().add(new Label("Ruchów zrobiono: " + simulation.statMovesMade));
    }

    @Override
    public void onChange(GrassTile current) {
        Platform.runLater(() -> {
            StackPane stackPane = new StackPane();
            stackPane.getChildren().add(new Rectangle(cellSize, cellSize, Color.GRAY));
            if (current.hasGrass()) {
                stackPane.getChildren().add(new Rectangle(cellSize, cellSize, Color.GREEN));
            }
            int animalCount = current.getAnimals().size();
            int n = 1;
            while (n*n < animalCount) {
                n++;
            }
            GridPane animalGrid = new GridPane();
            int x = 0, y = 0;
            double radius = (double) cellSize / ((double) n) / 2 * 0.9;
            for (Animal animal : current.getAnimals()) {
                animalGrid.add(new Circle(radius, Color.ORANGE), x, y);
                x++;
                if (x == n) {
                    x = 0;
                    y++;
                }
            }
            stackPane.getChildren().add(animalGrid);
            gridPane.add(stackPane, current.getPosition().getIntX(), current.getPosition().getIntY());
            updateStats();
        });
    }
}
