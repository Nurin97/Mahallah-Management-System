package com.mycompany.assignment_oop;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class Remove {
    public Parent getView(MahallahMain app) 
    {
        VBox layout = new VBox(10);
        layout.setStyle("-fx-background-color: white; -fx-padding: 30 50 30 50; -fx-spacing: 20;");
        layout.setAlignment(Pos.CENTER);

        // Title and instructions
        Text title = new Text("REMOVE INFORMATION");
        title.setStyle("-fx-font-size: 40; -fx-font-weight: bold;");
        Text instructions = new Text("Please enter the Matric No. Or ID of the person you want to remove:");

        TextField input = new TextField();
        input.setPromptText("eg. 24127833");
        input.setMaxWidth(370);

        Button btnGoToRemove = new Button("Remove");
        btnGoToRemove.setStyle("-fx-background-color: #ff4444; -fx-text-fill: white; -fx-padding: 10px 20px;");
        btnGoToRemove.setPrefWidth(370);

        VBox centerBox = new VBox(20, title, instructions, input, btnGoToRemove);


        Button btnGoToMahallahMenu = new Button("Back");
        btnGoToMahallahMenu.setPrefWidth(370);

        btnGoToRemove.setOnAction(e -> {
            String inputText = input.getText().trim();
            if (inputText.isEmpty()) {
                showAlert(AlertType.WARNING, "Input Required", "Please enter an ID.");
                return;
            }
            
            try {
                int id = Integer.parseInt(inputText);
                showAlert(AlertType.INFORMATION, "Success", "Record with ID " + id + " has been successfully removed.");
                input.clear(); 
            } catch (NumberFormatException ex) {
                showAlert(AlertType.ERROR, "Invalid Input", "Please enter a valid numeric ID.");
            }
        });
        

        //back
        btnGoToMahallahMenu.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent e) 
            {
                app.setScene(app.getMahallahMenu());
            }
        });

        

        layout.getChildren().addAll(title, instructions, input, btnGoToRemove, btnGoToMahallahMenu);
        return layout;
    }

    private void showAlert(AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    
}
