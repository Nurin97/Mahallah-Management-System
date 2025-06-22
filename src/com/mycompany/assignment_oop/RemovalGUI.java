package com.mycompany.assignment_oop;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RemovalGUI {

    private final String STUDENT_FILE = "src/data/students.txt";
    private final String STAFF_FILE = "src/data/staffs.txt";

    public Parent getView(MahallahMain app) {
        VBox layout = new VBox(10);
        layout.setStyle("-fx-background-color: white; -fx-padding: 30 50 30 50; -fx-spacing: 20;");
        layout.setAlignment(Pos.CENTER);

        // Title and instructions
        Text title = new Text("REMOVE INFORMATION");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        Text instructions = new Text("Please enter the Matric No. or Staff ID:");

        TextField input = new TextField();
        input.setPromptText("e.g. 24127833");
        input.setMaxWidth(200);

        // Message label
        Label messageLabel = new Label();
        messageLabel.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");

        Button btnGoToRemove = new Button("Remove");
        btnGoToRemove.setPrefWidth(200);
        btnGoToRemove.setPrefHeight(40);

        Button btnGoToMahallahMenu = new Button("Back");
        btnGoToMahallahMenu.setPrefWidth(200);
        btnGoToMahallahMenu.setPrefHeight(40);

        // Remove button action
        btnGoToRemove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String inputText = input.getText().trim();
                if (inputText.isEmpty()) {
                    messageLabel.setText("Please enter an ID.");
                    messageLabel.setStyle("-fx-text-fill: red;");
                    return;
                }

                boolean removed = removeFromFile(STUDENT_FILE, inputText) || removeFromFile(STAFF_FILE, inputText);

                if (removed) {
                    messageLabel.setText("Record with ID " + inputText + " has been successfully removed.");
                    messageLabel.setStyle("-fx-text-fill: green;");
                    input.clear();
                } else {
                    messageLabel.setText("No record found with ID: " + inputText);
                    messageLabel.setStyle("-fx-text-fill: red;");
                }
            }
        });

        // Back button action (no lambda)
        btnGoToMahallahMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                app.setScene(app.getMahallahMenu());
            }
        });

        layout.getChildren().addAll(title, instructions, input, btnGoToRemove, btnGoToMahallahMenu, messageLabel);
        return layout;
    }

    // Remove record by ID (assumes ID is at index 2)
    private boolean removeFromFile(String filePath, String idToRemove) {
        File file = new File(filePath);
        if (!file.exists()) {
            return false;
        }

        List<String> updatedLines = new ArrayList<>();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    String id = parts[2].trim();
                    if (!id.equals(idToRemove)) {
                        updatedLines.add(line);
                    } else {
                        found = true;
                    }
                } else {
                    updatedL