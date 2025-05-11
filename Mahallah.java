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
    private char block;
    private ArrayList<Room> rooms;

    // Updated list of Mahallah names
    static final String[] mahallahNames = {
        "Faruq", "Uthman", "Ali", "Bilal", "Siddiq", "Zubair", "Salahuddin", "Aminah",
        "Salahuddin", "Asiah", "Sumayyah", "Asma", "Maryam", "Halimah", "Hafsah",
        "Safiyyah", "Ruqayyah"
    };

    public Mahallah(String name, char block) {
        this.name = name;
        this.block = block;
        this.rooms = new ArrayList<>();
    }

    // Add a room to the Mahallah
    public void addRoom(Room room) {
        rooms.add(room);
    }

    // Get all rooms in this Mahallah
    public ArrayList<Room> getRooms() {
        return rooms;
    }

    // Display Mahallah info and its rooms
    public void display() {
        System.out.println("Mahallah Name: " + name);
        System.out.println("Block: " + block);
        System.out.println("Rooms:");
        for (Room r : rooms) {
            r.display();
            System.out.println("-----");
        }
    }

    // Static method to choose Mahallah name
    public static String chooseMahallah(Scanner sc) {
        System.out.println("\nChoose Mahallah:");
        for (int i = 0; i < mahallahNames.length; i++) {
            System.out.println((i + 1) + ". " + mahallahNames[i]);
        }
        System.out.print("Enter choice (1-" + mahallahNames.length + "): ");
        int choice = sc.nextInt();
        if (choice >= 1 && choice <= mahallahNames.length) {
            return mahallahNames[choice - 1];
        } else {
            System.out.println("Invalid choice. Defaulting to: " + mahallahNames[0]);
            return mahallahNames[0];
        }
    }

    // Static method to choose block letter
    public static String chooseBlock(Scanner sc) {
        System.out.println("\nChoose Block (A-G):");
        for (char c = 'A'; c <= 'G'; c++) {
            System.out.println(c);
        }
        System.out.print("Enter block letter: ");
        return sc.next().toUpperCase();
    }

    // Static method to choose floor
    public static String chooseFloor(Scanner sc) {
        System.out.println("\nChoose Floor:");
        System.out.println("1. Ground Floor");
        System.out.println("2. Floor 1");
        System.out.println("3. Floor 2");
        System.out.println("4. Floor 3");
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();
        switch (choice) {
            case 1: return "Ground Floor";
            case 2: return "Floor 1";
            case 3: return "Floor 2";
            case 4: return "Floor 3";
            default: return "Ground Floor";
        }
    }
}
