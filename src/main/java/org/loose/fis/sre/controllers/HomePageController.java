package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.model.CarWash;
import org.loose.fis.sre.services.AppointmentService;
import org.loose.fis.sre.services.CarWashService;
import org.loose.fis.sre.services.WashTypeService;

import java.io.IOException;
import java.util.Objects;

import static org.loose.fis.sre.services.CarWashService.getCarWashRepository;

public class HomePageController {

    @FXML
    private Text loggedUser;
    public void setLoggedUser(String text){
        loggedUser.setText(text);
    }
    
    ObjectRepository<CarWash> carwashRepository = getCarWashRepository();
    @FXML
    private ListView<String> CarWashList;
    @FXML
    public void initialize() {
        for (CarWash carwash : carwashRepository.find()) {
            CarWashList.getItems().add(carwash.getCarWashName());
        }
    }
    @FXML
    public void handleMouseClicked(MouseEvent event) throws IOException {
        String selectedCarWash = CarWashList.getSelectionModel().getSelectedItem();
        String address = "";
        for(CarWash carwash : carwashRepository.find()) {
            if (Objects.equals(carwash.getCarWashName(), selectedCarWash)) {
                address = carwash.getCarWashAdress();
            }
        }
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("carwashDetails.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        CarwashDetailsController controller = loader.getController();
        controller.setCarwash(selectedCarWash);
        controller.setAddress(address);
        controller.setLoggedUser(loggedUser.getText());
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setTitle("Car Wash Details");
        appStage.setScene(scene);
        appStage.show();
    }
    @FXML
    private Button history;
    @FXML
    public void handleViewHistoryAction(javafx.event.ActionEvent event) throws IOException {
        Stage stage= (Stage) history.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("clientAppointmentHistory.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        ClientAppointmentHistoryController controller = loader.getController();
        controller.setLoggedUser(loggedUser.getText());
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setTitle("Your Appointment History");
        appStage.setScene(scene);
        appStage.show();
    }
    @FXML
    private Button logout;
    @FXML
    public void handleLogoutAction() throws IOException {
        Stage stage= (Stage) logout.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("register.fxml"));
        stage.setTitle("Car Wash Login or Register");
        stage.setScene(new Scene(root, 400, 500));
        stage.show();
        CarWashService.initDatabase();
        WashTypeService.initDatabase();
        AppointmentService.initDatabase();
    }

}
