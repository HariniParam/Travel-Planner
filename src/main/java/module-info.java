module com.example.travelplanner {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;
    requires jdk.jsobject;
    requires okhttp3;
    requires org.json;


    opens com.example.travelplanner to javafx.fxml;
    exports com.example.travelplanner;
}