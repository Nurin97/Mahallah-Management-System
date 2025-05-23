package com.mycompany.assignment_oop;

import javafx.event.ActionEvent;
import javafx.scene.Parentandler;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.Parent;

public class Register {
    MahallahMainent getView(MahallahMain app) 
    {
        VBofx-padding new VBox(10);
        layout.setStyle("-fx-padding: 20");

        Text label = new Text("REGISTER");

        Button btnGoToMahallahMenu = new Button("back");


        btnGoToMahallahMenu.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent e) 
            {
                app.setScene(app.getMahallahMenu());
            }
        });


        layout.getChildren().addAll(label, btnGoToMahallahMenu);
        return layout;
    }
}
