package com.mycompany.assignment_oop;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;


    public class MahallahMain extends Application {
    private Stage stage;
    private Scene scene;
    // data
    private Registration registration = new Registration();
    private SettingsManager settings = SettingsManager.getInstance();

    // GUI views (up to you: as fields or new each time)
    private final MahallahMenuGUI menuView     = new MahallahMenuGUI();
    private final RegisterStudentGUI regStdView = new RegisterStudentGUI();
    private final RegisterStaffGUI   regStfView = new RegisterStaffGUI();
    private final RemoveGUI removeView           = new RemoveGUI();
    private final ViewStudentGUI viewStdView     = new ViewStudentGUI();
    private final ViewStaffGUI   viewStfView     = new ViewStaffGUI();
    private final SettingsGUI    settingsView    = new SettingsGUI();

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        Parent root = menuView.getView(this, stage);
        scene = new Scene(root, 800,650);
        primaryStage.setTitle("Mahallah Management System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void setScene(Parent view) {
        // reuse the same Scene to preserve size, CSS, etc.
        scene.setRoot(view);
    }

    public Parent getMahallahMenu() {
        return menuView.getView(this, stage);
    }
    public Parent getRegisterStudent() {
        return regStdView.getView(this);
    }
    public Parent getRegisterStaff() {
        return regStfView.getView(this);
    }

    // ← your preferred removal method
    public Parent getRemove() {
        return removeView.getView(this);
    }

    public Parent getViewStudent() {
        return viewStdView.getView(this);
    }
    public Parent getViewStaff() {
        return viewStfView.getView(this);
    }

    // ← your preferred settings method
    public Parent getSettingsView() {
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
