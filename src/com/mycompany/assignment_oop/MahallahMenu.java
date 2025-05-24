package com.mycompany.assignment_oop;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MahallahMenu 
{
    public Parent getView(MahallahMain app, Stage stage)

    {
        //iium logo
        Image image = new Image("https://upload.wikimedia.org/wikipedia/en/thumb/3/31/International_Islamic_University_Malaysia_emblem.svg/1024px-International_Islamic_University_Malaysia_emblem.svg.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(150);
        imageView.setPreserveRatio(true);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(5);
        grid.setPadding(new Insets(25, 25, 25, 25));

        //title 
        Text scenetitle = new Text("Mahallah Management System");
        scenetitle.setFont(Font.font("Arial", FontWeight.BOLD, 35));


        //button 
        Button btnGoToRegister = new Button ("        Register       ");
        Button btnGoToRemove = new Button   ("        Remove       ");
        Button btnGoToStdInfo = new Button  ("View Student Info");
        Button btnGoToStaffInfo = new Button("   ViewStaffInfo   ");
        Button btnExit = new Button            ("           Exit           ");

        //event handler 
        //register panel
        btnGoToRegister.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent e) 
            {
                app.setScene(app.getRegisterView());
            }
        });

        //remove panel
        btnGoToRemove.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent e) 
            {
                app.setScene(app.getRemoveView());
            }
        });
        
        //student info panel
        btnGoToStdInfo.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent e) 
            {
                app.setScene(app.getViewStd());
            }
        });


        //staff info panel
        btnGoToStaffInfo.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent e) 
            {
                app.setScene(app.getViewStaff());
            }
        });

        
        //event handler exit
        btnExit.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent e) 
            {
              stage.close();
            }
        });

         
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(15));
        layout.setAlignment(Pos.CENTER);
        
        layout.getChildren().addAll(imageView, scenetitle, grid, btnGoToRegister, btnGoToRemove, btnGoToStdInfo, btnGoToStaffInfo, btnExit);

        return layout;
    }
}
