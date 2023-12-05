package com.example;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.App;
import model.*;

public class HomeController implements Initializable {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button signupButton;

    @FXML
    private void initialize() {
      
    }

    @FXML
    private void handleLogin() throws IOException {
     
        String userName = usernameField.getText();
        String password = passwordField.getText();
        UiFacade facade = UiFacade.getInstance();

        if(!facade.login(userName, password)){
            System.out.println("Error Logging in");
            return;
        } 

        System.out.println("Welcome " + facade.getCurrentUser().toString());
        App.setRoot("mainscreen");
    }

    @FXML
    private void handleSignup() {
        System.out.println("Signup button clicked");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
}
