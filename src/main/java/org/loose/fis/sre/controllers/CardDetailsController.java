package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.sre.exceptions.CardDetailsException;
import org.loose.fis.sre.services.CardDetailsService;

public class CardDetailsController {

    @FXML
    private TextField name;
    @FXML
    private TextField number;
    @FXML
    private TextField code;
    @FXML
    private Button done;

    @FXML
    public void handleDoneAction() {
        Stage stage = (Stage) done.getScene().getWindow();
        stage.close();
    }
}
