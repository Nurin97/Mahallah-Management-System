package Assignment2OOP;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

public class Register 
{
    public Parent getView(MahallahMain app) 
    {
        VBox mainLayout = new VBox(10);
        mainLayout.setStyle("-fx-padding: 20");
        mainLayout.setAlignment(Pos.CENTER);
        
        Text label = new Text("REGISTER AS:");
        label.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        
        Button studentBtn = new Button("Student");
        Button staffBtn = new Button("Staff");
        Button btnGoToMahallahMenu = new Button("Back");
        
        double buttonWidth = 200;
        double buttonHeight = 40;
        
        studentBtn.setPrefSize(buttonWidth, buttonHeight);
        staffBtn.setPrefSize(buttonWidth, buttonHeight);
        btnGoToMahallahMenu.setPrefSize(buttonWidth, buttonHeight);
        
        mainLayout.getChildren().addAll(label, studentBtn, staffBtn, btnGoToMahallahMenu);
           
        StackPane root = new StackPane(mainLayout);
        
        studentBtn.setOnAction(e -> {
            RegisterStudent studentForm = new RegisterStudent();
            Parent studentView = studentForm.getView(app);
            
            root.getChildren().setAll(studentView);
        });

        staffBtn.setOnAction(e -> {
            RegisterStaff staffForm = new RegisterStaff();
            Parent staffView = staffForm.getView(app);
            
            root.getChildren().setAll(staffView);
        });
        
        btnGoToMahallahMenu.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent e) 
            {
                app.setScene(app.getMahallahMenu());
            }
        });

        return root;
    }
}