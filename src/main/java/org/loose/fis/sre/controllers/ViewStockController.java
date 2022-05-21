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
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.StockItemAlreadyExists;
import org.loose.fis.sre.exceptions.StockItemDoesNotAlreadyExist;
import org.loose.fis.sre.exceptions.WashTypeAlreadyExists;
import org.loose.fis.sre.exceptions.WashTypeDoesNotAlreadyExists;
import org.loose.fis.sre.model.Stock;
import org.loose.fis.sre.model.WashType;
import org.loose.fis.sre.services.StockService;
import org.loose.fis.sre.services.WashTypeService;

import java.io.IOException;
import java.util.Objects;


import static org.loose.fis.sre.services.StockService.getStockRepository;


public class ViewStockController {

    @FXML
    private ListView<String> StockItemList;

    @FXML
    private static String CarWashName;

    @FXML
    public static void setCarWashName(String name) {
        CarWashName = name;
    }

    @FXML
    private Button GoBack;

    private static String loggedCarWash;

    public static void setLoggedCarWash(String name){loggedCarWash=name;}

    @FXML
    private Button modify;

    ObjectRepository<Stock> stockRepository = getStockRepository();

    @FXML
    public void initialize()
    {
        for (Stock stk : stockRepository.find()) {
            if(Objects.equals(stk.getCarWashName(),CarWashName))
            {
                StockItemList.getItems().add(stk.getStockName());
            }
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
        AddStockItemController.setCarWashName(CarWashName);
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("addStock.fxml"));
        Parent login = loader.load();
        Scene scene = new Scene(login);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }

}
