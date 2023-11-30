package com.example.travelplanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CartController implements Initializable {
    @FXML
    private GridPane cartContent;
    @FXML
    private Label emptyCartLabel;

    @FXML
    private void goBackToDashboard(ActionEvent event) {
        DBUtils.changeScene(event, "Dashboard.fxml", "Dashboard", null);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        int userId = UserSession.getInstance().getCurrentUserId();
        int row = 0;
        int col = 0;
        int maxColumns = 3;

        List<TravelPackage> packagesInCart = loadPackagesInCart(userId);
        if (packagesInCart.isEmpty()) {
            emptyCartLabel.setVisible(true);
        } else {
            emptyCartLabel.setVisible(false);
        }

        for (TravelPackage p : packagesInCart) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("TravelPackageBox.fxml"));
                Parent box = loader.load();
                TravelPackageBoxController controller = loader.getController();
                controller.setPackageId(p.getPackageId());
                controller.setPackageData(p);
                cartContent.add(box, col, row);
                col++;

                if (col >= maxColumns) {
                    col = 0;
                    row++;
                }
                controller.setRemoveButtonVisible(true);
                controller.setRemoveButtonAction(() -> removeFromCart(p.getPackageId()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private List<TravelPackage> loadPackagesInCart(int userId) {
        List<TravelPackage> packagesInCart = new ArrayList<>();


        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/travel_planner", "root", "Hp@300703")) {
            String query = "SELECT p.* FROM package p INNER JOIN cart c ON p.package_id = c.package_id WHERE c.user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                TravelPackage tripPackage = new TravelPackage();
                tripPackage.setPackageId(resultSet.getInt("package_id"));
                tripPackage.setPackageName(resultSet.getString("package_name"));
                tripPackage.setPackageDescription(resultSet.getString("package_description"));
                tripPackage.setDestination(resultSet.getString("destination"));
                tripPackage.setDuration(resultSet.getInt("duration"));
                tripPackage.setPrice(resultSet.getDouble("price"));
                tripPackage.setInclusions(resultSet.getString("inclusions"));
                tripPackage.setExclusions(resultSet.getString("exclusions"));
                tripPackage.setStartDate(resultSet.getString("start_date"));
                tripPackage.setEndDate(resultSet.getString("end_date"));
                tripPackage.setAvailableSeats(resultSet.getInt("available_seats"));
                tripPackage.setPackageImages(resultSet.getString("package_images"));
                tripPackage.setAverageRating(resultSet.getDouble("average_rating"));
                tripPackage.setContactEmail(resultSet.getString("contactEmail"));
                tripPackage.setContactPhone(resultSet.getString("contactPhone"));
                packagesInCart.add(tripPackage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return packagesInCart;
    }
    private void refreshCartContent() {

        cartContent.getChildren().clear();


        int userId = UserSession.getInstance().getCurrentUserId();

        int row = 0;
        int col = 0;
        int maxColumns = 3;


        List<TravelPackage> packagesInCart = loadPackagesInCart(userId);
        if (packagesInCart.isEmpty()) {
            emptyCartLabel.setVisible(true);
        } else {
            emptyCartLabel.setVisible(false);
        }
        for (TravelPackage p : packagesInCart) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("TravelPackageBox.fxml"));
                Parent box = loader.load();
                TravelPackageBoxController controller = loader.getController();
                controller.setPackageId(p.getPackageId());
                controller.setPackageData(p);
                cartContent.add(box, col, row);
                col++;

                if (col >= maxColumns) {
                    col = 0;
                    row++;
                }
                controller.setRemoveButtonVisible(true);
                controller.setRemoveButtonAction(() -> removeFromCart(p.getPackageId()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void removeFromCart(int packageId) {

        int userId = UserSession.getInstance().getCurrentUserId();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/travel_planner", "root", "Hp@300703")) {
            String deleteQuery = "DELETE FROM cart WHERE user_id = ? AND package_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, packageId);

            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                showAlert("Item Removed", "Item removed from cart", "The selected item has been removed from your cart.");
            } else {
                showAlert("Removal Error", "Failed to remove item", "An error occurred while removing the item from your cart.");
            }
            refreshCartContent();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Database Error", "An error occurred", "A database error occurred while removing the item from your cart.");
        }
    }
    private void showAlert(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
}
