package com.mycompany.assignment_oop;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MahallahMenuGUI {

    public Parent getView(MahallahMain app, Stage stage) {
        // IIUM Logo
        Image image = new Image("https://upload.wikimedia.org/wikipedia/en/thumb/3/31/International_Islamic_University_Malaysia_emblem.svg/1024px-International_Islamic_University_Malaysia_emblem.svg.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(150);
        imageView.setPreserveRatio(true);

        // Title
        Text title = new Text("Mahallah Management System");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 35));

        // Buttons
        Button btnRegisterStudent = new Button("Register Student");
        Button btnRegisterStaff = new Button("Register Staff");
        Button btnRemove = new Button("Remove");
        Button btnViewStudent = new Button("View Student Info");
        Button btnViewStaff = new Button("View Staff Info");
        Button btnSettings = new Button("Settings");
        Button btnExit = new Button("Exit");

        // Set button sizes
        int buttonWidth = 200;
        int buttonHeight = 40;
        btnRegisterStudent.setPrefSize(buttonWidth, buttonHeight);
        btnRegisterStaff.setPrefSize(buttonWidth, buttonHeight);
        btnRemove.setPrefSize(buttonWidth, buttonHeight);
        btnViewStudent.setPrefSize(buttonWidth, buttonHeight);
        btnViewStaff.setPrefSize(buttonWidth, buttonHeight);
        btnSettings.setPrefSize(buttonWidth, buttonHeight);
        btnExit.setPrefSize(buttonWidth, buttonHeight);

        // Event handlers
        btnRegisterStudent.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                app.setScene(app.getRegisterStudent());
            }
        });

        btnRegisterStaff.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                app.setScene(app.getRegisterStaff());
            }
        });

        btnRemove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                app.setScene(app.getRemove());
            }
        });

        btnViewStudent.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                app.setScene(app.getViewStudent());
            }
        });

        btnViewStaff.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                app.setScene(app.getViewStaff());
            }
        });

        btnSettings.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                app.setScene(app.getSettings());
            }
        });

        btnExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                stage.close();
            }
        });

        // Layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(15));
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(
            imageView,
            title,
            btnRegisterStudent,
            btnRegisterStaff,
            btnRemove,
            btnViewStudent,
            btnViewStaff,
            btnSettings,
            btnExit
        );

        return layout;
    }
}
