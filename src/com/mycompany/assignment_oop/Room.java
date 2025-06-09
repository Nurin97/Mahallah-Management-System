/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment_oop;

/**
 *
 * @author LENOVO
 */
import java.util.Scanner;

public class Room 
{
    private String roomNumber;
    private String floor;
    private String compartment;

    // ✅ Constructor with compartment
    public Room(String roomNumber, String floor, String compartment) {
        this.roomNumber = roomNumber;
        this.floor = floor;
        this.compartment = compartment;
    }

    // ✅ Constructor without compartment (for SettingsManager use)
    public Room(String roomNumber, String floor) {
        this.roomNumber = roomNumber;
        this.floor = floor;
        this.compartment = "-"; // default value
    }

    // Getters
    public String getRoomNumber() {
        return roomNumber;
    }
    @Override
    public String toString() {
        return getRoomNumber(); 
    }

    public String getFloor() {
        return floor;
    }

    public String getCompartment() {
        return compartment;
    }

    public void setCompartment(String compartment) {
        this.compartment = compartment;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }
    public void setFloor(String floor) {
        this.floor = floor;
    }

    // Display method
    public void display() {
        System.out.println("Room: " + roomNumber + " | Floor: " + floor + " | Compartment: " + compartment);
    }

    // Static input methods
    public static String chooseRoom(Scanner sc) {
        System.out.print("Enter room number: ");
        return sc.nextLine();
    }

    public static String chooseCompartment(Scanner sc) {
        System.out.print("Enter compartment: ");
        return sc.nextLine();
    }
}
