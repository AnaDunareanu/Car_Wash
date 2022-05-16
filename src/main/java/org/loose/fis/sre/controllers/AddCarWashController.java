package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.sre.exceptions.CarWashAlreadyExistsException;
import org.loose.fis.sre.services.CarWashService;

import java.io.IOException;

public class AddCarWashController {

    @FXML
    private Text addCarWashMessage;
    @FXML
    private TextField carwashnameField;
    @FXML
    private TextField adressField;

    private String administratorName;
    @FXML
    public void setAdmin(String username){
        administratorName = username;
    }

    @FXML
    public void handleAddCarWashAction(javafx.event.ActionEvent event) {
        try {
            CarWashService.addCarWash(carwashnameField.getText(), adressField.getText(), administratorName);
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("register.fxml"));
            Parent login = loader.load();
            Scene scene = new Scene(login);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();
        } catch (CarWashAlreadyExistsException | IOException e) {
            addCarWashMessage.setText(e.getMessage());
        }
    }

}