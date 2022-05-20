package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class ClientAppointmentHistoryController {

    @FXML
    private ListView<String> ClientAppointmentHistoryList;
    private String loggedUser;
    public void setLoggedUser(String text){
        loggedUser = text;
    }


}
