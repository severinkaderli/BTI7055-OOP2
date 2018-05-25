package buttons;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ButtonController {
    @FXML
    private Label aLabel;

    @FXML
    Label bLabel;

    @FXML
    public void handleAClick(ActionEvent event) {
        int currentValue = Integer.valueOf(this.aLabel.getText());
        this.aLabel.setText(String.valueOf(++currentValue));
    }

    @FXML
    public void handleBClick(ActionEvent event) {
        int currentValue = Integer.valueOf(this.bLabel.getText());
        this.bLabel.setText(String.valueOf(++currentValue));
    }
}
