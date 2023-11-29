module com.example.travelplanner {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.web;
    requires okhttp3;

    opens com.example.travelplanner to javafx.fxml;
    exports com.example.travelplanner;
}