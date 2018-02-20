import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    /**
     * This will hold the list of people for the table view.
     */
    ObservableList<Person> people;

    SceneBuilder sceneBuilder;

    public Main() {
        this.people = FXCollections.observableArrayList();
        this.sceneBuilder = new SceneBuilder();
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
        primaryStage.setScene(this.sceneBuilder.buildMainWindow(this.people));
        primaryStage.show();
    }
}
