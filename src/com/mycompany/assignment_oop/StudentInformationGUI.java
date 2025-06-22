package com.mycompany.assignment_oop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class StudentInformationGUI {

    public Parent getView(MahallahMain app) {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(15));

        Label title = new Label("Student Information");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        TableView<String[]> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        String[] headers = {
            "Name", "Gender", "Matrix", "Phone", "Email", "Kulliyyah",
            "Mahallah", "Block", "Floor", "Room", "Compartment"
        };

        for (int i = 0; i < headers.length; i++) {
            final int colIndex = i;
            TableColumn<String[], String> column = new TableColumn<>(headers[i]);
            column.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue()[colIndex]));
            table.getColumns().add(column);
        }

        ObservableList<String[]> dataList = FXCollections.observableArrayList();

        try (BufferedReader reader = new BufferedReader(new FileReader("src/data/students.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",\\s*");
                if (fields.length == headers.length) {
                    dataList.add(fields);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        table.setItems(dataList);

        Label totalLabel = new Label("Total Students: " + dataList.size());
        totalLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

        Button btnBack = new Button("Back");
        btnBack.setOnAction(e -> app.setScene(app.getMahallahMenu()));

        layout.getChildren().addAll(title, table, totalLabel, btnBack);
        return layout;
    }
}
