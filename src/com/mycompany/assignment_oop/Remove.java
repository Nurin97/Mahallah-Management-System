package com.mycompany.assignment_oop;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Textler;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class Remove {
    public Parent getView(MahallahMain app) 
    {
        VBox layout = new VBox(10);
        layout.setStyle("-fx-padding: 20");

        

        Button btnGoToMahallahMenu = new Button("back");
        

        //back
        btnGoToMahallahMenu.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent e) 
            {
                app.setScene(app.getMahallahMenu());
            }
        });

        

        layout.getChildren().addAll(btnGoToMahallahMenu);
        return layout;
    }
    
}
