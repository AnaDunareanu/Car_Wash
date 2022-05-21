package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.sre.exceptions.StockItemAlreadyExists;
import org.loose.fis.sre.exceptions.StockItemDoesNotAlreadyExist;
import org.loose.fis.sre.services.StockService;

import java.io.IOException;

public class AddStockItemController {

    @FXML
    private static String CarWashName;

    @FXML
    public static void setCarWashName(String name) {
        CarWashName = name;
    }

    @FXML
    private Text stockMessage;

    @FXML
    private TextField stockItemField;

    @FXML
    private Button GoBack;

    @FXML
    private Button Add;

    @FXML
    private Button Delete;

    @FXML
    public void handleAddStockItem(javafx.event.ActionEvent event) {
        try {
            StockService.addSelectedStock(stockItemField.getText(),CarWashName);
            stockMessage.setText("Item added successfully in stock!");
        } catch (StockItemAlreadyExists e) {
            stockMessage.setText(e.getMessage());
        }
    }

    @FXML
    public void handleDelete(javafx.event.ActionEvent event) {
        try {
            StockService.deleteStock(stockItemField.getText());
            stockMessage.setText("Item removed from stock!");
        } catch (StockItemDoesNotAlreadyExist e) {
            stockMessage.setText(e.getMessage());
        }
    }

    @FXML
    public void handleGoBack(javafx.event.ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("viewStock.fxml"));
        Parent login = loader.load();
        ViewStockController controller = loader.getController();
        controller.setCarWashName(CarWashName);
        Scene scene = new Scene(login);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }
}
