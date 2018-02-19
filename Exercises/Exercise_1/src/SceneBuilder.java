import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SceneBuilder {
    public Scene build() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(20);
        gridPane.setVgap(10);

        // Add labels
        List<Node> labels = new ArrayList<>();
        labels.add(new Label("Name"));
        labels.add(new Label("First name"));
        labels.add(new Label("Date of birth"));
        labels.add(new Label("Marital status"));

        for(int i = 0; i < labels.size(); i++) {
            gridPane.add(labels.get(i), 0, i);
        }


        TextField nameField = new TextField();
        gridPane.add(nameField, 1, 0);

        TextField firstNameField = new TextField();
        gridPane.add(firstNameField, 1, 1);

        DatePicker dateOfBirthField = new DatePicker(LocalDate.now());
        gridPane.add(dateOfBirthField, 1, 2);

        ChoiceBox martialStatusField = new ChoiceBox(FXCollections.observableArrayList(MaritalStatus.values()));
        martialStatusField.getSelectionModel().selectFirst();

        gridPane.add(martialStatusField, 1, 3);
        martialStatusField.setMaxWidth(Double.MAX_VALUE);
        gridPane.setFillWidth(martialStatusField, true);

        Button saveButton = new Button("Save");
        saveButton.setOnAction(action -> {
            // Validating
            if(nameField.getText().isEmpty()) {
                this.showValidationAlert("The name field is empty!");
            }

            if(firstNameField.getText().isEmpty()) {
                this.showValidationAlert("The first name field is empty!");
            }

            if(dateOfBirthField.getValue().isAfter(LocalDate.now())) {
                this.showValidationAlert("Date of birth is in the future!");
            }


            Person person = new Person(nameField.getText(), firstNameField.getText(), dateOfBirthField.getValue(), (MaritalStatus) martialStatusField.getValue());
            System.out.println(person);
        });

        gridPane.setHalignment(saveButton, HPos.RIGHT);
        gridPane.add(saveButton, 1, 4);

        BorderPane rootPane = new BorderPane();
        rootPane.setCenter(gridPane);
        return new Scene(rootPane, 400, 300);
    }

    private void showValidationAlert(String description) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Validation Error");
        alert.setHeaderText("Invalid or missing data");
        alert.setContentText(description);
        alert.showAndWait();
    }
}
