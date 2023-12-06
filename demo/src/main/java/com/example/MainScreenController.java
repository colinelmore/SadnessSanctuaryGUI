package com.example;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.input.MouseEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import model.*;


public class MainScreenController {

    @FXML
    private Circle lbl_electric_missle;

    @FXML
    void changeScreen(MouseEvent event) throws IOException {
        App.setRoot("project");
    }

}





