package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.InvalidCredentialsException;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.model.User;
import org.loose.fis.sre.services.UserService;

import java.io.IOException;
import java.util.Objects;

import static org.loose.fis.sre.services.UserService.getUserRepository;

public class RegistrationController {

    @FXML
    private Text registrationMessage;
    @FXML
    private Text loginMessage;
    @FXML
    private PasswordField passwordFieldLogin;
    @FXML
    private TextField usernameFieldLogin;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField emailField;
    @FXML
    private ChoiceBox role;

    @FXML
    public void initialize() {
        role.getItems().addAll("Client", "Admin");
    }

    @FXML
    private Button register;
    @FXML
    public void handleRegisterAction(javafx.event.ActionEvent event) {
        try {
            UserService.addUser(usernameField.getText(), passwordField.getText(), emailField.getText(), (String) role.getValue());
            String username = usernameField.getText();

            ObjectRepository<User> userRepository = getUserRepository();
            String role = "";
            for (User user : userRepository.find()) {
                if (Objects.equals(username, user.getUsername()))
                    role = (String) user.getRole();
            }

            if(role.equals("Admin")) {
                //Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("addCarWash.fxml"));
                Stage stage= (Stage) register.getScene().getWindow();
                stage.close();
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("addCarWash.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                AddCarWashController ACcontroller = loader.getController();
                ACcontroller.setAdmin(username);
                Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                appStage.setTitle("Add your car wash");
                appStage.setScene(scene);
                appStage.show();
            }
            registrationMessage.setText("Account created successfully!");
        } catch (UsernameAlreadyExistsException | IOException e) {
            registrationMessage.setText(e.getMessage());
        }
    }

    @FXML
    private Button but;

    @FXML
    public void handleLoginAction(javafx.event.ActionEvent event)
    {
        try {
            UserService.verifyUser(usernameFieldLogin.getText(), passwordFieldLogin.getText());
            Stage stage= (Stage) but.getScene().getWindow();
            stage.close();
            String username = usernameFieldLogin.getText();
            ObjectRepository<User> userRepository = getUserRepository();

            String role = "";
            for (User user : userRepository.find()) {
                if (Objects.equals(username, user.getUsername()))
                    role = (String) user.getRole();
            }

            if(role.equals("Client")) {
                //Parent login = FXMLLoader.load(getClass().getClassLoader().getResource("HomePage.fxml"));
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("HomePage.fxml"));
                Parent login = loader.load();
                Scene scene = new Scene(login);
                HomePageController controller = loader.getController();
                controller.setLoggedUser(username);
                Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                appStage.setTitle("Home Page");
                appStage.setScene(scene);
                appStage.show();
            }

            if(role.equals("Admin")) {
                Parent login = FXMLLoader.load(getClass().getClassLoader().getResource("MyCarWashPage.fxml"));
                Scene scene = new Scene(login);
                Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                appStage.setScene(scene);
                appStage.show();
            }

        } catch (InvalidCredentialsException | IOException e) {
            loginMessage.setText(e.getMessage());
        }
    }
}
