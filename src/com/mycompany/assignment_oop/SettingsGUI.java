package com.mycompany.assignment_oop;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SettingsGUI {

    private SettingsManager settingsManager;
    private TabPane tabPane;
    private ListView<Mahallah> mahallahListView;
    private ListView<Block> blockListView;
    private ListView<Room> roomListView;
    private ComboBox<Mahallah> mahallahComboBox;
    private ComboBox<Block> blockComboBox;
    private ComboBox<Mahallah> roomsMahallahComboBox; // Added reference for rooms tab combo
    private Mahallah selectedMahallah;
    private Block selectedBlock;
    private MahallahMain app;
    private Button btnGoToMahallahMenu;
    

    public Parent getView(MahallahMain app) {
        this.app = app;
        settingsManager = SettingsManager.getInstance();
        VBox root = createMainLayout();
        refreshMahallahList();
        return root;
    }
    
    private VBox createMainLayout() {
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #f5f5f5;");
        
        // Simple top bar with back button
        HBox topBar = createTopBar();
        
        // Simple header
        VBox header = createSimpleHeader();
        
        // Tab pane
        tabPane = createSimpleTabPane();
        
        root.getChildren().addAll(topBar, header, tabPane);
        return root;
    }
    
    private HBox createTopBar() {
        HBox topBar = new HBox();
        topBar.setPadding(new Insets(0, 0, 10, 0));
        
        btnGoToMahallahMenu = new Button("← Back to Menu");
        btnGoToMahallahMenu.setOnAction(this::handleBackToMenu);
        btnGoToMahallahMenu.setStyle(
            "-fx-background-color: #6c757d; " +
            "-fx-text-fill: white; " +
            "-fx-padding: 8 16; " +
            "-fx-background-radius: 5;"
        );
        
        topBar.getChildren().add(btnGoToMahallahMenu);
        return topBar;
    }
    
    private VBox createSimpleHeader() {
        VBox header = new VBox(5);
        header.setAlignment(Pos.CENTER);
        header.setPadding(new Insets(20));
        header.setStyle("-fx-background-color: white; -fx-background-radius: 8;");
        
        Label title = new Label("Settings Manager");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        title.setTextFill(Color.DARKBLUE);
        
        Label subtitle = new Label("Manage Mahallahs, Blocks, and Rooms");
        subtitle.setFont(Font.font("Arial", 14));
        subtitle.setTextFill(Color.GRAY);
        
        header.getChildren().addAll(title, subtitle);
        return header;
    }

    private void setupMahallahListView() {
        mahallahListView.setCellFactory(lv -> new ListCell<Mahallah>() {
            private final Label nameLabel = new Label();
            private final Button editButton = new Button("Edit");
            private final Button deleteButton = new Button("Delete");
            private final Region spacer = new Region();
            private final HBox content = new HBox(10, nameLabel, spacer, editButton, deleteButton);

            {
                HBox.setHgrow(spacer, Priority.ALWAYS);
                editButton.setOnAction(e -> {
                    Mahallah mahallah = getItem();
                    if (mahallah != null) showEditMahallahDialog(mahallah);
                });
                deleteButton.setOnAction(e -> {
                    Mahallah mahallah = getItem();
                    if (mahallah != null) {
                        settingsManager.getMahallahList().remove(mahallah);
                        settingsManager.saveMahallahs(); // Save changes to file
                        refreshMahallahList();
                    }
                });
            }

            @Override
            protected void updateItem(Mahallah item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    nameLabel.setText(item.toString());
                    setGraphic(content);
                }
            }
        });
    }

    private void setupBlockListView() {
        blockListView.setCellFactory(lv -> new ListCell<Block>() {
            private final Label nameLabel = new Label();
            private final Button editButton = new Button("Edit");
            private final Button deleteButton = new Button("Delete");
            private final Region spacer = new Region();
            private final HBox content = new HBox(10, nameLabel, spacer, editButton, deleteButton);

            {
                HBox.setHgrow(spacer, Priority.ALWAYS);
                editButton.setOnAction(e -> {
                    Block block = getItem();
                    if (block != null) showEditBlockDialog(block);
                });
                deleteButton.setOnAction(e -> {
                    Block block = getItem();
                    if (block != null && selectedMahallah != null) {
                        selectedMahallah.removeBlock(block.getBlockName());
                        settingsManager.saveMahallahs(); // Save changes to file
                        refreshBlockList();
                    }
                });
            }

            @Override
            protected void updateItem(Block item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    nameLabel.setText(item.toString());
                    setGraphic(content);
                }
            }
        });
    }

    private void setupRoomListView() {
        roomListView.setCellFactory(lv -> new ListCell<Room>() {
            private final Label nameLabel = new Label();
            private final Button editButton = new Button("Edit");
            private final Button deleteButton = new Button("Delete");
            private final Region spacer = new Region();
            private final HBox content = new HBox(10, nameLabel, spacer, editButton, deleteButton);

            {
                HBox.setHgrow(spacer, Priority.ALWAYS);
                editButton.setOnAction(e -> {
                    Room room = getItem();
                    if (room != null) showEditRoomDialog(room);
                });
                deleteButton.setOnAction(e -> {
                    Room room = getItem();
                    if (room != null && selectedBlock != null) {
                        selectedBlock.removeRoom(room.getRoomNumber());
                        settingsManager.saveMahallahs(); // Save changes to file
                        refreshRoomList();
                    }
                });
            }

            @Override
            protected void updateItem(Room item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    nameLabel.setText(item.toString());
                    setGraphic(content);
                }
            }
        });
    }
    
    
    
    private TabPane createSimpleTabPane() {
        TabPane tabPane = new TabPane();
        tabPane.setStyle("-fx-background-color: white;");
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        
        // Create tabs
        Tab mahallahTab = new Tab("Mahallahs", createMahallahTab());
        Tab blockTab = new Tab("Blocks", createBlockTab());
        Tab roomTab = new Tab("Rooms", createRoomTab());
        
        tabPane.getTabs().addAll(mahallahTab, blockTab, roomTab);
        return tabPane;
    }
    
    private VBox createMahallahTab() {
        VBox content = new VBox(15);
        content.setPadding(new Insets(20));
        
        // Header with add button
        HBox header = new HBox(15);
        header.setAlignment(Pos.CENTER_LEFT);
        
        Label title = new Label("Manage Mahallahs");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        
        Button btnAddMahallah = new Button("+ Add Mahallah");
        btnAddMahallah.setOnAction(this::handleAddMahallah);
        btnAddMahallah.setStyle("-fx-background-color: #007bff; -fx-text-fill: white;");
        
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        
        header.getChildren().addAll(title, spacer, btnAddMahallah);
        
        // Simple list view
        mahallahListView = new ListView<>();
        mahallahListView.setPrefHeight(350);
        mahallahListView.setStyle("-fx-border-color: #ddd; -fx-border-radius: 5;");
        setupMahallahListView();
        
        content.getChildren().addAll(header, mahallahListView);
        return content;
    }
    
    private VBox createBlockTab() {
        VBox content = new VBox(15);
        content.setPadding(new Insets(20));
        
        // Header with controls
        HBox header = new HBox(15);
        header.setAlignment(Pos.CENTER_LEFT);
        
        Label title = new Label("Manage Blocks");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        
        mahallahComboBox = new ComboBox<>();
        mahallahComboBox.setPromptText("Select Mahallah");
        mahallahComboBox.setPrefWidth(200);
        mahallahComboBox.setOnAction(this::handleMahallahSelection);
        
        Button btnAddBlock = new Button("+ Add Block");
        btnAddBlock.setOnAction(this::handleAddBlock);
        btnAddBlock.setStyle("-fx-background-color: #28a745; -fx-text-fill: white;");
        
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        
        header.getChildren().addAll(title, spacer, mahallahComboBox, btnAddBlock);
        
        // Simple list view
        blockListView = new ListView<>();
        blockListView.setPrefHeight(350);
        blockListView.setStyle("-fx-border-color: #ddd; -fx-border-radius: 5;");
        setupBlockListView();
        
        content.getChildren().addAll(header, blockListView);
        return content;
    }
    
    
    private VBox createRoomTab() {
        VBox content = new VBox(15);
        content.setPadding(new Insets(20));
        
        // Header with controls
        HBox header = new HBox(15);
        header.setAlignment(Pos.CENTER_LEFT);
        
        Label title = new Label("Manage Rooms");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        
        roomsMahallahComboBox = new ComboBox<>(); // Store reference
        roomsMahallahComboBox.setPromptText("Select Mahallah");
        roomsMahallahComboBox.setPrefWidth(150);
        roomsMahallahComboBox.setOnAction(this::handleMahallahForRooms);
        
        blockComboBox = new ComboBox<>();
        blockComboBox.setPromptText("Select Block");
        blockComboBox.setPrefWidth(120);
        blockComboBox.setOnAction(this::handleBlockSelection);
        
        Button btnAddRoom = new Button("+ Add Room");
        btnAddRoom.setOnAction(this::handleAddRoom);
        btnAddRoom.setStyle("-fx-background-color: #ffc107; -fx-text-fill: black;");
        
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        
        header.getChildren().addAll(title, spacer, roomsMahallahComboBox, blockComboBox, btnAddRoom);
        
        // Sync combo boxes
        roomsMahallahComboBox.setItems(FXCollections.observableArrayList(settingsManager.getMahallahList()));
        
        // Simple list view
        roomListView = new ListView<>();
        roomListView.setPrefHeight(350);
        roomListView.setStyle("-fx-border-color: #ddd; -fx-border-radius: 5;");
        setupRoomListView();
        
        content.getChildren().addAll(header, roomListView);
        return content;
    }
    
    // Event Handlers (No Lambdas)
    private void handleBackToMenu(ActionEvent event) {
        System.out.println("Back button clicked!"); // Debug
        app.setScene(app.getMahallahMenu());
    }
    
    private void handleAddMahallah(ActionEvent event) {
        showAddMahallahDialog();
    }
    
    private void handleAddBlock(ActionEvent event) {
        showAddBlockDialog();
    }
    
    private void handleAddRoom(ActionEvent event) {
        showAddRoomDialog();
    }
    
    private void handleMahallahSelection(ActionEvent event) {
        selectedMahallah = mahallahComboBox.getValue();
        refreshBlockList();
    }
    
    private void handleBlockSelection(ActionEvent event) {
        selectedBlock = blockComboBox.getValue();
        refreshRoomList();
    }
    
    private void handleMahallahForRooms(ActionEvent event) {
        ComboBox<Mahallah> source = (ComboBox<Mahallah>) event.getSource();
        selectedMahallah = source.getValue();
        refreshBlockComboBox();
    }
    
    // Simple Dialog Methods
    private void showAddMahallahDialog() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add Mahallah");
        dialog.setHeaderText("Enter Mahallah Name");
        dialog.setContentText("Name:");
        
        dialog.showAndWait().ifPresent(name -> {
            if (!name.trim().isEmpty()) {
                Mahallah newMahallah = new Mahallah(name);
                settingsManager.getMahallahList().add(newMahallah);
                
                // Save to file
                settingsManager.saveMahallahs();
                
                refreshMahallahList();
                showSimpleAlert("Success", "Mahallah added successfully!");
            }
        });
    }
    
    private void showEditMahallahDialog(Mahallah m) {
        TextInputDialog dialog = new TextInputDialog(m.getName());
        dialog.setTitle("Edit Mahallah");
        dialog.setHeaderText("Rename Mahallah");
        dialog.setContentText("New name:");
        dialog.showAndWait().ifPresent(name -> {
            if (!name.trim().isEmpty()) {
                m.setName(name.trim());
                settingsManager.saveMahallahs(); // Save changes to file
                refreshMahallahList();
                showSimpleAlert("Updated", "Mahallah renamed");
            }
        });
    }
    
    private void showAddBlockDialog() {
        if (selectedMahallah == null) {
            showSimpleAlert("Error", "Please select a Mahallah first!");
            return;
        }
        
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add Block");
        dialog.setHeaderText("Enter Block Name for " + selectedMahallah.getName());
        dialog.setContentText("Block Name:");
        
        dialog.showAndWait().ifPresent(name -> {
            if (!name.trim().isEmpty()) {
                char blockChar = name.toUpperCase().charAt(0);
                Block newBlock = new Block(blockChar);
                selectedMahallah.addBlock(newBlock);
                settingsManager.saveMahallahs(); // Save changes to file
                refreshBlockList();
                showSimpleAlert("Success", "Block added successfully!");
            }
        });
    }

    private void showEditBlockDialog(Block b) {
        TextInputDialog dialog = new TextInputDialog(String.valueOf(b.getBlockName()));
        dialog.setTitle("Edit Block");
        dialog.setHeaderText("Rename Block");
        dialog.setContentText("New block letter:");
        dialog.showAndWait().ifPresent(name -> {
            if (!name.trim().isEmpty()) {
                b.setBlockName(name.toUpperCase().charAt(0));
                settingsManager.saveMahallahs(); // Save changes to file
                refreshBlockList();
                showSimpleAlert("Updated", "Block renamed");
            }
        });
    }
    
    private void showAddRoomDialog() {
        if (selectedBlock == null) {
            showSimpleAlert("Error", "Please select a Block first!");
            return;
        }
        
        Dialog<Room> dialog = new Dialog<>();
        dialog.setTitle("Add Room");
        dialog.setHeaderText("Enter room details for Block " + selectedBlock.getBlockName());
        
        ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);
        
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        
        TextField roomField = new TextField();
        TextField floorField = new TextField();
        TextField compartmentField = new TextField();
        
        grid.add(new Label("Room Number:"), 0, 0);
        grid.add(roomField, 1, 0);
        grid.add(new Label("Floor:"), 0, 1);
        grid.add(floorField, 1, 1);
        grid.add(new Label("Compartment:"), 0, 2);
        grid.add(compartmentField, 1, 2);
        
        dialog.getDialogPane().setContent(grid);
        
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                return new Room(roomField.getText(), floorField.getText(), compartmentField.getText());
            }
            return null;
        });
        
        dialog.showAndWait().ifPresent(room -> {
            selectedBlock.addRoom(room);
            settingsManager.saveMahallahs(); // Save changes to file
            refreshRoomList();
            showSimpleAlert("Success", "Room added successfully!");
        });
    }

    private void showEditRoomDialog(Room r) {
        TextInputDialog dialog = new TextInputDialog(r.getRoomNumber());
        dialog.setTitle("Edit Room");
        dialog.setHeaderText("Rename Room");
        dialog.setContentText("New room number:");
        dialog.showAndWait().ifPresent(name -> {
            if (!name.trim().isEmpty()) {
                r.setRoomNumber(name.trim());
                settingsManager.saveMahallahs(); // Save changes to file
                refreshRoomList();
                showSimpleAlert("Updated", "Room renamed");
            }
        });
    }
    
    // Utility Methods
    private void showSimpleAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    // Refresh Methods
    private void refreshMahallahList() {
        ObservableList<Mahallah> items = FXCollections.observableArrayList(settingsManager.getMahallahList());
        if (mahallahListView != null) {
            mahallahListView.setItems(items);
        }
        if (mahallahComboBox != null) {
            mahallahComboBox.setItems(items);
        }
        if (roomsMahallahComboBox != null) {
            roomsMahallahComboBox.setItems(items);
        }
    }
    
    private void refreshBlockList() {
        if (selectedMahallah != null && blockListView != null) {
            ObservableList<Block> items = FXCollections.observableArrayList(selectedMahallah.getBlocks());
            blockListView.setItems(items);
        }
    }
    
    private void refreshBlockComboBox() {
        if (selectedMahallah != null && blockComboBox != null) {
            ObservableList<Block> items = FXCollections.observableArrayList(selectedMahallah.getBlocks());
            blockComboBox.setItems(items);
        }
    }
    
    private void refreshRoomList() {
        if (selectedBlock != null && roomListView != null) {
            ObservableList<Room> items = FXCollections.observableArrayList(selectedBlock.getRooms());
            roomListView.setItems(items);
        }
    }
}