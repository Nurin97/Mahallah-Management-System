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
import java.util.Scanner;

public class Room 
{
    private int num;
    private int level;
    private String compartment;
    private boolean status; // true = occupied, false = vacant

    public Room(int num, int level, String compartment, boolean status) 
    {
        this.num = num;
        this.level = level;
        this.compartment = compartment;
        this.status = status;
    }

    public void statusUpdate(boolean status) 
    {
        this.status = status;
    }

    public void display() 
    {
        System.out.println("Room Number: " + num);
        System.out.println("Level: " + level);
        System.out.println("Compartment: " + compartment);
        System.out.println("Occupied: " + status);
    }

    public String toFileString() 
    {
        return num + "," + level + "," + compartment + "," + status;
    }

    //choose room 
    public static int chooseRoom(Scanner sc) 
    {
        System.out.print("\nEnter room number: ");
        return sc.nextInt();
    }

    public static String chooseCompartment(Scanner sc) 
{
    System.out.println();
    sc.nextLine();  
    System.out.print("\nEnter compartment (A, B, C, D): ");
    return sc.nextLine();
}

}
