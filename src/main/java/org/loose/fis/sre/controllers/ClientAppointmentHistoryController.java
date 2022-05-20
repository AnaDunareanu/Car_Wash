package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.model.Appointment;

import java.io.IOException;
import static org.loose.fis.sre.services.ClientAppointmentHistoryService.getClientAppointmentsRepository;

public class ClientAppointmentHistoryController {

    private String loggedUser;
    public void setLoggedUser(String text){
        loggedUser = text;
    }

    ObjectRepository<Appointment> clientAppointmentsRepository = getClientAppointmentsRepository();
    @FXML
    private ListView<String> ClientAppointmentHistoryList;
    @FXML
    public void initialize() {
        for (Appointment appointment : clientAppointmentsRepository.find()) {
            ClientAppointmentHistoryList.getItems().add("Car Wash Name: "+appointment.getCarwash()
                    +"\nStart Time: "+appointment.getsTime()
                    +"\nNumber of coins: "+appointment.getnCoins()
                    +"\nPayment method: "+appointment.getPayment()
                    +"\nClient: "+appointment.getUsername());
        }
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
