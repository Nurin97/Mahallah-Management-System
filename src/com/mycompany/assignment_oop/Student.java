/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment_oop;

/**
 *
 * @author LENOVO
 */


public class Student extends Person 
{
    private String kulliyah;

    private String mahallahName;
    private String blockName;
    private String floor;
    private int roomNum;
    private String compartment;

    public Student(String name, int ID, String email, int phoneNumber, String kulliyah,
                   String mahallahName, String blockName, String floor, int roomNum, String compartment) {
        super(name, ID, email, phoneNumber);
        this.kulliyah = kulliyah;
        this.mahallahName = mahallahName;
        this.blockName = blockName;
        this.floor = floor;
        this.roomNum = roomNum;
        this.compartment = compartment;
    }

    @Override
    public void displayInfo() {
        System.out.println("Student Name: " + name);
        System.out.println("ID: " + ID);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phoneNumber);
        System.out.println("Kulliyah: " + kulliyah);
        System.out.println("Location: Mahallah " + mahallahName + ", Block " + blockName + ", Floor " + floor + ", Room " + roomNum + ", Compartment " + compartment);
    }
    
    // getters & setters untuk lokasi
    public String getMahallahName() { return mahallahName; }
    public void setMahallahName(String m) { this.mahallahName = m; }

    public String getBlockName() { return blockName; }
    public void setBlockName(String b) { this.blockName = b; }

    public String getFloor() { return floor; }
    public void setFloor(String f) { this.floor = f; }

    public int getRoomNum() { return roomNum; }
    public void setRoomNum(int r) { this.roomNum = r; }

    public String getCompartment() { return compartment; }
    public void setCompartment(String c) { this.compartment = c; }
    
    public String getKulliyah() {
        return kulliyah;
    }
    
    @Override
    public String toFileString() {
        return name + ", " + ID + ", " + email + ", " + phoneNumber + ", " + kulliyah + ", "
        + mahallahName + ", " + blockName + ", " + floor + ", " + roomNum + ", " + compartment;
    }
}
