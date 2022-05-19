package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

import static org.loose.fis.sre.services.WashTypeService.getSelectedWashTypes;
import static org.loose.fis.sre.services.WashTypeService.getSelectedWashTypesNum;

public class CarwashDetailsController {

    @FXML
    private Text carwashName;
    @FXML
    private Text address;
    @FXML
    private ListView<String> WashTypeList;
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
    public void initialize()
    {
        if(getSelectedWashTypesNum()>0) {
            for(String wash :  getSelectedWashTypes())
                WashTypeList.getItems().add(wash);
        }
    }
    @FXML
    public void handleCreateAppointmentAction(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("appointmentDetails.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        AppointmentDetailsController controller = loader.getController();
        controller.setLoggedUser(loggedUser);
        controller.setSelectedCarwash(carwashName.getText());
        controller.setAddress(address.getText());
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setTitle("Appointment Details");
        appStage.setScene(scene);
        appStage.show();
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
