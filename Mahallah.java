/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment_oop;

/**
 *
 * @author LENOVO
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Mahallah 
{
    private String name;
    private char block;
    private ArrayList<Room> rooms;

    //nama mahallah 
    static final String[] mahallahNames = 
    {
        "Faruq", "Uthman", "Ali", "Bilal", "Siddiq", "Zubair", "Salahuddin", "Aminah",
        "Salahuddin", "Asiah", "Sumayyah", "Asma", "Maryam", "Halimah", "Hafsah",
        "Safiyyah", "Ruqayyah"
    };

    public Mahallah(String name, char block) 
    {
        this.name = name;
        this.block = block;
        this.rooms = new ArrayList<>();
    }
 
    public void addRoom(Room room) 
    {
        rooms.add(room);
    }

    public ArrayList<Room> getRooms() 
    {
        return rooms;
    }

    public void display() 
    {
        System.out.println("Mahallah Name: " + name);
        System.out.println("Block: " + block);
        System.out.println("Rooms:");
        for (Room r : rooms) 
        {
            r.display();
            System.out.println("-----");
        }
    }

    
    // mahallah name
    public static String chooseMahallah(Scanner sc) 
    {
        System.out.println("\nChoose Mahallah:");
        for (int i = 0; i < mahallahNames.length; i++) 
        {
            System.out.println((i + 1) + ". " + mahallahNames[i]); 
        }
        System.out.print("\nEnter mahallah choice\n ");
        int choice = sc.nextInt();
        
        if (choice >= 1 && choice <= mahallahNames.length) 
        {
            return mahallahNames[choice - 1];
        } 
        else 
        {
            System.out.println("Invalid choice, please enter a correct choice ");
            return mahallahNames[0];
        }
    }

    // block mahallah
   public static String chooseBlock(Scanner sc) 
{
    char block;
    while (true) 
    {
        System.out.println("\nThe available blocks are (A-G): "); //print block
        for (char c = 'A'; c <= 'G'; c++) 
        {
            System.out.println(c);
        }
        System.out.print("Choose the block: ");
        
        block = sc.next().toUpperCase().charAt(0);  

        if (block >= 'A' && block <= 'G') //kalau salah enter valid
        {
            return String.valueOf(block); 
        } 
        else 
        {
            System.out.println("Invalid block. Please enter a valid block (A-G).");
        }
    }
}


    // floor mahallah
    public static String chooseFloor(Scanner sc) 
    {
        System.out.println("\nChoose Floor:");
        System.out.println("1. Ground Floor");
        System.out.println("2. Floor 1");
        System.out.println("3. Floor 2");
        System.out.println("4. Floor 3");
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();
        switch (choice) 
        {
            case 1: return "Ground Floor";
            case 2: return "Floor 1";
            case 3: return "Floor 2";
            case 4: return "Floor 3";
            default: return "Ground Floor";
        }
    }
}
