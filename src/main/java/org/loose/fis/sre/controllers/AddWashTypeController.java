package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.WashTypeAlreadyExists;
import org.loose.fis.sre.exceptions.WashTypeDoesNotAlreadyExists;
import org.loose.fis.sre.model.User;
import org.loose.fis.sre.model.WashType;
import org.loose.fis.sre.services.UserService;
import org.loose.fis.sre.services.WashTypeService;
import javafx.scene.control.Button;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

import static org.loose.fis.sre.services.UserService.getUserRepository;
import static org.loose.fis.sre.services.WashTypeService.getSelectedWashTypesNum;
import static org.loose.fis.sre.services.WashTypeService.getTypeRepository;

public class AddWashTypeController {

    @FXML
    private TextField washTypeField;

    @FXML
    private Text washTypeMessage;

    private String CarWashName;

    @FXML
    public void setCarWashName(String name) {
        CarWashName = name;
    }

    @FXML
    private Button AddWashType;

    @FXML
    private Button DeleteWashType;

    @FXML
    private Button GoBack;



    @FXML
    public void handleAddWashType(javafx.event.ActionEvent event) {
        try {
            WashTypeService.addSelectedWashType(washTypeField.getText());
            System.out.println("NUMAR SELECTED WASH TYPES: " + WashTypeService.getSelectedWashTypesNum());
            for(String washType : WashTypeService.getSelectedWashTypes()){
                System.out.println(washType);
            }
            washTypeMessage.setText("Wash Type added successfully!");
        } catch (WashTypeAlreadyExists e) {
            washTypeMessage.setText(e.getMessage());
        }
    }

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

    @FXML
    public void handleDelete(javafx.event.ActionEvent event) {
        try {
            WashTypeService.deleteWashType(washTypeField.getText());
            washTypeMessage.setText("Wash Type deleted successfully!");
        } catch (WashTypeDoesNotAlreadyExists e) {
            washTypeMessage.setText(e.getMessage());
        }
    }
}
