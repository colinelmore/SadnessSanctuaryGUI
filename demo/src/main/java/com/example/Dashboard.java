package com.example;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.*;

public class Dashboard implements Initializable {

    @FXML
    private Label lbl_user_name;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UiFacade facade = UiFacade.getInstance();
        lbl_user_name.setText(facade.getCurrentUser().toString());
     
    }
}
