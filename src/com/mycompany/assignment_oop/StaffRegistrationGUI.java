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
import java.util.List;
import java.util.stream.Collectors;

public class StaffRegistrationGUI {

    private SettingsManager settingsManager = SettingsManager.getInstance(); 

    public Parent getView(MahallahMain app) {

        Label title = new Label("Staff Registration");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // Input fields
        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();

        Label genderLabel = new Label("Gender:");
        RadioButton maleBtn = new RadioButton("Male");
        RadioButton femaleBtn = new RadioButton("Female");
        ToggleGroup genderGroup = new ToggleGroup();
        maleBtn.setToggleGroup(genderGroup);
        femaleBtn.setToggleGroup(genderGroup);
        HBox genderBox = new HBox(10, maleBtn, femaleBtn);

        Label staffIdLabel = new Label("Staff ID:");
        TextField staffIdField = new TextField();

        Label phoneLabel = new Label("Phone Number:");
        TextField phoneField = new TextField();

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();

        Label positionLabel = new Label("Position:");
        TextField positionField = new TextField();

        Label mahallahLabel = new Label("Choose your Mahallah:");
        ComboBox<String> mahallahBox = new ComboBox<>();

        // Gender-based Mahallah from real list
        genderGroup.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            mahallahBox.getItems().clear();
            if (newVal != null) {
                RadioButton selected = (RadioButton) newVal;
                String selectedGender = selected.getText();

                List<Mahallah> filtered = settingsManager.getMahallahList().stream()
                        .filter(m -> m.getGender().equalsIgnoreCase(selectedGender))
                        .collect(Collectors.toList());

                for (Mahallah m : filtered) {
                    mahallahBox.getItems().add(m.getName());
                }
            }
        });

        Label blockLabel = new Label("Block:");
        Label blockValueLabel = new Label("PG");

        Label floorLabel = new Label("Choose your Floor:");
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

        Label roomLabel = new Label("Room Number:");
        TextField roomField = new TextField();

        Label compartmentLabel = new Label("Choose your Compartment:");
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

        // Message label
        Label messageLabel = new Label();
        messageLabel.setStyle("-fx-text-fill: red;");
        HBox messageBox = new HBox(messageLabel);
        messageBox.setAlignment(Pos.CENTER);

        // Submit button 
        Button submitBtn = new Button("Submit");
        submitBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String name = nameField.getText().trim();
                Toggle genderToggle = genderGroup.getSelectedToggle();
                String gender = (genderToggle != null) ? ((RadioButton) genderToggle).getText() : "";
                String staffId = staffIdField.getText().trim();
                String phone = phoneField.getText().trim();
                String email = emailField.getText().trim();
                String position = positionField.getText().trim();
                String mahallah = mahallahBox.getValue();
                String block = "PG";
                Toggle floorToggle = floorGroup.getSelectedToggle();
                String floor = (floorToggle != null) ? ((RadioButton) 