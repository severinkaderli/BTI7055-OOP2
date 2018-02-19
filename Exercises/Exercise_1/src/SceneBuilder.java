import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.*;

public class SceneBuilder {

    public Scene buildMainWindow(ObservableList<Person> people) {

        GridPane gridPane = new GridPane();

        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setHgap(20);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(100);
        gridPane.getColumnConstraints().add(column1);


        TableView<Person> personTableView = new TableView<Person>(people);

        TableColumn<Person,String> firstNameCol = new TableColumn<Person,String>("Name");
        firstNameCol.setCellValueFactory(new PropertyValueFactory("name"));

        TableColumn<Person,String> lastNameCol = new TableColumn<Person,String>("First name");
        lastNameCol.setCellValueFactory(new PropertyValueFactory("firstName"));

        TableColumn<Person,String> dateOfBirthColumn = new TableColumn<Person,String>("Date of birth");
        dateOfBirthColumn.setCellValueFactory(new PropertyValueFactory("dateOfBirth"));

        TableColumn<Person,String> maritalStatusColumn = new TableColumn<Person,String>("Marital status");
        maritalStatusColumn.setCellValueFactory(new PropertyValueFactory("maritalStatus"));


        personTableView.getColumns().setAll(firstNameCol, lastNameCol, dateOfBirthColumn, maritalStatusColumn);

        gridPane.add(personTableView, 0, 0);

        Button addButton = new Button("Add person...");
        addButton.setOnAction(action -> {
            System.out.println("Add person");
            Stage addPersonStage = new Stage();
            addPersonStage.setScene(this.buildAddWindow(addPersonStage, people));
            addPersonStage.initModality(Modality.APPLICATION_MODAL);
            addPersonStage.showAndWait();
        });

        gridPane.setHalignment(addButton, HPos.RIGHT);
        gridPane.add(addButton, 0, 1);


        return new Scene(gridPane, 600, 600);
    }

    private Scene buildAddWindow(Stage stage, ObservableList<Person> people) {
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
                return;
            }

            if(firstNameField.getText().isEmpty()) {
                this.showValidationAlert("The first name field is empty!");
                return;
            }

            if(dateOfBirthField.getValue().isAfter(LocalDate.now())) {
                this.showValidationAlert("Date of birth is in the future!");
                return;
            }

            Person person = new Person(nameField.getText(), firstNameField.getText(), dateOfBirthField.getValue(), (MaritalStatus) martialStatusField.getValue());
            people.add(person);
            stage.close();
        });

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(action -> {
            stage.close();
        });

        HBox buttonBox = new HBox(saveButton, cancelButton);
        buttonBox.setSpacing(10);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        gridPane.setHalignment(buttonBox, HPos.RIGHT);
        gridPane.add(buttonBox, 1, 4);

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
