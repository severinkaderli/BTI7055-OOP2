
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.time.LocalDate;
import java.util.Arrays;

import static java.util.stream.Collectors.toList;


public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Setup stage
        primaryStage.setTitle("Person registry");
        primaryStage.setResizable(false);
        primaryStage.setWidth(400);
        primaryStage.setHeight(300);
        SceneBuilder sceneBuilder = new SceneBuilder();
        primaryStage.setScene(sceneBuilder.build());
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}
