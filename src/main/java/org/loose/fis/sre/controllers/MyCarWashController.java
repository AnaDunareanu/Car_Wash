package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.CarWashAlreadyExistsException;
import org.loose.fis.sre.exceptions.WashTypeAlreadyExists;
import org.loose.fis.sre.model.CarWash;
import org.loose.fis.sre.model.User;
import org.loose.fis.sre.model.WashType;
import org.loose.fis.sre.services.CarWashService;
import org.loose.fis.sre.services.WashTypeService;


import java.io.IOException;
import java.util.Objects;

import static org.loose.fis.sre.services.CarWashService.getCarWashRepository;
import static org.loose.fis.sre.services.WashTypeService.getTypeRepository;


public class MyCarWashController {

    /*@FXML
    private Text addWashTypeMessage;
    @FXML
    private TextField typenameField;*/

    @FXML
    private ListView<String> WashTypeList;

    @FXML
    public void setLoggedUser(String name){LoggedUser = name;}

    @FXML
    private Text CarWashName;

    @FXML
    public void setCarWashName(String name){
        CarWashName.setText(name);
    }

    private String LoggedUser;

    ObjectRepository<WashType> WashTypeRepository = getTypeRepository();

    @FXML
    public void initialize()
    {
        for(WashType wash :  WashTypeRepository.find())
            WashTypeList.getItems().add(wash.getWashTypeName());
    }

}
