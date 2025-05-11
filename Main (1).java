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

public class Main 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        Registration reg = new Registration();

        int choice;
        
        System.out.println("Welcome to Mahallah Management System");

        do{
            System.out.println();
            System.out.println("Choose the service:");
            System.out.println("1. Register");
            System.out.println("2. Remove");
            System.out.println("3. View Student Information");
            System.out.println("4. View Staff Information");
            System.out.println("5. Exit");
            System.out.println();
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) 
            {
                case 1:
                    reg.registerMenu(sc);
                    break;
                case 2:
                    reg.removeMenu(sc);
                    break;
                case 3:
                    viewPersonInfo(sc, "student", reg);
                    break;
                case 4:
                    viewPersonInfo(sc, "staff", reg);
                    break;
                case 5:
                    System.out.println("Thank you for using Mahallah Management System!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } 
        while (choice != 5);
    }

    public static void viewPersonInfo(Scanner sc, String type, Registration reg) 
    {
        
        String mahallah = Mahallah.chooseMahallah(sc);
        System.out.println("\n" + type.toUpperCase() + " in Mahallah " + mahallah + ":");

        if (type.equals("student")) //student info
        {
            if (reg.getStudentList().isEmpty()) 
            {
                System.out.println("No students registered.");
            } 
            else 
            {
                reg.listPeople(reg.getStudentList());
            }
        } 
        else if (type.equals("staff")) //staff info
        {
            if (reg.getStaffList().isEmpty()) 
            {
                System.out.println("No staff registered.");
            } 
            else 
            {
                reg.listPeople(reg.getStaffList());
            }
        }
    }
}
