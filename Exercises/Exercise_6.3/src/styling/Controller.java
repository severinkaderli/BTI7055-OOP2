package styling;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Reflection;

/**
 * This is the main controller for the application.
 *
 * @author Severin Kaderli
 */
public class Controller {
    /**
     * The main label for the text.
     */
    @FXML
    Label mainLabel;

    /**
     * The button to toggle the reflection.
     */
    @FXML
    Button toggleButton;

    /**
     * Toggles the reflection on the label and the button.
     *
     * @param event The ActionEvent object
     */
    public void handleToggle(ActionEvent event) {
        if (mainLabel.getEffect() != null) {
            mainLabel.setEffect(null);
            toggleButton.setEffect(null);
        } else {
            mainLabel.setEffect(new Reflection());
            toggleButton.setEffect(new Reflection());
        }
    }
}
