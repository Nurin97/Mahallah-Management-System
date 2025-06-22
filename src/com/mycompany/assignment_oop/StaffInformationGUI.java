package com.mycompany.assignment_oop;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class StaffInformationGUI {
    public Parent getView(MahallahMain app) {
        VBox mainLayout = new VBox(10);
        mainLayout.setStyle("-fx-padding: 20");
        mainLayout.setAlignment(Pos.CENTER);

        // Title
        Text label = new Text("View Staff Information");

        // Display area
        TextArea taDisplay = new TextArea();
        taDisplay.setEditable(false);
        taDisplay.setPrefHeight(400);

        // Load data from file
        String filePath = "src/data/staff.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            taDisplay.setText(content.toString());
        } catch (IOException ex) {
            taDisplay.setText("Error loading staff data: " + ex.getMessage());
        }

        // Back button
        Button btnBack = new Button("Back");
        btnBack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                app.setScene(app.getMahallahMenu());
            }
        });

        // Add everything to layout
        mainLayout.getChildren().addAll(label, taDisplay, btnBack);
        return mainLayout;
    }
}
