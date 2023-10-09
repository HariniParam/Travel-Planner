package com.example.travelplanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private Label label_login;

    @FXML
    private void openTripPlanningPage(ActionEvent event) throws IOException {
        DBUtils.changeScene(event, "TripPlanning.fxml", "Plan a Trip", null);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void setUserInformation(String username){
        label_login.setText("Hi "+username+"!!Login successful");
    }
}
