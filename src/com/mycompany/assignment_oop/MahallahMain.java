package com.mycompany.assignment_oop;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;


public class MahallahMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private Stage primaryStage;
    private Scene scene;

    private final MahallahMenu mahallahMenuView = new MahallahMenu(); 
    private final Register registerView = new Register();
    private final Remove removeView = new Remove();
    private final ViewStd ViewStd = new ViewStd();
    private final ViewStaff ViewStaff = new ViewStaff();


    @Override
    public void start(Stage primaryStage) 
    {
        this.primaryStage = primaryStage;
        Parent root = getMahallahMenu();
        scene = new Scene(root, 800, 650);
        primaryStage.setTitle("MAHALLAH MANAGEMENT SYSTEM");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void setScene(Parent view) 
    {
        scene.setRoot(view);
    }

    //getter
    public Parent getMahallahMenu() 
    {
    return mahallahMenuView.getView(this, primaryStage);
    }


    public Parent getRegisterView() 
    {
        return registerView.getView(this);
    }

    public Parent getRemoveView() 
    {
        return removeView.getView(this);
    }
    
    public Parent getViewStd() 
    {
        return ViewStd.getView(this);
    }

    public Parent getViewStaff() 
    {
        return ViewStaff.getView(this);
    }


    // public static void main(String[] args) 
    // {
    //     launch(args);
    // }
    
}
