/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment_oop;

/**
 *
 * @author LENOVO
 */
import java.util.ArrayList;
import java.util.Scanner;

public class Mahallah 
{
    private String name;
    private ArrayList<Block> blocks;

    public Mahallah(String name, String status) {
        this.name = name;
        this.status = status;
        this.blocks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public void addBlock(Block block) {
        blocks.add(block);
    }

    public boolean removeBlock(char blockName) {
        for (int i = 0; i < blocks.size(); i++) {
        if (blocks.get(i).getBlockName() == blockName) {
            blocks.remove(i);
            return true;
            }
        }
        return false;
    }
   
    public Block getBlock(char blockName) {
        for (Block b : blocks) {
            if (b.getBlockName() == blockName) {
                return b;
            }
        }
        return null;
    }

    public void display() {
        System.out.println("Mahallah: " + name);
        if (blocks.isEmpty()) {
            System.out.println("  No blocks.");
        } else {
            for (Block b : blocks) {
                b.displayRooms();
            }
        }
    }

    public void displayBlocks() {
        if (blocks.isEmpty()) {
            System.out.println("No blocks.");
        } else {
            System.out.print("Blocks: ");
            for (Block b : blocks) {
                System.out.print(b.getBlockName() + " ");
            }
            System.out.println();
        }
    }

    // choose mahallah from SettingsManager
    public static Mahallah chooseMahallah(Scanner sc) {
    ArrayList<Mahallah> mahallahList = SettingsManager.getInstance().getMahallahList();

    if (mahallahList.isEmpty()) {
        System.out.println("No Mahallahs available. Please add one in Settings first.");
        return null;
    }

    System.out.println("\nChoose a Mahallah:");
    for (int i = 0; i < mahallahList.size(); i++) {
        System.out.println((i + 1) + ". " + mahallahList.get(i).getName());
    }

    int choice = -1;
    while (true) {
        System.out.print("Enter your choice (1-" + mahallahList.size() + "): ");
        if (sc.hasNextInt()) {
            choice = sc.nextInt();
            sc.nextLine(); 
            if (choice >= 1 && choice <= mahallahList.size()) {
                break;
            } else {
                System.out.println("Invalid choice. Please enter a number between 1 and " + mahallahList.size() + ".");
            }
        } else {
            System.out.println("Invalid input. Please enter a valid number.");
            sc.nextLine();
        }
    }

    return mahallahList.get(choice - 1);
}
    
    // choose block from selected mahallah 
    public static Block chooseBlock(Scanner sc, Mahallah mahallah) {
        ArrayList<Block> blocks = mahallah.getBlocks();

        if (blocks.isEmpty()) {
            System.out.println("No blocks available in this Mahallah.");
            return null;
        }

        System.out.println("Choose Block:");
        for (int i = 0; i < blocks.size(); i++) {
            System.out.println((i + 1) + ". Block " + blocks.get(i).getBlockName());
        }

        System.out.print("Enter choice (1-" + blocks.size() + "): ");
        int choice = -1;
        while (true) {
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                sc.nextLine();
                if (choice >= 1 && choice <= blocks.size()) {
                    break;
                } else {
                    System.out.print("Invalid choice. Enter 1 to " + blocks.size() + ": ");
                }
            } else {
                System.out.print("Invalid input. Enter a number: ");
                sc.nextLine();
            }
        }

        return blocks.get(choice - 1);
    }

    public static String chooseFloor(Scanner sc) {
        System.out.println("Choose Floor:");
        System.out.println("1. Ground");
        System.out.println("2. First");
        System.out.println("3. Second");
        System.out.print("Enter choice (1-3): ");
        int choice = -1;
        while(true){
            if(sc.hasNextInt()){
                choice = sc.nextInt();
                sc.nextLine();
                if(choice >=1 && choice <= 3) break;
                else System.out.print("Invalid choice. Enter 1 to 3: ");
            } else {
                System.out.print("Invalid input. Enter a number: ");
                sc.nextLine();
            }
        }

        switch(choice){
            case 1: return "Ground";
            case 2: return "First";
            case 3: return "Second";
            default: return "Ground"; 
        }
    }

    public static Room chooseRoom(Scanner sc, Block block, String floor) {
        if (block == null) {
            System.out.println("Block not selected.");
            return null;
        }

        ArrayList<Room> roomsOnFloor = block.getRoomsByFloor(floor);

        if (roomsOnFloor.isEmpty()) {
            System.out.println("No rooms available on this floor.");
            return null;
        }

        System.out.println("Choose Room:");
        for (int i = 0; i < roomsOnFloor.size(); i++) {
            System.out.println((i + 1) + ". Room " + roomsOnFloor.get(i).getRoomNumber());
        }

        System.out.print("Enter choice (1-" + roomsOnFloor.size() + "): ");
        int choice = -1;
        while (true) {
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                sc.nextLine();
                if (choice >= 1 && choice <= roomsOnFloor.size()) {
                    break;
                } else {
                    System.out.print("Invalid choice. Enter 1 to " + roomsOnFloor.size() + ": ");
                }
            } else {
                System.out.print("Invalid input. Enter a number: ");
                sc.nextLine();
            }
        }

        return roomsOnFloor.get(choice - 1);
    }

    private String status = "Available"; // 🟢 Default

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
