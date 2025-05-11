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
public class Staff extends Person 
{
    private String position;//jawatan
    private String mahallah;

    public Staff(String name, int ID, String email, int phoneNumber, String position, String mahallah) 
    {
        super(name, ID, email, phoneNumber);
        this.position = position;
        this.mahallah = mahallah;
    }

    @Override
    public void displayInfo() 
    {
        System.out.println("Staff Name: " + name);
        System.out.println("Staff number: " + ID);
        System.out.println("Email: " + email);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Position: " + position);
        System.out.println("Mahallah: " + mahallah);
    }

    @Override
    public String toFileString() 
    {
        return "Staff," + super.toFileString() + "," + position + "," + mahallah;  // Include Mahallah
    }
}
