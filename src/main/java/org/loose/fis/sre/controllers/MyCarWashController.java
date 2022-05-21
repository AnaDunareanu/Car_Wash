package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.model.CarWash;
import org.loose.fis.sre.model.WashType;


import java.io.IOException;
import java.util.Objects;

import static org.loose.fis.sre.services.CarWashService.getCarWashRepository;
import static org.loose.fis.sre.services.WashTypeService.*;


public class MyCarWashController {

    @FXML
    private ListView<String> WashTypeList;

    @FXML
    public void setLoggedUser(String name){LoggedUser = name;}

    private static String loggedCarWash;

    public static void setLoggedCarWash(String name){loggedCarWash=name;}

    @FXML
    private Text CarWashName;

    @FXML
    public void setCarWashName(String name){
        CarWashName.setText(name);
    }

    @FXML
    private Button modify;

    @FXML
    private Button viewStock;

    private String LoggedUser;


    @FXML
    private Button viewAppointments;

    @FXML
    private Button logOut;

    @FXML
    private Button History;

    ObjectRepository<WashType> typeRepository = getTypeRepository();

    @FXML
    public void initialize()
    {
            for (WashType washType : typeRepository.find()) {
                if(Objects.equals(washType.getCarWashName(),loggedCarWash))
                {
                    WashTypeList.getItems().add("Name: " + washType.getWashTypeName() + "\n Price: " +
                            washType.getPrice() + "RON");
                }
        }

    }

    @FXML
    public void handleModify(javafx.event.ActionEvent event) throws IOException {
            AddWashTypeController.setCarWashName(CarWashName.getText());
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("addWashType.fxml"));
            Parent login = loader.load();
            Scene scene = new Scene(login);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();
    }

    @FXML
    public void handleViewStock(javafx.event.ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("viewStock.fxml"));
        Parent login = loader.load();
        ViewStockController controller = loader.getController();
        controller.setCarWashName(CarWashName.getText());
        Scene scene = new Scene(login);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }

    @FXML
    public void handleViewAppointment(javafx.event.ActionEvent event) throws IOException {
        ViewAppointmentsController.setCarWashName(CarWashName.getText());
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("viewAppointments.fxml"));
        Parent login = loader.load();
        Scene scene = new Scene(login);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }

    @FXML
    public void handleClientHistory(javafx.event.ActionEvent event) throws IOException {
        ClientHistoryController.setCarWashName(CarWashName.getText());
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("clientHistory.fxml"));
        Parent login = loader.load();
        Scene scene = new Scene(login);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }

    @FXML
    public void handleLogOut(javafx.event.ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("register.fxml"));
        Parent login = loader.load();
        Scene scene = new Scene(login);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }

}
