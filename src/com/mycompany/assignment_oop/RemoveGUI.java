package com.mycompany.assignment_oop;

import javafx.event.ActionEvent;
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

public class RemoveGUI {

    private final String STUDENT_FILE = "src/data/students.txt";
    private final String STAFF_FILE = "src/data/staff.txt";

    public Parent getView(MahallahMain app) {
        VBox layout = new VBox(10);
        layout.setStyle("-fx-background-color: white; -fx-padding: 30 50 30 50;");
        layout.setAlignment(Pos.CENTER);

        // Title & instructions
        Text title = new Text("REMOVE INFORMATION");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        Text instructions = new Text("Please enter the Matric No. or Staff ID:");

        TextField input = new TextField();
        input.setPromptText("e.g. 24127833");
        input.setMaxWidth(200);

        Label messageLabel = new Label();
        messageLabel.setStyle("-fx-font-size: 14px;");

        Button btnRemove = new Button("Remove");
        btnRemove.setPrefWidth(200);
        btnRemove.setPrefHeight(40);

        Button btnBack = new Button("Back");
        btnBack.setPrefWidth(200);
        btnBack.setPrefHeight(40);

        btnRemove.setOnAction(e -> {
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
        });

        btnBack.setOnAction((ActionEvent e) -> app.setScene(app.getMahallahMenu()));

        layout.getChildren().addAll(title, instructions, input, btnRemove, btnBack, messageLabel);
        return layout;
    }

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
                if (parts.length >= 2) {
                    String id = parts[1].trim();
                    if (!id.equals(idToRemove)) {
                        updatedLines.add(line);
                    } else {
                        found = true;
                    }
                } else {
                    updatedLines.add(line); // line malformed, keep it
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        if (found) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (String updatedLine : updatedLines) {
                    writer.write(updatedLine);
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        return found;
    }
}
