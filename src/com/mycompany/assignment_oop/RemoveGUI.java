package com.mycompany.assignment_oop;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class RemoveGUI {
    public Parent getView(MahallahMain app) {
        VBox layout = new VBox(20); // Increased spacing
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: white;");

        // 1. Top Bar with Back Button
        HBox topBar = new HBox();
        topBar.setAlignment(Pos.TOP_LEFT);
        Button btnGoToMahallahMenu = new Button("← Back to Menu");
        btnGoToMahallahMenu.setOnAction(e -> {
            System.out.println("Back button clicked!"); // Debug
            app.setScene(app.getMahallahMenu());
        });
        btnGoToMahallahMenu.setStyle("-fx-background-color: #6c757d; -fx-text-fill: white; -fx-padding: 8 16; -fx-background-radius: 5;");
        topBar.getChildren().add(btnGoToMahallahMenu);

        // 2. Content
        Text title = new Text("REMOVE INFORMATION");
        title.setStyle("-fx-font-size: 40; -fx-font-weight: bold;");
        Text instructions = new Text("Please enter the Matric No. Or ID of the person you want to remove:");
        TextField input = new TextField();
        input.setPromptText("eg. 24127833");
        input.setMaxWidth(370);
        Button btnGoToRemove = new Button("Remove");
        btnGoToRemove.setStyle("-fx-background-color: #ff4444; -fx-text-fill: white; -fx-padding: 10px 20px;");
        btnGoToRemove.setPrefWidth(370);

        // 3. Add all components to layout
        layout.getChildren().addAll(topBar, title, instructions, input, btnGoToRemove);

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
