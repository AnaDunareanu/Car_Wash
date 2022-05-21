package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.model.Appointment;
import org.loose.fis.sre.model.User;

import java.io.IOException;
import java.util.Objects;

import static org.loose.fis.sre.services.AppointmentService.getAppointmentRepository;
import static org.loose.fis.sre.services.UserService.getUserRepository;

public class ClientHistoryController {

    @FXML
    private ListView<String> ClientsList;

    private static String CarWashName;

    @FXML
    private Button GoBack;


    @FXML
    public void initialize()
    {
            for(Appointment app:appointmentRepository.find())
            {
                if(Objects.equals(app.getCarwash(),CarWashName))
                {
                    ClientsList.getItems().add("Username: " + app.getUsername());
                }
            }
    }

    public static void setCarWashName(String name) {
        CarWashName = name;
    }

    ObjectRepository<Appointment> appointmentRepository = getAppointmentRepository();


    @FXML
    public void handleGoBack(javafx.event.ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MyCarWashPage.fxml"));
        Parent login = loader.load();
        MyCarWashController controller = loader.getController();
        controller.setCarWashName(CarWashName);
        Scene scene = new Scene(login);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }

}
