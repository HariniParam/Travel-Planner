package com.example.travelplanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private Label label_login;
    @FXML
    private Button logoutButton;

    @FXML
    private void openTripPlanningPage(ActionEvent event) throws IOException {
        DBUtils.changeScene(event, "TripPlanning.fxml", "Plan a Trip", null);
    }

    @FXML
    private void openTripPackagePage(ActionEvent event) throws IOException {
        DBUtils.changeScene(event, "TravelPackage.fxml", "Travel Package", null);
    }

    @FXML
    private void openCartPage(ActionEvent event) throws IOException {
        DBUtils.changeScene(event, "Cart.fxml", "My Cart", null);
    }

    @FXML
    private void onLogout(ActionEvent event) {
        String fxmlFile = "MainPage.fxml";
        String title = "Main Page";
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
            Stage stage = (Stage) logoutButton.getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(new Scene(root, 800, 600)); // Set the appropriate width and height
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void setUserInformation(String username){
        label_login.setText("Hi "+username+"!! Login successful");
    }

}
