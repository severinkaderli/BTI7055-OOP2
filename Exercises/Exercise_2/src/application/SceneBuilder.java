package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Person;
import model.MaritalStatus;

import java.time.LocalDate;
import java.util.*;

public class SceneBuilder {
    /**
     * The padding value used throughout the application.
     */
    private static final int PADDING = 10;

    /**
     * Builds the stage for the main window.
     *
     * @param people The list of people which is used for the table view.
     * @return The stage of the main window.
     */
    public Scene buildMainWindow(ObservableList<Person> people) {
        // Get the ready grid pane
        GridPane gridPane = this.prepareGridPane();

        // Set constraints for the columns
        ColumnConstraints firstColumnConstraint = new ColumnConstraints();
        firstColumnConstraint.setPercentWidth(100);
        gridPane.getColumnConstraints().add(0, firstColumnConstraint);

        // Build the table view
        gridPane.add(this.buildTableView(people), 0, 0);

        // Set the alignment of the button and add it to the grid pane
        Button addButton = new Button("Add person...");
        GridPane.setHalignment(addButton, HPos.RIGHT);
        gridPane.add(addButton, 0, 1);

        // Handles the event when the add person button is clicked. It opens a new window
        // with a form to enter a new person.
        addButton.setOnAction(event -> {
            Stage addPersonStage = new Stage();
            addPersonStage.setScene(this.buildAddPersonWindow(addPersonStage, people));
            addPersonStage.initModality(Modality.APPLICATION_MODAL);
            addPersonStage.showAndWait();
        });

        return new Scene(gridPane);
    }

    /**
     * Prepares the grid pane for the different views.
     *
     * @return The prepared grid pane
     */
    private GridPane prepareGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setHgap(PADDING);
        gridPane.setVgap(PADDING);
        gridPane.setPadding(new Insets(PADDING));
        return gridPane;
    }

    /**
     * Builds the table view for the people list.
     *
     * @param people The list of people
     * @return The built table view
     */
    private TableView<Person> buildTableView(ObservableList<Person> people) {
        TableView<Person> personTableView = new TableView<>(people);

        // Prepare the columns of the table view
        TableColumn<Person, String> firstNameColumn = new TableColumn<>("Name");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Person, String> lastNameColumn = new TableColumn<>("First name");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<Person, String> dateOfBirthColumn = new TableColumn<>("Date of birth");
        dateOfBirthColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));

        TableColumn<Person, String> maritalStatusColumn = new TableColumn<>("Marital status");
        maritalStatusColumn.setCellValueFactory(new PropertyValueFactory<>("maritalStatus"));

        // Add the columns to the table view and set constraints
        personTableView.getColumns().addAll(Arrays.asList(firstNameColumn, lastNameColumn, dateOfBirthColumn, maritalStatusColumn));
        personTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        return personTableView;
    }

    /**
     * Builds the scene for the add person window.
     *
     * @param stage The stage of the the window
     * @param people The list of people
     * @return
     */
    private Scene buildAddPersonWindow(Stage stage, ObservableList<Person> people) {
        GridPane gridPane = this.prepareGridPane();

        // Add the labels to the grid pane
        List<Label> labels = new ArrayList<>(Arrays.asList(
                new Label("Name"),
                new Label("First name"),
                new Label("Date of birth"),
                new Label("Marital status")
        ));

        for (int i = 0; i < labels.size(); i++) {
            gridPane.add(labels.get(i), 0, i);
        }


        TextField nameField = new TextField();
        gridPane.add(nameField, 1, 0);

        TextField firstNameField = new TextField();
        gridPane.add(firstNameField, 1, 1);

        DatePicker dateOfBirthField = new DatePicker(LocalDate.now());
        gridPane.add(dateOfBirthField, 1, 2);

        ChoiceBox martialStatusField = new ChoiceBox<>(FXCollections.observableArrayList(MaritalStatus.values()));
        martialStatusField.getSelectionModel().selectFirst();

        gridPane.add(martialStatusField, 1, 3);
        martialStatusField.setMaxWidth(Double.MAX_VALUE);
        GridPane.setFillWidth(martialStatusField, true);

        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> {
            // Validating
            if (nameField.getText().isEmpty()) {
                this.showValidationAlert("The name field is empty!");
                return;
            }

            if (firstNameField.getText().isEmpty()) {
                this.showValidationAlert("The first name field is empty!");
                return;
            }

            if (dateOfBirthField.getValue().isAfter(LocalDate.now())) {
                this.showValidationAlert("Date of birth is in the future!");
                return;
            }

            Person person = new Person(nameField.getText(), firstNameField.getText(), dateOfBirthField.getValue(), (MaritalStatus) martialStatusField.getValue());
            people.add(person);
            stage.close();
        });

        Button cancelButton = new Button("Cancel");

        // Close the current window on click on the cancel button
        cancelButton.setOnAction(event -> {
            stage.close();
        });

        HBox buttonBox = new HBox(saveButton, cancelButton);
        buttonBox.setSpacing(PADDING);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        GridPane.setHalignment(buttonBox, HPos.RIGHT);
        gridPane.add(buttonBox, 1, 4);

        BorderPane rootPane = new BorderPane();
        rootPane.setCenter(gridPane);
        return new Scene(rootPane, 350, 200);
    }

    /**
     * Show a validation alert window with the given description.
     *
     * @param description The description that will be shown
     */
    private void showValidationAlert(String description) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Validation Error");
        alert.setHeaderText("Invalid or missing data");
        alert.setContentText(description);
        alert.showAndWait();
    }
}
