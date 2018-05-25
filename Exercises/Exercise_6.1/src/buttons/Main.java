package buttons;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(this.getClass().getResource("buttons2.fxml"));
        setUserAgentStylesheet(this.getClass().getResource("application.css").toExternalForm());
        primaryStage.setTitle("Two Buttons");
        primaryStage.setScene(new Scene(root));
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
