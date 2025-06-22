package com.mycompany.assignment_oop;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class StudentRegistrationGUI {
    
    private SettingsManager settingsManager = SettingsManager.getInstance(); 

    public Parent getView(MahallahMain app) {
        settingsManager = SettingsManager.getInstance(); // ADDED
        VBox layout = new VBox(10);
        layout.setStyle("-fx-padding: 15;");

        Label title = new Label("Student Registration");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();

        Label matrixLabel = new Label("Matrix number:");
        TextField matrixField = new TextField();

        Label phoneLabel = new Label("Phone number:");
        TextField phoneField = new TextField();

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();

        Label kuliyyahLabel = new Label("Kulliyyah:");
        TextField kuliyyahField = new TextField();

        Label genderLabel = new Label("Gender:");
        RadioButton maleBtn = new RadioButton("Male");
        RadioButton femaleBtn = new RadioButton("Female");
        ToggleGroup genderGroup = new ToggleGroup();
        maleBtn.setToggleGroup(genderGroup);
        femaleBtn.setToggleGroup(genderGroup);
        HBox genderBox = new HBox(10, maleBtn, femaleBtn);

        Label mahallahLabel = new Label("Mahallah:");
        ComboBox<String> mahallahBox = new ComboBox<>();

        genderGroup.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            mahallahBox.getItems().clear();
            if (newVal != null) {
                RadioButton selected = (RadioButton) newVal;
                String selectedGender = selected.getText();

                for (Mahallah m : settingsManager.getMahallahList()) {
                    if (m.getGender().equalsIgnoreCase(selectedGender)) {
                        mahallahBox.getItems().add(m.getName());
                    }
                }
            }
        });


        Label blockLabel = new Label("Block:");
        ComboBox<String> blockBox = new ComboBox<>();
        blockBox.getItems().addAll("A", "B", "C", "D", "E", "F", "G");

        Label floorLabel = new Label("Floor:");
        RadioButton floor1 = new RadioButton("Ground Floor");
        RadioButton floor2 = new RadioButton("Floor 1");
        RadioButton floor3 = new RadioButton("Floor 2");
        RadioButton floor4 = new RadioButton("Floor 3");
        ToggleGroup floorGroup = new ToggleGroup();
        floor1.setToggleGroup(floorGroup);
        floor2.setToggleGroup(floorGroup);
        floor3.setToggleGroup(floorGroup);
        floor4.setToggleGroup(floorGroup);
        HBox floorBox = new HBox(10, floor1, floor2, floor3, floor4);

        Label roomLabel = new Label("Room number:");
        TextField roomField = new TextField();

        Label compartmentLabel = new Label("Compartment:");
        RadioButton compA = new RadioButton("A");
        RadioButton compB = new RadioButton("B");
        RadioButton compC = new RadioButton("C");
        RadioButton compD = new RadioButton("D");
        ToggleGroup compGroup = new ToggleGroup();
        compA.setToggleGroup(compGroup);
        compB.setToggleGroup(compGroup);
        compC.setToggleGroup(compGroup);
        compD.setToggleGroup(compGroup);
        HBox compBox = new HBox(10, compA, compB, compC, compD);

        Button submitBtn = new Button("Submit");
        Button btnBack = new Button("Back");

        HBox buttonBox = new HBox(20, submitBtn, btnBack);
        buttonBox.setAlignment(Pos.CENTER);

        Label messageLabel = new Label();
        messageLabel.setStyle("-fx-text-fill: red;");
        HBox messageBox = new HBox(messageLabel);
        messageBox.setAlignment(Pos.CENTER);

        submitBtn.setOnAction(e -> {
            String name = nameField.getText().trim();
            String matrix = matrixField.getText().trim();
            String phone = phoneField.getText().trim();
            String email = emailField.getText().trim();
            String kuliyyah = kuliyyahField.getText().trim();
            String room = roomField.getText().trim();

            Toggle genderToggle = genderGroup.getSelectedToggle();
            String gender = (genderToggle != null) ? ((RadioButton) genderToggle).getText() : null;

            String mahallah = mahallahBox.getValue();
            String block = blockBox.getValue();

            Toggle floorToggle = floorGroup.getSelectedToggle();
            String floor = (floorToggle != null) ? ((RadioButton) floorToggle).getText() : null;

            Toggle compToggle = compGroup.getSelectedToggle();
            String compartment = (compToggle != null) ? ((RadioButton) compToggle).getText() : null;

            // Validate
            if (name.isEmpty() || matrix.isEmpty() || phone.isEmpty() || email.isEmpty() ||
                    kuliyyah.isEmpty() || room.isEmpty() || gender == null || mahallah == null ||
                    block == null || floor == null || compartment == null) {
                messageLabel.setText("Please fill in all the fields.");
                return;
            }

            String line = String.join(", ",
                    name, gender, matrix, phone, email, kuliyyah,
                    mahallah, block, floor, room, compartment);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/data/students.txt", true))) {
                writer.write(line);
                writer.newLine();
                messageLabel.setText("Successfully registered student!");
                messageLabel.setStyle("-fx-text-fill: green;");
            } catch (IOException ex) {
                messageLabel.setText("Error saving student data.");
                ex.printStackTrace();
            }

            // Clear form
            nameField.clear();
            matrixField.clear();
            phoneField.clear();
            emailField.clear();
            kuliyyahField.clear();
            genderGroup.selectToggle(null);
            mahallahBox.getItems().clear();
            blockBox.getSelectionModel().clearSelection();
            floorGroup.selectToggle(null);
            roomField.clear();
            compGroup.selectToggle(null);
        });

        btnBack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                app.setScene(app.getMahallahMenu());
            }
        });

        layout.getChildren().addAll(
                title,
                nameLabel, nameField,
                matrixLabel, matrixField,
                phoneLabel, phoneField,
                emailLabel, emailField,
                kuliyyahLabel, kuliyyahField,
                genderLabel, genderBox,
                mahallahLabel, mahallahBox,
                blockLabel, blockBox,
                floorLabel, floorBox,
                roomLabel, roomField,
                compartmentLabel, compBox,
                buttonBox,
                messageBox
        );

        return layout;
    }
}
