package com.example.travelplanner;
import com.example.travelplanner.TravelPackage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;

public class TravelPackageController implements Initializable{
    @FXML
    private GridPane packageGrid;
    @FXML
    private Slider scrollSlider;

    @FXML
    private void goBackToDashboard(ActionEvent event) {
        DBUtils.changeScene(event, "Dashboard.fxml", "Dashboard", null);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        List<TravelPackage> packages = loadPackagesFromDatabase();


        int row = 0;
        int col = 0;
        int maxColumns = 3;
        for (TravelPackage p : packages) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("TravelPackageBox.fxml"));
                Parent box = loader.load();
                TravelPackageBoxController controller = loader.getController();
                controller.setPackageId(p.getPackageId());
                controller.setPackageData(p);
                packageGrid.add(box, col, row);
                col++;

                if (col >= maxColumns) {
                    col = 0;
                    row++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        scrollSlider.setMin(0);
        scrollSlider.setMax(packages.size() - 1);
        scrollSlider.setValue(0);


        scrollSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            int value = newValue.intValue();
            packageGrid.setLayoutY(-value * 220);
        });
    }

    private List<TravelPackage> loadPackagesFromDatabase() {
        List<TravelPackage> packages = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/travel_planner", "root", "abd@1234")) {
            String query = "SELECT * FROM package";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    TravelPackage tripPackages = new TravelPackage();
                    tripPackages.setPackageId(resultSet.getInt("package_id"));
                    tripPackages.setPackageName(resultSet.getString("package_name"));
                    tripPackages.setPackageDescription(resultSet.getString("package_description"));
                    tripPackages.setDestination(resultSet.getString("destination"));
                    tripPackages.setDuration(resultSet.getInt("duration"));
                    tripPackages.setPrice(resultSet.getDouble("price"));
                    tripPackages.setInclusions(resultSet.getString("inclusions"));
                    tripPackages.setExclusions(resultSet.getString("exclusions"));
                    tripPackages.setStartDate(resultSet.getString("start_date"));
                    tripPackages.setEndDate(resultSet.getString("end_date"));
                    tripPackages.setAvailableSeats(resultSet.getInt("available_seats"));
                    tripPackages.setPackageImages(resultSet.getString("package_images"));
                    tripPackages.setAverageRating(resultSet.getDouble("average_rating"));
                    tripPackages.setContactEmail(resultSet.getString("contactEmail"));
                    tripPackages.setContactPhone(resultSet.getString("contactPhone"));
                    packages.add(tripPackages);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return packages;
    }
}
