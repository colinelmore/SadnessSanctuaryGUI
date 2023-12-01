package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class HomeController {

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
    private void handleLogin() {
     
        System.out.println("Login button clicked");
    }

    @FXML
    private void handleSignup() {
        System.out.println("Signup button clicked");
    }
}
