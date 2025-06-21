package com.mycompany.assignment_oop;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MahallahMain extends Application {
    private Stage stage;
    private Scene scene;

    // Data
    private Registration registration = new Registration();
    private SettingsManager settings = SettingsManager.getInstance();

    // GUI views
    private final MahallahMenuGUI menuView = new MahallahMenuGUI();
    private final RegisterStudentGUI registerStudentView = new RegisterStudentGUI();
    private final RegisterStaffGUI registerStaffView = new RegisterStaffGUI();
    private final RemoveGUI removeView = new RemoveGUI();
    private final ViewStudentGUI viewStudentView = new ViewStudentGUI();
    private final ViewStaffGUI viewStaffView = new ViewStaffGUI();
    private final SettingsGUI settingsView = new SettingsGUI();

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        Parent root = menuView.getView(this, stage);
        scene = new Scene(root, 800, 650);
        primaryStage.setTitle("Mahallah Management System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void setScene(Parent view) {
        scene.setRoot(view);
    }

    // Navigation methods
    public Parent getMahallahMenu() {
        return menuView.getView(this, stage);
    }

    public Parent getRegisterStudent() {
        return registerStudentView.getView(this);
    }

    public Parent getRegisterStaff() {
        return registerStaffView.getView(this);
    }

    public Parent getRemove() {
        return removeView.getView(this);
    }

    public Parent getViewStudent() {
        return viewStudentView.getView(this);
    }

    public Parent getViewStaff() {
        return viewStaffView.getView(this);
    }

    public Parent getSettings() {
        return settingsView.getView(this);
    }

    public Registration getRegistration() {
        return registration;
    }

    public SettingsManager getSettingsManager() {
        return settings;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
