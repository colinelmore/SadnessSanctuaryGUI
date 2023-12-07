package com.example;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;

public class SignupController {

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private Button signupButton;

    @FXML
    private void initialize() {
    }

    // @FXML
    // private void handleSignup() {
    //     System.out.println("Account created for " + firstNameField.getText() + " " + lastNameField.getText());
    // }

    @FXML
    private void handleSignup() throws IOException{
        App.setRoot("mainscreen");
    }
}
