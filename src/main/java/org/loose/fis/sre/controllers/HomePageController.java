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

import java.io.IOException;
import java.util.Objects;

import static org.loose.fis.sre.services.CarWashService.getCarWashRepository;

public class HomePageController {

    @FXML
    private Text loggedUser;
    @FXML
    private ListView<String> CarWashList;

    public void setLoggedUser(String text){
        loggedUser.setText(text);
    }
    
    ObjectRepository<CarWash> carwashRepository = getCarWashRepository();

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

}
