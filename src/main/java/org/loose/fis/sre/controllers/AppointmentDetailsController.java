package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.sre.exceptions.AppointmentNotAvailableException;
import org.loose.fis.sre.services.AppointmentService;

import java.io.IOException;


public class AppointmentDetailsController {

    @FXML
    private Text appointmentMessage;
    @FXML
    private Spinner nCoins;
    @FXML
    private Spinner sTime;
    private String loggedUser;
    public void setLoggedUser(String text) {
        loggedUser = text;
    }
    private String selectedCarwash;
    public void setSelectedCarwash(String text) {
        selectedCarwash = text;
    }
    private String address;
    public void setAddress(String text) {
        address = text;
    }

    SpinnerValueFactory<Integer> nCoinsValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,1);
    SpinnerValueFactory<Integer> sTimeValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,24,18);

    @FXML
    public void initialize() {
        nCoins.setValueFactory(nCoinsValueFactory);
        sTime.setValueFactory(sTimeValueFactory);
    }
    @FXML
    public void handleMakeAppointmentAction(javafx.event.ActionEvent event) {
        try {
            AppointmentService.addAppointment(loggedUser, selectedCarwash, (Integer) nCoins.getValue(), (Integer) sTime.getValue());
            appointmentMessage.setText("Appointment created successfully!");
        } catch (AppointmentNotAvailableException e) {
            appointmentMessage.setText(e.getMessage());
        }
    }
    @FXML
    public void handleBackAction(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("carwashDetails.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        CarwashDetailsController controller = loader.getController();
        controller.setLoggedUser(loggedUser);
        controller.setCarwash(selectedCarwash);
        controller.setAddress(address);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setTitle("Car Wash Details");
        appStage.setScene(scene);
        appStage.show();
    }

}
