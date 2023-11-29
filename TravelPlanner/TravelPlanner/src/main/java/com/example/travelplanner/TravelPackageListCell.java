package com.example.travelplanner;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class TravelPackageListCell extends ListCell<TravelPackage> {
    @Override
    protected void updateItem(TravelPackage travelPackage, boolean empty) {
        super.updateItem(travelPackage, empty);

        if (empty || travelPackage == null) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TravelPackageBox.fxml"));
            try {
                VBox travelPackageBox = loader.load();
                TravelPackageBoxController controller = loader.getController();
                controller.setPackageData(travelPackage);
                setGraphic(travelPackageBox);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
