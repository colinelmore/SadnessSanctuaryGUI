package com.example;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.control.Button;

import java.io.IOException;

public class MainScreenController 
{

    @FXML
    private Circle lbl_electric_missle;

    @FXML
    private Button logoutButton;

    @FXML
    void initialize() 
    {
        logoutButton.setOnAction(event -> 
        {
            try 
            {
                handleLogout();
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        });
    }

    @FXML
    void changeScreen(MouseEvent event) throws IOException {
        App.setRoot("project");
    }

    @FXML
    void handleLogout() throws IOException {
        // take the user back to the home screen
        App.setRoot("home");
    }
}





