package com.mycompany.assignment_oop;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;



public class ViewStd {
    public Parent getView(MahallahMain app) 
    {
        VBox layout = new VBox(10);
        layout.setStyle("-fx-padding: 20");

        Text label = new Text("Student Information");

        //TextArea
        TextArea taDisplay = new TextArea();
        taDisplay.setEditable(false);
        taDisplay.setWrapText(true);
        taDisplay.setWrapText(true);
        taDisplay.setPrefHeight(500);

        //Scroll
        ScrollPane scrollPane = new ScrollPane(taDisplay);
        scrollPane.setFitToWidth(true); 
        scrollPane.setFitToHeight(true); 
        scrollPane.setPannable(true);

        Button btnGoToMahallahMenu = new Button("Back");
    
        btnGoToMahallahMenu.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent e) 
            {
                app.setScene(app.getMahallahMenu());
            }
        });


        layout.getChildren().addAll(label, taDisplay, btnGoToMahallahMenu);
        return layout;
    }
}
