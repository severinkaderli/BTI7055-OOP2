package stopwatch;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        Timer firstTimer = new Timer(500);
        Timer secondTimer = new Timer(250);
        new Stopwatch(firstTimer);
        new Stopwatch(secondTimer);
    }


    public static void main(String[] args) {
        launch(args);
    }
}