package com.mycompany.assignment_oop;

import java.io.FileWriter;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.Parent;
import javafx.geometry.Pos;


public class StaffRegistrationGUI {
    
    public Parent getView(MahallahMain app) {
        // Title (centered)
        Label title = new Label("Staff Registration");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // Create a container for left-aligned content
        VBox leftAlignedContent = new VBox(10);
        leftAlignedContent.setAlignment(Pos.TOP_LEFT);
        leftAlignedContent.setStyle("-fx-padding: 0 0 0 20;");

        // Input Fields with left-aligned labels
        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();
        nameField.setPromptText("Enter full name");

        Label staffIdLabel = new Label("Staff ID:");
        TextField staffIdField = new TextField();
        staffIdField.setPromptText("Enter staff ID");

        Label phoneLabel = new Label("Phone number:");
        TextField phoneField = new TextField();
        phoneField.setPromptText("Enter phone number");

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        emailField.setPromptText("Enter email");
        
        Label mahallahLabel = new Label("Choose your Mahallah:");
        ComboBox<String> mahallahBox = new ComboBox<>();
        mahallahBox.getItems().addAll("Faruq", "Siddiq", "Ali", "Bilal", "Uthman", "Salahuddin",
                "Ruqayyah", "Aminah", "Asiah", "Asma'", "Hafsah", "Halimah", "Maryam", "Nusaibah", "Safiyyah", "Sumayyah");

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
        
        Label roomLabel = new Label("Room number:");
        TextField roomField = new TextField();

        Label compLabel = new Label("Choose your Compartment:");
        RadioButton compA = new RadioButton("A");
        RadioButton compB = new RadioButton("B");
        RadioButton compC = new RadioButton("C");
        RadioButton compD = new RadioButton("D");
        ToggleGroup compGroup = new ToggleGroup();
        compA.setToggleGroup(compGroup);
        compB.setToggleGroup(compGroup);
        compC.setToggleGroup(compGroup);
        compD.setToggleGroup(compGroup);

       
        leftAlignedContent.getChildren().addAll(
            nameLabel, nameField,
            staffIdLabel, staffIdField,
            phoneLabel, phoneField,
            emailLabel, emailField,
            mahallahLabel, mahallahBox,
            floorLabel, new HBox(10, floor1, floor2, floor3, floor4),
            roomLabel, roomField,
            compLabel, new HBox(10, compA, compB, compC, compD)
        );

        // Buttons (centered)
        Button submitBtn = new Button("Submit");
        submitBtn.setPrefWidth(100);
        submitBtn.setOnAction(e -> {
            if (validateInput(nameField, staffIdField, phoneField, emailField)) {
                System.out.println("Staff registered: " + nameField.getText());
                showAlert("Success", "Staff registered successfully!");
            }
        });

        Button backBtn = new Button("Back");
        backBtn.setPrefWidth(100);
        backBtn.setOnAction(e -> app.setScene(app.getMahallahMenu()));

        HBox buttonBox = new HBox(20, submitBtn, backBtn);
        buttonBox.setAlignment(Pos.CENTER);

        // Main layout (centered overall, with left-aligned content)
        VBox layout = new VBox(15);
        layout.setStyle("-fx-padding: 25;");
        layout.setAlignment(Pos.TOP_CENTER);
        layout.getChildren().addAll(
            title,
            leftAlignedContent,
            buttonBox
        );

        return layout;
    }

    private boolean validateInput(TextField... fields) {
        for (TextField field : fields) {
            if (field.getText().trim().isEmpty()) {
                showAlert("Error", "All fields must be filled!");
                return false;
            }
        }
        return true;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}

