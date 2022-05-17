package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class CarwashDetailsController {

    @FXML
    private Text carwashName;
    @FXML
    private Text address;

    private String loggedUser;
    public void setLoggedUser(String text) {
        loggedUser = text;
    }
    @FXML
    public void setCarwash(String text) {
        carwashName.setText(text);
    }
    @FXML
    public void setAddress(String text) {
        address.setText(text);
    }
    @FXML
    public void handleBackAction(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("HomePage.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        HomePageController controller = loader.getController();
        controller.setLoggedUser(loggedUser);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setTitle("Home Page");
        appStage.setScene(scene);
        appStage.show();
    }
}
