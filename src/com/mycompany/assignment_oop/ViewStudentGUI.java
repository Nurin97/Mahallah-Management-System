package com.mycompany.assignment_oop;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.Parent;


public class ViewStudentGUI {
    public Parent getView(MahallahMain app) {
        VBox mainLayout = new VBox(10);
        mainLayout.setStyle("-fx-padding: 20");
        mainLayout.setAlignment(Pos.CENTER);

        Button btnBack = new Button("Back");

        btnBack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                app.setScene(app.getMahallahMenu());
            }
        });

        mainLayout.getChildren().add(btnBack);
        return mainLayout;
    }
}
