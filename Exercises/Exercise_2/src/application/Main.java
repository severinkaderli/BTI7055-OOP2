package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import model.Person;
import persistence.PersonDAO;
import persistence.serialized.SerializedPersonDAO;
import persistence.textfile.TextFilePersonDAO;

import java.nio.file.Paths;


public class Main extends Application {
    /**
     * This will hold the list of people for the table view.
     */
    ObservableList<Person> people;

    public Main() {
        //this.dataAccessObject = new TextFilePersonDAO(Paths.get("people.txt"));
        this.people = FXCollections.observableArrayList();
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Sets up the stage.
     *
     * @param stage The stage which will be set up.
     */
    private void setupStage(Stage stage) {
        stage.setTitle("Person registry");
        stage.setResizable(false);
        stage.setWidth(600);
        stage.setHeight(500);
        stage.centerOnScreen();
    }

    @Override
    public void start(Stage primaryStage) {
        this.setupStage(primaryStage);
        SceneBuilder sceneBuilder = new SceneBuilder(primaryStage);
        primaryStage.setScene(sceneBuilder.buildMainWindow(this.people));
        primaryStage.show();
    }
}
