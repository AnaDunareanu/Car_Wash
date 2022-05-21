package org.loose.fis.sre.controllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.sre.exceptions.StockItemAlreadyExists;
import org.loose.fis.sre.exceptions.StockItemDoesNotAlreadyExist;
import org.loose.fis.sre.exceptions.WashTypeAlreadyExists;
import org.loose.fis.sre.exceptions.WashTypeDoesNotAlreadyExists;
import org.loose.fis.sre.services.StockService;
import org.loose.fis.sre.services.WashTypeService;

import java.io.IOException;

import static org.loose.fis.sre.services.StockService.getSelectedStock;
import static org.loose.fis.sre.services.StockService.getSelectedStockNum;

public class ViewStockController {

    @FXML
    private ListView<String> StockItemList;

    @FXML
    private String CarWashName;

    @FXML
    public void setCarWashName(String name) {
        CarWashName = name;
    }

    @FXML
    private Button GoBack;

    @FXML
    private Button modify;

    @FXML
    public void initialize()
    {
        if(getSelectedStockNum()>0) {
            for(String item :  getSelectedStock())
                StockItemList.getItems().add(item);
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
    public void handleModify(javafx.event.ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("addStock.fxml"));
        Parent login = loader.load();
        AddStockItemController controller = loader.getController();
        controller.setCarWashName(CarWashName);
        Scene scene = new Scene(login);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }

}
