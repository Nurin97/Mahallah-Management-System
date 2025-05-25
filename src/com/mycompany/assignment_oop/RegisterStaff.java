package com.mycompany.assignment_oop;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.geometry.Pos;


public class RegisterStaff {
    
    public Parent getView (MahallahMain app) {
        
        Label title = new Label("Staff Registration");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        
        //Register staff
        Label nameLabel = new Label ("Name: ");
        TextField nameField = new TextField();
        
        Label staffIdLabel = new Label ("Staff Id: ");
        TextField staffIdField = new TextField();
        
        Label phoneLabel = new Label ("Phone number: ");
        TextField phoneField = new TextField();
        
        Label emailLabel = new Label ("Email: ");
        TextField emailField = new TextField();
        
        Label positionLabel = new Label ("Position: ");
        TextField positionField = new TextField();
        
        Label mahallahLabel = new Label("Choose your mahallah");
        ComboBox<String> mahallahBox = new ComboBox<>();
        mahallahBox.getItems().addAll("Faruq", "Siddiq", "Ali", "Bilal", "Uthman", "Salahuddin",
                "Ruqayyah", "Aminah", "Asiah", "Asma'", "Hafsah", "Halimah", "Maryam", "Nusaibah", "Safiyyah", "Sumayyah");
        
        Label blockLabel = new Label("Choose your block");
        ComboBox<String> blockBox = new ComboBox<>();
        blockBox.getItems().addAll("A", "B", "C", "D", "E", "F", "G");
        
        Label floorLable = new Label("Choose your floor");
        RadioButton floor1Btn = new RadioButton("Ground Floor");
        RadioButton floor2Btn = new RadioButton("Floor 1");
        RadioButton floor3Btn = new RadioButton("Floor 2");
        RadioButton floor4Btn = new RadioButton("Floor 3");

        ToggleGroup group = new ToggleGroup();
        floor1Btn.setToggleGroup(group);
        floor2Btn.setToggleGroup(group);
        floor3Btn.setToggleGroup(group);
        floor4Btn.setToggleGroup(group);
        
        Label roomLabel = new Label ("Room number: ");
        TextField roomField = new TextField();
        
        Label compartmentLable = new Label("Choose your compartment");
        RadioButton compABtn = new RadioButton("A");
        RadioButton compBBtn = new RadioButton("B");
        RadioButton compCBtn = new RadioButton("C");
        RadioButton compDBtn = new RadioButton("D");
        
        ToggleGroup group1 = new ToggleGroup();
        compABtn.setToggleGroup(group1);
        compBBtn.setToggleGroup(group1);
        compCBtn.setToggleGroup(group1);
        compDBtn.setToggleGroup(group1);
        
        Label messageLabel = new Label(); 
        HBox messageBox = new HBox(messageLabel);
        messageBox.setAlignment(Pos.CENTER);
        
        Button submitBtn = new Button("Submit");
        submitBtn.setOnAction(e -> {
            String name = nameField.getText().trim();
            String staffId = staffIdField.getText().trim();
            String phone = phoneField.getText().trim();
            String email = emailField.getText().trim();
            String position = positionField.getText().trim();
            String room = roomField.getText().trim();
    
            boolean mahallahSelected = mahallahBox.getSelectionModel().getSelectedItem() != null;
            boolean blockSelected = blockBox.getSelectionModel().getSelectedItem() != null;
            boolean floorSelected = group.getSelectedToggle() != null;
            boolean compartmentSelected = group1.getSelectedToggle() != null;
            

            if (
                    name.isEmpty() || 
                    staffId.isEmpty() || 
                    phone.isEmpty() || 
                    email.isEmpty() || 
                    position.isEmpty() || 
                    room.isEmpty() ||
                    !mahallahSelected ||
                    !blockSelected ||
                    !floorSelected ||
                    !compartmentSelected
                    ) 
            {
                    messageLabel.setText("Please fill in all the fields.");
                    messageLabel.setStyle("-fx-text-fill: red;");
                    return;
            }

            messageLabel.setText("Successfully registered staff!");
            messageLabel.setStyle("-fx-text-fill: green;");

            nameField.clear();
            staffIdField.clear();
            phoneField.clear();
            emailField.clear();
            positionField.clear();
            mahallahBox.getSelectionModel().clearSelection();
            blockBox.getSelectionModel().clearSelection();
            group.selectToggle(null); 
            roomField.clear();
            group1.selectToggle(null);
            });
        
        Button btnGoToRegister = new Button("Back");
        btnGoToRegister.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent e) 
            {
                app.setScene(app.getRegisterView());
            }
        });
        
        submitBtn.setPrefWidth(80);
        btnGoToRegister.setPrefWidth(80);
        
        VBox layout = new VBox(10);
        HBox buttonBox = new HBox(20, submitBtn, btnGoToRegister);
        buttonBox.setAlignment(Pos.CENTER);
        
        layout.setStyle("-fx-padding: 15;");
        layout.getChildren().addAll(
                title,
                nameLabel, nameField,
                staffIdLabel, staffIdField,
                phoneLabel, phoneField,
                emailLabel, emailField, 
                positionLabel, positionField,
                mahallahLabel, new VBox(5, mahallahBox),
                blockLabel, new VBox(5, blockBox),
                floorLable, new HBox(10, floor1Btn, floor2Btn, floor3Btn, floor4Btn),
                roomLabel, roomField,
                compartmentLable, new HBox(10, compABtn, compBBtn, compCBtn, compDBtn),
                buttonBox, messageBox
        );

        return layout;
    }

}

