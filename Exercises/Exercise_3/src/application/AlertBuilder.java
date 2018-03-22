package application;

import javafx.scene.control.Alert;

public class AlertBuilder {
    public static Alert buildErrorAlert(String title, String header, String description) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(description);
        alert.showAndWait();

        return alert;
    }
}
