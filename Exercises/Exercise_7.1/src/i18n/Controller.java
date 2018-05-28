package i18n;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private ResourceBundle bundle;

    private DateTimeFormatter formatter;

    public void initialize(URL location, ResourceBundle resources) {
        this.bundle = resources;
        this.prepareFormatters();
    }

    private void prepareFormatters() {
        this.formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(this.bundle.getLocale());
    }



    public void changeToGerman(ActionEvent event) {

        System.out.println("German");
    }

    public void changeToEnglish(ActionEvent event) {
        System.out.println("English");
    }
}
