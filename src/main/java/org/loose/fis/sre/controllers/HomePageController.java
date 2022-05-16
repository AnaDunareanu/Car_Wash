package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.model.CarWash;

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
    public void handleMouseClicked(MouseEvent event) {
        System.out.println(CarWashList.getSelectionModel().getSelectedItem());
    }

}
