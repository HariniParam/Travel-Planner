package com.example.travelplanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;


import javafx.util.Callback;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class TripPlanningController implements Initializable {

    @FXML
    private TextField searchBar;

    @FXML
    private WebView mapView;

    @FXML
    private WebView weatherReport;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private ListView<TravelPackage> touristPackageList;

    @FXML
    private Button backButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        WebEngine webEngine = mapView.getEngine();
        WebEngine webEngine1 = weatherReport.getEngine();

        searchBar.setOnAction(event -> {
            String destination = searchBar.getText();
            loadMap(destination, webEngine);
            loadWeather(destination, webEngine1);
            loadTouristPackages(destination);
        });
        touristPackageList.setCellFactory(param -> new TravelPackageListCell());
    }

    private List<TravelPackage> loadPackagesFromDatabase(String destination) {
        List<TravelPackage> packages = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/travel_planner", "root", "abd@1234")) {
            String query = "SELECT * FROM package WHERE destination = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, destination);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TravelPackage p = new TravelPackage();
                p.setPackageId(resultSet.getInt("package_id"));
                p.setPackageName(resultSet.getString("package_name"));
                p.setPackageDescription(resultSet.getString("package_description"));
                p.setDestination(resultSet.getString("destination"));
                p.setDuration(resultSet.getInt("duration"));
                p.setPrice(resultSet.getDouble("price"));
                p.setInclusions(resultSet.getString("inclusions"));
                p.setExclusions(resultSet.getString("exclusions"));
                p.setStartDate(resultSet.getString("start_date"));
                p.setEndDate(resultSet.getString("end_date"));
                p.setAvailableSeats(resultSet.getInt("available_seats"));
                p.setPackageImages(resultSet.getString("package_images"));
                p.setAverageRating(resultSet.getDouble("average_rating"));
                p.setContactEmail(resultSet.getString("contactEmail"));
                p.setContactPhone(resultSet.getString("contactPhone"));
                packages.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return packages;
    }

    @FXML
    private void goBackToDashboard(ActionEvent event) {
        DBUtils.changeScene(event, "Dashboard.fxml", "Dashboard", null);
    }

    private void loadMap(String location, WebEngine webEngine) {
        String mapUrl = "https://www.google.com/maps?q=" + location;
        webEngine.load(mapUrl);
    }

    private void loadWeather(String location, WebEngine webEngine){
        String weatherURL="https://www.google.com/search?q=weather+" + location;
        webEngine.load(weatherURL);
    }

    private void loadTouristPackages(String destination) {
        List<TravelPackage> packages = loadPackagesFromDatabase(destination);
        touristPackageList.getItems().setAll(packages);
    }


}
