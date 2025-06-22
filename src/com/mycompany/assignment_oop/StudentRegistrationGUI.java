package com.mycompany.assignment_oop;

import java.io.FileWriter;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
import javafx.scene.Parent;


public class StudentRegistrationGUI{
    
    public Parent getView(MahallahMain app) {
        Label title = new Label("Student Registration");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();

        Label matrixLabel = new Label("Matric number:");
        TextField matrixField = new TextField();

        Label phoneLabel = new Label("Phone number:");
        TextField phoneField = new TextField();

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();

        Label kuliyyahLabel = new Label("Kulliyyah:");
        TextField kuliyyahField = new TextField();

        Label mahallahLabel = new Label("Choose your Mahallah:");
        ComboBox<String> mahallahBox = new ComboBox<>();
        mahallahBox.getItems().addAll("Faruq", "Siddiq", "Ali", "Bilal", "Uthman", "Salahuddin",
                "Ruqayyah", "Aminah", "Asiah", "Asma'", "Hafsah", "Halimah", "Maryam", "Nusaibah", "Safiyyah", "Sumayyah");

        Label blockLabel = new Label("Choose your Block:");
        ComboBox<String> blockBox = new ComboBox<>();
        blockBox.getItems().addAll("A", "B", "C", "D", "E", "F", "G");

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

        Label messageLabel = new Label();
        HBox messageBox = new HBox(messageLabel);
        messageBox.setAlignment(Pos.CENTER);

        Button submitBtn = new Button("Submit");
        submitBtn.setPrefWidth(80);
        submitBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) 
            {
                try {
                    String name = nameField.getText().trim();
                    int id = Integer.parseInt(matrixField.getText().trim());
                    int phone = Integer.parseInt(phoneField.getText().trim());
                    String email = emailField.getText().trim();
                    String kuliyyah = kuliyyahField.getText().trim();
                    String mahallah = mahallahBox.getValue();
                    String block = blockBox.getValue();
                    String floor = floorGroup.getSelectedToggle() != null ? ((RadioButton) floorGroup.getSelectedToggle()).getText() : "";
                    int room = Integer.parseInt(roomField.getText().trim());
                    String compartment = compGroup.getSelectedToggle() != null ? ((RadioButton) compGroup.getSelectedToggle()).getText() : "";

                    Student student = new Student(name, id, email, phone, kuliyyah, mahallah, block, floor, room, compartment);
                    app.getRegistration().addStudent(student);

                    // Save to file
                    String record = student.toFileString() + "\n";
                    try (FileWriter writer = new FileWriter("src/data/students.txt", true)) {
                        writer.write(record);
                        messageLabel.setText("Student registered successfully.");
                    } catch (IOException ex) {
                        messageLabel.setText("Error saving student.");
                    }
                } catch (NumberFormatException ex) {
                    messageLabel.setText("ID, Phone, and Room must be numbers.");
                }
            }
        });

        Button backBtn = new Button("Back");
        backBtn.setPrefWidth(80);
        backBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                app.setScene(app.getMahallahMenu());
            }
        });

        VBox layout = new VBox(10);
        layout.setStyle("-fx-padding: 15;");
        layout.setAlignment(Pos.TOP_LEFT);

        HBox buttonBox = new HBox(20, submitBtn, backBtn);
        buttonBox.setAlignment(Pos.CENTER);

        layout.getChildren().addAll(
                title, nameLabel, nameField, matrixLabel, matrixField,
                phoneLabel, phoneField, emailLabel, emailField,
                kuliyyahLabel, kuliyyahField,
                mahallahLabel, mahallahBox,
                blockLabel, blockBox,
                floorLabel, new HBox(10, floor1, floor2, floor3, floor4),
                roomLabel, roomField,
                compLabel, new HBox(10, compA, compB, compC, compD),
                buttonBox, messageBox
        );

        return layout;
    }
    
}

