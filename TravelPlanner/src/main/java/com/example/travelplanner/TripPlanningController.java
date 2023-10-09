package com.example.travelplanner;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;

public class TripPlanningController implements Initializable {

    @FXML
    private TextField searchBar;

    @FXML
    private WebView mapView;

    @FXML
    private WebView weatherReport;
    @FXML
    private Button backButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize the web view for displaying maps
        WebEngine webEngine = mapView.getEngine();

        // Add a listener to the search bar to load the map when the user presses Enter
        searchBar.setOnAction(event -> {
            String destination = searchBar.getText();
            loadMap(destination, webEngine);
            loadWeather(destination);
        });
    }

    @FXML
    private void goBackToDashboard(ActionEvent event) {
        DBUtils.changeScene(event, "Dashboard.fxml", "Dashboard", null);
    }
    private void loadMap(String location, WebEngine webEngine) {
        String mapUrl = "https://www.google.com/maps?q=" + location;
        webEngine.load(mapUrl);
    }
    private String getWeatherIconUrl(String iconCode) {
        return "http://openweathermap.org/img/w/" + iconCode + ".png";
    }
    private void loadWeather(String location) {
        String apiKey = "cd33ec1ca7535aecb3aa479fa4a0735d";

        // Construct the API URL
        String apiUrl = "http://api.openweathermap.org/data/2.5/weather?q=" + location + "&appid=" + apiKey;
        try {
            // Create a URL object and open a connection
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request method to GET
            connection.setRequestMethod("GET");

            // Get the response code
            int responseCode = connection.getResponseCode();

            // If the response code is OK (200), read the response data
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Update the WebView with the response data
                Platform.runLater(() -> {
                    WebEngine webEngine = weatherReport.getEngine();
                    webEngine.loadContent(response.toString());
                });
            } else {
                // Handle other response codes if needed
                Platform.runLater(() -> {
                    weatherReport.getEngine().loadContent("Failed to fetch weather data. Response code: " + responseCode);
                });
            }

            // Close the connection
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
            Platform.runLater(() -> {
                weatherReport.getEngine().loadContent("An error occurred while fetching weather data.");
            });
        }
    }
}
