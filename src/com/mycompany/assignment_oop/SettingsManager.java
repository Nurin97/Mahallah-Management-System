package com.mycompany.assignment_oop;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SettingsManager {
    
    private static SettingsManager instance = null;
    private ArrayList<Mahallah> mahallahList;
    private final String FILE_NAME = "src/data/mahallahs.txt";

    private SettingsManager() {
        mahallahList = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (file.exists()) {
            loadMahallahs();
        }
        if (mahallahList.isEmpty()) {
            System.out.println("[DEBUG] Initializing default Mahallahs...");
            initializeDefaultMahallahs();
            saveMahallahs(); 
        } else {
            boolean hasRooms = false;
            for (Mahallah m : mahallahList) {
                for (Block b : m.getBlocks()) {
                        if (!b.getRooms().isEmpty()) {
                        hasRooms = true;
                        break;
                        }
                }
                if (hasRooms) break;
            }
                if (!hasRooms) {
                    mahallahList.clear();
                    initializeDefaultMahallahs();
                    saveMahallahs();
                }
        }
    }

    public static SettingsManager getInstance() {
        if (instance == null) {
            instance = new SettingsManager();
        }
        return instance;
    }

    public ArrayList<Mahallah> getMahallahList() {
        return mahallahList;
    }

    public void settingsMenu(Scanner sc) {
        int choice;
        do {
            System.out.println("\n===== Settings Menu =====");
            System.out.println("1. Modify Mahallah");
            System.out.println("2. Modify Block");
            System.out.println("3. Modify Room");
            System.out.println("4. Exit Settings");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> mahallahMenu(sc);
                case 2 -> blockMenu(sc);
                case 3 -> roomMenu(sc);
                case 4 -> System.out.println("Exiting Settings...");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 4);
    }

    private void mahallahMenu(Scanner sc) {
        int choice;
        do {
            System.out.println("\n--- Mahallah Menu ---");
            System.out.println("1. Add Mahallah");
            System.out.println("2. Remove Mahallah");
            System.out.println("3. View All Mahallahs");
            System.out.println("4. Back");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter new Mahallah name: ");
                    String name = sc.nextLine();
                    if (findMahallahByName(name) == null) {
                        Mahallah newMahallah = new Mahallah(name);
                        mahallahList.add(newMahallah);
                        saveMahallahs();
                        System.out.println("Mahallah added.");    
                        newMahallah.display();
                    } else {
                        System.out.println("Mahallah already exists.");
                    }
                }
                case 2 -> {
                    Mahallah m = selectMahallah(sc);
                    if (m != null) {
                        mahallahList.remove(m);
                        saveMahallahs();
                        System.out.println("Mahallah removed.");
                    }
                }
                case 3 -> viewAllMahallahs();
                case 4 -> System.out.println("Returning...");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 4);
    }

    private void blockMenu(Scanner sc) {
        Mahallah m = selectMahallah(sc);
        if (m == null) return;

        int choice;
        do {
            System.out.println("\n--- Block Menu (" + m.getName() + ") ---");
            System.out.println("1. Add Block");
            System.out.println("2. Remove Block");
            System.out.println("3. View Blocks");
            System.out.println("4. Back");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter block name (A-Z): ");
                    char blockChar = sc.nextLine().toUpperCase().charAt(0);
                    if (m.getBlock(blockChar) == null) {
                        m.addBlock(new Block(blockChar));
                        saveMahallahs();
                        System.out.println("Block added.");
                    } else {
                        System.out.println("Block already exists.");
                    }
                }
                case 2 -> {
                    System.out.print("Enter block name to remove: ");
                    char blockChar = sc.nextLine().toUpperCase().charAt(0);
                    if (m.removeBlock(blockChar)) {
                        saveMahallahs();
                        System.out.println("Block removed.");
                    } else {
                        System.out.println("Block not found.");
                    }
                }
                case 3 -> m.displayBlocks();
                case 4 -> System.out.println("Returning...");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 4);
    }

    private void roomMenu(Scanner sc) {
        Mahallah m = selectMahallah(sc);
        if (m == null) return;
        Block b = selectBlock(sc, m);
        if (b == null) return;

        int choice;
        do {
            System.out.println("\n--- Room Menu (Block " + b.getBlockName() + ") ---");
            System.out.println("1. Add Room");
            System.out.println("2. Remove Room");
            System.out.println("3. View Rooms");
            System.out.println("4. Back");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter new room number: ");
                    String roomNo = sc.nextLine();
                    System.out.print("Enter floor of the room: ");
                    String floor = sc.nextLine();
                    b.addRoom(new Room(roomNo, floor));
                    saveMahallahs();
                    System.out.println("Room added.");
                }
                case 2 -> {
                    System.out.print("Enter room number to remove: ");
                    String roomNo = sc.nextLine();
                    if (b.removeRoom(roomNo)) {
                        saveMahallahs();
                        System.out.println("Room removed.");
                    } else {
                        System.out.println("Room not found.");
                    }
                }
                case 3 -> b.displayRooms();
                case 4 -> System.out.println("Returning...");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 4);
    }

    public void viewAllMahallahs() {
        if (mahallahList.isEmpty()) {
            System.out.println("No mahallahs yet.");
        } else {
            for (Mahallah m : mahallahList) {
                m.display();
                System.out.println("==============");
            }
        }
    }

    private Mahallah findMahallahByName(String name) {
        for (Mahallah m : mahallahList) {
            if (m.getName().equalsIgnoreCase(name)) return m;
        }
        return null;
    }

    private Mahallah selectMahallah(Scanner sc) {
        if (mahallahList.isEmpty()) {
            System.out.println("No mahallahs available.");
            return null;
        }

        System.out.println("Select a Mahallah:");
        for (int i = 0; i < mahallahList.size(); i++) {
            System.out.println((i + 1) + ". " + mahallahList.get(i).getName());
        }

        int choice = sc.nextInt();
        sc.nextLine();
        if (choice >= 1 && choice <= mahallahList.size()) {
            return mahallahList.get(choice - 1);
        } else {
            System.out.println("Invalid choice.");
            return null;
        }
    }

    private Block selectBlock(Scanner sc, Mahallah mahallah) {
        if (mahallah.getBlocks().isEmpty()) {
            System.out.println("No blocks in this mahallah.");
            return null;
        }

        mahallah.displayBlocks();
        System.out.print("Enter block name: ");
        char blockChar = sc.nextLine().toUpperCase().charAt(0);
        return mahallah.getBlock(blockChar);
    }

    private void loadMahallahs() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            mahallahList.clear();

            while ((line = br.readLine()) != null) {
                System.out.println("Reading line: " + line);
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String name = parts[0].trim();
                    Mahallah m = new Mahallah(name);
                    
                    String[] blockParts = parts[1].split(",");
                    for (String blockData : blockParts) {
                        if (blockData.contains("[") && blockData.contains("]")) {
                            String blockName = blockData.substring(0, blockData.indexOf('[')).trim();
                            String roomData = blockData.substring(blockData.indexOf('[') + 1, blockData.indexOf(']'));

                            Block block = new Block(blockName.charAt(0));

                            if (!roomData.isEmpty()) {
                                String[] roomParts = roomData.split(";");
                                for (String roomEntry : roomParts) {
                                    String[] roomDetails = roomEntry.split("-");
                                    if (roomDetails.length >= 3) {
                                        String roomNo = roomDetails[0];
                                        String floor = roomDetails[1];
                                        String compartment = roomDetails[2];
                                        block.addRoom(new Room(roomNo, floor, compartment));
                                    }
                                }
                            }
                            m.addBlock(block);
                        } else {
                            System.out.println("⚠️ Skipping invalid block data: " + blockData);
                        }
                    }
                    mahallahList.add(m);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading Mahallahs: " + e.getMessage());
        }
    }

    public void saveMahallahs() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Mahallah m : mahallahList) {
                StringBuilder sb = new StringBuilder();
                sb.append(m.getName()).append(":");
                
                ArrayList<Block> blocks = m.getBlocks();
                for (int i = 0; i < blocks.size(); i++) {
                    Block b = blocks.get(i);
                    sb.append(b.getBlockName()).append("[");
                    
                    ArrayList<Room> rooms = b.getRooms();
                    for (int j = 0; j < rooms.size(); j++) {
                        Room r = rooms.get(j);
                        sb.append(r.getRoomNumber()).append("-")
                            .append(r.getFloor()).append("-") 
                            .append(r.getCompartment());
                        
                        if (j < rooms.size() - 1) sb.append(";");
                        }

                        sb.append("]");
                        if (i < blocks.size() - 1) sb.append(",");
                    }

                    bw.write(sb.toString());
                    bw.newLine();
                }
            
        } catch (IOException e) {
            System.out.println("Error saving Mahallahs: " + e.getMessage());
        }
    }

    private void initializeDefaultMahallahs() {
        String[] names = {"Faruq", "Siddiq", "Bilal", "Aminah", "Asiah", "Hafsa"};

        for (String name : names) {
            Mahallah m = new Mahallah(name);
            m.addBlock(new Block('A'));
            m.addBlock(new Block('B'));
            m.addBlock(new Block('C'));
            m.addBlock(new Block('D'));
            if (name.equalsIgnoreCase("Faruq")) {
                Block b = m.getBlock('B');
                if (b != null) b.addRoom(new Room("201", "2", "A"));
            } else if (name.equalsIgnoreCase("Siddiq")) {
                Block b = m.getBlock('B');
                if (b != null) b.addRoom(new Room("202", "2", "B"));
            } else if (name.equalsIgnoreCase("Aminah")) {
                Block b = m.getBlock('C');
                if (b != null) b.addRoom(new Room("103", "1", "C"));
            } else if (name.equalsIgnoreCase("Bilal")) {
                Block bA = m.getBlock('A');
                Block bB = m.getBlock('B');
                if (bA != null) bA.addRoom(new Room("305", "3", "B"));
                if (bB != null) bB.addRoom(new Room("101", "1", "C"));
            } else if (name.equalsIgnoreCase("Asiah")) {
                Block b = m.getBlock('D');
                if (b != null) b.addRoom(new Room("410", "4","D"));
            }

            mahallahList.add(m);
        }
    }
}
